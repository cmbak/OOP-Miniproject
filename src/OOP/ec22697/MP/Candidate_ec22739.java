package OOP.ec22697.MP;

import java.util.Scanner;
import java.util.Random;

class Candidate_ec22739 extends Candidate {
    
    public String getName() {
        return "Nuhan";
    }
    
    public String getSlogan() {
        return "More affrdable housing!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        // If array is empty, return instance of friend's class.
        if (candidates.length == 0) 
            return new Candidate_ec22738();
        
        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("More affordable housing!"))
                return c;
        
        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Abdullah"))
                return c;
        
        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        // If array is empty, return instance of friend's class.
        if (votes.length == 0) 
            return new Candidate_ec22738();
        
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
    
    public static void main() {
        Candidate[] allCandidate = A3.getCandidateArray();
        Candidate[] electionCandidates = new Candidate[allCandidate.length];
        int count = 0;

        String userChoice = inputString("a) add a new candidate b) add a random candidate c) run the election");

        while(userChoice.equals("a")){
            String uname = inputString("Enter a username: ");
            String canToAdd = inputString("Enter the name of the candidate: ");
            electionCandidates[count] = A3.getByUsername(canToAdd, allCandidate);
            System.out.println((count+1) + ". " + electionCandidates[count].getName());
            count++;
            userChoice = inputString("a) add a new candidate b) add a random candidate c) run the election");
        }

        while(userChoice.equals("b")){
            Random rando = new Random();
            int randomer = rando.nextInt(allCandidate.length);
            electionCandidates[count] = allCandidate[randomer];
            System.out.println("random candidate added.");
            userChoice = inputString("a) add a new candidate b) add a random candidate c) run the election");
        }

        while(userChoice.equals("c")){
            String voteCounterName = inputString("Enter the name of the vote counter: ");
            Candidate voteCounter = A3.getByUsername(voteCounterName, allCandidate);
            int voteLoops = inputInteger("How many times should the election run: ");
            Candidate[] winnerList = new Candidate[voteLoops];

            Candidate[] voteCount = new Candidate[electionCandidates.length];
            for(int i=0; i<voteLoops; i++){
                Candidate[] votes = new Candidate[allCandidate.length];
                for(int j=0; j<electionCandidates.length; j++){
                    votes[j] = (allCandidate[j]).vote(electionCandidates);
                }
                winnerList[i] = (A3.getByUsername(voteCounterName, allCandidate)).selectWinner(votes);
            }
            System.out.println("Most common winner is:");
            for(int x = 0; x<voteLoops; x++){
                System.out.println((winnerList[x]).getName());
            }

        }

    }

    public static Integer inputInteger(String message)
        {
            Integer data1;
            Scanner scanner = new Scanner(System.in);

            System.out.println(message);
            data1 = Integer.parseInt(scanner.nextLine());

            return data1;
        }

        //Allow a single string to be input, returning the string typed as the data2
        //
        public static String inputString(String message)
        {
            String data2;
            Scanner scanner = new Scanner(System.in);

            System.out.println(message);
            data2 = scanner.nextLine();

            return data2;
        }
    
}
