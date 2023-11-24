package OOP.ec22697.MP;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

// File Candidate_ec22429.java
//
// Machine generated stub for Assignment 2

class Candidate_ec22429 extends Candidate {
    
        public static void main (String[] args){
        Candidate[] allCandidates = A3.getCandidateArray();
        Candidate[] list = new Candidate[allCandidates.length];
        rerunElection(allCandidates, list);
        return;
    }

    public static String inputString (String message){
        String strInput;
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        strInput = scanner.nextLine();
        return strInput;
    }

    public static int inputInt (String message){
        int intInput;
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        intInput = scanner.nextInt();
        return intInput;
    }

    public static void rerunElection(Candidate[] allCandidates, Candidate[] list){
        ArrayList <Candidate> electionArray = new ArrayList<Candidate>();
        int counter = 0;

        String menuoption = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election or d) exit?");
        while (!menuoption.equals("d")){

            if(menuoption.equals("a")){
                String candidate_username = ("Please enter a username.");
                Candidate addCandidate = A3.getByUsername(candidate_username, allCandidates);
                
                if(addCandidate != null){
                    electionArray.add(addCandidate);
                    counter = counter + 1;
                }
                else{
                    System.out.println("Invalid input.");
                    System.out.println("User " + addCandidate + " was not found.");
                }

                System.out.println("Candidates are:");

                if(allCandidates.length == 0){
                    System.out.println("None");
                }
                else{
                    for(int i=0;i<counter;i++){
                        System.out.println((i+1) +". " + electionArray.get(i));
                    }
                }
            }
            // Adapted from Candidate_ec22591.java
            else if(menuoption.equals("b")){
                Random randomCandidate =  new Random();
                int randomInt = randomCandidate.nextInt(allCandidates.length);
                list[counter] = allCandidates[randomInt];
                counter = counter + 1;
            }
            // Adapted from Candidate_ec22556.java
            else if(menuoption.equals("c")){
                String voterName = inputString("Who should count the votes?");
                Candidate votingMethod = A3.getByUsername(voterName, allCandidates);
                
                // Adapted from Candidate_ec22411.java
                while (votingMethod == null){
                    System.out.println("Invalid input. Please enter another candidate");
                    voterName = inputString("Who should count the votes?");
                }

                int repetition = inputInt("How many times should we run the election?");
                Candidate[] votes = new Candidate[counter * repetition];

                for (int x = 0; x < repetition; x++){
                    for (int i = 0; i < counter; i++)
                    {
                        votes[i+(x*counter)] = list[i].vote(list);
                    }
                }

                // Adapted from Candidate_ec22556.java
                Candidate mostCommonWinner = votingMethod.selectWinner(votes);
                System.out.println("Most common winner is: " + mostCommonWinner.getName());
                System.out.println("Other common winners could not be determined.");
            }
        }
    }
    
    public String getName() {
        return "Donkey";
    }
    
    public String getSlogan() {
        return "Are we there yet?";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        if (candidates.length == 0) 
            return new Candidate_ec22429();
        
        for (Candidate c : candidates)
            if (c.getSlogan().contains("ChatGPT") || c.getSlogan().contains("King Julian") || c.getSlogan().contains("Shrek") || c.getSlogan().contains("Bean") || c.getSlogan().contains("Spongebob"))
                return c;
        
        for (Candidate c : candidates)
            if (c.getName().startsWith("X") || c.getName().endsWith("o"))
                return c;
        
        return candidates[candidates.length/2];
    }
    
     public Candidate selectWinner(Candidate[] votes) {
        
        if (votes.length == 0) 
            return new Candidate_ec22429();
        
        Candidate currentWinner = votes[0];
        
        int nameScore=0;
        int highestScore = 0;
        int highestScoreIndex = 0;
         
        for(int i = 0; i < votes.length; i++){
            if(votes[i].getName().length() <= 5){
                nameScore = votes[i].getName().length() + 3;
            }
            else if((votes[i].getName().length() >= 6) && (votes[i].getName().length() <=11)){
                nameScore = votes[i].getName().length() + 10;
            }
            else if((votes[i].getName().length() <= 15) && (votes[i].getName().length() >= 12)){
                nameScore = votes[i].getName().length() + 17;
            }
            else{
                nameScore=0;
            }
            
                if(nameScore>highestScore){
                    highestScore=nameScore;
                    highestScoreIndex = i;
                    currentWinner = votes[i];
                }
        }
         
        return currentWinner;
     }
}
