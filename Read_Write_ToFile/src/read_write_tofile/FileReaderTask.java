/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package read_write_tofile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tlaku
 */
public class FileReaderTask implements Runnable {
    private String inputFilePath;


    public FileReaderTask(String inputFilePath) {
                this.inputFilePath = inputFilePath;

    }

    @Override
    public void run() {
         try {
            File inputFile = new File(inputFilePath);
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            String line;

            while ((line = br.readLine()) != null) {
                System.out.println("Read: " + line);
                FileWriterTask.addToQueue(line); // Add the line to the write queue
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
       
    }
    
}
