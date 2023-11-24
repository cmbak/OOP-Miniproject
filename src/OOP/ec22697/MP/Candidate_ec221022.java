package OOP.ec22697.MP;// File Candidate_ec221022.java
//
// Machine generated stub for Assignment 3

import java.util.Random;
import java.util.Scanner;

class Candidate_ec221022 extends Candidate {

    public static void main(String[] args){

        Candidate[] c_all = A3.getCandidateArray();
        Candidate[] Candidates = new Candidate[c_all.length];
        String option = input("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
        boolean BREAK = false;
        int total_Candidates =0;

        while(!(BREAK)){
            System.out.println("= Running Repeated Election =");

            if(total_Candidates == 0){
                System.out.println("Candidates are ");
                System.out.println("none");
            }
            else{
                System.out.println("Candidates are ");
                for(int i=0; i< total_Candidates; i++){
                   System.out.println((i+1)+ ". "+ Candidates[i].getName());
                }
            }


            if(option.equals("a")){
                String username = input("Please enter a username.");
                add_Username(c_all, Candidates, username, total_Candidates);
                total_Candidates++;
                System.out.println("Candidates are ");
                for(int i=0; i< total_Candidates; i++){
                    System.out.println((i+1)+ ". "+ Candidates[i].getName());
                }
            }

            if(option.equals("b")){
                addrando(c_all, Candidates, total_Candidates);

            }
            if(option.equals("c")){
                run_election(c_all, Candidates, total_Candidates, BREAK);
            }
            if(option.equals("d")){
                BREAK = true;
            }
            else{
                System.out.println("please enter a valid option.");
            }

        }
    }
    public static void add_Username(Candidate[] c_all, Candidate[] Candidates, String username, int total_Candidates){
        Candidate Add_Candidate = A3.getByUsername(username, c_all);
        if(Add_Candidate == null){
            System.out.println("Invalid username");
        }
        else{
            Candidates[total_Candidates] = Add_Candidate;
        }
    } 

    public static void addrando(Candidate[] c_all, Candidate[] Candidates, int total_Candidates){

        Random rando = new Random();
    	int index = rando.nextInt(c_all.length);
    	Candidates[total_Candidates] = c_all[index];
    }

    public static void run_election(Candidate[] c_all, Candidate[] Candidates, int total_Candidates, boolean BREAK){
        String count = input("Who will count the votes?");
        int runs = input_int("How many times shall we run the election");
         for (int i = 0;i < c_all.length;i++){
    	  	if ((c_all[i]).equals(count)){ // making the person chosen count the votes 
    	            
    	            	Candidate[] votes = new Candidate[runs];
    	            	for (int j = 0; j<runs; j++){
    	            	
    	            	    votes[j] = Candidates[j];
    	            	}
    	                Candidate winner = c_all[i].selectWinner(votes); // counts votes, finds winner
    	                System.out.println("The most common winner is "+ winner.getName());
                        BREAK = true;
    	            }
    	   }


    }

    public static String input(String message) {

        System.out.println(message);
        String INPUT;
        Scanner scanner = new Scanner(System.in);
        final String ERROR = "please enter text";
        while (!scanner.hasNextLine()){
            System.out.println(ERROR);
            System.out.println(message);
            scanner.next();
        }
        return INPUT = scanner.nextLine();
    }
    public static int input_int(String message) {
        System.out.println(message);
        int INPUT;
        Scanner scanner = new Scanner(System.in);
        final String ERROR = "please enter an integer";
        while (!scanner.hasNextInt()){
            System.out.println(ERROR);
            System.out.println(message);
            scanner.next();
        }
        return INPUT = scanner.nextInt();

    }


    public String getName() {
        return "John";
    }

    
    public String getSlogan() {
        return "Cheaper transport";
    }

    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec221022();

        
        if(candidates.length == 0){
            return new Candidate_ec221022();
        }

        
        for (Candidate c : candidates){
          if(c.getSlogan().equals("cheaper transport")){
              return c;
          }

            if(c.getSlogan().equals("cheaper transport")){
                return c;
            }
        }
        for (Candidate c : candidates){
          if(c.getName().equals("Alex")){
              return c;
          }
             if(c.getName().equals("Alex")){  
                return c;
             }
        }


        
        return candidates[candidates.length-1];


    }

    
    public Candidate selectWinner(Candidate[] votes) {

        
        // If array is empty, return instance of friend's class.
        if (votes.length == 0){
            return new Candidate_ec221023();
        }

        
        // Default to first vote, but this will be over-written.
        Candidate currentWinner = votes[0];

        
        // Count the votes for each object in the array,
        // selecting one with the most.
        int highestCount = 0;
        for (Candidate c : votes){

            
            int count = 0;
            for (Candidate x : votes){

                if (x == c){

                    count++;
                }

            }
            if (count > highestCount){
            if (count > highestCount) {
                highestCount = count;
                currentWinner = c;
            }

        }
    }

    
        return currentWinner;
    
 }
}
