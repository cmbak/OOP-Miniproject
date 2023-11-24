package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22906 extends Candidate {

    public String getName() {
        return "Adewale";
    }

    public String getSlogan() {
        return "Damn bro it's ntd...";
    }

    public static void main(String[] args) {
        Candidate[] allCandidates = A3.getCandidateArray(); //get one of all the candidates in a3
        Candidate[] votees = new Candidate[allCandidates.length]; //array at max size to add candidates to
        int counter = 0;//keep track of number of candidates on votees
        String userInput = inputString("Would you like to (a) add a specific candidate (b) add a candidate at random (c) run the election?").toLowerCase();
        
        if (userInput.equals("a")){
            String usernameInput = inputString("Please enter the username: ");//gets username of candidate to add
            Candidate newVotee = A3.getByUsername(usernameInput, allCandidates);
            votees[counter] = newVotee;//adds to array
            counter++; //increments counter
            printCanArray(votees, counter);
        
        } else if (userInput.equals("b")) {
            boolean alreadyThere = true;
            
            while (alreadyThere == true) { //checks each candidate doesn't vote more than once
                int rando = randomInt(allCandidates.length-1);
                alreadyThere = checkVotes(allCandidates[rando], votees);
                
                if (alreadyThere == false) {
                    Candidate chosen = allCandidates[rando];
                    votees[counter] = chosen;//adds to array
                    counter++;
                    printCanArray(votees, counter);
                }
            }
        
        } else if (userInput.equals("c")) {
            String voteInput = inputString("Who will count the votes?");
            Candidate voteRunner = A3.getByUsername(voteInput, allCandidates);
            int loops = checkInput("How many times do you want to run the election?");
            runElection(votees, counter, loops, voteRunner, allCandidates);
        }
    }

    public static void runElection(Candidate[] votees, int counter, int loops, Candidate voteRunner, Candidate[] allCandidates) {
        Candidate[] actualCandidates = new Candidate[counter];//this bit sets up an array without empty spaces, filled with candidates
        
        for (int i = 0; i<=counter; i++){
            actualCandidates[i] = votees[i];
        }

        Candidate[] winningCandidates = new Candidate[loops]; //holds all winners of votes
        Candidate[] allTempVotes = new Candidate[actualCandidates.length];//holds what everyone votes for each time
        
        for (int i = 0; i<loops; i++) {
            
            for (int j = 0; j < allCandidates.length; j++) {
                allTempVotes[j] = allCandidates[i].vote(actualCandidates);
            }
            winningCandidates[i] = voteRunner.selectWinner(allTempVotes);//gets what the winning candidate this round
        }
        
        Candidate finalWinner = voteRunner.selectWinner(winningCandidates);//gets winner over all rounds
        print("The winner is " + finalWinner.getName() + " with the slogan " + finalWinner.getSlogan());
    }

    public static boolean checkVotes (Candidate toCheck, Candidate[] votees) {
        boolean alreadyThere = false;
        for (int i = 0; i<votees.length; i++) {
            if (toCheck.equals(votees[i])) {
                alreadyThere = true;
            }
        }
        return alreadyThere;
    }

    public static void printCanArray(Candidate[] votees, int counter) {
        
        print("Candidates so far: ");
        
        for (int i = 0; i<=counter; i++) {
            Candidate printing = votees[i];
            print((i+1) + ". " + printing.getName() + " with the slogan " + printing.getSlogan());
        }
    
    }

    public Candidate vote(Candidate[] candidates) {
        
        Candidate r = new Candidate_ec22906();
        
        if (candidates.length == 0) //if nothing in there, pass what we just made
            return r;

        if (candidates.length != 0) r = candidates[0];

        // Add code that inspects the array candidates
        // calling the various methods of the Candidate objects
        // and finds a Candidate object to vote for.
        
        for (int i=0; i<candidates.length; i++){ //similar slogan
            
            if (candidates[i].getSlogan().equals("Damn bro it's ntd..."))
                return candidates[i];
        }
        
        for (int i=0; i<candidates.length; i++) { //friend
            if (candidates[i].getName().equals("Adewale")) {
                return candidates[i];
            }
        }

        return r; // all else fails just return this class
    }

    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22906();

        if (votes.length != 0) r = votes[0];
        if (votes.length == 0) { //if nothing there, return this class
            return r;
        }

        // Add code that either counts the votes
        // or uses some other way to return an element 
        // of the array vote as the winner of an election.

        int highestCount = 0;
        Candidate winner = votes[0];
        
        for (int i =0; i< votes.length; i++) {
            int count = 0;
            
            for (int j = 0; j<votes.length;j++)
                if (votes[i] == votes[j])
                    count++;

            if (count >= highestCount) {
                highestCount = count;
                winner = votes[i];
            }
        }
        return winner;
    }

    public static void print(String print) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println(print);
    }

    public static int checkInput(String questionToAsk)//asks and checks its an int
    {
        Scanner keyboard = new Scanner(System.in);
        print(questionToAsk);
        boolean integerAns = false;
        int userAnswer = 0;
        
        while (integerAns == false)
        {
            try
            {
                userAnswer = Integer.parseInt(keyboard.nextLine());
                integerAns = true;
            }
            catch(Exception e)
            {
                print("Please enter an integer.");
            }
        }
        return userAnswer;
    }

    public static String inputString(String questionToAsk)
    {
        Scanner keyboard = new Scanner(System.in);
        String userInput;
        print(questionToAsk);
        userInput = keyboard.nextLine();
        return userInput;
    }

    public static int randomInt(int bound) {
        Random r = new Random();
        return r.nextInt(bound);
    }
}
