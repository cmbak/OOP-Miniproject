package OOP.ec22697.MP;// File Candidate_ec22413.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22413 extends Candidate {
    
    public static String inputstring(String message)
    {
        String answer;

        Scanner scanner= new Scanner(System.in);

        System.out.println(message);     //prints the message that was passed to the function
        answer=scanner.nextLine();

        return answer;
    }
    
    public static int inputint(String message)
    {
        int input=0;
        String answer;

        answer=inputstring(message);

        input=Integer.parseInt(answer);  //converts to an integer 

        return(input);
    }
    
    
    public static void main(String[]args)
    {
        Candidate[] AllCandidates = A3.getCandidateArray();
        Candidate[] TotalVotes = new Candidate[AllCandidates.length];
        
        String user_choice;
        
        int count=0;
        
        String specific_candidate;
        
        boolean stop= false;
        
        while (!stop){
            
            System.out.println("Candidates are");
            
            if (count==0) System.out.println("none");
            
            else{
                
                for (int i=0; i< count; i++){
                    System.out.println(TotalVotes[i].getName());
                }
                
            }
            System.out.println("= Running Repeated election =");
            
            user_choice=inputstring("Would you like to a) add a specific cadidate b) add a candidate at random c)run the election?");
            
            if (user_choice.equals("a"))
            {
                specific_candidate=inputstring("Enter the name of the candidate");
                
                for (int match=0; match< AllCandidates.length; match++)
                {
                    if (AllCandidates[match].un.equals(specific_candidate))
                    {
                        TotalVotes[count]=AllCandidates[match];
                    }
                }   
            }
            
            else if (user_choice.equals("b"))
            {
                Random random=new Random();
                
                int number= random.nextInt(AllCandidates.length);
                
                TotalVotes[count]=AllCandidates[number];
                
                count=count+1;
            }
            
            else if (user_choice.equals("c"))
            {
                String Select_winner=inputstring("Who will count the votes?");
                
                int frequency=inputint("How many times shall we run the election?");
                
                for (int g = 0;g < AllCandidates.length;g++)
                {
                    if (AllCandidates[g].un.equals(Select_winner))
                    {
                        Candidate[] Q = new Candidate[count];
                        
                        for (int h = 0;h<count;h++)
                        {
                            Q[h] = TotalVotes[h];
                        }
                        
                        Candidate winner = AllCandidates[g].selectWinner(Q);
                        System.out.println("Most common winner is "+winner.getName());
                        stop= true;
                    } 
                }
            }
            
            else
            {
                System.out.println("Incorrect input, please choose an option");
            } 
        }
    }

    public String getName() {
        return "Yaser Alharbi";
    }
    
   public String getSlogan() {
        return "Hakuna Matata";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        //if array is empty
        if (candidates.length == 0) 
            return new Candidate_ec22413();
        
        
        // First search for self on the list of candidates.
        for (Candidate c : candidates)
            if (c.getName().equals("Yaser Alharbi"))
                return c;
        
        // Default to random candidate on list.
        Random r = new Random();
        int c = r.nextInt(candidates.length);
        return candidates[c];
    }
    
    public Candidate selectWinner(Candidate[] candidate) {
        
        // If array is empty, return instance of this class.
        if (candidate.length == 0)
            return new Candidate_ec22413();
        
        int highest_vote = 0;
        
        for (int outer = 0; outer<candidate.length; outer++){
            
            int count = 0;
            
            for (int inner = 0; inner<candidate.length; inner++){
                 
                if (candidate[outer] == candidate[inner]){
                    count=count+1;
                }
            }
            
            if (count > highest_vote){
                highest_vote = count;
            }
             
        }
        
        return candidate[highest_vote];
    }
    
}
