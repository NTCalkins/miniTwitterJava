package main.gui;

import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import main.users_and_groups.Group;
import main.users_and_groups.User;
import main.users_and_groups.Party;


/**
 * JPanel for housing the tree of users and groups
 * 
 * @author Nicholas Calkins
 *
 */
public class TreePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTree tree;
	private DefaultTreeModel treeModel;
	private DefaultMutableTreeNode rootNode;
	
	public TreePanel(DefaultMutableTreeNode rootNode) {
		this.rootNode = rootNode;
		
		//Must ascertain if anything going into this tree can have children
		this.treeModel = new DefaultTreeModel(this.rootNode, true);
		
		this.tree = new JTree(this.treeModel);
		
        tree.getSelectionModel().setSelectionMode (TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setShowsRootHandles(true);
        tree.setSelectionRow(0);
        
        //Add tree to the panel
        this.add(tree);
	}
	
	public void addUserOrGroup(Party node) {
		DefaultMutableTreeNode parentNode = null;
		TreePath parentPath = tree.getSelectionPath();
		
		//If there's no parent, the root is the group
		if (parentPath == null) {
			parentNode = this.rootNode;
		}
		//If there is a party highlighted, we'll add it there
		else {
			parentNode = (DefaultMutableTreeNode) parentPath.getLastPathComponent();
		}
		
		//If the addition is a user, then don't allow it to have children
		if (node.getClass() == User.class) {
			node.setAllowsChildren(false);
		}
		
		//If this parent is actually a user, use the parent of the user
		if (parentNode.getClass() != Group.class) {
			parentNode = (DefaultMutableTreeNode) parentNode.getParent();
			
			//Insert the user into the tree and put it under the group
			treeModel.insertNodeInto(node, parentNode, parentNode.getChildCount());
			((Group) parentNode).addParty( (Party) node);
		}
		
		//Otherwise the parent is a group, add the node
		else {
			treeModel.insertNodeInto(node, parentNode, parentNode.getChildCount());
			((Group) parentNode).addParty( (Party) node);
		}
		
		//Refresh the tree
		treeModel.reload(rootNode);

		//Expand the tree to show all nodes
		for (int i = 0; i < tree.getRowCount(); i++) {
			tree.expandRow(i);
		}
	}
	
	public boolean containsID(String id) {
		return ((Group) rootNode).containsID(id);
	}
	
	public User getUser(String id) {
		return ((Group) rootNode).getUser(id);
	}
	
	public JTree getTree() {
		return this.tree;
	}
}
