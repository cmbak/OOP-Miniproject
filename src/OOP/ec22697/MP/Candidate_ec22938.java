package OOP.ec22697.MP;// If your code uses libraries, be sure to include the necessary
// import lines in your file Candidate_/un/.java

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22938 extends Candidate {
    
    public static String stringInput(String message) {
        
        String output;
        
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        output = scanner.nextLine();
        
        return output;
        
    }

    public static int intInput(String message) {
        
        int output;
        
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        output = scanner.nextInt();
        
        return output;
        
    }
    
    public static int numberOfTimes(Candidate x, Candidate[] a) {

        int r = 0;

        // A lot like isAmong(int, int[]).
        for (int i = 0; i < a.length; i++)
        {
            if (a[i] == x)
            {
                r += 1;
            }
        }

        return r;
    }


    public static Candidate mostCommon(Candidate[] a) 
    {

        Candidate r = a[0]; 
        int number;

        int counter = 0;
        for (int i = 0; i < a.length; i++)
        {
            number = (numberOfTimes((a[i]), a));
            if (number > counter) 
            {
                counter = number;
                r = a[i];
            }
        }
        return r;
    }
    
    public static int addCandidate(Candidate[] allCandidates, Candidate[] candidateLineUp, int counter) {
        String name = stringInput("Please enter a username: ");
        Candidate newCandidate = A3.getByUsername(name, allCandidates);
        if (newCandidate == null) {
            
            System.out.println("Cannot find candidate: " + name);
            return counter;
        }
        else {
            
            candidateLineUp[counter] = newCandidate;
            return counter + 1;
        }
    }
    
    public static int addRandomCandidate(Candidate[] allCandidates, Candidate[] candidateLineUp, int counter) {
        
        if (counter == allCandidates.length) {
            
            System.out.println("Candidate line up FULL");
            return counter;
        }
            
        boolean exist = false;

        while (true) {
            
            exist = false;
            
            Random rand = new Random();

            int randomCandidate = rand.nextInt(allCandidates.length);
            Candidate candidate = allCandidates[randomCandidate];

            for (int i = 0; i < counter; i++) {
                if (candidateLineUp[i] == candidate) {

                    System.out.println("Candidate already exists in line up");
                    exist = true;
                }
            }

            if (exist == false) {
                
                candidateLineUp[counter] = candidate;
                return counter + 1;
            }      
        }         
    }
    
    public static void runElection(Candidate[] allCandidates, Candidate[] candidateLineUp, int counter) {
        
        String voteCounterPersonName = stringInput("Who should count the votes?: ");
        Candidate voteCounterPerson = A3.getByUsername(voteCounterPersonName, allCandidates);
        
        if (voteCounterPerson == null) {
            
             System.out.println("Cannot find candidate: " + voteCounterPersonName);
        }
        else {
            
            int numElectionRuns = intInput("How many times shall we run the election?: ");
            
            Candidate[] allWinners = new Candidate[numElectionRuns];
            Candidate[] candidates = new Candidate[counter];
            Candidate[] votes = new Candidate[allCandidates.length];
            
            for (int i = 0; i < counter; i++) {
                
                candidates[i] = candidateLineUp[i];
            }
            
            for (int i = 0; i < numElectionRuns; i++) {
                
                for (int j = 0; j < allCandidates.length; j++)
                {
                    votes[j] = allCandidates[j].vote(candidates);
                }
                
                allWinners[i] = voteCounterPerson.selectWinner(votes);
            }
            
            Candidate winner = mostCommon(allWinners);
            System.out.println("Most common winner is " + winner.getName());
        }
    }
    
    public static void main(String[] args) {
        
        Candidate[] allCandidates = A3.getCandidateArray();
        Candidate[] candidateLineUp = new Candidate[allCandidates.length];
        int counter = 0;
        String userChoice;
        boolean exit = false;
        
        while (exit == false) {
            System.out.println("= Running Repeated Elections =");
            userChoice = stringInput("Would you like to a) add a specific candidate b) add a candidate at random c) run the election d) exit ?");
            if (userChoice.equals("a")) {
                counter = addCandidate(allCandidates, candidateLineUp, counter);
            }
            else if (userChoice.equals("b")) {
                counter = addRandomCandidate(allCandidates, candidateLineUp, counter);
            }
            else if (userChoice.equals("c")) {
                
                runElection(allCandidates, candidateLineUp, counter);
                
            }
            else if (userChoice.equals("d")) {
                
                exit = true;
            }
        }
    }
    
    public String getName() {
        return "Alesha";
    }
    
    public String getSlogan() {
        return "I don’t wanna work, I wanna be a baller";
    }
    
    public Candidate vote(Candidate[] candidates) {

        if (candidates.length == 0) 
            return new Candidate_ec22938();

        for (Candidate c : candidates)
            if (c.getName().equals("Humairaa"))
                return c;

        Random r = new Random();
        int c = r.nextInt(candidates.length);
        return candidates[c]; 
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        if (votes.length == 0) 
            return new Candidate_ec22938();
        
        Candidate currentWinner = votes[0];
        
        int highestCount = 0;
        for (Candidate c : votes) {
            
            int count = 0;
            for (Candidate x : votes)
                if (x == c)
                    count++;
            if (count >= highestCount) {
                highestCount = count;
                currentWinner = c;
            }
        }
        
        return currentWinner;
    }
}
