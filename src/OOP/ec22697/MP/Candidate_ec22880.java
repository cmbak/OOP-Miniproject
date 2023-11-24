package OOP.ec22697.MP;// File Candidate_ec22880.java
//
// Machine generated stub for Assignment 3

import java.util.Random;
import java.util.Scanner;


class Candidate_ec22880 extends Candidate {
    
    
    public static String inputString(String message){
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        String response = scanner.nextLine();
        return response;
    }
    
    public static int inputInt(String message){
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        int response = scanner.nextInt();
        return response;
    }
    
    public Candidate selectWinner (Candidate[] candidate){
        
        if (candidate.length == 0){
            return new Candidate_ec22880();
        }
        
        int highest_vote = 0;
        
        for (int i = 0; i<candidate.length; i++){
            
            int count = 0;
            
            for (int j = 0; j<candidate.length; j++){
                 
                if (candidate[i] == candidate[j]){
                    count ++;
                }
            }
            
            if (count > highest_vote){
                highest_vote = count;
            }
             
        }
        
        return candidate[highest_vote];
    }
    
    public String getName() {
        return "Rish";
    }
    
    public String getSlogan() {
        return "Power belongs to the people";
    }
    
    public Candidate vote(Candidate[] candidate){
        
        if (candidate.length == 0){
            return new Candidate_ec22880();
        }
        
        for (Candidate c : candidate){
            if (c.getName().equals("Rish")){
                return c;
            }
        }
        
        Random r = new Random();
        int c = r.nextInt(candidate.length);
        return candidate[c];
    }

    public static void main (String [] a){
        
        Candidate[] allCandidates = A3.getCandidateArray();
        Candidate[] NewCandidate = new Candidate[allCandidates.length];
        int count = 0;
        
        while (true){
            
            String response = inputString("Would you like to a) add a specific candidate " + 
                                          "b) add a candidate at random c) run the election?");
            
            if (response.equals("a")){
                String UserName = ("Please enter a username.");
                NewCandidate[count] = A3.getByUsername(UserName,allCandidates);
                count++;
                System.out.println("Candidates are");
                for (int i = 0; i<count; i++){
                    System.out.println(i + ". " + allCandidates[i].getName());
                }
            }
            
            if (response.equals("b")){
                System.out.println("= Running Repeated Elections =");
                if (allCandidates.length == 0){
                    System.out.println("none");
                }
                
                else {
                    
                    Random random = new Random();
                    int RandomCandidate = random.nextInt(allCandidates.length);
                    NewCandidate[count] = allCandidates[RandomCandidate];
                    count++;
                    System.out.println("Candidates are");
                    for (int i = 0; i<count; i++){
                        System.out.println(i + ". " + allCandidates[i].getName());
                    }
                    }
            }
            
            if (response.equals("c")){
                
                   String VoteCounter = inputString("Who should count the votes");
                   int electionNumber = inputInt("How many times shall we run the election");
                   for (int y = 0;y < allCandidates.length;y++)
                   {
                    if (allCandidates[y].un.equals(VoteCounter))
                        {
                            Candidate[] FinalCandidates = new Candidate[count];
                            for (int z = 0;z<count;z++)
                            {
                                FinalCandidates[z] = NewCandidate[z];
                            }
                            Candidate winner = allCandidates[y].selectWinner(FinalCandidates);
                            System.out.println("The most common winner is "+winner.getName());
                        }
                   }
            }
            
        }
        

    }
}
