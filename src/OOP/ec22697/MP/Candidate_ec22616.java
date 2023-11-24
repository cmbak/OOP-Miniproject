package OOP.ec22697.MP;

import java.util.*;



class Candidate_ec22616 extends Candidate {
    
    public String getName() {
        return "Saif";
    }
    
    public String getSlogan() {
        return "hellooo";
    }
    
     public Candidate vote(Candidate[] candidates) {
        Candidate c = new Candidate_ec22616();
        
        try{
            for(Candidate candidate: candidates){  //checks to see if "saif" is in the candidate array and votes for him
                if(candidate.getName().equals("Saif")){
                    return candidate;
                }
            }
            
            Random random = new Random(); //if "saif" isn't a candidate then vote for a random
            int random_number = random.nextInt((candidates.length / 2) - 1);
            
            return candidates[random_number];
        }
        catch(Exception e){ //if list is empty
            return c;
        }
        
    }
    
    public Candidate selectWinner(Candidate[] votes) { //selects the most frequent appearing candidate on the votes list
         return mostFrequent(votes);
    }
    
     public static Candidate mostFrequent(Candidate[] votes){
        int most_frequent_index = 0;
        int max_vote_frequency = 0;
        
        for(int i=0; i<=votes.length-1; i++){  //Loops through votes array and finds the most frequent and sets the index to the variable above
            int count = 0;
            for(int j=1; j<=votes.length-1; j++){
                if(votes[i] == votes[j]){
                    count++;
                }
            }
            if(count > max_vote_frequency){
                max_vote_frequency = count;
                most_frequent_index = i;
            }
            count = 0;
        }
        
        return votes[most_frequent_index];
    }
    
    public static void add_candidate(Candidate[] candidates, int current_index) {
    	Scanner scanner = new Scanner(System.in);
    	String username;
    	Candidate[] all_candidates = A3.getCandidateArray();
    	
    	System.out.println("Please enter a username");
    	username = scanner.nextLine();
    	Candidate candidate = A3.getByUsername(username, all_candidates);
    	
    	if(A3.getByUsername(username, all_candidates) == null) {
    		System.out.println("User not found");
    		add_candidate(candidates, current_index);
    	} else {
    		candidates[current_index] = candidate;
    	}
    }
    
    public static void add_random_candidate(Candidate[] candidates, int current_index) {
    	Random random = new Random();
    	Candidate[] all_candidates = A3.getCandidateArray();
    	int random_number = random.nextInt(all_candidates.length);
    	
    	candidates[current_index] = all_candidates[random_number];
    }
    
    public static void run_election(Candidate[] candidates, int current_index) {
    	Scanner scanner = new Scanner(System.in);
    	String username;
    	int number_of_times_election_held;
    	Candidate[] all_candidates = A3.getCandidateArray();
    	Candidate[] votes = new Candidate[all_candidates.length];
    	
    	System.out.println("Who should count the votes?");
    	username = scanner.nextLine();
    	Candidate vote_counter = A3.getByUsername(username, all_candidates);
    	
    	System.out.println("How many times shall we run the election?");
    	number_of_times_election_held = scanner.nextInt();
    	Candidate[] winners = new Candidate[number_of_times_election_held];
    	for(int i=0; i<=number_of_times_election_held; i++) {
    		for(int j=0; j<=all_candidates.length; j++) {
        		votes[j] = all_candidates[j].vote(all_candidates);
        	}
    		winners[i] = vote_counter.selectWinner(votes);
    	}
    	
    	String most_common_winner = vote_counter.selectWinner(winners).getName();
    	
    	System.out.println("Most common winner is " + most_common_winner);
    }
  
    
    
    public static void runLoop() {
    	String choice;
    	Scanner scanner = new Scanner(System.in);
    	int current_index = 0;
    	Candidate[] candidates = new Candidate[current_index];
    	
    	do {
    		System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
    		choice = scanner.nextLine();
    		switch(choice) {
    			case("a"):
    				add_candidate(candidates, current_index);
    				current_index += 1;
    				break;
    			case("b"):
    				add_random_candidate(candidates, current_index);
    				current_index += 1;
    				break;
    		}
    	} while(!choice.equals("c"));
    	
    	run_election(candidates, current_index);
    }
    
    public static void main(String[] args) {
    	runLoop();
    }
    
}
