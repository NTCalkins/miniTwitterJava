package visitor;

import main.users_and_groups.Group;
import main.users_and_groups.Party;
import main.users_and_groups.User;

/**
 * Visitor pattern implementation for counting messages
 * 
 * @author Nicholas Calkins
 *
 */
public class MessageCountVisitor implements Visitor {

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
		return u.getNumMessages();
	}

	@Override
	public int visit(Group g) {
		return 0;
	}
	
}
