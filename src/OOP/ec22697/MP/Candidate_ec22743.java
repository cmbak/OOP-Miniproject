package OOP.ec22697.MP;// File Candidate_ec22743.java
//
// Machine generated stub for Assignment 2
// update with A3

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22743 extends Candidate
{
  public String getName()
  {
    return "Rucha";
  }

  public String getSlogan()
  {
    return "idek";
  }
  
  public static void main (String[] args) 
  {

      Candidate[] allCandidates = A3.getCandidateArray(); //an array of all the candidates
      Candidate[] candidates = new Candidate[allCandidates.length]; // an array for inputing a candidate (vote)
      int array_count = 0; // to keep up with the number of times voted.

      String ans = AskQuestion();
        
        while (!(ans.equals("exit"))) {
          if (ans.equals("a")) {
              String username = inputString("Please enter a username");
              Candidate person = A3.getByUsername(username, allCandidates); //person from getname username added to the votes array
              candidates[array_count] = person;
              array_count++;

              for(int i=0;i<array_count; i++) {
                  System.out.println((i+1) + ")" + candidates[i].getName()); // prints out the response
              }
              ans = AskQuestion();
          }
          else if (ans.equals("b")) {

              Random random = new Random();
              int random_num = random.nextInt(allCandidates.length);
              candidates[array_count] = allCandidates[random_num]; //picks a random candidate to vote for form all candidates
              array_count++;

              for(int i=0;i<array_count; i++) {
                  System.out.println((i+1) + ")" + candidates[i].getName()); //prints out response
              }
              ans = AskQuestion();
          }
          else if (ans.equals("c")) {
              String count = inputString("Who should count the votes?");
              Candidate counter = A3.getByUsername(count, allCandidates); // get the user who counts
              int howMany = inputInt("How many times shall we run the election?"); //number of times to run election

              runElection(allCandidates, candidates, counter, howMany, array_count);
              ans = "exit";
        }
      }
  }
    
  public static void runElection (Candidate[] allCandidates, Candidate[] candidates, Candidate counter, int howMany, int array_count)
  {
      Candidate[] votes = new Candidate[candidates.length];
      
      for (int i=0; i<array_count; i++) {
          votes[i] = candidates[i]; //votes of all candidates stored in new array
      }
      
      Candidate[] winner = new Candidate[howMany]; //for selectWinner
      Candidate[] allVotes = new Candidate[allCandidates.length]; //for all votes
      
      for (int i=0; i<howMany; i++) { //loops the election the number of times stated
          for (int j=0; j<allCandidates.length; j++) {
              allVotes[j] = (allCandidates[j]).vote(allCandidates); //puts votes of all candidates
          }
          winner[i] = counter.selectWinner(allVotes); //puts votes of the counter candidate
      }
      
      Candidate finalWinner = counter.selectWinner(winner);
      System.out.println("The winner is " + finalWinner.getName() + " with the slogan " + finalWinner.getSlogan());
  }

  public static String inputString(String line) // to input string with scanner inside
  {
      Scanner scanner = new Scanner(System.in);
      System.out.println(line);
      String ans = scanner.nextLine();
      return ans;
  }

  public static int inputInt(String line) // inputed string converted to int from scanner
  {
      Scanner scanner = new Scanner(System.in);
      System.out.println(line);
      String ans = scanner.nextLine();
      return Integer.parseInt(ans);
  }

  public static String AskQuestion () 
  {
      String ans = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
      return ans;
  }
  
  public Candidate vote(Candidate[] candidates)
  {
    if (candidates.length == 0) {
      return new Candidate_ec22743();
    }

    for (Candidate c : candidates) {
      if (c.getSlogan().equals("idek")) {
        return c;
      }
    }

    for (Candidate c : candidates) {
      if (c.getName().equals("Rucha")) {
        return c;
      }
    }

    return candidates[candidates.length-1];
  }


  public Candidate selectWinner(Candidate[] votes)
  {
    if (votes.length == 0) {
      return new Candidate_ec22743();
    }

    Candidate currentWinner = votes[0];

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
