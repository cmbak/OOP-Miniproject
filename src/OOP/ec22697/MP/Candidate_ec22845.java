package OOP.ec22697.MP;

import java.util.Scanner;
import java.util.Random;

class Candidate_ec22845 extends Candidate {

    static boolean DEBUG = false; //ADDS DEBUG PRINTS 

    private static <T> void print (T msg) { System.out.print(msg); return; }

    private static <T> void println (T msg) { System.out.println(msg); return; }

    private static <T> void DEBUGprint (T msg) { 
        if (DEBUG) {
            System.out.println("// DEBUG //");
            System.out.println(msg); 
            System.out.println("// DEBUG //");
        }
         return;
    }

    private static void toggleDEBUG ()  { DEBUG = !DEBUG; println("*DEBUG messages: "+DEBUG+"*"); return; }

    private static <T> String inputString (T msg) {
        Scanner sc = new Scanner(System.in);
        
        println(msg);
        print("> ");

        String stringIn = sc.nextLine();
        return stringIn;
    }

    private static void printCandidateNames (Candidate[] candidates) 
    {
        println("Candidates are");

        if (candidates.length == 0) {
            println("None");
        }

        for (int i=0; i<candidates.length; i++)
        {
            try {
                println(candidates[i].getName());
            } catch (Exception e) {
                println("[INVALID CANDIDATE]");
            }
        }

        println("");

        return;
    }

    private static Candidate[] appendCandidateToArray (Candidate newCandidate, Candidate[] candidates) 
    {
        Candidate[] temp = new Candidate[candidates.length+1];

        for (int i=0; i<candidates.length; i++)
        {
            temp[i] = candidates[i];
        }

        temp[temp.length-1] = newCandidate;

        return temp;
    }

    private static boolean validateUsername (String username) 
    {
        Candidate candidate = new Candidate_ec22845();

        boolean validUsername = true;
        try {
            candidate = A3.getByUsername(username, A3.getCandidateArray());
        } catch (Exception e) 
        {
            println("Invalid username");
            validUsername = false;
        }

        if (candidate == null) 
        {
            println("Invalid username");
            validUsername = false;
        }

        return validUsername;
    }

    private static Candidate randomCandidate (Candidate[] candidates) 
    {
        Random rnd = new Random();
        
        Candidate randomCandidate = candidates[rnd.nextInt(candidates.length)];

        return randomCandidate;
    }

    private static Candidate[] countVotes (Candidate[] candidates) 
    {
        Candidate[] votes = new Candidate[candidates.length];
 
        for (int voter = 0; voter<candidates.length; voter++)
        {
            try {
                votes[voter] = candidates[voter].vote(candidates);
            } catch (Exception e) {
                println(("Vote" + candidates[voter].getName() +"failed"));
                votes[voter] = new Candidate_ec22845();
            }
                
        }

        return votes;
    }

    private static Candidate[] runElections (Candidate[] candidates, Candidate voteManager, int numberOfElections) 
    {   
        Candidate[] votes = countVotes(candidates);

        DEBUGprint("Votes");
        if (DEBUG) for (int i = 0; i<votes.length; i++)
        {
            println(candidates[i].getName() + "(" + (candidates[i]) + ") voted for " + votes[i].getName()  + "(" + (votes[i]) + ")");
        }

        Candidate[] winners = new Candidate[numberOfElections];

        try {
            for (int election = 0; election<numberOfElections; election++)
            {
                Candidate winner = voteManager.selectWinner(votes);

                DEBUGprint("Election " + (election+1) + " winner: " + winner.getName());

                winners[election] = winner;
            }
        } 
        catch (Exception e) 
        { 
            println("Election failed"); 
            return new Candidate[0];
        }

        return winners;
    }
    
    
    public static void main(String[] args) 
    {
        final String SENTINEL = "q";

        Candidate[] candidates = new Candidate[0];

        String option = "";
        while ( !option.equals(SENTINEL) ) 
        {
            printCandidateNames(candidates);

            println("Would you like to");
            println("a) add a specific candidate");
            println("b) add a candidate at random ");
            println("c) run the election");
            println("d) toggle DEBUG messages");
            println("q) exit");
            option = inputString("").toLowerCase();

            if (option.equals("q")) {;}//EXIT

            else if (option.equals("a")) //ADD SPECIFIC CANDIDATE
            {
                Candidate addCandidate = new Candidate_ec22845();

                boolean validUsername = false;
                while (!validUsername) {
                    option = inputString("Please enter a username.");

                    validUsername = validateUsername(option);
                }
                addCandidate = A3.getByUsername(option, A3.getCandidateArray());

                candidates = appendCandidateToArray(addCandidate, candidates);
            } 

            else if (option.equals("b")) //ADD RANDOM CANDIDATE
            {
                Candidate addCandidate = randomCandidate(A3.getCandidateArray());

                String username = addCandidate.getClass().getSimpleName();

                println(("Username: " + username.replace("Candidate_","")));

                candidates = appendCandidateToArray(addCandidate, candidates);

            }

            else if (option.equals("c")) //RUN ELECTION
            {
                option = "b";
                while (!option.equals("q") && !option.equals("a"))
                { 
                    if (option.equals("q") || option.equals("a")) {;} // EXIT or BACK TO MENU

                    else if (option.equals("b")) //RUN ELECTION MULTIPLE TIMES
                    {
                        Candidate voteManager = new Candidate_ec22845();

                        boolean validUsername = false;
                        while (!validUsername) {
                            option = inputString("Who should count the votes?");

                            validUsername = validateUsername(option);
                        }

                        voteManager = A3.getByUsername(option, A3.getCandidateArray());
                        
                        int numberOfElections = 0;

                        boolean validInput = false;
                        while (!validInput) // KEEP ASKING UNTIL A VALID INT IS ENTERED
                        {
                            try {
                                option = inputString("How many times shall we run the election?");

                                numberOfElections = Integer.parseInt(option);

                                validInput = true;

                            } 
                            catch (Exception e) { println("Invalid input"); }
                        }

                        Candidate[] winners = runElections(candidates, voteManager, numberOfElections);

                        Candidate winner = voteManager.selectWinner(winners);

                        DEBUGprint("Winners");
                        if (DEBUG) printCandidateNames(winners);

                        println("Most common winner is " + winner.getName() + ".");
                        
                    }
                    else {
                        println("This is not an option, or is not yet implemented");
                    }

                    println("Would you like to");
                    println("a) go back to menu");
                    println("b) run same election many times");
                    //println("c) check who counts honestly [NOT IMPLEMENTED]");
                    //println("d) run all possible two-candidate elections [NOT IMPLEMENTED]");
                    //println("e) run a higher-order election [NOT IMPLEMENTED]");
                    println("q) exit");
                    option = inputString("").toLowerCase();
                }
            } 
            else if (option.equals("d")) { toggleDEBUG(); } // TOGGLE DEBUG MESSAGES
            else { println("Invalid input"); }
        }
    }

    public String getName() {
        return "John Doe";
    }
    
    public String getSlogan() {
        return "I don't really want to win tbh.";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22845(); //vote for self

        return r;
    }

    public Candidate selectWinner(Candidate[] votes) {
        if (votes.length == 0)      // If nothing in the array
        {
            return new Candidate_ec22845(); // Claim I won.
        }
        
        // Default to first item.
        Candidate winner = votes[0]; 

        // Track highest votes currently
        int currentHighest = 0;

        //Loop over every vote
        for (int i=0; i<votes.length; i++)
        {
            int currentCount = 0;

            //count how many times a vote has been repeated so far
            for (int j=0; j<i; j++)
            {
                if (votes[i].getName() == votes[j].getName()) {currentCount++;}
            }

            // keep track of current highest count (winner)
            if (currentCount > currentHighest)
            {
                currentHighest = currentCount;
                winner = votes[i];
            }
        }
    
        return winner;
    }
}