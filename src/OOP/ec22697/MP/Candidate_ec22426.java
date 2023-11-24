package OOP.ec22697.MP;// File Candidate_ec22426.java
//
//  Assignment 3

import java.util.Scanner;
import java.util.Random;

class Candidate_ec22426 extends Candidate {

    //returns my candidate name
    public String getName() {
        return "Dorian";
    }
    
    //returns my slogan
    public String getSlogan() {
        return "Vote for me!";
    }
    
    //votes  for a candidate
    public Candidate vote(Candidate[] candidates) {
        Candidate newCandidate = new Candidate_ec22426();
        
        // If array is empty, return instance of friend's class.
        if (candidates.length == 0){
            return new Candidate_ec22547();
        }
        
         if (candidates.length != 0)
         {
        
            // Search for a candidate with this slogan.
            for (Candidate candidate : candidates)
                if (candidate.getSlogan().equals("Vote me for hacker points!"))
                    //votes
                    return candidate;

            // Otherwise, search for this candidates name.
            for (Candidate candidate : candidates)
                if (candidate.getName().equals("Madara"))
                    //votes
                    return candidate;

         }
        // If array is empty, return instance of friend's class.
        else{
            return new Candidate_ec22547();
        }
        
        // Otherwise votes for the first element
        return candidates[0];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate newCandidate = new Candidate_ec22426();
        
        if (votes.length != 0){
           newCandidate = votes[0];
        
            // Default to first vote, but this will be over-written.
            Candidate currentWinner = votes[0];

            // Count the votes for each object in the array,
            // selecting one with the most.
            int highestCount = 0;
            for (Candidate candidate : votes) {

                int count = 0;
                for (Candidate otherCandidate : votes)
                    if (otherCandidate == candidate)
                        count++;
                if (count >= highestCount) {
                    highestCount = count;
                    currentWinner = candidate;
                }
            }

            //returns winner
            return currentWinner;
        }
        //if array is empty
       else{
            //returns this winner
            return new Candidate_ec22426();
       }
        
    }

    //method used to print messages
    public static void print(String message)
    {
        System.out.println(message);
        return;
    }

    //method used to take strings as input
    public static String inputString()
    {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    //method used to take intger as inputs
    public static int inputInteger()
    {
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    //main method that runs whole program
    public static void main(String[] args)
    {
        menuMethod();
        return;
    }

    //method containing the menu the user can use to make decisions
    public static void menuMethod()
    {
        //gets the entire list of possible candidates
        Candidate[] totalCandidates =  A3.getCandidateArray();

        //creates blank array
        Candidate[] currentCandidates = new Candidate[totalCandidates.length];

        //count
        int candidateCount = 0;

        print("= Running Repeated Elections =");
        printCandidates(candidateCount, currentCandidates);

        Boolean quit = false;
        //repeats until the user quits
        while (!quit)
        {
            print("Would you like to a) add a specific candidate b) add a candidate at random c) run the election d)exit?");
            String answer = inputString().toLowerCase();

            //add chosen username
            if (answer.equals("a"))
            {
                currentCandidates = addSpecificCandidate(totalCandidates, currentCandidates, candidateCount);
                if (candidateCount<currentCandidates.length)
                {
                    candidateCount++;
                }
                printCandidates(candidateCount, currentCandidates);

            }
            //add random username
            else if (answer.equals("b"))
            {
                currentCandidates = addRandomCandidate(totalCandidates, currentCandidates, candidateCount);
                if (candidateCount<currentCandidates.length-1)
                {
                    candidateCount++;
                }
                printCandidates(candidateCount, currentCandidates);
            }
            //run election
            else if (answer.equals("c"))
            {
                runElection(totalCandidates, currentCandidates, candidateCount);
            }
            //stop menu
            else if (answer.equals("d"))
            {
                quit = true;
            }
            //invalid choice made
            else
            {
                print("That is not an option.");
            }
        }
        return;

    }

    //method that allows the user to add a candidate to the array by picking a username
    public static Candidate[] addSpecificCandidate(Candidate[] totalCandidates, Candidate[]  currentCandidates, int candidateCount)
    {
        //gets the candidate of that username
        print("Please enter a username:");
        String username = inputString();
        Candidate newCandidate = A3.getByUsername(username, totalCandidates);

        //adds candidate if it isnt a duplicate
        if (checkDuplicates(newCandidate, currentCandidates, candidateCount) == true)
        {
            currentCandidates[candidateCount]=newCandidate;
        }

        return currentCandidates;
    }

    //method that allows the user to add a candidate to the array by picking a random username
    public static Candidate[] addRandomCandidate(Candidate[] totalCandidates, Candidate[]  currentCandidates, int candidateCount)
    {
        //gets random candidate
        Random random = new Random();
        int randomInt = random.nextInt(totalCandidates.length);
        Candidate newCandidate = totalCandidates[randomInt];

        //adds candidate if it isnt a duplicate
        if (checkDuplicates(newCandidate, currentCandidates, candidateCount) == true)
        {
            currentCandidates[candidateCount]=newCandidate;
        }


        return currentCandidates;
    }

    //outputs the amount of current candidates and their names
    public static void printCandidates(int candidateCount, Candidate[] currentCandidates)
    {
        print("Candidates are:");

        if (candidateCount == 0)
        {
            print("None.");
        }
        else
        {
            //uses for loop to output candidates.
            for(int i=0;i<currentCandidates.length;i++)
            {
                if (!(currentCandidates[i]==null)){
                    print(i+1 +". "+currentCandidates[i].getName());
                }

            }
        }
        return;
    }


    //checks there are no duplicate candidates
    public static boolean checkDuplicates(Candidate newCandidate, Candidate[] currentCandidates, int candidateCount)
    {
        //compares new candidate to every current candidate using for loop
        for (int i=0;i<candidateCount;i++)
        {
            if (currentCandidates[i]==newCandidate)
            {
                return false;
            }
        }
        return true;
    }

    //runs the election
    public static void runElection(Candidate[] totalCandidates, Candidate[] currentCandidates, int candidateCount)
    {
        //gets a counter candidate
        print("Who should count the vote?");
        String username = inputString();
        Candidate counter = A3.getByUsername(username, totalCandidates);

        print("How many times shall we run the election?");
        int repeat = inputInteger();


        //Creates an array filled with candidates
        Candidate[] newElection = new Candidate[candidateCount];
        for (int i = 0; i < candidateCount; i++) {
            newElection[i] = currentCandidates[i];
        }

        Random rand = new Random();
        Candidate[] winningCandidates = new Candidate[repeat];
        //Repeat as many times as the user requests
        for (int i = 0; i < repeat; i++) {
            Candidate[] votes = new Candidate[totalCandidates.length];
            for (int j = 0; j < totalCandidates.length; j++) {
                //random candidate vote if that doesnt work
                try {
                    votes[j] = totalCandidates[rand.nextInt(totalCandidates.length)].vote(newElection);
                }
                //do this
                catch (Exception e) {
                    votes[j] = newElection[rand.nextInt(candidateCount)];
                }
            }
            winningCandidates[i] = votes[i];
        }
        //Stores the winning candidate
        Candidate winner = counter.selectWinner(winningCandidates);


        //Prints out the winner
        print("Most common winner is: " + winner.getName());

        return;
    }

}

