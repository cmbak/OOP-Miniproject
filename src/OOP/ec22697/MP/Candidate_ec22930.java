package OOP.ec22697.MP;// File Candidate_ec22930.java
//
// Machine generated stub for Assignment 2

import java.util.Scanner;
class Candidate_ec22930 extends Candidate {
    
    public String getName() {
        return "Nasrudin";
    }
    
    public String getSlogan() {
        return "It is what it is";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22930();
        
        if (candidates.length != 0) r = candidates[0];
        
        
 
        // Add code that inspects the array candidates
        // calling the various methods of the Candidate objects
        // and finds a Candidate object to vote for.
        
        return r;
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22930();
        
        if (votes.length != 0) r = votes[0];
 
        // Add code that either counts the votes
        // or uses some other way to return an element 
        // of the array vote as the winner of an election.
        
        return r;
    }
    
    public static void main(){
        Candidate[] allcandidates = A3.getCandidateArray();
        Candidate[] selected = new Candidate[100];
        int pos = 0;
        boolean exit = false;
        String choice;
        Scanner scanner = new Scanner(System.in);
        while(!exit){
            System.out.println("the candidates are");
            if(pos == 0){
                System.out.println("None");
            }
            else{
                for(int i = 0 ; i < pos ;i++){
                    System.out.println(selected[i].getName());
                    
                }
            }

            System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
            choice = scanner.nextLine();
            
            
            if(choice.equals("a")){
                System.out.println("Please enter a username.");
                String name = scanner.nextLine();
                selected[pos] = A3.getByUsername(name, allcandidates);
                pos++;
            }
            else if(choice.equals("b")){
                System.out.println("");
            }
            else if(choice.equals("c")){
                System.out.println("who should count the votes");
                String name = scanner.nextLine();
                Candidate counter = A3.getByUsername(name, allcandidates);
                System.out.println("how many times do you want to run the election");
                String runstr = scanner.nextLine();
                int runs = Integer.parseInt(runstr);
                Candidate[] votes = new Candidate[allcandidates.length];
                Candidate[] winners = new Candidate[runs];
                for(int i = 0 ; i <runs ; i++){
                    for(int j = 0; j < allcandidates.length ; j++){
                        votes[j] = allcandidates[j].vote(selected);
                    }
                    winners[i]= counter.selectWinner(votes);
                }
                System.out.println("the most common winner was");
                Candidate r = winners[0];
                int largest = 0;
                int numberOf = 0;
        
                for(int i = 0 ; i<winners.length ; i++)
                {
                    for(int j = 0 ; j<winners.length ; j++)
                    {
                        if(winners[i] == winners [j])
                        {
                            numberOf++;
                        }
                    }
            
                    if(numberOf > largest)
                    {
                        r = winners[i];
                        largest = numberOf;
                    }
                    numberOf = 0;
                }
                System.out.println("the most common winner was"+r.getName());
            }
            else if(choice.equals("d")){
                exit = false;
            }
            else{
                System.out.println("");
            }
        }
          
                
                
                
                
   
                
              
    }
    
    
}
