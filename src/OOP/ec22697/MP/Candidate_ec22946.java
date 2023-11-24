package OOP.ec22697.MP;

import java.util.*;

class Candidate_ec22946 extends Candidate {
	
	public static void main(String[] args)
	{
		Candidate[] all_candidates = A3.getCandidateArray();
		for(int i = 0; i < all_candidates.length; i++)
		{
			Candidate current_candidate = all_candidates[i];
			current_candidate.vote(all_candidates);
		}
		do
		{
			Candidate random_candidate = all_candidates[(new Random()).nextInt(all_candidates.length)];
			System.out.println("the ability to add specific candidates to an election");
			System.out.println(random_candidate.getName());
			System.out.println(random_candidate.getSlogan());
			System.out.println("the ability to run the same election many times and report the most common winner(s)");
			System.out.println((random_candidate.vote(all_candidates)));
			System.out.println((random_candidate.selectWinner(all_candidates)));
		}while(((new Scanner(System.in)).nextLine()).equals("again"));
	}
    
    public String getName() {
		String name = "";
		for(int i = 0;i<8;i++)
		{
			name = name + (char) ((new Random()).nextInt(26) + 97);
		}
		return name;
    }
    
    public String getSlogan() {
		String slogan = "";
		for(int i = 0;i<25;i++)
		{
			slogan = slogan + (char) ((new Random()).nextInt(26) + 65);
		}
		return slogan;
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        if (candidates.length == 0) 
            return new Candidate_ec22946();
        
        return candidates[(new Random()).nextInt(candidates.length)]; 
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        if (votes.length == 0) 
            return new Candidate_ec22946();
        
		return votes[(new Random()).nextInt(votes.length)];
    }
    
}
