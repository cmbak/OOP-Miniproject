package OOP.ec22697.MP;// File Candidate_ec22752.java
//
// Machine generated stub for Assignment 2

class Candidate_ec22752 extends Candidate {

    public String getName() {
        return "Larry";
    }

    public String getSlogan() {
        return "Forwards and upwards!";
    }

    public Candidate vote(Candidate[] candidates) {

        if (candidates.length == 0){
            return new Candidate_ec22752();
        }
        else{
            for(int i = 0; i<candidates.length; i++){
                if(candidates[i].getName() == "Larry") return candidates[i];
            }
            return candidates[candidates.length-1];
        }
    }

    public Candidate selectWinner(Candidate[] votes) {
        if (votes.length == 0){
            return new Candidate_ec22752();
        }

        Candidate winner = new Candidate_ec22752();
        int highestCount = 0;
        for (Candidate c : votes) {

            int count = 0;
            for (Candidate x : votes)
                if (x == c)
                    count++;
            if (count > highestCount) {
                highestCount = count;
                winner = c;
            }
        }
        return winner;
    }

}
