package OOP.ec22697.MP;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Candidate_ec22501 extends Candidate {
    public static void main(String[]args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Candidate> candidates = new ArrayList<Candidate>();
        boolean check = true;
        Candidate[] allContributions = A3.getCandidateArray();


        while(check) {

            System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");

            String response = scanner.nextLine();

            if(response.equals("a")) {
                System.out.println("Please enter a username.");

                String username = scanner.nextLine();

                Candidate x = A3.getByUsername(username, allContributions);

                candidates.add(x);

                System.out.println("Candidates are");

                for(int i = 0; i < candidates.size(); i++) {
                    System.out.println((i+1) + "." + candidates.get(i).getName());
                }
            }
            else if(response.equals("b")){
                int max = allContributions.length;
                Random r = new Random();

                candidates.add(allContributions[r.nextInt(max)]);

                System.out.println("Candidates are");

                for(int i = 0; i < candidates.size(); i++) {
                    System.out.println((i+1) + "." + candidates.get(i).getName());
                }
            }
            else if(response.equals("c")) {
                boolean checker = true;
                while(checker)
                {
                    System.out.println("Who should count the votes?");

                    String judge = scanner.nextLine();

                    Candidate counter = A3.getByUsername(judge, allContributions);

                    System.out.println("How many times shall we run the election?");

                    int runElection = Integer.parseInt(scanner.nextLine());

                    Candidate[] candidateArray = candidates.toArray(new Candidate[0]);

                    for(int i = 0; i < runElection; i++) {
                        counter.vote(candidateArray);
                    }

                    System.out.println("Most common winner is " + counter.selectWinner(candidateArray).un + ".");

                    System.out.println();
                    System.out.println("Would you like to ");
                    System.out.println("a) exit ");
                    System.out.println("b) Run the election again");

                    String choice = scanner.nextLine();

                    if(choice.equals("a")) {
                        checker = false;
                    }
                    else if(choice.equals("b")) {
                        checker = true;
                    }
                    else {
                        System.out.println("Invalid Input. END.");
                        System.exit(0);
                    }
                }
            }
            else
            {
                System.out.println("Invalid Input");
            }
        }
    }

    public String getName() {
        return "Mason";
    }

    public String getSlogan() {
        return "I just love computers";
    }

    public Candidate vote(Candidate[] candidates) {

        // If array is empty, return instance of friend's class.
        if (candidates.length == 0) {
            return new Candidate_ec211030();
        }

        // Search for a like minded candidate.
        for (Candidate c : candidates) {
            if (c.getSlogan().equals("More trees!")) {
                return c;
            }
        }

        // Otherwise, search for a friend.
        for (Candidate c : candidates) {
            if (c.getName().equals("Dainius")) {
                return c;
            }
        }

        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }

    public Candidate selectWinner(Candidate[] votes) {

        final int zero = 0;
        int highestCount = 0;
        int count = 0;

        // If array is empty, return instance of friend's class.
        if (votes.length == zero) {
            return new Candidate_ec22501();
        }

        // Default to first vote, but this will be over-written.
        Candidate currentWinner = votes[zero];

        // Count the votes for each object in the array,
        // selecting one with the most.

        for (Candidate c : votes) {

            for (Candidate x : votes) {
                if (x == c) {
                    count++;
                }
            }
            if (count > highestCount) {
                highestCount = count;
                currentWinner = c;
            }
        }

        return currentWinner;
    }

}
