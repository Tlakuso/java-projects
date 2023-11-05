/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package read_write_tofile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author tlaku
 */
public class FileWriterTask implements Runnable {
    private String outputFilePath;
    private static final Object lock = new Object();

    public FileWriterTask(String outputFilePath) {
        this.outputFilePath = outputFilePath;
    }

    @Override
    public void run() {
         try {
            File outputFile = new File(outputFilePath);
            FileWriter fw = new FileWriter(outputFile);
            BufferedWriter bw = new BufferedWriter(fw);

            while (true) {
                String line = getFromQueue();
                if (line == null) {
                    break; // Exit the loop when there are no more lines to write
                }

                bw.write(line);
                bw.newLine();
            }

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static String queue = null;
    
    public static synchronized void addToQueue(String line) {
        queue = line;
        lock.notify();
    }    
    

    private static synchronized String getFromQueue() {
        while (queue == null) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String line = queue;
        queue = null;
        return line;
    }
    
}
