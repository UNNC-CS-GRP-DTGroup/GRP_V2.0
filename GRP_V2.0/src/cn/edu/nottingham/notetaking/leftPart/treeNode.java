/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.edu.nottingham.notetaking.leftPart;

import javax.swing.tree.DefaultMutableTreeNode;

/**This is the class for catalogue tree node
 *
 * @author RUNFA LV
 */
public class treeNode {
    private DefaultMutableTreeNode n;

    public treeNode(String name) {

	n = new DefaultMutableTreeNode(name);
    }

    public DefaultMutableTreeNode getNode() {
	return n;
    }
}
