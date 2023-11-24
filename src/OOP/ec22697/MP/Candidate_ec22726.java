package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;

public class Candidate_ec22726 extends Candidate {
    public String name;
    public String slogan;

    public Candidate_ec22726() {
        this.name = "Noor";
        this.slogan = "More Opportunities";
    }

    public Candidate_ec22726(String name, String slogan) {
        this.name = name;
        this.slogan = slogan;
    }

    public String getName() {
        return name;
    }

    public String getSlogan() {
        return slogan;
    }

    public Candidate vote(Candidate[] candidates) {
        if (candidates.length == 0) {
            return new Candidate_ec22726();
        } else {
            Random rand = new Random();
            int ran = rand.nextInt(candidates.length);
            return candidates[ran];
        }

    }

    public Candidate selectWinner(Candidate[] votesCast) {

        if (votesCast.length == 0) {
            return new Candidate_ec22726();
        } else {
            Random r = new Random();
            int c = r.nextInt(votesCast.length);
            return votesCast[c];
        }
    }

    public static void main(String[] args) {
        boolean caseRunning = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Election simulator has started..");

        System.out.print("Please enter the number of candidates: ");
        int numCandidates = scanner.nextInt();

        String[] candidateNames = new String[numCandidates];
        String[] candidateSlogans = new String[numCandidates];
        String choice = "";
        while(caseRunning){
            System.out.println("menu: a) Add a Candidate, b) Run the election, c) Exit");
            choice = scanner.next();
            if(choice.equals("a")){
                for (int i = 0; i < numCandidates; i++) {
                    System.out.print("Name of candidate #" + (i + 1) + ": ");
                    candidateNames[i] = scanner.next();

                    System.out.print("Slogan of candidate #" + (i + 1) + ": ");
                    candidateSlogans[i] = scanner.next();
                }
                System.out.println("Candidates have been added.");

            } else if(choice.equals("b")){
                System.out.print("How many voters?");
                int numVoters = scanner.nextInt();

                int[] voteCounts = new int[numCandidates];

                for (int i = 0; i < numVoters; i++) {
                    int vote = getRandomIntInRange(0, numCandidates - 1);
                    voteCounts[vote]++;
                }

                int maxVotes = getMaxValue(voteCounts);
                int winningCandidateIndex = getIndexWithMaxValue(voteCounts);

                if (countOccurrences(maxVotes, voteCounts) > 1) {
                    System.out.println("A tie between the candidates!");
                } else {
                    System.out.println("The winner is " + candidateNames[winningCandidateIndex] + " with " + maxVotes + " votes!");
                }
                System.out.println("Election completed.");
            } else if (choice.equals("c")){
                caseRunning = false;
            }
            else{
                System.out.println("Please type one of the option (a, b, or c)");
            }
        }
        System.out.println("Thanks for using the simulator!");
    }

    public static int getRandomIntInRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static int getMaxValue(int[] arr) {
        int maxValue = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maxValue) {
                maxValue = arr[i];
            }
        }
        return maxValue;
    }


    public static int getIndexWithMaxValue(int[] arr) {
        int maxValue = getMaxValue(arr);
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == maxValue) {
                if (index != -1) {
                    // There is a tie between candidates
                    return -1;
                }
                index = i;
            }
        }
        return index;
    }

    public static int countOccurrences(int num, int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                count++;
            }
        }
        return count;
    }
}
