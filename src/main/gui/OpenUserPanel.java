package main.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import main.users_and_groups.Party;
import main.users_and_groups.User;


public class OpenUserPanel extends JPanel {
	
	//Sets for keeping track of the current users and panels
	private Set<UserFrame> currentPanels;
	private Set<User> currentUsers;
	
	//Button for opening user view
	private JButton openUserButton;
	
	//reference to tree to know which user to open
	private TreePanel treePanel;
	
	public OpenUserPanel(TreePanel t) {
		super();
		
		//Instantiate class variables
		this.treePanel = t;
		this.currentPanels = new HashSet<UserFrame>();
		this.currentUsers = new HashSet<User>();
		
		this.openUserButton = new JButton("Open User View");
		add(openUserButton);
		
		openUserButton.addActionListener((ActionListener) new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				//Get the Party object highlighted by the user
				Party p = (Party) t.getTree().getLastSelectedPathComponent();
				
				//If there is no one highlighted or a group is highlighted, alert user
				if (p == null || p.getClass() != User.class) {
					openUserButton.setText("Open User View (Highlight a User!)");
				}
				
				else {
					openUserButton.setText("Open User View");
					User u = (User) p;
					
					//Only open a new UserFrame if one isn't open already
					if (currentUsers.contains(u)) {
						openUserButton.setText("Open User View (That user is open!)");
					}
					else {
						new UserFrame(t,u,currentPanels,currentUsers);
					}
				}
			}
			
		});
	}
}
