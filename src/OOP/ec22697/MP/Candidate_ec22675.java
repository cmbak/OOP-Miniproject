package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;

// File Candidate_ec22675.java
//
// Machine generated stub for Assignment 2

class Candidate_ec22675 extends Candidate {

    public static void main(String[] args) {
        boolean quit = false;
        Candidate[] candidates = A3.getCandidateArray();
        Candidate[] noms = new Candidate[candidates.length];
        int nomCount = 0;
        

        while (!quit) {
            String option = getStringWithMsg("Would you like to a) " +
                "add a specific candidate b) add a candidate at random c) run the election?");

            if (option.equals("a")) {
                // Add based on username
                String username = getStringWithMsg("Please enter a username.");
                System.out.println("Adding " + username);
                Candidate candidateToAdd = A3.getByUsername(username, candidates) ;
                System.out.println(candidateToAdd);

                noms[nomCount] = candidateToAdd;
                nomCount++;
                showCandidates(noms, nomCount);
                
            } else if (option.equals("b")) {
                Candidate randomCandidate = getRandomCandidate(candidates);
                noms[nomCount] = randomCandidate;
                nomCount++;
                showCandidates(noms, nomCount);

            } else if (option.equals("c")) {
                String usernameToCount = getStringWithMsg("Who should count the votes?");
                Candidate counter = A3.getByUsername(usernameToCount, candidates);
                int repeats = getIntegerWithMsg("Enter the number of repeats");
                Candidate[] votes = new Candidate[repeats * (nomCount - 1)];

                for (int j = 0; j <= repeats; j++) {
                    for (int i = 0; i < nomCount; i++) {
                        if (noms[i] == null) {
                            continue;
                        }

                        // inspired from ec22562
                        try {
                            votes[(nomCount * j) + i] = noms[i].vote(noms);
                        } catch (Exception e) {
                            // If vote doesn't work/ the person is null
                            votes[i] = new Candidate_ec22675();
                        }
                    }
                }
        Candidate winner = counter.selectWinner(votes);

        System.out.println("Most common winner is: " + winner.getName());

                quit = true;
            } else { 
                System.out.println("Select a valid option");
            }
        }
    }

    public static int getIntegerWithMsg(String message) {
        System.out.println(message);
        return Integer.parseInt(new Scanner(System.in).nextLine());
    }

    public static Candidate getRandomCandidate(Candidate[] cs) {
        Random random = new Random();
        int randInt = random.nextInt(cs.length - 1);
        return cs[randInt];

    }

    public static void showCandidates(Candidate[] noms, int nomCount) {
        System.out.println("Candidates are:");
        for (int i = 0; i < nomCount; i++) {
            System.out.println(i + 1 + ": " + noms[i].getName());
        }
    }

    public static String getStringWithMsg(String msg) {
        System.out.println(msg);
        return new Scanner(System.in).nextLine();
    }

    // This is an edit made remotely
    public String getName() {
        return "Sully";
    }
    
    public String getSlogan() {
        return "Mashalah brother!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        if (candidates.length == 0) {
            // if empty return myself
            new Candidate_ec22675();
        }

        Candidate selected = candidates[0];
        
        for (Candidate c : candidates) {
            // look for me or abdullah
            if(c.getName().equals("Sully") || c.getName().equals("Abdullah")) {
                selected = c;
            // if not found then look for someone who has mashalah as their slogan
            } else if (c.getSlogan().equals("Mashalah")) {
                selected = c;
            } 
        }
        return selected;
    }
   
    // inspired by the example on jhub
    public Candidate selectWinner(Candidate[] votes) {
        if (votes.length == 0) {
            return new Candidate_ec22675();
        }

        Candidate winner = votes[0];
        int largest = 0;
        int count = 0;

        for (int i = 0; i < votes.length; i++) {
            for (int j = 0; j < votes.length; j++) {
                if (votes[i] == votes[j]) {
                    count++;
                }
            }
            if (count > largest) {
                winner = votes[i];
                largest = count;
            }
            count = 0;
        }
        return winner;
    }
    
}
