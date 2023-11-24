package OOP.ec22697.MP;// File Candidate_ec22776.java
//
// Machine generated stub for Assignment 3

import java.util.Scanner;
import java.util.Random;

class Candidate_ec22776 extends Candidate {
       //method used to print messages 
     public static void print(String message)
     {
         System.out.println(message);
         return;
     }

     //method used to take strings as input
     public static String inputString()
     {
         Scanner scanner = new Scanner(System.in);
         return scanner.nextLine();
     }

     //method used to take intger as inputs
     public static int inputInteger()
     {
         Scanner scanner = new Scanner(System.in);
         return Integer.parseInt(scanner.nextLine());
     }

     //main method that runs whole program
     public static void main(String[] args)
     {
         menuMethod();
         return;
     }

     //method containing the menu the user can use to make decisions
     public static void menuMethod()
     {
         //gets the entire list of possible candidates
         Candidate[] totalCandidates =  A3.getCandidateArray();

         //creates blank array
         Candidate[] currentCandidates = new Candidate[totalCandidates.length];

         //count
         int candidateCount = 0;

         print("= Running Repeated Elections =");
         printCandidates(candidateCount, currentCandidates);

         Boolean quit = false;
         //repeats until the user quits
         while (!quit)
         {
             print("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
             String answer = inputString().toLowerCase();

             //add chosen username
             if (answer.equals("a"))
             {
                 currentCandidates = addSpecificCandidate(totalCandidates, currentCandidates, candidateCount);
                 if (candidateCount<currentCandidates.length-1)
                 {    
                     candidateCount++;
                 }
                 printCandidates(candidateCount, currentCandidates);

             }
             //add random username
             else if (answer.equals("b"))
             {
                 currentCandidates = addRandomCandidate(totalCandidates, currentCandidates, candidateCount);
                 if (candidateCount<currentCandidates.length-1)
                 {    
                     candidateCount++;
                 }
                 printCandidates(candidateCount, currentCandidates);
             }
             //run election
             else if (answer.equals("c"))
             {
                 runElection(totalCandidates, currentCandidates, candidateCount);
             }
             //stop menu 
             else if (answer.equals("d"))
             {
                 quit = true;
             }
             //invalid choice made
             else
             {
                 print("That is not an option.");
             }
         }        
     return;  

     }

     //method that allows the user to add a candidate to the array by picking a username
     public static Candidate[] addSpecificCandidate(Candidate[] totalCandidates, Candidate[]  currentCandidates, int candidateCount)
     {
         print("Please enter a username:");
         String username = inputString();
         Candidate newCandidate = A3.getByUsername(username, totalCandidates);

         if (checkDuplicates(newCandidate, currentCandidates, candidateCount) == true)
         {
             currentCandidates[candidateCount]=newCandidate;
         }

         return currentCandidates;
     } 

     //method that allows the user to add a candidate to the array by picking a random username
     public static Candidate[] addRandomCandidate(Candidate[] totalCandidates, Candidate[]  currentCandidates, int candidateCount)
     {
         Random random = new Random();
         int randomInt = random.nextInt(totalCandidates.length);
         Candidate newCandidate = totalCandidates[randomInt];

         if (checkDuplicates(newCandidate, currentCandidates, candidateCount) == true)
         {
             currentCandidates[candidateCount]=newCandidate;
         }


         return currentCandidates;
     }

     //outputs the amount of current candidates and their names
     public static void printCandidates(int candidateCount, Candidate[] currentCandidates)
     {
         print("Candidates are:");

         if (candidateCount == 0)
         {
             print("None.");
         }
         else
         {
             for(int i=0;i<currentCandidates.length;i++)
             {
                 print(i+1 +". "+currentCandidates[i]);
             }
         }
         return;
     }


     //checks there are no duplicate candidates
     public static boolean checkDuplicates(Candidate newCandidate, Candidate[] currentCandidates, int candidateCount)
     {
         for (int i=0;i<candidateCount;i++)
         {
             if (currentCandidates[i]==newCandidate)
             {
                 return false;
             }
         }
         return true;    
     }

     //runs the election
     public static void runElection(Candidate[] totalCandidates, Candidate[] currentCandidates, int candidateCount)
     {
         print("Who should count the vote?");
         String username = inputString();
         Candidate counter = A3.getByUsername(username, totalCandidates);

         print("How many times shall we run the election?");
         int repeat = inputInteger();

          //Creates an array filled with candidates
         Candidate[] newElection = new Candidate[candidateCount];
         for (int i = 0; i < candidateCount; i++) {
             newElection[i] = currentCandidates[candidateCount];
         }

         Candidate[] winningCandidates = new Candidate[repeat * (candidateCount - 1)];
         //Repeat as many times as the user requests
         for (int i = 0; i <= repeat; i++) {
             for (int j = 0; j < candidateCount; j++) {
                 try {
                     winningCandidates[(candidateCount * i) + j] = newElection[j].vote(newElection);
                 } catch (Exception e) {
                     //If voting doesn't work, vote for myself
                     winningCandidates[j] = new Candidate_ec22776();
                 }
             }
         }
         //Stores the winning candidate
         Candidate winner = counter.selectWinner(winningCandidates);
         //Prints out the winner
         System.out.println("Most common winner is: " + winner.getName());

         return;
     }
    
    public String getName() {
        return "Fabrizio Romano";
    }
    
    public String getSlogan() {
        return "Here we go!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        // If array is empty, return instance of friend's class.
        if (candidates.length == 0) 
            return new Candidate_ec22776();
        
        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("Here we go!"))
                return c;
        
        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Fabrizio Romano"))
                return c;
        
        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        // If array is empty, return instance of friend's class.
        if (votes.length == 0) 
            return new Candidate_ec22776();
        
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
    
}
