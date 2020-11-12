package main.visitor;

import main.users_and_groups.Group;
import main.users_and_groups.Party;
import main.users_and_groups.User;

/**
 * Visitor that counts the amount of groups, showing the visitor design pattern
 * 
 * @author Nicholas Calkins
 *
 */
public class GroupCountVisitor implements Visitor {

	@Override
	public int visit(Party p) {
		int count = 0;
		
		if (p.getClass() == User.class) {
			count += visit((User) p);
		}
		else if (p.getClass() == Group.class) {
			count += visit( (Group) p);
		}
		
		return count;
	}

	@Override
	public int visit(User u) {
		return 0;
	}

	@Override
	public int visit(Group g) {
		return 1;
	}

}
