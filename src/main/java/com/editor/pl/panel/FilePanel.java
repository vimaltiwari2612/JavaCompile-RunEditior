package com.editor.pl.panel;
import com.editor.pl.*;
import com.editor.model.*;
import com.editor.exception.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.border.*;
public class FilePanel
{
private JFileChooser fileChooser;
private ApplicationPanel applicationPanel;
private WriterPanel writerPanel;
private File file , files[],directory;
private String contents;
private FileNameExtensionFilter extensions;
private FileWriter fileWriter; 
public FilePanel() 
{
fileChooser=new JFileChooser();
fileChooser.setOpaque(true);
fileChooser.setBackground(new Color(255,255,128));
fileChooser.repaint();
/*extensions=new FileNameExtensionFilter(".java","JAVA File","JPEG",".Txt");
fileChooser.addChoosableFileFilter(extensions);  
fileChooser.setFileFilter(extensions);  */
fileChooser.setMultiSelectionEnabled(false);
directory=fileChooser.getCurrentDirectory();
}

public boolean openFile(ApplicationPanel applicationPanel,WriterPanel writerPanel) throws DAOException
{
this.writerPanel=writerPanel;
this.applicationPanel=applicationPanel;
fileChooser.setDialogTitle("Choose A File");
fileChooser.setCurrentDirectory(directory);
int option = fileChooser.showOpenDialog(this.applicationPanel);
if(option == JFileChooser.APPROVE_OPTION)
{
try{
file=fileChooser.getSelectedFile();
if(fileChooser.getTypeDescription(file).equals("JAVA File"))
{
this.contents=TaskModel.copyContentsForEditing(file).trim();
this.writerPanel.setFileName(file.getName());
this.writerPanel.setBorder(new TitledBorder(new EtchedBorder(),file.getName()));
this.writerPanel.setTextAreaContents(this.contents);
this.writerPanel.setTextAreaEditable(true);
return true;
}
else
{
throw new DAOException("only JAVA files Are Supported!!");
}
}catch(Exception e)
{
throw new DAOException("Can't Open File!! "+e.getMessage());
}
}
    
if (option == JFileChooser.CANCEL_OPTION) {
}    
return false;
}

public boolean saveFile(ApplicationPanel applicationPanel,String contents) throws DAOException
{
this.applicationPanel=applicationPanel;
this.contents=contents;
fileChooser.setDialogTitle("Save As");
fileChooser.setCurrentDirectory(directory);
fileChooser.setApproveButtonText("Save As");
int option = fileChooser.showSaveDialog(this.applicationPanel);
if (option == JFileChooser.APPROVE_OPTION)
{
try{
file=fileChooser.getSelectedFile();
fileWriter=new FileWriter(file);
fileWriter.write(this.contents);
fileWriter.close();
}
catch(Exception e)
{
throw new DAOException("Can't Save File!! "+e.getMessage());
}
return true;
}

if (option == JFileChooser.CANCEL_OPTION) {
}
return false;
}

public boolean deleteFile(ApplicationPanel applicationPanel) throws DAOException
{
this.applicationPanel=applicationPanel;
fileChooser.setDialogTitle("Delete");
fileChooser.setApproveButtonText("Delete");
fileChooser.setMultiSelectionEnabled(true); 
fileChooser.setCurrentDirectory(directory);
int option = fileChooser.showOpenDialog(this.applicationPanel);
if (option == JFileChooser.APPROVE_OPTION)
{
try{
files=fileChooser.getSelectedFiles();
for(int i=0;i<files.length;i++)
TaskModel.deleteFile(files[i]);
}
catch(Exception e)
{
throw new DAOException("Can't Delete Files - "+e.getMessage());
}
return true;
}

if (option == JFileChooser.CANCEL_OPTION) {
}

return false;
}
}