package amazon;
/*
 * 
 * You are given a Double Link List with one pointer of each node pointing to the next node just like in a single
 *  link list. The second pointer however CAN point to any node in the list and not just the previous 
 *  node. Now write a program in O(n) time to duplicate this list. That is, write a program which will
 *   create a copy of this list.

Let us call the second pointer as arbitrary pointer as it can point to any arbitrary node in the linked list
 * 
 * Clone a linked list with next and random pointer | 
 * 
 * The idea is to use Hashing. Below is algorithm.
1. Traverse the original linked list and make a copy in terms of data.
2. Make a hash map of key value pair with original linked list node and copied linked list node.
3. Traverse the original linked list again and using the hash map adjust the next and random reference of 
cloned linked list nodes.
 * 
 * 
 * */

import java.util.HashMap; 
import java.util.Map; 

// Linked List Node class 
class ALNode 
{ 
	int data;//Node data 
	ALNode next, arbitrary;

	//Node constructor 
	public ALNode (int data) 
	{ 
		this.data = data; 
		this.next = this.arbitrary = null; 
	} 
} 


class LinkedList 
{ 
	ALNode head;//Linked list head reference 

	// Linked list constructor 
	public LinkedList(ALNode head) 
	{ 
		this.head = head; 
	} 

	// push method to put data always at the head 
	// in the linked list. 
	public void push(int data) 
	{ 
		ALNode node = new ALNode(data); 
		node.next = this.head; 
		this.head = node; 
	} 

}


// Driver Class 
class DeepCopy 
{ 

	public static ALNode deepCopy(ALNode head) {
		ALNode origCurr = head, cloneCurr = null; 
		Map<ALNode, ALNode> map = new HashMap<ALNode, ALNode>(); 
		while (origCurr != null) 
		{ 
			cloneCurr = new ALNode(origCurr.data); 
			map.put(origCurr, cloneCurr); 
			origCurr = origCurr.next; 
		} 
		origCurr = head; 

		while (origCurr != null) 
		{ 
			cloneCurr = map.get(origCurr); 
			cloneCurr.next = map.get(origCurr.next); 
			cloneCurr.arbitrary = map.get(origCurr.arbitrary); 
			origCurr = origCurr.next; 
		} 

		//return the head reference of the clone list. 
		return (map.get(head)); 
	} 

	 public static void print(ALNode head) 
	    { 
		 	ALNode temp = head; 
	        while (temp != null) 
	        { 
	        	ALNode random = temp.arbitrary; 
	            int randomData = (random != null)? random.data: -1; 
	            System.out.println("Data = " + temp.data + 
	                               ", Random data = "+ randomData); 
	            temp = temp.next; 
	        } 
	    } 


	public static void main(String[] args) 
	{ 
		// Pushing data in the linked list. 
		LinkedList list = new LinkedList(new ALNode(5)); 
		list.push(4); 
		list.push(3); 
		list.push(2); 
		list.push(1); 

		// Setting up random references. 
		list.head.arbitrary = list.head.next.next; 
		list.head.next.arbitrary = 
				list.head.next.next.next; 
		list.head.next.next.arbitrary = 
				list.head.next.next.next.next; 
		list.head.next.next.next.arbitrary = 
				list.head.next.next.next.next.next; 
		list.head.next.next.next.next.arbitrary = 
				list.head.next; 

		ALNode al = deepCopy(list.head);
		print(al);

	} 
}