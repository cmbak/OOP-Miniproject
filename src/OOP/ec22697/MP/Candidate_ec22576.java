package OOP.ec22697.MP;

import java.util.Scanner;
import java.util.Random;

class Candidate_ec22576 extends Candidate {
    
    public String getName() {
        return "Ben";
    }
    
    public String getSlogan() {
        return "More Pollution, Less Trees!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        if (candidates.length == 0) 
            return new Candidate_ec22576();
        
        for (Candidate c : candidates)
            if (c.getSlogan().equals("More Pollution, Less Trees!"))
                return c;
        
        for (Candidate c : candidates)
            if (c.getName().equals("Sandeep"))
                return c;
        
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        if (votes.length == 0) 
            return new Candidate_ec22576();
        
        Candidate currentWinner = votes[0];
        
        int highestCount = 0;
        for (Candidate c : votes) {
            
            int count = 0;
            for (Candidate x : votes)
                if (x == c)
                    count++;
            
            if (count > highestCount) {
                highestCount = count;
                currentWinner = c;
            }
        }
        
        return currentWinner;
    }
    
    // Below is adapted from ecs414-b22ua/A3
    
    public static String getString(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        
        return scanner.nextLine();
    }

    public static char getCharacter(String message) {
        Scanner scanner3 = new Scanner(System.in);
        System.out.println(message);
        
        return scanner3.nextLine().charAt(0);

    }

    public static int getInteger(String message) {
        Scanner scanner2 = new Scanner(System.in);
        System.out.println(message);
        
        return Integer.parseInt(scanner2.nextLine());
    }

    public static Candidate[] getCandidateArray() {
        Candidate[] John = new Candidate[5];

        for (int i = 0; i < 5; i++) {
            John[i] = new Candidate_ec22562();
        }

        return John;
    }

    public static void main(String[] args) {
        Candidate[] allCandidates = getCandidateArray();
        Candidate[] election = new Candidate[allCandidates.length];
        int counter = 0;
        String username = "";

        boolean exit = false;
        char menu = '0';

        while (!exit) {
            menu = getCharacter(
                    "Please select an option: a) Add a specific candidate b) Add a random candidate c) Run the election?");
            switch (menu) {
                case 'a':
                    username = getString("Please enter a username:");
                    Candidate newCandidate = A3.getByUsername(username, allCandidates);
                    election[counter] = newCandidate;

                    counter++;
                    displayCandidates(election, counter);
                    break;
                    
                case 'b':
                    Random randInt = new Random();
                    int randomInt = randInt.nextInt(allCandidates.length);

                    election[counter] = allCandidates[randomInt];

                    counter++;
                    displayCandidates(election, counter);
                    break;
                    
                case 'c':
                    username = getString("Who should count the votes?:");
                    Candidate chosenCandidate = A3.getByUsername(username, allCandidates);

                    int repeat = getInteger("How many times should we run the election?");
                    runningVote(election, counter, repeat, chosenCandidate);
                    exit = true;
                    break;

                default:
                    System.out.println("Option '" + menu + "' is not a valid option.");
            }
        }
    }

    public static void displayCandidates(Candidate[] candidateList, int counter) {
        System.out.println("The Candidates are: ");
        for (int i = 0; i < counter; i++) {
            System.out.println(candidateList[i].getName() + ": " + candidateList[i].getSlogan());
        }
        
        System.out.println("End of List");

        return;
    }

    public static void runningVote(Candidate[] election, int counter, int repeat, Candidate chosenCandidate) {
        Candidate[] electionNew = new Candidate[counter];


        for (int i = 0; i < counter; i++) {
            electionNew[i] = election[counter];
        }

        Candidate[] votes = new Candidate[repeat * (counter - 1)];

        for (int j = 0; j <= repeat; j++) {

            for (int i = 0; i < counter; i++) {
                try {
                    votes[(counter * j) + i] = electionNew[i].vote(electionNew);
                } catch (Exception e) {
                    votes[i] = new Candidate_ec22562();
                }
            }
        }

        Candidate winner = chosenCandidate.selectWinner(votes);
        System.out.println("Most common winner is: " + winner.getName());
    }
    
}
