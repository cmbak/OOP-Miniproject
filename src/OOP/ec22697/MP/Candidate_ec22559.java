package OOP.ec22697.MP;// File Candidate_ec22559.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22559 extends Candidate {
    
    public String getName() {
        return "Mayur";
    }
    
    public String getSlogan() {
        return "More trees!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        // If array is empty, return instance of friend's class.
        if (candidates.length == 0) 
            return new Candidate_ec22414();
        
        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("More trees!"))
                return c;
        
        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Adil"))
                return c;
        
        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        // If array is empty, return instance of friend's class.
        if (votes.length == 0) 
            return new Candidate_ec22414();
        
        // Default to first vote, but this will be over-written.
        Candidate currentWinner = votes[0];
        
        // Count the votes for each object in the array,
        // selecting one with the most.
        int highestCount = 0;
        for (Candidate c : votes) {
            
            int count = 0;
            for (Candidate x : votes)
                if (x == c)
                    count++;
            if (count > highestCount) {
                highestCount = count;
                currentWinner = c;
            }
        }
        
        return currentWinner;
    }
    
    //string input handler
    public static String inputString(String message){
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextLine();
    }

    //integer input handler
    public static int inputInt(String message){
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextInt();
    }
    
    //creates a copy of the array size +1 from the original to add a new person
    public static Candidate[] addToArray(Candidate[] allCandArry, Candidate newCandidate){
        Candidate[] newArray = new Candidate[allCandArry.length+1];
        System.arraycopy(allCandArry, 0, newArray, 0, allCandArry.length);
        newArray[newArray.length-1] = newCandidate;
        System.out.println(newCandidate + " added!");
        return newArray;
    }
    
    //find a random candidate from the array of candidates
    public static void addRandomCandidate(Candidate[] allC){
        Random rand = new Random(); 
        int upperbound = allC.length;
        int int_random = rand.nextInt(upperbound); 
        addToArray(allC, allC[int_random]);
    }
    
    public static void main(String[] args) {
        int option = 0;
        Candidate[] allContributions = new Candidate[]{};
        
        System.out.println("= Running Repeated Elections =");
        boolean flag = true;
        
        while (flag) {
            if (allContributions.length == 0) {
                System.out.println("None");
            } else {
                for (int i = 0; i < allContributions.length; i++) {
                    System.out.println((i+1)+". "+allContributions[i].getName());
                }
            }

            option = inputInt("Would you like to 1) add a specific candidate 2) add a candidate at random 3) exit?");

            if (option == 1){
                String username = inputString("Please enter a username.");
                allContributions = addToArray(allContributions, A3.getByUsername(username, A3.getCandidateArray()));
            }

            else if (option == 2) {
                if(allContributions.length == 0){
                    System.out.println("NO CANDIDATES");
                }else{
                    addRandomCandidate(allContributions);
                }
            }

            else if (option == 3) {
                flag = false;
            }

            else {
                System.out.println("Select an option (1,2 or 3)");
            }

        }
    }
    
}
