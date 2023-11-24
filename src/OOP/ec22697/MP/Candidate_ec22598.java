package OOP.ec22697.MP;

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
class Candidate_ec22598 extends Candidate 
{
    private static <T> void p(T s) { System.out.print(s);}
    private static <T> void pr(T s) { System.out.println(s);}
    private static Scanner sc() {return new Scanner(System.in);}
    private static char getChar(String q) 
    {
        pr(q);
        String t = sc().nextLine(); 
        return ( t.length()==0 ? ' ' : t.charAt(0) );   
    }
    
    private static int getInt(String q) 
    {
        pr(q);
        return sc().nextInt();
    }
    private static String getString(String q) 
    {
        pr(q);
        return sc().nextLine();
    }
    
     
    public static void main(String[] args) 
    {
         Candidate[] candidates = A3.getCandidateArray();
         ArrayList<Candidate> all_candidates = new ArrayList<Candidate>();
      
         boolean exit = false;
         String choice ="?";

         do 
         {


             choice = getString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election? d) exit"); 
             switch (choice) 
             {
                 case "a": String specificName = getString("Which specific user would you like to include?"); add_candidate(all_candidates,getSpec(specificName,candidates,all_candidates));break;
                 case "b": add_candidate(all_candidates,getRandomCandidate(candidates)); break;
                 case "c": run_c(candidates,all_candidates); break;
                 case "d": exit = true; break;
                 default: pr("Option '"+choice+"' not available.");
             }
        } while (!exit);
    }
    
    private static Candidate getSpec(String specificName, Candidate[] candidates, ArrayList<Candidate>all_candidates)
    {
         if (!validUser(specificName,candidates)) 
         {
             pr("User not found."); Candidate_ec22598.main(null);
             
         }
         return A3.getByUsername(specificName, candidates); 
    }
    private static void add_candidate(ArrayList<Candidate>all_candidates, Candidate new_can)
    { 
       all_candidates.add(new_can);
       print_all(all_candidates);
    }
    private static void print_all(ArrayList<Candidate> all_candidates)
    {
        if(all_candidates.size()==0) 
        {
            pr("none");
        }
        else
        {
            pr("Candidates are:");
            for(int i=0; i<all_candidates.size(); i++)
            {
                pr(all_candidates.get(i).getName());
            }
        }
    }
    private static boolean validUser(String specificName, Candidate[] candidates)
    {
        Candidate specificCandidate = A3.getByUsername(specificName, candidates);           
        if(specificCandidate!=null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    private static void run_c(Candidate[] candidates, ArrayList<Candidate> all_candidates)
    {
        String voter = getString("Who should count the votes?");
        if(!validUser(voter, candidates)) run_c(candidates,all_candidates);
        
        int num = getInt("How many times should we run the election?");
        
        Candidate voter_1 = A3.getByUsername(voter,candidates);
        run_election(num,candidates,all_candidates,voter_1);
    }
    public String getName() 
    {
        return "Eliott";
    }
    public String getSlogan() 
    {
        return "More trees!";
    }
    private static void run_election(int num, Candidate[] candidates, ArrayList<Candidate> all_candidates, Candidate voter)
    {
        ArrayList<Candidate> vote_box = new ArrayList<Candidate>();
        ArrayList<Candidate> winners = new ArrayList<Candidate>();
        Candidate[] candidate = all_candidates.toArray(new Candidate[0]);
        if (candidate.length == 0) 
        {
            pr("There were no other winners.");
        }
         
        for (int i=0; i<num;i++) 
        {
            
            for(int j =0; j<num;j++)
            {
                
                try 
                {
                     vote_box.add(candidates[j].vote(candidate));

                }
                catch(Exception e)
                {
                }
            }
            Candidate[] votes = vote_box.toArray(new Candidate[0]);
            winners.add(voter.selectWinner(votes));
       }
       
       MostCommonWinner(winners.toArray(new Candidate[0]));
    }
    private static void MostCommonWinner(Candidate[] winners)
    {              
       if(winners.length==0) pr("There is no winner");              
       else if(winners.length==1) pr("The winner is " + winners[0].getName());                 
       Candidate currentWinner = winners[0];
       int highest_count =0;
       for(int i=0; i<winners.length; i++)
       {
            int count=0;
            for(int j=0; j<winners.length; j++)
            {
                if(winners[i]== winners[j]) count++;
            }
            if(count>highest_count)
            {
                currentWinner=winners[i];
                highest_count=count;
            }
       }                       
       pr("The most common winner is " + currentWinner.getName());
     }
    private static Candidate getRandomCandidate(Candidate[] allContributions)
    {
        Random r = new Random();
        int random_num = r.nextInt(allContributions.length);
        if(allContributions[random_num]==null)
        {
            getRandomCandidate(allContributions);
        }
        return allContributions[random_num];
    }
    public Candidate vote(Candidate[] candidates) {
        
        // If array is empty, return instance of friend's class.
        if (candidates.length == 0) 
            return new Candidate_ec22598();
        
        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("More trees!"))
                return c;
        
        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Kim"))
                return c;
        
        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        // If array is empty, return instance of friend's class.
        if (votes.length == 0) 
            return new Candidate_ec22598();

        // Default to first vote, but this will be over-written.
        Candidate currentWinner = votes[0];
        
        // Count the votes for each object in the array,
        // selecting one with the most.
        int highestCount = 0;
        for (Candidate c : votes) {
            
            int count = 0;
            for (Candidate x : votes)
                if (x == c)
                    count++;
            if (count > highestCount) {
                highestCount = count;
                currentWinner = c;
            }
        }
        
        return currentWinner;
    }
    
}
