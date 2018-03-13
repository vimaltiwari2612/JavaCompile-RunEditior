package com.editor.pl.panel;
import com.editor.pl.*;
import com.editor.components.*;
import com.editor.exception.*;
import com.editor.model.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;
public class ReplacePanel extends JDialog implements ActionListener,DocumentListener
{
private Container container;
private JLabel text,replaceText;
private JTextField textBox,replaceTextBox;
private JButton ok,cancel;
private WriterPanel writerPanel;
private JClearTextField clearTextField1,clearTextField2;
private String findContents ,find[];
private int[] positions;
public ReplacePanel(WriterPanel writerPanel)
{
this.writerPanel=writerPanel;
text=new JLabel("Enter Text");
replaceText=new JLabel("Replace With");
ok=new JButton("",new ImageIcon(this.getClass().getResource(GlobalResources.REPLACE_BUTTON)));
cancel=new JButton("",new ImageIcon(this.getClass().getResource(GlobalResources.CLOSE_BUTTON)));
textBox=new JTextField();
replaceTextBox=new JTextField();
textBox.getDocument().addDocumentListener(this);
this.clearTextField1=new JClearTextField(textBox);
this.clearTextField2=new JClearTextField(replaceTextBox);
container=getContentPane();
this.setTitle("Replace");
this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
this.setLayout(null);
text.setBounds(10,10,80,30);
textBox.setBounds(100,10,150,30);
clearTextField1.setBounds(250,10,28,28);
replaceText.setBounds(10,50,80,32);
replaceTextBox.setBounds(100,50,150,30);
clearTextField2.setBounds(250,50,28,28);
ok.setBounds(150,100,34,34);
cancel.setBounds(200,100,34,34);
container.add(replaceText);
container.add(replaceTextBox);
container.add(text);
container.add(textBox);
container.add(ok);
container.add(cancel);
container.add(clearTextField1);
container.add(clearTextField2);
this.setSize(300,180);
Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
this.setLocation(d.width/2 - this.getWidth()/2,d.height/2 - this.getHeight()/2);
ok.addActionListener(this);
cancel.addActionListener(this);
setResizable(false);
this.setIconImage(new ImageIcon(this.getClass().getResource(GlobalResources.REPLACE_ICON)).getImage());
this.container.setBackground(new Color(255,255,183));
this.setModal(true);
this.setVisible(true);
}

public void actionPerformed(ActionEvent ae)
{
Object o=ae.getSource();

if(o==ok)
{
try{
this.writerPanel.setTextAreaContents(TaskModel.replaceContents(this.writerPanel.getTextAreaContents().trim(),this.textBox.getText().trim(),this.replaceTextBox.getText().trim()));
JOptionPane.showMessageDialog(this,"SuccessFully Replaced!!","Notification",JOptionPane.INFORMATION_MESSAGE);

this.textBox.requestFocus();
}catch(Exception e)
{
JOptionPane.showMessageDialog(this,e.getMessage(),"Error",JOptionPane.WARNING_MESSAGE);
}
}

if(o==cancel)
{
this.setModal(false);
this.setVisible(false);
this.dispose();
}

}

public void changedUpdate(DocumentEvent de)  
{
try{
findContents=TaskModel.findContents(this.writerPanel.getTextAreaContents().trim(),this.textBox.getText().trim());
if(findContents.trim().equals("")){
}
else
{
find=findContents.trim().substring(1).split(",");
positions=new int[find.length];
for(int i=0;i<find.length;i++)
{
positions[i]=Integer.parseInt(find[i]);
}
this.writerPanel.showFinding(positions,this.textBox.getText().trim().length());
}
}
catch(Exception e)
{
JOptionPane.showMessageDialog(this,e.getMessage(),"Error",JOptionPane.WARNING_MESSAGE);
}
}


public void insertUpdate(DocumentEvent de) 
{
}

public void removeUpdate(DocumentEvent de) 
{
}

}