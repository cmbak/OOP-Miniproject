package OOP.ec22697.MP;

class Candidate_ec22530 extends Candidate {

    public String getName() {
        return "Yasin ";
    }

    public String getSlogan() {
        return "Messi is the goat.";
    }

    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_bt21057();
        
        if(candidates.length==0)
        {
            return new Candidate_bt21057();
        }
        
  

        return r;
    }

    public Candidate selectWinner(Candidate[] votes) {
  
          if (votes.length == 0)      // If nothing in the array
        {
            return new Candidate_ec22530(); // Claim I won.
        }

      

        // Add code that either counts the votes
        // or uses some other way to return an element 
        // of the array vote as the winner of an election.
         // Default to first item.
        Candidate winner = votes[0]; 

        // Track highest votes currently
        int currentHighest = 0;

        //Loop over every vote
        for (int i=0; i<votes.length; i++)
        {
            int currentCount = 0;

            //count how many times a vote has been repeated so far
            for (int j=0; j<i; j++)
            {
                if (votes[i].getName() == votes[j].getName()) {currentCount++;}
            }

            // keep track of current highest count (winner)
            if (currentCount > currentHighest)
            {
                currentHighest = currentCount;
                winner = votes[i];
            }
        }

        return winner;


      
    }
    
}
