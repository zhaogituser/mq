package util;

import java.io.*;

/**
 * Created by ${zhao} on 2017/11/2 0002.
 */
public class CancellableReader {
    private Thread readerThread; // only one at a time supported
    private FileInputStream dataFile;


    public synchronized void startReaderThread() throws IllegalStateException, FileNotFoundException {
        if (readerThread != null) throw new IllegalStateException();
        dataFile = new FileInputStream("F:\\cdcard\\two.xml");
        readerThread = new Thread(new Runnable() {
            public void run() {
                doRead();
            }
        });
        readerThread.start();
    }

    protected synchronized void closeFile() { // utility method
        if (dataFile != null) {
            try {
                dataFile.close();
            } catch (IOException ignore) {
            }
            dataFile = null;
        }
    }

    protected void doRead() {
        try {
            int c = 0;
//            while (!Thread.interrupted()) {
                try {
                    System.out.println(dataFile.read());
//                    if (c == -1) {
//                        break;
//                    }
//                    else{
//                        process(c);
//                    }
                    byte[] bytes=new byte[1024];
                    FileOutputStream fout=new FileOutputStream("F:\\cdcard\\three.xml");
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fout);
                    long start=System.currentTimeMillis();
                    while ((c=dataFile.read(bytes))!=-1){
                        System.out.println("C的值:"+c);
                        bufferedOutputStream.write(bytes);
                    }
                    long end=System.currentTimeMillis();
                    System.out.println("花费时间为:"+(end-start)+"秒");
                    bufferedOutputStream.close();
                } catch (IOException ex) {
//                   break; // perhaps first do other cleanup
                }
//            }
        } finally {
            closeFile();
            synchronized (this) {
                readerThread = null;
            }
        }
    }



    public synchronized void cancelReaderThread() {
        if (readerThread != null) readerThread.interrupt();
        closeFile();
    }

    public static void main(String[] args) {
        try {
            new CancellableReader(). startReaderThread();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
