package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22535 extends Candidate {
   
    public String getName() {
        return "Amina";
    }
    
    public String getSlogan() {
        return "Don't stop!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        // If array is empty, return instance of friend's class.
        if (candidates.length == 0) 
            return new Candidate_ec22535();
        
        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("Don't stop"))
                return c;
        
        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Amina"))
                return c;
        
        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        // If array is empty, return instance of friend's class.
        if (votes.length == 0) 
            return new Candidate_ec22535();
        
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
     
    private static void ChoiceMessage() {
       System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election d) Exit?");
       return;
    }
    
    public static String InputString(String message) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }
    
    
     public static void printCandidatesInfo(Candidate[] election_Candidates, int electionCount)
     {
        System.out.println("Candidates are");
        if (electionCount == 0) {
            System.out.println("None");
        } else {
            for (int i = 0; i < electionCount; i++) {
                System.out.println((i + 1) + ". " + election_Candidates[i].getName() + ": " + election_Candidates[i].getSlogan());
            }
        }
     }
    
     private static int askQuestionInt(String question)
     {
        Scanner scanner = new Scanner(System.in);
        System.out.println(question);
        int input = Integer.parseInt(scanner.nextLine());

        return input;
     }
    
     private static Candidate[] copyArray(Candidate[] candidates, int electionCount)
     {
        Candidate[] copy = new Candidate[electionCount];
        for (int i = 0; i < electionCount; i++)
        {
            copy[i] = candidates[i];
        }
        return copy;
     }

     private static int addCandidate(Candidate[] election_Candidates, Candidate[] allCandidates, int electionCount)
     {
        System.out.println("Enter a name.");
        String candidateName = InputString("");
        Candidate candidate = A3.getByUsername(candidateName, allCandidates);
         
        if (candidate != null) {
            election_Candidates[electionCount] = candidate;
            return electionCount + 1;
        } else {
            System.out.println("Cannot find candidate: " + candidateName);
            return electionCount;
        }
    }

    private static int addRandomCandidate(Candidate[] election_Candidates, Candidate[] allCandidates, int electionCount)
    {
        if (electionCount == allCandidates.length) {
            System.out.println("Candidates are full.");
            return electionCount;
        }
        
        Random r = new Random();
        boolean found = false;
        while (true)
        {
            found = false;
            int random_num = r.nextInt(allCandidates.length);
            Candidate candidate = allCandidates[random_num];
            for (int i = 0; i < electionCount; i++) {
                if (election_Candidates[i] == candidate) {
                    found = true;
                }
            }
            if (found == false) {
                election_Candidates[electionCount] = candidate;
                return electionCount + 1;
            }
        }
    }

    
    public static void runElection(Candidate[] election_Candidates, Candidate[] allCandidates, int electionCount)
    {
        System.out.println("Who should count the votes?");
        String counterName = InputString("");
        Candidate counter = A3.getByUsername(counterName, allCandidates);     //person who counts the votes 
        if (counter != null)
        {
            int count = askQuestionInt("How many times shall we run the election?");
            Candidate[] winners = new Candidate[count];
            for (int i = 0; i < count; i++) 
            {
                Candidate[] candidates = copyArray(election_Candidates, electionCount);
                Candidate[] votes = new Candidate[allCandidates.length];
                for (int j = 0; j < allCandidates.length; j++) 
                {
                    votes[j] = allCandidates[j].vote(candidates);
                }
                winners[i] = counter.selectWinner(votes);
            }
            getCommonWinner(winners);
        }
        else
        {
            System.out.println("Cannot find candidate: " + counterName);
        }
    }
    
    private static void getCommonWinner(Candidate[] winners) 
    {
        boolean moreWinners = false;
        String winnerName = null;
        if (winners.length != 0) 
        {
            Candidate[] countWinner = copyArray(winners, winners.length);
            int highestCount = 0;
            for (int i = 0; i < countWinner.length; i++) 
            {
                Candidate c = countWinner[i];
                if (c != null)
                {
                    int count = 0;
                    for (int j = 0; j < countWinner.length; j++) 
                    {
                        Candidate x = countWinner[j];
                        if (x != null && x.getName() == c.getName()) 
                        {
                            countWinner[j] = null;
                            count++;
                        }
                    }
                    if (count == highestCount) 
                    {
                        winnerName = winnerName + " and " + c.getName();
                        moreWinners = true;
                    }
                    if (count > highestCount) 
                    { 
                        highestCount = count; 
                        winnerName = c.getName();
                        moreWinners = false;
                    } 
                }
            }
        }
        if (winnerName != null)
        {
            if (moreWinners == true)
            {
                System.out.println("Most common winners are " + winnerName);
            }
            else
            {
                System.out.println("Most common winner is " + winnerName);
            }
        }
        System.out.println("There were no other winners.");
    }


    
    public static void main(String[] args){
        Candidate[] allCandidates = A3.getCandidateArray();
        Candidate[] election_Candidates = new Candidate[allCandidates.length];
        int electionCount = 0;        //election candidate count
        
        System.out.println("= Running Repeated Elections =");
        
        String choice = "";
        while(choice != "d") {
             printCandidatesInfo(election_Candidates, electionCount);
             ChoiceMessage();
             choice = InputString("");
            
             if(choice == "a") {
                  electionCount = addCandidate(election_Candidates, allCandidates, electionCount);
             } else if (choice == "b") {
                  electionCount = addRandomCandidate(election_Candidates, allCandidates, electionCount);
             } else if (choice == "c") {
                  runElection(election_Candidates, allCandidates, electionCount);
             } else if (choice == "d") {
                 break;
             }
            else {
                System.out.println("You must choose a, b, c or d.");
            }
        }
              
    }
    
}
