/**
 * BinaryTree is the interface for a basic binary tree.
 */
public interface BinaryTree<T> {

	/**
	 * Get the root node for this tree.
	 *
	 * @return root or null if tree is empty.
	 */
	public BinaryTreeNode<T> getRoot();

	/**
	 * Set the root node for this tree.
	 */
	public void setRoot(BinaryTreeNode<T> root);

	/**
	 * Test if the tree is empty.
	 *
	 * @return true if the tree has no data.
	 */
	public boolean isEmpty();

}
