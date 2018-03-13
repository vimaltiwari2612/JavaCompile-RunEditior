package com.editor.dao;
import com.editor.exception.*;
import com.editor.interfaces.*;
import com.editor.model.*;
import java.io.*; 
import java.lang.reflect.*; 
public class RunningFile implements RunningFileInterface,FileInterface
{
private File file;
private Process process=null;
private String mainClassName,classPath,code,path;
private FileWriter fileWriter;
private ProcessBuilder processBuilder;
public File formingBatchJobsOnWindows(String mainClassName,String classPath) throws DAOException
{
TaskModel.createIfNotAvailable();
try{
this.mainClassName=mainClassName;
this.classPath=classPath;
file=new File(LOCATION+this.mainClassName+".bat");
path=file.getAbsolutePath().trim().replaceAll(this.mainClassName+".bat","");
this.code="@echo off\ncls\nTitle jCRE Running Window - "+mainClassName+"\ncd\\\ncd "+path+"\njava "+classPath+" "+mainClassName+"\npause\nexit";
fileWriter=new FileWriter(file);
fileWriter.write(this.code);
fileWriter.close();
}
catch(IOException io)
{
throw new DAOException("can't Run!! "+io.getMessage());
}
catch(Exception e)
{
throw new DAOException("can't Run!! "+e.getMessage());
}
return file;
}

public void programToRunOnWindows(File file) throws DAOException
{
TaskModel.createIfNotAvailable();
try
{
String commands[]={"cmd.exe","/c","start",file.getAbsolutePath()};
processBuilder=new ProcessBuilder(commands);
this.process=processBuilder.start();
}catch(Throwable e)
{
throw new DAOException("can't Run!! - "+e.getMessage());
}
}
}
