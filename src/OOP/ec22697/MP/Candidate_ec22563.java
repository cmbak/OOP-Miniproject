package OOP.ec22697.MP;// File Candidate_ec22563.java

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Candidate_ec22563 extends Candidate {
    
    public static void main(String[] args) {
        ArrayList<Candidate> candidates = new ArrayList<Candidate>();

        Candidate[] allCandidates = A3.getCandidateArray();

        Scanner sc = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("Candidates are:");
            if (candidates.size() == 0) {
                System.out.println("None");
            } else {
                for (int i = 0; i < candidates.size(); i++) {
                    System.out.println(candidates.get(i).getName());
                }
            }
            System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
            String choice = sc.nextLine();
            switch (choice) {
                case "a":
                    addCandidate(candidates, allCandidates, sc);
                    break;
                case "b":
                    addRandomCandidate(candidates, allCandidates);
                    break;
                case "c":
                    runElection(candidates, allCandidates, sc);
                    System.out.println("Would you like to\na) exit\nb) run same election many times");
                    String choice2 = null;
                    boolean exit2 = true;
                    while (exit2) {
                        choice2 = sc.nextLine();
                        if (choice2.equals("a")) {
                            exit = true;
                            exit2 = false;
                        } else if (choice2.equals("b")) {
                            candidates.clear();
                            exit2 = false;
                        }
                    }
                    break;
                default:
                    System.out.println("Invalid Choice");
            }

            
        }
    }

    private static void runElection(ArrayList<Candidate> candidates, Candidate[] allCandidates, Scanner sc) {
        
        System.out.println("Who should count the votes?");
        boolean counterSelected = false;
        Candidate counter = new Candidate_ec22563();
        while (!counterSelected) {
            counter = A3.getByUsername(sc.nextLine(), allCandidates);
            if (counter != null) {
                counterSelected = true;
            } else {
                System.out.println("Invalid user, try again: ");
            }
        }

        System.out.println("How many times shall we run the election?");
        boolean countSelected = false;
        int count = 1;
        while (!countSelected) {
            try {
                count = Integer.parseInt(sc.nextLine());
                if (count < 1) {
                    System.out.println("Must be >0");
                } else {
                    countSelected = true;
                }
            } catch (Exception e) {
                System.out.println("Please enter an integer");
            }
        }        
        
        // Get all the winners
        Candidate[] winners = new Candidate[count];
        Candidate[] votes = new Candidate[candidates.size()];
        for (int i = 0; i < candidates.size(); i++) {
            votes[i] = candidates.get(i).vote(candidates.toArray(new Candidate[0]));
        }
        for (int i = 0; i < count; i++) {
            winners[i] = counter.selectWinner(votes);
        }

        // Find the most frequent winner
        int x = 1, y;
        Candidate winner = winners[0];
        Candidate temp = new Candidate_ec22563();
        for (int i = 0; i < (winners.length - 1); i++)
        {
            temp = winners[i];
            y = 0;
            for (int j = 1; j < winners.length; j++)
            {
                if (temp == winners[j]) {
                    y++;
                }
                if (y > x) {
                    winner = temp;
                    x = y;
                }
            }
        }

        System.out.println("Most common winner is " + winner.getName());
    }

    private static void addRandomCandidate(ArrayList<Candidate> candidates, Candidate[] allCandidates) {
        int rnd = new Random().nextInt(allCandidates.length);
        candidates.add(allCandidates[rnd]);
    }

    private static void addCandidate(ArrayList<Candidate> candidates, Candidate[] allCandidates, Scanner sc) {
        System.out.print("Please enter a username: ");

        // Get the new candidate
        Candidate newCandidate = A3.getByUsername(sc.nextLine(), allCandidates);

        // Add user's candidate choice to list
        if (newCandidate != null) {
            candidates.add(newCandidate);
        } else {
            System.out.println("Invalid candidate");
        }
    }

    public String getName() {
        return "Hamza";
    }
    
    public String getSlogan() {
        return "Website at hamzahuda.com - also vote me";
    }
    
    public Candidate vote(Candidate[] candidates) 
    {
        // default value is an instance of this class
        Candidate r = new Candidate_ec22563();

        // randomise if cant find candidate "Bob"
        if (candidates.length != 0) {
            for (Candidate c : candidates) {
                if (c.getName().equals("Bob")) {
                    r = c;               
                }
            }

            Random ran = new Random(); 
            int c = ran.nextInt(candidates.length);
            r = candidates[c];
        }

        // return candidate chosen
        return r;
    }
    
    public Candidate selectWinner(Candidate[] votes) 
    {
        Candidate r = new Candidate_ec22563();
        
        // choose a random winner
        if (votes.length != 0) {
            Random ran = new Random();
            int ranInt = ran.nextInt(votes.length);
            r = votes[ranInt];   
        }

        return r;
    }
}

