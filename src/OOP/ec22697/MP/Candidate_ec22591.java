package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;

// File Candidate_ec22591.java
//
// Machine generated stub for Assignment 2

class Candidate_ec22591 extends Candidate {
    

    /*
    System.out.println(allContributions[42].getSlogan());
    System.out.println(A2.getByUsername("eey577",allContributions).getSlogan());
    System.out.println(allContributions[479].un);
    */

    public static void main(String[] args) {
        Candidate[] allContributions = A3.getCandidateArray();
        int electionIndex = 0;
        Candidate[] electionList = new Candidate[allContributions.length];
        boolean run= true;

        while (run){
            displayCandidates(electionList, electionIndex);
            String userChoice = input("\nWould you like to a) add a specific candidate b) add a candidate at random c) run the election d)exit?\n>");
            
            if (userChoice.equals("a")){
                String username = input("Please enter a username\n>");
                Candidate specificCandidate = A3.getByUsername(username, allContributions);
                electionList[electionIndex] = specificCandidate;
                electionIndex ++;
            }
            
            else if (userChoice.equals("b")){
                Random random = new Random();
                int ranInt = random.nextInt(allContributions.length);
                electionList[electionIndex] = allContributions[ranInt];
                electionIndex ++;
            }
            else if (userChoice.equals("c")){
                String username = input("Who should count the votes?\n>");
                Candidate voteCounter = A3.getByUsername(username, allContributions);
                Candidate[] finalElectionList = new Candidate[electionIndex];
                for (int i = 0 ; i < electionIndex; i ++)
                {
                    finalElectionList[i] = electionList[i];
                }
                
                int noOfTimes = intInput("How many times shall we run the election?\n>");

                Candidate[] listOfVotes = new Candidate[noOfTimes*allContributions.length];
                System.out.println(noOfTimes*allContributions.length);
                System.out.println(listOfVotes.length);

                for (int electionNo = 0; electionNo<noOfTimes ; electionNo++){
                    
                    for (int i = 0; i< allContributions.length; i++ ){
                        try{
                        listOfVotes[(electionNo*allContributions.length) + i] = allContributions[i].vote(finalElectionList);
                        }
                        catch(Exception e){
                            listOfVotes[(electionNo*allContributions.length) + i] = new Candidate_ec22591();
                                
                        }
                        
                        
                        //System.out.println(electionNo*allContributions.length + i);
                        
                    }
                }
                Candidate winner = voteCounter.selectWinner(listOfVotes);
                System.out.println("Most common winner is: " + winner.getName());

                electionIndex = 0;
                electionList = null;
            }
            else if(userChoice.equals("d")){
                run = false;
            }
        }
        
    }

    public static void displayCandidates(Candidate[] list, int length){
        System.out.println("#####################\nCandidates are");
        if (length !=0){
            for (int i =0; i < length; i++){
                System.out.println(list[i].getName()+ ": " + list[i].getSlogan());
            }
        }
        else{
            System.out.println("None");
        }
        return;
    }
    public static String input(String text){
        Scanner scanner = new Scanner(System.in);
        System.out.println(text);
        String input = scanner.nextLine();
        return input;
    }

    public static int intInput(String text){
        boolean run = true;
        String input = "";
        while (run){
        input = input(text);
        try { 
            Integer.parseInt(input); 
            run = false;
        } catch(NumberFormatException e) { 
            System.out.println("Enter an integer please!!");
        }
    }
        return Integer.parseInt(input);
    }


    public String getName(){
        return "Joker";
    }

    public String getSlogan(){
        return "Why so serious?";
    }

    public Candidate vote(Candidate[] candidates){
        if (candidates.length == 0){
            return new Candidate_ec22591();
        }
        for (Candidate c : candidates){
            if (c.getName().equals("Joker")){
                return c;
            }
        }
        return candidates[candidates.length /2 ];
    }

    public Candidate selectWinner(Candidate[] votes){
        if (votes.length == 0) 
            return new Candidate_ec22591();
        Candidate winner = votes[0];
        int modalVote= 0;
        for (Candidate c :votes){
            int count = 0;
            for (Candidate i : votes){
                if (i == c){
                    count++;
                }
            }
            if (count > modalVote){
                modalVote = count;
                winner = c;
            }
        }
        return winner;
    }
}
