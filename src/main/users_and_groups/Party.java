package main.users_and_groups;

import javax.swing.tree.DefaultMutableTreeNode;

import main.visitor.Visitor;

/**
 * abstract class to demonstrate the Composite design pattern
 * 
 * @author Nicholas Calkins
 *
 */
public abstract class Party extends DefaultMutableTreeNode {

	/**
	 * For the serializable derived from DefaultMutableTreeNode
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	
	//Time attribute that can be used by both users and groups
	private long creationTime = 0;
	
	public Party(String id) {
		this.id = id;
		this.creationTime = System.currentTimeMillis();
	}
	
	public String getID() {
		return this.id;
	}
	
	@Override
	public String toString() {
		return id;
	}
	
	public long getCreationTime()
	{
		return this.creationTime;
	}
	
	public void setCreationTime()
	{
		this.creationTime = System.currentTimeMillis();
	}
	
	public abstract int getNumMessages();
	
	public abstract int getNumPositiveMessages();
	
	public abstract int acceptVisitor(Visitor v);
}
