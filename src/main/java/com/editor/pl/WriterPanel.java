package com.editor.pl;
import com.editor.pl.*;
import com.editor.pl.panel.*;
import com.editor.exception.*;
import com.editor.interfaces.*;
import com.editor.model.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.text.Highlighter.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class WriterPanel extends JPanel implements DocumentListener,FileInterface
{
private JTextArea textArea,lineCountArea;
private Container container;
private JScrollPane scrollPane;
private Dimension d;
private String name="";
private ErrorPanel errorPanel;
private File file;
private boolean state=false;
private Highlighter highlighter;
private HighlightPainter redPainter,greenPainter;
private int rows,columns,oldTextSize,newTextSize;
private Font font;
public WriterPanel(ErrorPanel errorPanel)
{
this.errorPanel=errorPanel;
this.setBorder(new TitledBorder(new EtchedBorder(),"New File"));
this.setBackground(new Color(255,128,0));	
file=new File(FILE_NAME);
lineCountArea=new JTextArea("");
lineCountArea.setBackground(Color.LIGHT_GRAY);
lineCountArea.setEditable(false);
textArea=new JTextArea();
textArea.setLineWrap(false);
scrollPane=new JScrollPane(textArea);
scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
scrollPane.setViewportBorder(new LineBorder(Color.red));
this.add(scrollPane);
this.textArea.setEditable(false);
scrollPane.setRowHeaderView(lineCountArea);
scrollPane.setWheelScrollingEnabled(true);
textArea.getDocument().addDocumentListener(this);
this.setVisible(true);
redPainter=new DefaultHighlighter.DefaultHighlightPainter(Color.red);
greenPainter=new DefaultHighlighter.DefaultHighlightPainter(Color.green);
highlighter=textArea.getHighlighter();
highlighter.removeAllHighlights();
lineCountArea.setForeground(Color.black);
this.font=textArea.getFont();
}


public void setAutomaticCompilation(boolean state)
{
this.state=state;
if(!state)
{
this.updateLineCountArea();
lineCountArea.setForeground(Color.black);
highlighter.removeAllHighlights();
}
}

public boolean getAutomaticCompilation()
{
return this.state;
}

public void setTextAreaSize(int rows,int columns)
{
this.rows=rows;
this.columns=columns;
textArea.setRows(rows);
textArea.setColumns(columns);
}

public String getTextAreaContents()
{
return textArea.getText();
}

public void preparingForCompilation() throws DAOException 
{
TaskModel.copyContentsForCompilation(this.getFileName(),textArea.getText());
}

public void setColorOfTextArea(Color color)
{
textArea.setForeground(color);
}

public void setFormatOfTextArea(Font font)
{
this.font=null;
this.font=font;
if(font.getSize()>12)
{
textArea.setRows(textArea.getRows()-1);
textArea.setColumns(textArea.getColumns()-1);
}
else
{
textArea.setRows(this.rows);
textArea.setColumns(this.columns);
}
textArea.setFont(this.font);
lineCountArea.setFont(new Font(this.font.getFontName(),this.font.getStyle(),this.font.getSize()-1));
this.errorPanel.setFormatOfTextArea(this.font);
textArea.repaint();
lineCountArea.repaint();
}

public Font getFormatOfTextArea()
{
return this.font;
}

public void setTextAreaContents(String contents)
{
textArea.setText(contents);
}

public void setTextAreaEditable(boolean order)
{
textArea.setEditable(order);
}

public String getTextSize(){
String text ="";
Element root = textArea.getDocument().getDefaultRootElement();
this.newTextSize=root.getElementCount();
for(int i = 1; i < root.getElementCount(); i++)
{
text += i +" "+ System.getProperty("line.separator");
}
return text;
}

public void changedUpdate(DocumentEvent de) 
{
if(state)
this.updateTextArea();
else
this.updateLineCountArea();
}

public void insertUpdate(DocumentEvent de) 
{
if(state)
this.updateTextArea();
else
this.updateLineCountArea();
}

public void removeUpdate(DocumentEvent de) 
{
if(state)
this.updateTextArea();
else
this.updateLineCountArea();
}

public void setFileName(String name)
{
this.name=name;
}

public String getFileName()
{
return name;
}


public void updateTextArea()
{
try{
TaskModel.copyContentsForCompilation(this.getFileName().trim(),textArea.getText());
this.updateLineCountArea();
}catch(Exception e)
{
this.updateLineCountArea();
}
lineCountArea.setText(getTextSize());
try{
this.errorPanel.updateErrors(file,this.getFileName());
}
catch(Exception ee){
this.errorPanel.setTextAreaContents("Some Error in processing!! - "+ee.getMessage());
}
try{
this.highlightErrors();
}
catch(Exception ee){
System.out.println(ee);
ee.printStackTrace();
}
}


public void updateLineCountArea()
{
if(state)
lineCountArea.setForeground(Color.red);
else
lineCountArea.setForeground(Color.black);
if(this.oldTextSize<=this.newTextSize)
{
this.oldTextSize=this.newTextSize;
lineCountArea.setText(this.getTextSize());
}
}



private void highlightErrors() throws DAOException,BadLocationException
{
highlighter.removeAllHighlights();
String errorString="",text="";
String errors[];

errorString=TaskModel.getErrorLine(file);
if(!errorString.trim().equals(""))
{
errors=errorString.trim().substring(1).trim().split("`");
text=textArea.getText().trim();
for(int i=0;i<errors.length;i++)
{
int position=text.trim().indexOf(errors[i].trim());
if(position>=0)
highlighter.addHighlight(position,position+errors[i].trim().length(),redPainter);
}
}
}


public void showFinding(int[] position,int length) throws BadLocationException
{
highlighter.removeAllHighlights();
if(state){
try{
this.highlightErrors();
}
catch(Exception ee){
}
}
for(int i=0;i<position.length;i++)
{
highlighter.addHighlight(position[i],position[i]+length,greenPainter);
}
}

}