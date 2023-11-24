package OOP.ec22697.MP;

class Candidate_ec22900 extends Candidate {
    
    
    public String getName() {
        return "Luca";
    }
    
    public String getSlogan() {
        return "ooga booga!";
    }
    
    public Candidate selectWinner(Candidate[] candidates){
         if (candidates.length == 0){
            return new Candidate_ec22900();
         }

        
        
        int Candidate_votes1[] = new int[100];
        Candidate Candidate_index1[] = new Candidate[100];
        boolean CandidateInArray = false;
        int possitionInIndex = 0;
        int nextIndexPossition = 0;
        
        for(int i=0; i<candidates.length; i++){
            for(int x = 0; x>Candidate_index1.length; x++){
                if(candidates[i] == Candidate_index1[x]){
                    CandidateInArray = true;
                    possitionInIndex = x;
                   }
                   
                   if (!CandidateInArray){
                       Candidate_index1[nextIndexPossition] = candidates[i];
                       Candidate_votes1[nextIndexPossition] = 1;
                       nextIndexPossition++;
                   }
                   else{
                        Candidate_votes1[possitionInIndex]++;
                   }
                   
            }
        }
        Candidate mostPopularCandidate = candidates[0];
        int highestVote = 0;
        for(int i=0; i<Candidate_votes1.length; i++){
            if(Candidate_votes1[i] > highestVote){
                mostPopularCandidate = Candidate_index1[i];
            }
        
        }
        return mostPopularCandidate;
    }
        
    public Candidate vote(Candidate[] votes) {
        if(votes.length == 0){
            return new Candidate_ec22900();
            }
        else{
            return votes[0];
        }
        
    }
        
}
