package main.users_and_groups;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import main.visitor.Visitor;

/**
 * User that shows Visitor, Composite, and Observer design patterns
 * 
 * @author Nicholas Calkins
 *
 */
public class User extends Party implements Observer, Subject {

	
	/**
	 * Default value for serializable
	 */
	private static final long serialVersionUID = 1L;

	//Words that denote a message as positive
	String[] posWords = {"excellent","good","great","nice","awesome","happy"};
	
	//Messages are those authored by this user
	private List<String> messages;
	//The feed is a mixture of user messages and following messages
	private List<String> feed;
	
	//IDs of followers and following
	private Set<String> followerIds;
	private Set<String> followingIds;
	
	//Those that are being followed and following
	private Set<Observer> followers;
	private Set<Subject> following;
	
	//variable for storing the time that the user was last updated
	long lastUpdatedTime = 0;
		
	public User(String id) {
		super(id);
		
		this.lastUpdatedTime = this.getCreationTime();
		
		//Instantiate the various fields of this User
		this.feed = new ArrayList<String>();
		this.followerIds = new HashSet<String>();
		this.followingIds = new HashSet<String>();
		this.followers = new HashSet<Observer>();
		this.following = new HashSet<Subject>();
		this.messages = new ArrayList<String>();
		
		following.add(this);
	}
	
	public void updateLastUpdatedTime()
	{
		this.lastUpdatedTime = System.currentTimeMillis();
	}
	
	public long getLastUpdatedTime()
	{
		return this.lastUpdatedTime;
	}
	
	public Set<String> getFollowerIds() {
		return followerIds;
	}
	
	public Set<String> getFollowingIds() {
		return followingIds;
	}
	
	public String getLatestTweet() {
		return messages.get(messages.size() -1);
	}
	
	public List<String> getFeed() {
		return this.feed;
	}
	
	/**
	 * Adds user and their id to this user's following and followingIds
	 * 
	 * @param user
	 */
	public void addFollowing(User user) {
		this.followingIds.add(user.getID());
		this.following.add(user);
	}
	

	/**
	 * Adds observer to the list of followers
	 * 
	 * @param observer
	 */
	@Override
	public void attach(Observer observer) {
		this.followerIds.add( ((User) observer).getID());
		this.followers.add((User) observer);
	}

	@Override
	public void notifyObservers() {
		for (Observer o : followers) {
			o.update(this);
		}
		this.updateLastUpdatedTime();
		
	}
	
	public Set<Observer> getFollowers() {
		return this.followers;
	}
	
	public Set<Subject> getFollowing() {
		return this.following;
	}
	
	/**
	 * Adds some String m to messages and feed, notifies observers
	 * 
	 * @param m
	 */
	public void postMessage(String m) {
		messages.add(new String(this.getID() + ": " + m));
		feed.add(new String(this.getID() + ": " + m));
		this.notifyObservers();
	}

	/**
	 * Adds the latest tweet from a subject to feed
	 */
	@Override
	public void update(Subject subject) {
		this.feed.add( ( (User) subject ).getLatestTweet() );
		this.updateLastUpdatedTime();
	}

	@Override
	public int getNumMessages() {
		return messages.size();
	}

	/**
	 * Returns the number of positive messages
	 */
	@Override
	public int getNumPositiveMessages() {
		int positive = 0;
		for (String m : messages) {
			for (String pos : posWords) {
				if (m.toLowerCase().contains(pos)) {
					positive++;
				}
			}
		}
		return positive;
	}

	@Override
	public int acceptVisitor(Visitor v) {
		return v.visit(this);
	}
	
	
	
}
