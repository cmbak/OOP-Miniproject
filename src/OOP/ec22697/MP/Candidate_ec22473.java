package OOP.ec22697.MP;// File Candidate_ec22473.java
//
// Machine generated stub for Assignment 2

class Candidate_ec22473 extends Candidate {
    
    public String getName() {
        return "Ilyas";
    }
    
    public String getSlogan() {
        return "baaaka";
    }
    
    public Candidate vote(Candidate[] candidates) {
         if (candidates.length == 0) 
            return new Candidate_ec22473();
         for (Candidate d : candidates)
            if (d.getSlogan().equals("baaka"))
                return d;

        for (Candidate d : candidates)
            if (d.getName().equals("Rameez"))
                return d;

        
 
        return candidates[candidates.length-1];
        
       
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        if (votes.length == 0) 
            return new Candidate_ec22473();
        Candidate currentWinner = votes[0];
        int top_Count = 0;
        for (Candidate d : votes) {
            
            int counter = 0;
            for (Candidate y : votes)
                if (y == d)
                    counter++;
            if (counter > top_Count) {
                top_Count = counter;
                currentWinner = d;
            }
        }
        
        return currentWinner;
    }
    
}
