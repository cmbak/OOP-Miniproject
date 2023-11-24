package OOP.ec22697.MP;// File Candidate_ec22601.java
//
// Machine generated stub for Assignment 2
// A3 Code
// Modified code from examples on JHUB

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22601 extends Candidate {
    
    
    public static void main(String[] args){
        
        Scanner scanner = new Scanner(System.in);
        
        Candidate[] AllCandidates = A3.getCandidateArray();
        
        System.out.println("Would you like to");
        System.out.println("a) exit");
        System.out.println("b) run same election many times");
        System.out.println("c) check who counts honestly"); 
        System.out.println("d) run all possible two-candidate elections");
        System.out.println("e) run a higher-order election");
        System.out.println("f) ...");
        
        String choice = scanner.nextLine();
        
        while(!choice.equals("a")){
            
            if(choice.equals("b")){
            
                System.out.println("= Running Repeated Elections =");
                System.out.println("The candidates are");
                
                for(int i = 0; i<AllCandidates.length; i++){
                    System.out.println(AllCandidates[i]);
                }
                
                System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
                
                String ChoiceElection = scanner.nextLine();

                while(ChoiceElection.equals("a")||ChoiceElection.equals("b")||ChoiceElection.equals("c")){

                    if(ChoiceElection.equals("a")){
                    
                        System.out.println("Which specific user would you like to include?");
                        String specificName = scanner.nextLine();
                        Candidate specificCandidate = A3.getByUsername(specificName, AllCandidates);
                        if (specificCandidate != null){

                            AllCandidates[0] = specificCandidate;
                        }
                        else{

                            System.out.println("User not found.");
                        } 
                        
                    }
                    
                    else if(ChoiceElection.equals("c")){
                        System.out.println("Who should count the votes?");
                        String VoteCounter = scanner.nextLine();
                        System.out.println("How many times shall we run the election? ");
                        int NumTimes = Integer.parseInt(scanner.nextLine());

                        Candidate[] Votes = new Candidate[AllCandidates.length];
                        Candidate[] Winners = new Candidate[AllCandidates.length];
                        Candidate[] WinnerForRound = new Candidate[NumTimes];


                        for(int i = 0; i<NumTimes; i++){

                            for(int j = 0; i<Votes.length; i++){

                                Votes[i] = AllCandidates[i].vote(AllCandidates);
                            }

                            for(int k = 0; i<Winners.length; i++){

                                Winners[i] = AllCandidates[i].selectWinner(AllCandidates);
                            }

                            Candidate r = Winners[0]; 
                            int largestCountSoFar = 1; 
    
                            for(int l = 0; i < Winners.length; i++) {
                                if(numberOfTimes(Winners[i], Winners) > largestCountSoFar) {
                                    r = Winners[i]; // Using = at type T.
                                    largestCountSoFar = numberOfTimes(Winners[i], Winners);
                                }
                            }

                            WinnerForRound[i] = r;
                        }


                        //FIND FINAL WINNER


                        Candidate r = WinnerForRound[0]; 
                        int largestCountSoFar = 1; 
    
                        for(int i = 0; i < WinnerForRound.length; i++) {
                            if(numberOfTimes(WinnerForRound[i], WinnerForRound) > largestCountSoFar) {
                                r = WinnerForRound[i]; // Using = at type T.
                                largestCountSoFar = numberOfTimes(WinnerForRound[i], WinnerForRound);
                            }
                        }

                        System.out.println("The most common winner is " + r);
                        System.out.println("There was no other winners.");
                        
                    }

                }
    
            }
        
            System.out.println("Would you like to");
            System.out.println("a) exit");
            System.out.println("b) run same election many times");
            System.out.println("c) check who counts honestly"); 
            System.out.println("d) run all possible two-candidate elections");
            System.out.println("e) run a higher-order election");
            System.out.println("f) ...");
        
            choice = scanner.nextLine();
            
        }
        
        
        
        return;
    }


    public static int numberOfTimes(Candidate x, Candidate[] a) {
        int r = 0;
        
        for(int i = 0; i < a.length; i++) {
            if(x == a[i]) r++;
        }
        
        return r;
    }
    
    public String getName() {
        return "Lewis";
    }
    
    public String getSlogan() {
        return "Lower Taxes!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate VotingCandidate = new Candidate_ec22601();
 
        //Returns a new candidate of my class if array is empty
        if (candidates.length == 0){
            return VotingCandidate;
        }
        //Votes for someone with similar slogan and if not, the middle candidate gets the vote
        for (int i = 0; i<candidates.length; i++){
            if (VotingCandidate.getSlogan().equals("Lower Taxes!")){
                int SimilarCandidatePosition = i;
                return candidates[i];
            }
        }
        return candidates[(candidates.length/2)];  
    }
    
    public Candidate selectWinner(Candidate[] votes) {

        // Creates new candidate object
        Candidate WinningCandidate = new Candidate_ec22601();
 
        // If the array is empty, a new 
        if (votes.length == 0) 
            return WinningCandidate;
        
        // Votes for a random candidate 
        Random RandomNum = new Random();
        int RandomWinner = 0;
        
        RandomWinner = RandomNum.nextInt(votes.length);
        return votes[RandomWinner];
    }
    
}
