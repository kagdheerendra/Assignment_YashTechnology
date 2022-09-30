package com.yash.assignment.collections;

import java.util.HashSet;

class Node{
	int data;
	Node left, right;
	public Node(int data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}
}
public class BinarySearchTreeExampleTask10 {
    Node root;
    
    BinarySearchTreeExampleTask10(){
    	root = null;
    }
    
    public static Node insert(Node root, int data)
    {
        if (root == null)
            return new Node(data);
        if (data < root.data)
            root.left = insert(root.left, data);
        if (data > root.data)
            root.right = insert(root.right, data);
        
        return root;
    }
    
    void printInOrder(Node node) {
    	if(node == null) {
    		return;
    	}
    	
    	printInOrder(node.left);
    	System.out.println(node.data);
    	printInOrder(node.right);
    }
    
	public static void main(String[] args) {
        BinarySearchTreeExampleTask10 b = new BinarySearchTreeExampleTask10();
        //int arr[] = { 1, 2, 3, 2, 5, 4, 4 };
        int arr[] = { 10, 2, 13, 2, 15, 4, 4, 13 };
        int n = arr.length;
        for (int i = 0; i < n; i++)
        {
            b.root = insert(b.root,arr[i]);
        }
        b.printInOrder(b.root);
    }

}
