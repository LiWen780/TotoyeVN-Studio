package totoye;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

public class AsHTML5 {
	
	AsHTML5(String name, String folder)
	{
		File desktop = FileSystemView.getFileSystemView().getHomeDirectory();
		
		//Copy the project to the desktop
		File temp = new File(desktop+"\\"+name+"_temp123");
		
		temp.mkdir();
		
		new FileManage().copyTo(folder, temp.getAbsolutePath(),"folder");
		
		//unhide runner file and API file
		String runner = codedToHexadecimal(name)+".html";
		new FileManage().hideFile(new File(temp.getAbsolutePath()+"\\TotoyeVN_2.1.js"), false);
		new FileManage().hideFile(new File(temp.getAbsolutePath()+"\\"+runner), false);
		
		//Delete mainFile
		new FileManage().delete(temp.getAbsolutePath()+"\\"+name+".js", "file");
		
		
		//zip the project
		new ZipManager().zip(desktop+"\\"+name+".zip", temp.getAbsolutePath());
		
		//Delete the tempory folder
		//new FileManage().delete(temp.getAbsolutePath(),"folder");
		
		JOptionPane.showMessageDialog(new JFrame(), "HTML5 game created successfully.");
	}
	
	public String codedToHexadecimal(String name)
	{
		String code = "";
		
		for(int i = 0; i < name.length(); i++)
		{
			switch(name.toLowerCase().charAt(i))
			{
				case 'a':
					code += Integer.toHexString(1);
					break;
				case 'b':
					code += Integer.toHexString(2);
					break;
				case 'c':
					code += Integer.toHexString(3);
					break;
				case 'd':
					code += Integer.toHexString(4);
					break;
				case 'e':
					code += Integer.toHexString(5);
					break;
				case 'f':
					code += Integer.toHexString(6);
					break;
				case 'g':
					code += Integer.toHexString(7);
					break;
				case 'h':
					code += Integer.toHexString(8);
					break;
				case 'i':
					code += Integer.toHexString(9);
					break;
				case 'j':
					code += Integer.toHexString(10);
					break;
				case 'k':
					code += Integer.toHexString(11);
					break;
				case 'l':
					code += Integer.toHexString(12);
					break;
				case 'm':
					code += Integer.toHexString(13);
					break;
				case 'n':
					code += Integer.toHexString(14);
					break;
				case 'o':
					code += Integer.toHexString(15);
					break;
				case 'p':
					code += Integer.toHexString(16);
					break;
				case 'q':
					code += Integer.toHexString(17);
					break;
				case 'r':
					code += Integer.toHexString(18);
					break;
				case 's':
					code += Integer.toHexString(19);
					break;
				case 't':
					code += Integer.toHexString(20);
					break;
				case 'u':
					code += Integer.toHexString(21);
					break;
				case 'v':
					code += Integer.toHexString(22);
					break;
				case 'w':
					code += Integer.toHexString(23);
					break;
				case 'x':
					code += Integer.toHexString(24);
					break;
				case 'y':
					code += Integer.toHexString(25);
					break;
				case 'z':
					code += Integer.toHexString(26);
					break;
				
				default:
					code += Integer.toHexString(0);
			}
		}
		return code;
	}
}
