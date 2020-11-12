package main.gui;

import main.users_and_groups.Group;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * MainFrame that collates the other panels. Implements the Singleton Design
 * Pattern
 * 
 * @author Nicholas Calkins
 *
 */
public class MainFrame extends JFrame {
	
	//Static MainPanel for the Singleton design pattern
	private static MainFrame INSTANCE;
	
	//Root for the tree
	private Group rootNode;
	
	//Panels for the mainPanel
	private TreePanel t;
	private AddPanel a;
	private OpenUserPanel o;
	private VisitorPanel v;
	
	//Panels for further structuring
	private JPanel center;
	private JPanel right;
	
	
	private MainFrame() {
		
		super();
		
		//Instantiate the parts of the mainframe
		this.rootNode = new Group("Root");
		this.t = new TreePanel(rootNode);
		this.a = new AddPanel(t);
		this.o = new OpenUserPanel(t);
		this.v = new VisitorPanel(t);
		
		//Organize everything in the righthand panel
		this.setLayout(new BorderLayout());
		this.right = new JPanel();
		this.right.setLayout(new GridLayout(3,1));
		this.right.add(a);
		this.right.add(o);
		this.right.add(v);
		
		//Put the tree and rightpanel into the tree
		this.add(t,BorderLayout.CENTER);
		this.add(right,BorderLayout.EAST);
		this.setVisible(true);
		this.setSize(750,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Methods to get the only instance of the MainPanel
	 * 
	 * @return the instance of MainPanel
	 */
	public static MainFrame getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new MainFrame();
		}
		return INSTANCE;
	}
}
