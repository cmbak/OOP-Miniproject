package OOP.ec22697.MP;// File Candidate_eey577.java
//   
// Machine generated stub for Assignment 2
//

class Candidate_eey577 extends Candidate {
    
    public static void main(String[] args) {
        
        Candidate[] allContributions = A3.getCandidateArray();
        System.out.println(allContributions[42].getSlogan());
        System.out.println(A3.getByUsername("eey577",allContributions).getSlogan());
        System.out.println(allContributions[479].un);
        
    }
    
     
    public String getName() {
        return "Ip";
    }
    
    public String getSlogan() {
        return "Vote for ip!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_eey577();
        
        if (candidates.length != 0) r = candidates[0];
 
        // Add code that inspects the array candidates
        // calling the various methods of the Candidate objects
        // and finds a Candidate object to vote for.
        
        return r;
    }
    
    public Candidate selectWinner(Candidate[] votes) {
       
        // If array is empty, return instance of this class.
        if (votes.length == 0) 
            return new Candidate_eey577();
        
        // Default to first vote, but this will be over-written.
        Candidate currentWinner = votes[0];
        
        // Count the votes for each object in the array,
        // selecting one with the most.
        int highestCount = 0;
        for (Candidate c : votes) {
            
            int count = 0;
            for (Candidate x : votes)
                if (x == c)
                    count++;
            
            if (count > highestCount) {
                highestCount = count;
                currentWinner = c;
            }
        }
        
        return currentWinner;
    }
    
}
