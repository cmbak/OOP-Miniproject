package OOP.ec22697.MP;// File Candidate_ec22557.java

// import necessary libraries

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22557 extends Candidate 
{
    // from assignment 2
    public String getName() 
    {
        return "Saloni";
    }
    
    public String getSlogan() 
    {
        return "More Chocolate!";
    }
    
    public static void main(String[] args)
    {
        // used A3.java 
        Candidate[] allCandidates = A3.getCandidateArray(); //get one candidate from A3
        Candidate[] votees = new Candidate[allCandidates.length]; 
        
        int counter = 0;
        String userInput = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?").toLowerCase();
        
        // adapted some code from ec22696
        
        // option a 
        if (userInput.equals("a"))
        {
            String usernameInput = inputString("Please enter the username: ");
            Candidate newVoter = A3.getByUsername(usernameInput, allCandidates);
            votees[counter] = newVoter;
            counter++; 
            printCandidateArray(votees, counter);
            
            for(int i=0;i<counter; i++) 
            {
                  System.out.println((i+1) + ")" + votees[i].getName()); // prints out the response
            }
        } 
        
        // option b 
        else if (userInput.equals("b")) 
        {
            boolean present = true;
            
            // ensures Candidate doesn't vote more than once
            while (present == true) 
            { 
                int generate = randomInt(allCandidates.length-1);
                present = checkVotes(allCandidates[generate], votees);
                
                if (present == false) 
                {
                    Candidate chosen = allCandidates[generate];
                    votees[counter] = chosen;
                    counter++;
                    printCandidateArray(votees, counter);
                }
            }
        } 
        
        // option c
        else if (userInput.equals("c")) 
        {
            String voteInput = inputString("Who should count the votes?");
            Candidate voteRunner = A3.getByUsername(voteInput, allCandidates);
            
            int loops = checkInput("How many times shall we run the election?");
            runElection(votees, counter, loops, voteRunner, allCandidates);
        }
    }

    // run election 
    public static void runElection(Candidate[] votees, int counter, int loops, Candidate voteRunner, Candidate[] allCandidates) 
    {
        Candidate[] actualCandidates = new Candidate[counter];
        
        for (int i = 0; i<counter; i++)
        {
            actualCandidates[i] = votees[i];
        }

        // winning candidate array holds winning candidates of each round
        // all TempVotes array holds all people voted for 
        Candidate[] winningCandidates = new Candidate[loops]; 
        Candidate[] allTempVotes = new Candidate[actualCandidates.length];
        
        for (int i = 0; i<loops; i++) 
        {
            for (int j = 0; j < allCandidates.length; j++) 
            {
                allTempVotes[j] = allCandidates[i].vote(actualCandidates);
            }
            winningCandidates[i] = voteRunner.selectWinner(allTempVotes);//gets what the winning candidate this round
        }
        
        Candidate finalWinner = voteRunner.selectWinner(winningCandidates);//gets winner over all rounds
        print("The winner is " + finalWinner.getName() + " with the slogan " + finalWinner.getSlogan());
    }

    // checks everyone only voted once
    public static boolean checkVotes (Candidate toCheck, Candidate[] votees) 
    {
        boolean present = false;
        
        for (int i = 0; i<votees.length; i++) 
        {
            if (toCheck.equals(votees[i])) 
            {
                present = true;
            }
        }
        return present;
    }

    public static void printCandidateArray(Candidate[] votees, int counter) 
    {
        print("Candidates so far: ");
        for (int i = 0; i<=counter; i++) 
        {
            Candidate printing = votees[i];
            print((i+1) + ". " + printing.getName() + " with the slogan " + printing.getSlogan());
        }
    }
    
    public Candidate vote(Candidate[] candidates) 
    {
        Candidate r = new Candidate_ec22557();

        if (candidates.length == 0)
        {
            return r;
        }

        for (Candidate i : candidates)
        {
            if (i.getName().equals("Saloni"))
            {
                return i;
            }
        }

        for (Candidate i : candidates)
        {
            if (i.getSlogan().equals("More Chocolate!"))
            {
                return i;
            }
        }
        
        Random rand = new Random();
        int c = rand.nextInt(candidates.length);
        return candidates[c];

    }

    public Candidate selectWinner(Candidate[] votes) 
    {
        Candidate r = new Candidate_ec22557();
        
        if (votes.length == 0) return r;
        
         int highestCount = 0;

        for (Candidate i: votes)
        {
            int count = 0;
            for (Candidate j: votes)
            {
                if (i.equals(j))
                {
                    count++;
                }
                
                if (count > highestCount)
                {
                    highestCount = count;
                    r = i;
                }
            }
        }


        return r;
    }
    
    public static void print(String toPrint) 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println(toPrint);
    }

    public static int checkInput(String questionToAsk)//asks and checks its an int
    {
        Scanner scanner = new Scanner(System.in);
        print(questionToAsk);
        boolean integerAns = false;
        int userResponse = 0;
        
        while (integerAns == false)
        {
            try
            {
                userResponse = Integer.parseInt(scanner.nextLine());
                integerAns = true;
            }
            catch(Exception e)
            {
                print("Please enter an integer.");
            }
        }
        return userResponse;
    }

    public static String inputString(String questionToAsk)
    {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        print(questionToAsk);
        userInput = scanner.nextLine();
        return userInput;
    }

    public static int randomInt(int bound) 
    {
        Random r = new Random();
        return r.nextInt(bound);
    }
}

