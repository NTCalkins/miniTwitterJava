package main.gui;

import main.users_and_groups.Group;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * MainPanel that collates the other panels. Implements the Singleton Design
 * Pattern
 * 
 * @author Nicholas Calkins
 *
 */
public class MainPanel extends JFrame {
	
	//Static MainPanel for the Singleton design pattern
	private static MainPanel INSTANCE;
	
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
	
	
	private MainPanel() {
		
		super();
		
		
		this.rootNode = new Group("Root");
		this.t = new TreePanel(rootNode);
		this.a = new AddPanel(t);
		this.o = new OpenUserPanel(t);
		this.v = new VisitorPanel(t);
		
		this.setLayout(new BorderLayout());
		this.right = new JPanel();
		this.right.setLayout(new GridLayout(3,1));
		this.right.add(a);
		this.right.add(o);
		this.right.add(v);
		
		this.add(t,BorderLayout.CENTER);
		this.add(right,BorderLayout.EAST);
		this.setVisible(true);
		this.setSize(750,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Methods to get the only instance of the MainPanel
	 * 
	 * @return
	 */
	public static MainPanel getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new MainPanel();
		}
		return INSTANCE;
	}
}
