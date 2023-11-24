package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22816 extends Candidate {

    public static void main(String[] args) {
        final Candidate[] all = A3.getCandidateArray();
        Candidate[] candidates = new Candidate[0];
        String choice = "";
        while(!choice.equalsIgnoreCase("d")) {
            print("-=-=[ Election Simulator 1.0 ]=-=-");
            choice = input("| What would you like to do?     |" +
                    "\n|   a) add specific candidate(s) |" +
                    "\n|   b) add random candidate(s)   |" +
                    "\n|   c) run the election          |" +
                    "\n|   d) exit                      |" +
                    "\n-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-");
            if(choice.equalsIgnoreCase("a")) {
                candidates = addCandidate(all);
                print("The following candidates are in the election:");
                if(candidates != null && candidates.length > 0)
                    for(Candidate candidate : candidates)
                        if(candidate != null)
                            print(candidate.getName() + " : " + candidate.getSlogan());
                        else
                            print("Candidate cannot be null!");
                else
                    print("Candidates list can't be empty!");
            }
            else if(choice.equalsIgnoreCase("b")) {
                candidates = addRandomCandidate(all);
                print("The following candidates are in the election:");
                if(candidates != null && candidates.length > 0)
                    for (int i = 0; i < candidates.length; i++)
                        if(candidates[i] != null)
                            print(i+1 + ". " + candidates[i].getName() + " [Slogan: " + candidates[i].getSlogan() + "]");
                        else
                            print("Candidate cannot be null!");
                else
                    print("Candidates list can't be empty!");
            }
            else if(choice.equalsIgnoreCase("c")) {
                if(candidates != null) {
                    if(candidates.length == 0) {
                        print("Candidates list is empty! Add some candidates, then try again.");
                        return;
                    }
                    int reps = inputInt("How many times should the election run?");
                    if(reps <= 0) {
                        print("Election must be run a positive number of times! (> 0)");
                        return;
                    }
                    Candidate most = getMost(getWinners(reps, candidates, all));
                    print("Most common winner is:");
                    try {
                        print(most.getName() + " [Slogan: " + most.getSlogan() + "]");
                    } catch(Exception e) {
                        print("Most common winner is invalid! (Are you sure you added candidates?)");
                    }
                } else {
                    print("Fatal error. Candidates list is null.");
                }
            }
            else if(choice.equalsIgnoreCase("d")) {
                print("Goodbye! :)");
            }
        }
    }

    // Find winners using provided selector, return array of all repetitions' winners
    public static Candidate[] getWinners(int reps, Candidate[] candidates, Candidate[] all) {
        Candidate[] winners = new Candidate[reps];
        Candidate[] votes = new Candidate[all.length];
        Candidate candidate = A3.getByUsername(input("Who should count the votes?"), all);
        for(int i = 0; i < reps; i++)
            for(int j = 0; j < all.length; j++) {
                try {
                    votes[j] = all[j].vote(candidates);
                } catch(Exception e) {
                    print("Fatal error occured. (Invalid vote method)");
                }
            try {
                winners[i] = candidate.selectWinner(votes);
            } catch(Exception e) {
                print("Fatal error occured. (Invalid selectWinner method)");
            }
        }
        return winners;
    }

    // Get most frequent candidate from array of winners
    public static Candidate getMost(Candidate[] winners) {
        Candidate most = null;
        int highest = 0;
        for(Candidate c : winners) {
            int count = 0;
            for(Candidate x : winners)
                if (x == c)
                    count++;
            if(count >= highest) {
                highest = count;
                most = c;
            }
        }
        return most;
    }

    // Add user-inputted candidate(s) to array and return the list
    public static Candidate[] addCandidate(Candidate[] candidates) {
        int size = inputInt("Enter the number of candidates you wish to add:");
        if(size <= 0) {
            System.out.println("Number of candidates must be positive.");
            return null;
        }
        Candidate[] candis = new Candidate[size];
        for(int i = 0; i < size; i++) {
             Candidate candidate = A3.getByUsername(input("Enter username:"), candidates);
             if(candidate != null)
                 candis[i] = candidate;
             else
                 print("Invalid input! Candidate can't be null.");
        }
        return candis;
    }

    // Add random candidate(s) to array and return list
    public static Candidate[] addRandomCandidate(Candidate[] candidates) {
        int size = inputInt("Enter the number of candidates you wish to add:");
        if(size <= 0) {
            System.out.println("Number of candidates must be positive.");
            return null;
        }
        Candidate[] candis = new Candidate[size];
        Random r = new Random();
        for(int i = 0; i < size; i++) {
            Candidate candidate = A3.getCandidateArray()[r.nextInt(candidates.length)];
            if(candidate != null)
                candis[i] = candidate;
            else
                print("Error: Null candidate found.");
        }
        return candis;
    }

    // Prompt user to provide integer. Parse valid
    // or catch invalid values. Return input.
    public static int inputInt(String message) {
        Scanner sc = new Scanner(System.in);
        print(message);
        int input = -1;

        try {
            input = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            print("Invalid input! Must be integer.");
        }

        return input;
    }

    // Prompt user to provide String, return input
    public static String input(String message) {
        Scanner sc = new Scanner(System.in);
        print(message);
        return sc.nextLine();
    }

    // Print String message to screen
    public static void print(String message) {
        System.out.println(message);
    }

    public String getName() {
        return "Elon Musk";
    }

    public String getSlogan() {
        return "A ceiling is simply a floor from below";
    }

    public Candidate vote(Candidate[] candidates) {
        // If the array is empty,
        // return instance of this class
        if(candidates.length == 0)
            return new Candidate_ec22816();

        // Vote for candidate with slogan
        // containing Mars
        for(Candidate c : candidates)
            if(c.getSlogan().contains("Mars"))
                return c;

        // Random selection
        return candidates[new Random().nextInt(candidates.length)];
    }

    public Candidate selectWinner(Candidate[] votes) {
        // If the array is empty,
        // return instance of this class
        if(votes.length == 0)
            return new Candidate_ec22816();

        // Default case: first candidate
        Candidate winner = votes[0];

        // Find the highest no. of votes
        // and select as winner
        int highest = 0;
        for(Candidate c : votes) {
            int count = 0;
            for(Candidate a : votes)
                if(a == c)
                    count++;
            if(count >= highest) {
                highest = count;
                winner = c;
            }
        }

        return winner;
    }
}
