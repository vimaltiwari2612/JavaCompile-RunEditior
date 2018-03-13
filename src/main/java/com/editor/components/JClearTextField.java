package com.editor.components;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.editor.pl.GlobalResources;
public class JClearTextField extends JLabel
{
private JTextField targetTextField;
private JTextArea targetTextArea;
public JClearTextField(JTextField targetTextField)
{
this.setIcon(new ImageIcon(this.getClass().getResource(GlobalResources.BACKSPACE_ICON)));
this.targetTextField=targetTextField;
this.addMouseListener(new MouseAdapter(){
public void mouseClicked(MouseEvent mouseEvent)
{
JClearTextField.this.targetTextField.setText("");
}
});
}

public JClearTextField(JTextArea targetTextArea)
{
this.setIcon(new ImageIcon(this.getClass().getResource(GlobalResources.BACKSPACE_ICON)));
this.targetTextArea=targetTextArea;
this.addMouseListener(new MouseAdapter(){
public void mouseClicked(MouseEvent mouseEvent)
{
JClearTextField.this.targetTextArea.setText("");
}
});
}
}