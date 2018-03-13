package com.editor.dao;
import com.editor.exception.*;
import com.editor.interfaces.*;
import com.editor.model.*;
import java.io.*; 
public class DeletionOfFile implements FileInterface,DeletionOfFileInterface
{

public void deleteFile(File file) throws DAOException
{
TaskModel.createIfNotAvailable();
try{
file.delete();
}catch(Exception e)
{
throw new DAOException("Cant't Delete File!! "+e.getMessage());
}
}


public void deleteAll() throws DAOException
{
TaskModel.createIfNotAvailable();
try{
File directory=new File(LOCATION);
String files[]=directory.list();
if(files!=null)
{
for(int i=0;i<files.length;i++)
{
File file=new File(LOCATION+files[i]);
if(!file.isDirectory())
{
if(!file.getName().trim().endsWith(".java"))
{
file.delete();
}
}
}
}
}catch(Exception e)
{
throw new DAOException("Cant't Refine JAVA files!! "+e.getMessage());
}
}
}
