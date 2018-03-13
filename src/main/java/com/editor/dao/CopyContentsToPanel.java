package com.editor.dao;
import com.editor.exception.*;
import java.io.*;

import com.editor.model.*;
import com.editor.interfaces.*;
import com.editor.pl.panel.*;
import com.editor.pl.*;
import javax.swing.*;
public class CopyContentsToPanel implements CopyContentsToPanelInterface
{
private File file;
private RandomAccessFile randomAccessFile;
private String contents;
private int errorLineCount=0;
public String copyContentsForEditing(File file) throws DAOException
{
TaskModel.createIfNotAvailable();

try{
contents="";
this.file=file;
randomAccessFile=new RandomAccessFile(file,"rw");
randomAccessFile.seek(0);
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
contents=contents+randomAccessFile.readLine()+"\n";
}
randomAccessFile.close();
}catch(Exception e)
{
contents="";
throw new DAOException("Error in Reading File -"+e.getMessage());
}
return contents;
}

public String getErrorLine(File errorFile) throws DAOException
{
TaskModel.createIfNotAvailable();

contents="";
String newContents="",subContents="";
char ch[];
try{
this.file=errorFile;
randomAccessFile=new RandomAccessFile(file,"rw");
randomAccessFile.seek(0);
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
contents=randomAccessFile.readLine().trim();
if(contents.indexOf("error:")>=0)
{
subContents="";
subContents=randomAccessFile.readLine().trim();
newContents=newContents+"`"+subContents;
}
}
randomAccessFile.close();

}catch(Exception e)
{
throw new DAOException("Error in finding Error Text -"+e.getMessage());
}
return newContents;
}


public String getErrorText(File errorFile,String javaFileName) throws DAOException
{
TaskModel.createIfNotAvailable();

contents="";
String newContents="",subContents1="",subContents2="";
char ch[];
try{
this.file=errorFile;
randomAccessFile=new RandomAccessFile(file,"rw");
randomAccessFile.seek(0);
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
contents="";
contents=randomAccessFile.readLine().trim();
if(contents.indexOf("error:")>=0)
{
subContents1="";
subContents2="";
subContents1=contents.replace((errorFile.getPath().replaceAll(errorFile.getName().trim(),javaFileName))+":","\nLine    :-   ");
subContents2=subContents1.replaceAll(": error:","\nError  :-   ");
newContents=newContents+"`"+subContents2+"\nProblem :-   "+randomAccessFile.readLine().trim()+"\n\n";
}
}
randomAccessFile.close();

}catch(Exception e)
{
throw new DAOException("Error in finding Error Text -"+e.getMessage());
}

return newContents;
}
}