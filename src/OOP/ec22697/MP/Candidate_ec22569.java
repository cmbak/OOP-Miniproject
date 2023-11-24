package OOP.ec22697.MP;// File Candidate_ec22569.java
//

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22569 extends Candidate 
{

    public String getName()
    {
        return "Homer Simpson";
    }
    
    
    public String getSlogan()
    {
        return "Doughnuts.";
    }
    
    public Candidate vote(Candidate[] candidates)
    {      
        // if empty, return instance of friend's class
        if (candidates.length == 0) 
        {
            return new Candidate_ec22503();
        }
        
        // set default to 0 
        Candidate to_vote = candidates[0];
                
        // vote for another simpson
        for (int i = 0; i < (candidates.length); i ++ )
        {
          String name = candidates[i].getName();
          
          if ((name.contains("simpson")) || (name.contains("Simpson")))
          {
            return to_vote = candidates[i];          
          }
          
        }
        
        // otherwise return candidate in the middle of array
        int num = ((candidates.length)/2);
        return candidates[num];
    }
    
    public Candidate selectWinner(Candidate[] votes)
    {
        // if array is empty, return instance of friend's class
        if (votes.length == 0) 
        {
           return new Candidate_ec22562();
        }

        // select the winner as a candidate whose name also begins with letter 'H' or 'h'
        Candidate winner = votes[0];        
        for (int i = 0; i < (votes.length); i ++ )
        {
          String name = votes[i].getName();
          
          if ((name.startsWith("H")) || (name.startsWith("h")))
          {
              if (!(name.equals("Homer Simpson")))
              {
                  return winner = votes[i];    
              }
          }
          
        }
        
        // otherwise randomly choose based on array length
        Random randomly_choose = new Random ();
        int pos = randomly_choose.nextInt((votes.length)-1);
        return votes[pos];        
    }

    // general method to print text onscreen to user
    public static void print (String text)
    {
      System.out.println(text);
    }

   // general method for user to enter string only
   public static String inputString(String message)
   {
       String user_input;
       int numerical_value;
    
       Scanner scanner = new Scanner(System.in);
    
       print(message);
    
       user_input = scanner.nextLine();
    
       if (user_input.equals(""))
       {
          print("Error - nothing entered.");
          user_input = inputString(message);
       }
       else
       {
         try 
          {
             numerical_value = Integer.parseInt(user_input);
             print("Error - no number(s) should be entered.");
             user_input = inputString(message);
          }
          catch (Exception e)
          {
             user_input = user_input;
          }
        }
      
       return user_input;
    }
    
   // general method for user to enter integer only
    public static int inputInteger(String text)
    {
      String user_input;
      int number;

      Scanner scanner = new Scanner(System.in);

      System.out.println(text);
      user_input = scanner.nextLine();

      try 
      {
        number = Integer.parseInt(user_input);
      }
      catch (Exception e)
      {
        if (user_input.equals(""))
        {
          print("Error - nothing entered.");
          number = inputInteger(text);
        }
        else
        {
          print("Error. Enter a number.");
          number = inputInteger(text);
        }
      }    
        return number;
    }
    
    // print all candidates 
    public static void printCandidates(Candidate[] election_candidates)
    {
      int length = election_candidates.length;
      
      print("Candidates are");
      for (int i = 0; i < length; i++)
      {
          if (!(election_candidates[i] == null))
          {
              print((i+1) + ". " + (election_candidates[i].getName()));
          }
      
      }
    
    }
    
    // add candidate to array and return the updated array
    public static Candidate[] addSpecificCandidate(Candidate[] all_candidates, Candidate[] election_candidates)
    {
        String name = "";
        boolean added = false;
        int count = 0;
        boolean found = false;
        int length = election_candidates.length;
        
        name = inputString("Please enter a username.");
        
        Candidate specific_candidate = A3.getByUsername(name, all_candidates);
    
        if (!(specific_candidate == null)) 
        {
        
            if (!(election_candidates[(length-1)] == null))
            {
               print("There is no space left in the array to add another candidate.");
            }
            
            else
            {
                while (found == false && (election_candidates[length-1] == null))
                {
                    if (election_candidates[count] == null)
                    {
                        found = true;
                    }
                    else
                    {
                        count = count + 1;
                    }
                }
                
                election_candidates[count] = specific_candidate;
            }
        }    
        else 
        {
            print("User not found.");
        }
        
        printCandidates(election_candidates);
        
        return election_candidates;
    }
    
    // randomly add a candidate
    public static Candidate[] addRandomCandidate(Candidate[] all_candidates, Candidate[] election_candidates)
    {
        Random random = new Random ();
        int number = random.nextInt(all_candidates.length);
        int count = 0;
        int length = election_candidates.length;
        boolean found = false;
        
        if (!(all_candidates[number] == null)) 
        {
            if (!(election_candidates[length-1] == null))
            {
               print("There is no space left in the array to add another candidate.");
            }
            
            else
            {
                while (found == false && (election_candidates[length-1] == null))
                {
                    if (election_candidates[count] == null)
                    {
                        found = true;
                    }
                    else
                    {
                        count = count + 1;
                    }
                }
                
                election_candidates[count] = all_candidates[number];
            }  
        }
        
        return election_candidates;
    }
    
    // run election
    public static void runElection(Candidate[] all_candidates, Candidate[] election_candidates)
    {
        Candidate[] winning_candidates = new Candidate[all_candidates.length];
        Candidate winner = new Candidate_ec22569();
        String user_counting = "";
        int total = 0;
        boolean exists = false;
        
        user_counting = inputString("Who should count the votes?");
        
        for (int i = 0; i<(winning_candidates.length); i++)
        {
            winning_candidates[i] = winner;        
        }
        
        total = inputInteger("How many times shall we run the election?");

        if (election_candidates != null)
        {
            for (int j = 0; j<total; j++)
            {
                winner = election_candidates[j];
                winner = winner.vote(election_candidates);
                winning_candidates[j] = winner;
            }

            winner = A3.getByUsername(user_counting, all_candidates);
            winner = winner.selectWinner(winning_candidates);

            print("Most common winner is " + winner.getName());  
        }
        else
        {
            winner = A3.getByUsername(user_counting, all_candidates);
            print("Most common winner is " + winner.getName());
        }

    }
    
    public static void chooseOption(Candidate[] all_candidates, Candidate[] election_candidates)
    {   
       
        boolean exit = false;
        String choice = "";
        
        while (exit == false)
        {
            // repeatedly choose an option until the election is ran
          choice = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election d) exit?");
          
          if (choice.equals("a"))
          {
              election_candidates = addSpecificCandidate(all_candidates, election_candidates);
              chooseOption(all_candidates, election_candidates);
          }
          else if (choice.equals("b"))
          {
              election_candidates = addRandomCandidate(all_candidates, election_candidates);
              chooseOption(all_candidates, election_candidates);
          }
          else if (choice.equals("c"))
          {
              runElection(all_candidates, election_candidates);
              chooseOption(all_candidates, election_candidates);  
          }
          else if (choice.equals("d"))
          {
              exit = true;
          }          
        }
        
        print("End.");
        
    }
    
    public static void main(String[] args)
    {    
    
        Candidate[] all_candidates = A3.getCandidateArray();
        Candidate[] election_candidates = new Candidate[all_candidates.length];
        
        chooseOption(all_candidates, election_candidates);
    }
}
