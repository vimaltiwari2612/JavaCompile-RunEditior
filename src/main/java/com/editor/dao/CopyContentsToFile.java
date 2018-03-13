package com.editor.dao;
import com.editor.exception.*;
import com.editor.interfaces.*;
import com.editor.model.*;
import java.io.*;
public class CopyContentsToFile implements CopyContentsToFileInterface,FileInterface
{
private File file,file1,file2,file3;
private BufferedWriter bw;
private FileWriter fw;
private String contents,savingContents,fileName;
private CompilationOfFile compilationOfFile;
private int result;
private RandomAccessFile reader,writer;
public CopyContentsToFile()
{
compilationOfFile=new CompilationOfFile();
}

public void copyContentsForCompilation(String fileName,String contents) throws DAOException
{
try{
TaskModel.createIfNotAvailable();
this.fileName=fileName;
this.contents="";
this.contents=contents;
if(this.contents.trim().equals(""))
{
copyContentsToErrorFile("Empty File",FILE_NAME);
throw new DAOException(" - Empty file");
}
file1=this.copyContentsForSaving(this.contents);
file=new File(LOCATION+this.fileName);
file3=new File(LOCATION+file1.getName());
file1.renameTo(file3);
TaskModel.deleteFile(file1);
reader=new RandomAccessFile(file3,"rw");
writer=new RandomAccessFile(file,"rw");
reader.seek(0);
writer.seek(0);
writer.setLength(0);
while(reader.getFilePointer()<reader.length())
{
writer.writeBytes(reader.readLine()+"\n");
}
reader.close();
writer.close();
TaskModel.deleteFile(file3);
result=compilationOfFile.compileFile(this.fileName);
if(result!=0)
{
throw new DAOException("see Error Panel for Details (Ctrl+P)");
}
copyContentsToErrorFile("No Error",FILE_NAME);
}catch(IOException io)
{
throw new DAOException("Error in initializing Compilation - "+io.getMessage());
}
catch(Exception e)
{
throw new DAOException("Compilation FAILED - "+e.getMessage());
}
}

public File copyContentsForSaving(String contents) throws DAOException
{
try{
TaskModel.createIfNotAvailable();
file2=new File(FILE_NAME_FOR_SAVING);
writer=new RandomAccessFile(file2,"rw");
writer.setLength(0);
writer.seek(0);
writer.writeBytes(contents);
writer.close();
}catch(IOException io)
{
throw new DAOException(" - Error in Saving "+io.getMessage());
}
catch(Exception e)
{
throw new DAOException("- Error in Saving "+e.getMessage());
}
return file2;
}

public void copyContentsToErrorFile(String contents,String fileName) throws DAOException
{
try{
TaskModel.createIfNotAvailable();
fw=new FileWriter(fileName);
bw=new BufferedWriter(fw);
bw.write(contents.trim());
bw.close();
}catch(IOException io)
{
throw new DAOException("Error in saving Error FIle Contents -"+io.getMessage());
}
}
}