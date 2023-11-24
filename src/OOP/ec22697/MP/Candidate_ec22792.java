package OOP.ec22697.MP;

import java.util.Scanner;
import java.util.Random;

class Candidate_ec22792 extends Candidate {
  
    
    //main
    public static void main(String[] args) {
    
        Candidate[] allContributions = A3.getCandidateArray();
        Candidate[] elected= new Candidate[allContributions.length];
        int pos= 0;
        String option="";
        System.out.println("=Running repeated elections=");
        System.out.println("Candidates are 546 in total");
        
        
        while (!option.equals("d")){
            
            System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election d) other menu?");
            option= answer();
            
            if (option.equals("d")){
                option=menu();
                 if (option.equals("a")){
                    System.out.println("Stopping election");
                     break;
                }
                else if (option.equals("b")){
                    Candidate candNew=optB(allContributions);
                    elected[pos]= candNew;
                    pos= pos+1;
                }
                else if (option.equals("[cde]")){
                    System.out.println("Love the energy but these are coming soon!");
                }
                else if (option.equals("f")) {
                    printCandidates(elected);
                }      
            }
            
            else {
                if (option.equals("a")){
                Candidate candNew=optA(allContributions);
                elected[pos]= candNew;
                pos= pos+1;
                printCandidates(elected);
                }
            
                else if (option.equals("b")){
                    Candidate candNew=optB(allContributions);
                    elected[pos]= candNew;
                    pos= pos+1;
                }

                else if (option.equals("c")){
                    System.out.println("Running the election...");
                    optC(allContributions, elected);
                }


                System.out.println("Enter your next option (d to exit)");
                option= inputString();
            } 
        }
    } //END main
    
    //Print the elected candidates
    public static void printCandidates (Candidate[] elected) {
        
        Candidate cand= elected[0];
        if (cand==null){
            System.out.println("No candidates were elected");
        }
        else {
            System.out.println("The elected candidates are:");
            for (int i=0; i<elected.length; i++) {
                cand= elected[i];
                if (cand==null) {
                    break;
                }
                else {
                    System.out.println(cand.getName());
                }
                
            }
            
        }
        return;
        
    } //END printCandidates
  
    //Print the second menu and ask user for choice
    public static String menu(){
        System.out.println("Would you like to");
        System.out.println("a) exit ");
        System.out.println("b) run same election many times");
        System.out.println("c) check who counts honestly ");
        System.out.println("d) run all possible two-candidate elections");
        System.out.println("e) run a higher-order election");
        System.out.println("f) print all candidates");
        
        String choice=inputString();
        choice= choice.toLowerCase();
        
        while (!choice.matches("[abcdef]")){
            System.out.println("Enter a valid option a-f:");
            choice= inputString();
        }
                          
        return choice;
    } //END menu
    
    //Option A: enter a candidate
    public static Candidate optA(Candidate[] allContributions){
        System.out.println("Please enter a username:");
        String username= inputString();
        Candidate candNew= A3.getByUsername(username, allContributions);
        
        if (candNew== null) {
            System.out.println("This candidate does not exist");
            optA(allContributions);
        }
        else {
            System.out.println("The candidate is " + candNew.getName());
            System.out.println("The slogan is " + candNew.getSlogan());
        }
        return candNew;
    }
    
    //Option B: add a candidate at random
    public static Candidate optB(Candidate[] allContributions){
        Random ran= new Random();
        int ranpos= ran.nextInt(allContributions.length);
        String username= allContributions[ranpos].un;
        Candidate candNew= A3.getByUsername(username, allContributions);
        System.out.println("The random candidate is " + candNew.getName());
        System.out.println("The slogan is " + candNew.getSlogan());
        
        return candNew;
    }
    
    //Option C: run the election
    public static void optC(Candidate[] allContributions, Candidate[] elected){
        System.out.println("Who should run the election?");
        String runner= inputString();
        Candidate candNew= A3.getByUsername(runner, allContributions);
        Candidate[] votes= new Candidate[allContributions.length];
        
        if (candNew== null) {
            System.out.println("This candidate does not exist");
            optC(allContributions, elected);
        }
        
        else {
            System.out.println("How many times shall we run the election?");
            int repeat= Integer.parseInt(inputString());
            
            //Make a copy of elected array
            Candidate[] copy= new Candidate[elected.length];
            for (int i=0; i<elected.length;i++){
                copy[i]=elected[i];
            }

            for (int j=0; j<repeat;j++){
                for (int k=0; k<copy.length;k++){
                    // Adapted from A3 vote method email
                    try {
                        votes[k]= elected[k].vote(copy);
                    }
                    catch (Exception e) {
                        votes[k]=new Candidate_ec22792();
                    }
                }
                
            }
            Candidate winner=candNew.selectWinner(votes);
            System.out.println("The winner is " + winner.getName());        
        }
            
            
        return;
    } //END optC
       
        

 
    // Get user's answer
    public static String answer(){
        Scanner sc= new Scanner(System.in);
        String ans= "";
        while (!ans.equals("a") && !ans.equals("b") && !ans.equals("c") && !ans.equals("d")){
            System.out.println("Choose option a, b, c or d");
            ans= inputString();
            ans= ans.toLowerCase();
        }
        
        return ans;
    } // END answer
    
    //input a String
    public static String inputString(){
        Scanner sc= new Scanner(System.in);
        String inp= sc.nextLine();
        
        return inp;
    } //END inputString
    
    //get name of Candidate ec22792
    public String getName(){
        return "Ilenia";
    } // END getName
    
    //return slogan of Candidate ec22792
    public String getSlogan(){
        return "More programming!";
    } // END getSlogan
    
    //Vote for a candidate
    public Candidate vote (Candidate[] candidates){
        
        Candidate r= new Candidate_ec22792();
        
        //Return this class if array is empty
        if (candidates.length!=0) {
            r=candidates[0];
        }
        
        if (candidates.length==0){
            return new Candidate_ec22792();
        }
        
        //Search for a candidate named Ilenia 
        for (Candidate c: candidates) {
            if  (c.getName().equals("Ilenia")){
                return c;
            }
        } 
        
        //Search for a candidate named Amy
        for (Candidate c: candidates) {
            if  (c.getName().equals("Amy")){
                return c;
            }
        }
        
        //Search for a candidate with the following slogan
        for (Candidate c: candidates){
            if (c.getSlogan().equals("May the force be with you"))
                return c;
        }
        
        return r;
    } // END vote
    
    //Select the winner
    public Candidate selectWinner(Candidate[] votes) {
    
        Candidate mostvoted= votes[0];
        int largest=0;
        int total=0;
     
        //If votes array is empty return a new candidate
        //Adapted from Assignment 2 Spec example
        if (votes.length == 0){
            return new Candidate_ec22792();
        }
        
        for (Candidate c : votes) {
            
            for (Candidate x : votes)
                if (x == c)
                    total=total+1;
            if (total > largest) {
                largest = total;
                mostvoted = c;
            }
        }
        
        return mostvoted;
     } //END selectWinner
}
