package OOP.ec22697.MP;// File Candidate_ec22623.java
//
// Machine generated stub for Assignment 2

class Candidate_ec22623 extends Candidate {
    
    public String getName() {
        return "Rijul Das";
    }
    
    public String getSlogan() {
        return "It's me";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22623();
        
        if(candidates.length==0){
            return new Candidate_ec22623();
        }
        else{       
        
        return r;
        }
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22623();
        
         if (votes.length == 0) 
            return new Candidate_ec22623();
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
    
}
