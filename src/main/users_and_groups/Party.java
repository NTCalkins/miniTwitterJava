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
	
	public Party(String id) {
		this.id = id;
	}
	
	public String getID() {
		return this.id;
	}
	
	@Override
	public String toString() {
		return id;
	}
	
	public abstract int acceptVisitor(Visitor v);
}
