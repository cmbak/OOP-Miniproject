package OOP.ec22697.MP;// File Candidate_ec22712.java
//
// Machine generated stub for Assignment 2

class Candidate_ec22712 extends Candidate {
    
    public String getName() {
        return "Hasan";
    }
    
    public String getSlogan() {
        return "Save lives";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22712();
        if (candidates.length == 0)
		return new Candidate_ec22712();
 
        for (Candidate c : candidates)
		if(c.getName().equals("Utfur"))
			return c;
	return r;   
 }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        if (votes.length == 0) 
		return new Candidate_ec22712();
	
	Candidate currentWinner = votes[0];

	int highestCount = 0;
	for (Candidate c: votes) {
	
		int count = 0;
		for (Candidate x : votes){

			if (x == c){

				count++;
			}
		}
		if (count > highestCount) {
			highestCount = count;
			currentWinner = c;
		}
	   }
	return currentWinner;
	}
    
}
