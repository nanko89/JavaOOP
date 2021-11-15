package implementation.appenders;

import interfaces.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class LogFile implements File {
    private static final String DEFAULT_FILE_NAME = "default.txt";
    private StringBuilder text;
    private int size;
    private String fileName;

    public LogFile() {
        this(DEFAULT_FILE_NAME);
    }

    public LogFile(String fileName) {
        this.text = new StringBuilder();
        this.size = 0;
        this.fileName = fileName;
    }

    @Override
    public int getSize() {
        return calculateSize();
    }

    private int calculateSize(){
        int size = 0;
        for (char  c : this.text.toString().toCharArray() ) {
            if (Character.isAlphabetic(c)){
                size += c;
            }
        }
        return size;
    }

    @Override
    public boolean write(String text) {
        try {
            Files.write(Paths.get(this.fileName),text.getBytes(), StandardOpenOption.APPEND);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void append(String text) {
        text = addLineBreakInFile(text);
        this.text.append(text);
        this.write(text);
    }

    String addLineBreakInFile(String text){
        StringBuilder sb = new StringBuilder(text);
        sb.append(System.lineSeparator());
        return sb.toString();
    }
}
