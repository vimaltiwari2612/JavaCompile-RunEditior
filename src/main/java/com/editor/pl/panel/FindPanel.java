package com.editor.pl.panel;
import com.editor.pl.*;
import com.editor.model.*;
import com.editor.components.*;
import com.editor.exception.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class FindPanel extends JDialog implements ActionListener
{
private WriterPanel writerPanel;
private Container container;
private JButton ok,cancel;
private JLabel text;
private JTextField textName;
private String findContents ,find[];
private int[] positions;
private JClearTextField clearTextField;
public FindPanel(WriterPanel writerPanel)
{
this.writerPanel=writerPanel;
container=getContentPane();
textName=new JTextField();
text=new JLabel("Enter Text to search ");
ok=new JButton("",new ImageIcon(this.getClass().getResource(GlobalResources.OK_BUTTON)));
cancel=new JButton("",new ImageIcon(this.getClass().getResource(GlobalResources.CLOSE_BUTTON)));
this.clearTextField=new JClearTextField(textName);
this.setTitle("Find");
this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
this.setLayout(null);
text.setBounds(10,10,300,30);
textName.setBounds(10,50,200,30);
clearTextField.setBounds(210,50,28,28);
ok.setBounds(20,90,34,34);
cancel.setBounds(60,90,34,34);
container.add(text);
container.add(textName);
container.add(ok);
container.add(cancel);
container.add(clearTextField);
this.setSize(300,180);
Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
this.setLocation(d.width/2 - this.getWidth()/2,d.height/2 - this.getHeight()/2);
setResizable(false);
this.setIconImage(new ImageIcon(this.getClass().getResource(GlobalResources.FIND_ICON)).getImage());
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
Object o=ae.getSource();
if(o==ok)
{
try{
findContents=TaskModel.findContents(this.writerPanel.getTextAreaContents().trim(),this.textName.getText().trim());
if(findContents.trim().equals("")){
JOptionPane.showMessageDialog(this,this.textName.getText().trim()+" - Not Found!!","Information",JOptionPane.INFORMATION_MESSAGE);
}
else
{
find=findContents.trim().substring(1).split(",");
positions=new int[find.length];
for(int i=0;i<find.length;i++)
{
positions[i]=Integer.parseInt(find[i]);
}
this.writerPanel.showFinding(positions,this.textName.getText().trim().length());
JOptionPane.showMessageDialog(this,"Done!","Information",JOptionPane.INFORMATION_MESSAGE);
this.textName.requestFocus();
}
}
catch(Exception e)
{
JOptionPane.showMessageDialog(this,e.getMessage(),"Error",JOptionPane.WARNING_MESSAGE);
}
}

if(o==cancel)
{
this.dispose();
}
}

}