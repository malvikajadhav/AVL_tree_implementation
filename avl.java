import java.util.*;
public class avl {
    //create root for empty tree
    private Node root;
    List<String> result = new ArrayList<String>();
    //create basic node structure
    private class Node {

		int data;
		Node left;
		Node right;
		int height;
        
        //initialize variables with constructor
		public Node(int data) {
			this.data = data;
			this.height = 1;
		}
	}
    //get height of given node
    private int height(Node N){
        if(N==null) return 0;
        else return N.height;
    }
    
    //calls internal Insert() passing the arguments received from outside the class
	public void Insert(int item) {
		this.root = Insert(this.root, item);
	}

    //function to insert node into AVL tree
	private Node Insert(Node node, int item) {
        //create new node with given key 
        
		if (node == null) {
			Node nn = new Node(item);
			return nn;
		}
        //insert node in right subtree
		if (item > node.data) {
			node.right = Insert(node.right, item);
		}//insert node in left subtree 
        else if (item < node.data) {
			node.left = Insert(node.left, item);
		}
        if (item == node.data) {
            return null;
        }
        //update node height value
		node.height = Math.max(height(node.left), height(node.right)) + 1;

        //calculate balance factor
		int bf = getBalanceFactor(node);

        //balance avl tree 
		return balanced(bf,node,item);

	}

    //function to balance the avl tree after making changes to tree structure
    private Node balanced(int bf, Node node, int d){
        // LL Case
		if (bf > 1 && d < node.left.data) {
			return rightRotate(node);
		}

		// RR Case
		if (bf < -1 && d > node.right.data) {
			return leftRotate(node);
		}

		// LR Case
		if (bf > 1 && d > node.left.data) {
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}

		// RL Case
		if (bf < -1 && d < node.right.data) {
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}
        return node;

    }

    //calcute balance factor of a node
    int getBalanceFactor(Node n){
        if(n==null) return 0;
        else return (height(n.left) - height(n.right));
    }

    //perform right rotation
    private Node rightRotate(Node c){
        Node b = c.left;
        Node t = b.right;

        //rotate
        b.right = c;
        c.left = t;

        //update height
        c.height = Math.max(height(c.left),height(c.right))+1;
        b.height = Math.max(height(b.left),height(b.right))+1;
        return b;
    }

    //perform right rotation
    Node leftRotate(Node c){
        Node b = c.right;
        Node t = b.left;

        //rotate
        b.left = c;
        c.right = t;

        //update height
        c.height =  Math.max(height(c.left),height(c.right))+1;
        b.height =  Math.max(height(b.left),height(b.right))+1;
        return b;
    }

//calls internal Delete() passing the arguments received from outside the class
public void Delete(int item) {
		this.root = Delete(this.root, item);
}

//function to delete specified key from avl tree
private Node Delete(Node node, int item) 
    {   //base case
        if (node == null){
            return null; 
        }
        
        //delete from left subtree
        if (item < node.data) {
            node.left = Delete(node.left, item); 
        }//delete from right subtree
        else if (item > node.data) 
            node.right = Delete(node.right, item); 
        else
        { 
            // Case 1: node with no child 
            if ((node.left == null) && (node.right == null)){
                node = null;
                return node;
            } 
            // Case 2: node with only one child 
            else if ((node.left == null) || (node.right == null)){
                Node t = (root.left == null) ? root.right :  root.left;
                node = t;
            }   
            // Case 3: node with two children 
            else{ 
  
                //replace the node with its inorder successor
                //this is the node with smallest value in current node's right subtree
                Node t = successorNode(node.right); 
  
                //copy the inorder successor's data to current node 
                node.data = t.data; 
  
                //delete the inorder successor 
                node.right = Delete(node.right, t.data); 
            } 

            if (node == null) 
            return node; 
        }  
  
        //update height
        node.height = Math.max(height(node.left), height(node.right)) + 1; 
  
        //calculate balance factor
        int bf = getBalanceFactor(node); 

        // If only one node left (root) then return 
        if (this.root == null)
            {return this.root;}
  
        //balance avl tree after deletion
        return balanced(bf,node,item); 
        } 

    //function to find inorder successor of current node
    private Node successorNode(Node N){
        Node temp = N; 
        while (temp.left != null){
            temp = temp.left; 
        } 
        return temp; 
    }


    //function to search a single node

public String Search(int l, int r) {
		result = Searchtwo(this.root,l,r);
        String output = makeString(result);
        if (result.isEmpty() == true) return "NULL";
        result.clear();
        return output;
    }

public String Search(int item){
        Node n = this.root;
        while (n != null) {
            if (n.data == item) {
                break;
            }
            n = n.data > item ? n.left : n.right;
        }
        if (n==null) return "NULL";

        return Integer.toString(item);
    }

    //function to search values within a given range
public List<String> Searchtwo(Node n, int l, int r) {
    /* base case */
    if ( null == n )
        return result;
    
    if ( l <= n.data ) Searchtwo(n.left, l, r);
     
    if ( l <= n.data && r >= n.data ) {
        result.add(Integer.toString(n.data));
    }     
    if(r >= n.data) Searchtwo(n.right, l, r);

    return result;
}

//helper function to convert array list to a single string
private String makeString(List<String> arr){
    StringBuilder val = new StringBuilder("");
    // Traverse the entire ArrayList
    for (String each_val : arr) {

        //appending each element
        val.append(each_val).append(",");
    }
    // StringBuffer to String conversion
    String new_val = val.toString();

    // Condition check to remove the last comma
    if (new_val.length() > 0)
       new_val = new_val.substring(0,new_val.length() - 1);
    return new_val;
}

public void printInorder() {
        printInorder(root); }
 /* Given a binary tree, print its nodes in inorder*/
    void printInorder(Node node){
     if (node == null)
         return;

     /* first recur on left child */
     printInorder(node.left);

     /* then print the data of node */
     System.out.print(node.data + " ");
     System.out.println(getBalanceFactor(node));

     /* now recur on right child */
     printInorder(node.right);
 }
 
}

