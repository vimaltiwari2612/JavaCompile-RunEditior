package com.editor.interfaces;
import com.editor.exception.*;
import java.io.*; 
public interface SearchContentsOnPanelInterface
{
public String replaceContents(String contents,String replaceFor,String replaceWith) throws DAOException;
public String findContents(String contents,String find) throws DAOException;

}