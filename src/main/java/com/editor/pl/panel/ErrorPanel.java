package com.editor.pl.panel;
import com.editor.pl.*;
import com.editor.pl.panel.*;
import com.editor.exception.*;
import com.editor.model.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class ErrorPanel extends JPanel implements ActionListener
{
private JTextArea errorTextArea;
private Container container;
private JScrollPane scrollPane;
private Dimension d;
private String fileName;
private JButton close;
private ApplicationPanel applicationPanel;
private File file;
private Font font;
private int rows,columns;
public ErrorPanel(ApplicationPanel applicationPanel)
{
this.applicationPanel=applicationPanel;
this.setBorder(new TitledBorder(new EtchedBorder(),"Error"));
errorTextArea=new JTextArea();
errorTextArea.setBackground(new Color(222,251,164));
errorTextArea.setForeground(Color.black);
errorTextArea.setLineWrap(false);
scrollPane=new JScrollPane(errorTextArea);
scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
scrollPane.setViewportBorder(new LineBorder(Color.blue));
this.add(scrollPane);
this.errorTextArea.setEditable(false);
this.setVisible(true);
this.close=new JButton("Close",new ImageIcon(this.getClass().getResource(GlobalResources.QUIT_ICON)));
close.setBounds((this.getWidth()/2)-30,this.getHeight()-(this.getHeight()-50),100,20);
this.add(close);
this.font=errorTextArea.getFont();
close.addActionListener(this);
this.setBackground(new Color(255,128,0));
}

public void setTextAreaSize(int rows,int columns)
{
this.rows=rows;
this.columns=columns;
errorTextArea.setRows(this.rows);
errorTextArea.setColumns(this.columns);
}

public void setFormatOfTextArea(Font font)
{
this.font=null;
this.font=font;
if(font.getSize()>12 && this.errorTextArea.getRows()>0)
{
errorTextArea.setRows(errorTextArea.getRows()-1);
errorTextArea.setColumns(errorTextArea.getColumns()-1);
}
else
{
errorTextArea.setRows(this.rows);
errorTextArea.setColumns(this.columns);
}
errorTextArea.setFont(this.font);
errorTextArea.repaint();
}


public void setTextAreaContents(String contents)
{
errorTextArea.setText(contents.trim());
}

public void actionPerformed(ActionEvent ae)
{
if(ae.getSource()==close)
{
this.applicationPanel.setDefault(true);
this.applicationPanel.setDefaultPanel();
this.applicationPanel.repaint();
}
}

public void updateErrors(File file,String javaFileName) throws DAOException
{
this.setBorder(new TitledBorder(new EtchedBorder(),"Errors - "+javaFileName));
this.file=file;
String errorString="",newContents="";
String errors[];
errorString=TaskModel.getErrorText(this.file,javaFileName);
if(!errorString.trim().equals(""))
{
errors=errorString.substring(1).trim().split("`");
for(int i=0;i<errors.length;i++)
{
newContents=newContents+errors[i].trim()+"\n";
}
this.setTextAreaContents(newContents);
}
else
{
this.setTextAreaContents(TaskModel.copyContentsForEditing(this.file));
}
}
}