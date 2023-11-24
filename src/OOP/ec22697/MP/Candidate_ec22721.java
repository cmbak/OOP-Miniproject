package OOP.ec22697.MP;

import java.util.Scanner;

class Candidate_ec22721 extends Candidate {
    
    public static void main(String[] args) {
        final Candidate[] all = A3.getCandidateArray();
        Candidate[] votes = new Candidate[all.length];

        // start- enter the candidates
        int count = Intput("How many candidates do you wish to enter?");
        Candidate[] candidates = new Candidate[count];
        for (int i = 0; i < count; i++) {
            String username = inputString("Enter candidate username: ");
            Candidate c = A3.getByUsername(username, all);
            
            if (c == null) {
                Print("Not found. Please, try again.");
                i--;
            }
            else {
                candidates[i] = c;
            }
        }

        // Print candidates
        Print("Candidates are:");
        for (Candidate c : candidates) {
            Print(c.getName());
        }

        // Decide who counts
        boolean valid = false;
        Candidate counter = new Candidate_ec22721();
        while (!valid) {
            String counterUsername = inputString("Who do you want to count? [enter username]");
            counter = A3.getByUsername(counterUsername, all);
            if (counter == null){
                Print("Not found. Please try again.");
            }else {
                valid = true;
            }
        }

        // Decide how many runs
        boolean positive = false;
        int numOfRuns = 0;
        while (!positive) {
            numOfRuns = Intput("How many times shall we run the election?");
            if (numOfRuns > 0) positive = true;
            else Print("Can't be negative, please try again.");
        }

        Candidate[] winners = new Candidate[numOfRuns];

        // Run the election
        for (int run = 0; run < numOfRuns; run++) {

            for (int i = 0; i < all.length; i++) {
                votes[i] = all[i].vote(candidates);
            }

            winners[run] = counter.selectWinner(votes);
        }

        // Print most common winner
        int highestFreq = 0;
        int freq = 0;
        Candidate W = winners[0];
        for (int i = 0; i < winners.length; i++) {
            freq = 1;
            for (int j = 0; j < winners.length; j++) {
                if (winners[i] == winners[j]) freq++;
            }
            if (freq > highestFreq) {
                highestFreq = freq;
                W = candidates[i];
            }
        }
        Print("The most frequent winner was " + W.getName());


    }
    //input methods
 
    static <T> void Print(T message) {
        System.out.println(message);
    }

    // Returns next string from user
    static <T> String inputString(T message) {
        Scanner sc = new Scanner(System.in);

        Print(message);

        return sc.nextLine();
    }
    
    
    static <T> int Intput(T message) {
        Scanner sc = new Scanner(System.in);
        boolean valid = false;
        int i = 0;

        Print(message);

        while (valid == false) {
            //test for errors
            try {
                i = Integer.parseInt(sc.nextLine());
                valid = true;
            }
            catch(Exception e) {
                System.out.println("Invalid input.");
            }
        }
        return i;
    }
    
    
    //A2
    
    
    //name and slogan 
    public String getName() {
        return "Aryan";
    }

    public String getSlogan() {
        return "The future rules";
    }
    
    //Election methods
    
    
    //Your vote method must somehow decide which object to return from the array of Candidate objects it is given. It should do this by calling the methods of those objects and  
    //considering the results. For example, it might search for an object in the array with a particular slogan and return that object.
    public Candidate vote(Candidate[] candidates){
        //decide which object to return from the array,
        
        // If array empty, return me.
        if (candidates.length == 0) {
            return new Candidate_ec22721();
        }
        // Search for a similair candidate.
        for (Candidate c : candidates){
            if (c.getSlogan().equals("The future rules!"))
                return c;
        }

        // Or, last candidate on list.
        return candidates[candidates.length-1];
    }
    //Your selectWinner(Candidate[]) method would normally return the most frequently occurring Candidate object in the array it is passed.
    
        public Candidate selectWinner(Candidate[] votes) {
            if (votes.length == 0){
                return new Candidate_ec21416();
            }
       
            else{
                // Count each object's votes, select highest
                Candidate Winner = votes[0];
                int highestCount = 0;
                for (Candidate c : votes) {
                    int count = 0;
                    for (Candidate x : votes)
                        if (x == c)
                            count++;
                    if (count > highestCount) {
                        highestCount = count;
                        Winner = c;
                    }
                }
                return Winner;
            }
        
        }


}
