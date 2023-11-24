package OOP.ec22697.MP;// File Candidate_ec22566.java
//
// Machine generated stub for Assignment 3

import java.util.Scanner;
import java.util.Random;

class Candidate_ec22566 extends Candidate {

    public String getName(){
        return "John Doe";
    }

    public String getSlogan(){
        return "Fix the cost of living crisis!";
    }

    public Candidate vote(Candidate[] candidates){
        if (candidates.length == 0) {
            return new Candidate_ec22566();
        }
        
        for (Candidate c : candidates) {
            if (c.getSlogan().equals("Fix the cost of living crisis!")) {
                return c;
            }
        }

        Random r = new Random();
        //int c = r.nextInt((candidates.length/2));
        int c = r.nextInt(candidates.length);
        return candidates[c];
    }

    public Candidate selectWinner(Candidate[] votes){
        if (votes.length == 0) {
            return new Candidate_ec22566();
        }

        Random r = new Random();
        int c = r.nextInt((votes.length/2));
        return votes[c];
    }

    public static void main(String[] args) {
        Candidate[] allCandidates = A3.getCandidateArray();
        int index = 0;
        Candidate[] list = new Candidate[allCandidates.length];
        boolean valid= true;

        while (valid){
            displayCandidates(list, index);
            String choice = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election d)exit?").toLowerCase();

            if (choice.equals("a")){
                String name = inputString("Please enter a username");
                Candidate specificCandidate = A3.getByUsername(name, allCandidates);
                if (specificCandidate != null) {
                    list[index] = specificCandidate;
                    index ++;
                }
                else{
                    System.out.println("Not found. Try again.");
                }
            }

            else if (choice.equals("b")){
                // Adapted from ec22586
                if (index == allCandidates.length)
                {
                    System.out.println("Candidates are full.");
                }
                Random randInt = new Random();
                boolean found = false;
                while (!found)
                {
                    found = false;
                    int randomInt = randInt.nextInt(allCandidates.length);
                    Candidate candidate = allCandidates[randomInt];

                    for (int i = 0; i < index; i++)
                    {
                        if (list[i] == candidate)
                        {
                            found = true;
                        }
                    }
                    if (found == false)
                    {
                        list[index] = candidate;
                        index++;
                    }
                }
            }
            else if (choice.equals("c")){
                String name = inputString("Who should count the votes?");
                Candidate voteCount = A3.getByUsername(name, allCandidates);
                Candidate[] finalList = new Candidate[index];
                for (int i = 0 ; i < index; i ++)
                {
                    finalList[i] = list[i];
                }

                int noOfTimes = inputInt("How many times shall we run the election?");

                Candidate[] votes = new Candidate[noOfTimes*allCandidates.length];
                System.out.println(noOfTimes*allCandidates.length);
                System.out.println(votes.length);

                for (int electionNo = 0; electionNo<noOfTimes ; electionNo++){

                    for (int i = 0; i< allCandidates.length; i++ ){
                        try{
                            votes[(electionNo*allCandidates.length) + i] = allCandidates[i].vote(finalList);
                        }
                        catch(Exception e){
                            votes[(electionNo*allCandidates.length) + i] = new Candidate_ec22566();

                        }
                    }
                }
                Candidate winner = voteCount.selectWinner(votes);
                System.out.println("Most common winner is: " + winner.getName());

                index = 0;
                list = null;
            }
            else if(choice.equals("d")){
                valid = false;
            }
        }

    }

    public static void displayCandidates(Candidate[] list, int length){
        System.out.println("Candidates are");
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
    public static String inputString(String message){
        Scanner scanner = new Scanner(System.in);
        String answer;

        System.out.println(message);
        answer = scanner.nextLine();

        return answer;
    }

    public static int inputInt(String message){
        boolean valid = true;
        String ans = "";

        while (valid){
            ans = inputString(message);
            try {
                Integer.parseInt(ans);
                valid = false;
            } catch(NumberFormatException e) {
                System.out.println("Invalid integer input. Please enter an integer.");
            }
        }

        return Integer.parseInt(ans);
    }

}
