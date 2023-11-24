package OOP.ec22697.MP;

class Candidate_ec22432 extends Candidate {

    public String getName() {
        return "Mohammad (ec22432)";
       
    }

    public String getSlogan() {
        return "More GPU's";
    }

    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22432();

        if (candidates.length != 0) r = candidates[0];

        if (candidates.length == 0)
        {
            return r;
        }
      
        for (Candidate c : candidates)
        {
            if (c.getSlogan().equals("let him cook"))
            {
                return c;
            }
        }

        for (Candidate c : candidates)
        {
            if (c.getName().equals("who let him cook"))
            {
                return c;
            }
        }

        return r;
    }

    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22432();

        if (votes.length != 0) r = votes[0];
        if (votes.length == 0) 
        {
            return r;
        }
        Candidate currentWinner = votes[0];
        int highestCount = 0;
        for (Candidate c : votes)
        {
            int count = 0;
            for (Candidate i : votes)
            {
                if (c == i)
                {
                    count = count + 1;
                }
                if (count > highestCount)
                {
                    highestCount = count;
                    currentWinner = c;
                }
            }

        }


        return currentWinner;
    }

}

