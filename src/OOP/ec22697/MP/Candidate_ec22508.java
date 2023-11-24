package OOP.ec22697.MP;

public class Candidate_ec22508 extends Candidate {

    @Override
    public String getName() {
        return "Radek";
    }

    @Override
    public Candidate vote(Candidate[] candidateDS) {
        int[] lengths = new int[candidateDS.length];

        if (candidateDS.length == 0) {
            return new Candidate_ec22508();
        } else {
            int i = 0;
            int longest = 0;
            int best_i = 0;
            for (Candidate c : candidateDS) {
                lengths[i] = c.getSlogan().length();
                if (c.getSlogan().length() > longest) {
                    longest = c.getSlogan().length();
                    best_i = i;
                }
                i++;
            }
            Candidate chosen = candidateDS[best_i];
        }
        return new Candidate_ec22508();
    }

    @Override
    public Candidate selectWinner(Candidate[] votes) {
        if (votes.length == 0) {
            return new Candidate_ec22508();
        } else {
            Candidate winner = votes[0];
            int leastVotes = 0;
            for (Candidate c : votes) {

                int count = 0;
                for (Candidate x : votes) {
                    if (x == c) {
                        count++;
                    }
                }
                if (count <= leastVotes) {
                    leastVotes = count;
                    winner = c;
                }
                return c;
            }
        }

        return new Candidate_ec22508();
    }

    @Override
    public String getSlogan() {
        return "Per aspera";
    }
}
