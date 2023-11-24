package OOP.ec22697.MP;// File Candidate_ec21645.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;


class Candidate_ec21645 extends Candidate {
    
    public String getName() {
        return "JimmyBillBob";
    }
    
	// The name of my Candidate
    public String getSlogan() {
        return "More Cookies";
    }
    
    public Candidate vote(Candidate[] candidates) {
        //Candidate r = new Candidate_ec21645();
        
        //if (candidates.length != 0)
       // {
       //     r = candidates[0];
      //  }
        
        if (candidates.length == 0)
        {
            return new Candidate_ec21645();
        }
        
        Random random = new Random();
        int Num = random.nextInt(candidates.length);
        return candidates[Num];
 
        // Add code that inspects the array candidates
        // calling the various methods of the Candidate objects
        // and finds a Candidate object to vote for.
        
       // return r;
    }
    
    public Candidate selectWinner(Candidate[] votes) {
      //  Candidate r = new Candidate_ec21645();
        
      //  if (votes.length != 0) r = votes[0];
        
 
        // Add code that either counts the votes
        // or uses some other way to return an element 
        // of the array vote as the winner of an election.
        if (votes.length == 0)
        {
            return new Candidate_ec21645();
        }
        
        
         Random Random = new Random();

        int Num2 = Random.nextInt(votes.length);



        return votes[Num2];
        
        
        
       // return r;
    }
	
	 public static void main(String[] args) 
{
        Candidate[] all = A3.getCandidateArray();
        Scanner scanner = new Scanner(System.in);
        Candidate[] ca = new Candidate[546];
        System.out.println("= Running Repeated Elections =");
        System.out.println("Candidates are");
        showCandidates(ca);
        System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");

        char ch = scanner.nextLine().charAt(0); // hack
        int index = 0;
       // switch (ch) {
         //   case 'a':

        if (ch == 'a')
        {
                System.out.println("Please enter a username.");
                String username = scanner.nextLine();
                ca[index] = getCandidate(username, all);
                showCandidates(ca);
                //break;
        }
        
        else if (ch == 'b')
        {
            Random random = new Random();

            int random_candidate = random.nextInt(546);

            System.out.println(all[random_candidate]);
            

        }

        else if (ch == 'c')
        {
            System.out.println("Who should count the votes?");
            String Username = scanner.nextLine();
            Candidate user = getCandidate(Username, all);
            System.out.println("How many times shall we run the election?");
            int time_run = Integer.parseInt(scanner.nextLine());

            FindWinner(all ,user, time_run);

        }
      //  switch (ch) {
       //     case 'a':
        
         //   default:
         //       break;

       // }

       return;
    }
    public static Candidate getCandidate(String username, Candidate[] all){
        
        for(Candidate eachCandidate : all){
            if (eachCandidate.un.equals(username)){
                return eachCandidate;
            }
        }
        return null;
    }


    public static void showCandidates(Candidate[] ca) {
        int index = 1;
        for (Candidate candidate : ca) {
            if (candidate != null && candidate.un != null){
                System.out.println(index + ". "+candidate.getName());
                index++;
            }
        }
        if (index == 1){
            System.out.println("None");
        }
    }


    public static void FindWinner(Candidate[] all , Candidate username, int run_times) {

        //String[] winner = new String[run_times];
        

        //username.
        //int count = 0;
        for (int i = 0; i < run_times; i++) {
        
            username.selectWinner(all);
            
        }

        System.out.println("The most common answer is " + username.selectWinner(all));
        System.out.println("There were no other winners");

        

        return;
    }

    
}

