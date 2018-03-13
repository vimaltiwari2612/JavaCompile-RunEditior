package com.editor.interfaces;
import com.editor.exception.*;
import java.io.*; 
public class CreateDirectory implements FileInterface,CreateDirectoryInterface
{
public File directory;

public void createIfNotAvailable() throws DAOException
{
directory=new File(PROGRAM_DIRECTORY);
if(!directory.exists())
{
directory.mkdir();
throw new DAOException("Directory is not Available due to some Errors!!\n Successfully Created Again!!");
}
}
}