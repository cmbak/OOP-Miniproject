package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;
class Candidate_ec22541 extends Candidate {


 
    public String getName() {
        return "Mainushaan";
    }
    
    public String getSlogan() {
        return "Less assignments!";
    }
    
    
    public static void main(String[] args){
        // create candidate object
        Candidate[] allCandidates = A3.getCandidateArray();
        // create candidate array
        Candidate[] votees = new Candidate[allCandidates.length];
        // array to hold user ID in the same position as usernames
        String[] userIDArray = new String[allCandidates.length];
        // used to check if candidate ID is unique
        int newCandCheck = 0;
        int arrayPos = 0;   

        boolean valid = false;
        System.out.println("= Running Repeated Elections =");
        String newCand = "";
        while (!valid)
        {
        
            System.out.println("Candidates are: ");
            if (arrayPos!=0)
            {
                for (int i = 0; i<arrayPos; i++)
                {
                    System.out.println(votees[i]);
                }
            }
            if (arrayPos == 0)
            {
                System.out.println("None");
            }
        
            char userInput = charInput("Would you like to a) add a specific candidate, b) add a candidate at random, c) run the election?");
            if (userInput == 'a')
            {
                    while (newCandCheck == 0)
                    {
                        newCand = stringInput("Please enter a valid username");
                        // checkCand used to check if candidate is unique
                        newCandCheck = checkCand(newCand, userIDArray, arrayPos);
                    }
                    // create candidate object
                    Candidate newVote = A3.getByUsername(newCand, allCandidates);
                    // store into array (votees) (userIDArray
                    votees[arrayPos] = newVote;
                    userIDArray[arrayPos] = newCand;
                    arrayPos++;
                    printInfo(votees);
            }
            else if (userInput == 'b')
            {
                votees = randomCandidate(votees, allCandidates, arrayPos);
                arrayPos++;
            }
            else if (userInput == 'c')
            {
                election(votees, allCandidates, arrayPos);

                boolean valid2 = false;

                while (!valid2)
                {
                    char option = charInput("Would you like to a) start another elction b) quit?");

                    if(userInput == 'a')
                        valid2 = true;
                    else if(option == 'b')
                    {
                        valid = true;
                        valid2 = true;
                    }
                    else 
                        System.out.println("Invalid option. Please try again.");
                }
            }
            else 
                System.out.println("Inavlid option. Please try again.");
        }

    }
    

            
            



                                                                                                                 
                                                     
    // needs to be edited                                                 
    public static int checkCand(String candToCheck, String[] arrayOfID, int arrayPosition)
    {
        if (candToCheck.equals(arrayOfID[arrayPosition]))
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

public static Candidate[] randomCandidate (Candidate[] candidates, Candidate[] ca, int candidate_num)
{
    Random ran = new Random();
    int random = ran.nextInt(ca.length);
    Candidate randomC = ca[random];

    candidates[candidate_num] = randomC;

         return candidates;
    }
    
    public static void election (Candidate[] candidates, Candidate[] allCandidates, int candidate_num)
    {
        Candidate[] fullCandidates = new Candidate[candidate_num];
        for(int i=0;i<candidate_num;i++)
             {
                 fullCandidates[i]=candidates[i];
             }

        String name = stringInput("Who should count the votes?");
        Candidate chosen = A3.getByUsername(name, allCandidates);

        int numRuns = intInput("How many times shall we run the election?");
        Candidate[] winners = new Candidate[numRuns];
        for (int i=0; i<numRuns; i++)
        {
            Candidate[] votes = new Candidate[allCandidates.length];
            for (int j=0; j<allCandidates.length; j++)
            {
                     try
                     {
                         votes[j] = allCandidates[j].vote(fullCandidates);
                     }
                     catch (Exception e)
                     {

                     }
            }
                winners[i]=chosen.selectWinner(votes);
        }

        int highestCount = 0;
        Candidate[] overallWinner = new Candidate[fullCandidates.length];
        int winnerCount = 1;
        for (int i=0;i<fullCandidates.length;i++)
        {
            int count = 0;
            for (int j=0;j<winners.length;j++)
            {
                if(winners[j].equals(fullCandidates[i]))
                {
                    count++;
                }
            }
            if(count>highestCount)
            {
                highestCount=count;
                overallWinner[0] = candidates[i];
                winnerCount=1;
            }
            else if(count==highestCount)
            {
                winnerCount++;
                overallWinner[winnerCount-1] = candidates[i];
            }
        }

        if(winnerCount==1)
        {
            System.out.println("Most common winner is " + overallWinner[0] + ".");
            System.out.println("There were no other winners.");
        }
        else
        {
            System.out.println("Most common winners:");
            for(int i=0;i<winnerCount;i++)
            {
                System.out.println(overallWinner[i]);
            }
        }

        return;
    }
    
    
    public static String stringInput(String message)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        String answer = scanner.nextLine(); 
        
        return answer;
    }
    
    public static char charInput(String message)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        String text = scanner.nextLine();
        char answer = text.charAt(0);
        
        return answer;
    }
    
    public static int intInput (String message)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        int answer = Integer.parseInt(scanner.nextLine());  
        
        return answer;
    }

    public static void printInfo(Candidate[] allVotees)
    {
        for (int i=0; i <= (allVotees.length); i++)
        {
            System.out.print(allVotees[i]);
        }
    }                                            
                                                     
    public Candidate vote(Candidate[] candidates) {

        // If array is empty, return instance of friend's class.
        if (candidates.length == 0) 
            return new Candidate_ec22541();

        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("Less assignments!"))
                return c;

        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Sisi"))
                return c;

        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {

        // If array is empty, return instance of friend's class.
        if (votes.length == 0) 
            return new Candidate_ec22981();
        
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
  
