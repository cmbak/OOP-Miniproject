package OOP.ec22697.MP;

import java.util.Scanner;
import java.util.Random;

class Candidate_ec22858 extends Candidate {
  public static void main(String[] args) {

    // From A3
    Candidate[] allCandidates = A3.getCandidateArray();

    boolean continueElection = true;

    while (continueElection) {
      Candidate[] runningCandidates = new Candidate[0];

      displayCandidates(runningCandidates);

      String option = selectOption();

      while (!option.equals("c")) {

        if (option.equals("a")) {
          runningCandidates = addSpecificCandidate(runningCandidates, allCandidates);
          displayCandidates(runningCandidates);
        }

        if (option.equals("b")) {
          runningCandidates = addRandomCandidate(runningCandidates, allCandidates);
          displayCandidates(runningCandidates);
        }

        option = selectOption();
      }

      runElection(runningCandidates);

      String postOption = postSelectOption();

      if (postOption.equals("a")) {
        continueElection = false;
      }

    }

  }

  private static String stringInput(String message) {
    Scanner scanner = new Scanner(System.in);

    System.out.println(message);
    String input = scanner.nextLine();

    return input;
  }

  public static int intInput(String message) {
    Scanner scanner = new Scanner(System.in);

    System.out.println(message);
    String input = scanner.nextLine();
    int int_input;

    try {
      int_input = Integer.parseInt(input);
      if (int_input < 0) {
        System.out.println("Please input a postive integer");
        int_input = intInput(message);
      }
    } catch (NumberFormatException nfe) {
      System.out.println("Please input an integer");
      int_input = intInput(message);
    }

    return int_input;
  }

  private static void displayCandidates(Candidate[] candidates) {
    System.out.println("Candidates are");

    if (candidates.length == 0) {
      System.out.println("None");
    } else {
      for (Candidate candidate : candidates) {
        System.out.println(candidate.getName());
      }
    }
  }

  private static String selectOption() {
    boolean isValid = false;
    String option = "";

    while (!isValid) {
      option = stringInput(
          "Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");

      if (option.equals("a") || option.equals("b") || option.equals("c")) {
        isValid = true;
      } else {
        System.out.println("Only input 'a' or 'b' or 'c'.");
      }
    }

    return option;
  }

  private static Candidate[] addSpecificCandidate(Candidate[] runningCandidates, Candidate[] allCandidates) {
    String usernameInput = stringInput("Please enter a username.");

    // get running candididates
    // make array 1 bigger, with old ones in new array
    Candidate[] newRunningCandidates = new Candidate[runningCandidates.length + 1];
    for (int i = 0; i < runningCandidates.length; i++) {
      newRunningCandidates[i] = runningCandidates[i];
    }

    // get all candidates
    // compare all candidates name to user input
    boolean foundMatch = false;
    Candidate matchingCandidate = allCandidates[0];

    for (int i = 0; i < allCandidates.length; i++) {
      if (allCandidates[i].un.equals(usernameInput)) {
        foundMatch = true;
        newRunningCandidates[newRunningCandidates.length - 1] = allCandidates[i];
      }
    }

    // if matches, add to running candidates and return
    // return new array
    // if not matches, send print, return old array
    if (foundMatch) {
      return newRunningCandidates;
    } else {
      return runningCandidates;
    }

  }

  private static Candidate[] addRandomCandidate(Candidate[] runningCandidates, Candidate[] allCandidates) {
    Random random = new Random();
    int randomCandidateIndex = random.nextInt(allCandidates.length);

    // get running candididates
    // make array 1 bigger, with old ones in new array
    Candidate[] newRunningCandidates = new Candidate[runningCandidates.length + 1];
    for (int i = 0; i < runningCandidates.length; i++) {
      newRunningCandidates[i] = runningCandidates[i];
    }
    // get all candidates
    // add 1 of those randomly to running candidates
    newRunningCandidates[newRunningCandidates.length - 1] = allCandidates[randomCandidateIndex];

    // return new running candidates array
    return newRunningCandidates;
  }

  private static void runElection(Candidate[] runningCandidates) {
    if (runningCandidates.length > 0) {
      Candidate candidateWhoCounts = candidateWhoCountsSelect(runningCandidates);
      int timesToRunElection = intInput("How many times shall we run the election?");
      displayWinners(runningCandidates, candidateWhoCounts, timesToRunElection);
    } else {
      System.out.println("There are no running candidates to elect.");
    }
  }

  private static String postSelectOption() {

    boolean isValid = false;
    String option = "";

    while (!isValid) {
      option = stringInput("Would you like to \n a) exit \n b) continue");

      if (option.equals("a") || option.equals("b")) {
        isValid = true;
      } else {
        System.out.println("Only input 'a' or 'b'.");
      }
    }

    return option;
  }

  private static Candidate candidateWhoCountsSelect(Candidate[] runningCandidates) {
    Candidate selectedCandidate = runningCandidates[0];

    String candidateNameInput = stringInput("Who should count the votes?");

    // get running candidates
    // get user input
    // compare names

    boolean foundMatch = false;

    while (!foundMatch) {

      for (int i = 0; i < runningCandidates.length; i++) {
        if (runningCandidates[i].un.equals(candidateNameInput)) {
          foundMatch = true;
          selectedCandidate = runningCandidates[i];
        }
      }

      // if valid name, return candidate and do their election
      // if invalid, ask to try again
      if (!foundMatch) {
        System.out.println("Invalid name. Please enter a valid username.");
        candidateNameInput = stringInput("Who should count the votes?");
      }
    }

    return selectedCandidate;
  }

  private static void displayWinners(Candidate[] runningCandidates, Candidate candidateWhoCounts,
                                     int timesToRunElection) {

    Candidate[] allWinners = new Candidate[timesToRunElection];

    // run election [blank] many times
    for (int i = 0; i < timesToRunElection; i++) {
      Candidate[] votes = new Candidate[runningCandidates.length];
      // get votes
      for (int j = 0; j < runningCandidates.length; j++) {
        votes[i] = runningCandidates[i].vote(runningCandidates);
      }

      // select winner
      Candidate winner = candidateWhoCounts.selectWinner(votes);
      allWinners[i] = winner;
    }

    // say most common winner
    // get me so i can choose select winner
    Candidate me = new Candidate_ec22858();
    Candidate ultimateWinner = me.selectWinner(allWinners);
    System.out.println("Most common winner is " + ultimateWinner.getName());
  }

  public String getName() {
    return "Edgar Nash";
  }

  public String getSlogan() {
    return "My name is Edgar and I like education.";
  }

  public Candidate vote(Candidate[] candidates) {
    if (candidates.length == 0) {
      return new Candidate_ec22415();
    }

    Candidate longestName = candidates[0];

    for (int i = 0; i < candidates.length; i++) {
      if (longestName.getName().length() < candidates[i].getName().length()) {
        longestName = candidates[i];
      }
    }

    return longestName;
  }

  public Candidate selectWinner(Candidate[] votes) {
    if (votes.length == 0) {
      return new Candidate_ec22858();
    }

    Candidate highestVotedCandidate = votes[0];
    int votesForHighestVotedCandidate = 0;

    for (int i = 0; i < votes.length; i++) {
      Candidate candidate = votes[i];
      int votesForCandidate = 0;
      for (Candidate candidatesToCheck : votes) {
        if (candidate == candidatesToCheck) {
          votesForCandidate++;
        }
      }
      if (votesForCandidate > votesForHighestVotedCandidate) {
        highestVotedCandidate = votes[i];
        votesForHighestVotedCandidate = votesForCandidate;
      }
    }

    return highestVotedCandidate;
  }
}
