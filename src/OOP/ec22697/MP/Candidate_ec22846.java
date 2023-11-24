package OOP.ec22697.MP;

import java.util.Scanner;
import java.util.Random;
// File Candidate_ec22846.java
//
// Machine generated stub for Assignment 2

class Candidate_ec22846 extends Candidate {
    
    public String getName() {
        return "AayomiTTV";
    }
    
    public String getSlogan() {
        return "FOLLOW THE TWITCH";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        if (candidates.length == 0) 
            return new Candidate_ec22846();
        
        Random r = new Random();
        int i = r.nextInt(candidates.length);
        return candidates[i];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        if (votes.length == 0) 
            return new Candidate_ec22846();
       
        Candidate currentWinner = votes[0];
    
        // Count the votes for each object in the array,
        // selecting one with the most.
        int highestCount = 0;
        int count = 0;
        for (Candidate i : votes) {
            for (Candidate j : votes)
                if (j == i)
                    count++;
            if (count > highestCount) {
                highestCount = count;
                currentWinner = i;
            }
        }
        
        return currentWinner;
        // Add code that either counts the votes
        // or uses some other way to return an element 
        // of the array vote as the winner of an election.
        
    }
    public static String correctInput(String message){
        String answer = inputString(message);
        boolean answered = false;
        if (answer.equals("a") ||answer.equals("b") ||answer.equals("c") ||answer.equals("d") ){
            answered  = true;
        }
        else{
            answered = false;
        }
         
        while (answered == false) {
            answer = inputString("Please enter  valid answer");
            if (answer.equals("a")||answer.equals("b") ||answer.equals("c")||answer.equals("d") ){
                answered  = true;
            }
            else {
                answered = false;
            }
        }
        return answer; 
    }
    public static int inputInt(String message){
        String answer = inputString(message);
        return Integer.parseInt(answer);
    }
    
    public static char inputChar(String message) 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        char answer =  scanner.nextLine().charAt(0);
        scanner.close();
        return answer;
    }
    public static String inputString(String message){
        String answer;
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        answer = scanner.nextLine();
        return answer;
    }

    public static void displayCandidates(Candidate[] candidateList, int counter) 
    {
        System.out.println("The Candidates are: ");
        for (int i = 0; i < counter; i++) 
        {
            System.out.println(candidateList[i].getName() + ": " + candidateList[i].getSlogan());
        }
        System.out.println("End of List");
        return;
    }
    public static Candidate getCandidate (String username ) 
    {
        Candidate everycandidate[] = A3.getCandidateArray();
        return A3.getByUsername(username, everycandidate);
    }
    public static int numberOfTimes(Candidate x, Candidate[]  a, int counter)
    {
        int r = 0;
        for (int i = 0; i < counter; i++)
        {
            if (x.equals(a[i]))
            {
                r++;
            }
        }

        return r;
    }
    public static int[] eachNumberOfTimes(Candidate[] a, int counter)
    {
        int[] r = new int[counter];

        // Loop that calls numberOfTimes.
        for (int i = 0; i < counter; i++)
        {
            r[i] = numberOfTimes(a[i], a, counter);
        }

        return r;
            
    }
    public static void main (String[] args) {
        Candidate[] allContributions = A3.getCandidateArray();
        Candidate[] electionCandidates = new Candidate[allContributions.length];

        for (int i = 0; i < allContributions.length; i++) {
            electionCandidates[i] = new Candidate_ec22846();

        }
      
        
        int counter = 0;
        boolean exit = false;

        while(!exit){
            String options = correctInput("a) Add specific candidate\nb) Add random candidate\nc) Run election\nd) Exit ");
            if(options.equals("a")){
                    String username = inputString("Which candidate whould u like?");
                    Candidate newCandidate = getCandidate(username);
                    electionCandidates[counter] = newCandidate;
                    counter++;
                    displayCandidates(electionCandidates, counter);
                }

            else if(options.equals("b")){
                    Random randInt = new Random();
                    int randomInt = randInt.nextInt(allContributions.length);
                    electionCandidates[counter] = allContributions[randomInt];
                    counter++;
                    displayCandidates(electionCandidates, counter);
                }
            else if(options.equals("c")){
                    System.out.println("How many times should we run this election?");
                    int elections = inputInt("Enter a Number:");

                    int indexOfMostCommon = 0;
                    Candidate[] winnersArray = new Candidate[elections];
                    for (int j = 0; j < elections; j++)
                    {

                        Candidate chooseWinner = A3.getByUsername("ec22846", allContributions);
                        winnersArray[j] = chooseWinner.selectWinner(electionCandidates);

                        
                    }
                    int[] numberOfTimes = eachNumberOfTimes(winnersArray, counter);
                        for ( int q = 0; q < numberOfTimes.length; q++ )
                        {
                            if (numberOfTimes[q] > numberOfTimes[indexOfMostCommon])
                            {
                                indexOfMostCommon = q;
                            }
                        }
                        exit = true;
                

                System.out.println("Most common winner is: "+ winnersArray[indexOfMostCommon].getName());
            }
            else if(options.equals("d")){
                exit = true;
            }
            }
        
        }
    }
