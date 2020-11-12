package main.users_and_groups;

public class PositiveCountVisitor implements Visitor {

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
		return u.getNumPositiveMessages();
	}

	@Override
	public int visitGroup(Group g) {
		
		int count = 0;
		
		for (Party p : g.getEntries()) {
			count += visitParty((Party) p);
		}
		
		return count;
	}

}
