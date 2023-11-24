package OOP.ec22697.MP;

class Candidate_ec211045 extends Candidate {

     public String getName() {
         return "Meshari (ec211045)";
     }

     public String getSlogan() {
         return "We will win!(ec211045)";
     }

     public Candidate vote(Candidate[] candidates) 
     {
         Candidate r = new Candidate_ec211045();

         if (candidates.length != 0) r = candidates[0];

         if (candidates.length == 0) 
             return new Candidate_ec211045();

         // Search for a like minded candidate.
         for (Candidate c : candidates)
             if (c.getSlogan().equals("We will win!"))
                 return c;

         // Otherwise, search for a friend.
         for (Candidate c : candidates)
             if (c.getName().equals("Adam"))
                 return c;

         return r;
     }

     public Candidate selectWinner(Candidate[] votes) {
         Candidate r = new Candidate_ec211045();

         if (votes.length != 0) 
             r = votes[0];
         Candidate currentWinner = votes[0];

         // Count the votes for each object in the array,
         // selecting one with the most.
         int highestCount = 0;
         for (Candidate c : votes) 
         {

             int count = 0;
             for (Candidate x : votes)
                 if (x == c)
                     count++;
             if (count > highestCount) {
                 highestCount = count;
                 currentWinner = c;
             }

         
         }
         return r;
     }
}
