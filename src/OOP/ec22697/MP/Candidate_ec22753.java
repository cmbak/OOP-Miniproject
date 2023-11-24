package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22753 extends Candidate {
    public String getName() {
        return "Alexey";
    }
    public String getSlogan() {
        return "Who is Joe?";
    }

    
    public Candidate vote(Candidate[] candidates) {
        Random rand = new Random();
        
        if(candidates.length == 0){return new Candidate_ec22753();}
        
        //returns candidate with same slogan
        for (int i = 0; i < candidates.length; i++) {
            if (candidates[i].getSlogan().equals("Who is Joe?")){
                return candidates[i];
            }
        }
        //returns candidate with same name
        for (int i = 0; i < candidates.length; i++) {
            if (candidates[i].getName().equals("Alexey")){
                return candidates[i];
            }
        }
        //if previous conditions were not met return a random candidate from array
        return candidates[rand.nextInt(candidates.length)];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        // if empty return inst of this class
        if(votes.length==0){
            return new Candidate_ec22753();
        }
        //declare current winner and highest count detected
        Candidate winner = votes[0];
        int maxCount = 0;
        //loop through, comparing every element to each other using nested loops i,j to find winner
        for (Candidate vote : votes) {
            int count = 0;
            for (int j = 0; j < votes.length; j++) {
                if(vote==votes[j]){
                    count=count+1;
                }
            }
            if(count>maxCount){ winner=vote; maxCount = count;}
        }
        return winner;
    }

    /**
     * Main method that runs to conduct Election as needed for A3
     */
    public static void main(String[] args) {
        Candidate[] allPossibleCandidates = A3.getCandidateArray();
        Candidate[] candidates = new Candidate[allPossibleCandidates.length];

        System.out.println("= Running Repeated Elections =");
        String option ="";
        int candidateIndex = 0;
        while (!option.equalsIgnoreCase("c")) {
            option = inputStr("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
            if(option.equalsIgnoreCase("a")) {
                String selectedName = inputStr("Please enter a username.");
                Candidate selectedCandidate = A3.getByUsername(selectedName, allPossibleCandidates);
                if(selectedCandidate.equals(null)){
                    candidates[candidateIndex] = selectedCandidate;
                    candidateIndex = candidateIndex+1;
                    listCandidates(candidates);
                }else {
                    System.out.println("Candidate with username "+selectedName+" does not exist.");
                    listCandidates(candidates);
                }
            }else if(option.equalsIgnoreCase("b")){
                Random rand = new Random();
                candidates[candidateIndex] = allPossibleCandidates[rand.nextInt(allPossibleCandidates.length)];
                listCandidates(candidates);
            }else if(option.equalsIgnoreCase("c")){
                Candidate chosenOne = A3.getByUsername(inputStr("Who should count the election votes?"), allPossibleCandidates);
                int numRuns;
                while (true) {
                    try {
                        numRuns = Integer.parseInt(inputStr("Input Number of times the election shall be run.(Integer)"));
                        break;
                    } catch (Exception e) {System.out.println("NAN - Input Integer");}
                }
                election(candidates, candidateIndex, numRuns, chosenOne, allPossibleCandidates);
            }
        }

    }


    /**
     * @param arr Takes a candidate array and prints it out formatted
     */
    public static void listCandidates(Candidate[] arr) {
        System.out.println("Candidates are");
        if(arr.length==0) {
            System.out.println("None");
        }else {
            for (int i = 0; i < arr.length; i++) {
                System.out.println((1+i)+". "+ arr[i].getName());
            }
        }
    }
    /**
     * @param arg the prompt that the user sees as a printed query
     * @return returns the string given by the user
     */
    public static String inputStr(String arg) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println(arg);
            return sc.nextLine();
        }
    }
    /**
     * @param candidates that are voted for by in the election
     * @param count the counter that keeps track of the number of candidates that are voted for
     * @param numRuns number of times election conducted
     * @param whoCounts details who counts the vote, using the selectWinner method of each candidate 
     * @param allCandidates provided by the A3.getCandidateArray(), gives all those that can participate in election
     */
    public static void election(Candidate[] candidates, int count, int numRuns, Candidate whoCounts, Candidate[] allCandidates){
        Random rand = new Random();
        Candidate[] currentElection = new Candidate[count];
        Candidate[] winners = new Candidate[numRuns*(count-1)];
        for (int i = 0; i < count; i++) {
            currentElection[i] = candidates[count];
        }
        for (int i = 0; i <= numRuns; i++) {
            for (int j = 0; j < count; j++) {
                try {
                    winners[(count*i)+j] = currentElection[j].vote(currentElection);
                } catch (Exception e) {winners[j]=allCandidates[rand.nextInt(allCandidates.length)];} //unable to determine winner votes randomly
            }
        }
        System.out.println("The winner of the election with the most votes is: "+whoCounts.selectWinner(winners).getName()); //gives winner
    }
    
}
