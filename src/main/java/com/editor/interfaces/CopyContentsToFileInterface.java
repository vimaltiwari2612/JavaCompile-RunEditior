package com.editor.interfaces;
import com.editor.interfaces.*;
import com.editor.exception.*;
import java.io.*;
public interface CopyContentsToFileInterface
{
public void copyContentsForCompilation(String fileName,String content) throws DAOException;
//for making a file ready for compilation;
public void copyContentsToErrorFile(String contents,String fileName) throws DAOException;
//for making an error file
public File copyContentsForSaving(String contents) throws DAOException;
//saving a file

}