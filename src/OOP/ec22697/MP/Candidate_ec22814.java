package OOP.ec22697.MP;// File Candidate_ec22814.java
//
// Machine generated stub for Assignment 3

import java.util.Random;
import java.util.Scanner;

public class Candidate_ec22814 extends Candidate {
    
    public String getName() {
        return "Darien";
    }
    
    public String getSlogan() {
        return "and dey say chivalry is dead";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        // If array is empty, return instance of friend's class.
        if (candidates.length == 0) 
            return new Candidate_ec22814();
        
        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("and dey say chivalry is dead"))
                return c;
        
        // Vote for random otherwise
        Random r = new Random();
        int c = r.nextInt(candidates.length);
        return candidates[c];
        
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        // If array is empty, return instance of friend's class.
        if (votes.length == 0) 
            return new Candidate_ec22814();
        
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

    
    //generic input method that returns a string.
    public static String inputAnswer (String Question)
    {
        String answer;
        Scanner scanner = new Scanner(System.in);

        System.out.println(Question);    

        return answer=scanner.nextLine();
    }

    //check if input is either letter a or b or c
    public static boolean validAorBorC (String input)
    {
        boolean valid=false;
        if (input.length()==1)
        {
            if (input.equals("a") | input.equals("b") | input.equals("c") )
            {
                valid=true;
            }
        }

        return valid;
            
    }
    
    //check if string is a number from 0 and above
    public static boolean validNum(String a)
    {
        int i=0;
        int stringLength= a.length();
        boolean loop=true;
        boolean number=true;

        while (loop)
        {
            if (i==(stringLength-1))
            {
                loop=false;
            }

            if ( a.charAt(i)=='0' ||a.charAt(i)=='1' ||a.charAt(i)=='2' ||a.charAt(i)=='3' ||a.charAt(i)=='4'
               ||a.charAt(i)=='5' ||a.charAt(i)=='6' ||a.charAt(i)=='7' ||a.charAt(i)=='8' ||a.charAt(i)=='9')
            {
                i+=1;
            }
            else
            {
                number=false;
                loop=false;
            }
        }
        return number;
    }
    
    public static int numOfCandidateBallot()
    {
        String nSCandidates;
        int numCandidates;
        
        nSCandidates=inputAnswer("What should be the limit to the amount of candidates on the ballot?");

        while ( !(validNum(nSCandidates)) | nSCandidates.equals("0") )
        {
            System.out.println("Enter a valid number!");
            System.out.println("");
            nSCandidates=inputAnswer("What should be the limit to the amount of candidates on the ballot?");
        }
        
        numCandidates=Integer.parseInt(nSCandidates);
        
        return numCandidates;
    }

    public static void main (String[] args) {

        Candidate[] all = A3.getCandidateArray();
        
        String input;
        
        int numCandidates=numOfCandidateBallot();
        Candidate[] selectedCandidates= new Candidate[numCandidates];
        
        boolean loop=true;
        while (loop)
        {
            System.out.println("");
            input = inputAnswer("Do you want to    a) Add candidates to the ballot    b) Run an election    c) Exit");
            if (validAorBorC(input))
            {
                if (input.equals("a"))
                {
                    selectedCandidates=addCandidates(selectedCandidates, all, numCandidates);
                }
                else if (input.equals("b") & selectedCandidates[0]==null)
                {
                    System.out.print("You need to add a candidate first");
                }
                else if (input.equals("b"))
                {
                    runElection(selectedCandidates,all);
                }
                else
                {
                    return;
                }
            }    
            else 
            {
                System.out.println("Enter only a, b or c!");
            }
        }
        
        return;
        
    }
    
    //add candidates to an array
    public static Candidate[] addCandidates(Candidate[] selected, Candidate[] all, int numCandidates) {

        boolean loop;
        String answer;
        Candidate chosen;
        
        for (int i=0;i<numCandidates;i++)
        {
            loop=true;
            while (loop)
            {
                answer= inputAnswer("Please enter a username");
                chosen = A3.getByUsername(answer,all);

                if (chosen != null)
                {
                    selected[i]=chosen;
                    loop=false;
                }
                else
                {
                    System.out.println("Username not found, try again");
                }

            }
                
        }
    
        System.out.println("The candidates are: ");
        for (int i=0;i<numCandidates;i++)
        {
            System.out.println( (i+1)+ "." +selected[i].getName() );
        } 

        return selected;

    }

    //determine the most common winner
    public static void runElection(Candidate[] selected, Candidate[] all) {

        boolean loop=true;
        String chosenVote;
        Candidate chosenVoteC=all[0];
        
        while (loop)
        {
            chosenVote = inputAnswer("Who should count the votes?");
            chosenVoteC = A3.getByUsername(chosenVote,all);

            if (chosenVoteC != null)
            {
                loop=false;
            }
            else
            {
                System.out.println("Username not found, try again");
            }

        }

        String numOfE=inputAnswer("How many times should the election run");
        while ( !(validNum(numOfE)) | numOfE.equals("0") )
        {
            System.out.println("Enter a valid number!");
            numOfE=inputAnswer("How many times should the election run");
        }

        int NumOfElections=Integer.parseInt(numOfE);
        
        Candidate[] votes = new Candidate[all.length];
        Candidate[] winners = new Candidate[NumOfElections];
        
        for (int i=0;i<all.length;i++)
        {
            try { 
                votes[i]=all[i].vote(selected);
            } catch (Exception e) { 
                votes[i]=all[374].vote(selected); 
            }
            
        }
        
        for (int i=0; i<NumOfElections;i++)
        {
            winners[i] = chosenVoteC.selectWinner(votes);
        }


        int highestCounter=0;
        Candidate currentCandWinner=winners[0];
        
        for (int i=0;i<winners.length;i++)
        {
            int counter=0;
            for (int x=0;x<winners.length;x++)
            {
                if (winners[i].equals(winners[x]))
                {
                    counter++;
                }
                
                if (counter>highestCounter)
                {
                    highestCounter=counter;
                    currentCandWinner=winners[i];
                }

            }
            
        }
        
        

        System.out.println("The most common winner was " + currentCandWinner.getName() );

        return;

    }
}
