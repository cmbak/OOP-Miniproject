package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22442 extends Candidate {
    
    public static String inputString (String m)
    {
       String userInput;
       Scanner scanner = new Scanner(System.in);

       System.out.println(m);
       userInput = scanner.nextLine();
   
       return userInput;
    }
    
    public static int inputInt(String m)
    {
        String userInput = inputString(m);
        return Integer.parseInt(userInput);
    }
    
    public String getName() {
        return "Robin";
    }
    
    public String getSlogan() {
        return "Plant more trees!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22442();
        if (candidates.length != 0) r = candidates[0];
        return r;
    }

    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22442();
        
        if (votes.length != 0) r = votes[0];
        return r;
    }
    
    public static Candidate[] getCandidateArray() {
    Candidate[] candidates = new Candidate[3];
    candidates[0] = new Candidate_ec22442();
    candidates[1] = new Candidate_ec22442();
    candidates[2] = new Candidate_ec22442();
    return candidates;
    }

    
    public static void main(String[] args) {
        System.out.println("= Running Repeated Elections =");
        
        Candidate[] all = getCandidateArray();
        String option =  inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election d) exit?");
        while(!option.equals("d"))
        {
            if(option.equals("a"))
            {
                String userCandidate = inputString("Please enter a username.");
                for (Candidate c : all) {
                    if (c.un.equals(userCandidate)) {
                        System.out.println("Candidate found: " + c.getName());
                        return;
                    }
                }
            }
            else if(option.equals("b"))
            {
                Random r = new Random();
                int c = r.nextInt(all.length);
                System.out.println("Random candidate selected: " + all[c].getName()); 
            }
            else if(option.equals("c"))
            {
                String userCandidate = inputString("Who should run the election?");
                Candidate[] votes = new Candidate_ec22442[10];
            
                int RUNS = inputInt("How many times should we run the election?");
                Candidate currentWinner = votes[0];
                for(int i = 0; i<RUNS; i++) {
                    int highestCount = 0;
                    for (Candidate c : votes) {
                        int count = 0;
                        for (Candidate x : votes)
                            if (x == c)
                            count++;
                        if (count >= highestCount) {
                        highestCount = count;
                        currentWinner = c;
                        }
                    }
                }
                System.out.println("Most common winner is " + currentWinner);
            }
        }
    }
}
