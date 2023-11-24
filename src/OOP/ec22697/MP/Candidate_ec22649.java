package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22649 extends Candidate {
    //  gets the name of the string
    public String getName() {
        return "Green Arrow";
   } 
    
    public String getSlogan() {
        return "You have failed this city.";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        // If array is empty, return instance of friend's class.
        if (candidates.length == 0) 
            return new Candidate_ec22649();
        
        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("You have failed this city."))
                return c;
        
        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Green Arrow"))
                return c;
        
        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        // If array is empty, return instance of friend's class.
        if (votes.length == 0) 
            return new Candidate_ec22649();
        
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
		Candidate[] allNames = A3.getCandidateArray();
        Candidate[] allVote = new Candidate[allNames.length];
        int count = 0;
        String input = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");

        if (input.equals("a")){
            String name = inputString("Please enter a username.");
            Candidate newCandidate = A3.getByUsername(name, allNames);
            allVote[count] = newCandidate;
            count++;

            showNames(allVote, count);
        } 
        else if (input.equals("b")){
            Random random = new Random();
            int randomNum = random.nextInt(allNames.length);
            allVote[count] = allNames[randomNum];
            count++;
            showNames(allVote, count); 
        }

        else if (input.equals("c")){
            String voteCount = inputString("Who should count the votes?");
            Candidate candidateName = A3.getByUsername(voteCount, allNames);
            int times = inputInt("How many times shall we run the election?");
            runElection(allVote, count, times, candidateName, allNames);
        }
    }

    public static void showNames(Candidate[] candidates, int count) {
        System.out.println("Candidates are: ");
        for (int i = 0; i < count; i++) {
            System.out.println(candidates[i].getName() + " and their slogan is: " + candidates[i].getSlogan());
        }
        return;

	}

	private static String inputString(String question)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println(question);
        String value = scanner.nextLine();
        return value;
    }

	private static int inputInt(String question)
    {
        int value;
        Scanner scanner = new Scanner(System.in);

        System.out.println(question);
        value = Integer.parseInt(scanner.nextLine());

        return value;
    }

	public static void runElection(Candidate[] votes, int count, int times, Candidate voteCount, Candidate[] names){
        Candidate[] newElection = new Candidate[count];
        for (int i = 0; i < count; i++) {
            newElection[i] = votes[count];
        }

        Candidate[] winningCandidates = new Candidate[times * (count - 1)];

        for (int i = 0; i <= times; i++) {
            for (int j = 0; j < count; j++) {

                    winningCandidates[(count * i) + j] = newElection[j].vote(newElection);
            }
        }
        //stores the winning candidate
        Candidate actualWinner = voteCount.selectWinner(winningCandidates);
        System.out.println("Most common winner is: " + actualWinner.getName());
    }
    
}
