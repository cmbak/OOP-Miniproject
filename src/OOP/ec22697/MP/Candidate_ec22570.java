package OOP.ec22697.MP;// File Candidate_ec22570.java
//
// Machine generated stub for Assignment 2

import java.util.Scanner;
//import java.util.Random;

class Candidate_ec22570 extends Candidate {
    
    public String getName() {
        return "Louis (ec22570)";
    }
    
    public String getSlogan() {
        return "Ok (ec22570)";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22570();
        
        if (candidates.length != 0) r = candidates[0];
 
        // Add code that inspects the array candidates
        // calling the various methods of the Candidate objects
        // and finds a Candidate object to vote for.
        
        return r;
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22570();
        
        if (votes.length != 0) r = votes[0];
 
        // Add code that either counts the votes
        // or uses some other way to return an element 
        // of the array vote as the winner of an election.
        
        return r;
    }
  
    public static void main(String[] a)
    {
        //Random random = new Random();
        char option;

        System.out.println("= Running Repeated Elections =");
        System.out.println("Candidates are");
        //if(votes == 0) {
        //    System.out.println("none");
        //}
        //else {
        //    System.out.println(votes);
        //}

        option = decide();
        while(option != 'c') {
           option = decide();
        }  

        System.out.println("Would you like to");
        System.out.println("a) exit");
        System.out.println("b) run same election many times");
        System.out.println("c) check who counts honestly");
        System.out.println("d) run all possible two-candidate elections");
        System.out.println("e) run a higher order election");
        System.out.println("f) ...");
        
        return;
    }

    public static char decide() {
        Scanner scanner = new Scanner(System.in);
        char OPTION;
        String username;
        int elections;

        System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election");
        OPTION = scanner.nextLine().charAt(0);
        if(OPTION == 'a') {
            System.out.println("Please enter a username: ");
            username = scanner.nextLine();
            // get getName from the username's class and add it to the election
        }
        else if(OPTION == 'b') {
            System.out.println("Generating random username...");
            //System.out.println(candidates[random.nextInt(candidates.length)]);
            // get getName from the random username's class and add it to the election
        }
        else if(OPTION == 'c') {
            System.out.println("Who should run the election?");
            username = scanner.nextLine();
            System.out.println("How many times shall we run the election?");
            elections = Integer.parseInt(scanner.nextLine());
            for(int i=0; i<elections; i++) {
                // run username's selectWinner method elections amount of times
                // a way to keep track of the most common winner
            }
            // System.out.println(##Most common winner##)
        }
        return OPTION;
    } 
}

// hello
