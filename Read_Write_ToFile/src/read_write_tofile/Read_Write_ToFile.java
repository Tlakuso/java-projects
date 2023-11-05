
package read_write_tofile;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Read_Write_ToFile {

  
    public static void main(String[] args) {
        String inputFilePath = "input.txt";
        String outputFilePath = "output.txt";
        
        //create threads for reading and writing
        Thread readerThread = new Thread(new FileReaderTask(inputFilePath));
        Thread writerThread = new Thread(new FileWriterTask(outputFilePath));
        
        readerThread.start();
        writerThread.start();
        
        try {
            readerThread.join();
            writerThread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Read_Write_ToFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Data has been read from the input fileand written to the output file");
        
    }
    
}
