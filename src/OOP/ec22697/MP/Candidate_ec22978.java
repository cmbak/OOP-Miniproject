package OOP.ec22697.MP;// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;


class Candidate_ec22978 extends Candidate {

    public String getName() {
        return "Dainius Zukas";
    }

    public String getSlogan() {
        return "I miss the dark zone";
    }

    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22978();

        if (candidates.length != 0) r = candidates[0];

        // First search for Mason on the list of candidates.
        for (Candidate c : candidates)
            if (c.getName().equals("Mason"))
                return c;

        // First search for like-minded on the list of candidates.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("Let him cook!"))
                return c;

        // Otherwise, choose at random from list.
        Random rand = new Random();
        int vote = rand.nextInt(candidates.length);
        return candidates[vote];

    }

    public Candidate selectWinner(Candidate[] voted) {
        if (voted.length == 0)
            return new Candidate_ec22978();

        Candidate currentWinner = voted[0];
        int highest = 0;

        for (Candidate c : voted) {
            int count = 0;
            for (Candidate v : voted) {
                if (v == c) {
                    count++;
                }
            }
            if (count > highest) {
                highest = count;
                currentWinner = c;
            }
        }
        return currentWinner;
    }

    //Method used to print methods (easier than typing out System.out.println constantly
    public static void print(String message){
        System.out.println(message);
    }

    //Method to read a string input after taking in a question as an argument
    public static String inputString(String question) {
        Scanner scan = new Scanner(System.in);
        print(question);
        return scan.nextLine();
    }

    //Method to read an integer input, making sure it is positive
    //if negative it will loop till positive

    public static int inputInt(String message) {
        Scanner scan = new Scanner(System.in);
        int number = 0;
        boolean numberValid = false;

        while (!numberValid) {
            System.out.println(message);
            if (scan.hasNextInt() && (number = scan.nextInt()) >= 0) {
                return number;
            } else {
                System.out.println("Not a valid number");
                scan.next();
            }
        }
        return number;
    }

    public static Candidate mostVoted(Candidate[] voted) {

        Candidate currentWinner = voted[0];
        int countHighest = 0;
        for (Candidate c : voted) {
            int count = 0;
            for (Candidate v : voted) {
                if (v == c) {
                    count++;
                }
            }
            if (count > countHighest) {
                countHighest = count;
                currentWinner = c;
            }
        }
        return currentWinner;
    }

    private static Candidate selectCandidate(Candidate[] currentElection, int count, Candidate[] allCandidates) {

        String candidateName = inputString("Enter a Candidate Name");
        Candidate candidate = A3.getByUsername(candidateName, A3.getCandidateArray());
        if (candidate == null){
            print("Unable to add Candidate");
            candidate = selectCandidate(currentElection, count, allCandidates);
        }
        else{
            currentElection[count] = candidate;
            count+=1;
            StringBuilder Output = new StringBuilder("Added Candidate\nCurrent list of candidates : ");
            for (int i=0; i<count; i++){
                Output.append(currentElection[i].getName()).append(", ");
            }
            print(Output.toString());
        }
        return candidate;

    }

    private static Candidate addRandomCandidate(Candidate[] currentElection, int count, Candidate[] allCandidates){
        Random rand = new Random();
        Candidate candidate = allCandidates[rand.nextInt(allCandidates.length)];
        currentElection[count] = candidate;
        count+=1;
        StringBuilder Output = new StringBuilder("Added Candidate\nCurrent list of candidates : ");
        for (int i=0; i<count; i++){
            Output.append(currentElection[i].getName()).append(", ");
        }
        print(Output.toString());
        return candidate;

    }

    public static void runRandomElection(Candidate[] currentElection, int count) {
        Random rand = new Random();
        StringBuilder Output = new StringBuilder("List of Current Votes:\n");

        int randomVotes = inputInt("How many random votes?");

        Candidate[] voted = new Candidate[randomVotes];
        for (int i = 0; i < randomVotes; i++){
            voted[i] = currentElection[rand.nextInt(count)];
        }

        for (int j=0; j<randomVotes; j++){
            if (voted[j]!=null) {
                Output.append(", ").append(voted[j].getName());
            }
        }
        Candidate Winner = mostVoted(voted);

        print(Output+"\n\n With the Final Winner(s) of :"+Winner.getName()+", '"+Winner.getSlogan()+"'");

    }

    public static void main(String [] args) {
        Candidate[] allCandidates = A3.getCandidateArray();

        int numCandidates = inputInt("How many candidates would you like to participate?");
        print("Currently there are 0 candidates please add them using options 1 or 2.");
        Candidate[] currentElection = new Candidate[numCandidates];
        int count = 0;
        boolean running = true;

        while (running) {
            int input = inputInt("What would you like to do: \n 1) add a specific candidate 2) add a candidate at random 3) run the election");
            if (count < numCandidates) {
                if (input==1) {
                    currentElection[count] = selectCandidate(currentElection, count, allCandidates);
                    count = count + 1;
                } else if (input==2) {
                    currentElection[count] = addRandomCandidate(currentElection, count, allCandidates);
                    count = count + 1;
                }
            } else if (input==3) {
                runRandomElection(currentElection, count);
                running = false;
            }

        }
    }
}
