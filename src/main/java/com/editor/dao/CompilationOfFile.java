package com.editor.dao;
import com.editor.exception.*;
import com.editor.interfaces.*;
import java.io.*;
import javax.tools.*;
public class CompilationOfFile implements CompilationOfFileInterface,FileInterface
{
private JavaCompiler compiler;
private FileOutputStream errorSections;
private String fileName;
private int compilationResult=1234;
private File file;

public CompilationOfFile()
{
file=new File(FILE_NAME);
System.setProperty("java.home", "C:\\Program Files\\Java\\jdk1.8.0_11\\jre");
compiler=ToolProvider.getSystemJavaCompiler();
}

public int compileFile(String fileName) throws DAOException
{
this.fileName=fileName;
try{
errorSections=new FileOutputStream(FILE_NAME);
compilationResult = compiler.run(null, null, errorSections, "-verbose", LOCATION+fileName);
}
catch(FileNotFoundException fnfe)
{
throw new DAOException(" MY_CODE_ERRORS.txt Not found!!\nPlease make a file with same name in same folder in which your java file is present. "+fnfe.getMessage());
}
catch(Exception e)
{
throw new DAOException("Can't Compile!"+e.getMessage());
}
return compilationResult;
}
}