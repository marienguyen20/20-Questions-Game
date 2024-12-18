/**
 * GameTreeNode.java implements a basic binary tree node,
 * with data and pointers to left and right children.
 */

public class GameTreeNode implements BinaryTreeNode<String> {
    public String data;
    public BinaryTreeNode<String> leftChild;
    public BinaryTreeNode<String> rightChild;

    /**
     * Constructor creates a node with data.
     */
    public GameTreeNode(String data) {
        this.data = data;
    }

    /**
     * Get the data stored at this node.
     * @return Object data.
     */
    public String getData() {
        return data;
    }

    /**
     * Set the data stored at this node.
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Get the left child.
     * @return BinaryTreeNode that is left child,
     * or null if no child.
     */
    public BinaryTreeNode<String> getLeftChild() {
        return leftChild;
    }

    /**
     * Get the right child.
     * @return BinaryTreeNode that is right child,
     * or null if no child.
     */
    public BinaryTreeNode<String> getRightChild() {
        return rightChild;
    }

    /**
     * Set the left child.
     */
    public void setLeftChild(BinaryTreeNode<String> left) {
        leftChild = left;
    }

    /**
     * Set the right child.
     */
    public void setRightChild(BinaryTreeNode<String> right) {
        rightChild = right;
    }

    /**
     * Tests if this node is a leaf (has no children).
     * @return true if leaf node.
     */
    public boolean isLeaf() {
        return (leftChild == null) && (rightChild == null);
    }
}
