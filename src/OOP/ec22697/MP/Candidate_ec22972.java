package OOP.ec22697.MP;// File Candidate_ec22972.java

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22972 extends Candidate {
    
    public String getName() {
        return "Messi";
    }
    
    public String getSlogan() {
        return "Goat";
    }
    
    public Candidate vote(Candidate[] candidates) {
        // If array is empty, return instance of friend's class.
        if (candidates.length == 0) 
            return new Candidate_ec22632();
        
        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c != null)
                if (c.getSlogan().equals("Goat"))
                    return c;
        
        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c != null)
                if (c.getName().equals("Szymon"))
                    return c;
        
        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        // If array is empty, return instance of friend's class.
        if (votes.length == 0) 
            return new Candidate_ec22632();
        
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
            if (count > highestCount) {
                highestCount = count;
                currentWinner = c;
            }
        }
        return currentWinner;
    }

    public static void main(String[] args) {
        Candidate[] Candidates = A3.getCandidateArray();
        Candidate[] electionCandidates = new Candidate[Candidates.length];
        int candidateCount = 0;

        String Choice = "";
        boolean Valid = false;
        System.out.println("= Running Repeated Elections =");

        while (!Valid){
            System.out.println("Candidates are");
            if (candidateCount == 0){
                System.out.println("none");
            }
            else{
                for (int i = 0; i < candidateCount; i++) {
                    System.out.println((i + 1) + ". " + electionCandidates[i].getName());
                }
            }

            String Option = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");

            if (Option.equals("a")){
                String Username = inputString("Please enter a username.");
                Candidate C = A3.getByUsername(Username, Candidates);
                electionCandidates[candidateCount] = C;
                candidateCount++;
            }
            else if (Option.equals("b")){
                Random R = new Random();
                int Rand = R.nextInt(Candidates.length);
                electionCandidates[candidateCount] = Candidates[Rand];
                System.out.println("Random candidate has been added.");
                candidateCount++;
            }
            else if (Option.equals("c")){
                String voteCounterUsername = inputString("Who should count the votes?");
                Candidate voteCounter = A3.getByUsername(voteCounterUsername, Candidates);
                int Cycle = Integer.parseInt(inputString("How many times shall we run the election?"));
                Candidate[] Winners = new Candidate[Cycle];

                Candidate[] voteCount = new Candidate[electionCandidates.length];
                for (int i = 0; i < Cycle; i++) {
                    Candidate[] Votes = new Candidate[Candidates.length];
                    for (int j = 0; j < electionCandidates.length; j++) {
                        Votes[j] = (Candidates[j].vote(electionCandidates)); 
                    }
                    Winners[i] = (A3.getByUsername(voteCounterUsername, Candidates)).selectWinner(Votes);
                }
                System.out.println("Most common winner is:");
                for (int i = 0; i < Cycle; i++) {
                    System.out.println((Winners[i]).getName());
                }

                boolean Valid2 = false;
                while(!Valid2){
                    String Menu = inputString("Would you like to a) start another election b) quit?");
                    if(Menu.equals("a")){
                        Valid2 = true;
                    }
                    else if(Menu.equals("b")){
                        Valid = true;
                        Valid2 = true;
                    }
                    else{
                        System.out.println("Please select again...");
                    }
                }
            }
            else{
                System.out.println("Please select again...");
            }
        }
    }

    public static String inputString(String message){
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        String answer = scanner.nextLine();
        return answer;
    }
}
