package OOP.ec22697.MP;// File Candidate_ec221016.java
//
// Machine generated stub for Assignment 2

import java.util.Scanner;
import java.util.Random;

class Candidate_ec221016 extends Candidate {
    
    public static void main(String[] args) {
        
        Candidate[] check = new Candidate[546];
        Candidate[] everyone = A3.getCandidateArray();
        char choice = inputchar("Would you like to a) add a specific candidate b) add a candidate at random c) run the election? ");
        
        if (choice == 'a') {
            
            String sname = inputstring("Please enter a username. ");
            check[0] = getCandidate(sname, everyone);
            showCandidates(check);
            
        } else if (choice == 'b') {
            
            Random r = new Random();
            int random_candidate = r.nextInt(546);
            System.out.println(" The random candidate chosen is: " + everyone[random_candidate]);
            
        } else if (choice == 'c') {
        
            String anothername = inputstring("Who should count the votes");
            Candidate user = getCandidate(anothername, everyone);
            int runtime = inputint("How many times should the election be run? ");                                                
            CalculatingWinner(everyone, user, runtime);
            
        }
    }
    
    public static void CalculatingWinner(Candidate[] array, Candidate name, int rtime) {
    
        for (int i = 0; i < rtime; i++) {
            
            name.selectWinner(array);
            
        }
        System.out.println("The most common answer is " + name.selectWinner(array));
        System.out.println("There were no other winners");
        
        return;
     }
    
    
    public static Candidate getCandidate(String name , Candidate[] array){
    
        for(Candidate eachCandidate : array){
            if (eachCandidate.un.equals(name)){
                return eachCandidate;
            }
        }
        return null;
    
    }
    
    public static void showCandidates(Candidate[] array) {
    
        int index = 1;
        for (Candidate candidate : array) {
            if (candidate != null && candidate.un != null){
            
                System.out.println(index + ". "+candidate.getName());
                index++;
            }
        }
        if (index == 1){
            System.out.println("None");
        }
    
    }
    
    public static char inputchar(String p) {
        System.out.println(p);
        Scanner input = new Scanner(System.in);
        return input.next().charAt(0);
    }
    
    public static String inputstring(String p) {
        System.out.println(p);
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
    
    public static int inputint(String p) {
        System.out.println(p);
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }
    
    
    public String getName() {
        return "Bob";
    }
    
    public String getSlogan() {
        return "Bob always Wins!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec221016();
        
        if (candidates.length == 0) {
            return r;
        } else {
            Random b = new Random();
            int c = b.nextInt(candidates.length);
            return candidates[c];
        }
      
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec221016();
        
        if (votes.length == 0) {
            return r;
        } else {
            Random b = new Random();
            int c = b.nextInt(votes.length);
            return votes[c];
        }
 
    }
    
}
