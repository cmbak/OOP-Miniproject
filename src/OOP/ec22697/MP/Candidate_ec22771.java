package OOP.ec22697.MP;//

import java.util.Scanner;
import java.util.Random;

class Candidate_ec22771 extends Candidate {

    public String getName() {
        return "Bas";
    }

    public String getSlogan() {
        return "Bas 4 President";
    }

    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22771();

        if (candidates.length != 0) r = candidates[0];

        if (candidates.length == 0)
            return new Candidate_ec22771();

        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("Bas 4 President"))
                return c;

        // Otherwise, search for my friend.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("More the Rugs"))
                return c;

        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }


    public Candidate selectWinner(Candidate[] votes) {
        if (votes.length == 0)
            return new Candidate_ec22771();

        Candidate currentWinner = votes[0];
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

    //this will ask the user for an input, and verify if the user input is a positive integer and return it
    //if the user enters a letter or negative number, it will loop again until the user enters a positive integer
    public static int inputInt(String message) {
        Scanner sc = new Scanner(System.in);
        int number = 0;
        boolean numberValid = false;

        while (!numberValid) {
            System.out.println(message);
            if (sc.hasNextInt()) {
                number = sc.nextInt();
                if (number < -1) {
                    print("This is not a positive Integer.");
                } else if (0 <= number) {
                    numberValid = true;
                }
            } else {
                print("This is not a number");
                sc.next();
            }
        }
        return number;
    }

    public static Candidate popularWinner(Candidate[] votes) {

        Candidate currentWinner = votes[0];
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

    //takes in a question as an argument that will be asked to the user, and returns the user input as a string
    public static String inputString(String question) {
        String inputString;
        Scanner scanner = new Scanner(System.in);

        print(question);
        inputString = scanner.nextLine();
        return inputString;
    }

    public static void print(String message){
        System.out.println(message);
    }

    public static void main(String [] args){
        final Candidate[] allCandidates = A3.getCandidateArray();
        int numberOfCandidates = inputInt("How many candiates are in this election");
        int candidatesAdded = 0;
        Candidate[] candidateArray = new Candidate[numberOfCandidates];
        boolean running = true;

        while (running){
            String input = inputString("What would you like to do: \n a) add a specific candidate b) add a candidate at random c) run the election");
            if (candidatesAdded < numberOfCandidates){
                if (input.equalsIgnoreCase("a")){
                    candidateArray[candidatesAdded] = getSpecificCandidate();
                    candidatesAdded = candidatesAdded + 1;
                } else if (input.equalsIgnoreCase("b")){
                    candidateArray[candidatesAdded] = getRandomCandidate();
                    candidatesAdded = candidatesAdded + 1;
                }
            } else if (input.equalsIgnoreCase("c")){
                printCandidates(candidateArray);

                int runCount = inputInt("How many times would you like to run this election?");
                String counterInput = inputString("Who is counting the votes?");
                Candidate Counter = A3.getByUsername(counterInput, allCandidates);
                Candidate[] winners = new Candidate[runCount];

                for (int i = 0; i < runCount; i++){
                    winners[i] = getWinner(candidateArray, Counter);
                    System.out.println("The winner of election " + (i + 1) + " is " + winners[i].getName());
                }

                Candidate winner = popularWinner(winners);
                System.out.println("The most popular winner is " + winner.getName() + "!");
                running = false;
            } else if (candidatesAdded == numberOfCandidates){
                print("The election is full. No more candidates can be added");
            }

            else{
                System.out.println("Not a valid choice");
            }
        }
    }

    public static Candidate getWinner(Candidate[] candidates, Candidate Counter) {
        Candidate[] allCandidates = A3.getCandidateArray();
        Candidate[] voteArray = new Candidate[allCandidates.length];
        for(int i = 0; i < A3.getCandidateArray().length; i++) {
            try {
                voteArray[i] = allCandidates[i].vote(candidates);
            } catch(Exception e) {

            }
        }
        return Counter.selectWinner(voteArray);
    }

    public static void printCandidates(Candidate[] candidates) {
        System.out.println("The candidates in the election are are:");
        for(Candidate candidate : candidates) {
            System.out.println(candidate.getName());
        }
    }

    public static Candidate getSpecificCandidate(){
        String candidateName = inputString("Enter Candidate Username");
        Candidate newCandidate = A3.getByUsername(candidateName, A3.getCandidateArray());

        if (newCandidate == null) {
            System.out.println("Candidate was not found.");
        } else {
            System.out.println("Candidate Found");
        }
        return newCandidate;
    }

    public static Candidate getRandomCandidate() {
        Random rand = new Random();
        Candidate[] allCandidates = A3.getCandidateArray();
        return A3.getCandidateArray()[rand.nextInt(allCandidates.length-1)];
    }
}
