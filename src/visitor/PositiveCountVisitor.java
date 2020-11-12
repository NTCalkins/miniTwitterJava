package visitor;

import main.users_and_groups.Group;
import main.users_and_groups.Party;
import main.users_and_groups.User;

/**
 * 
 * 
 * @author Nicholas Calkins
 *
 */
public class PositiveCountVisitor implements Visitor {

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
		return u.getNumPositiveMessages();
	}

	@Override
	public int visit(Group g) {
		return 0;
	}

}
