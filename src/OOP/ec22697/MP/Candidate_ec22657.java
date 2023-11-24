package OOP.ec22697.MP;// File Candidate_ec22657.java
//

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22657 extends Candidate {
    public static String inputString(String a)
    {
        String ans;
        Scanner scanner = new Scanner(System.in);
    
        System.out.println(a);
        ans = scanner.nextLine();
        return ans;
    }
    
    public static int inputInt(String a)
    {
        int ans;
        Scanner scanner = new Scanner(System.in);
    
        System.out.println(a);
        ans = scanner.nextInt();
        return ans;
    }
    
    public static int addRandomCandidate(int start, Candidate[] n, Candidate[] a)
    {
        if(start==n.length){
            System.out.println("The list is already full.");
            return start;
        }
        else{
            Random r = new Random();
            int num = r.nextInt(n.length);
            n[start+1] = a[num];
            int newN =start+1;
            return newN;
        }
    }
    
    public static void runElection(Candidate[] n, Candidate[] a)
    {
        String user = inputString("Who should count the votes?");
        int number = inputInt("How many times shall we run the election?");
        Candidate c = A3.getByUsername(user, a);
    
        for(int i=0; i<n.length; i++){
            if(c!=null){
                Candidate winner = a[i].selectWinner(n);
                System.out.println("Most common winner is " + winner.getName());
            }
        }
    }
    
    public static void main(String[] args)
    {
        int start = 0;
        Candidate[] allContributions = A3.getCandidateArray();
        Candidate[] newOne = new Candidate[start];
    
        boolean exit =false;
    
        System.out.println("= Running Repeated Elections =");
    
        while(exit = false){
            System.out.println("Candidates are");
        
            if(newOne.length==0){
                System.out.println("None");
            }
            else{
                for(int i=0; i<newOne.length; i++){
                    System.out.println((i+1) + ". " + allContributions[i].getName());
                }
            }
        
            String choice = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
        
            if(choice.equals("a")){
                String username = inputString("Please enter a username.");
                
                Candidate c = A3.getByUsername(username, allContributions);
                
                if(c!=null){
                    newOne[start] = c;
                    start +=1;
                }
                else{
                    System.out.println("The user is already in the candidate list.");
                }
            }
            else if(choice.equals("b")){
                start = addRandomCandidate(start, newOne, allContributions);
            }
            else if(choice.equals("c")){
                runElection(newOne, allContributions);
                String option = inputString("Would you like to a) exit \nb) run same election many times \nc) check who counts honestly \nd) run all possible two-candidate elections \ne) run a higher-order election \nf) ...");
                if(option.equals("a")){
                    exit = true;
                }
                else if(option.equals("b")){
                    runElection(newOne, allContributions);
                }
                else{
                    exit = true;
                }
            }
            else{
                System.out.println("Invalid input. Please type the correct input.");
                exit = true;
            }
        }
    }
        
    
    public String getName() {
        return "Charlie";
    }
    
    public String getSlogan() {
        return "Let's go!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        if (candidates.length == 0) 
            return new Candidate_ec22657();
        
        for (Candidate c : candidates)
            if (c.getName().equals("King Julian"))
                return c;
        
        // Otherwise, choose at random from list.
        Random r = new Random();
        int c = r.nextInt(candidates.length);
        return candidates[c]; 

    }
    
    public Candidate selectWinner(Candidate[] votes) {

        if (votes.length == 0) 
            return new Candidate_ec22657();
        
        Random r = new Random();
        int c = r.nextInt(votes.length);
        return votes[c];  
    }
    
}
