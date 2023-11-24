package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;

class Candidate_ah21407 extends Candidate {
    
    public static void main(String[] args) {
        final Candidate[] EVERYONE = A3.getCandidateArray();
        Candidate[] votes = new Candidate[EVERYONE.length];

        // Enter candidates
        int count = inputInt("How many candidates do you wish to enter?");
        Candidate[] candidates = new Candidate[count];
        for (int i = 0; i < count; i++) {
            String username = inputString("Enter candidate username: ");
            Candidate c = A3.getByUsername(username, EVERYONE);
            if (c == null) {
                print("Not found. Try again.");
                i--;
            }
            else {
                candidates[i] = c;
            }
        }

        // Print candidates
        print("Candidates are:");
        for (Candidate c : candidates) {
            print(c.getName());
        }

        // Decide who counts
        boolean valid = false;
        Candidate counter = new Candidate_ec22424();
        while (!valid) {
            String counterUsername = inputString("Who do you want to count? [enter username]");
            counter = A3.getByUsername(counterUsername, EVERYONE);
            if (counter == null) print("Not found. Try again.");
            else valid = true;
        }

        // Decide how many runs
        boolean positive = false;
        int numOfRuns = 0;
        while (!positive) {
            numOfRuns = inputInt("How many times shall we run the election?");
            if (numOfRuns > 0) positive = true;
            else print("Cannot be negative. Try again.");
        }

        Candidate[] winners = new Candidate[numOfRuns];

        // Run the election
        for (int run = 0; run < numOfRuns; run++) {

            for (int i = 0; i < EVERYONE.length; i++) {
                votes[i] = EVERYONE[i].vote(candidates);
            }

            winners[run] = counter.selectWinner(votes);
        }

        // Print most common winner
        int highestFreq = 0;
        int freq = 0;
        Candidate bigWinner = winners[0];
        for (int i = 0; i < winners.length; i++) {
            freq = 1;
            for (int j = 0; j < winners.length; j++) {
                if (winners[i] == winners[j]) freq++;
            }
            if (freq > highestFreq) {
                highestFreq = freq;
                bigWinner = candidates[i];
            }
        }
        print("The most frequent winner was " + bigWinner.getName());


    }

    // Simple generic print 
    static <T> void print(T message) {
        System.out.println(message);
    }

    // Returns next string from user
    static <T> String inputString(T message) {
        Scanner sc = new Scanner(System.in);

        print(message);

        return sc.nextLine();
    }

    // Returns next int from user
    static <T> int inputInt(T message) {
        Scanner sc = new Scanner(System.in);
        boolean valid = false;
        int i = 0;

        print(message);

        while (!valid) {
            try {
                i = Integer.parseInt(sc.nextLine());
                valid = true;
            }
            catch(Exception e) {
                System.out.println("Invalid input.");
            }
        }
        return i;
    }

    public String getName() {
        return "John Cena";
    }

    public String getSlogan() {
        return "You can't see me";
    }

    public Candidate vote(Candidate[] candidates) {

        // First search for self on the list of candidates.
        for (Candidate c : candidates)
            if (c.getName().equals("John Cena"))
                return c;
        
        // Otherwise, default to ramdom candidate on list.
        Random r = new Random();
        int c = r.nextInt(candidates.length);
        return candidates[c];
    }

    public Candidate selectWinner(Candidate[] votesCast) {

        // If array is empty, return instance of this class.
        if (votesCast.length == 0) 
            return new Candidate_ah21407();
        
        // Choose winner at random from votes cast.
        Random r = new Random();
        int c = r.nextInt(votesCast.length);
        return votesCast[c];        
    }

}
