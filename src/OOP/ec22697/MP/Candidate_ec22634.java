package OOP.ec22697.MP;// File Candidate_ec22634.java
//
// Machine generated stub for Assignment 3

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22634 extends Candidate {
    
    public String getName() {
        return "Anon";
    }
    
    public String getSlogan() {
        return "Free food";
    }
    
    public Candidate vote(Candidate[] candidates)
    {
        
        for (Candidate c : candidates)
            if (c.getName().equals("Anon"))
                return c;
        
        Random r = new Random();
        int c = r.nextInt(candidates.length);
        return candidates[c];
    }
    
    public Candidate selectWinner(Candidate[] votesCast) 
    {
        
        if (votesCast.length == 0) 
            return new Candidate_ec22634();
        
        // Choose winner at random from votes cast.
        Random r = new Random();
        int c = r.nextInt(votesCast.length);
        return votesCast[c];        
    }


public static int inputInteger(String message)
{
  Scanner keyboard = new Scanner(System.in);
  System.out.println(message);
  int answer = Integer.parseInt(keyboard.nextLine());
  return answer;
}

public static String inputString(String message)
{
  Scanner keyboard = new Scanner(System.in);
  System.out.println(message);
  String answer = keyboard.nextLine();
  return answer;
    }

public static void main(String[] args) 
{
 Candidate[] allCandidates = A3.getCandidateArray();
 Candidate[] votes = new Candidate[allCandidates.length];
 int count = 0;
 String answer = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");

        if (answer.equals("a") || answer.equals("A"))
        {
            String username = inputString("Please enter a username.");
            Candidate inputted_name = A3.getByUsername(username, allCandidates);
            votes[count] = inputted_name;
            count = count +1;
            printCandidates(votes, count);
        } 
        else if ((answer.equals("b") || answer.equals("B")))
         {
            Random random = new Random();
            int randomInt = random.nextInt(allCandidates.length);
            votes[count] = allCandidates[randomInt];
            count = count +1;
            printCandidates(votes, count);
         }
        else if ((answer.equals("c") || answer.equals("C")))
         {
            String selected_counter = inputString("Who should count the votes?");
            Candidate chosenCounter = A3.getByUsername(selected_counter, allCandidates);
            int howManyTimes = inputInteger("How many times shall we run the election?");
          
            runElection(votes, count, howManyTimes, chosenCounter);
        }
 }
 public static void printCandidates(Candidate[] candidates, int count) 
  {
   System.out.println("Candidates are: ");
   for (int i = 0; i < count; i++) 
    {
      System.out.println(candidates[i].getName());
    }
        return;
    }

   public static void runElection(Candidate[] election, int counter, int repeat, Candidate chosenCandidate)  // this method was adapted from ec22486
 {
        // Creates a new array with all candidates and no 'null' values
    
        Candidate[] election_cleaned = new Candidate[counter];
        for (int i = 0; i < counter; i++) 
        {
            election_cleaned[i] = election[counter];

        }

        Candidate[] votes = new Candidate[repeat * (counter - 1)];

        for (int j = 0; j <= repeat; j++) 
        {
            // Counts each vote for each candidate
            for (int i = 0; i < counter; i++) 
            {
                try 
                {
                    votes[((counter - 1) * j) + i] = election_cleaned[i].vote(election_cleaned);
                } 
                catch (Exception e) 
                {
                    //votes for myself if the above doesnt work
                    votes[i] = new Candidate_ec22634();
                }
            }
        }

        Candidate winner = chosenCandidate.selectWinner(votes);
        System.out.println("Most common winner is: " + winner.getName());
       
       return;
    }
    }
