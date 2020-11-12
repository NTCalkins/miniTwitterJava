package main.users_and_groups;

/**
 * Visitor that counts the amount of groups, showing the visitor design pattern
 * 
 * @author Nicholas Calkins
 *
 */
public class GroupCountVisitor implements Visitor {

	@Override
	public int visitParty(Party p) {
		int count = 0;
		
		if (p.getClass() == User.class) {
			count += visitUser((User) p);
		}
		else if (p.getClass() == Group.class) {
			count += visitGroup( (Group) p);
		}
		
		return count;
	}

	@Override
	public int visitUser(User u) {
		return 0;
	}

	@Override
	public int visitGroup(Group g) {
		
		int count = 1;
		
		for (Party p : g.getEntries()) {
			count += visitParty((Party) p);
		}
		
		return count;
	}

}
