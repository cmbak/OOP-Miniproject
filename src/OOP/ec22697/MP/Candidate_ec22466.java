package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22466 extends Candidate {
    
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Candidate[] allCandidates = A3.getCandidateArray();
        int length = 1;
        Candidate[] candidates = new Candidate[length];

        System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
        String choice = s.nextLine();
        
        if(choice.equals("a")){
            
            String name;
            
            System.out.println("Please enter a username.");
            name = s.nextLine();

            Candidate specific = A3.getByUsername(name, allCandidates);

            if(specific != null){
                
                System.out.println("user not found");
            }
            else{
                
                candidates[0] = specific;
                length = length + 1;
                Candidate[] temp = new Candidate[length];
                
                for(int i = 0; i<length-1; i++){
                    
                    temp[i+1] = candidates[i];
                }
                
                candidates = temp;
            }
        } else if(choice.equals("b")){

            int random = (new Random()).nextInt(allCandidates.length);
            candidates[0] = allCandidates[random];
            length = length + 1;
            Candidate[] temp = new Candidate[length];
            for(int i = 0; i<length-1; i++){

                temp[i+1] = candidates[i];
            }

            candidates = temp;
        }                    
        else if(choice.equals("c")){
            
            Candidate[] votes = new Candidate[allCandidates.length];

            for(int i = 0; i<allCandidates.length; i++){

                votes[i] = (allCandidates[i]).vote(candidates);
            }
        }
        
        return;
    
    }
    
    public String getName() {
        return "Zoro";
    }
    
    public String getSlogan() {
        return "3 sword style";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22466();

         for (Candidate x : candidates)
        {
            if (x.getName().equals("zoro") && x.getSlogan().equals("3 sword style"))
            {
                return x;
            }
        }
        return r;

    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22466();
        Candidate winner = new Candidate_ec22466();
            
    int current_winner = 0;

        for (Candidate s : votes)
        {
            int count = 0;

            for (Candidate a : votes)
            {
                if (a==s)
                {
                    count = count + 1;
                }
            }

            if (count >= current_winner)
            {
                current_winner = count;
                winner = s;
            }
            
        return winner;
            
        }
        return r;
    }
}
