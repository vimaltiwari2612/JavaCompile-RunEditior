package com.editor.pl;
import com.editor.pl.*;
import com.editor.pl.panel.*;
import com.editor.model.*;
import com.editor.exception.*;
import com.editor.interfaces.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class ApplicationPanel extends JFrame implements ActionListener,FileInterface
{
private JMenuBar menuBar;
private JLayeredPane desktop;
private JMenu file,format,color,tools,exit,edit,about,view,help,autoCompilation;
private JMenuItem newFile,openFile,deleteFile,saveFile,font,size,style,vcolor,compile,run,clear,quit,find,editFileName,replace,aboutUs,showErrorPanel,mailID,autoCompileOn,autoCompileOff;
private WriterPanel writerPanel;
private Container container;
private FilePanel filePanel;
private ErrorPanel errorPanel;
private File errorFile;
public ApplicationPanel()
{
try{
TaskModel.createIfNotAvailable();
}catch(Exception e)
{
JOptionPane.showMessageDialog(this,e.getMessage(),"Notification",JOptionPane.INFORMATION_MESSAGE);
}
initComponents();
}

public void initComponents()
{
this.container=getContentPane();
this.filePanel=new FilePanel();
this.errorPanel=new ErrorPanel(this);
this.writerPanel=new WriterPanel(errorPanel);
container.setLayout(null);
setJMenuBar(createMenuBar());
Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
this.setSize((int)d.getWidth()-1,(int)d.getHeight()-40);
this.setLocation(0,0);
this.setVisible(true);
this.container.setBackground(Color.black);
this.setResizable(false);
this.setTitle("JAVA Compile & Run Editor (jCRE)");
this.addListeners();
this.setDefault(false);
this.setIconImage(new ImageIcon(this.getClass().getResource(GlobalResources.APPLICATION_ICON)).getImage());
container.add(writerPanel);
container.add(errorPanel);
setDefaultPanel();
this.setCursor(this.HAND_CURSOR);
/*try{
UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
}catch(ClassNotFoundException cnfe)
{
}catch(InstantiationException ie)
{
}catch(IllegalAccessException iae)
{
}catch(UnsupportedLookAndFeelException ulafe)
{
}*/
errorFile=new File(FILE_NAME);
}
public void setDefaultPanel()
{
this.writerPanel.setBounds(0,0,this.getWidth()-3,this.getHeight()-45);
this.writerPanel.setTextAreaSize((int)((this.getHeight()-50)/17),(int)((this.getWidth()-5)/13));
this.errorPanel.setVisible(false);
this.writerPanel.repaint();
}

public void setCombinedPanel()
{
this.writerPanel.setBounds(0,0,(this.getWidth()-5),(int)(this.getHeight()*2/3));
this.writerPanel.setTextAreaSize((int)((this.getHeight()*2/3)/17),(int)((this.getWidth()-5)/13));
this.errorPanel.setBounds(0,(int)(this.getHeight()*2/3),this.getWidth()-5,this.getHeight()-(int)((this.getHeight()*2/3)/17)-50);
this.errorPanel.setTextAreaSize((int)((this.getHeight()-(this.getHeight()*2/3)-70)/17),(int)((this.getWidth()-5)/13));
this.errorPanel.setVisible(true);
this.writerPanel.repaint();
this.errorPanel.repaint();
}

public void addListeners()
{
quit.addActionListener(this);
compile.addActionListener(this);
vcolor.addActionListener(this);
font.addActionListener(this);
size.addActionListener(this);
openFile.addActionListener(this);
style.addActionListener(this);
clear.addActionListener(this);
saveFile.addActionListener(this);
deleteFile.addActionListener(this);
newFile.addActionListener(this);
aboutUs.addActionListener(this);
find.addActionListener(this);
replace.addActionListener(this);
editFileName.addActionListener(this);
showErrorPanel.addActionListener(this);
replace.addActionListener(this);
deleteFile.addActionListener(this);
mailID.addActionListener(this);
autoCompileOn.addActionListener(this);
autoCompileOff.addActionListener(this);
run.addActionListener(this);
}

public JMenuBar createMenuBar()
{
menuBar=new JMenuBar();
autoCompilation=new JMenu("Auto Compilation");
autoCompilation.setMnemonic('A');
autoCompileOn=new JMenuItem("On",new ImageIcon(this.getClass().getResource(GlobalResources.AUTO_COMPILE_ON_ICON)));
autoCompileOff=new JMenuItem("Off",new ImageIcon(this.getClass().getResource(GlobalResources.AUTO_COMPILE_OFF_ICON)));
autoCompilation.add(autoCompileOn);
autoCompilation.add(autoCompileOff);
help=new JMenu("Help");
help.setMnemonic('H');
mailID=new JMenuItem("Query & Support",new ImageIcon(this.getClass().getResource(GlobalResources.HELP_ICON)));
mailID.setMnemonic('Q');
view=new JMenu("View");
view.setMnemonic('V');
showErrorPanel=new JMenuItem("Show Error Panel",new ImageIcon(this.getClass().getResource(GlobalResources.SHOW_ERROR_PANEL_ICON)));
showErrorPanel.setMnemonic('s');
file=new JMenu("File");
file.setMnemonic('F');
format=new JMenu("Format");
format.setMnemonic('O');
color=new JMenu("Color");
color.setMnemonic('C');
tools=new JMenu("Tools");
tools.setMnemonic('T');
exit=new JMenu("Exit");
exit.setMnemonic('X');
edit=new JMenu("Edit");
edit.setMnemonic('E');
find=new JMenuItem("Find",new ImageIcon(this.getClass().getResource(GlobalResources.FIND_ICON)));
find.setMnemonic('F');
replace=new JMenuItem("Replace",new ImageIcon(this.getClass().getResource(GlobalResources.REPLACE_ICON)));
replace.setMnemonic('R');
about=new JMenu("About");
about.setMnemonic('A');
aboutUs=new JMenuItem("about",new ImageIcon(this.getClass().getResource(GlobalResources.ABOUT_ICON)));
aboutUs.setMnemonic('A');
newFile=new JMenuItem("New",new ImageIcon(this.getClass().getResource(GlobalResources.NEW_FILE_ICON)));
newFile.setMnemonic('N');
openFile=new JMenuItem("Open",new ImageIcon(this.getClass().getResource(GlobalResources.OPEN_FILE_ICON)));
openFile.setMnemonic('O');
deleteFile=new JMenuItem("Delete",new ImageIcon(this.getClass().getResource(GlobalResources.DELETE_FILE_ICON)));
deleteFile.setMnemonic('D');
saveFile=new JMenuItem("Save As",new ImageIcon(this.getClass().getResource(GlobalResources.SAVE_FILE_ICON)));
saveFile.setMnemonic('S');
font=new JMenuItem("Set Font",new ImageIcon(this.getClass().getResource(GlobalResources.FONT_ICON)));
font.setMnemonic('F');
vcolor=new JMenuItem("Set Text Color",new ImageIcon(this.getClass().getResource(GlobalResources.COLOR_ICON)));
vcolor.setMnemonic('C');
compile=new JMenuItem("Compile",new ImageIcon(this.getClass().getResource(GlobalResources.COMPILE_ICON)));
compile.setMnemonic('C');
run=new JMenuItem("Run",new ImageIcon(this.getClass().getResource(GlobalResources.RUN_ICON)));
run.setMnemonic('R');
size=new JMenuItem("Size");
style=new JMenuItem("Style");
clear=new JMenuItem("Clear Text",new ImageIcon(this.getClass().getResource(GlobalResources.CLEAR_ICON)));
clear.setMnemonic('T');
quit=new JMenuItem("Quit",new ImageIcon(this.getClass().getResource(GlobalResources.QUIT_ICON)));
quit.setMnemonic('Q');
editFileName=new JMenuItem("File Rename",new ImageIcon(this.getClass().getResource(GlobalResources.EDIT_FILE_NAME_ICON)));
editFileName.setMnemonic('E');
file.add(newFile);
file.add(openFile);
file.add(saveFile);
file.addSeparator();
file.add(deleteFile);
view.add(showErrorPanel);
color.add(vcolor);
edit.add(editFileName);
edit.add(find);
edit.add(replace);
about.add(aboutUs);
help.add(mailID);
format.add(font);
tools.add(compile);
tools.add(run);
tools.add(autoCompilation);
tools.addSeparator();
tools.add(clear);
exit.add(quit);
menuBar.add(file);
menuBar.add(edit);
menuBar.add(view);
menuBar.add(format);
menuBar.add(color);
menuBar.add(tools);
menuBar.add(about);
menuBar.add(help);
menuBar.add(exit);
menuBar.setBackground(new Color(255,255,128));
menuBar.setForeground(Color.black);
newFile.setBackground(new Color(255,255,128));
openFile.setBackground(new Color(255,255,128));
saveFile.setBackground(new Color(255,255,128));
deleteFile.setBackground(new Color(255,255,128));
showErrorPanel.setBackground(new Color(255,255,128));
vcolor.setBackground(new Color(255,255,128));
editFileName.setBackground(new Color(255,255,128));
replace.setBackground(new Color(255,255,128));
find.setBackground(new Color(255,255,128));
aboutUs.setBackground(new Color(255,255,128));
mailID.setBackground(new Color(255,255,128));
compile.setBackground(new Color(255,255,128));
run.setBackground(new Color(255,255,128));
autoCompilation.setOpaque(true);
autoCompilation.setBackground(new Color(255,255,128));
clear.setBackground(new Color(255,255,128));
quit.setBackground(new Color(255,255,128));
help.setBackground(new Color(255,255,128));
autoCompileOn.setBackground(new Color(255,255,128));
autoCompileOff.setBackground(new Color(255,255,128));
font.setBackground(new Color(255,255,128));
newFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N,java.awt.event.InputEvent.CTRL_MASK));
openFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O,java.awt.event.InputEvent.CTRL_MASK));
saveFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S,java.awt.event.InputEvent.CTRL_MASK));
deleteFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D,java.awt.event.InputEvent.CTRL_MASK));
vcolor.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1,java.awt.event.InputEvent.CTRL_MASK));
replace.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R,java.awt.event.InputEvent.CTRL_MASK));
find.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F,java.awt.event.InputEvent.CTRL_MASK));
editFileName.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E,java.awt.event.InputEvent.CTRL_MASK));
showErrorPanel.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P,java.awt.event.InputEvent.CTRL_MASK));
quit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q,java.awt.event.InputEvent.CTRL_MASK));
mailID.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3,java.awt.event.InputEvent.CTRL_MASK));
compile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9,java.awt.event.InputEvent.ALT_MASK));
run.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9,java.awt.event.InputEvent.CTRL_MASK));
clear.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L,java.awt.event.InputEvent.CTRL_MASK));
font.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2,java.awt.event.InputEvent.CTRL_MASK));
aboutUs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U,java.awt.event.InputEvent.CTRL_MASK));
autoCompileOn.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4,java.awt.event.InputEvent.ALT_MASK));
autoCompileOff.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5,java.awt.event.InputEvent.ALT_MASK));
return menuBar;
}


public void setDefault(boolean value)
{
openFile.setEnabled(true);
saveFile.setEnabled(value);
deleteFile.setEnabled(value);
font.setEnabled(value);
vcolor.setEnabled(value);
clear.setEnabled(value);
compile.setEnabled(value);
run.setEnabled(value);
aboutUs.setEnabled(true);
find.setEnabled(value);
replace.setEnabled(value);
editFileName.setEnabled(value);
showErrorPanel.setEnabled(value);
mailID.setEnabled(true);
autoCompilation.setEnabled(value);
autoCompileOn.setEnabled(value);
autoCompileOff.setEnabled(false);
}

public void actionPerformed(ActionEvent ae)
{
Object o=ae.getSource();
if(o==quit)
{
this.closeAll();
}

if(o==deleteFile)
{
try{
boolean b=filePanel.deleteFile(this);
if(b)
JOptionPane.showMessageDialog(this,"Successfully Deleted!!","Notification",JOptionPane.INFORMATION_MESSAGE);
}catch(Exception e)
{
JOptionPane.showMessageDialog(this,e.getMessage(),"Error",JOptionPane.WARNING_MESSAGE);
}

}

if(o==find)
{
new FindPanel(this.writerPanel);
}

if(o==run)
{
try{
writerPanel.preparingForCompilation();
new RunPanel(this);
}
catch(Exception e)
{
JOptionPane.showMessageDialog(this,"Can't Excecute!!\nthere are some \" Errors\" in Your program...\n Fix them first before Running!!!\n\nError :- "+e.getMessage(),"Error",JOptionPane.WARNING_MESSAGE);
}
try{
this.errorPanel.updateErrors(errorFile,writerPanel.getFileName());
}
catch(Exception ee){
this.errorPanel.setTextAreaContents("Some Error in processing..."+ee.getMessage());
}

}

if(o==autoCompileOn)
{
String text="If Auto Compilation is On , Some Adjustments will be made...\n\n 1. Text color will be Changed to black(default) \n 2. Color menu will be disabled \n 3. Manual Compilation(ALT+F9) will be disabled \n 4. Error Lines are shown By Red Highlights\n\n Are u Sure ,You want to use it??";
int result=JOptionPane.showConfirmDialog(this,text,"Warning",JOptionPane.YES_NO_OPTION);
if(result==JOptionPane.YES_OPTION)
{
autoCompileOn.setEnabled(false);
autoCompileOff.setEnabled(true);
compile.setEnabled(false);
this.writerPanel.setColorOfTextArea(Color.black);
vcolor.setEnabled(false);
this.compileAndUpdate();
this.writerPanel.updateTextArea();
this.writerPanel.setAutomaticCompilation(true);
JOptionPane.showMessageDialog(this,"Auto Compilation - ON","Notification",JOptionPane.INFORMATION_MESSAGE);
}
}

if(o==autoCompileOff)
{
autoCompileOn.setEnabled(true);
autoCompileOff.setEnabled(false);
compile.setEnabled(true);
this.writerPanel.setAutomaticCompilation(false);
vcolor.setEnabled(false);
this.writerPanel.updateLineCountArea();
this.compileAndUpdate();
JOptionPane.showMessageDialog(this,"Auto Compilation - OFF","Notification",JOptionPane.INFORMATION_MESSAGE);
}




if(o==mailID)
{
String text="Having Queries...???\n\nContact - vimaltiwari2612@gmail.com\nthank you!";
JOptionPane.showMessageDialog(this,text,"Query",JOptionPane.QUESTION_MESSAGE);
}

if(o==replace)
{
new ReplacePanel(writerPanel);
}


if(o==showErrorPanel)
{
JOptionPane.showMessageDialog(this,"Note:- Compile Your Program To See Errors!!","Notification",JOptionPane.INFORMATION_MESSAGE);
this.compileAndUpdate();
this.setCombinedPanel();
this.showErrorPanel.setEnabled(false);
this.writerPanel.repaint();
this.errorPanel.repaint();
}

if(o==editFileName)
{
new EditFile(this,writerPanel);
}
if(o==aboutUs)
{
String text="This is Java Editor .\n Version 1.0.0\n\t\t developed by-\n\t\t\t Vimal Tiwari";
JOptionPane.showMessageDialog(this,text,"Notification",JOptionPane.INFORMATION_MESSAGE);
}

if(o==compile)
{
try{
writerPanel.preparingForCompilation();
JOptionPane.showMessageDialog(this,"Compilation Successfull!!","Notification",JOptionPane.INFORMATION_MESSAGE);
}
catch(Exception e)
{
JOptionPane.showMessageDialog(this,e.getMessage(),"Error",JOptionPane.WARNING_MESSAGE);
}
try{
this.errorPanel.updateErrors(errorFile,writerPanel.getFileName());
}
catch(Exception ee){
this.errorPanel.setTextAreaContents("Some Error in processing..."+ee.getMessage());
}
}

if(o==vcolor)
{
JOptionPane.showMessageDialog(this,"TEXT COLOR SETTINGS - Choose Your settings and Close it to apply!!","Usage",JOptionPane.INFORMATION_MESSAGE);
new ColorPanel(writerPanel);
}

if(o==font)
{
JOptionPane.showMessageDialog(this,"FONT SETTINGS - Choose Your settings and Close it to apply!!","Usage",JOptionPane.INFORMATION_MESSAGE);
new FormatPanel(writerPanel);
}

if(o==openFile)
{
try{
if(!this.writerPanel.getFileName().trim().equals(""))
{
int result=JOptionPane.showConfirmDialog(this,"Save Changes - "+this.writerPanel.getFileName().trim(),"Warning",JOptionPane.YES_NO_OPTION);
if(result==JOptionPane.YES_OPTION)
{
boolean b=filePanel.saveFile(this,writerPanel.getTextAreaContents());
if(b)
JOptionPane.showMessageDialog(this,"Successfully Saved!!","Notification",JOptionPane.INFORMATION_MESSAGE);
}
else
{
TaskModel.deleteFile(new File(LOCATION+this.writerPanel.getFileName().trim()));
}
}
boolean b=filePanel.openFile(this,writerPanel);
if(b){
if(this.writerPanel.getAutomaticCompilation())
{
this.writerPanel.updateTextArea();
}
else
{
this.writerPanel.updateLineCountArea();
}
this.setDefault(true);
TaskModel.deleteAll();
}
}catch(Exception e)
{
JOptionPane.showMessageDialog(this,e.getMessage(),"Error",JOptionPane.WARNING_MESSAGE);
}
}

if(o==newFile)
{
if(!this.writerPanel.getFileName().trim().equals(""))
{
try
{
int result=JOptionPane.showConfirmDialog(this,"Save Changes - "+this.writerPanel.getFileName().trim(),"Warning",JOptionPane.YES_NO_OPTION);
if(result==JOptionPane.YES_OPTION)
{
boolean b=filePanel.saveFile(this,writerPanel.getTextAreaContents());
if(b)
JOptionPane.showMessageDialog(this,"Successfully Saved!!","Notification",JOptionPane.INFORMATION_MESSAGE);
}
else
{
TaskModel.deleteFile(new File(LOCATION+this.writerPanel.getFileName().trim()));
}
TaskModel.deleteAll();
}catch(Exception e)
{
JOptionPane.showMessageDialog(this,e.getMessage(),"Error",JOptionPane.WARNING_MESSAGE);
}
}
new NewFile(this,writerPanel);
if(this.writerPanel.getAutomaticCompilation())
this.writerPanel.updateTextArea();
else
this.writerPanel.updateLineCountArea();
}

if(o==saveFile)
{
try{
boolean b=filePanel.saveFile(this,writerPanel.getTextAreaContents());
if(b)
JOptionPane.showMessageDialog(this,"Successfully Saved!!","Notification",JOptionPane.INFORMATION_MESSAGE);
}catch(Exception e)
{
JOptionPane.showMessageDialog(this,e.getMessage(),"Error",JOptionPane.WARNING_MESSAGE);
}
}



if(o==clear)
{
writerPanel.setTextAreaContents("");
}
}

public void compileAndUpdate()
{
try{
writerPanel.preparingForCompilation();
}
catch(Exception e)
{}
try{
this.errorPanel.updateErrors(errorFile,writerPanel.getFileName());
}
catch(Exception ee){
this.errorPanel.setTextAreaContents("Some Error in processing..."+ee.getMessage());
}
}

private void closeAll()
{
if(this.writerPanel.getFileName().trim().equals(""))
{
int result=JOptionPane.showConfirmDialog(this,"Are You Sure to Exit???","Warning",JOptionPane.YES_NO_OPTION);
if(result==JOptionPane.YES_OPTION)
{
try{
TaskModel.deleteAll();
}catch(Exception e)
{
JOptionPane.showMessageDialog(this,e.getMessage(),"Error",JOptionPane.WARNING_MESSAGE);
}
System.exit(0);
}
return;
}
if(!this.writerPanel.getFileName().trim().equals(""))
{
try
{
int result=JOptionPane.showConfirmDialog(this,"Save Changes - "+this.writerPanel.getFileName().trim(),"Warning",JOptionPane.YES_NO_OPTION);
if(result==JOptionPane.YES_OPTION)
{
boolean b=filePanel.saveFile(this,writerPanel.getTextAreaContents());
if(b)
JOptionPane.showMessageDialog(this,"Successfully Saved!!","Notification",JOptionPane.INFORMATION_MESSAGE);
}
else
{
TaskModel.deleteFile(new File(LOCATION+this.writerPanel.getFileName().trim()));
}
TaskModel.deleteAll();
}catch(Exception e)
{
JOptionPane.showMessageDialog(this,e.getMessage(),"Error",JOptionPane.WARNING_MESSAGE);
}
System.exit(0);
}
}

public static void main(String...gg)
{
new ApplicationPanel();
}

}