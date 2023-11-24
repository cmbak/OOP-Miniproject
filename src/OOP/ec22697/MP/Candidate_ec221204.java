package OOP.ec22697.MP;// File Candidate_ec221204.java
//
// Machine generated stub for Assignment 2

import java.util.Scanner;
import java.util.Random;







class Candidate_ec221204 extends Candidate {

    public String getName(){
        return "Bo";
    }

    public String getSlogan(){
        return "Hi, kids!";
    }

    public Candidate vote(Candidate[] candidates){
        if (candidates.length == 0) {
            return new Candidate_ec221204();
        }
        
        for (Candidate c : candidates) {
            if (c.getSlogan().equals("Hi, kids!")) {
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
            return new Candidate_ec221204();
        }

        Random r = new Random();
        int c = r.nextInt((votes.length/2));
        return votes[c];
    }

    public static void main(String[] args) {
        Candidate[] Candidates = A3.getCandidateArray();
        int ind = 0;
        Candidate[] arr = new Candidate[Candidates.length];
        boolean valid= true;

        while (valid){
            displayCandidates(arr, ind);
            String choice = strInp("Would you like to a) add a specific candidate b) add a candidate at random c) run the election d)exit?");

            if (choice.equals("a")){
                String name = strInp("enter a username");
                Candidate specCand = A3.getByUsername(name, Candidates);
                if (specCand != null) {
                    arr[ind] = specCand;
                    ind ++;
                }
                else{
                    System.out.println("does not exist");
                }
            }

            else if (choice.equals("b")){
                // Adapted from ec22566
                if (ind == Candidates.length)
                {
                    System.out.println("Candidates are full.");
                }
                Random randInt = new Random();
                boolean found = false;
                while (!found)
                {
                    found = false;
                    int randomInt = randInt.nextInt(Candidates.length);
                    Candidate candidate = Candidates[randomInt];

                    for (int i = 0; i < ind; i++)
                    {
                        if (arr[i] == candidate)
                        {
                            found = true;
                        }
                    }
                    if (found == false)
                    {
                        arr[ind] = candidate;
                        ind++;
                    }
                }
            }
            else if (choice.equals("c")){
                String name = strInp("Who should count the votes?");
                Candidate voteCount = A3.getByUsername(name, Candidates);
                Candidate[] finList = new Candidate[ind];
                for (int i = 0 ; i < ind; i ++)
                {
                    finList[i] = arr[i];
                }

                int noOfTimes = intInp("How many times shall we run the election?");

                Candidate[] votes = new Candidate[noOfTimes*Candidates.length];
                System.out.println(noOfTimes*Candidates.length);
                System.out.println(votes.length);

                for (int electionNo = 0; electionNo<noOfTimes ; electionNo++){

                    for (int i = 0; i< Candidates.length; i++ ){
                        try{
                            votes[(electionNo*Candidates.length) + i] = Candidates[i].vote(finList);
                        }
                        catch(Exception e){
                            votes[(electionNo*Candidates.length) + i] = new Candidate_ec22566();

                        }
                    }
                }
                Candidate winner = voteCount.selectWinner(votes);
                System.out.println("Common winner: " + winner.getName());

                ind = 0;
                arr = null;
            }
            else if(choice.equals("d")){
                valid = false;
            }
        }

    }
    
    
    
    
    
    

    public static void displayCandidates(Candidate[] arr, int length){
        System.out.println("Candidates are");
        if (length !=0){
            for (int i =0; i < length; i++){
                System.out.println(arr[i].getName()+ ": " + arr[i].getSlogan());
            }
        }
        else{
            System.out.println("None");
        }
        return;
    }
    
    
    
    
    public static String strInp(String message){
        Scanner scanner = new Scanner(System.in);
        String ans;

        System.out.println(message);
        ans = scanner.nextLine();

        return ans;
    }
    
    
    
    

    public static int intInp(String message){
        boolean valid = true;
        String ans = "";

        while (valid){
            ans = strInp(message);
            try {
                Integer.parseInt(ans);
                valid = false;
            } catch(NumberFormatException e) {
                System.out.println("Enter an integer.");
            }
        }

        return Integer.parseInt(ans);
    }

}
