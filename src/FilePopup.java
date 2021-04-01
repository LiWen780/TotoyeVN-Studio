package totoye;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
//import javax.swing.JSeparator;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class FilePopup extends JPopupMenu {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FilePopup(JTree tree, UserInterface ui)
	{
		JMenuItem delete = new JMenuItem("Delete Asset");
		delete.setBackground(new Color(32,32,32));
		delete.setForeground(Color.WHITE);
		//JMenuItem change = new JMenuItem("Change Node");
		
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
				
				TreePath[] paths = tree.getSelectionPaths();
				
				if(paths != null)
				{
					for(TreePath path : paths)
					{
						DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
						System.out.println(node.getParent());
						if(node.getParent() != null)
						{
							String folder = node.getParent().toString(); 
							String file = node.toString();
							
							if(folder.equals("images") || folder.equals("sounds"))
							{
								model.removeNodeFromParent(node);
								new FileManage().deleteFile(folder, file, ui);
								
								if(!ui.console.isEnabled())
					        	{
					        		ui.console.setForeground(Color.WHITE);
					        		ui.console.setEnabled(true);
					        	}
								ui.console.setText("Asset "+file+" deleted.");
							}
						}
					}
				}
			}
			
		});
		
		add(delete);
		//add(new JSeparator());
		//add(change);
	}
}
