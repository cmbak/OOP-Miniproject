package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;

class Candidate_ex21423 extends Candidate {
    
    private static Random random = new Random();
    
    public static String stringInput(String prompt){ //Gets string input from given prompt
        Scanner scanner = new Scanner(System.in);
        System.out.println(prompt);
        return scanner.nextLine();
    }
    
   public static int intInput(String prompt){ //Same as previous but for integers
        Scanner scanner = new Scanner(System.in);
        System.out.println(prompt);
        return scanner.nextInt();
    }
    
     public static String runElection(Candidate[] all, Candidate[] votedOn, Candidate counter, int runs, int count)
    {
        Candidate[] voted = new Candidate[count]; 
        for(int i = 0; i < count; i++) voted[i] = votedOn[i]; //Copies candidate selection over
        Collections.shuffle(Arrays.asList(voted)); //Scrambles the candidates as some users return the first thing they get to add an element of randomness
        int tempCount = 0;
        Candidate[] runVotes = new Candidate[all.length];
        Candidate[] runWinners = new Candidate[runs];
        for(int y = 0; y< runs; y++){  //Runs the election multiple times and keeps track of the winner of each run
            tempCount = 0;
            for(Candidate x: all) {
                try{  //Just in case some peoples programs dont work or break
                    runVotes[tempCount]= x.vote(voted);  //Gets each person vote
                }
                catch (Exception e){
                    runVotes[tempCount] = voted[random.nextInt(count)]; //If excpetion caught random candidate is chosen
                }
                tempCount++;
            }
            runWinners[y] = counter.selectWinner(runVotes);  //Uses counters method to select winner
        }
        return counter.selectWinner(runWinners).getName(); //Returns string
    }
    
    public static void printCandidates(Candidate[] voted, int count){ //Prints each voted candidate name
        System.out.println("Current Candidates:");
        if (count == 0) System.out.println("None");
        else{
            for (Candidate i: voted) System.out.println(i.getName());
        }
    }
    
    public static void main( String[] args ){  //Main method with options
        Candidate[] all = A3.getCandidateArray();
        Candidate[] votedOn = new Candidate[all.length];
        Candidate counter;
        int count = 0;
        int menuChoice;
        int runs;
        boolean endProgram = false;
        while (!endProgram){
            printCandidates(votedOn, count);
            menuChoice = intInput("Would you like to 1) add a specific candidate 2) add a candidate at random 3) run the election? 4)exit");
            switch (menuChoice){
                case 4:
                    endProgram = true;
                    break;
                case 1:
                    votedOn[count] = A3.getByUsername(stringInput("Enter candidates username: "),all); //Adds candidate through username
                    count++;
                    System.out.println("Candidate added");
                    break;
                case 2:
                    votedOn[count] = all[random.nextInt(all.length)]; //Adds random candidate from array with all candidates
                    count++;
                    System.out.println("Random candidate added");
                    break;
                case 3:
                    if (count == 0){
                        System.out.println("No candidates available therefore can't run election");
                        break;
                    }
                    counter = A3.getByUsername(stringInput("Enter username of candidate that is counting: "), all);
                    System.out.println("Winner of election is " + runElection( all, votedOn, counter, intInput("Enter how many times the election will run"), count));                            
                    break;
            }
        }
    }
            
    
    
    
    public String getName() {
        return "P";
    }
    
    public String getSlogan() {
        return "Goo goo gaa gaa";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate voted = new Candidate_ex21423();
        
        if (candidates.length != 0) voted = candidates[random.nextInt(candidates.length)];
        else voted = new Candidate_ex21299();
        for (Candidate i : candidates) if (i.getSlogan().equals("No more university fees!")) return i;
        for (Candidate i: candidates) if (i.getName().equals("David J")) return i;                        
        return voted;
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate winner = new Candidate_ex21423();
        int mostVotes = 0;
        int tempCount;
        if (votes.length == 0) return new Candidate_ex21299();
        for (Candidate i : votes){
            tempCount = 0;
            for (Candidate x : votes) if (x == i) tempCount++;
            if (tempCount > mostVotes){
                mostVotes = tempCount;
                winner = i;
            }
        }
        return winner;
    }
    
}
