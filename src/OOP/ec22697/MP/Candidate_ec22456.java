package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;
class Candidate_ec22456 extends Candidate {

    public String getName() {
        return "Erik";
    }

    public String getSlogan() {
        return "The Future is Socialist!";
    }

    public Candidate vote(Candidate[] candidates) {

        if (candidates.length == 0) {
            return new Candidate_ec22456();
        }

        for (Candidate c : candidates)
            if (c.getName().equals("Erik"))
                return c;

        return candidates[0];
    }

    public Candidate selectWinner (Candidate[] votes) {
        if (votes.length == 0)
            return new Candidate_ec22456();

        Random random = new Random();
        int randomCandidate = random.nextInt(votes.length);
        return votes[randomCandidate];
    }

    public static void main(String[] a) {
        int exit = 0; int iterations = 0;
        Candidate[] runningCandidates = new Candidate[A3.getCandidateArray().length];
        Candidate[] allCandidates = A3.getCandidateArray();
        Random r = new Random();

        Scanner sc = new Scanner(System.in);

        while (exit == 0) {
            int answered = 0;
            System.out.println("Candidates are: ");

            if (runningCandidates[0] == null) System.out.println("None");

            else{
                for (int i = 0; i < iterations;  i++)
                    System.out.println((i + 1) + ". " + runningCandidates[i].getName());
            }

            System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election d) exit the program?");
            String answer = sc.nextLine();

            while(answered == 0) {
                if (answer.equals("a")){
                    System.out.println("Please enter a username: ");
                    String id = sc.nextLine();

                    Candidate newCandidate = A3.getByUsername(id, allCandidates);
                    runningCandidates[iterations] = newCandidate;
                    iterations++;
                    answered = 1;
                }

                if (answer.equals("b")){
                    int randomCandidate = r.nextInt(allCandidates.length);
                    runningCandidates[iterations] = allCandidates[randomCandidate];
                    iterations++;
                    answered = 1;
                }

                if (answer.equals("c")){
                    System.out.println("Who should count the votes?: ");
                    String userInput = sc.nextLine();
                    Candidate counter = A3.getByUsername(userInput, allCandidates);

                    System.out.println("How many times should the election be run?");
                    String temp = sc.nextLine();
                    int times = 0;
                    try {
                        times = Integer.parseInt(temp);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid");
                        return;
                    }

                    Candidate[] votes = new Candidate[times * allCandidates.length];

                    for (int i = 0; i < times; i++) {
                        for (int j = 0; j < allCandidates.length; j++) {
                            try {
                                votes[(i * allCandidates.length) + i] = allCandidates[i].vote(runningCandidates);
                            }
                            catch (Exception e){
                                votes[(i * allCandidates.length) + i] = new Candidate_ec22456();
                            }
                        }
                    }

                    Candidate winner = counter.selectWinner(votes);
                    System.out.println("The most common winner is: " + winner.getName());
                    answered = 1;
                }

                if (answer.equals("d")) {
                    return;
                }

                if (!answer.equals("a") && !answer.equals("b") && !answer.equals("c")){
                    System.out.println("Please choose a valid input.");
                }
            }
        }
        return;
    }
}

