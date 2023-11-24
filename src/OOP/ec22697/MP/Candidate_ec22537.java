package OOP.ec22697.MP;// File Candidate_ec22537.java

import java.util.Scanner;
import java.util.Random;

class Candidate_ec22537 extends Candidate {
    
    
    public static void main(String [] args) {
        Candidate[] allCandidates = A3.getCandidateArray();
        
        Candidate[] candidateArray = new Candidate[allCandidates.length];

        String option = "";
        
        int candidateCount = 0;
        
        System.out.println("= Running Repeated Elections =");

        while (!option.equals("d")) {
            
            // Show candidates
            
            printCandidates(candidateArray, candidateCount);
            
            Scanner scanner = new Scanner(System.in);
            
            System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election d) exit");

            if (scanner.hasNext()) {
                option = scanner.next();

                if (option.equals("a")) 
                {
                    Candidate newCandidate = addCandidate(allCandidates,candidateArray);
                    if(newCandidate != null) {
                        candidateArray[candidateCount] = newCandidate;
                        candidateCount++;
                    }
                }
                else if (option.equals("b")) 
                {
                   Candidate newRandomCandidate = addRandomCandidate(allCandidates, candidateArray);
                   candidateArray[candidateCount] = newRandomCandidate;
                   candidateCount++;
                }
                else if (option.equals("c"))
                {
                    String voteInput = inputStr("Who should count the votes?");
                    Candidate voteHandler = A3.getByUsername(voteInput, allCandidates);
                    int noOfIterations = inputInt("How many times shall we run the election?");
                    runElection(allCandidates, candidateArray, candidateCount, noOfIterations, voteHandler);
                }
            }

            else {
                option = scanner.next();

            } 
        }
        
    }
    public static String inputStr (String question) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(question);
        return scanner.nextLine();
    }

    // Asks the user a question and provides an input box for the user to type in their answer
    // answer given must be an integer or the method will reject and ask the user to type another input again

    public static int inputInt (String userInput) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(userInput);    

        while (!scanner.hasNextInt()){
            System.out.println("Input wasn't a number try again: ");
            scanner.nextLine();
        }

        return scanner.nextInt();
    }  
    
    public String getName() {
        return "Lebron James";
    }
    
    public String getSlogan() {
        return "I love Basketball";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22537();
        
        if (candidates.length != 0) r = candidates[0];
 
        if(candidates.length == 0 || candidates == null) {
            return new Candidate_ec22537(); // Return the instance of this class
        } 
        
        for(Candidate c: candidates) {
            if (c.getSlogan().contains("Nature"))
                return c;
        }
        
        for(Candidate c: candidates) {
            if (c.getName().contains("James"))
                return c;
        }
        
        return r;
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22537();
        
        if (votes.length != 0) r = votes[0];
 
        if(votes.length == 0 || votes == null) {
            return new Candidate_ec22537(); // Return the instance of this class
        }    
        
        int highestCount = 0;
        for (Candidate c : votes) {
            int counter = 0;
            for (Candidate x : votes)
                if (x == c)
                    counter++;
            if (counter > highestCount) {
                highestCount = counter;
                r = c;
            }
        }
        
        return r;
    }
    
    public static Candidate addCandidate(Candidate[] allCandidates, Candidate[] candidateArray) {
        String user = inputStr("Please enter a username.");
        Candidate userCandidate  = A3.getByUsername(user, allCandidates);
        int indexCounter = 0;
        for(int i = 0; i<allCandidates.length;i++) {
            if(allCandidates[i].equals(userCandidate)) {
                System.out.println("Candidate: " + userCandidate.getName());
                indexCounter++;
                return userCandidate;
            };
        }
        
        System.out.println("Could not find user");
        
        return null;
    }
    
    public static Candidate addRandomCandidate(Candidate[] allCandidates, Candidate[] candidateArray) {
        Random randomObj = new Random();
        Candidate randomCandidate = allCandidates[randomObj.nextInt(allCandidates.length-1)];
        
        System.out.println("Candidate chosen was: " + randomCandidate.getName());
        return randomCandidate;
    }
    
    
    public static void runElection(Candidate[] allCandidates, Candidate[] candidateArray, int counter, int noOfIterations, Candidate voteHandler) {
        
        if (counter == 0) {
            System.out.println("No candidates, addition of candidates required.");
            return;
        }
        
        Candidate[] electionCandidates = new Candidate[counter];
        for (int i = 0; i<counter; i++){
            electionCandidates[i] = candidateArray[i];
        }

        Candidate[] winningCandidates = new Candidate[noOfIterations]; 
        Candidate[] allTempVotes = new Candidate[electionCandidates.length];
        for (int i = 0; i<noOfIterations; i++) {
            for (int j = 0; j < electionCandidates.length; j++) {
                try {
                    allTempVotes[j] = allCandidates[i].vote(electionCandidates);
                
                }
                catch (Exception e) {
                    System.out.println("Problem occured.");
                    allTempVotes[j] = new Candidate_ec22537();
                }
            }
            winningCandidates[i] = voteHandler.selectWinner(allTempVotes);
        }
        Candidate winnerFinal = voteHandler.selectWinner(winningCandidates);
        System.out.println("Most common winner is " + winnerFinal.getName() + ".");
        if(winningCandidates.length == 1) {
            System.out.println("There were no other winners");
        }
    }
    
    
    public static void printCandidates(Candidate[] candidateArray, int candidateCount) {
        System.out.println("Candidates are");
        
        if (candidateCount == 0) {
            System.out.println("None");
        } 
        else {
            for(int i=0;i<candidateCount;i++) {
                System.out.println(i + 1 + ": "+ candidateArray[i].getName() + " " +  candidateArray[i].getSlogan());
            }
        }
    }
}
