package OOP.ec22697.MP;// File Candidate_ec22621.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;
class Candidate_ec22621 extends Candidate {
    
    //input method for strings
    public static String inputString(String message) {
        Scanner s = new Scanner(System.in);
        System.out.println(message);
        String response = s.nextLine();
        return response;
    }
    
    public static void main( String[] args) {
        //get all candidates
        Candidate[] allCandidates = A3.getCandidateArray();
        Candidate[] votes = new Candidate[allCandidates.length];
        
        final String END = "QUIT";
        
        //asks user what they want to do
        String usrChoice = inputString("Would you like to \na) Quit \nb) add a specific candidate \nc) add a candidate at random \nd) run the election?").toLowerCase();
        int counter = 0;
        
        while (! usrChoice.equals(END)){ 
            //quit
            if (usrChoice.equals("a")) { 
                usrChoice = END;
                break;
            }

            //add specific candidate
            else if (usrChoice.equals("b")) {            
                String username = inputString("Please enter the username: ");
                Candidate c = A3.getByUsername(username, allCandidates);
                if (c == null) {System.out.println("Not found");}
                else {
                    votes[counter] = c;
                    counter++;
                }
            }

            //add random candidate
            else if (usrChoice.equals("c")) {
                Random rs = new Random();
                int randomInt = rs.nextInt(allCandidates.length);
                votes[counter] = allCandidates[randomInt];
                counter++;
            }
            
            else if (usrChoice.equals("d")) {
                String voteCountr = inputString("Who should count the votes?");
                Candidate voteCounter = A3.getByUsername(voteCountr, allCandidates);
                int numTimes = Integer.parseInt(inputString("How many times shall we run the election?"));
                
                Candidate[] newElection = new Candidate[counter];
                for (int i = 0; i < counter; i++) {
                    newElection[i] = votes[i];
                }
                
                Candidate[] winningCandidates = new Candidate[(numTimes * counter)];
                
                //Repeat as many times as the user requests
                for (int i = 0; i < numTimes; i++) {
                    for (int j = 0; j < counter; j++) {
                        try {
                            winningCandidates[(counter * i) + j] = newElection[j].vote(newElection);
                        }
                        catch (Exception e) {
                            //If voting doesn't work, vote for myself
                            winningCandidates[(counter * i) + j] = new Candidate_ec22621();
                        }
                    }
                }
                Candidate actualWinner = voteCounter.selectWinner(winningCandidates);
                //Prints out the winner
                System.out.println("Most common winner is: " + actualWinner.getName());
            }
            else {System.out.println("Input error"); }
            usrChoice = inputString("Would you like to \na) Quit \nb) add a specific candidate \nc) add a candidate at random \nd) run the election?").toLowerCase();
        }
    }
    public String getName() {
        return "Tafsir";
    }
    
    public String getSlogan() {
        return "Slogan...";
    }
    
    public Candidate vote(Candidate[] candidates) {
        if (candidates.length == 0) 
        {
            return new Candidate_ec22621();
        }
        
        //if Walter White found, return it
        for (Candidate c : candidates)
        {
            if (c.getName().equals("Walter White"))
            {
                return c;
            }
        }
        
        // otherwise random pick
        Random r = new Random();
        int c = r.nextInt(candidates.length);
        return candidates[c];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        
        if (votes.length == 0)
        {
            return new Candidate_ec22621();
        }
        
        // default
        Candidate r = votes[0];
        
        // checks highest
        int highestCount = 0;
        for (Candidate c : votes) 
        {            
            int count = 0;
            for (Candidate x : votes)
            {
                if (x == c)
                {
                    count++;
                }
            }
            if (count >= highestCount)
            {
                highestCount = count;
                r = c;
            }
        }        
        return r;
    }
    
}
