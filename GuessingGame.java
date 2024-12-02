import java.util.ArrayList;
import java.util.LinkedList;
import java.io.*;
import java.util.Scanner;
/**
 * GuessingGame: read a text file from the command line and the program asks user questions and get user's inputs as yes/no
 * unil the answer (leaf of the binary tree) is reached. 
 * @author Marie Nguyen 
 * @version April 28th 2022
 */
public class GuessingGame{
    // Constructor
    public GuessingGame(){ // Intentionally empty 
    }
    
    LinkedList<String> listOfLeaf = new LinkedList<String>();  
    /**
     * Recursive listAnswer method getting a node from a binary tree and add to the list of answers
     * if the node is the leaf 
     * @param node
     * @return a list of answers (leaves in the binary tree)
     */
    public LinkedList<String> listAnswer(BinaryTreeNode<String> node){
        // Base case
        if (node == null){
            return null;
        }
        // Add to the list if the current node is a leaf
        if(node.isLeaf()){
            listOfLeaf.add(node.getData());
        }
        // Recurse the left and right nodes until reaching the base case 
        listAnswer(node.getLeftChild());
        listAnswer(node.getRightChild());  

        return listOfLeaf;
    } 
    /**
     * Recursive playGame method: gradually ask user questions based on user's answers yes or no 
     * until the answer (leaf) is reached. If user's response "yes" then go to the left node;
     * otherwise go to the right node in the binary tree 
     * @param currentNode
     * @return the answer (leaf node)
     */
    public BinaryTreeNode<String> playGame(BinaryTreeNode<String> currentNode){
        Scanner sc = new Scanner(System.in);
        // Based case 
        if (currentNode == null){
            return null;
        } 
        // if the current node is the answer(leaf)
        if(currentNode.isLeaf()){
            System.out.println("Were you thinking of " + currentNode.getData() +"?");
            String userResponse = sc.nextLine();
            if (userResponse.equals("yes")){
                System.out.println("Yass! I guessed it");
            }
            else{
               System.out.println("Awww noo -.- Beats me!");
            }
        }    
        /* otherwise, recurse the method to ask questions based on user's response "yes" or "no".  
        If the user response "yes" then go to the left node, otherwise go to the right node*/     
        else {
            // Print out the question and get user's response 
            System.out.println(currentNode.getData());
            String userResponse = sc.nextLine();
            // If user responses "yes", current node will go to the left for next resursion
            if (userResponse.equals("yes")){
                // Go to left node
                BinaryTreeNode<String> yesNode = currentNode.getLeftChild();
                yesNode = playGame(yesNode);  // Recurse itself 
            }
            else{
                // Otherwise, go to right node
                BinaryTreeNode<String> noNode = currentNode.getRightChild();
                noNode = playGame(noNode);  // Recurse itself
            }
        }
        return currentNode;
    }

    // Main method 
    public static void main (String args[]){
        // // Get the file name from the command line in the configuration
        // File infile = new File(args[0]);
        // // convert the file into the string
        // String filename = infile.toString();
        // Build a tree game
        GameTree treegame = new GameTree("anime.txt");
        GuessingGame game = new GuessingGame();
        BinaryTreeNode<String> root = treegame.getRoot();

        Scanner sc = new Scanner(System.in);
        System.out.println("Xin chao(Vietnamese)! Do you want to play 20 questions? [yes/no] ");
        String userWannaPlay = sc.nextLine();

        // List of answers (leaves of binary tree)
        LinkedList<String> listOfAnwers = game.listAnswer(root);
        
        // Iterate the binary tree until user dont wanna play any more
        while (userWannaPlay.equals("yes")){

            System.out.println("Alright! You will think of an answer and I will try to guess it!");
            System.out.println("All you have to do is answer 'yes' or 'no' !");
            System.out.println("Now think of an international airport from this set.");
            
            System.out.println(listOfAnwers.toString());  

            System.out.println("Are you ready to play? [yes/no]");
            String begin = sc.nextLine();

            // Invoke playGame method for going through the binary tree game
            if (begin.equals("yes")){
                game.playGame(root);     
            }
            else{
                break;
            }
            System.out.println("Would you like to play again? [yes/no]");  
            userWannaPlay = sc.nextLine();
        }
    }
}