package main.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import main.users_and_groups.User;

/**
 * Frame for holding the user information
 * 
 * @author Nicholas Calkins
 *
 */
public class UserFrame extends JFrame {
	
	private TreePanel t;
	
	private User u;
	private UserFrame self;

	private Set<UserFrame> currentPanels;
	private Set<User> currentUsers;
	
	private JPanel mainPanel;
	
	private JLabel nameLabel;
	
	private JLabel lastUpdatedLabel;
	
	private JTextField followText;
	private JButton followButton;
	private JPanel followPanel;
	
	private JLabel followingLabel;
	private DefaultListModel<String> followingListModel;
	private JList followingList;
	
	private JTextField tweetText;
	private JButton tweetButton;
	private JPanel tweetPanel;
	
	private JLabel feedLabel;
	private DefaultListModel feedListModel;
	private JList feedList;
	
	
	public UserFrame(TreePanel t,User user, Set<UserFrame> cp, Set<User> users) {
		super();
		this.t = t;
		this.u = user;
		this.currentUsers = users;
		
		//Needed this in order to reference the UserFrame in the actionlistener
		self = this;
		
		nameLabel = new JLabel(u.getID(), SwingConstants.CENTER);
		
		lastUpdatedLabel = new JLabel("Time last updated: " + String.valueOf(u.getLastUpdatedTime()), SwingConstants.CENTER);
				
		this.currentPanels = cp;
		this.mainPanel = new JPanel();
		
		mainPanel.setLayout(new GridLayout(8,1));
		
		
		//Components for following a user
		followText = new JTextField();
		followButton = new JButton("Follow User");
		
		//Set up the follow portion of the panel
		followPanel = new JPanel(new GridLayout(1,2));
		followPanel.add(followText);
		followPanel.add(followButton);
		
		//Set up the area for the user to tweet
		tweetText = new JTextField();
		tweetButton = new JButton("Tweet message");
		
		tweetPanel = new JPanel(new GridLayout(1,2));
		tweetPanel.add(tweetText);
		tweetPanel.add(tweetButton);
		
		//Show the user their followers
		followingLabel = new JLabel("Following", SwingConstants.CENTER);
		followingListModel = new DefaultListModel();
		followingList = new JList(followingListModel);
		
		//Set up the follower list
		for (String followerId : u.getFollowingIds())
		{
			followingListModel.addElement(followerId);
		}
		
		//Set up the user's feed
		feedLabel = new JLabel("News Feed", SwingConstants.CENTER);
		feedListModel = new DefaultListModel();
		feedList = new JList(feedListModel);
		
		for (String feedMessage : u.getFeed())
		{
			feedListModel.addElement(feedMessage);
		}
		
		//Add everything to the main panel
		mainPanel.add(nameLabel);
		mainPanel.add(lastUpdatedLabel);
		mainPanel.add(followPanel);
		mainPanel.add(followingLabel);
		mainPanel.add(followingList);
		mainPanel.add(tweetPanel);
		mainPanel.add(feedLabel);
		mainPanel.add(feedList);
		
		mainPanel.setVisible(true);
		
		//Add the mainPanel to the frame
		this.add(mainPanel);
		
		this.setSize(500,500);
		
		this.setVisible(true);
		
		//Account for this panel and user 
		this.currentPanels.add(this);
		this.currentUsers.add(u);
		
		followButton.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				User uToFollow;
				String uId = followText.getText();
				
				//Only follow if there is someone by that ID
				if (!t.containsID(uId)) {
					followButton.setText("Follow User (Error, user not found!)");
				}
				else {
					uToFollow = t.getUser(uId);
					//Check if the found Party is actually a user
					if (uToFollow.getClass() != User.class) {
						followButton.setText("Follow User (Error: Not a user!)");
					}
					else {
						followButton.setText("Follow User");
						//Prompt the found user to attach this user
						uToFollow.attach(u);
						//Add this user to our following list
						u.addFollowing(uToFollow);
						//Refresh the list of followers
						updateFollowingList();
					}
				}
				
			}
		});
		
		tweetButton.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String msg = tweetText.getText();
				//This posting will prompt the observers to update
				u.postMessage(msg);
				//Prompt each open userframe to update their feeds
				for (UserFrame ufr : currentPanels) {
					ufr.updateFeedList();
				}
			}
			
		});
		
		addWindowListener((WindowListener) new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				currentPanels.remove(self);
				currentUsers.remove(u);
			}
		});
	}
	
	/**
	 * Refresh the feedListModel of this UserFrame
	 */
	private void updateFeedList() {
		feedListModel.removeAllElements();
		for (String feedMessage : u.getFeed())
		{
			feedListModel.addElement(feedMessage);
		}
		feedList.updateUI();
		lastUpdatedLabel.setText("Time last updated: " + String.valueOf(u.getLastUpdatedTime()));
		lastUpdatedLabel.updateUI();
	}
	
	/**
	 * Refresh the followingListModel of this UserFrame
	 */
	private void updateFollowingList() {
		followingListModel.removeAllElements();
		for (String followerId : u.getFollowingIds())
		{
			followingListModel.addElement(followerId);
		}
		followingList.updateUI();
	}
}
