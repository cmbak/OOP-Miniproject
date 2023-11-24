package OOP.ec22697.MP;// File Candidate_ec22548.java

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22548 extends Candidate {
    //main calls the starting method
    private Candidate[] candidatesToVote = new Candidate[0];
    
    public static void main(String[] args) {
        System.out.println("Hello world");
        Candidate_ec22548 candidate = new Candidate_ec22548();
        candidate.openingMenu();
    }
    
    public String getName() {
        return "Eula Lawrence";
    }
    
    public String getSlogan() {
        return "Cats should be promoted as gods";
    }
    
    public void print(String string) {
        System.out.println(string);
    }
    
    public String stringInput(String inputInfo) {
        Scanner scanner = new Scanner(System.in);
        print (inputInfo);
        String input = scanner.nextLine();
        return input;
    }
    
    public int intInput(String inputInfo) {
        Scanner scanner = new Scanner(System.in);
        print (inputInfo);
        int input = scanner.nextInt();
        return input;
    } 
    
    //starting method
    public void openingMenu() {
        print("= Running Repeated Elections =");
        
        String loop = "y";
        while (loop.equals("y")) {
            candidatesToVote = mainMenu(candidatesToVote);
            loop = stringInput("Do you wish to repeat this?");
        }
        return;
    }
 
    private Candidate[] mainMenu(Candidate[] candidatesToVote) {
        boolean keepGoing = true;
        
        while (keepGoing) {
            char option = getOption();        
           
            switch (option) {
                case 'a':
                    candidatesToVote = addCandidate(candidatesToVote);
                    break;
                    
                case 'b':
                    candidatesToVote = addRandomCandidate(candidatesToVote);
                    break;

                case 'c':
                    runElection(candidatesToVote);
                    break;
                
                default:
                    keepGoing = false;
            }  
        }
        
        return candidatesToVote;
    }
 

    private char getOption() {
        Scanner scanner = new Scanner(System.in);
        print("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
        char option = scanner.next().charAt(0);
        return option;       
    }

    private Candidate[] addCandidate(Candidate[] candidatesToVote) {
        print("Adding a candidate at random.");
        String name = stringInput("Please enter a username:");
        candidatesToVote = addSpecificCandidate(candidatesToVote, name);
        return candidatesToVote;
    }
     
     private Candidate[] addRandomCandidate(Candidate[] candidatesToVote) {
         A3 newA3 = new A3();
         Candidate[] candidates = newA3.getCandidateArray();
        
         Random random = new Random();
         int userListPosition = random.nextInt(candidates.length-1);
            
         Candidate candidateSelected = candidates[userListPosition];
         candidatesToVote = extendCandidates(candidatesToVote, candidateSelected);
         print ("Random Candidate has been added to list!");
         return candidatesToVote;
    }

 
    private void runElection(Candidate[] candidatesToVote) {
        int repeats = intInput("How many times do we run the election?");
        Candidate[] winnerList = new Candidate[repeats];
        
        for (int i=0; i<repeats; i++) {
            winnerList[i] = electionWinner(candidatesToVote);
        }
        
        Candidate overallWinner = selectWinner(winnerList);
        print ("The overall winner is: " + overallWinner);
        return;
    }
    
    private Candidate electionWinner(Candidate[] candidatesToVote) {
        A3 newA3 = new A3();
        Candidate[] candidates = newA3.getCandidateArray();
        
        Candidate[] votes = new Candidate[candidates.length];
        int count = 0;
        for (Candidate voter: candidates) {
            Candidate vote = tryVote(voter, candidatesToVote);
            votes[count] = vote;
            count +=1;
        }
        
        Candidate winner = selectWinner(votes);
        return winner;
    }
    
    private Candidate tryVote (Candidate voter, Candidate[] candidatesToVote) {
        try {
            Candidate vote = voter.vote(candidatesToVote);
            return vote;
        }
        catch (Exception e) {
            Random random = new Random();
            int length = candidatesToVote.length;
            int randomVote = random.nextInt(length);
            
            Candidate vote = candidatesToVote[randomVote];
            return vote;
        }
    }
    
    private Candidate[] addSpecificCandidate(Candidate[] candidatesToVote, String name) {
        A3 newA3 = new A3();
        Candidate[] candidates = newA3.getCandidateArray();
        
        for (Candidate candidateSelected: candidates) {
            String candidateOption = candidateSelected.un;
            
            if (name.equals(candidateOption)) {
                candidatesToVote = extendCandidates(candidatesToVote, candidateSelected);
                print ("Candidate " + name + " has been added to list!");
                return candidatesToVote;
            }
        }
        
        print ("Please enter a correct username.");
        
        return candidatesToVote;
    }
    
    public Candidate[] extendCandidates(Candidate[] candidatesToVote, Candidate candidate) {
        int length = candidatesToVote.length;
        Candidate[] newCandidatesList = new Candidate[length+1];
        
        for (int i=0; i<length; i++) {
            newCandidatesList[i] = candidatesToVote[i];
        }

        newCandidatesList[length] = candidate;
        print ("Added candidate: " + candidate.un);
        return newCandidatesList;
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate me = new Candidate_ec22548();
        
        if (candidates.length == 0) {
            return me;
        }
        
        else {
            
            for (Candidate candidateToVote : candidates) {
                if (candidateToVote.getName().equals("annie")) {
                    return candidateToVote;
                }
            }
            
            for (Candidate candidateToVote : candidates) {
                if (candidateToVote.getSlogan().equals("Mark my words, vengeance shall be mine!")) {
                    return candidateToVote;
                }
            }
            
            Random random = new Random();
            int listIndex = random.nextInt(candidates.length);
            return candidates[listIndex];
        }
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate mostPop = new Candidate_ec22548();
        int highestNum = 0;
        
        if (votes.length == 0) {
            return mostPop;
        }
        else {
            
            for (int x=0; x<votes.length; x++) { //if it isn't empty does a nested loop to check
                int count = 1;
                
                for (int y=0; y<votes.length; y++) {
                    
                    if (votes[x]==votes[y]) { //if votes matches in both for loops adds a vote
                        count += 1;
                    }
                }
                
                if (count>highestNum) { //after first for checks if the max votes has been surpassed, if it has replace it
                    highestNum = count;
                    mostPop = votes[x];
                }

            }
            return mostPop;
        }
    }
}
