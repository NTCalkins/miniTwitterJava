package main.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.tree.DefaultMutableTreeNode;


import main.users_and_groups.Group;
import main.users_and_groups.Party;
import main.users_and_groups.User;

/**
 * Panel that allows a user to add a user or a group
 * 
 * @author Nicholas Calkins
 *
 */
public class AddPanel extends JPanel {
	
	//Swing components for the panel
	private JButton addUser;
	private JButton addGroup;
	private JTextField userID;
	private JTextField groupID;
	
	//Reference to the TreePanel for knowing what's highlighted
	private TreePanel treePanel;
	
	public AddPanel(TreePanel t) {
		super(new GridLayout(2,2));
		
		this.treePanel = t;
		
		//Instantiate components
		addUser = new JButton("Add User");
		addGroup = new JButton("Add Group");		
		userID = new JTextField();
		groupID = new JTextField();
		
		//Add them to this panel
		this.add(userID);
		this.add(addUser);
		this.add(groupID);
		this.add(addGroup);
		
		addGroup.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				//Check if the tree contains this ID already
				if (treePanel.containsID(groupID.getText())) {
					addGroup.setText("Add Group (Already in tree!)");
				}
				
				//add a new group with this ID
				else {
					Group g = new Group(groupID.getText());
					addGroup.setText("Add Group");
					treePanel.addUserOrGroup( (Party) g );
				}
			}
			
		});
		
		addUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				//Check if this ID is in the tree already
				if (treePanel.containsID(userID.getText())) {
					addUser.setText("Add User (Already in tree!)");
				}
				
				//Add a new user into the tree
				else {
					User u = new User(userID.getText());
					addUser.setText("Add User");
					treePanel.addUserOrGroup( (Party) u );
				}
			}
			
		});
	}	

}
