package ca.jrvs.apps.grep;

import java.io.*;
import java.util.List;

public interface JavaGrep{
   
    /** 
     *   High-Level Search Workflow
     *   @throws IOException
     */ 
    void process() throws IOException;


    /** 
     *   Traverse Given Directory (Recursively) and Return All Files
     *   @param rootDir = Root Directory
     *   @return All Files Under rootDir
     */
    List<File> listFiles(String rootDir);

    /**
     *   Read File & Return All Lines
     *   @param inputFile = File To Be Read
     *   @return lines
     *   @throws IllegalArgumentException if a given inputFile is not a file
     */
    List<String> readLines(File inputFile);

    /**
     *   Check For Regex Pattern (Submitted by User)
     *   @param line = Input String
     *   @return true if there is a match
     */
    boolean containsPattern(String line);

    /**
     *   Write Lines to File
     * 
     *   Explore: FileOutputStream, OutputStreamWriter, and BufferedWriter
     * 
     *   @param lines = Matched Input String
     *   @throws IOException If Write Failed
     */
    void writeToFile(List<String> lines) throws IOException;

    String getRootPath();

    void setRoothPath(String rootPath);

    String getRegex();

    void setRegex(String regex);

    String getOutFile();

    void setOutFile(String outFile);
    
}
    