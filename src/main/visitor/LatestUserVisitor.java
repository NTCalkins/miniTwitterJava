package main.visitor;

import main.users_and_groups.Group;
import main.users_and_groups.Party;
import main.users_and_groups.User;

public class LatestUserVisitor implements Visitor {

	private long latestTime = 0;
	private String latestId = "None";
	
	@Override
	public int visit(Party p) {
		if (p.getClass() == User.class)
		{
			this.visit((User) p); 
		}
		return 0;
	}

	@Override
	public int visit(User u) {
		if (u.getLastUpdatedTime() > latestTime)
		{
			latestTime = u.getLastUpdatedTime();
			latestId = u.getID();
		}
		return 0;
	}

	@Override
	public int visit(Group g) {
		return 0;
	}
	
	public String getLatestUser()
	{
		return latestId;
	}

}
