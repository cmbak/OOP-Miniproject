package OOP.ec22697.MP;

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
class Candidate_ec22553 extends Candidate
{
    public String getName()
    {
        return "Donkey";
    }

    public String getSlogan()
    {
        return "What are you doing in my swamp";
    }

    public Candidate vote(Candidate[] candidates) 
    {
        //Return the class of ec22906 if empty
        if (candidates.length == 0)
        { 
            return new Candidate_ec22551();
        }

        //Check if there are any other shrek enjoyers
        for(Candidate c : candidates)
        {
            if(c.getSlogan().equals("What are you doing in my swamp"))
            {
                return c;
            }
        }

        //Check if anyone else has donkey as their name
        for(Candidate c : candidates)
        {
            if(c.getName().equals("donkey"))
            {
                return c;
            }
        }

        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }

    public Candidate selectWinner(Candidate[] votes)
    {
        //Return the class of ec22906 if empty
        if (votes.length == 0)
        { 
            return new Candidate_ec22906();
        }

        // Default to first vote, but this will be over-written.
        Candidate currentWinner = votes[0];

        int highestCount = 0;
        for (Candidate c : votes) 
        {
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


//****************************************************************************/


    public static void print(String t)
    {
        System.out.println(t);
    }

    public static String getResponse(String t)
    {
        print(t);
        Scanner scanner = new Scanner(System.in);
        String r = scanner.nextLine();
        return r;
    }

    public static int getInt(String t)
    {
        print(t);
        Scanner scanner = new Scanner(System.in);
        int r = scanner.nextInt();
        return r;   
    }

    public static void show_candidates(ArrayList<Candidate> c)
    {
        print("Candidates are: ");

        int count = 0;
        for(Candidate x: c)
        {
            System.out.println((count + 1) + ". " + x.un);
            count ++;
        }
    }

    //copped from A3
    public static char getChar(String q) {
        print(q);
        Scanner scanner = new Scanner(System.in);
        String t = scanner.nextLine(); 
        return ( t.length()==0 ? ' ' : t.charAt(0) );   
        //return sc().nextLine().charAt(0);
    }

//****************************************************************************/

    public static void main(String[] args) 
    {
        ArrayList<Candidate> candidates = new ArrayList<>();

        boolean Election_run = false;

        while(!Election_run)
        {
            char choice = getChar("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
            switch(choice)
            {
                case 'a': add_specific_candidate(candidates); break;
                case 'b': add_random_candidate(candidates); break;
                case 'c': run_election(candidates); Election_run = true; break;
            }
        }
        
    }

    private static void add_specific_candidate(ArrayList<Candidate> c)
    {
        String Spec_cand = getResponse("Please enter a username.");
        boolean found = false;
        for(Candidate x: A3.getCandidateArray())
        {
            if(Spec_cand.equals(x.un))
            {
                c.add(x);
                found = true;
            }
        }
        if(!found)
        {
            print(Spec_cand + " Does not exist\n");
        }
        show_candidates(c);
    }



    private static void add_random_candidate(ArrayList<Candidate> c)
    {
        Candidate[] all = A3.getCandidateArray();
        Random random = new Random();
        int random_choice = random.nextInt(all.length);
        Candidate value = all[random_choice];
        c.add(value);
        show_candidates(c);
    }



    private static void run_election(ArrayList<Candidate> c)
    {
        Candidate[] d = new Candidate[(c.size())];
        int Counter = 0;
        for(Candidate x: c)
        {
            d[Counter] = x;
            Counter++;
        }
        boolean finished = false;
        String vote_counter = getResponse("Who should count the votes?");
        while(!finished)
        {
            try
            {
                A3.getByUsername(vote_counter, d);
                finished = true;
            }
            catch(Exception e)
            {
                vote_counter = getResponse("Who should count the votes?");
            }
        }

        int attempt_counter = getInt("How many elections?");
        Candidate[] winner_array = new Candidate[attempt_counter];
        for(int i = 0; i < attempt_counter; i++)
        {
            winner_array[i] = (A3.getByUsername(vote_counter, A3.getCandidateArray())).selectWinner(d);
        }
        Candidate currentWinner = winner_array[0];
        for(int i = 0; i < attempt_counter; i++)
        {   
            int highestCount = 0;
            for (Candidate j : winner_array) 
                {
                    int count = 0;
                    for (Candidate x : winner_array)
                        if (x == j)
                            count++;
                    if (count > highestCount) {
                        highestCount = count;
                        currentWinner = j;
                    }
                }
        }
        System.out.println("Most common winner is" + currentWinner.un);
    }
}
