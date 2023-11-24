package OOP.ec22697.MP;// File Candidate_ec22929.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22929 extends Candidate {
    
    public String getName() {
        return "Boba";
    }
    
    public String getSlogan() {
        return "Boba makes you happy :)";
    }
    
    public Candidate vote(Candidate[] candidates) { //votes for random candidate
        Candidate r = new Candidate_ec22929();
        
        if (candidates.length != 0){
            r = candidates[0];
        }else{
            Random random = new Random();
            int randomCandidate = random.nextInt(candidates.length);
            r = candidates[randomCandidate];
        }  
        return r;
    }
    
    
    public Candidate selectWinner(Candidate[] votes) { //select random winner
        Candidate r = new Candidate_ec22929();
        
        if (votes.length != 0){
            r = votes[0];
        }else{
            Random random = new Random();
            int randomVotes = random.nextInt(votes.length);
            r = votes[randomVotes];
        }  
        return r;
    }
    
    public static String inputString(String message){
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    
    public static void election(){
        Candidate candidates[] = new Candidate[100];
        int numCandidates = 0;
        String userInput = inputString("Option a: Add candidate \n Option b: Add random candidate \n Option c: Run elections \n Option d: Exit");
        Boolean loop = true;
        while(loop==true){
            if (userInput.equals("a")){
                if(numCandidates<100){
                    candidates [numCandidates] = addCandidate();
                    numCandidates++;
                }
                else{
                    System.out.println("Max number of candidates has been reached.");
                }
            }
            else if (userInput.equals("b")){
                if(numCandidates<100){
                    candidates [numCandidates] = randomCandidate();
                    numCandidates++;
                }
                else{
                    System.out.println("Max number of candidates has been reached.");
                }
            }
            else if (userInput.equals("c")){
                runElection(candidates, numCandidates);
            }
            else {
                loop =false;
            }
        }
    }
  
    public static Candidate addCandidate(){
        String username = inputString("Please enter the name of the Candidate you would like to add.");
        Candidate allCandidates[]= A3.getCandidateArray();
        return A3.getByUsername(username, allCandidates);
    }
     
    public static Candidate randomCandidate(){
        Candidate allCandidates[]= A3.getCandidateArray();
        Random random = new Random();
        return allCandidates[random.nextInt(allCandidates.length)];
    }
   
    public static void runElection(Candidate[] candidates, int numCandidates){
        Candidate allCandidates[]= A3.getCandidateArray();
        Candidate winner = A3.getByUsername("ec22929",allCandidates);
        if(numCandidates==0){
            System.out.println("Please add candidates before running the election.");}
        else{
            Candidate me = A3.getByUsername("ec22929",allCandidates);
            Candidate[] votes = getAllVotes(candidates);
            winner = me.selectWinner(votes);
            try {
                System.out.println("The winner was " + winner.getName());
            }
            catch(Exception e){
                System.out.println("No one won the election.");
            }
        }
    }
        
    private static Candidate[] getAllVotes(Candidate[] candidates){
        Candidate[] allCandidates = A3.getCandidateArray();
        Candidate me = A3.getByUsername("ec22929",allCandidates);
        Candidate[] votes = new Candidate[allCandidates.length];
        for(int i = 0; i < allCandidates.length; i++){
            try { 
                votes[i] = allCandidates[i].vote(candidates);


            }
            catch (Exception e) {
                votes[i] = me.vote(candidates);
            }
        }
        return votes;
    }
            
    public static void main(String[] args){
        election();
    }
    
    
}
