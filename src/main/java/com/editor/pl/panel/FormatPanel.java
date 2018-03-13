package com.editor.pl.panel;
import com.editor.pl.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
public class FormatPanel extends JDialog implements ActionListener,ItemListener
{
private Container container;
private JLabel font,size,style,boldLabel,italicLabel,plainLabel;
private JButton boldOn,italicOn,plainOn,boldOff,italicOff,plainOff;
private JComboBox styleList,sizeList;
private WriterPanel writerPanel;
private JScrollPane scrollPane;
private Font fontDetails,currentFontSettings;
private boolean boldStatus,italicStatus,plainStatus;
public FormatPanel(WriterPanel writerPanel)
{
this.writerPanel=writerPanel;
currentFontSettings=this.writerPanel.getFormatOfTextArea();
initComponents();
}

public void initComponents()
{
font=new JLabel("Font");
boldLabel=new JLabel("  B");
boldLabel.setFont(new Font("Times New Roman",Font.BOLD,12));
italicLabel=new JLabel("  I");
italicLabel.setFont(new Font("Times New Roman",Font.ITALIC,12));
plainLabel=new JLabel("  P");
plainLabel.setFont(new Font("Times New Roman",Font.PLAIN,12));
style=new JLabel("Style");
size=new JLabel("Size");
scrollPane=new JScrollPane(styleList,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
String styles[]={"Arial Black","Cambria","Calibri light","Century Gothic","Comic Sans MS","Dialog.plain","Lucida Console","Monotype Corsiva","Segoe Print","Segoe Script","System","Times New Roman"};
String sizes[]={"12","14","16","18","20","22","24","26","28","30","32","36","42","48"};
styleList=new JComboBox(styles);
sizeList=new JComboBox(sizes);
container=getContentPane();
boldOn=new JButton("",new ImageIcon(this.getClass().getResource(GlobalResources.OK_BUTTON)));
italicOn=new JButton("",new ImageIcon(this.getClass().getResource(GlobalResources.OK_BUTTON)));
plainOn=new JButton("",new ImageIcon(this.getClass().getResource(GlobalResources.OK_BUTTON)));
boldOff=new JButton("",new ImageIcon(this.getClass().getResource(GlobalResources.CLOSE_BUTTON)));
italicOff=new JButton("",new ImageIcon(this.getClass().getResource(GlobalResources.CLOSE_BUTTON)));
plainOff=new JButton("",new ImageIcon(this.getClass().getResource(GlobalResources.CLOSE_BUTTON)));
setLayout(null);
setResizable(false);
this.setBackground(Color.white);
Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
this.setSize(200,180);
this.setLocation(d.width/2-this.getWidth()/2,d.height/2-this.getHeight()/2);
style.setBounds(10,5,100,20);
boldLabel.setBounds(10,15,34,30);
italicLabel.setBounds(44,15,34,30);
plainLabel.setBounds(76,15,34,30);
boldOn.setBounds(10,40,34,34);
italicOn.setBounds(44,40,34,34);
plainOn.setBounds(76,40,34,34);
boldOff.setBounds(10,40,34,34);
italicOff.setBounds(44,40,34,34);
plainOff.setBounds(76,40,34,34);
font.setBounds(10,80,100,30);
styleList.setBounds(10,110,140,30);
size.setBounds(120,10,100,30);
sizeList.setBounds(120,40,50,30);
container.add(font);
container.add(style);
container.add(size);
container.add(scrollPane);
if(currentFontSettings.isBold())
container.add(boldOff);
else
container.add(boldOn);
if(currentFontSettings.isItalic())
container.add(italicOff);
else
container.add(italicOn);
if(currentFontSettings.isPlain())
container.add(plainOff);
else
container.add(plainOn);
container.add(styleList);
container.add(sizeList);
container.add(boldLabel);
container.add(italicLabel);
container.add(plainLabel);

setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
addListeners();
setTitle("Format Panel");
styleList.setSelectedItem(String.valueOf(currentFontSettings.getFontName()));
sizeList.setSelectedItem(String.valueOf(currentFontSettings.getSize()));
this.setIconImage(new ImageIcon(this.getClass().getResource(GlobalResources.FONT_ICON)).getImage());
this.container.setBackground(new Color(255,255,183));
this.setModal(true);
this.setVisible(true);
}

public void addListeners()
{
boldOn.addActionListener(this);
italicOn.addActionListener(this);
plainOn.addActionListener(this);
boldOff.addActionListener(this);
italicOff.addActionListener(this);
plainOff.addActionListener(this);
sizeList.addItemListener(this);
styleList.addItemListener(this);
}

public void actionPerformed(ActionEvent ae)
{
Object o=ae.getSource();
if(o==boldOn)
{
boldStatus=true;
if(italicStatus && plainStatus)
{
writerPanel.setFormatOfTextArea(new Font((styleList.getSelectedItem()).toString(),Font.BOLD+Font.ITALIC+Font.PLAIN,Integer.parseInt((sizeList.getSelectedItem()).toString())));
}
if(italicStatus && !plainStatus)
{
writerPanel.setFormatOfTextArea(new Font((styleList.getSelectedItem()).toString(),Font.BOLD+Font.ITALIC,Integer.parseInt((sizeList.getSelectedItem()).toString())));
}
if(plainStatus && !italicStatus)
{
writerPanel.setFormatOfTextArea(new Font((styleList.getSelectedItem()).toString(),Font.BOLD+Font.PLAIN,Integer.parseInt((sizeList.getSelectedItem()).toString())));
}
if(!italicStatus && !plainStatus)
{
writerPanel.setFormatOfTextArea(new Font((styleList.getSelectedItem()).toString(),Font.BOLD,Integer.parseInt((sizeList.getSelectedItem()).toString())));
}
boldOff.setBounds(10,40,34,34);
container.remove(boldOn);
container.add(boldOff);
}

if(o==italicOn)
{
italicStatus=true;
italicOff.setBounds(44,40,34,34);
if(boldStatus && plainStatus)
{
writerPanel.setFormatOfTextArea(new Font((styleList.getSelectedItem()).toString(),Font.BOLD+Font.ITALIC+Font.PLAIN,Integer.parseInt((sizeList.getSelectedItem()).toString())));
}
if(boldStatus && !plainStatus)
{
writerPanel.setFormatOfTextArea(new Font((styleList.getSelectedItem()).toString(),Font.BOLD+Font.ITALIC,Integer.parseInt((sizeList.getSelectedItem()).toString())));
}
if(plainStatus && !boldStatus)
{
writerPanel.setFormatOfTextArea(new Font((styleList.getSelectedItem()).toString(),Font.ITALIC+Font.PLAIN,Integer.parseInt((sizeList.getSelectedItem()).toString())));
}
if(!boldStatus && !plainStatus)
{
writerPanel.setFormatOfTextArea(new Font((styleList.getSelectedItem()).toString(),Font.ITALIC,Integer.parseInt((sizeList.getSelectedItem()).toString())));
}
container.remove(italicOn);
container.add(italicOff);
}


if(o==plainOn)
{
plainStatus=true;
plainOff.setBounds(76,40,34,34);
if(italicStatus && boldStatus)
{
writerPanel.setFormatOfTextArea(new Font((styleList.getSelectedItem()).toString(),Font.BOLD+Font.ITALIC+Font.PLAIN,Integer.parseInt((sizeList.getSelectedItem()).toString())));
}
if(italicStatus && !boldStatus)
{
writerPanel.setFormatOfTextArea(new Font((styleList.getSelectedItem()).toString(),Font.PLAIN+Font.ITALIC,Integer.parseInt((sizeList.getSelectedItem()).toString())));
}
if(boldStatus && !italicStatus)
{
writerPanel.setFormatOfTextArea(new Font((styleList.getSelectedItem()).toString(),Font.BOLD+Font.PLAIN,Integer.parseInt((sizeList.getSelectedItem()).toString())));
}
if(!italicStatus && !boldStatus)
{
writerPanel.setFormatOfTextArea(new Font((styleList.getSelectedItem()).toString(),Font.PLAIN,Integer.parseInt((sizeList.getSelectedItem()).toString())));
}
container.remove(plainOn);
container.add(plainOff);
}


if(o==boldOff)
{
boldStatus=false;
boldOn.setBounds(10,40,34,34);
if(italicStatus && plainStatus)
{
writerPanel.setFormatOfTextArea(new Font((styleList.getSelectedItem()).toString(),Font.ITALIC+Font.PLAIN,Integer.parseInt((sizeList.getSelectedItem()).toString())));
}
if(italicStatus && !plainStatus)
{
writerPanel.setFormatOfTextArea(new Font((styleList.getSelectedItem()).toString(),Font.ITALIC,Integer.parseInt((sizeList.getSelectedItem()).toString())));
}
if(plainStatus && !italicStatus)
{
writerPanel.setFormatOfTextArea(new Font((styleList.getSelectedItem()).toString(),Font.PLAIN,Integer.parseInt((sizeList.getSelectedItem()).toString())));
}
if(!italicStatus && !plainStatus)
{
writerPanel.setFormatOfTextArea(new Font((styleList.getSelectedItem()).toString(),0,Integer.parseInt((sizeList.getSelectedItem()).toString())));
}
container.remove(boldOff);
container.add(boldOn);
}

if(o==italicOff)
{
italicStatus=false;
italicOn.setBounds(44,40,34,34);
if(boldStatus && plainStatus)
{
writerPanel.setFormatOfTextArea(new Font((styleList.getSelectedItem()).toString(),Font.BOLD+Font.PLAIN,Integer.parseInt((sizeList.getSelectedItem()).toString())));
}
if(boldStatus && !plainStatus)
{
writerPanel.setFormatOfTextArea(new Font((styleList.getSelectedItem()).toString(),Font.BOLD,Integer.parseInt((sizeList.getSelectedItem()).toString())));
}
if(plainStatus && !boldStatus)
{
writerPanel.setFormatOfTextArea(new Font((styleList.getSelectedItem()).toString(),Font.PLAIN,Integer.parseInt((sizeList.getSelectedItem()).toString())));
}
if(!boldStatus && !plainStatus)
{
writerPanel.setFormatOfTextArea(new Font((styleList.getSelectedItem()).toString(),0,Integer.parseInt((sizeList.getSelectedItem()).toString())));
}
container.remove(italicOff);
container.add(italicOn);
}


if(o==plainOff)
{
plainStatus=false;
plainOn.setBounds(76,40,34,34);
if(italicStatus && boldStatus)
{
writerPanel.setFormatOfTextArea(new Font((styleList.getSelectedItem()).toString(),Font.BOLD+Font.ITALIC,Integer.parseInt((sizeList.getSelectedItem()).toString())));
}
if(italicStatus && !boldStatus)
{
writerPanel.setFormatOfTextArea(new Font((styleList.getSelectedItem()).toString(),Font.ITALIC,Integer.parseInt((sizeList.getSelectedItem()).toString())));
}
if(boldStatus && !italicStatus)
{
writerPanel.setFormatOfTextArea(new Font((styleList.getSelectedItem()).toString(),Font.PLAIN,Integer.parseInt((sizeList.getSelectedItem()).toString())));
}
if(!italicStatus && !boldStatus)
{
writerPanel.setFormatOfTextArea(new Font((styleList.getSelectedItem()).toString(),0,Integer.parseInt((sizeList.getSelectedItem()).toString())));
}
container.remove(plainOff);
container.add(plainOn);
}
this.repaint();
}

public void itemStateChanged(ItemEvent ie)
{
Object o=ie.getSource();
fontDetails=this.writerPanel.getFormatOfTextArea();
if(o==styleList)
{
writerPanel.setFormatOfTextArea(new Font((styleList.getSelectedItem()).toString(),fontDetails.getStyle(),Integer.parseInt((sizeList.getSelectedItem()).toString())));
}

if(o==sizeList)
{
writerPanel.setFormatOfTextArea(new Font((styleList.getSelectedItem()).toString(),fontDetails.getStyle(),Integer.parseInt((sizeList.getSelectedItem()).toString())));
}

}

}