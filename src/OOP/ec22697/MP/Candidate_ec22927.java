package OOP.ec22697.MP;// File Candidate_ec22927.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22927 extends Candidate {

public static void main (String[] args) {

    Candidate[] allCandidates = A3.getCandidateArray();
    Candidate[] newCandidates = new Candidate[allCandidates.length];
    String option="a"; 
    int candidate_length = 0;

    System.out.println("= Running Repeated Elections =");

    while (!option.equals("d")){

        System.out.println("Candidates are");

        if (candidate_length==0){
            System.out.println("None");
        }
        else {
            for (int i=0; i<candidate_length; i++){
                System.out.println("Candidates are");
                System.out.println((i+1) + ". " + newCandidates[i].getName());
            }
        }

        option = userInputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election d) quit?");

        if (option.equals("a")){
            String username = userInputString("Please enter a username.");

            for (int i=0; i<candidate_length; i++){
                if (allCandidates[i].equals(username)){
                    newCandidates[candidate_length] = allCandidates[i];
                }
            }
        candidate_length+=1;
        }

        else if (option.equals("b")){
            Random random = new Random();
            int x = random.nextInt(allCandidates.length);
            newCandidates[candidate_length]=allCandidates[x];
            candidate_length+=1;
        }
        else if (option.equals("c")){
        String vote_counter = userInputString("Who should count the votes?");
            int repeats = userInputInt("How many times shall we run the election?");
            Candidate counter = A3.getByUsername(vote_counter, newCandidates);
            
            Candidate[] winners = new Candidate[repeats];
            
            for(int i=0; i<repeats; i++){
            winners[i] = counter.selectWinner(newCandidates);
            }
            }
        else if (option.equals("d")){
            break;
        }
        else{
            option = userInputString("Please enter a, b, c or d.");
        }
    return;
    }
}
        
    public String getName() {
        return "Woody";
    }
    
    public String getSlogan() {
        return "There's a snake in my boot!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22927();
        
        //if array is empty, return instance 
        if (candidates.length == 0)
            return r;

        
        for (Candidate c: candidates)
            if (c.getSlogan().equals("To infinity and beyond!"))
                return c;
        
        for (Candidate c: candidates)
            if (c.getName().equals("Mr Bean") && c.getSlogan().equals("Teddy!"))
                return c;
                
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22927();
        
        if (votes.length == 0)
            return r;
 
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
//for user to input a string 
public static String userInputString (String question)
{
    Scanner scanner = new Scanner(System.in);
    System.out.println(question);
    String answer = scanner.nextLine();
    return answer;
}

//for user to input an integer 
public static int userInputInt (String question)
{
    Scanner scanner = new Scanner(System.in);
    System.out.println(question);
    int answer = Integer.parseInt(scanner.nextLine());
    return answer;    
}
}
