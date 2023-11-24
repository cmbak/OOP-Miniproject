package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;
class Candidate_ex21283 extends Candidate {
    
    public String getName() {
        return "MonkeyDLuffy";
    }
    
    public String getSlogan() {
        return "I got nothing :(";
    }
    public Candidate vote(Candidate[] candidates) {
        
        // If array is empty, vote for self
        if (candidates.length == 0) 
            return new Candidate_ex21283();
        
        //vote for the king
        for (Candidate c : candidates)
            if (c.getName().equals("King"))
                return c;
        
        // otherwise randomly select winner
       Random r = new Random();
        int i = r.nextInt(candidates.length);
        return candidates[i];
        

    }
    
    public Candidate selectWinner(Candidate[] candidates) {
        
        // If array is empty, return instance of this class.
        if (candidates.length == 0) 
            return new Candidate_ex21283();
        
        //select self. 
        for (Candidate iwin : candidates)
            if (iwin.getName().equals("MonkeyDLuffy"))
                return iwin;
        //in the rare instance I wasn't voted select the modal vote
        Candidate currentWinner = candidates[0];
        int highestCount = 0;
        for (Candidate c : candidates) {
            
            int count = 0;
            for (Candidate x : candidates)
                if (x == c)
                    count++;
            if (count >= highestCount) {
                highestCount = count;
                currentWinner = c;
            }
        }
        
        return currentWinner;
    }
    
    
    //returns input as string
    public static String inputString(String message) {
         Scanner scanner = new Scanner(System.in);
         System.out.println(message);
         return scanner.nextLine();
    }

    //returns input as integer
    public static int inputInteger(String message) {
        Scanner scanner2 = new Scanner(System.in);
        System.out.println(message);
        return Integer.parseInt(scanner2.nextLine());
    }
    
    
    public static void main(String[] args) {
        Candidate[] allCandidates = A3.getCandidateArray();
        Candidate[] votes = new Candidate[allCandidates.length];
        int counter = 0;
        String input = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?").toLowerCase();

        if (input.equals("a")){
            String username = inputString("Please enter a username.");
            Candidate newCandidate = A3.getByUsername(username, allCandidates);
            votes[counter] = newCandidate;
            counter++;
            
            printCandidates(votes, counter);
        } 
        else if (input.equals("b")){
            Random random = new Random();
            int randomInt = random.nextInt(allCandidates.length);
            votes[counter] = allCandidates[randomInt];
            counter++;
            printCandidates(votes, counter); 
        }
        
        else if (input.equals("c")){
            String voteCounter = inputString("Who should count the votes?");
            Candidate chosenCounter = A3.getByUsername(voteCounter, allCandidates);
            int howManyTimes = inputInteger("How many times shall we run the election?");
            runElection(votes, counter, howManyTimes, chosenCounter, allCandidates);
        }
    }

    public static void printCandidates(Candidate[] candidates, int counter) {
        System.out.println("Candidates are: ");
        for (int i = 0; i < counter; i++) {
            System.out.println(candidates[i].getName() + " with their slogan: " + candidates[i].getSlogan());
        }
        return;
    }

    public static void runElection(Candidate[] votes, int counter, int howManyTimes, Candidate voteCounter, Candidate[] allCandidates){
        Candidate[] newElection = new Candidate[counter];
        for (int i = 0; i < counter; i++) {
            newElection[i] = votes[counter];
        }

        Candidate[] winningCandidates = new Candidate[howManyTimes * (counter - 1)];
        
        for (int i = 0; i <= howManyTimes; i++) {
            for (int j = 0; j < counter; j++) {
                try {
                    winningCandidates[(counter * i) + j] = newElection[j].vote(newElection);
                } catch (Exception e) {
                     //If voting doesn't work, vote for myself
                    winningCandidates[j] = new Candidate_ex21283();
                }
            }
        }
        //stores the winning candidate
        Candidate actualWinner = voteCounter.selectWinner(winningCandidates);
        System.out.println("Most common winner is: " + actualWinner.getName());
    }
    


    
}
