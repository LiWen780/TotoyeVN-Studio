package totoye;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.JViewport;
import javax.swing.LookAndFeel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.InsetsUIResource;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.TabSet;
import javax.swing.text.TabStop;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
//import javax.swing.tree.TreeCellEditor;
import javax.swing.undo.UndoManager;


public class UserInterface implements ActionListener{
	
	public JFrame window;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int Swidth = (int) (screenSize.getWidth());
    private int Sheight = (int) (screenSize.getHeight());
    
    private JMenuBar mb;
	private JMenu Project;
	private JMenu Editor;
	private JMenu Game;
	private JMenu Help;
	private JMenuItem Icon;
	private JMenuItem undo;
	private JMenuItem redo;
	private JPanel fileSystem;
	private JTree fichiers;
	public JTextPane console;
	private JTabbedPane editorTab;
	private JTabbedPane fileTab;
	private JTabbedPane assetTab;
	private JTabbedPane consoleTab;
	private JPanel assetPanel;
	private JButton saveButton;
	private JButton runButton;
	int CHARACTER = 0, BUTTON = 0, IMAGE = 0, READER = 0, SCREEN = 0, BOOK = 0;
	UndoManager undoManager = new UndoManager();
	GridBagConstraints constraints = new GridBagConstraints();
	TitledBorder tb;
	private JLabel statusLabel;
	JSlider slide;
	private JLabel statusLength;
	private JLabel statusWord;
	private JPanel statusPanel;
	private EditorListener listen;
	
	ImageIcon folderIcon = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("folderIcon.png")).getImage().getScaledInstance( Swidth/55, Swidth/55, java.awt.Image.SCALE_SMOOTH));
	ImageIcon node_leaf = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("node_leaf.png")).getImage().getScaledInstance( Swidth/110, Swidth/110, java.awt.Image.SCALE_SMOOTH));
	ImageIcon node_tree = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("node.png")).getImage().getScaledInstance( Swidth/110, Swidth/110, java.awt.Image.SCALE_SMOOTH));
	
	ImageIcon javaIcon = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("javaIcon.png")).getImage().getScaledInstance( Swidth/55, Swidth/55, java.awt.Image.SCALE_SMOOTH));
	ImageIcon windowsIcon = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("windowsIcon.png")).getImage().getScaledInstance( Swidth/55, Swidth/55, java.awt.Image.SCALE_SMOOTH));
	ImageIcon html5Icon = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("html5Icon.png")).getImage().getScaledInstance( Swidth/55, Swidth/55, java.awt.Image.SCALE_SMOOTH));
	
	ImageIcon soundIcon = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("soundIcon.png")).getImage().getScaledInstance( Swidth/55, Swidth/55, java.awt.Image.SCALE_SMOOTH));
	ImageIcon imageIcon = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("imageIcon.png")).getImage().getScaledInstance( Swidth/55, Swidth/55, java.awt.Image.SCALE_SMOOTH));
	ImageIcon fileIcon = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("fileIcon.png")).getImage().getScaledInstance( Swidth/55, Swidth/55, java.awt.Image.SCALE_SMOOTH));
	ImageIcon runIcon = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("runIcon.png")).getImage().getScaledInstance( Swidth/55, Swidth/55, java.awt.Image.SCALE_SMOOTH));
	ImageIcon saveIcon = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("save_icon.png")).getImage().getScaledInstance( Swidth/55, Swidth/55, java.awt.Image.SCALE_SMOOTH));
	
	ImageIcon patreonIcon = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("patreonIcon.png")).getImage().getScaledInstance( Swidth/55, Swidth/55, java.awt.Image.SCALE_SMOOTH));
	ImageIcon twitterIcon = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("twitterIcon.png")).getImage().getScaledInstance( Swidth/55, Swidth/55, java.awt.Image.SCALE_SMOOTH));
	ImageIcon instagramIcon = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("instagramIcon.png")).getImage().getScaledInstance( Swidth/55, Swidth/55, java.awt.Image.SCALE_SMOOTH));
	
	ImageIcon runIco = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("runIco.png")).getImage().getScaledInstance( Swidth/55, Swidth/55, java.awt.Image.SCALE_SMOOTH));
	ImageIcon saveIco = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("saveIco.png")).getImage().getScaledInstance( Swidth/55, Swidth/55, java.awt.Image.SCALE_SMOOTH));
	ImageIcon projectIco = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("projectIcon.png")).getImage().getScaledInstance( Swidth/55, Swidth/55, java.awt.Image.SCALE_SMOOTH));
	ImageIcon openIco = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("openProj.png")).getImage().getScaledInstance( Swidth/55, Swidth/55, java.awt.Image.SCALE_SMOOTH));
	ImageIcon importIco = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("importIcon.png")).getImage().getScaledInstance( Swidth/55, Swidth/55, java.awt.Image.SCALE_SMOOTH));
	ImageIcon exportIco = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("exportIco.png")).getImage().getScaledInstance( Swidth/55, Swidth/55, java.awt.Image.SCALE_SMOOTH));
	
	ImageIcon colorIco = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("colorIcon.png")).getImage().getScaledInstance( Swidth/55, Swidth/55, java.awt.Image.SCALE_SMOOTH));
	ImageIcon sizeIco = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("sizeIcon.png")).getImage().getScaledInstance( Swidth/55, Swidth/55, java.awt.Image.SCALE_SMOOTH));
	ImageIcon textIco = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("textIcon.png")).getImage().getScaledInstance( Swidth/55, Swidth/55, java.awt.Image.SCALE_SMOOTH));
	ImageIcon textColorIco = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("TextColor.png")).getImage().getScaledInstance( Swidth/55, Swidth/55, java.awt.Image.SCALE_SMOOTH));
	ImageIcon fontSizeIco = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("fontSize.png")).getImage().getScaledInstance( Swidth/55, Swidth/55, java.awt.Image.SCALE_SMOOTH));
	ImageIcon supportIco = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("supportIcon.png")).getImage().getScaledInstance( Swidth/55, Swidth/55, java.awt.Image.SCALE_SMOOTH));
	ImageIcon contactIco = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("contactIcon.jpg")).getImage().getScaledInstance( Swidth/55, Swidth/55, java.awt.Image.SCALE_SMOOTH));
	ImageIcon APIDocIco = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("APIDoc.png")).getImage().getScaledInstance( Swidth/55, Swidth/55, java.awt.Image.SCALE_SMOOTH));
	
	
	String currentFile = ""; //the current file
	public ArrayList<String> currentProject; //0=>Name, 1=> URL of folder, 2=> main file path, 3=> icon url
	
	private boolean alreadyRun = false;
	private final GameRunner GR = new GameRunner();
	
	public UserInterface()
	{
		
	}
	
	public UserInterface(ArrayList<String> data)
	{
		//---------------------------------------------------------
		UIManager.put("TabbedPane.contentBorderInsets", new InsetsUIResource(1, 0, 0, 0));
		UIManager.put("TabbedPane.contentAreaColor", new ColorUIResource(Color.WHITE));
		UIManager.put("TabbedPane.focus", new ColorUIResource(new Color(32,32,32)));
		UIManager.put("TabbedPane.selected", new ColorUIResource(new Color(51,59,80)));
		UIManager.put("TabbedPane.darkShadow", new ColorUIResource(Color.DARK_GRAY));
		UIManager.put("TabbedPane.borderHightlightColor", new ColorUIResource(Color.LIGHT_GRAY));
		UIManager.put("TabbedPane.light", new ColorUIResource(Color.WHITE));
		UIManager.put("TabbedPane.tabAreaBackground", new ColorUIResource(Color.CYAN));
		UIManager.put("ToolTip.background", Color.WHITE);
		UIManager.put("ToolTip.border", new BorderUIResource(new LineBorder(Color.BLACK)));
		//------------------------------------------------------------------------
		
		window = new JFrame("TotoyeVN Studio - "+data.get(0));
		currentProject = data;
		
		ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("TVN-logo.png"));
        window.setIconImage(img.getImage());
        
        //MENUBAR-----------------------------------------------------------
        mb = new JMenuBar();
		mb.setBackground(Color.DARK_GRAY);
        
        //The project menu--------------------------------------------------------------------------------
        Project = new JMenu("Project");
		Project.setForeground(Color.WHITE);
		
		JMenuItem New = new JMenuItem("New");
			New.setIcon(projectIco);
		JMenuItem Open = new JMenuItem("Open");
			Open.setIcon(openIco);
		JMenuItem Save = new JMenuItem("Save");
			Save.setIcon(saveIco);
		
		JMenu importer = new JMenu("Import");
			importer.setIcon(importIco);
			JMenuItem importImage = new JMenuItem("Image");
			JMenuItem importSound = new JMenuItem("Sound");
			
			importImage.setIcon(imageIcon);
			importSound.setIcon(soundIcon);
		importer.add(importImage);
		importer.add(importSound);
		
		JMenu Export = new JMenu("Export");
			Export.setIcon(exportIco);
			JMenuItem java = new JMenuItem("As Java");
			JMenuItem exe = new JMenuItem("As Windows Desktop");
			JMenuItem html5 = new JMenuItem("As HTML5");
			
			java.setIcon(javaIcon);
			exe.setIcon(windowsIcon);
			html5.setIcon(html5Icon);
		Export.add(java);
		Export.add(exe);
		Export.add(html5);
		
		New.addActionListener(this);
		Open.addActionListener(this);
		Save.addActionListener(this);
		importImage.addActionListener(this);
		importSound.addActionListener(this);
		java.addActionListener(this);
		exe.addActionListener(this);
		html5.addActionListener(this);
		
		Project.add(New);
		Project.add(Open);
		Project.add(Save);
		Project.add(importer);
		Project.add(Export);
		
		//Editor menu----------------------------------------------------------------------------------------
		
		Editor = new JMenu("Editor");
		Editor.setForeground(Color.WHITE);
		
		JMenu back = new JMenu("Background Theme");
			back.setIcon(colorIco);
			JMenuItem light = new JMenuItem("Light");
			
			light.addActionListener(new ActionListener() {
			     public void actionPerformed(ActionEvent ev) 
			     { 	 
			    	 //LIGHT
			    	 consoleTab.setUI(new CustomTabbedUI(new Color(255,255,255)));
			    	 editorTab.setUI(new CustomTabbedUI(new Color(255,255,255)));
			    	 fileTab.setUI(new CustomTabbedUI(new Color(255,255,255)));
			 		 assetTab.setUI(new CustomTabbedUI(new Color(255,255,255)));
			     }
			});
			
			JMenuItem dark = new JMenuItem("Dark");
			
			dark.addActionListener(new ActionListener() {
			     public void actionPerformed(ActionEvent ev) 
			     {
			    	 //DARK
			    	 consoleTab.setUI(new CustomTabbedUI(new Color(22,22,22)));
			    	 editorTab.setUI(new CustomTabbedUI(new Color(22,22,22)));
			    	 fileTab.setUI(new CustomTabbedUI(new Color(22,22,22)));
			 		 assetTab.setUI(new CustomTabbedUI(new Color(22,22,22)));
			     }
			});
			
		back.add(light);
		back.add(dark);
		
		JMenu font = new JMenu("Font");
			font.setIcon(sizeIco);
		
		JMenu textFont = new JMenu("Text Font");
			textFont.setIcon(textColorIco);
		
			JMenuItem normal = new JMenuItem("Normal");
				normal.addActionListener(new ActionListener() {
				     public void actionPerformed(ActionEvent ev) 
				     {
				    	 JViewport greview = ((JScrollPane) editorTab.getComponentAt(0)).getViewport();
				    	 ((JTextPane) greview.getView()).setFont(new Font("Times New Roman", Font.BOLD, ((JTextPane) greview.getView()).getFont().getSize()));
				     }
				});
			JMenuItem serif = new JMenuItem("Serif");
				serif.addActionListener(new ActionListener() {
				     public void actionPerformed(ActionEvent ev) 
				     {
				    	 JViewport greview = ((JScrollPane) editorTab.getComponentAt(0)).getViewport();
				    	 ((JTextPane) greview.getView()).setFont(new Font(Font.SERIF, Font.BOLD, ((JTextPane) greview.getView()).getFont().getSize()));
				     }
				});
			JMenuItem sans_serif = new JMenuItem("Sans Serif");
				sans_serif.addActionListener(new ActionListener() {
				     public void actionPerformed(ActionEvent ev) 
				     {
				    	 JViewport greview = ((JScrollPane) editorTab.getComponentAt(0)).getViewport();
				    	 ((JTextPane) greview.getView()).setFont(new Font(Font.SANS_SERIF, Font.BOLD, ((JTextPane) greview.getView()).getFont().getSize()));
				     }
				});
			JMenuItem dialog = new JMenuItem("Dialog");
				dialog.addActionListener(new ActionListener() {
				     public void actionPerformed(ActionEvent ev) 
				     {
				    	 JViewport greview = ((JScrollPane) editorTab.getComponentAt(0)).getViewport();
				    	 ((JTextPane) greview.getView()).setFont(new Font(Font.DIALOG, Font.BOLD, ((JTextPane) greview.getView()).getFont().getSize()));
				     }
				});
			JMenuItem monospaced = new JMenuItem("Monospaced");
				monospaced.addActionListener(new ActionListener() {
				     public void actionPerformed(ActionEvent ev) 
				     {
				    	 JViewport greview = ((JScrollPane) editorTab.getComponentAt(0)).getViewport();
				    	 ((JTextPane) greview.getView()).setFont(new Font(Font.MONOSPACED, Font.BOLD, ((JTextPane) greview.getView()).getFont().getSize()));
				     }
				});
			JMenuItem chalkboard = new JMenuItem("Chalkboard");
				chalkboard.addActionListener(new ActionListener() {
				     public void actionPerformed(ActionEvent ev) 
				     {
				    	 JViewport greview = ((JScrollPane) editorTab.getComponentAt(0)).getViewport();
				    	 ((JTextPane) greview.getView()).setFont(new Font("Chalkboard", Font.BOLD, ((JTextPane) greview.getView()).getFont().getSize()));
				     }
				});
			JMenuItem georgia = new JMenuItem("Georgia");
				georgia.addActionListener(new ActionListener() {
				     public void actionPerformed(ActionEvent ev) 
				     {
				    	 JViewport greview = ((JScrollPane) editorTab.getComponentAt(0)).getViewport();
				    	 ((JTextPane) greview.getView()).setFont(new Font("Georgia", Font.BOLD, ((JTextPane) greview.getView()).getFont().getSize()));
				     }
				});
			JMenuItem papyrus = new JMenuItem("Papyrus");
				papyrus.addActionListener(new ActionListener() {
				     public void actionPerformed(ActionEvent ev) 
				     {
				    	 JViewport greview = ((JScrollPane) editorTab.getComponentAt(0)).getViewport();
				    	 ((JTextPane) greview.getView()).setFont(new Font("Papyrus", Font.BOLD, ((JTextPane) greview.getView()).getFont().getSize()));
				     }
				});
			JMenuItem tahoma = new JMenuItem("Tahoma");
				tahoma.addActionListener(new ActionListener() {
				     public void actionPerformed(ActionEvent ev) 
				     {
				    	 JViewport greview = ((JScrollPane) editorTab.getComponentAt(0)).getViewport();
				    	 ((JTextPane) greview.getView()).setFont(new Font("Tahoma", Font.BOLD, ((JTextPane) greview.getView()).getFont().getSize()));
				     }
				});
			JMenuItem verdana = new JMenuItem("Verdana");
				verdana.addActionListener(new ActionListener() {
				     public void actionPerformed(ActionEvent ev) 
				     {
				    	 JViewport greview = ((JScrollPane) editorTab.getComponentAt(0)).getViewport();
				    	 ((JTextPane) greview.getView()).setFont(new Font("Verdana", Font.BOLD, ((JTextPane) greview.getView()).getFont().getSize()));
				     }
				});
			
		textFont.add(normal);
		textFont.add(serif);
		textFont.add(sans_serif);
		textFont.add(monospaced);
		textFont.add(dialog);
		textFont.add(chalkboard);
		textFont.add(georgia);
		textFont.add(papyrus);
		textFont.add(tahoma);
		textFont.add(verdana);
		
		JMenu size = new JMenu("Size");
			size.setIcon(fontSizeIco);
		
		slide = new JSlider(10, 30);
		slide.setValue(16);
		slide.setPaintLabels(true);
		slide.setPaintTrack(true);
		slide.setPaintTicks(true);
		slide.setMajorTickSpacing(5);
		slide.setMinorTickSpacing(1);
		slide.setVisible(true);
		
		slide.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e)
			{
				float value = slide.getValue();
				
				JViewport greview = ((JScrollPane) editorTab.getComponentAt(0)).getViewport();
				((JTextPane) greview.getView()).setFont(editorTab.getComponentAt(0).getFont().deriveFont(value));
			}
		});
		
		size.add(slide);
		font.add(textFont);
		font.add(size);
		
		JMenu text = new JMenu("Text");
			text.setIcon(textIco);
		JMenuItem cut = new JMenuItem("Cut");
		JMenuItem copy = new JMenuItem("Copy");
		JMenuItem paste = new JMenuItem("Paste");
		undo = new JMenuItem("Undo");
		redo = new JMenuItem("Redo");
		
		text.add(cut); text.add(copy); text.add(paste); text.add(undo); text.add(redo);
		
		back.addActionListener(this);
		cut.addActionListener(this);
		copy.addActionListener(this);
		paste.addActionListener(this);
		undo.addActionListener(new ActionListener() {
		     public void actionPerformed(ActionEvent ev) 
		     {
		    	 undo.setEnabled(undoManager.canUndo());
		    	 if(undoManager.canUndo())
		    	 {
		    		 undoManager.undo();
		    	 }
		     }
		});
		redo.addActionListener(new ActionListener() {
		     public void actionPerformed(ActionEvent ev) 
		     {
		    	 redo.setEnabled(undoManager.canUndo());
		    	 if(undoManager.canRedo())
		    	 {
		    		 undoManager.redo();
		    	 }
		     }
		});

		Editor.add(back);
		Editor.add(font);
		Editor.add(text);
		
		//Game menu-------------------------------------------------------
		Game = new JMenu("Game");
		Game.setForeground(Color.WHITE);
				
		JMenuItem Run = new JMenuItem("Run");
		Icon = new JMenuItem("Change Icon");
					
		Run.addActionListener(this);
		Icon.addActionListener(this);
		
		Run.setIcon(runIcon);
		
		ImageIcon projectIcon = new ImageIcon(new ImageIcon(currentProject.get(3)).getImage().getScaledInstance( Swidth/55, Swidth/55, Image.SCALE_SMOOTH));
		
		if(projectIcon != null)
		{
			Icon.setIcon(projectIcon);
		}
		
		Game.add(Icon);
		Game.add(Run);
				
		//Help menu--------------------------------------------------------
		Help = new JMenu("Help");
		Help.setForeground(Color.WHITE);
				
		JMenuItem TVN = new JMenuItem("API Documentation");
			TVN.setIcon(APIDocIco);
		JMenuItem contact = new JMenuItem("Contact us");
			contact.setIcon(contactIco);
		JMenu support = new JMenu("Support the team");
			support.setIcon(supportIco);
		JMenuItem Patreon = new JMenuItem("Patreon");
		JMenuItem Twitter = new JMenuItem("Twitter");
		JMenuItem Instagram = new JMenuItem("Instagram");
				
		Patreon.setIcon(patreonIcon);
		Twitter.setIcon(twitterIcon);
		Instagram.setIcon(instagramIcon);
		
		TVN.addActionListener(this);
		contact.addActionListener(this);
		Patreon.addActionListener(this);
		Twitter.addActionListener(this);
		Instagram.addActionListener(this);
					
		support.add(Patreon); support.add(Twitter); support.add(Instagram);
				
		Help.add(TVN);
		Help.add(contact);
		Help.add(support);
				
				
		mb.add(Project);
		mb.add(Editor);
		mb.add(Game);
		mb.add(Help);
		
		//-----------------------------------------------------------------------------------
		
		editorTab = new JTabbedPane();
		editorTab.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
		editorTab.updateUI();
		editorTab.setBackground(new Color(32,32,32));
		editorTab.setForeground(Color.WHITE);
		editorTab.setUI(new CustomTabbedUI(new Color(22,22,22)));
		
		setFile(new File(currentProject.get(2)));
		
		fileTab = new JTabbedPane();
		
		fileSystem = new JPanel();
		fileSystem.setBackground(new Color(51,51,51));
		
		JScrollPane system = new JScrollPane(fileSystem, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		system.getVerticalScrollBar().setUI(new TScrollBarUI());
		system.getHorizontalScrollBar().setUI(new HTScrollBarUI());
		
		fileTab.addTab("FileSystem",system);
		
		 fileTab.updateUI();
		 fileTab.setBackground(new Color(32,32,32));
		 fileTab.setForeground(Color.WHITE);
		 fileTab.setUI(new CustomTabbedUI(new Color(22,22,22)));
		 
		 loadFileTree();
		//---------------------------------------------------------
		 
		 assetTab = new JTabbedPane();
		 assetTab.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		    
		 assetTab.updateUI();
		 assetTab.setBackground(new Color(32,32,32));
		 assetTab.setForeground(Color.WHITE);
		 assetTab.setUI(new CustomTabbedUI(new Color(22,22,22)));
		 
		 assetPanel = new JPanel();
		 assetPanel.setBackground(new Color(51,59,80));
		 
		 JScrollPane assetPane = new JScrollPane(assetPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		 assetPane.getVerticalScrollBar().setUI(new TScrollBarUI());
		 assetPane.getHorizontalScrollBar().setUI(new HTScrollBarUI());
		 
		 assetTab.addTab("Asset Viewer", assetPane);
		 //-------------------------------------------------------
	    
	    fileSystem.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource().getClass().getName().equals("javax.swing.JPanel"))
				{
					((JTree) (fileSystem.getComponent(0))).clearSelection();
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
	    });
	    
        //---------------------------------------------------------------------------------------------------------
	    
		console = new JTextPane();
		console.setEditable(false);
		console.setFont(new Font("Courier New", Font.PLAIN, 14));
		console.setBackground(new Color(31,37,49));
		console.setForeground(Color.WHITE);
		console.setCaretColor(Color.GREEN);
		
		JScrollPane consolescroll = new JScrollPane(console);
		consolescroll.setOpaque(false);
		consolescroll.getViewport().setOpaque(false);
		
		consolescroll.getVerticalScrollBar().setUI(new TScrollBarUI());
		consolescroll.getHorizontalScrollBar().setUI(new HTScrollBarUI());
		
		consoleTab = new JTabbedPane();
		
		consoleTab.updateUI();
		consoleTab.setBackground(new Color(32,32,32));
		consoleTab.setForeground(Color.WHITE);
		consoleTab.setUI(new CustomTabbedUI(new Color(22,22,22)));
		
		consoleTab.addTab("Console", consolescroll);
		
		//Separate the editorTab and the consoleTab
		
        JSplitPane spl2 = new JSplitPane(SwingConstants.HORIZONTAL, editorTab, consoleTab);
        spl2.setOneTouchExpandable(true);
        spl2.setDividerLocation((int) (1.7*Sheight/3));
         
        BasicSplitPaneDivider divider2 = (BasicSplitPaneDivider) spl2.getComponent(2);
        Border border2 = new LineBorder(Color.BLACK, 5);
        UIManager.put("BasicSplitPaneDivider.border", border2);
        divider2.setBorder(border2);
        
        //Separate filesystem and assetPanel
        
        JSplitPane spl3 = new JSplitPane(SwingConstants.HORIZONTAL, fileTab, assetTab){
	    	/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			private final int location = (int) (Sheight/2.5);
	    	{
	    		setDividerLocation(location);
	    		setOneTouchExpandable(true);
	    		setEnabled(false);
	    	}
	    	@Override
	    	public int getDividerLocation()
	    	{
	    		return location;
	    	}
	    	@Override
	    	public int getLastDividerLocation()
	    	{
	    		return location;
	    	}
	    };
         
        BasicSplitPaneDivider divider3 = (BasicSplitPaneDivider) spl3.getComponent(2);
        Border border3 = new LineBorder(Color.BLACK, 5);
        UIManager.put("BasicSplitPaneDivider.border", border3);
        divider3.setBorder(border3);
        
		
		//Separate the screen for the files system and the editor
        
        JSplitPane spl = new JSplitPane(SwingConstants.VERTICAL, spl3, spl2){
	    	/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			private final int location = (int) (Swidth/5);
	    	{
	    		setDividerLocation(location);
	    		setOneTouchExpandable(true);
	    		setEnabled(false);
	    		
	    	}
	    	@Override
	    	public int getDividerLocation()
	    	{
	    		return location;
	    	}
	    	@Override
	    	public int getLastDividerLocation()
	    	{
	    		return location;
	    	}
	    };
         
        BasicSplitPaneDivider divider = (BasicSplitPaneDivider) spl.getComponent(2);
        Border border = new LineBorder(Color.BLACK, 5);
        UIManager.put("BasicSplitPaneDivider.border", border);
        divider.setBorder(border);
        
        window.add(spl);
        window.setJMenuBar(mb);
        window.setSize(Swidth, Sheight);
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
		//STATUS BAR
		statusPanel = new JPanel();
		statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		statusPanel.setBackground(new Color(45,32,21));	
		statusPanel.setPreferredSize(new Dimension(window.getWidth(), 26));
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
		
		statusLabel = new JLabel("TotoyeVN Studio v2.0");
		statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
		statusLabel.setForeground(Color.GREEN.darker());
		
		statusLength = new JLabel("Length : 0");
		statusLength.setHorizontalAlignment(SwingConstants.LEFT);
		statusLength.setForeground(Color.WHITE);
		
		statusWord = new JLabel("Words : 0");
		statusWord.setHorizontalAlignment(SwingConstants.LEFT);
		statusWord.setForeground(Color.WHITE);
		
		statusPanel.add(statusLabel);
		statusPanel.add(new JSeparator(SwingConstants.VERTICAL));
		statusPanel.add(statusLength);
		statusPanel.add(new JSeparator(SwingConstants.VERTICAL));
		statusPanel.add(statusWord);
		
		JToolBar tools = new JToolBar();
		tools.setFloatable(false);
		tools.setRollover(true);
		tools.setBackground(new Color(31,37,49));
		tools.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		//SAVE AND RUN BUTTON
		saveButton = new JButton();
		saveButton.setIcon(saveIcon);
		saveButton.setBackground(new Color(31,37,49));
		saveButton.setForeground(Color.WHITE);
		saveButton.setFocusPainted(false);
		saveButton.setBorder(null);
		saveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		saveButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		saveButton.setToolTipText("Save project");
		
		saveButton.addActionListener(new ActionListener() {
		     public void actionPerformed(ActionEvent ev) 
		     {
	             //Save the project
		    	 SaveFile();
		     }
		});
		
		runButton = new JButton();
		runButton.setIcon(runIco);
		runButton.setBackground(new Color(31,37,49));
		runButton.setForeground(Color.WHITE);
		runButton.setFocusPainted(false);
		runButton.setBorder(null);
		runButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		runButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		runButton.setToolTipText("Run project");
		
		runButton.addActionListener(new ActionListener() {
		     public void actionPerformed(ActionEvent ev) 
		     {
	             //Run the project
		    	 RunProject();
		     }
		});
		
		tools.add(saveButton, BorderLayout.CENTER);
		tools.add(runButton, BorderLayout.CENTER);
		
		window.add(tools, BorderLayout.NORTH);
		window.add(statusPanel, BorderLayout.SOUTH);
		
		window.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(window, 
		            "Are you sure you want to close TotoyeVN Studio?", "Close Window?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
		        {
		            System.exit(0);
		            Thread.currentThread().interrupt();
		        }
		    }
		});
		
		
	}
	
	public LookAndFeel lookAndFeel(JTree mytree, String type)
	{
		LookAndFeel previousLF = UIManager.getLookAndFeel();

		try {
			
			if(type.equals("Windows"))
			{
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				SwingUtilities.updateComponentTreeUI(mytree);
			}
			else if(type.equals("Nimbus"))
			{
				for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) 
				{
					if ("Nimbus".equals(info.getName())) 
					{
	                    UIManager.setLookAndFeel(info.getClassName());
	                    SwingUtilities.updateComponentTreeUI(mytree);
	                    break;
	                }
	            }
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return previousLF;
	}
	
	
	public void loadFileTree()
	{
		if(fichiers != null)
		{
			DefaultTreeModel model = (DefaultTreeModel) fichiers.getModel();
			DefaultMutableTreeNode oldRoot = (DefaultMutableTreeNode) fichiers.getModel().getRoot();
			oldRoot.removeAllChildren();
			model.reload();
			model.setRoot(null);
		}
		
		FileManage manager = new FileManage();
		
		//Check all the files in the project folder
		File[] list = manager.listOfFiles(new File(currentProject.get(1)));
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("res");
		
		DefaultTreeCellRenderer filder = new DefaultTreeCellRenderer() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			@Override
			public Color getBackgroundNonSelectionColor()
			{
				return (null);
			}
			
			@Override
			public Color getBackgroundSelectionColor()
			{
				return (null);
			}
			
			@Override
			public Color getBackground()
			{
				return (null);
			}
			
			@Override
			public Color getTextNonSelectionColor()
			{
				return (Color.WHITE);
			}
			
			@Override
			public Color getTextSelectionColor()
			{
				return (Color.RED);
			}
			
		};
		
		filder.setClosedIcon(folderIcon);
		filder.setOpenIcon(folderIcon);
		filder.setLeafIcon(fileIcon);
		
		filder.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		for(int i = 0; i < list.length; i++)
		{
			if (list[i].isFile()) 
			{
				DefaultMutableTreeNode fich = new DefaultMutableTreeNode(list[i].getName());
				fich.setAllowsChildren(false);
				root.add(fich);
			} 
			else if (list[i].isDirectory()) 
			{
				File[] subList = manager.listOfFiles(new File(list[i].getAbsolutePath()));
				
				DefaultMutableTreeNode dire = new DefaultMutableTreeNode(list[i].getName(), true);
				
				for(int j = 0; j < subList.length; j++)
				{
					DefaultMutableTreeNode subFile = new DefaultMutableTreeNode(subList[j].getName(),false);
					dire.add(subFile);
				}
				root.add(dire);
			}
		}
		
		DefaultTreeModel rootFirm = new DefaultTreeModel(root);
		rootFirm.setAsksAllowsChildren(true);
		
		fichiers = new JTree(rootFirm);
		fichiers.setShowsRootHandles(true);
		fichiers.setCellRenderer(filder);
		fichiers.setOpaque(false);
		
		fichiers.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				// TODO Auto-generated method stub
				if(e.getNewLeadSelectionPath() != null)
				{
					Object obj = e.getNewLeadSelectionPath().getLastPathComponent();
					
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) obj;
					
					
					if(node.getParent() != null)
					{
						String folder = node.getParent().toString(); 
						String file = node.toString();
						
						//Remove older asset view
						assetPanel.removeAll();
						assetPanel.repaint();
						
						if(folder.equals("images"))
						{
							//load image
							double imageRatio = 1.0;
							
							try 
							{
								BufferedImage theImage = ImageIO.read(new File(currentProject.get(1)+"\\"+folder+"\\"+file));
								imageRatio = ((double)theImage.getWidth())/((double)theImage.getHeight());
							} 
							catch (IOException e1) 
							{
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							ImageIcon iconImage = new ImageIcon(new ImageIcon(currentProject.get(1)+"\\"+folder+"\\"+file).getImage().getScaledInstance((int)(imageRatio * Swidth/5), Swidth/5, Image.SCALE_SMOOTH));
							
							JLabel addImage = new JLabel(iconImage, JLabel.CENTER);
							
							assetPanel.add(addImage);
						}
						//Validate the new asset view
						assetPanel.revalidate();
					}
				}
			}
		});
		
		for(int i = 0; i < fichiers.getRowCount(); i++)
		{
			fichiers.expandRow(i);
		}
		
		fichiers.putClientProperty("JTree.lineStyle", "Angled");
		fichiers.setRowHeight(35);
		
		final FilePopup filepopup = new FilePopup(fichiers, this);
		fichiers.addMouseListener(new MouseAdapter() {
			
			public void mouseReleased(MouseEvent e)
			{
				if(e.isPopupTrigger() && fichiers.getSelectionPaths() != null)
				{
					filepopup.show(e.getComponent(), e.getX(),e.getY());
				}
			}
		});
		
		expandTree(fichiers, true);
		
		fileSystem.removeAll();
		fileSystem.repaint();
		fileSystem.add(fichiers, BorderLayout.WEST);
		fileSystem.revalidate();
		
		try 
		{
			UIManager.setLookAndFeel(lookAndFeel(fichiers, "Windows"));
		} 
		catch (UnsupportedLookAndFeelException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static void setTabs( final JTextPane textPane, int charactersPerTab)
    {
        FontMetrics fm = textPane.getFontMetrics( textPane.getFont() );
//          int charWidth = fm.charWidth( 'w' );
        int charWidth = fm.charWidth( ' ' );
        int tabWidth = charWidth * charactersPerTab;
//      int tabWidth = 100;

        TabStop[] tabs = new TabStop[5];

        for (int j = 0; j < tabs.length; j++)
        {
            int tab = j + 1;
            tabs[j] = new TabStop( tab * tabWidth );
        }

        TabSet tabSet = new TabSet(tabs);
        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setTabSet(attributes, tabSet);
        int length = textPane.getDocument().getLength();
        textPane.getStyledDocument().setParagraphAttributes(0, length, attributes, false);
    }
	
    private void expandTree(JTree tree, boolean expand) {
        TreeNode root = (TreeNode) tree.getModel().getRoot();
        expandAll(tree, new TreePath(root), expand);
    }

    private void expandAll(JTree tree, TreePath path, boolean expand) {
        TreeNode node = (TreeNode) path.getLastPathComponent();

        if (node.getChildCount() >= 0) {
            @SuppressWarnings("rawtypes")
			Enumeration enumeration = node.children();
            while (enumeration.hasMoreElements()) {
                TreeNode n = (TreeNode) enumeration.nextElement();
                TreePath p = path.pathByAddingChild(n);

                expandAll(tree, p, expand);
            }
        }

        if (expand) {
            tree.expandPath(path);
        } else {
            tree.collapsePath(path);
        }
    }
    
	
	public void setFile(File fichier)
	{
		//Translate ele = new Translate();
		listen = new EditorListener();
        
		JTextPane t = new JTextPane();
		
		listen.setContainer(t);
    	t.setStyledDocument(listen.getDoc());
    	
		t.setBackground(Color.BLACK);
		t.setFont(new Font("Times New Roman", Font.BOLD, 16));
		t.setCaretColor(Color.WHITE);
		
		setTabs(t, 8);
		
		JScrollPane tr = new JScrollPane(t, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		tr.getVerticalScrollBar().setUI(new TScrollBarUI());
		tr.getHorizontalScrollBar().setUI(new HTScrollBarUI());
		
		t.setText(new FileManage().readFile(new File(currentProject.get(2))));
		
		t.getDocument().addUndoableEditListener(new UndoableEditListener() {

			@Override
			public void undoableEditHappened(UndoableEditEvent e) {
				// TODO Auto-generated method stub
				undoManager.addEdit(e.getEdit());
			}
		});
		
		t.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e)
			{
				editorTab.setTitleAt(0, "*"+new File(currentProject.get(2)).getName());
			}

			@Override
			public void keyPressed(KeyEvent e)
			{
				// TODO Auto-generated method stub
				if((e.getKeyCode() == KeyEvent.VK_S) && ((e.getModifiersEx() & InputEvent.CTRL_DOWN_MASK) != 0))
				{
					//CTRL + S to save project
					SaveFile();
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		t.addCaretListener(new CaretListener() {

			@Override
			public void caretUpdate(CaretEvent e) {
				// TODO Auto-generated method stub
				int longueur = t.getText().length();
				int wordCount = t.getText().split("\\s").length;
				
				statusLength.setText("Length : "+longueur);
				statusWord.setText("Words : "+wordCount);
			}
			
		});
		
        TextLineNumber tln = new TextLineNumber(t);
        tln.setBorderGap(1);
        tln.setBackground(Color.DARK_GRAY);
        tln.setForeground(Color.WHITE);
        tr.setRowHeaderView(tln);
        
        editorTab.add(fichier.getName(), tr);
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
	
	public void SaveFile() {
		
		 // Save the current data in current file 
		File fi_save = new File(currentProject.get(2));
				
		JViewport viw = ((JScrollPane) editorTab.getComponentAt(0)).getViewport();
		String temporary[] = ((JTextPane) viw.getView()).getText().split("\n");
	
		try 
		{ 
			// Create a file writer 
			FileWriter wr = new FileWriter(fi_save, false); 
	
			// Create buffered writer to write 
			BufferedWriter w = new BufferedWriter(wr); 
					
			//write
					
			for(int ir = 0; ir<temporary.length;ir++)
			{         
				w.write(temporary[ir]);
				w.newLine();			
			}
	
			w.flush(); 
			w.close(); 
			editorTab.setTitleAt(0, ""+new File(currentProject.get(2)).getName());
			
        	if(!console.isEnabled())
        	{
        		console.setForeground(Color.WHITE);
        		console.setEnabled(true);
        	}
        	
			console.setText("Project saved!");
			
			JOptionPane.showMessageDialog(window, "Project saved!"); 
		} 
		catch (Exception evtsave) 
		{ 
			JOptionPane.showMessageDialog(window, evtsave.getMessage()); 
		}
	}
	
	public void RunProject()
	{
		File projectRunner = new File(currentProject.get(1));
    	
    	File runnerFile =new File(projectRunner, codedToHexadecimal(currentProject.get(0))+".html");
    	File apiFile = new File(projectRunner, "TotoyeVN_2.1.js");
		
    	//Check if running file already exists
		if(!runnerFile.exists())
		{
			try
			{
				runnerFile.createNewFile();
			} 
			catch (IOException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else
		{
			//Unhide runner file to modify
			new FileManage().hideFile(runnerFile, false);
		}
		
		//Check if API is already there
		if(!apiFile.exists())
		{
			//copy the API to the project Folder
			try 
			{
				new FileManage().copyTo(URLDecoder.decode(new File(getClass().getClassLoader().getResource("API//TotoyeVN_2.1.js").getPath()).getAbsolutePath(),"UTF-8"), currentProject.get(1),"file");
			} 
			catch (UnsupportedEncodingException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		try
		{
			String js =  ((JTextPane)((JViewport)((JScrollPane) editorTab.getComponentAt(0)).getViewport()).getView()).getText();
			
			String runnerData = "<!DOCTYPE html>\n<html>\n<head>\n<title>"+currentProject.get(0)+"</title>\n<meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0'/>\n<script src = 'TotoyeVN_2.1.js'></script>\n<style>\n body{margin:0;padding:0;} \n </style>\n</head>\n<body>\n<script type = 'text/javascript'>\n"+js+"\n</script>\n</body>\n</html>";
				
			FileWriter writer = new FileWriter(runnerFile);
			writer.write(runnerData);
			writer.close();
		}
		catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Hide both API and RUNNER files
		new FileManage().hideFile(runnerFile, true);
		new FileManage().hideFile(apiFile, true);
		
		//Start the game runner
		Thread runner = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				if(!alreadyRun)
				{
					setAlreadyRun();
					GR.lancer(currentProject.get(0), runnerFile.getAbsolutePath(), console, currentProject.get(3),"false");	
				}
				else
				{
					GR.lancer(currentProject.get(0), runnerFile.getAbsolutePath(), console, currentProject.get(3),"true");
				}
			}
			
		});
		runner.setDaemon(true);
		console.setText("Running project...");
		runner.start();
	}
	
	
	public void setAlreadyRun()
	{
		alreadyRun = true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s = e.getActionCommand();
		
		if(s.equals("New"))
		{	
			window.dispose();	
			new ProjectManager();
		}
		
		else if(s.equals("Open"))
		{
			window.dispose();
			new ProjectManager();
		}
		
		else if(s.equals("Image"))
		{
			Import test = new Import(this, "image");
			
			if(test.isDone())
			{
				JOptionPane.showMessageDialog(window, "Image imported.");
				loadFileTree();
				console.setText("Image imported!");
			}
		}
		
		else if(s.equals("Sound"))
		{
			Import test = new Import(this, "sound");
			
			if(test.isDone())
			{
				JOptionPane.showMessageDialog(window, "Sound imported.");
				loadFileTree();
				console.setText("Sound imported!");
			}
		}
		
		else if(s.equals("Browser"))
		{
			File projectRunner = new File(System.getProperty("user.home")+"\\000215E6_Runner");
        	
        	if(!projectRunner.exists())
			{
				projectRunner.mkdir();
			}
        	
        	File runnerFile =new File(projectRunner, codedToHexadecimal(currentProject.get(0))+".html");
			
			if(!runnerFile.exists())
			{
				try
				{
					runnerFile.createNewFile();
				} 
				catch (IOException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			try
			{
				String js =  ((JTextPane)((JViewport)((JScrollPane) editorTab.getComponentAt(0)).getViewport()).getView()).getText();
				
				String runnerData = "<!DOCTYPE html><html><head><title>"+currentProject.get(0)+"</title><meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0'/><style>body{margin:0;padding:0;}</style></head><body><script type='text/javascript'>"+js+"</script></body></html>";
			
				FileWriter writer = new FileWriter(runnerFile);
				writer.write(runnerData);
				writer.close();
			}
			catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
			new OpenBrowser(runnerFile.getAbsolutePath(), "local");
		}
		else if(s.equals("Run"))
		{
			//Run the script project
			RunProject();
		}
		
		else if(s.equals("Change Icon"))
		{
			FileExplorer logo = new FileExplorer(currentProject.get(1)+"\\images",window,"image");
			
			if(logo.isDone())
			{
				boolean DONE = new FileManage().updateTVNFile(currentProject.get(0), currentProject.get(1), currentProject.get(2), logo.getFilePath());
				
				if(DONE == true)
				{
					currentProject.set(3, logo.getFilePath());
					ImageIcon newProjectIcon = new ImageIcon(new ImageIcon(logo.getFilePath()).getImage().getScaledInstance( Swidth/55, Swidth/55, Image.SCALE_SMOOTH));
					
					Icon.setIcon(newProjectIcon);
					JOptionPane.showMessageDialog(window, "Icon changed.");
					console.setText("Icon changed!");
				}
			}
		}
		
		else if(s.equals("Cut"))
		{
			JViewport greview = ((JScrollPane) editorTab.getComponentAt(editorTab.getSelectedIndex())).getViewport();

			if(((JTextPane) greview.getView()).getSelectedText() != null)
			{
				((JTextPane) greview.getView()).cut();
				console.setText("Text cut.");
			}
		}
		else if(s.equals("Copy"))
		{
			JViewport greview = ((JScrollPane) editorTab.getComponentAt(editorTab.getSelectedIndex())).getViewport();
        		
			if(((JTextPane) greview.getView()).getSelectedText() != null)
			{
				((JTextPane) greview.getView()).copy();
				console.setText("Text copied!");
			}
		}
		else if(s.equals("Paste"))
		{
        	JViewport greview = ((JScrollPane) editorTab.getComponentAt(editorTab.getSelectedIndex())).getViewport();
        	((JTextPane) greview.getView()).paste();
        	console.setText("Text pasted!");
		}
		
		else if(s.equals("As Java"))
		{
			//Export the game as Java Game
			System.out.println("Export as Java Game");
			new AsJava(currentProject.get(0), currentProject.get(1), codedToHexadecimal(currentProject.get(0))+".html");
			console.setText("Exporting project...");
		}
		else if(s.equals("As Windows Desktop"))
		{
			//Export the game as Windows Game
			System.out.println("Export as Windows Desktop Game");
			new AsWindowsEXE(currentProject.get(0), currentProject.get(1), currentProject.get(2), currentProject.get(3));
			console.setText("Exporting project...");
		}
		else if(s.equals("As HTML5"))
		{
			//Export the game as Windows Game
			System.out.println("Export as HTML5 Game");
			new AsHTML5(currentProject.get(0), currentProject.get(1));
			console.setText("Exporting project...");
		}
		
		else if(s.equals("Save"))
		{
			//Save file
			SaveFile();
		}
		
		else if(s.equals("API Documentation"))
		{
			new OpenBrowser("https://liwenstudios.fun/TotoyeVN","web");
			console.setText("Checking API documentation.");
		}
		
		else if(s.equals("Contact us"))
		{
			new OpenBrowser("mailto:%20manhaipictures@gmail.com", "web");
		}
		else if(s.equals("Patreon"))
		{
			new OpenBrowser("https://www.patreon.com/liwenstudios", "web");
		}
		else if(s.equals("Twitter"))
		{
			new OpenBrowser("https://twitter.com/liwenstudios", "web");
		}
		else if(s.equals("Instagram"))
		{
			new OpenBrowser("https://www.instagram.com/liwenstudios/", "web");
		}
	}
}
