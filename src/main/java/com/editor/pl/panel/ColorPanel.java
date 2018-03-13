package com.editor.pl.panel;
import com.editor.pl.*;
import com.editor.dao.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
public class ColorPanel extends JDialog implements ActionListener
{
private Container container;
private JButton red,blue,green,yellow,orange,pink,black,gray;
private WriterPanel writerPanel;
public ColorPanel(WriterPanel writerPanel)
{
this.writerPanel=writerPanel;
initComponents();
}

public void initComponents()
{
container=getContentPane();
red=new JButton("",new ImageIcon(this.getClass().getResource(GlobalResources.RED_ICON)));
blue=new JButton("",new ImageIcon(this.getClass().getResource(GlobalResources.BLUE_ICON)));
yellow=new JButton("",new ImageIcon(this.getClass().getResource(GlobalResources.YELLOW_ICON)));
green=new JButton("",new ImageIcon(this.getClass().getResource(GlobalResources.GREEN_ICON)));
orange=new JButton("",new ImageIcon(this.getClass().getResource(GlobalResources.ORANGE_ICON)));
pink=new JButton("",new ImageIcon(this.getClass().getResource(GlobalResources.PINK_ICON)));
black=new JButton("",new ImageIcon(this.getClass().getResource(GlobalResources.BLACK_ICON)));
gray=new JButton("",new ImageIcon(this.getClass().getResource(GlobalResources.GRAY_ICON)));
setLayout(null);
setResizable(false);
this.setBackground(Color.white);
red.setBounds(0,0,64,64);
yellow.setBounds(64,0,64,64);
green.setBounds(0,64,64,64);
orange.setBounds(64,64,64,64);
pink.setBounds(128,0,64,64);
gray.setBounds(192,0,64,64);
black.setBounds(128,64,64,64);
blue.setBounds(192,64,64,64);
container.add(red);
container.add(black);
container.add(blue);
container.add(green);
container.add(yellow);
container.add(orange);
container.add(pink);
container.add(gray);
Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
this.setSize(266,158);
this.setLocation(d.width/2-this.getWidth()/2,d.height/2-this.getHeight()/2);
setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
setTitle("Color Panel");
this.setIconImage(new ImageIcon(this.getClass().getResource(GlobalResources.COLOR_ICON)).getImage());
addListeners();
this.setModal(true);
this.setVisible(true);
}

public void addListeners()
{
red.addActionListener(this);
blue.addActionListener(this);
yellow.addActionListener(this);
green.addActionListener(this);
orange.addActionListener(this);
gray.addActionListener(this);
black.addActionListener(this);
pink.addActionListener(this);
}

public void actionPerformed(ActionEvent ae)
{
Object o=ae.getSource();

if(o==red)
{
this.writerPanel.setColorOfTextArea(Color.red);
}

if(o==blue)
{
this.writerPanel.setColorOfTextArea(Color.blue);

}

if(o==green)
{
this.writerPanel.setColorOfTextArea(Color.green);

}

if(o==yellow)
{
this.writerPanel.setColorOfTextArea(Color.yellow);

}

if(o==black)
{
this.writerPanel.setColorOfTextArea(Color.black);

}

if(o==pink)
{
this.writerPanel.setColorOfTextArea(Color.pink);

}

if(o==gray)
{
this.writerPanel.setColorOfTextArea(Color.gray);

}

if(o==orange)
{
this.writerPanel.setColorOfTextArea(Color.orange);

}

}
}