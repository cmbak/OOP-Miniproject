package OOP.ec22697.MP;// File Candidate_ec20315.java
//
// Machine generated stub for Assignment 2

class Candidate_ec20315 extends Candidate {
    
    public String getName() {
        return "Linus Loelliger";
    }
    
    public String getSlogan() {
        return "GTO bot";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec20315();
		Candidate[] all = A3.getCandidateArray();

       if (all.length == 0) {
			return r;
		} else if (all.length == 1) {
			return all[0];
		} else {
			int randomIndex = (int)(Math.random() * all.length);
			return all[randomIndex];
		}
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec20315();
        int highest=0;
        if(votes.length == 0){
            return new Candidate_ec20315();
        }
        Candidate winner = votes[0];
        int length = votes.length;
        for(Candidate c: votes){
            int votecount=0;
            for(Candidate cc : votes){
                if(c==cc){votecount++;}}
            if (votecount>highest){
                highest = votecount;
                winner=c;
            }
        }
       
       return winner;
    }
    
}
