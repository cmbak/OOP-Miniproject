package OOP.ec22697.MP;// File Candidate_ec22437.java
//
// Machine generated stub for Assignment 2\

class Candidate_ec22437 extends Candidate {

    public static void main(String[] args) {
        Candidate[] allmembers = A3.getCandidateArray();
        System.out.println(allmembers[120].getSlogan()); // adapted from File Candidate_eey577.java
        System.out.println(A3.getByUsername("ec22437", allmembers).getSlogan()); // adapted from File
                                                                                 // Candidate_eey577.java
        System.out.println(allmembers[120].un); // adapted from File Candidate_eey577.java
    }

    public String getName() {
        return "Malcolm";
    }

    public String getSlogan() {
        return "Make Computer Science great again";
    }

    public Candidate vote(Candidate[] candidates) {

        if (candidates.length == 0)
            return new Candidate_ec22423();

        for (Candidate c : candidates)
            if (c.getSlogan().equals("Make Computer Science great again"))
                return c;

        for (Candidate c : candidates)
            if (c.getName().equals("Malcolm"))
                return c;

        return candidates[0];

        // Add code that inspects the array candidates
        // calling the various methods of the Candidate objects
        // and finds a Candidate object to vote for.
    }

    public Candidate selectWinner(Candidate[] votes) {

        if (votes.length == 0) 
            return new Candidate_ec22437();
        
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
