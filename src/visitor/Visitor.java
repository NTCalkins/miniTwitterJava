package visitor;

import main.users_and_groups.Group;
import main.users_and_groups.Party;
import main.users_and_groups.User;

/**
 * Interface for implementing the Visitor design pattern
 * 
 * @author Nicholas Calkins
 *
 */
public interface Visitor {
	
	public int visit(Party p);
	
	public int visit(User u);
	
	public int visit(Group g);
}
