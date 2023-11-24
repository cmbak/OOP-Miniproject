package OOP.ec22697.MP;//Assignement 3
//ec22842

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

class Candidate_ec22842 extends Candidate {

    public static void main(String [] args){

        Candidate[]allCandidates = A3.getCandidateArray();
        ArrayList<Candidate> eligibleVoters = new ArrayList<Candidate>();
        String userChoice = "";
        
        while(!userChoice.equals("c")){
            
            userChoice = inputString("Would you like to a) add a specific candidate b) run the election?, c) to exit");

            if(userChoice.equals("a")){
                eligibleVoters = addCandidate(eligibleVoters,allCandidates);
                printCandidatesSl(eligibleVoters);
            }
            if(userChoice.equals("b")){
                String username_counter = inputString("Who shall count the votes?");
                Candidate userCounter = A3.getByUsername(username_counter, allCandidates);

                int nmbrOfElection = inputInt("How many times shall we run the election?");
                Candidate[] winners = new Candidate[nmbrOfElection];
                p(winners);
                
                for (int i = 0; i < nmbrOfElection; i++) {
                    winners[i] = runElec(allCandidates, eligibleVoters, username_counter);
            }
                Candidate mostFrWinner = mostFr(eligibleVoters.toArray(new Candidate[0]));

            }
        }
    }


    //runs elec
    public static Candidate runElec(Candidate[] allCandidates, ArrayList<Candidate> eligibleVoters, String voteCounter){
        
        Candidate[]votes = new Candidate[allCandidates.length];
        
        for (int i = 0; i < allCandidates.length; i++) {
            try{
                Candidate[] temp = eligibleVoters.toArray(new Candidate[0]);
                allCandidates[i].vote(temp);        
            
            }catch (Exception a){

            }
        }
         Candidate Counter = A3.getByUsername(voteCounter, allCandidates);
         return Counter.selectWinner(votes);
    }
    //returns most frequent winner of the election 
    public static Candidate mostFr(Candidate[]winner){
        
        int candidate_count = 0;
        int winning_C_count = 0;

        Candidate mostFr = winner [0];
        for (int i = 0; i < winner.length; i++) {
            for (int j = 0; j < winner.length; j++) {
                if(mostFr.equals(winner[j])){
                    candidate_count++;
                }
            }
            if (candidate_count > winning_C_count){
                winning_C_count = candidate_count;
                mostFr = winner[i];
            }
        }
        System.out.println("The most common winner is " + mostFr);
        
        return mostFr;
    }
    
    //used to look for a candidate in the list
    public static ArrayList<Candidate> addCandidate(ArrayList<Candidate> eligibleVoters, Candidate[] allCandidates){
        String username = inputString("Please enter a username. ");
        eligibleVoters.add(A3.getByUsername(username, allCandidates));
        return eligibleVoters;
    }
    
    //method used to print the candidates of eligible voters
    public static void printCandidatesSl(ArrayList<Candidate> eligibleVoters){
        System.out.println("Candidates are:");
        for (int i = 0; i < eligibleVoters.size(); i++) {
            System.out.println((i+1) + " " + eligibleVoters.get(i).getName());
        }
        return;
    }

    public static <T> void p(T s){
        System.out.print(s);
    }

    public static <T> void pr(T s){
        System.out.println(s);
    }
    
    //generate random number
    public static int generateRandomNmbr(int bound){
        Random r = new Random();
        return r.nextInt(bound);
    }

    public static int inputInt(String x){
        Scanner sc = new Scanner(System.in);
        System.out.println(x);
        int input = sc.nextInt();
        return input;
    }

    public static String inputString(String x){
        Scanner sc = new Scanner(System.in);
        System.out.println(x);
        String input = sc.nextLine();
        return input;
    }
    
    public String getName() {
        return "Burak";
    }
    
    public String getSlogan() {
        return "Help turkiye and syria";
    }
 
    public Candidate vote(Candidate[] candidates) {
        
        // If array is empty, return instance of friend's class.
        if (candidates.length == 0) 
            return new Candidate_ec22842();
        
        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("More trees!"))
                return c;
        
        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Burak"))
                return c;
        
        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        // If array is empty, return instance of friend's class.
        if (votes.length == 0) 
            return new Candidate_ec22842();
        
        // Default to first vote, but this will be over-written.
        Candidate currentWinner = votes[0];
        
        // Count the votes for each object in the array,
        // selecting one with the most.
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
