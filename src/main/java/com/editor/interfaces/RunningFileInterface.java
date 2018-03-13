package com.editor.interfaces;
import com.editor.exception.*;
import java.io.*; 
public interface RunningFileInterface
{
public File formingBatchJobsOnWindows(String mainClassName,String classPath) throws DAOException;
// create .bat File
public void programToRunOnWindows(File file) throws DAOException;//execute .bat file
}