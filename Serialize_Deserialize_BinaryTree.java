package amazon;

import java.util.*;
//We can do it in 2 ways either level order traversal or Pre Order Traversal

/*
 * Serialization is the process of converting a data structure or object into a sequence of
 *  bits so that it can be stored in a file or memory buffer, or transmitted across a network 
 *  connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on 
how your serialization/deserialization algorithm should work. You just need to ensure that a 
binary tree can be serialized to a string and this string can be deserialized to the original
 tree structure.
 * 
 * input -- [1,2,3,null,null,4,5]
 * stdout -- 1,2,3,#,#,4,5,#,#,#,#
 * output -- [1,2,3,null,null,4,5]
 * 
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 * 
 * 
 * Best Solution is PreOrder
 * 
 * */


class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

public class Serialize_Deserialize_BinaryTree {

	// Encodes a tree to a single string. (InOrder Solution)
	public String serialize(TreeNode root) {

		if(root==null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		while(!queue.isEmpty()){
			TreeNode t = queue.poll();
			if(t!=null){
				sb.append(String.valueOf(t.val) + ",");
				queue.add(t.left);
				queue.add(t.right);
			}else{
				sb.append("#,");
			}
		}
		sb.deleteCharAt(sb.length()-1);
		System.out.println(sb.toString());
		return sb.toString();

	}

	// Decodes your encoded data to tree.(InOrder Solution)
	public TreeNode deserialize(String data) {
		if(data==null || data.length()==0)
			return null;
		String[] arr = data.split(",");
		TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		int i=1;
		while(!queue.isEmpty()){
			TreeNode t = queue.poll();
			if(t==null)
				continue;
			if(!arr[i].equals("#")){
				t.left = new TreeNode(Integer.parseInt(arr[i]));
				queue.offer(t.left);
			}else{
				t.left = null;
				queue.offer(null);
			}
			i++;
			if(!arr[i].equals("#")){
				t.right = new TreeNode(Integer.parseInt(arr[i]));
				queue.offer(t.right);
			}else{
				t.right = null;
				queue.offer(null);
			}
			i++;
		}
		return root;
	}


	// Encodes a tree to a single string.(Preorder)
	public static String serializePreOrder(TreeNode root) {
		if(root==null)
			return null;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		StringBuilder sb = new StringBuilder();
		while(!stack.isEmpty()){
			TreeNode h = stack.pop();
			if(h!=null){
				sb.append(h.val+",");
				stack.push(h.right);
				stack.push(h.left);
			}else{
				sb.append("#,");
			}
		}
		return sb.toString().substring(0, sb.length()-1);
	}

	// Decodes your encoded data to tree. (Preorder solution)
	public TreeNode deserializePreOrder(String data) {
		if(data == null)
			return null;
		int[] t = {0};
		String[] arr = data.split(",");
		return helper(arr, t);
	}
	public TreeNode helper(String[] arr, int[] t){
		if(arr[t[0]].equals("#")){
			return null;
		}
		TreeNode root = new TreeNode(Integer.parseInt(arr[t[0]]));
		t[0]=t[0]+1;
		root.left = helper(arr, t);
		t[0]=t[0]+1;
		root.right = helper(arr, t);
		return root;

	}
	
	
	public static void main (String args[]) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.right.left = new TreeNode(4);
		root.right.right = new TreeNode(5);
	
		System.out.println(serializePreOrder(root));
	}

}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));