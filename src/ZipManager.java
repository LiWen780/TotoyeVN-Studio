package totoye;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipManager {
	
	/**
     * Size of the buffer to read/write data
     */
    private static final int BUFFER_SIZE = 4096;
    /**
     * Extracts a zip file specified by the zipFilePath to a directory specified by
     * destDirectory (will be created if does not exists)
     * @param zipFilePath
     * @param destDirectory
     * @throws IOException
     */
	
	public ZipManager()
	{
		
	}
	
	public void zip(String zipPath, String folderPath) 
	{
        String zipFile = zipPath;
        String srcDir = folderPath;
         
        try {
             
            // create byte buffer
            byte[] buffer = new byte[1024];
 
            FileOutputStream fos = new FileOutputStream(zipFile);
 
            ZipOutputStream zos = new ZipOutputStream(fos);
 
            File dir = new File(srcDir);
 
            File[] files = dir.listFiles();
 
            for (int i = 0; i < files.length; i++) 
            {
                
            	if(files[i].isFile())
            	{
	                System.out.println("Adding file: " + files[i].getName());
	 
	                FileInputStream fis = new FileInputStream(files[i]);
	 
	                // begin writing a new ZIP entry, positions the stream to the start of the entry data
	                zos.putNextEntry(new ZipEntry(files[i].getName()));
	                 
	                int length;
	 
	                while ((length = fis.read(buffer)) > 0) {
	                    zos.write(buffer, 0, length);
	                }
	 
	                zos.closeEntry();
	 
	                // close the InputStream
	                fis.close();
            	}
            	else if(files[i].isDirectory())
            	{
            		File[] subFiles = new File(files[i].getAbsolutePath()).listFiles();
            		 
                    for (int j = 0; j < subFiles.length; j++) 
                    {
                    	//For subFolder
	            		System.out.println("Adding file: " + files[i].getName()+"\\"+subFiles[j].getName());
	            		
		                FileInputStream fis1 = new FileInputStream(files[i]+"/"+subFiles[j].getName());
		                
		                // begin writing a new ZIP entry, positions the stream to the start of the entry data
		                zos.putNextEntry(new ZipEntry(files[i].getName()+"/"+subFiles[j].getName()));
		                 
		                int length1;
		 
		                while ((length1 = fis1.read(buffer)) > 0) {
		                    zos.write(buffer, 0, length1);
		                }
		 
		                zos.closeEntry();
		 
		                // close the InputStream
		                fis1.close();
                    }
            	}
            }
 
            // close the ZipOutputStream
            zos.close();
             
        }
        catch (IOException ioe) {
            System.out.println("Error creating zip file" + ioe);
        }
         
    }
	
	public void unzip(String zipFilePath, String destDirectory) throws IOException 
	{
        File destDir = new File(destDirectory);
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
        ZipEntry entry = zipIn.getNextEntry();
        // iterates over entries in the zip file
        while (entry != null) {
            String filePath = destDirectory + File.separator + entry.getName();
            if (!entry.isDirectory()) {
                // if the entry is a file, extracts it
                extractFile(zipIn, filePath);
            } else {
                // if the entry is a directory, make the directory
                File dir = new File(filePath);
                dir.mkdir();
            }
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
        }
        zipIn.close();
        System.out.println("File unzipped!");
    }
    /**
     * Extracts a zip entry (file entry)
     * @param zipIn
     * @param filePath
     * @throws IOException
     */
    private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[BUFFER_SIZE];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }
    
}
