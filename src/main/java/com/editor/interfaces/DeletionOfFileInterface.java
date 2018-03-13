package com.editor.interfaces;
import com.editor.exception.*;
import java.io.*; 
public interface DeletionOfFileInterface
{
public void deleteFile(File file) throws DAOException;
public void deleteAll() throws DAOException;
}