package OOP.ec22697.MP;// File Candidate_ex21247.java
// inspo from ec22943, and full with full credit 
// Machine generated stub for Assignment 2

import java.util.Random;
  import java.util.Scanner;
 class Candidate_ex21247 extends Candidate {

      public String getName() {
         return "RahatD(ex21247)";
     }
     public String getSlogan() {
         return "Hey!! (ex21247)";
     }
     public Candidate vote(Candidate[] candidates) 
     {
         Candidate r = new Candidate_ex21247();
         if (candidates.length != 0) r = candidates[0];
         if (candidates.length == 0) 
             return new Candidate_ec22885();
         // Search for a like minded candidate.
         for (Candidate c : candidates)
             if (c.getSlogan().equals("More trees!"))
                 return c;
         // Otherwise, search for a friend.
         for (Candidate c : candidates)
             if (c.getName().equals("Francois"))
                 return c;
         return r;
     }
     public Candidate selectWinner(Candidate[] votes) {
         Candidate r = new Candidate_ex21247();
         if (votes.length != 0) 
             r = votes[0];
         Candidate currentWinner = votes[0];
         // Count the votes for each object in the array,
         // selecting one with the most.
         int highestCount = 0;
         for (Candidate c : votes) 
         {
             int count = 0;
             for (Candidate x : votes)
                 if (x == c)
                     count++;
             if (count > highestCount) {
                 highestCount = count;
                 currentWinner = c;
             }
         
          }
          return r;
      }
       public static String inputString(String message)
  {
      Scanner scanner = new Scanner(System.in);
      System.out.println(message);
      String answer = scanner.nextLine();

      return answer;
  }

  public static int inputInt(String message)
  {
      Scanner scanner = new Scanner(System.in);
      System.out.println(message);
      int answer = Integer.parseInt(scanner.nextLine());

      return answer;
  }

  public static char inputChar(String message)
  {
      Scanner scanner = new Scanner(System.in);
      System.out.println(message);
      char answer = scanner.nextLine().charAt(0);

      return answer;
  }
      public static void main(String[] args)
  {
      Candidate[] ca = A3.getCandidateArray();
      int candidateNumber = 0;
      Candidate[] candidates = new Candidate[ca.length];

      boolean valid = false;
      System.out.println("= Running Repeated Elections =");

      while(!valid)
      {
          System.out.println("Candidates are: ");
          if (candidateNumber!=0)
          {
              for (int i = 0; i<candidateNumber; i++)
              {
                  System.out.println(candidates[i]);
              }
          }

          char menu = inputChar("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");

          if (menu == 'a')
          {
              candidates = addCandidate(candidates, ca, candidateNumber);
              candidateNumber++;
          }
          else if (menu == 'b')
          {
              candidates = randomCandidate(candidates, ca, candidateNumber);
              candidateNumber++;
          }
          else if (menu == 'c')
          {
              election(candidates, ca, candidateNumber);

              boolean valid2 = false;

              while (!valid2)
              {
                  char option = inputChar("Would you like to a) start another elction b) quit?");

                  if(menu == 'a')
                      valid2 = true;
                  else if(option == 'b')
                  {
                      valid = true;
                      valid2 = true;
                  }
                  else 
                      System.out.println("Invalid option. Please try again.");
              }
          }
          else 
              System.out.println("Inavlid option. Please try again.");
      }
  }

  public static Candidate[] addCandidate (Candidate[] candidates, Candidate[] ca, int candidateNumber)
  {
      String username = inputString("Please enter a username.");

      Candidate addedC = A3.getByUsername(username, ca);

      if (addedC != null)
      {
          candidates[candidateNumber] = addedC;
      }
      else
      {
          System.out.println("Could not find this candidate.");
      }

      return candidates;
  }

  public static Candidate[] randomCandidate (Candidate[] candidates, Candidate[] ca, int candidateNumber)
  {
      Random rand = new Random();
      int random = rand.nextInt(ca.length);
      Candidate randomCan = ca[random];

      candidates[candidateNumber] = randomCan;

       return candidates;
  }

  public static void election (Candidate[] candidates, Candidate[] allCandidates, int candidateNumber)
  {
      Candidate[] fullCandidates = new Candidate[candidateNumber];
      for(int i=0;i<candidateNumber;i++)
           {
               fullCandidates[i]=candidates[i];
           }

      String name = inputString("Who should count the votes?");
      Candidate chosen = A3.getByUsername(name, allCandidates);

      int numRuns = inputInt("How many times shall we run the election?");
      Candidate[] winners = new Candidate[numRuns];
      for (int i=0; i<numRuns; i++)
      {
          Candidate[] votes = new Candidate[allCandidates.length];
          for (int j=0; j<allCandidates.length; j++)
          {
                   try
                   {
                       votes[j] = allCandidates[j].vote(fullCandidates);
                   }
                   catch (Exception e)
                   {

                   }
          }
              winners[i]=chosen.selectWinner(votes);
      }

      int highestCount = 0;
      Candidate[] overallWinner = new Candidate[fullCandidates.length];
      int winnerCount = 1;
      for (int i=0;i<fullCandidates.length;i++)
      {
          int count = 0;
          for (int j=0;j<winners.length;j++)
          {
              if(winners[j].equals(fullCandidates[i]))
              {
                  count++;
              }
          }
          if(count>highestCount)
          {
              highestCount=count;
              overallWinner[0] = candidates[i];
              winnerCount=1;
          }
          else if(count==highestCount)
          {
              winnerCount++;
              overallWinner[winnerCount-1] = candidates[i];
          }
      }

      if(winnerCount==1)
      {
          System.out.println("Most common winner is " + overallWinner[0] + ".");
          System.out.println("There are no other winners.");
      }
      else
      {
          System.out.println("Most common winners are ");
          for(int i=0;i<winnerCount;i++)
          {
              System.out.println(overallWinner[i]);
          }
      }

      return;
  }

 }
