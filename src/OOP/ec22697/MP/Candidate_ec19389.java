package OOP.ec22697.MP;// File Candidate_ec19389.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

class Candidate_ec19389 extends Candidate {

    public static int numberOfVotes(Candidate c, Candidate[] totalVotes){
        int count = 0;

        for (int i = 0; i < totalVotes.length; i++) {
            if (totalVotes[i]==c){
                count++;
            }
        }
        return count;
    }

    public String getName(){
        return "Mr Bean";
    }

    public String getSlogan(){
        return "Behold the man who is a bean";
    }

    public Candidate vote(Candidate[] candidates){
        Random rand = new Random();

        if(candidates.length==0){
            return new Candidate_ec19389();
        }

        int random_position = rand.nextInt(candidates.length);
        return candidates[random_position];

    }

    public Candidate selectWinner(Candidate[] votes){
        if(votes.length==0){
            return new Candidate_ec19389();
        }

        Candidate winningCandidate = votes[0];
        int mostFrequent = 0;

        for(int i=0; i<votes.length; i++){
            if((numberOfVotes(votes[i],votes))>mostFrequent){
                mostFrequent = (numberOfVotes(votes[i],votes));
                winningCandidate = votes[i];
            }
        }
        return winningCandidate; // Return a new Candidate object no-one voted for.
    }

    //prints a passed string and returns the user input string
    public static String inputString(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextLine();
    }

    //prints a passed string and returns the user input string
    public static char inputChar(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.next().charAt(0);
    }

    //prints a passed string and returns the user input integer
    public static int input_Int(String message){
        String input = inputString(message);
        while (!isStringInt(input)){
            input = inputString(message);
        }
        return Integer.parseInt(input);
    }

    //returns true if passed char is int, else returns false
    public static boolean isCharInt(char charInput) {
        char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        for (int i = 0; i < digits.length; i++) {
            if (charInput == digits[i]) {
                return true ;
            }
        }
        return false;
    }

    //returns true if a string is an integer, else returns false
    public static boolean isStringInt(String stringInput){
        for (int i = 0; i < stringInput.length(); i++) {
            if (!isCharInt(stringInput.charAt(i))) {
                return false;
            }
        }
        return true;
    }



    //returns true/false when user inputs yes/no strings
    public static boolean yesOrNoInput(String message){
        String userInput = inputString(message);
        while (!(userInput.equalsIgnoreCase("yes")||userInput.equalsIgnoreCase("no"))){
            userInput = inputString(message);
        }
        if (userInput.equalsIgnoreCase("yes")){
            return true;
        }else {
            return false;
        }
    }

    //Prints the active candidate names
    public static void printActiveCandidateNames(ArrayList<Candidate> activeCandidates){
        System.out.println("Candidates are:");
        for (int i = 0; i < activeCandidates.size(); i++) {
            System.out.println(i+1+". "+activeCandidates.get(i).getName());
        }
    }

    //takes the username as a string input and adds the candidate to the active candidates list
    public static void addCandidate (Candidate[] allCandidates, ArrayList<Candidate> activeCandidates) {
        String username = inputString("Please enter candidate username:");
        if ((A3.getByUsername(username,allCandidates))==null){
            System.out.println("This candidate does not exist.");
        } else {
            activeCandidates.add(A3.getByUsername(username, A3.getCandidateArray()));
            printActiveCandidateNames(activeCandidates);
        }
    }

    //adds a random candidate to active candidate list
    public static void randomCandidate(Candidate[] allCandidates, ArrayList<Candidate> activeCandidates){
        Random random = new Random();
        int randomPosition = random.nextInt(allCandidates.length);
        activeCandidates.add(allCandidates[randomPosition]);
        printActiveCandidateNames(activeCandidates);
    }



    //takes the active candidates and all candidates and rune the election
    public static Candidate runElection (Candidate[] allCandidates, ArrayList<Candidate> activeCandidates){
        String voteCounterUsername = inputString("Who should count the votes?");
        boolean validVoteCounter = false;

        //checks that the user entered for voting exists
        while (!validVoteCounter) {
            if ((A3.getByUsername(voteCounterUsername, allCandidates)) == null) {
                System.out.println("This candidate does not exist.");
                voteCounterUsername = inputString("Who should count the votes?");
            } else{
                validVoteCounter = true;
            }
        }

        Candidate voteCounter = (A3.getByUsername(voteCounterUsername,allCandidates));

        int numOfElections = input_Int("How many times shall we run the votes?");

        Candidate[] activeCandidatesArray = new Candidate[activeCandidates.size()];
        for (int i = 0; i < activeCandidates.size(); i++) {
            activeCandidatesArray[i] = activeCandidates.get(i);
        }

        Candidate[] electionWinners = new Candidate[numOfElections];


        //loops for the given number of elections and for each election loops through all candidates taking in their votes
        for (int i = 0; i < numOfElections; i++) {
            Candidate[] votes = new Candidate[allCandidates.length];
            for (int j = 0; j < allCandidates.length; j++) {
                try {
                    votes[j] = (allCandidates[j].vote(activeCandidatesArray));
                }catch (Exception ignored){}
            }
            electionWinners[i]=(voteCounter.selectWinner(votes));
        }

        Candidate winner =  findMostFrequentCandidate(electionWinners);
        System.out.println("Winner is "+winner.getName());
        return winner;
    }

    //given an array of Candidates, returns the most frequent candidate
    public static Candidate findMostFrequentCandidate(Candidate[] electionWinners){
        int maxCount = 0;
        Candidate mostFrequentCandidate = null;

        for (int i = 0; i < electionWinners.length; i++) {
            int count = 0;
            for (int j = 0; j < electionWinners.length; j++) {
                if (electionWinners[i] == electionWinners[j]) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                mostFrequentCandidate = electionWinners[i];
            }
        }
        return mostFrequentCandidate;
    }

    public static void main(String[] args) {

        Candidate[] allCandidates = A3.getCandidateArray();
        ArrayList<Candidate> activeCandidates = new ArrayList<Candidate>();
        boolean end = false;

        System.out.println("= Running Repeated Elections =");

        while (!end){
            char userChoice = inputChar("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
            if (userChoice == 'a' || userChoice == 'A'){
                addCandidate(allCandidates, activeCandidates);
            } else if (userChoice == 'b' || userChoice == 'B'){
                randomCandidate(allCandidates,activeCandidates);
            } else if (userChoice == 'c' || userChoice == 'C') {
                runElection(allCandidates,activeCandidates);
                end = yesOrNoInput("Would you like to exit the program? (yes/no)");
            }
        }

    }
}
