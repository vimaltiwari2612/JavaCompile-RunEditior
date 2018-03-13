package com.editor.dao;
import java.util.*;
import java.io.*;
import com.editor.exception.*;
import com.editor.interfaces.*;

public class ValidKeyword implements ValidKeywordInterface
{
private HashMap<String,String> keywords;
private static boolean firstTime=true;
private String keyword="";
private Set<String> keys;
private String initialLetter="";
private boolean result=false,partialResult=false;
public boolean isValidKeyword(String keyword) throws DAOException
{
try{
if(firstTime){
keywords=new HashMap<String,String>();
File file=new File(javaKeywordFile);
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
randomAccessFile.seek(0);
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
String word=randomAccessFile.readLine().trim();
keywords.put(String.valueOf(word.charAt(0)),word);
}
keys=keywords.keySet();
randomAccessFile.close();
firstTime=false;
}
this.keyword=keyword.trim();
initialLetter=String.valueOf(this.keyword.charAt(0));
for(String key : keys)
{
if(key.equals(initialLetter)){
partialResult=true;
break;
}
}
if(!partialResult)
return result;
for(int i=0;i<keywords.size();i++)
{
if(this.keyword.equals(keywords.get(initialLetter)))
{
result=true;
return result;
}
}
}catch(Exception exception)
{
throw new DAOException("Can't Process KEYWORD VALIDATION - " +exception.getMessage());
}

return result;
}
}