package com.editor.interfaces;
import com.editor.exception.*;
public interface ValidKeywordInterface
{
String javaKeywordFile="JAVA_KEYWORDS.java";
public boolean isValidKeyword(String keyword) throws DAOException;
}