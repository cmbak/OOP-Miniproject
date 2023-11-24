package OOP.ec22697.MP;

import java.util.Scanner;
import java.util.Random;

// A3 section based off Candidate_ec22586.java
// A3 section also uses code from A3.java

class Candidate_ec22959 extends Candidate {

    // A3 section
    public static void main(String[] args) {
        Candidate[] all = A3.getCandidateArray();
        Candidate[] currentCandidates = new Candidate[all.length];
        int numCandidates = 0;
        boolean exit = false;
        char choice = '?';

        do {
            choice = getChar("\nChoose election type a) no election (exit) " + 
                             "\nb) add specific candidate " +
                             "\nc) add random candidate " + 
                             "\nd) run same election");
            switch (choice) {
                    
              case 'a': exit = true; break;
                    
              case 'b': numCandidates = addCandidate(currentCandidates, all, numCandidates); break;
                    
              case 'c': numCandidates = addRandomCandidate(currentCandidates, all, numCandidates); break;
                    
              case 'd': runElection(currentCandidates, all, numCandidates); break;
                    
              default: pr("Option '"+choice+"' not available.");
            }
          } while (!exit);
    }

    public static void displayCandidates(Candidate[] currentCandidates, int numCandidates) {
        pr("The candidates are: ");
        if (numCandidates == 0) {
            pr("There are ZERO candidates");
        } else {
            for (int i = 0; i < numCandidates; i++) {
                pr((i+1) + ". " + currentCandidates[i].getName() + ": " + currentCandidates[i].getSlogan());
            }
        }
    }

    private static int addCandidate(Candidate[] currentCandidates, Candidate[] all, int numCandidates) {
        String cName = getString("Enter candidate name to be added.");
        Candidate c = A3.getByUsername(cName, all);
        if (c != null) {
            currentCandidates[numCandidates] = c;
            return numCandidates + 1;
        } else {
            pr("Candidate name: " + cName + " NOT FOUND.");
            return numCandidates;
        }
    }

    private static int addRandomCandidate(Candidate[] currentCandidates, Candidate[] all, int numCandidates) {
        if (numCandidates == all.length) {
            pr("List of candidates is full.");
            return numCandidates;
        }
        Random rand = new Random();
        boolean FOUND = false;
        while (true) {
            FOUND = false;

            int n = rand.nextInt(all.length);
            Candidate c = all[n];

            for (int i= 0; i < numCandidates; i++) {
                if (currentCandidates[i] == c) {
                    FOUND = true;
                }
            }
            if (FOUND == false) {
                currentCandidates[numCandidates] = c;
                return numCandidates + 1;
            }
        }
    }

    public static void runElection(Candidate[] currrentCandidates, Candidate[] all, int numCandidates) {
        String bossman = getString("Who will count the votes?");
        Candidate counter = A3.getByUsername(bossman, all);
        if (counter != null) {
            int count = getInt("How many times should the election run.");
            Candidate[] winners = new Candidate[count];

            for (int i = 0; i < count; i++) {
                Candidate[] copyCandidates = copyArray(currrentCandidates, numCandidates);
                Candidate[] votes = new Candidate[all.length];
                for (int j = 0; j < all.length; j++) {
                    votes[j] = all[j].vote(copyCandidates);
                }
                winners[i] = counter.selectWinner(votes);
            }
            getFinalWinner(winners);
        } else {
            pr("No candidate with name: " + counter);
        }
    }

    private static void getFinalWinner(Candidate[] winners) {
        String winnerName = null;
        boolean multipleWinners = false;
        if (winners.length != 0) {
            Candidate[] tempWinners = copyArray(winners, winners.length);
            int maxCount = 0;
            for (int i = 0; i < tempWinners.length; i++) {
                Candidate c = tempWinners[i];

                if (c != null) {
                    int current = 0;
                    for (int j = 0; j < tempWinners.length; j++) {
                        Candidate n = tempWinners[j];
                        if (n != null && n.getName() == c.getName()) {
                            tempWinners[j] = null;
                            current++;
                        }
                    }
                    if (current == maxCount) {
                        winnerName = winnerName + " and " + c.getName();
                        multipleWinners = true;
                    }
                    if (current > maxCount) {
                        maxCount = current;
                        winnerName = c.getName();
                        multipleWinners = false;
                    }
                }
            }
        }
        if (winnerName != null) {
            if (multipleWinners) {
                pr("The overall winners are ");
            } else {
                pr("The overall winner is " + winnerName);
            }
        }
        pr("There are no winners :(");
    }

    private static Candidate[] copyArray(Candidate[] candidates, int count)
    {
        Candidate[] copy = new Candidate[count];
        for (int i = 0; i < count; i++)
        {
            copy[i] = candidates[i];
        }
        return copy;
    }

    private static String getString(String q) {
        pr(q);
        return sc().nextLine();   
    }

    private static int getInt(String q) {
        pr(q);
        return sc().nextInt();   
    }
    
    private static char getChar(String q) {
        pr(q);
        String t = sc().nextLine(); 
        return ( t.length()==0 ? ' ' : t.charAt(0) );   
        //return sc().nextLine().charAt(0);
    }

    // Use Standard Out.
    private static <T> void pr(T s) { System.out.println(s);}
    
    // Use Standard In.
    private static Scanner sc() {return new Scanner(System.in);}

    // Anything A2 below
    public String getName() {
        return "pogfish.gif";
    }
    
    public String getSlogan() {
        return "Subscribe to Ceres Fauna Ch. hololive-EN";
    }
    
    public Candidate vote(Candidate[] candidates) {
        if (candidates.length == 0) {
            return new Candidate_ec22959();
        }

        for (Candidate c : candidates) {
            if ((c.getSlogan().toLowerCase().contains("hololive"))&&(!"pogfish.gif".equals(c.getName()))) {
                return c;
            }
        }

        Random r = new Random();
        int c = r.nextInt(candidates.length);
        return candidates[c]; 
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        // If array is empty, return instance of this class.
        if (votes.length == 0) {
            return new Candidate_ec22959();
        }

        // Default to first vote, but this will be over-written.
        Candidate currentWinner = votes[0];

        // Count the votes for each object in the array,
        // selecting one with the most.
        int highestCount = 0;
        for (Candidate c : votes) {
            
            int count = 0;
            for (Candidate x : votes)
                if (x == c)
                    count++;
            if (count >= highestCount) {
                highestCount = count;
                currentWinner = c;
            }
        }
        return currentWinner;
    }
    
}
