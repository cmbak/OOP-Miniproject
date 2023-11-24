package OOP.ec22697.MP;// File Candidate_ex21405.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;
 class Candidate_ex21405 extends Candidate {

      public String getName() {
         return "Seb";
     }
     public String getSlogan() {
         return "I like food!";
     }
     public Candidate vote(Candidate[] candidates) 
     {
         Candidate r = new Candidate_ex21405();
         if (candidates.length != 0) r = candidates[0];
         if (candidates.length == 0) 
             return new Candidate_ec22569();
        
         for (Candidate c : candidates)
             if (c.getSlogan().equals("No more exams!"))
                 return c;
         
         for (Candidate c : candidates)
             if (c.getName().equals("Adam"))
                 return c;
         return r;
     }
     public Candidate selectWinner(Candidate[] votes) {
         Candidate r = new Candidate_ex21405();
         if (votes.length != 0) 
             r = votes[0];
         Candidate currentWinner = votes[0];
        
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
      System.out.println("Running Repeated Elections");

      while(!valid)
      {
          System.out.println("Candidates are:");
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
                      System.out.println("Invalid input. Please try again.");
              }
          }
          else 
              System.out.println("Inavlid input. Please try again.");
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

      int numRuns = inputInt("How many times should the election run?");
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
          System.out.println("Most common winner after running the elections is " + overallWinner[0] + ".");
          System.out.println("There are no other winners.");
      }
      else
      {
          System.out.println("Most common winners after running the elections are ");
          for(int i=0;i<winnerCount;i++)
          {
              System.out.println(overallWinner[i]);
          }
      }

      return;
  }

 }
