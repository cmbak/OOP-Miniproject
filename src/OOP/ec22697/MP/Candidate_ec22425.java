package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22425 extends Candidate
{
    public String getName()
    {
        return "Big Man";
    }
    public String getSlogan()
    {
        return "Keep Playing";
    }

    public Candidate vote(Candidate[] candidates)
    {
        if (candidates.length==0)
            return new Candidate_ec22440();

        for (Candidate c : candidates)
            if (c.getSlogan().equals("Keep playing"))
                return c;

        for (Candidate c : candidates)
            if(c.getName().equals("Isaac"))
                return c;

        return candidates[0];
    }

    public Candidate selectWinner(Candidate[] votes)
    {
        if (votes.length==0)
            return new Candidate_ec22440();

        Candidate currentWinner = votes[0];

        int highestCount = 0;
        for (Candidate c : votes)
        {

            int count = 0;
            for (Candidate x : votes)
                if (x == c)
                    count++;
            if (count > highestCount)
            {
                highestCount = count;
                currentWinner = c;
            }
        }
        return currentWinner;
    }

    public static String inputString(String message)
    {
        String outcome;

        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        outcome = scanner.nextLine();

        return outcome;
    }

    public static int inputInt(String message)
    {
        int outcome;

        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        outcome = scanner.nextInt();

        return outcome;
    }

    // Adapted from Candidate_ec22486.java

    public static void main(String[] args)
    {
        // Get al candidates from A3
        Candidate[] allCandidates = A3.getCandidateArray();

        // create new array the same size as the array of candidates
        Candidate[] votes = new Candidate[allCandidates.length];

        int canCounter = 0;

        String option = (inputString("Would you like to \na) add a specific candidate \nb) add a candidate at random " +
                        "\nc) run the election?").toLowerCase());

        if (option.equals("a")){
            //Gets the username of candidate to add
            String username = inputString("Please enter a username.");

            Candidate newCandidate = A3.getByUsername(username, allCandidates);
            votes[canCounter] = newCandidate;

            canCounter++;
            printCandidates(votes, canCounter);
        } else if (option.equals("b")){

            Random random = new Random();
            int randomInt = random.nextInt(allCandidates.length);

            votes[canCounter] = allCandidates[randomInt];
            canCounter++;

            printCandidates(votes, canCounter);
        } else if (option.equals("c")){
            String voteCounter = inputString("Who should count the votes?");
            Candidate Counter = A3.getByUsername(voteCounter, allCandidates);
            int howManyTimes = inputInt("How many times shall we run the election?");

            //Calls the method that would run the election
            runElection(votes, canCounter, howManyTimes, Counter);
        }

    }
    public static void printCandidates(Candidate[] candidates, int canCounter) {
        //Prints all the candidates in the current election
        System.out.println("Candidates are: ");
        for (int i = 0; i < canCounter; i++) {
            System.out.println(candidates[i].getName());
        }
        return;
    }

    public static void runElection(Candidate[] votes, int counter, int howManyTimes, Candidate voteCounter){
        //Creates an array filled with candidates
        Candidate[] newElection = new Candidate[counter];
        for (int i = 0; i < counter; i++) {
            newElection[i] = votes[counter];
        }

        Candidate[] winningCandidates = new Candidate[howManyTimes * (counter - 1)];
        //Repeat as many times as the user requests
        for (int i = 0; i <= howManyTimes; i++) {
            for (int j = 0; j < counter; j++) {
                    winningCandidates[(counter * i) + j] = newElection[j].vote(newElection);
            }
        }
        //Stores the winning candidate
        Candidate actualWinner = voteCounter.selectWinner(winningCandidates);
        //Prints out the winner
        System.out.println("Most common winner is: " + actualWinner.getName());
    }

}
