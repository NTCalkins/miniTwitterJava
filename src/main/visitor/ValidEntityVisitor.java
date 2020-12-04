package main.visitor;

import java.util.HashSet;

import main.users_and_groups.Group;
import main.users_and_groups.Party;
import main.users_and_groups.User;

/*
 * This is a visitor for determining if the values inside of the tree are valid.
 * 
 * 
 */

public class ValidEntityVisitor implements Visitor {

	private HashSet<String> ids = new HashSet<String>();
	
	@Override
	public int visit(Party p) {
		if (p.getClass() == User.class)
		{
			return this.visit((User) p);
		}
		return this.visit((Group) p);
	}

	@Override
	public int visit(User u) {
		return (ids.add(u.getID()) && !u.getID().contains(" ")) ? 1 : 0;
	}

	@Override
	public int visit(Group g) {
		//If the group can be successfully added to the HashSet of strings
		//and the id does not contain any spaces, return 1. Otherwise
		//return 0.
		return (ids.add(g.getID()) && !g.getID().contains(" ")) ? 1 : 0;
	}

}
