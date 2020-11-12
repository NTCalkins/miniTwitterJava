package main.users_and_groups;

public interface Visitor {
	
	public int visitParty(Party p);
	
	public int visitUser(User u);
	
	public int visitGroup(Group g);
}
