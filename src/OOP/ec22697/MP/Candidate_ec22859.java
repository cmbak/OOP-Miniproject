package OOP.ec22697.MP;

import java.util.ArrayList; // Used for candidates array.
import java.util.HashMap; // Making hashmaps
import java.util.Map; // For some reason this is needed to make entrysets for hashmaps?
//import java.util.Arrays; // Used for making dynamic arrays.  (Long way)
import java.util.Scanner; // Makes input available.
import java.util.concurrent.ThreadLocalRandom; // Used to generate random numbers within a given range.
// File Candidate_ec22859.java
//


// This is a line I added for Week 4 Lab, change to commit to my branch on GitHub.

// Machine generated stub for Assignment 2

class Candidate_ec22859 extends Candidate {

    // Use Standard Out.
    private static <T> void p(T s) { System.out.print(s);}
    private static <T> void pr(T s) { System.out.println(s);}
    
    // Use Standard In.
    private static Scanner sc() {return new Scanner(System.in);}

    // Randomiser
    private static int rando(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max+1);
    }

    public static void main(String[] args){
        Candidate[] everyone = A3.getCandidateArray();
        ArrayList<Candidate> candidates = new ArrayList<Candidate>();
        Candidate voteCounter = new Candidate_ec22859();
        boolean stop = false;
        String uInput = "";
        int uInputInt = 0;
        int c_count = 0;


        // Where the actual program runs, quits when user input is d.
        while(!stop){
            pr("Would you like to a) add a specific candidate b) add a candidate at random c) run the election d) quit?");
            uInput = sc().nextLine();

            if(uInput.equalsIgnoreCase("a")){
                pr("Enter the user name of the candidate: ");
                uInput = sc().nextLine();
                
                // Try to add the user by name (if exists)
                try {
                    candidates.add(c_count, A3.getByUsername(uInput, everyone));
                    pr(candidates.get(c_count).getName() + " was added as a candidate!");
                    c_count =+1;
                } catch (Exception e) {
                    // handle exception
                    pr("That candidate was not found!");
                }
            }
            else if(uInput.equalsIgnoreCase("b")){
                // random candidate adder.
                int rand = rando(0, everyone.length-1);
                candidates.add(c_count, everyone[rand]);
                pr(candidates.get(c_count).getName() + " was added as a candidate!");
                c_count =+1;
            }
            else if(uInput.equalsIgnoreCase("c")){
                // election runner.
                if(candidates.isEmpty()){
                    pr("No candidates were selected :(");
                }
                else{
                    pr("=== Running The Election ===");
                    pr("The candidates are :");
                    for(int i = 0; i < candidates.size(); i++){
                        pr((i+1) + ". " + candidates.get(i).getName());
                    }
                    pr("=== Do you want to change vote counter? Y/N (ec22859 is default) ===");
                    uInput = sc().nextLine();
                    if(uInput.equalsIgnoreCase("y")){
                        pr("Enter username:");
                        uInput = sc().nextLine();
                        try {
                            voteCounter = A3.getByUsername(uInput, everyone);
                        } catch (Exception e) {
                            // handle exception
                            pr("username not found, defaulting to ec22859...");
                        }
                    }
                    pr("=== Voter changed to " + voteCounter.getName() + " ===");
                    pr("=== How many rounds of election ?");
                    uInputInt = strToInt();
                    Candidate finalWinner = startElection(candidates, everyone, voteCounter, uInputInt);
                    pr("=== " + finalWinner.getName() + " won ===");
                }
            }
            else if(uInput.equalsIgnoreCase("d")){
                // quit.
                return;
            }
        }
        return;
    }


    // Turns String to Int, at least it tries to.
    public static int strToInt(){
        String input = sc().nextLine();
        try {
            if(Integer.parseInt(input) < 0){
                pr("That wasnt a number greater than 0, try again:");
                return strToInt();
            }
            else{
                return Integer.parseInt(input);
            }
            
        } catch (NumberFormatException e) {
            // handle exception
            pr("That wasnt a number greater than 0, try again:");
            return strToInt();
        }
    }

    // Counts the most common winner
    public static Candidate countMostCommon(ArrayList<Candidate> winnersCAND){
        // Hashmamp to keep a track of the number of wins for each candidate.
        Map<Candidate,Integer> winnerCountMap = new HashMap<Candidate, Integer>();

        // Adding all the elements of winners into hashmap, if duplicate, add one to the existing count.
        for(int i = 0; i < winnersCAND.size()-1; i++){
            if(winnerCountMap.containsKey(winnersCAND.get(i))){
                winnerCountMap.put(winnersCAND.get(i), winnerCountMap.get(winnersCAND.get(i))+1);
            }
            else{
                winnerCountMap.put(winnersCAND.get(i), 1);
            }
        }
       
        // Finding most frequent winner by going through all the entries in the map.
        int countFreq = 1;
        Candidate countCand = winnersCAND.get(0);
        String winString = "";
        for(Map.Entry<Candidate, Integer> entry :winnerCountMap.entrySet()){

            if(entry.getValue() > countFreq){
                countCand = entry.getKey();
                countFreq = entry.getValue();
            }
            else if(entry.getValue() == countFreq){
                winString = winString + ", " + countCand.getName();
            }
        }

        pr("The most common winner is " + countCand.getName() + " with " + (countFreq) + " number of wins!");
        pr("All other candidates with equal votes (bonus winners): " + winString);
        return countCand;
    }

    /*
     *
     * The election is really simple all it really does
     * is it simply gets all the voters, gets them to random vote a candidate
     * puts those votes all in an array and then sends it to the counter guy
     * who then selects the winner.
     * 
     */
    public static Candidate startElection(ArrayList<Candidate> candidates, Candidate[] everyone, Candidate voteCounter, int rounds){
        Candidate[] votes = new Candidate[everyone.length];
        ArrayList<Candidate> winnerCANDS = new ArrayList<Candidate>();
        for(int j = 0; j < rounds; j++){
            for(int i = 0; i < everyone.length-1; i++){
                int votedFor = rando(0, candidates.size()-1);
                votes[i] = candidates.get(votedFor);
            }
            Candidate winner = voteCounter.selectWinner(votes);
            winnerCANDS.add(0, winner);
            pr(winner.getName() + " WON ROUND ("+(j+1)+")");
        }
        Candidate finalW = countMostCommon(winnerCANDS);
        return finalW;
    }




    public String getName() {
        return "Sep";
    }
    
    public String getSlogan() {
        return "Buy The Rugs!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        // if array is empty return instance of my own class
        if (candidates.length == 0)
        {
            return new Candidate_ec22859();
        }
        
        // search for a like minded candidate
        for (Candidate c : candidates)
        {
            if (c.getSlogan().equals("Buy The Rugs!"))
            {
                return c;
            } 
        }
        
        // search for myself otherwise
        for (Candidate c : candidates)
        {
            if (c.getName().equals("Sep"))
            {
                return c;
            } 
        }
        
        // otherwise return last candidate on list
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        // if array is empty return instance of myself.
        if (votes.length == 0) 
            return new Candidate_ec22859();
        
        // Default to last vote, but this will be over-written.
        Candidate currentWinner = votes[votes.length-1];
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

        pr(currentWinner.getName());
        
        return currentWinner;
    }
    
}
