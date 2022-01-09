package ca.jrvs.apps.grep;

import java.io.*;
import java.util.*;
import org.slf4j.*;
import org.apache.*;

public class JavaGrepImp implements JavaGrep{

    final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

    //Private Variables
    private String regex;
    private String rootPath;
    private String outFile;

    //regex variable
    public String getRegex() {
        return regex;
    }
    public void setRegex(String regex){
        this.regex = regex;
    }


    //rootPath variable
    public String getRoothPath() {
        return rootPath;
    }
    public void setRoothPath(String rootPath){
        this.rootPath = rootPath;
    }


    //outFile variable
    public String getOutFile() {
        return outFile;
    }
    public void setOutFile(String outFile){
        this.outFile = outFile;
    }


    public List<File> listFiles(String rootPath){
        File root = new File(rootPath);
        return Arrays.asList(root.listFiles());
    }
    public List<String> readLines(String rootPath){
        Path filePath = inputFile.toPath();
        return Files.readAllLines(filePath);
    }

    public boolean containsPattern(String input){
        return input.matches(regex);
    }

    public void writeToFile(List<String> output) throws IOException {
        FileWriter write = new FileWriter(outFile);
        for (String input:lines) {
            writer.write(input + System.lineSeparator());
        }
        writer.close();
    }
    
    
    public void process() throws IOException {
        List<String> matchedLines = new ArrayList<>();

          for (File file : listFiles(rootPath)) {
            for (String input : readLines(file)) {
              if (containsPattern(input)) {
                matchedLines.add(input);
                writeToFile(matchedLines);}
                
              }
          }
    }


    public static void main(String[] args){
        if(args.legeth !=3){
            throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
        }

        BasicConifgurator.configure();
        
        JavaGrepImp javaGrepImp = new JavaGrepImp();
        javaGrepImp.setRegex(args[0]);
        javaGrepImp.setRootPath(args[1]);
        javaGrepImp.setOutFile(args[2]);

        try {
            javaGrepImp.process();
        } catch (Exception ex) {
            javaGrepImp.logger.error("Error: Unable to prcoess", ex);
        }
}

}

