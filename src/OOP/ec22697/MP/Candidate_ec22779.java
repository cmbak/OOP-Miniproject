package OOP.ec22697.MP;// File Candidate_ec22779.java
//
// Machine generated stub for Assignment 2

import java.util.Scanner;

class Candidate_ec22779 extends Candidate {
    
    public static int IntInput(String Message)
    {
        Scanner keyboard = new Scanner(System.in);
        printString(Message);
        int UserInput = keyboard.nextInt();
        return UserInput;
    }
    
    public static String StringInput(String Message)
    {
        Scanner keyboard = new Scanner(System.in);
        printString(Message);
        String UserInput = keyboard.nextLine();
        return UserInput;
    }
    
    public static void printString(String Message)
    {
        System.out.println(Message);
        return;
    }
    
    public static void main(String[] args)
    {
        Candidate[] allCandidates = A3.getCandidateArray();
        Candidate[] newArrayCandidates = new Candidate[allCandidates.length];
        int counter= 0;
        boolean exitProg = false;
        while(exitProg = false)
        {
            
            printString("= Running Repeated Elections =");
            printString("Would you like to \n a. Add a specific candidate \n b. Run the election \n c. Exit?");
            String UserInput = StringInput("Enter choice: ");
            if(UserInput.equals("a"))
            {
                counter = addCandidate(allCandidates, newArrayCandidates, counter);
            }
            else if(UserInput.equals("b"))
            {
                runCandidateElection(allCandidates, newArrayCandidates, counter);
            }
            else if(UserInput.equals("c"))
            {
                exitProg = true;
                break;
            }
            else
            {
                printString("Please either select a, b or c.");
            }
        }
        return;
    }
    
    public static int addCandidate(Candidate[] allCandidates, Candidate[] newArrayCandidates, int counter)
    {
        String UserInput = StringInput("Please add a candidate to add: ");
        Candidate candidateUsername = A3.getByUsername(UserInput, allCandidates);
        if(candidateUsername == null)
        {
            printString("I'm sorry, but I could not find that person.");
            return counter;
        }
        else
        {
            allCandidates[counter] = candidateUsername;
            counter = counter + 1;
            return counter;
        }
    }
    
    public static void runCandidateElection(Candidate[] allCandidates, Candidate[] newArrayCandidates, int counter)
    {
        String arbitrator = StringInput("Please enter who you would like to become The Arbitrator and count the votes: ");
        Candidate arbitratorObject = A3.getByUsername(arbitrator, newArrayCandidates);
        if(arbitratorObject != null)
        {
            int newCounter = IntInput("Please enter how many times you would like the election to be run as an integer: ");
            Candidate[] ElectionWinners = new Candidate[newCounter];
            for(int x=0; x<newCounter; x++)
            {
                Candidate[] MoreCandidates = newArrayMethod(allCandidates, counter);
                Candidate[] VotingsArray = new Candidate[newArrayCandidates.length];
                for(int i=0; i<newArrayCandidates.length; i++)
                {
                    VotingsArray[i] = allCandidates[i].vote(MoreCandidates);
                }
                ElectionWinners[x] = arbitratorObject.selectWinner(VotingsArray);
            }
            ChooseWinner(ElectionWinners);
        }
        else
        {
            printString("I'm sorry, but I could not find that candidate.");
        }
        return;
    }
    
    private static Candidate[] newArrayMethod(Candidate[] allCandidates, int counter)
    {
        Candidate[] newArrayCreated = new Candidate[counter];
        for(int y=0; y<counter; y++)
        {
            newArrayCreated[y] = allCandidates[y];
        }
        return newArrayCreated;
    }
    
    private static void ChooseWinner(Candidate[] ElectionWinners)
    {
        boolean TwoPlusWinners = false;
        String name = "";
        if(ElectionWinners.length != 0)
        {
            Candidate[] WinnersCountArray = newArrayMethod(ElectionWinners, ElectionWinners.length);
            int highC = 0;
            for(int x=0; x<WinnersCountArray.length; x++)
            {
                Candidate winner = WinnersCountArray[x];
                if(winner != null)
                {
                    int counter = 0;
                    for(int i=0; i<WinnersCountArray.length; i++)
                    {
                        Candidate y = WinnersCountArray[i];
                        if(y != null && y.getName()== winner.getName())
                        {
                            WinnersCountArray[i] = null;
                            counter = counter + 1;
                        }
                    }
                    if(counter == highC)
                    {
                        name = name + ", " + winner.getName();
                        TwoPlusWinners = true;
                    }
                    if(counter > highC)
                    {
                        highC = counter;
                        name = winner.getName();
                        TwoPlusWinners = false;
                    }
                }
            }
        }
        if(name != null)
        {
            if(TwoPlusWinners == true)
            {
                printString("The most common winners are:");
                System.out.println(name);
            }
            else
            {
                printString("The most common winner is:");
                System.out.println(name);
            }
        }
        return;
    }
       
    public String getName() {
        return "Dave";
    }
    
    public String getSlogan() {
        return "Dave for PM!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        // If array is empty, return instance of friend's class.
        if (candidates.length == 0) 
            return new Candidate_ec22779();
        
        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("Dave for PM!"))
                return c;
        
        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Dave"))
                return c;
        
        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        // If array is empty, return instance of friend's class.
        if (votes.length == 0) 
            return new Candidate_ec22779();
        
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

