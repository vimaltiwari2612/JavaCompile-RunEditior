package com.editor.interfaces;
import com.editor.exception.*;
import java.io.*; 
public interface CopyContentsToPanelInterface
{
public String copyContentsForEditing(File file) throws DAOException;//for open a new File
public String getErrorLine(File file) throws DAOException;//for Highlighting errors
public String getErrorText(File errorFile,String javaFileName) throws DAOException; //for error Panel

}