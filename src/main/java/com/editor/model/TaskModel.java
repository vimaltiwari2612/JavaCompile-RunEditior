package com.editor.model;
import com.editor.exception.*;
import com.editor.dao.*;
import com.editor.interfaces.*;
import java.io.*; 
public class TaskModel
{
private static RunningFileInterface runningFile;
private static CopyContentsToFileInterface copyContentsToFile;
private static SearchContentsOnPanelInterface searchContentsOnPanel;
private static CopyContentsToPanelInterface copyContentsToPanel;
private static DeletionOfFileInterface deletionOfFile;
private static CreateDirectoryInterface createDirectory;
private static ValidKeywordInterface validKeyword;
private TaskModel()
{
}
static{
createDirectory=new CreateDirectory();
deletionOfFile=new DeletionOfFile();
copyContentsToFile=new CopyContentsToFile();
searchContentsOnPanel=new SearchContentsOnPanel();
copyContentsToPanel=new CopyContentsToPanel();
runningFile=new RunningFile();
validKeyword=new ValidKeyword();
}

public static boolean isValidKeyword(String keyword) throws DAOException
{
return validKeyword.isValidKeyword(keyword);
}

public static File formingBatchJobsOnWindows(String mainClassName,String classPath) throws DAOException
{
return runningFile.formingBatchJobsOnWindows(mainClassName,classPath);
}

public static void programToRunOnWindows(File file) throws DAOException
{
runningFile.programToRunOnWindows(file);
}

public static void copyContentsForCompilation(String fileName,String content) throws DAOException
{
copyContentsToFile.copyContentsForCompilation(fileName,content);
}

public static String replaceContents(String contents,String replaceFor,String replaceWith) throws DAOException
{
return searchContentsOnPanel.replaceContents(contents,replaceFor,replaceWith);
}


public static String findContents(String contents,String find) throws DAOException
{
return searchContentsOnPanel.findContents(contents,find);
}


public static String copyContentsForEditing(File file) throws DAOException
{
return copyContentsToPanel.copyContentsForEditing(file);
}

public static String getErrorLine(File file) throws DAOException
{
return copyContentsToPanel.getErrorLine(file);
}

public static String getErrorText(File errorFile,String javaFileName) throws DAOException
{
return copyContentsToPanel.getErrorText(errorFile,javaFileName);
}

public static void deleteFile(File file) throws DAOException
{
deletionOfFile.deleteFile(file);
}

public static void deleteAll() throws DAOException
{
deletionOfFile.deleteAll();
}

public static void createIfNotAvailable() throws DAOException
{
createDirectory.createIfNotAvailable();
}

}