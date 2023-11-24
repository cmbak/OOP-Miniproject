package OOP.ec22697.MP;// File Candidate_ec22680.java
//
// Machine generated stub for Assignment 2

class Candidate_ec22680 extends Candidate {
    
    public String getName() {
        return "Micheal Jordan";
    }
    
    public String getSlogan() {
        return "And I took that personal";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22680();
        
        if (candidates.length==0)
        {
            return new Candidate_ec22680();
        }
        
        return r;
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        if (votes.length == 0)
        {
            return new Candidate_ec22680();
        }
 
        Candidate winner = votes[0];
        
        int currentHighest = 0;
        
        for (int i=0; i<votes.length; i++)
        { 
            int currentCount = 0;
            
            for(int j=0; j<i; j++)
            {
                if (votes[i].getName() == votes[j].getName()) {currentCount++;}
            }
            
            if (currentCount > currentHighest)
            {
                currentHighest = currentCount;
                winner = votes[i];
            }
        }
        return winner;
        
    }

    public static Candidate MostPop(Candidate[] votes) {

        Candidate currentWinner = votes[0];
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

