package OOP.ec22697.MP;// File Candidate_ec22558.java
//
// Machine generated stub for Assignment 2
//changes

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


class Candidate_ec22558 extends Candidate {
//hello world
        
    public String getName() {
            return "Saul Goodman";
    }

    public String getSlogan() {
            return "Better Call Saul";
    }
        
    public static String inputString (String message)  //input method
    {
        Scanner scanner = new Scanner(System.in);
        String answer;

        System.out.println(message);
        answer = scanner.nextLine();

        return answer;
    } // END inputString


    public static void print(String message)
    {
        System.out.println(message);
        return;
    }


    public static int inputInt (String message)
    {
        Scanner scanner = new Scanner(System.in);
        String textInput;
        int numberInput=0;
        boolean true_int= false;
        while(! true_int)
        {
            try{
                System.out.println(message);
                textInput = scanner.nextLine();
                numberInput = Integer.parseInt(textInput);
                true_int=true;
            }
            catch(Exception E)
            {
                System.out.println("Not an integer");
            }

        }
        return numberInput;
    }


        
        
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		print("=Running Repeated Election=");
		Candidate[] candidatesA3 = A3.getCandidateArray();
		ArrayList<Candidate> currentCandidates = new ArrayList<Candidate>();
		boolean running = true;
		while(running){
			print("Candidates are");
			for(Candidate c : currentCandidates){System.out.println(c.getName());}
			print("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
			String option = sc.nextLine();
			if(option.equals("a")){
				System.out.println("Please enter a username. ");
				String username = sc.nextLine();
				Candidate selection = A3.getByUsername(username, candidatesA3);
				if(selection!=null){
					currentCandidates.add(selection);

				}else{
					System.out.println("Username invalid");
				}
			}else if(option.equals("b")){
				Random random = new Random();
				int randomNumber = random.nextInt(candidatesA3.length-1);
				currentCandidates.add(candidatesA3[randomNumber]);
			}else if(option.equals("c")){
				print("Who should count the votes?");
				String inputUsername = sc.nextLine();
				if(A3.getByUsername(inputUsername, candidatesA3) != null){
					Candidate voteCounter = A3.getByUsername(inputUsername, candidatesA3);
					int electionRuns = inputInt("How many times should we run the election?");
					String winner = "";
					for(int i=0; i<electionRuns;i++){
						int highestCount = 0;
						for (Candidate c : currentCandidates) {
						   int count = 0;
						   for (Candidate x : currentCandidates)
							   if (x == c)
								   count++;
						   if (count > highestCount) {
							   highestCount = count;
							   winner = c.getName();
						   }
						}
					}
					print("Most common winner is "+winner);
				}else{
					print("Username invalid");
				}
			}
		}
	}

       

    public Candidate vote(Candidate[] candidates) {
        Candidate VotingCandidate = new Candidate_ec22558();
 
        //Returns a new candidate of my class if array is empty
        if (candidates.length == 0){
            return VotingCandidate;
        }
        //Votes for someone with similar slogan and if not, the middle candidate gets the vote
        for (int i = 0; i<candidates.length; i++){
            if (VotingCandidate.getSlogan().equals("Better call Saul")){
                int SimilarCandidatePosition = i;
                return candidates[i];
            }
        }
        return candidates[(candidates.length/2)];  
    }
        
        
    public Candidate selectWinner(Candidate[] votes) {
        // Create new candidate object
            
        Candidate WinningCandidate = new Candidate_ec22558();
 
        // If the array is empty, a new 
        if (votes.length == 0) 
            return WinningCandidate;
        
        // Votes random candidate 
        Random RandomNum = new Random();
        int RandomWinner = 0;
        
        RandomWinner = RandomNum.nextInt(votes.length);
        return votes[RandomWinner];
    }
   

}
