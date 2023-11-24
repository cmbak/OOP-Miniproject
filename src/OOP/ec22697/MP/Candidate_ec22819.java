package OOP.ec22697.MP;// File Candidate_ec22819.java
//
// Machine generated stub for Assignment 2

import java.util.Scanner;

class Candidate_ec22819 extends Candidate {

    public static void  main (String[] a) {
	A3 a3 = new A3();
        Candidate[] all = a3.getCandidateArray();
        Candidate[] x = new Candidate[0];
        
        String choice;
        System.out.println("Candidates are ");
        for(int i = 0; i < x.length; i++){
            System.out.println(x[i]);
        }

        choice = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election? d) exit ");
        while (!choice.equals("d")) {
            if(choice.equals("a")) {
                String username = inputString("Please enter a username");
                Candidate c = a3.getByUsername(username, all);

                System.out.println("Candidates are " + c );

                for(int i = 0; i < x.length; i++){
                    System.out.println(x[i]);
                }
            }

            if(choice.equals("b")) {
            }

            if(choice.equals("c")) {
            }

            else{
                System.exit(0);
            }
        }
	return;        
    }

    public static String inputString(String message) {
        Scanner scanner = new Scanner(System.in);
        String s;
        System.out.println(message);
        s = scanner.nextLine();
        return s;
    }
    public String getName() {
        return "Usman Adam";
    }
    
    public String getSlogan() {
        return "No Exams!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22819();
        
        if (candidates.length != 0) r = candidates[0];
 
        // Add code that inspects the array candidates
        // calling the various methods of the Candidate objects
        // and finds a Candidate object to vote for.
        if (candidates.length == 0) 
                return new Candidate_ec22819();
        
        return r;
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22819();
          
        // Add code that either counts the votes
        // or uses some other way to return an element 
        // of the array vote as the winner of an election.
        if (votes.length == 0) 
            return new Candidate_ec22819();
        
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
            if (count >= highestCount) {
                highestCount = count;
                currentWinner = c;
            }
        }
        
        return currentWinner;
    }
    
}
