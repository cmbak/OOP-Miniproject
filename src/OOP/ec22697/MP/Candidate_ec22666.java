package OOP.ec22697.MP;// File Candidate_ec222666.java
//
// Machine generated stub for Assignment 2

import java.util.ArrayList;
class Candidate_ec22666 extends Candidate {
    
    public String getName() {
        return "Mr. Bean";
    }
    
    public String getSlogan() {
        return "Teddy!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22666();
        if (candidates.length == 0 || candidates == null){
			return r;
		}
		
		// return first candidate with a name exactly six letters long and slogan longer than 10 letters long
		// if no such candidate exists reduce requirement for slogan length until slogan length == 0
		// if no such candidate with name length 6 exists (unlikely) return my own class instance (vote Mr. Bean)
		ArrayList<Candidate> tempList = new ArrayList<Candidate>();
		for (int i = 0; i < candidates.length; i++){
			if (candidates[i].getName().length() == 6){
				tempList.add(candidates[i]);
			}
		}
		
		for (int necessarySloganLength = 10; necessarySloganLength > 0; necessarySloganLength--){
			for (int i = 0; i < tempList.size(); i++){
				if (tempList.get(i).getSlogan().length() > necessarySloganLength){
					return tempList.get(i);
				}
			}
		}
        
        return r;
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec221028();
		if (votes.length == 0 || votes == null){
			return r;
		}
        
		// winner is decided by candidate with longest slogan, he is very persuasive, and his name easy to remember!
		// name is 2x weighted, shorter name more important than long slogan.
		// score is decided by slogan length - (name length * 2) [long slogan and short name results in higher score]
		int highestScore = -1000;
		int highestScorerIndex = 0;
		int currentVoteScore = 0;
		
		for (int i = 0; i < votes.length; i++){
			currentVoteScore = votes[i].getSlogan().length() - (votes[i].getName().length() * 2);
			
			if (currentVoteScore > highestScore){
				highestScore = currentVoteScore;
				highestScorerIndex = i;
			}
		} 
		
		r = votes[highestScorerIndex];	
        return r;
    }   
}
