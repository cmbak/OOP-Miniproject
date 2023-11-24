package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22872 extends Candidate {
    
    public String getName() {
        return "Harry Potter";
    }
    
    public String getSlogan() {
        return "More magic teaching";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        
        if (candidates.length == 0) 
        {
            return new Candidate_ec22872();
        }
        for (Candidate c : candidates)
            {
                if (c.getName().equals("Jett"))
                {return c;}
                
            }
                
        Random r = new Random();
        int c = r.nextInt(candidates.length);
        return candidates[c]; 
        // Add code that inspects the array candidates
        // calling the various methods of the Candidate objects
        // and finds a Candidate object to vote for.
        
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        
        if (votes.length == 0) return new Candidate_ec22872();
        
        Candidate currentWinner = votes[0];

        int highestvotes = 0;
        for(Candidate c : votes){
            int count = 0;
            for( Candidate x : votes){
                if(x.equals(c)){
                    count++;
                }
            }
            if(count >= highestvotes)
            {
                highestvotes = count;
                currentWinner = c;
            }
        }
        // Add code that either counts the votes
        // or uses some other way to return an element 
        // of the array vote as the winner of an election.
        
        return currentWinner;
    }
    



    public static void print(String message)
    {
        System.out.println(message);
        return;
    }

    public static String si(String message)
    {
        String input;
        Scanner scanner = new Scanner(System.in); 
        
        System.out.println(message);
        input = scanner.nextLine();
        
        return input;
    }
    
    public static Candidate[] addCandidate(Candidate[] currCandidates, Candidate to_add_to_array)
    {
        Candidate[] temp = new Candidate[currCandidates.length +1];
        for(int i =0; i < currCandidates.length; i ++)
        {
            temp[i] = currCandidates[i];
        }
        temp[currCandidates.length] = to_add_to_array;
        return temp;
    }

    public static void runelection(Candidate counter, int times, Candidate[] currCandidates, Candidate[] allCandidates)
    {
        Candidate[] winners = new Candidate[times];
        Candidate winner = A3.getByUsername("ec22572", allCandidates);
        for(int i=0; i < times; i++)
        {
            
            Candidate[] votes = new Candidate[allCandidates.length];

            for(int j = 0; j< allCandidates.length; j++){

                votes[j] = allCandidates[i].vote(currCandidates);
            }
            winner = (counter).selectWinner(currCandidates);
            winners[i] = winner;
            
            
            
        }            
      
        int count =0;
        int highest_count = 0;
        int note =0;
        for(int i = 0; i<winners.length; i ++)
        {
            for(int j= 0; j< winners.length; j++)
            {
                if(winners[i].equals(null))
                {

                }
                else if(winners[i].equals(winners[j]))
                {
                    count++;
                }
                else{}
            }
            if(highest_count < count)
            {
                highest_count = count;
                note = i;
            }
        }
        Candidate overall_winner = winners[note];
        System.out.println("The most common winner is " + overall_winner.getName() + " with " + overall_winner.getSlogan());
    }
    public static void election()
    {
        Candidate[] allcandidatelist = new Candidate[546];
        allcandidatelist = A3.getCandidateArray();
        final String code = "exit";
        boolean leave = false;
        Candidate[] currCandidates = new Candidate[0];
        String choice = si("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
        while(!leave)
        {
            Candidate to_add_to_array = A3.getByUsername("ec22572", currCandidates);
            if(choice.equals("a"))
            {
                
                boolean candidate_actual = false;
                while(!candidate_actual){
                    String Candidate_choice = si("Please enter a username");
                    to_add_to_array = A3.getByUsername(Candidate_choice, allcandidatelist);
                    if(to_add_to_array.equals(null))
                    {
                        print("Incorrect username enter a valid username");
                    }
                    else{
                        candidate_actual =true;
                    }
                }
                currCandidates = addCandidate(currCandidates, to_add_to_array); 
                for(int i = 0; i < currCandidates.length; i++)
                {
                    print((i+1) + ") "+ currCandidates[i].getName());
                }
            }
            else if(choice.equals("b"))
            {
                Random r = new Random();
                int x = r.nextInt(allcandidatelist.length);
                to_add_to_array = allcandidatelist[x];
                currCandidates = addCandidate(currCandidates, to_add_to_array);
                for(int i = 0; i < currCandidates.length; i++)
                {
                    print((i+1) + ") "+ currCandidates[i].getName());
                }
            }
            else if(choice.equals("c"))
            {
                String counter = si("Who would you like to count the votes?");
                String times = si("How many times should the election be run?");
                int total_times = Integer.parseInt(times);
                Candidate off_counter = A3.getByUsername(counter, allcandidatelist);
                if(off_counter == null)
                {
                    print("incorrect username please try again");
                }
                else{
                    runelection(off_counter, total_times, currCandidates, allcandidatelist);
                }
                

            }
            
            choice = si("Would you like to a) add a specific candidate b) add a candidate at random c) run the election or d) leave?");
            if(choice.equals("d"))
            {
              leave = true;   
            }
        }
    }
 

    public static void main (String [] args) 
{
    election(); 
    System.exit(0);
}
}
