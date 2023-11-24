package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;

class Candidate_ec21582 extends Candidate {
    
   public String getName() {
        return "James";
    }
    
    public String getSlogan() {
        return "World peace!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        if (candidates.length == 0) 
            return new Candidate_ec21582();
        
        for (Candidate c : candidates)
            if (c.getSlogan().equals("World peace!"))
                return c;
        
        Random r = new Random();
        int c = r.nextInt(candidates.length);
        return candidates[c]; 
        
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        if (votes.length == 0) 
            return new Candidate_ec21582();
        
        Candidate currentWinner = votes[0];
        
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
