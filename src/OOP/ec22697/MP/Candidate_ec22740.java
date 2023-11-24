package OOP.ec22697.MP;// File Candidate_ec22740.java
//

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22740 extends Candidate {

    public static void main(String[] args) {
        boolean valid = true;
        Candidate[] allCandidates = A3.getCandidateArray();
        Candidate[] voteCandidates = new Candidate[allCandidates.length];

        int count = 0;

        while (valid) {
            System.out.println("Candidates are: ");
            if(count!=0){
                for (int i=0; i<count; i++){
                    System.out.println(voteCandidates[i].getName());
                }
            }

            String choice = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election d) exit?");

            if (choice.equals("a")) {
                voteCandidates = addSpecificCandidate (allCandidates, voteCandidates, count);
                count++;
            }
            else if (choice.equals("b")){
                voteCandidates = addRandomCandidate (allCandidates, voteCandidates, count);
                count++;
            }
            else if (choice.equals("c")){
                runElections(allCandidates, voteCandidates, count);

                boolean again = true;

                while (again){
                    String opt = inputString("Would you like to a) start another election or b) exit?");

                    if (opt.equals("a")){
                        again=false;
                    }
                    else if(opt.equals("b")){
                        again=false;
                        valid=false;
                    }
                    else{
                        System.out.println("Invalid input.");
                    }
                }

            }
            else if (choice.equals("d")) {
                return;
            }
            else {
                System.out.println("Invalid input.");
            }
        }
        return;
    }


    public static String inputString(String q) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(q);
        String a = scanner.nextLine();

        return a;
    }


    public static int inputInt(String q) {
        int input = Integer.parseInt(inputString(q));

        return input;
    }


    public static Candidate[] addSpecificCandidate (Candidate[] allCandidates, Candidate[] voteCandidates, int count) {
        String username = inputString("Please enter the username: ");
        Candidate newCandidate = A3.getByUsername(username, allCandidates);

        if (newCandidate != null){
            voteCandidates[count] = newCandidate;
        }
        else {
            System.out.println("Could not find this candidate.");
        }

        return voteCandidates;
    }


    public static Candidate[] addRandomCandidate (Candidate[] allCandidates, Candidate[] voteCandidates, int count) {
        Random random = new Random();
        int randomInt = random.nextInt(allCandidates.length);

        voteCandidates[count] = allCandidates[randomInt];

        return voteCandidates;
    }

    public static void runElections (Candidate[] allCandidates, Candidate[] voteCandidates, int count) {
        Random randomCandidate = new Random();
        String voteCounter = inputString("Who should count the votes?");
        Candidate counter = A3.getByUsername(voteCounter, allCandidates);
        int timesRunElection = inputInt("How many times shall we run the election?");

        Candidate[] newElection = new Candidate[count];
        for (int i = 0; i < count; i++)
        {
            newElection[i] = voteCandidates[i];
            System.out.println(newElection[i].getName());
        }

        Candidate[] winners = new Candidate[timesRunElection];

        for (int i = 0; i < timesRunElection; i++) {
            Candidate[] votes = new Candidate[timesRunElection];
            for (int j = 0; j < allCandidates.length; j++) {
                try {
                    votes[j] = allCandidates[randomCandidate.nextInt(allCandidates.length)].vote(newElection);
                }
                catch (Exception e) {
                }
            }
            winners[i] = votes[i];
        }
        
        if (winners[0]==null){
            System.out.println("There are no winners.");
            return;
        }
        else{
            System.out.println("The winners are: ");
            for (int i=1; i<= timesRunElection; i++){
                System.out.println(i +". "+ winners[i-1].getName());
            }
        }

        return;
    }

    public String getName() {
        return "Madara";
    }

    public String getSlogan() {
        return "Have fun!";
    }

    public Candidate vote(Candidate[] candidates) {

        if (candidates.length == 0) {
            Candidate r = new Candidate_ec22740();
            return r;
        }

        // Search for a like-minded candidate.
        for (Candidate c : candidates) {
            if (c.getName().equals("Francois")) {
                if (c.getSlogan().equals("Vote me for hacker points!")) {
                    return c;
                }
            }
        }
        Random r = new Random();
        int c = r.nextInt(candidates.length);
        return candidates[c];
    }


    public Candidate selectWinner(Candidate[] votes) {
        // If array is empty, return instance of this class.
        if (votes.length == 0) {
            return new Candidate_ec22740();
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
