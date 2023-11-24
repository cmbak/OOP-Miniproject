package OOP.ec22697.MP;// File Candidate_ex21329.java
//
// Stub for Assignment 2

class Candidate_ex21329 extends Candidate {
    
    public String getName() {
        return "Yunus Khan";
    }
    
    public String getSlogan() {
        return "For The Few Not The Many!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ex21329();
        
        if (candidates.length != 0){

        for (Candidate c : candidates)
            if (c.getSlogan().equals("No more university fees!")){
                r = c;
			}
			else{
				return new Candidate_ex21329();
			}
		}
        return r;
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ex21329();
        
        if (votes.length != 0){
 
		int highestCount = 0;
            for (Candidate c : votes) {
                int count = 0;
                for (Candidate x : votes)
                  if (x == c)
                     count++;
                if (count > highestCount) {
                    highestCount = count;
                    r = c;
                }
            }
			
        }
        else{
            r = new Candidate_ex21329();
        }
        
        return r;
    }
    
}
