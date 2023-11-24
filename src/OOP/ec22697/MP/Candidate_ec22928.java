package OOP.ec22697.MP;// File Candidate_ec22928.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22928 extends Candidate
{

    public static void main(String[] args) {

        //gets all candidates
        Candidate[] candidateA = A3.getCandidateArray();
        //creates array for the candidates added
        Candidate[] electedA = new Candidate[candidateA.length];
        int index = 0;

        //loop until exit is chosen
        boolean cont = true;
        while (cont == true) {
            String choice = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election d) exit?");
            if (choice.equals("a")) {
                String username = inputString("Please enter a username.");
                Candidate c = A3.getByUsername(username, candidateA);
                electedA[index] = c;
                index++;
            } else if (choice.equals("b")) {
                Random rand = new Random();
                int c = rand.nextInt(candidateA.length);
                electedA[index] = candidateA[c];
                index++;
            } else if (choice.equals("c")) {

                String counterUsername = inputString("Who should count the votes?");
                Candidate counter = A3.getByUsername(counterUsername, candidateA);
                int numOfVotes = Integer.parseInt(inputString("How many times shall we run the election?"));
                //create array to hold everyones votes
                Candidate[] votesA = new Candidate[candidateA.length];
                //array to store winners
                Candidate[] winners = new Candidate[numOfVotes];
                for (int i = 0; i < numOfVotes; i++) {
                    for (int j = 0; j < candidateA.length; j++) {
                        votesA[j] = candidateA[j].vote(electedA);
                    }

                    winners[i] = counter.selectWinner(votesA);
                }

                //calculating the winner with the most vote
                Candidate winner = winners[0];
                if (winners.length != 0) {
                    winner = winners[0];
                    int hCount = 0;
                    int count = 0;

                    for (int i = 0; i < winners.length; i++) {
                        for (int j = 0; j < winners.length; j++) {
                            if (winners[i] == winners[j]) {
                                count++;
                            }
                        }
                        if (count > hCount) {
                            hCount = count;
                        }
                        winner = winners[i];
                    }

                    System.out.println("Most common winner is " + winner.getName());
                } else if (choice.equals("d")) {
                    cont = false;
                }
            }

        }
    }

        public static String inputString(String message)
        {
            String ans;
            Scanner scanner = new Scanner(System.in);
            System.out.println(message);
            ans=scanner.nextLine();
            return ans;
        }


        public String getName() {
        return "Seagull";
    }

        public String getSlogan() {
        return "Mine mine mine";
    }

        public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22928();

        if (candidates.length != 0) r = candidates[0];

        //vote for a random canidate
        Random rand = new Random();
        int i = rand.nextInt(candidates.length);
        r=candidates[i];

        return r;
    }

        public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22928();

        //Selects the highest voted canidate
        if (votes.length != 0) r = votes[0];

        int hCount=0;
        int count=0;

        for ( int i=0; i<votes.length;i++)
        {
            for ( int j=0; j<votes.length;j++)
            {
                if (votes[i]==votes[j])
                {
                    count++;
                }
            }
            if (count>hCount)
            {
                hCount=count;
            }
            r=votes[i];
        }

        return r;
    }

    }
