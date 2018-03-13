package com.editor.pl.panel;
import com.editor.pl.*;
import com.editor.components.*;
import com.editor.dao.*;
import com.editor.exception.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class NewFile extends JDialog implements ActionListener
{
private ApplicationPanel applicationPanel;
private WriterPanel writerPanel;
private Container container;
private JButton ok,cancel;
private JLabel fileName;
private JTextField name;
private JClearTextField clearTextField;
public NewFile(ApplicationPanel applicationPanel,WriterPanel writerPanel)
{
this.applicationPanel=applicationPanel;
this.writerPanel=writerPanel;
container=getContentPane();
name=new JTextField(100);
fileName=new JLabel("Enter New File Name (Without extension)");
ok=new JButton("",new ImageIcon(this.getClass().getResource(GlobalResources.OK_BUTTON)));
cancel=new JButton("",new ImageIcon(this.getClass().getResource(GlobalResources.CLOSE_BUTTON)));
this.clearTextField=new JClearTextField(name);
this.setTitle("New File");
this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
this.setLayout(null);
fileName.setBounds(10,10,300,30);
name.setBounds(10,50,200,30);
clearTextField.setBounds(210,50,28,28);
ok.setBounds(20,90,34,34);
cancel.setBounds(60,90,34,34);
container.add(fileName);
container.add(name);
container.add(ok);
container.add(cancel);
container.add(clearTextField);
this.setSize(300,180);
Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
this.setLocation(d.width/2 - this.getWidth()/2,d.height/2 - this.getHeight()/2);
setResizable(false);
this.setIconImage(new ImageIcon(this.getClass().getResource(GlobalResources.NEW_FILE_ICON)).getImage());
this.container.setBackground(new Color(255,255,183));
addListeners();
this.setModal(true);
this.setVisible(true);

}

public void addListeners()
{
ok.addActionListener(this);
cancel.addActionListener(this);
}


public void actionPerformed(ActionEvent ae)
{
String text="";
Object o=ae.getSource();
if(o==ok)
{
if(this.name.getText().trim().equals("")==false)
{
this.writerPanel.setTextAreaEditable(true);
this.writerPanel.setTextAreaContents("");
this.writerPanel.setBorder(new TitledBorder(new EtchedBorder(),this.name.getText().trim()+".java"));
this.applicationPanel.setDefault(true);
this.writerPanel.setFileName((this.name.getText().trim()+".java"));
text="public class "+this.name.getText().trim()+"\n{\npublic static void main(String args[])\n{\n\n\n}\n}\n";
this.writerPanel.setTextAreaContents(text);
this.dispose();
}
else
{
JOptionPane.showMessageDialog(this,"Name Can't be Empty!!","Error",JOptionPane.WARNING_MESSAGE);
}
}

if(o==cancel)
{
this.dispose();
}
}

}