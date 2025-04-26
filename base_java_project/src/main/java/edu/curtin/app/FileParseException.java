package edu.curtin.app;

/**
  Thrown when invalid fomatting found in files
 */
public class FileParseException extends Exception
{
    public FileParseException(String msg)
    {
        System.out.println(msg);
        System.exit(0);
    }

    public FileParseException(String msg, Throwable cause)
    {
        System.out.println(msg);
        System.exit(0);
    }
}
