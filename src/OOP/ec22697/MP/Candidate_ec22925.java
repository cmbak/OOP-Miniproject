package OOP.ec22697.MP;// File Candidate_ec22925.java
//
// Machine generated stub for Assignment 2

import java.util.Scanner;
import java.util.Random;

class Candidate_ec22925 extends Candidate {
    
    public String getName() {
        return "Daniil";
    }
    
    public String getSlogan() {
        return "Emmet is the best!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22925();
        
        if (candidates.length != 0)
        {
            r = candidates[0];
        }
        
        for (Candidate c : candidates)
        {
            if (c.getSlogan().equals("Emmet is the best!"))
            {
                return c;
            }
        }
            
        for (Candidate c : candidates)
        {
            if (c.getName().equals("Megatron"))
            {
                return c;
            }
        }
        
        return r;
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22925();
        int highestCount = 0;

        if (votes.length == 0) 
        {
            return r;
        }
            
        for (Candidate c : votes) {
            
            int count = 0;
            for (Candidate x : votes)
            {
                if (x == c)
                {
                    count++;
                }
            }
                
            if (count > highestCount) 
            {
                highestCount = count;
                r = c;
            }
        }
        
        return r;
    }

    private static <T> void p(T s) { System.out.print(s);}
    private static <T> void pr(T s) { System.out.println(s);}

    private static String getInput(){
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    private static boolean proper(Person p) {
        return !(p.getName().length() > 6 && p.getName().substring(0,7).equals("No name"));
    }

    //Returns a specific Candidate
    private static Candidate getCandidate(Candidate[] allCandidates)
    {
        String username = "";
        Candidate newCandidate = null;

        do
        {
            username = getInput();
            newCandidate = A3.getByUsername(username, allCandidates);
            if(newCandidate == null)
            {
            pr("A candidate with such username could not be found! Please try again: ");
            }
        }
        while(newCandidate == null);
        
        return newCandidate;
    }

    //Returns a random Candidate
    private static Candidate getRandomCandidate(Candidate[] allCandidates)
    {
        Candidate randomCandidate = null;

        do
        {
            randomCandidate = allCandidates[new Random().nextInt(allCandidates.length)];
        }
        while(!proper(randomCandidate));

        return randomCandidate;
    }

    //Prints the candidates in the current array of candidates
    private static void printCandidates(Candidate[] allCandidates){
        pr("The following candidates are in the election:");
        if(allCandidates != null && allCandidates.length > 0){
            for(Candidate candidate : allCandidates)
            {
                pr(candidate.getName() + " : " + candidate.getSlogan());
            }            
        }
        else{
            pr("The list of candidates is empty!");
        }
    }

    //Gets the most repeated Candidate in the Winner's array
    private static Candidate getMostRepeatedWinner(Candidate[] winnerCandidates){
        Candidate mostRepeatedCandidate = null;
        int highestRepeatedCandidateNumber = 0;

        for(Candidate c : winnerCandidates) {
            int count = 0;
            if(c != null)
            {
                for(Candidate x : winnerCandidates)
                {
                    if(x == c)
                    {
                        count++;
                    }
                    if(count >= highestRepeatedCandidateNumber) 
                    {
                        highestRepeatedCandidateNumber = count;
                        mostRepeatedCandidate = c;
                    }
                }   
            }
        }
        if(mostRepeatedCandidate == null)
        {
            pr("This is the first candidate in the list as not winners could be found: ");
            mostRepeatedCandidate = winnerCandidates[0];
        }

        return mostRepeatedCandidate;
    }

    //Runs an election a set number of times
    private static void runElection(Candidate[] allCandidates, Candidate[] newCandidates)
    {
        int electionsCount = 0;
        Candidate[] votedForCandidates = new Candidate[allCandidates.length];
        Candidate[] winnerCandidates;
        Candidate winner = null;

        pr("How many times should the election run?: ");
        do{
            electionsCount = Integer.parseInt(getInput());
            if(electionsCount <= 0)
            {
                pr("Please input a positive integer!");
            }
        }
        while(electionsCount <= 0);

        winnerCandidates = new Candidate[allCandidates.length];


        pr("Who should count the votes?: ");
        Candidate candidateCounter = getCandidate(allCandidates);

        for(int i = 0; i < electionsCount; i++) 
        {
            for(int j = 0; j < allCandidates.length; j++) 
            {
                try
                {
                    votedForCandidates[j] = allCandidates[j].vote(newCandidates);
                }
                catch(ArrayIndexOutOfBoundsException ex)
                {
                    
                }
                
            }
            winnerCandidates[i] = candidateCounter.selectWinner(votedForCandidates);
        }

        winner = getMostRepeatedWinner(winnerCandidates);

        pr("The winner is: ");
        pr(winner.getName() + " : " + winner.getSlogan());
    }

    //Main methods to add Candidates and run election
    public static void main(String[] args){
        Candidate[] allCandidates = A3.getCandidateArray();
        //FOR TESTING PURPOSES

        Candidate[] testCandidates = new Candidate[100];
        for(int i = 0; i < 100; i++)
        {
            testCandidates[i] = getRandomCandidate(allCandidates);
        }
        //FOR TESTING PURPOSES
        Candidate[] newCandidates = null;
        Candidate newCandidate = null;

        String choice = "";

        while(choice != "c")
        {
            p("Would you like to: \n a: Add a candidate \n b: Add a random candidate \n c: Run the election \n");
            
            choice = getInput();

            //Finds and adds the new candidate to a list of newCandidates
            if(choice.equalsIgnoreCase("a"))
            {
                pr("Enter the username of the candidate you wish to add: ");
                newCandidate = getCandidate(allCandidates);
            }
            //Finds and adds the new random candidate to a list of newCandidates
            else if(choice.equalsIgnoreCase("b"))
            {
                newCandidate = getRandomCandidate(allCandidates);
            }
            //Runs the election
            else if(choice.equalsIgnoreCase("c"))
            {
                if(newCandidates != null && newCandidates.length > 1)
                {
                    runElection(allCandidates, newCandidates);
                    //runElection(testCandidates, newCandidates);
                }
                else
                {
                    pr("You should add some candidates before running an election!");
                }
            }

            if(!choice.equalsIgnoreCase("c"))
            {
                //Prints the new candidates list if a new candidate is added successfully
                if(newCandidate != null && newCandidates != null)
                {
                    Candidate[] oldCandidates = newCandidates;
                    newCandidates = new Candidate[newCandidates.length + 1];
                    for(int i = 0; i < oldCandidates.length; i++)
                    {
                        newCandidates[i] = oldCandidates[i];
                    }

                    newCandidates[oldCandidates.length] = newCandidate;
                    printCandidates(newCandidates);
                }
                else
                {
                    newCandidates = new Candidate[]{newCandidate};
                    printCandidates(newCandidates);
                }
            }
        }
    }

    
}
