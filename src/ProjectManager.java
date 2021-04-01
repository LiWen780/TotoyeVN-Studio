package totoye;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Cursor;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;


public class ProjectManager {
	
	public JFrame window;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int Swidth = (int) (screenSize.getWidth()/1.4);
    private int Sheight = (int) (screenSize.getHeight()/1.4);
    
    private JToolBar Tool;
    private JPanel panelTool;
    private JPanel projectPanel;
    private String path = "";
    private GridBagConstraints constraints;

	public ProjectManager()
	{
		window = new JFrame("TVN - Projects");
		ImageIcon img = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("folderIcon.png")).getImage().getScaledInstance( Swidth/55, Swidth/55, java.awt.Image.SCALE_SMOOTH));
		ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("TVN-logo.png"));
        window.setIconImage(icon.getImage());
		
		panelTool = new JPanel();
		panelTool.setBackground(new Color(105,105,105));
		
		JLabel l1 = new JLabel("Project name: "); l1.setForeground(Color.WHITE);
		JTextField t1 = new JTextField();
		t1.setForeground(Color.GREEN.darker());
		
		JLabel l2 = new JLabel("Path: "); l2.setForeground(Color.WHITE);
		JTextField t2 = new JTextField();
		t2.setForeground(Color.GREEN.darker());
		
		Font newFont = new Font("Times New Roman", Font.BOLD, 16);
		Font lbFont = new Font("Times New Roman", Font.BOLD, 20);
    	
		t1.setPreferredSize(new Dimension(Swidth/4, 25));
    	t1.setFont(newFont);
    	l1.setFont(lbFont);
    	l1.setLabelFor(t1);
    	
    	t2.setPreferredSize(new Dimension(Swidth/4, 25));
    	t2.setFont(newFont);
    	l2.setFont(lbFont);
    	l2.setLabelFor(t2);
		
    	JButton create=new JButton("Create Project +");
    	create.setBackground(Color.BLACK);
    	create.setForeground(Color.WHITE);
    	create.setFocusPainted(false);
    	create.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	
    	create.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent ev) {
	        	
	        	if(!t1.getText().equals("") && !t2.getText().equals(""))
	        	{
	        		//t1 == project name , t2 == folder path
	        		
	        		String trimName = t1.getText().replaceAll("\\s+", "");
	        		
	                //create Data project if not exits
		        	File projectData = new File(System.getProperty("user.home")+"\\TotoyeVN_project");
		        	
		        	if(!projectData.exists())
					{
						projectData.mkdir();
					}
		        	
		        	//Create the project folder if not exist
		        	File projectFolder = new File(path+"\\"+trimName);
		        	
		        	if(!projectFolder.exists())
					{
						projectFolder.mkdir();
					}
		        	
		        	//Add the data file of the project
		        	String data = "{\nNAME: "+t1.getText()+";\nPATH: "+path+"\\"+trimName+";\nMAIN: "+t2.getText()+"\\"+trimName+"\\"+trimName+".js"+";\nICON: none;\n}";
					
		        	File dataFile=new File(projectData, trimName+".TVN");
					
					if(!dataFile.exists())
					{
						try
						{
							dataFile.createNewFile();
							
							FileWriter writer = new FileWriter(dataFile);
							writer.write(data);
							writer.close();
						}
						catch (IOException e1)
						{
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					//Add the main file of the project
					File mainFile = new File(projectFolder, trimName+".js");
					
					if(!mainFile.exists())
					{
						try
						{
							mainFile.createNewFile();
						}
						catch (IOException e1)
						{
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					//Create the images folder
					File imageFolder = new File(projectFolder,"images");
		        	
		        	if(!imageFolder.exists())
					{
						imageFolder.mkdir();
					}
		        	
		        	//Create the sounds folder
					File soundFolder = new File(projectFolder,"sounds");
		        	
		        	if(!soundFolder.exists())
					{
						soundFolder.mkdir();
					}
					
					JOptionPane.showMessageDialog(window, "Project created.");
					
					//Reload the projectPanel
					searchProject(projectPanel);
					
					t1.setText("");
					t2.setText("");
					
	        	}
	        	else
	        	{
	        		JOptionPane.showMessageDialog(window, "Fill all textfields.");
	        	}
	        }
		});
    	
    	JButton browse = new JButton();
    	browse.setIcon(img);
    	browse.setFocusPainted(false);
    	browse.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent ev) {
                //Browse files
	        	FileExplorer mine = new FileExplorer("f:", window, "dir");
	        	
	        	path = mine.getDirectoryPath();
	        	t2.setText(path);
	        }
		});
		
    	panelTool.add(l2);
    	panelTool.add(t2);
    	panelTool.add(browse);
    	panelTool.add(l1);
    	panelTool.add(t1);
    	panelTool.add(create);

    	
		Tool = new JToolBar();
		Tool.add(panelTool);
		
		projectPanel = new JPanel(new GridBagLayout());
		projectPanel.setBackground(new Color(54,52,51));
		
		constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
        
        TitledBorder tb = new TitledBorder(BorderFactory.createEtchedBorder(Color.WHITE, Color.BLUE), "Projects");
	    tb.setTitleColor(Color.WHITE);
		projectPanel.setBorder(tb);
		
		JScrollPane vr = new JScrollPane(projectPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		searchProject(projectPanel);
		
		window.add(Tool, BorderLayout.NORTH);
		window.add(vr);
		window.setSize(Swidth, Sheight);
		window.setVisible(true);
		//window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    window.setLocationRelativeTo(null);
		
		window.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(window, 
		            "Are you sure you want to close TotoyeVN Studio?", "Close Window?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
		        {
		            System.exit(0);
		        }
		    }
		});
	}
	
	private void searchProject(JPanel pane)
	{
		pane.removeAll();
		
		FileManage manager = new FileManage();
		
		File projectDirectory = new File(System.getProperty("user.home")+"\\TotoyeVN_project");
		
		
		if(!projectDirectory.exists())
		{
			projectDirectory.mkdirs();
		}
		
		String[] fileNames = manager.listOfFilesName(new File(System.getProperty("user.home")+"\\TotoyeVN_project"));
		File[] files = manager.listOfFiles(new File(System.getProperty("user.home")+"\\TotoyeVN_project"));
			
		
		if(fileNames.length == 0)
		{
			JLabel text = new JLabel();
			text.setText("No project created yet.");
			text.setForeground(Color.WHITE);
			constraints.gridx = 0;
	        constraints.gridy = 0;
			projectPanel.add(text, constraints);
		}
		
		for(int i = 0; i < fileNames.length; i++)
		{
			ArrayList<String> fileData = manager.getObjectFromFile(files[i]);
			
			
			JButton el = new JButton(fileData.get(0));
			
			if(!fileData.get(3).equals("none"))
			{
				ImageIcon iconEl = new ImageIcon(new ImageIcon(fileData.get(3)).getImage().getScaledInstance( 9*Sheight/50, 9*Sheight/50, java.awt.Image.SCALE_SMOOTH));
				el.setIcon(iconEl);
			}
			
			el.setBackground(new Color(105,102,98));
			el.setForeground(Color.WHITE);
			el.setCursor(new Cursor(Cursor.HAND_CURSOR));
			el.setFont( new Font("Times New Roman", Font.BOLD, 24));
			el.setPreferredSize(new Dimension(9*Swidth/10, Sheight/5));
			el.setFocusPainted(false);
			
			el.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent ev) {
	                //Enter the project
		        	window.dispose();
		        	
		        	Thread openUIThread = new Thread(new Runnable()
			    	{
			    			public void run()
			    			{
			    				new UserInterface(fileData);
			    			}
			    	});
		        	openUIThread.start();
		        }
			});
			
			
			constraints.gridx = 0;
	        constraints.gridy = i;
			projectPanel.add(el, constraints);
		}
		
		pane.revalidate();
	}
	
}
