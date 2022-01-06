package ca.jrvs.apps.grep;

import java.io.*;

public class JavaGrepImp implements JavaGrep{

    final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

    private String regex;
    private String rootPath;
    private String outFile;

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

