package com.editor.pl.panel;
import com.editor.pl.*;
import com.editor.pl.panel.*;
import com.editor.components.*;
import com.editor.exception.*;
import com.editor.model.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class RunPanel extends JDialog implements ActionListener
{
private JLabel classPathLabel,mainClassNameLabel,usageClassPathLabel,usageMainClassNameLabel;
private JTextField mainClassName;
private JTextArea classPath;
private JScrollPane scrollPane;
private JPanel panel;
private JButton run,close;
private Container container;
private ApplicationPanel applicationPanel;
private JClearTextField clearTextField1,clearTextField2;
public RunPanel(ApplicationPanel applicationPanel)
{
panel=new JPanel();
this.container=getContentPane();
this.setLayout(null);
this.applicationPanel=applicationPanel;
classPathLabel=new JLabel("Enter classPath (if required)");
mainClassNameLabel=new JLabel("Enter Main Class Name (Class that contain main function)");
usageClassPathLabel=new JLabel("Usage : -cp c:\\myjava\\myprograms; ");
usageMainClassNameLabel=new JLabel("Usage : MyFirstJavaProgram ");
mainClassName=new JTextField();
mainClassName.requestFocus();
classPath=new JTextArea(3,17);
classPath.setTabSize(0);
classPath.setLineWrap(true);
scrollPane=new JScrollPane(classPath);
scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
scrollPane.setViewportBorder(new LineBorder(Color.gray));
run=new JButton("",new ImageIcon(this.getClass().getResource(GlobalResources.OK_BUTTON)));
close=new JButton("",new ImageIcon(this.getClass().getResource(GlobalResources.CLOSE_BUTTON)));
this.clearTextField1=new JClearTextField(classPath);
this.clearTextField2=new JClearTextField(mainClassName);
classPathLabel.setBounds(10,10,300,30);
usageClassPathLabel.setBounds(10,40,300,30);
panel.setBounds(10,80,200,70);
clearTextField1.setBounds(210,100,28,28);
mainClassNameLabel.setBounds(10,160,330,30);
usageMainClassNameLabel.setBounds(10,190,300,30);
mainClassName.setBounds(10,220,200,30);
clearTextField2.setBounds(210,220,28,28);
run.setBounds(20,270,34,34);
close.setBounds(59,270,34,34);
this.panel.add(scrollPane);
this.container.add(panel);
this.container.add(classPathLabel);
this.container.add(usageClassPathLabel);
this.container.add(mainClassNameLabel);
this.container.add(usageMainClassNameLabel);
this.container.add(mainClassName);
this.container.add(run);
this.container.add(close);
container.add(clearTextField1);
container.add(clearTextField2);
run.addActionListener(this);
close.addActionListener(this);
this.setSize(370,340);
this.setResizable(false);
Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
this.setLocation(d.width/2-this.getWidth()/2,d.height/2-this.getHeight()/2);
this.setTitle("Run Your Program ");
this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
this.container.setBackground(new Color(255,255,183));
panel.setBackground(new Color(255,255,183));
this.setIconImage(new ImageIcon(this.getClass().getResource(GlobalResources.RUN_ICON)).getImage());
this.setModal(true);
this.setVisible(true);
}

public void actionPerformed(ActionEvent ae)
{
Object o=ae.getSource();

if(o==run)
{
try{
runApplication();
}
catch(Exception e)
{
JOptionPane.showMessageDialog(this,e.getMessage(),"ERROR",JOptionPane.WARNING_MESSAGE);
}
}

if(o==close)
{
this.dispose();
}

}

public void runApplication() throws DAOException
{
if(mainClassName.getText().trim().equals(""))
{
usageMainClassNameLabel.setForeground(Color.red);
mainClassName.setText("");
mainClassName.requestFocus();
return;
}
usageMainClassNameLabel.setForeground(Color.black);
if(!classPath.getText().trim().equals(""))
{
if(!isValidClassPath()){
usageClassPathLabel.setForeground(Color.red);
classPath.requestFocus();
return;
}
}
usageClassPathLabel.setForeground(Color.black);
try{
File runFile=TaskModel.formingBatchJobsOnWindows(mainClassName.getText().trim(),classPath.getText().trim());
TaskModel.programToRunOnWindows(runFile);
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}

public boolean isValidClassPath()
{
String vClassPath[]=classPath.getText().trim().split(" ");
if(!vClassPath[0].equals("-cp") || vClassPath.length!=2)
return false;
return true;
}


}