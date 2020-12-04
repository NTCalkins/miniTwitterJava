package main.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.users_and_groups.Group;
import main.users_and_groups.Party;
import main.visitor.GroupCountVisitor;
import main.visitor.MessageCountVisitor;
import main.visitor.PositiveCountVisitor;
import main.visitor.UserCountVisitor;
import main.visitor.LatestUserVisitor;
import main.visitor.ValidEntityVisitor;

/**
 * Panel for housing the visitor buttons that will determine
 * number of groups, users, etc.
 * 
 * @author Nicholas Calkins
 *
 */
public class VisitorPanel extends JPanel {

	private JLabel identityLabel;
	private JLabel numberLabel;
	
	//Buttons for getting user information
	private JButton groupNumButton;
	private JButton userNumButton;
	private JButton numMessagesButton;
	private JButton numPosMessagesButton;
	private JButton validEntityButton;
	private JButton lastUpdatedButton;
	
	//Visitors for the various metrics
	private UserCountVisitor userCounter;
	private GroupCountVisitor groupCounter;
	private PositiveCountVisitor posCounter;
	private MessageCountVisitor msgCounter;
	private LatestUserVisitor lastUpdatedVisitor;
	private ValidEntityVisitor validEntityVisitor;
	
	//Tree panel, mainly to get the root of the tree
	private TreePanel t;
	
	private Party root;
	
	public VisitorPanel(TreePanel treePanel) {
		super();
		
		this.setLayout(new GridLayout(4,3));
		this.t = treePanel;
		
		//Get a reference to the root of the tree
		this.root = (Party) treePanel.getTree().getModel().getRoot();
		
		//Instantiate labels and buttons
		identityLabel = new JLabel("Metric: ");
		numberLabel = new JLabel("N/A");
		
		groupNumButton = new JButton("Number of Groups");
		userNumButton = new JButton("Number of Users");
		numMessagesButton = new JButton("Number of Messages");
		numPosMessagesButton = new JButton("Percent Messages Positive");
		validEntityButton = new JButton("Check if Users and Groups are Valid");
		lastUpdatedButton = new JButton("Get Last Updated User");
		
		//Instantiate visitors
		userCounter = new UserCountVisitor();
		groupCounter = new GroupCountVisitor();
		posCounter = new PositiveCountVisitor();
		msgCounter = new MessageCountVisitor();
		lastUpdatedVisitor = new LatestUserVisitor();
		validEntityVisitor = new ValidEntityVisitor();
		
		
		
		//Add everything to the GridLayout VisitorPanel
		this.add(identityLabel);
		this.add(numberLabel);
		
		this.add(groupNumButton);
		this.add(userNumButton);
		this.add(numMessagesButton);
		this.add(numPosMessagesButton);
		this.add(validEntityButton);
		this.add(lastUpdatedButton);
		
		lastUpdatedButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				root.acceptVisitor(lastUpdatedVisitor);
				identityLabel.setText("Last updated user: " + lastUpdatedVisitor.getLatestUser());
				numberLabel.setText("");
			}
			
		});
		
		validEntityButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int valid = root.acceptVisitor(validEntityVisitor);
				if (valid == 1)
				{
					identityLabel.setText("All users and groups are valid!");
				}
				else
				{
					identityLabel.setText("All users and groups are NOT valid!");
				}
				numberLabel.setText("");
			}
			
		});
		
		groupNumButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				identityLabel.setText("Number of Groups: ");
				
				//Accept the visitor, set the text accordingly
				int numGroups = root.acceptVisitor(groupCounter);
				numberLabel.setText(Integer.toString(numGroups));
				
			}
			
		});
		
		userNumButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				identityLabel.setText("Number of Users: ");
				//Accept the visitor, set the text accordingly
				int numUsers = root.acceptVisitor(userCounter);
				
				numberLabel.setText(Integer.toString(numUsers));
			}
			
		});
		
		numMessagesButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				identityLabel.setText("Number of Messages: ");
				//Accept the visitor, set the text accordingly
				int numMessages = root.acceptVisitor(msgCounter);
				numberLabel.setText(Integer.toString(numMessages));
				
			}
			
		});
		
		numPosMessagesButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				identityLabel.setText("Percent positive messages: ");
				//Accept the visitors
				int numMessages = root.acceptVisitor(msgCounter);
				int numPosMessages = root.acceptVisitor(posCounter);
				
				//Calculate the percentage positive
				double percentPos = 0;
				if (numMessages == 0) {
					percentPos = 0;
				}
				else {
					percentPos = ((double) numPosMessages / numMessages) * 100;
				}
				
				numberLabel.setText(Double.toString(percentPos) + "%");
				
			}
			
		});
		
		
	}
}
