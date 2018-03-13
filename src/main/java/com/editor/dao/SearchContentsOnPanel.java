package com.editor.dao;
import com.editor.exception.*;
import java.io.*;
import com.editor.interfaces.*;
import com.editor.pl.panel.*;
import com.editor.pl.*;
import javax.swing.*;
public class SearchContentsOnPanel implements SearchContentsOnPanelInterface,FileInterface
{
private String contents="",replaceFor="",replaceWith="",newContents="",find="";
private File file;
private RandomAccessFile randomAccessFile;
public String replaceContents(String contents,String replaceFor,String replaceWith) throws DAOException
{
this.newContents="";
this.contents="";
this.contents=contents;
this.replaceFor=replaceFor;
this.replaceWith=replaceWith;
if(this.contents.equals(""))
{
throw new DAOException("File is Empty??");
}
if(this.replaceFor.equals(""))
{
throw new DAOException("Text To Replace Required!!");
}
if(this.replaceWith.equals(""))
{
throw new DAOException("Replace With Text Required!!");
}
newContents=this.contents.replaceAll(replaceFor,replaceWith);
return newContents;
}

public String findContents(String contents,String find) throws DAOException
{
int position=-1,count1=0,count=0;
this.newContents="";
this.contents="";
this.contents=contents.trim();
this.find=find;
String k=this.contents.trim();
if(this.contents.equals(""))
{
throw new DAOException("File is Empty??");
}
if(this.find.equals(""))
{
throw new DAOException("Enter some text to find!!");
}
while(true)
{
position=this.contents.indexOf(this.find);
if(position>=0)
{
newContents=newContents+","+(position+count);
count=count+position+find.length();
this.contents="";
this.contents=k.substring(count);
}
else
{
break;
}
}
return newContents;
}


public String getPackage(String contents) throws DAOException
{
this.contents="";
if(this.contents.indexOf("package")>=0)
{

}
return this.contents;
}


}