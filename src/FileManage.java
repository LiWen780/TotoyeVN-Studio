package totoye;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;

import net.sf.image4j.codec.ico.ICOEncoder;

public class FileManage {
	
	
	public FileManage()
	{
		
	}
	
	public void convertToICO(String source, String target)
	{
		//source = file.png
		//target = file.ico
		BufferedImage listOfImages;
		try 
		{
			listOfImages = ImageIO.read(new File(source));
			ICOEncoder.write(listOfImages, new File(target));
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void copyTo(String source, String folderTarget, String type)
	{
		if(type.equals("file"))
		{
			File sourcefile = new File(source);
	        
	        String asset_name = sourcefile.getName();
	        
	        Path sourcePath = sourcefile.toPath();
	        
	        Path targetPath = Paths.get(folderTarget, asset_name);
	
	        try 
	        {
				Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
	        }
	        catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	        }
		}
		else if(type.equals("folder"))
		{
			File srcDir = new File(source);
			File destDir = new File(folderTarget);

			try 
			{
			    FileUtils.copyDirectory(srcDir, destDir);
			} catch (IOException e) {
			    e.printStackTrace();
			}
		}
	}
	
	public void hideFile(File fichier, boolean copy)
	{
		//Hide a file
		Path file1 = Paths.get(fichier.getAbsolutePath());
		try 
		{
			Files.setAttribute(file1, "dos:hidden",copy);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getObjectFromFile(File fichier)
	{
		try 
		{
			ArrayList<String> data = new ArrayList<String>();
			File myObj = fichier;
		      
		    Scanner myReader = new Scanner(myObj);
		    int j = 0;
		      
		    while (myReader.hasNextLine()) 
		    {
		    	String el = myReader.nextLine();
		    	
		    	if(j > 0 && j < 5)
		    	{
		    		data.add(el.substring(el.indexOf(":")+2, el.indexOf(";")));
		    	}
		    	j++;
		    }
		    myReader.close();
		   
		    //Name, directory path, main file path, icon path
		    return data;
		} 
		catch (FileNotFoundException e) 
		{
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		      return null;
		}
	}
	
	public boolean updateTVNFile(String name, String path, String main, String icon)
	{
		String data = "{\nNAME: "+name+";\nPATH: "+path+";\nMAIN: "+main+";\nICON: "+icon+";\n}";
		
    	File dataFile=new File(new File(System.getProperty("user.home")+"\\TotoyeVN_project"), name+".TVN");
		boolean done = false;
		try
		{
			if(!dataFile.exists())
			{
				dataFile.createNewFile();
			}
				
			FileWriter writer = new FileWriter(dataFile);
			writer.write(data);
			writer.close();
			done = true;
		}
		catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return done;
	}
	
	public File[] listOfFiles(final File folder)
	{
		ArrayList<File> names = new ArrayList<File>();
		
		for(final File fileEntry: folder.listFiles())
		{
			names.add(fileEntry);
		}
		
		return Arrays.copyOf(names.toArray(), names.toArray().length, File[].class);
	}
	
	public String[] listOfFilesName(final File folder)
	{
		ArrayList<String> names = new ArrayList<String>();
		
		for(final File fileEntry: folder.listFiles())
		{
			names.add(fileEntry.getName());
		}
		
		return Arrays.copyOf(names.toArray(), names.toArray().length, String[].class);
	}
	
	public void searchToDelete(String type, UserInterface win)
	{
		
		if(type.equals("image"))
		{
			FileExplorer moon = new FileExplorer(win.currentProject.get(1)+"\\images", win.window, "image");
			
			if(moon.approv)
			{
				Path myObj = Paths.get(moon.getFilePath()); 
				
				try 
				{
					Files.delete(myObj);
					JOptionPane.showMessageDialog(win.window, "Image deleted successfully.");
				} 
				catch (IOException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(win.window, "Failed to delete the asset.");
				} 
			}
		}
		else if(type.equals("sound"))
		{
			FileExplorer moon = new FileExplorer(win.currentProject.get(1)+"\\sounds", win.window, "sound");
			
			if(moon.approv)
			{
				Path myObj = Paths.get(moon.getFilePath()); 
				
				try 
				{
					Files.delete(myObj);
					JOptionPane.showMessageDialog(win.window, "Sound deleted successfully.");
				} 
				catch (IOException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(win.window, "Failed to delete the asset.");
				} 
			}
		}
	}
	
	public void delete(String fichier, String type)
	{
		if(type.equals("file"))
		{
			File temp = new File(fichier);
			
			if(temp.exists())
			{
				Path myObj1 = Paths.get(temp.getAbsolutePath()); 
				
				try 
				{
					Files.delete(myObj1);
				} 
				catch (IOException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		}
		else if(type.equals("folder"))
		{
			File temp = new File(fichier);
			
			if(!temp.isFile())
			{
				if(temp.list().length != 0)
				{
					String[] entries = temp.list();
					for(String s: entries)
					{
						File currentFile = new File(temp.getPath(),s);
						
						if(currentFile.isDirectory())
						{
							delete(currentFile.getAbsolutePath(), "folder");
						}
						else
						{
							delete(currentFile.getAbsolutePath(), "file");
						}
					}
					
					temp.delete();
				}
				else
				{
					temp.delete();
				}
			}
		}
	}
	
	public void deleteFile(String type, String fichier, UserInterface win)
	{
		
		if(type.equals("image") || type.equals("images"))
		{
			File moon = new File(win.currentProject.get(1)+"\\images\\"+fichier);
			
			if(moon.exists())
			{
				Path myObj = Paths.get(moon.getAbsolutePath()); 
				
				try 
				{
					Files.delete(myObj);
					JOptionPane.showMessageDialog(win.window, "Image deleted successfully.");
					win.loadFileTree();
				} 
				catch (IOException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(win.window, "Failed to delete the asset.");
				} 
			}
		}
		else if(type.equals("sound") || type.equals("sounds"))
		{
			File moon = new File(win.currentProject.get(1)+"\\sounds\\"+fichier);
			
			if(moon.exists())
			{
				Path myObj = Paths.get(moon.getAbsolutePath()); 
				
				try 
				{
					Files.delete(myObj);
					JOptionPane.showMessageDialog(win.window, "Sound deleted successfully.");
					win.loadFileTree();
				} 
				catch (IOException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(win.window, "Failed to delete the asset.");
				} 
			}
		}
	}
	
	public String readFile(File fich)
	{
		try 
		{
			String data = "";
			File myObj = fich;
		    
			if(myObj.exists())
			{
			    Scanner myReader = new Scanner(myObj);
			      
			    while (myReader.hasNextLine()) 
			    {
			    	data += myReader.nextLine()+"\n";
			    }
			    myReader.close();
			}
		    return data;
		} 
		catch (FileNotFoundException e) 
		{
		      System.out.println("Cannot read the file.");
		      e.printStackTrace();
		      return null;
		}
	}
}
