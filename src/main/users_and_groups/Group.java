package main.users_and_groups;

import java.util.HashSet;
import java.util.Set;

import main.visitor.Visitor;

/**
 * Composite class for holding users and groups in order to demonstrate the
 * composite software design pattern as well as Observer design pattern
 * 
 * @author Nicholas Calkins
 *
 */
public class Group extends Party {

	/**
	 * Default value for serializable
	 */
	private static final long serialVersionUID = 1L;
	
	//Sets for keeping track of group members
	private Set<Party> entries;
	private Set<String> ids;
	
	public Group(String id) {
		super(id);
		this.entries = new HashSet<Party>();
		this.ids = new HashSet<String>();
	}
	
	public Set<Party> getEntries() {
		return entries;
	}
	
	/**
	 * Add a party to this party's entries
	 * 
	 * @param e
	 * @return true if added, false if unsuccessful
	 */
	public boolean addParty(Party e) {
		boolean added = this.ids.add( ( (Party) e).getID() );
		if (added == true) {
			this.entries.add(e);
			return true;
		}
		return false;
	}
	
	/**
	 * Get the number of messages in this group
	 */
	@Override
	public int getNumMessages() {
		int count = 0;
		for (Party p : entries) {
			count += p.getNumMessages();
		}
		return count;
	}
	
	/**
	 * Get the number of positive messages in this group
	 */
	@Override
	public int getNumPositiveMessages() {
		int count = 0;
		for (Party p : entries) {
			count += p.getNumPositiveMessages();
		}
		return count;
	}
	
	
	/**
	 * checks if this group contains some id
	 * @param uid, the desired id to check for in the composite
	 * @return true or false depending on if this ID is in the tree
	 */
	public boolean containsID(String uid) {
		boolean contains = false;
		if (this.getID().equals(uid)) {
			return true;
		}
		for (Party p : entries) {
			if (p.getClass() == Group.class) {
				contains = ((Group) p).containsID(uid);
				if (contains) {
					return true;
				}
			}
			else if (p.getClass() == User.class) {
				contains = ((User) p).getID().equals(uid);
				if (contains) {
					return true;
				}
			}
		}
		return false;
		
	}
	
	public User getUser(String uid) {
		User u = null;
		for (Party p : entries) {
			if (p.getClass() == User.class) {
				//Found the user
				if (p.getID().equals(uid)) {
					u = (User) p;
					return u;
				}
			}
			else if (p.getClass() == Group.class) {
				u = ((Group) p).getUser(uid);
			}
		}
		return u;
	}

	@Override
	public int acceptVisitor(Visitor v) {
		int count = 0;
		for (Party p : entries) {
			count += p.acceptVisitor(v);
		}
		count += v.visit(this);
		return count;
	}

}
