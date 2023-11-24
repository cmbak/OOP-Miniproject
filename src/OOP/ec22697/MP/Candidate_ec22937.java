package OOP.ec22697.MP;// File Candidate_ec22937.java
//
// Machine generated stub for Assignment 2

import java.util.*;
import java.util.regex.Pattern;

class Candidate_ec22937 extends Candidate {
    
    public static void main(String args[])
    {
        char userOption = 'z';
        while (userOption != 'a')
        {
            userOption = outOptions();
            completeAction(userOption);
        }
    }
    
    public String getName() {
        return "Guy Bowser";
    }
    
     public String getSlogan() {
        return "Incompetence is a feature!";
    }
   
    public Candidate vote(Candidate[] candidates) {
        //returns a new candidate if the array contains nothing
        if (candidates.length == 0)
        {
            return new Candidate_ec22937();
        }
        //Looks for a candidate who taklks about public transport
        for (int i=0; i<candidates.length; i++)
        {
            if (hasString(candidates[i], "public") || hasString(candidates[i], "transport")) 
            { 
                return candidates[i]; 
            }
            
            else if (hasString(candidates[i], "train")) 
            { 
                return candidates[i]; 
            }

        }
        //If there is no candidate with an empty slogan, it returns a random canddiate from the array
        return candidates[(new Random()).nextInt(candidates.length)];
    }
    
    //First get rid of any duplicates, then randomise the vote list
    public Candidate selectWinner(Candidate[] votes) {
        //If votes is empty, returns a new candidate. If votes does have values then returns a random candidate
        if (votes.length == 0) { return new Candidate_ec22937(); }
        //Creates a new Candidate array of purely unique candidates
        Candidate[] uniqVote = removeDuplicates(votes);
        Candidate winner = null;
        while (winner == null) { winner = uniqVote[(new Random()).nextInt(uniqVote.length)]; }
        return winner;
    }
    
    //Outputs all of the possible options and asks the user for what option they want
    private static char outOptions()
    {
        print("Would you like to");
        print("a) exit");
        print("b) run same election many times");
        return inputChar("");
    }
    
    //Completes the action that the user chooses
    private static void completeAction(char userOption)
    {
        if (userOption == 'a') { return; }
        if (userOption == 'b')
        {
            action_B();
        }
    }
    
    private static void action_B()
    {
        print("= Running Repeated Elections =");
        outputCandidates();
        //Creates the initial candidate array to be appended later
        Candidate[] candidateList = new Candidate[0];
        char pickedOption = inputChar("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
        if (pickedOption == 'a') { candidateList = addSpecCand(candidateList); }
        else if (pickedOption == 'b') { candidateList = addRandCand(candidateList); }
        else if (pickedOption == 'c') { actionB_C(candidateList); }
        
    }
    
    //Adds a new candidate of the user's choice to the array
    private static Candidate[] addSpecCand(Candidate[] candidateList)
    {
        //Copies the current candidate list and returns a new Candidate array with one more entry
        Candidate[] appendedList = addRandCand(candidateList);
        String username = inputString("Please enter a username");
        print("Candidates are");
        Candidate cd = A3.getByUsername(username, A3.getCandidateArray());
        if (cd != null)
        {
            appendedList[appendedList.length - 1] = cd;
            print(cd.getName());
        }
        return appendedList;
    }
    
    //Adds a random new candidate to the array
    private static Candidate[] addRandCand(Candidate[] candidateList)
    {
        Candidate[] appendedList = new Candidate[candidateList.length + 1];
        appendedList = copyArr(candidateList, appendedList);
        Candidate[] all = A3.getCandidateArray();
        appendedList[appendedList.length - 1] = all[(new Random()).nextInt(all.length)];
        return appendedList;
    }
    
    //This will run if the user picks option c (run election) under option B (run same election many times)
    private static void actionB_C(Candidate[] candidateList)
    {
        String username = inputString("Who should count the votes?");
        Candidate cd = A3.getByUsername(username, A3.getCandidateArray());
        //If the candidate received from the username is null, we should return early since we can't do the vote
        if (cd == null) { return; }
        int numElection = inputInt("How many times shall we run the election?"); 
        Candidate[] winners = runElection(cd, numElection, candidateList);
        Candidate totWinner = mostOccur(winners);
        print("Most common winner is " + totWinner.getName());
    }
    
    //Outputs all possible candidates
    private static void outputCandidates()
    {
        Candidate[] all = A3.getCandidateArray();
        print("Candidates are");
        for (int i=0; i<all.length; i++)
        {
            print(all[i].getName());
        }        
    }
    
    private static Candidate[] runElection(Candidate voteCounter, int numElection, Candidate[] candidateList)
    {
        Candidate[] winners = new Candidate[numElection];
        for (int i=0; i<winners.length; i++)
        {
            Candidate[] votes = new Candidate[candidateList.length];
            //Each candidate votes for one of the other candidates and all of the votes are added to the vote array
            for (int j=0; j<candidateList.length; j++) { votes[j] = candidateList[j].vote(candidateList); }
            //Picks the winner from the vote array created earlier
            winners[i] = voteCounter.selectWinner(votes);
        }
        return winners;        
    }
    
    //Returns whether the given candidate's slogan contains the given string
    private static boolean hasString(Candidate c, String checkVal)
    {
        return c.getSlogan().contains(checkVal);       
    }
   
    //Outputs a message
    private static <T> void pr(T text) { System.out.print(text); }
    
    //Outputs a message then creates a new line
    private static <T> void print(T text) { System.out.println(text); }
    
    //Takes in a string from the user and returns their input, in the form of a string
    public static String inputString(String askUser)
    {
        String endVal = "";
        //Creates a scanner
        Scanner scanner = new Scanner(System.in);
        //Iterates through a while loop until the string given by the user is not empty
        while(endVal.length() == 0)
        {
            //Prompts the user to enter a string
            print(askUser);
            endVal = scanner.nextLine();
        }
        return endVal;
    }
    
    //Takes in a character from the user and returns their input in the form of a character
    private static char inputChar(String askUser)
    {
        //exit condition for the while loop below
        boolean isChar = false;
        //String the the user inputs
        String inputVal = "";
        //Iterates until the user enters a string of length 1
        while (!isChar)
        {
            inputVal = inputString(askUser);
            //Checks if the given string contains only one character 
            //(and therefore can be converted into a proper character)
            isChar = (inputVal.length() == 1);
        }
        //Converts the string into a character 
        //once its been confirmed that the string only contains one character
        return inputVal.charAt(0);    
    }

    //Takes in a number from the user and returns their input in the form of an integer
    public static int inputInt(String askUser)
    {
        String outVal = "";
        while (!Pattern.matches("\\d+",outVal)) {outVal = inputString(askUser);}
        return Integer.parseInt(outVal);
    }

    //Takes an array and copies all of the entries up to the length of the original array
    private static Candidate[] copyArr(Candidate[] original, Candidate[] copyTo)
    {
        //If the original array is empty, return a new candidate array with one entry
        if (original.length == 0 ) { return new Candidate[] {new Candidate_ec22937()}; }
        else if (copyTo.length < original.length) { return original; }
        for (int i=0; i<original.length; i++) { copyTo[i] = original[i]; }
        return copyTo;
    }    
    
    //Returns the most ocurring candidate in a list of candidates
    private static Candidate mostOccur(Candidate[] arr)
    {
        if (arr.length == 0) { return new Candidate_ec22937(); }
        int currentMax = 0;
        Candidate mostPopular = new Candidate_ec22937();
        for (int i=0; i<arr.length; i++)
        {
            int count = 0;
            for (int j=i+1; j<arr.length; j++)
            {
                //Increments the duplicate counter whenever a duplicate is found
                if (arr[i].equals(arr[j])) { count++; }
            }
            //if current is greater than the maximum, the current becomes the new maximum
            if (count > currentMax)
            {
                currentMax = count;
                mostPopular = arr[i];
            }
        }
        return mostPopular;
    }

    //Takes in an array of candidates and returns a new array with no duplicates
    private static Candidate[] removeDuplicates(Candidate[] c)
    {
        HashSet<Candidate> setC = new HashSet<>(Arrays.asList(c));
        Candidate[] cPrime = new Candidate[setC.size()];
        setC.toArray(cPrime);
        return cPrime;
    }

}
