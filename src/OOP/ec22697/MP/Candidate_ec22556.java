package OOP.ec22697.MP;

class Candidate_ec22556 extends Candidate {

    public String getName() {
        return "Shucab";
    }

    public String getSlogan() {
        return "Every little helps";
    }

    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22556();

        for (Candidate c : candidates)
            if (c.getName().equals("King Julian"))
                return c;

        return candidates[0];

    }

    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22556();

        if (votes.length != 0) r = votes[0];

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

        return r;
    }

}
