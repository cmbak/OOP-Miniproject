package OOP.ec22697.MP;

class Candidate_ex21213 extends Candidate {

    public String getName() {
        return "Ria";
    }

    public String getSlogan() {
        return "No more university fees!";
    } 

    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ex21213();

        // Set the Ria as the default
        if (candidates.length > 0) 
            r = candidates[0];
 
        // If array is empty, return an instance of a friend's class
        if (candidates.length == 0) 
            return new Candidate_ex21626();

        for (Candidate c : candidates) {
            if (c.getSlogan().equals("No more university fees!")) {
                return c;
            }
        }

        for (Candidate c : candidates) {
            if (c.getName().equals("Alina")) {
                return c;
            }
        }
        
        return r; // Otherwise return default first candidate
    }

    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ex21213();

        if (votes.length > 0) 
            r = votes[0];
 
        // If array is empty, return an instance of a friend's class
        if (votes.length == 0) 
            return new Candidate_ex21626();

        // Set the current winner default 
        Candidate CurrentWinner = votes[0];

        int highestCount = 0;

        for (Candidate c : votes) {
            int count = 0;
            
            for (Candidate x : votes) {
                if (x == c) {
                    count++;
                } else if (count > highestCount) {
                    highestCount = count;
                    CurrentWinner = c;
                }
            }
        }

        return CurrentWinner;
    }
}
