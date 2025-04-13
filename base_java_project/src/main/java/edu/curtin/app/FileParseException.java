package edu.curtin.app;

/**
 * Thrown when the services or customer request files are found to have invalid formatting.
 */
public class FileParseException extends Exception
{
    public FileParseException(String msg)
    {
        super(msg);
    }

    public FileParseException(String msg, Throwable cause)
    {
        super(msg, cause);
    }
}
