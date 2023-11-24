package OOP.ec22697.MP;
// File Candidate_ec22993.java
    //
    // Machine generated stub for Assignment 2

import java.util.Random;
    import java.util.Scanner;

    class Candidate_ec22993 extends Candidate {

        public static void main(String[] args) {
            String choice = giveOptions();
            if (choice.equals("b")) {
                runMultipleElections();
            }
            return;
        }

        public static String giveOptions() {
            String response = inputAnswer("Would you like to \n a) exit \n b) run same election many times");

            return response;
        }

        public static String inputAnswer(String a) {
            System.out.println(a);
            Scanner scanner = new Scanner(System.in);
            String ans = scanner.nextLine();

            return ans;
        }

        public static int inputInt(String b) {
            System.out.println(b);
            Scanner scanner = new Scanner(System.in);
            int answer = scanner.nextInt();
            if (answer < 1) {
                inputInt("");
            }

            return answer;
        }

        public static void runMultipleElections() {
            Candidate[] allPossibleCandidates = A3.getCandidateArray();
            Candidate[] candidatesReady = new Candidate[allPossibleCandidates.length];
            int numCandidates = 0;

            System.out.println("= Running Repeated Elections =");
            System.out.println("Candidates are");

            if (numCandidates == 0) {
                System.out.println("none");
            }

            String choice = "";
            while (!(choice.equals("c"))) {

                choice = inputAnswer("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");

                if (choice.equals("a")) {
                    String newCandidate = inputAnswer("Please enter a username");
                    Candidate toAdd = A3.getByUsername(newCandidate, allPossibleCandidates);
                    candidatesReady[numCandidates] = toAdd;
                    numCandidates = +1;
                }

                else if (choice.equals("b")) {
                    Random R = new Random();
                    int rand = R.nextInt(allPossibleCandidates.length);
                    candidatesReady[numCandidates] = allPossibleCandidates[rand];
                    numCandidates = +1;

                }

            }

            if(numCandidates==0){
                System.out.println("There are no candidates to choose from");
            }
            else{

                int repeats = inputInt("How many times shall we run the election?");
                Candidate[] votes = new Candidate[repeats];
                Random G = new Random();
                for (int i = 0; i < repeats; i++) {
                    int newVote = G.nextInt(numCandidates);
                    votes[i] = candidatesReady[newVote];
                }
    
                Candidate electionWinner = getWinner(votes);
                System.out.println("The winner of the election is " + electionWinner.getName() + " with the slogan of " + electionWinner.getSlogan());
            }

        }

        public String getName() {
            return "Benny";
        }

        public String getSlogan() {
            return "New Day";
        }

        public Candidate vote(Candidate[] candidates) {

            if (candidates.length == 0) {
                return new Candidate_ec22993();
            }

            for (Candidate p : candidates) {
                if (p.getSlogan().equals("More Time")) {
                    return p;
                }
            }

            for (Candidate p : candidates) {
                if (p.getName().equals("Tom")) {
                    return p;
                }
            }

            Random R = new Random();
            int v = R.nextInt(candidates.length);
            return candidates[v];

        }

        public static Candidate getWinner(Candidate[] votes){
            if (votes.length == 0) {
                return new Candidate_ec22993();
            }

            Candidate winner = votes[0];
            int mostVotes = 0;

            for (Candidate z : votes) {
                int numVotes = 0;

                for (Candidate A : votes) {
                    if (A == z) {
                        numVotes += 1;
                    }
                }

                if (numVotes > mostVotes) {
                    mostVotes = numVotes;
                    winner = z;
                }
            }

            return winner; 
        }

        public Candidate selectWinner(Candidate[] votes) {

            if (votes.length == 0) {
                return new Candidate_ec22993();
            }

            Candidate winner = votes[0];
            int mostVotes = 0;

            for (Candidate z : votes) {
                int numVotes = 0;

                for (Candidate A : votes) {
                    if (A == z) {
                        numVotes += 1;
                    }
                }

                if (numVotes > mostVotes) {
                    mostVotes = numVotes;
                    winner = z;
                }
            }

            return winner;
        }

    }

