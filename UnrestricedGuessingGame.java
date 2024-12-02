import java.util.ArrayList;
import java.util.LinkedList;
import java.io.*;
import java.util.Scanner;
/**
 * UnrestricedGuessingGame inherit from GuessingGame.java but get new answer and question from user
 * if the answer is not in the original list of answers. 
 * @author Marie Nguyen 
 * @version April 28th 2022
 */
public class UnrestricedGuessingGame extends GuessingGame{
    // Constructor 
    public UnrestricedGuessingGame(){
        super();
    }
    /**
     * Recurse playGame method: gradually ask user questions based on user's answers yes or no 
     * until the answer (leaf) is reached. If user's response "yes" then go to the left node;
     * otherwise go to the right node in the binary tree. Allow user to add more question and answer 
     * when the program cant guess 
     * @param currentNode
     * @param listOfAnwers list of answers with new answer added by user
     * @return
     */
    public BinaryTreeNode<String> playGame(BinaryTreeNode<String> currentNode, LinkedList<String> listOfAnwers){
        Scanner sc = new Scanner(System.in);
        // Base case:
        if (currentNode == null){
            return null;
        } 
        // If the current node is the answer 
        if(currentNode.isLeaf()){
            System.out.println("Were you thinking of " + currentNode.getData() +"?");
            String userResponse = sc.nextLine();
            // Guessed the answer
            if (userResponse.equals("yes")){
                System.out.println("Yass! I guessed it! ");
            }
            // otherwise, add new infor from the user to update the binary tree
            else{
                System.out.println("What were you thinking of?");
                String userNewAnswer = sc.nextLine();
                // Add new answer(leaf) to the listOfAnswer
                listOfAnwers.add(userNewAnswer);
    
                System.out.println("Please give me a yes/no question that would have determined your thing");
                String userQuestion = sc.nextLine(); 

                System.out.println("Is the answer to your question yes or no?");
                String userChoice = sc.nextLine();
                // If the user chose "yes"
                if(userChoice.equals("yes")){
                    // Store the data of the current node 
                    String oldAnswer = currentNode.getData();
                    // Set data of current node to new question from user 
                    currentNode.setData(userQuestion);
                    // Generate 2 new nodes extending the binary game tree  
                    BinaryTreeNode<String>  yesNode = new GameTreeNode(userNewAnswer);
                    BinaryTreeNode<String>  noNode = new GameTreeNode(oldAnswer);
                    currentNode.setLeftChild(yesNode);    
                    currentNode.setRightChild(noNode);
                }
                // If the user chose to "no"
                else{
                    // Store the data of the current node 
                    String oldAnswer = currentNode.getData();
                    // Set data in the current node to new question from user 
                    currentNode.setData(userQuestion);
                    // Generate 2 new nodes extending the binary game tree
                    BinaryTreeNode<String>  yesNode = new GameTreeNode(oldAnswer);
                    BinaryTreeNode<String>  noNode = new GameTreeNode(userNewAnswer);
                    currentNode.setLeftChild(yesNode);
                    currentNode.setRightChild(noNode);
                }
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
                BinaryTreeNode<String> yesNode = currentNode.getLeftChild();
                yesNode = playGame(yesNode, listOfAnwers); // Recurse itself
            }
            // Otherwise, go to right node
            else{
                BinaryTreeNode<String> noNode = currentNode.getRightChild();
                noNode = playGame(noNode, listOfAnwers);  // Recurse itself
            }
        }
        return currentNode;
    }
    // Main method 
    public static void main(String[] args){
        // Get the file name from the command line in the configuration
        File infile = new File(args[0]);
        // convert the file into the string
        String filename = infile.toString();
        // Build a tree game
        GameTree treegame = new GameTree(filename);
        UnrestricedGuessingGame unrestrictedGame = new UnrestricedGuessingGame();
        BinaryTreeNode<String> root = treegame.getRoot();

        Scanner sc = new Scanner(System.in);
        System.out.println("Helloo! Do you want to play 20 questions? [yes/no] ");
        String userWannaPlay = sc.nextLine();

        // List of answers (leaves of binary tree)
        LinkedList<String> listOfAnwers = unrestrictedGame.listAnswer(root);
        // Game starts 
        while (userWannaPlay.equals("yes")){

            System.out.println("Alright! You will think of an answer and I will try to guess it!");
            System.out.println("All you have to do is answer 'yes' or 'no' !");
            System.out.println("Now think of an item from this set.");
            
            System.out.println(listOfAnwers.toString());  

            System.out.println("Are you ready to begin? [yes/no]");
            String begin = sc.nextLine();

            // Invoke playGame method for guessing game
            if (begin.equals("yes")){
                unrestrictedGame.playGame(root, listOfAnwers);     
            }
            else{
                break;
            }
            System.out.println("Would you like to play again? [yes/no]");  
            userWannaPlay = sc.nextLine();
        }
    }
}  