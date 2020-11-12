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
