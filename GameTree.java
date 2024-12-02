import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * GameTree.java implements the binary tree interface.
 *
 * @author Tayloe
 */

public class GameTree implements BinaryTree<String> {

	private BinaryTreeNode<String> root;
	//List to hold file values; needs to be global due to recursion
	private LinkedList<String> fileValues;

	/**
	 * Constructor creates an empty Game tree.
	 */
	public GameTree() {
		root = null;
	}

	/**
	 * Constructor creates a game tree based on the file passed in
	 * @param filename the name of the .txt file containg the tree
	 */
	public GameTree(String filename) {
		root = buildTree(filename);
	}

	/**
	 * Get the root node for this tree.
	 *
	 * @return root or null if tree is empty.
	 */
	public BinaryTreeNode<String> getRoot() {
		return root;
	}

	/**
	 * Set the root node for this tree.
	 */
	public void setRoot(BinaryTreeNode<String> root) {
		this.root = root;
	}

	/**
	 * Test if the tree is empty.
	 *
	 * @return true if tree has no data.
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Builds a tree based on the passed-in filename
	 * @param filename name of the .txt file that holds tree data
	 */
	public BinaryTreeNode<String> buildTree(String filename){
		//put all lines of file into a list
		fileValues = new LinkedList<String>();
		try {
			
			File infile = new File(filename);
			Scanner dataIn = new Scanner(infile);
			
			//If there is next line, continuing running this loop
			while (dataIn.hasNext()) {
				//Add the new value, trimming the new lines at the end
				fileValues.addLast(dataIn.nextLine().stripTrailing());
			}
			dataIn.close();

			//Recurse on new list
			return parseListRecursively();

		} catch (FileNotFoundException e) {
			//If file isn't found
			System.out.println(filename + " not found");
			e.printStackTrace();
			return null;
		}
	}

	public BinaryTreeNode<String> parseListRecursively(){
		//start with the first line
		String line = fileValues.pop();
		//count how many tabs there are
		int tabCount = countTabs(line);

		GameTreeNode node = new GameTreeNode(line.substring(tabCount));

		//if we've got enough left for two children
		if (fileValues.size() > 1){
			line = fileValues.peek();
			if (countTabs(line) > tabCount){
				node.setLeftChild(parseListRecursively());
				node.setRightChild(parseListRecursively());
			}
		}
		return node;
	}

	/**
	 * Count how many tabs are in a string
	 * @param s string with tabs
	 * @return how many tabs are in the string
	 */
	public int countTabs(String s){

		char tab = '\t';
		int count = 0;
		
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == tab) {
				count++;
			}
		}

		return count;
	}
	/**
	 * Returns a string containing an in-order traversal of the tree
	 * @return a string representing the tree
	 */
	public String toString(){
		return inOrderTraversal(root);
	}

	public String inOrderTraversal(BinaryTreeNode<String> node)
    {
        String res = "";
        if (!node.isLeaf()){
            res = res + inOrderTraversal(node.getLeftChild());
        }

        res = res + node.getData()+"\n";

        if (!node.isLeaf()){
            res = res + inOrderTraversal(node.getRightChild());
        }

        return res;
    }
}
