package OOP.ec22697.MP;// File Candidate_ec22579.java
//

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22579 extends Candidate 
{
    
    public static void main(String [] args)
    {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit)
        {
            System.out.println("Would you like to:");
            System.out.println("a) exit b) run same election many times c) check who counts honestly d) run all possible two-candidate elections ");
            String option = scanner.nextLine();

            if ((option.equals("a")||(option.equals("A"))))
            {
                exit = true;
            }
            else if ((option.equals("b")||(option.equals("B"))))
            {
                menu_b();
            }
            else if ((option.equals("c")||(option.equals("C"))))
            {
                check_who_counts_honestly();
            }
            else if ((option.equals("d")||(option.equals("D"))))
            {
                two_candidate_elections();
            }
            else
            {
                System.out.println("Invalid option");
            }
        }

            return;
    }

    public static void menu_b()
    {
        Scanner scanner = new Scanner(System.in);
        Candidate[] all_candidates = A3.getCandidateArray(); //All classes of every student
        Candidate[] running_candidates = new Candidate[all_candidates.length];
        int array_nextspace = 0;
        boolean finished = false;
        
        while(!finished)
        {
        
            System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election d) exit?");
            String option = scanner.nextLine();

            // Add specific candidate //

            if ((option.equals("a"))||(option.equals("A")))
            {
                System.out.println("Please enter a username:");
                String candidate_name = scanner.nextLine();
                Candidate specific_candidate = A3.getByUsername(candidate_name, all_candidates);

                if (specific_candidate != null) 
                {
                    running_candidates[array_nextspace] = specific_candidate;
                    array_nextspace = array_nextspace + 1;
                }
                else 
                {
                    System.out.println("User not found.");
                }

                print_candidates(running_candidates, array_nextspace);
            }

            //Add Random candidate //

            else if ((option.equals("b"))||(option.equals("B")))
            {
                boolean successfully_added = false;

                while (!(successfully_added)) //Will loop until a random candidate has been added to the users candidate array
                {
                    Random random = new Random(); 
                    int random_candidate = random.nextInt(all_candidates.length);
                    Candidate check_if_present = all_candidates[random_candidate];

                    boolean duplicate_found = false;

                    for (int a=0;a<array_nextspace;a++)  //checks to see if the candidate has already been entered into the array
                    {
                       if (running_candidates[a]==check_if_present)
                        {
                               duplicate_found = true;
                        }
                    }

                    if (duplicate_found == false)
                    {
                        running_candidates[array_nextspace]= all_candidates[random_candidate];
                        System.out.println("User added: "+  running_candidates[array_nextspace].getName() );
                        array_nextspace = array_nextspace +1;
                        successfully_added = true;
                    }

                    else
                    {
                        successfully_added = false;
                    }
                }

                print_candidates(running_candidates, array_nextspace);
            }

            //Count Votes//
            else if ((option.equals("c"))||(option.equals("C")))
            {
                if (running_candidates[0]==null)
                {
                    System.out.println("No candidates in the election");
                }

                else
                {
                    Candidate[] all_winners = run_election(all_candidates,running_candidates);
                    final_winner(all_winners);
                }
            }
            

            else if ((option.equals("d"))||(option.equals("D")))
            {
                finished = true;
            }
        }
        
        return;

    }


    public static void two_candidate_elections()
    {
        Candidate[] all_candidates = A3.getCandidateArray();
        for (int i=0;i<(all_candidates.length)-1;i++)
        {
            for(int j=i+1;j<all_candidates.length;j++)
            {
                Candidate[] running_candidates = new Candidate[2];
                running_candidates[0] = all_candidates[i];
                running_candidates[1] = all_candidates[j];

                Candidate counter = new Candidate_ec22579();
                Candidate[] votes = new Candidate[all_candidates.length];

                for (int k=0;k<all_candidates.length;k++) 
                {
                    
                    try
                    {
                        System.out.println();
                        votes[k] = all_candidates[k].vote(running_candidates);
                    }
                    catch (Exception e)
                    {
                        Candidate voter = new Candidate_ec22579();
                        votes[k]=null;
                    }
                                                                
                }

                Candidate election_winner = counter.selectWinner(votes);
                System.out.println("2 Candidates: "+running_candidates[0].getName()+", "+running_candidates[1].getName()+". Winner: "+ election_winner.getName());
                //System.out.println("The winner of this election is: "+ election_winner.getName()+"\n");

            }
        }
    }

    public static void check_who_counts_honestly()
    {
        Candidate[] all_candidates = A3.getCandidateArray();
        Candidate test_candidate = new Candidate_ec22579();
        Candidate[] test_votes = new Candidate[10];
        int count = 0;

        test_votes[0]= new Candidate_ec22570();;
        test_votes[1]= new Candidate_ec22572();;
        test_votes[2]= new Candidate_ec22573();;
        test_votes[3]= new Candidate_ec22576();;
        test_votes[4]= new Candidate_ec22578();;

        for (int i=5;i<test_votes.length;i++)
        {
            test_votes[i]= test_candidate;
        }

        Candidate winner = test_votes[5];
        System.out.println("The winner should be: "+winner.getName());
        System.out.println("Users who are counting honestly: ");
        
        for(int i=0;i<all_candidates.length;i++)
        {
            try
            {
                if ((all_candidates[i].selectWinner(test_votes))==winner)
                {
                    System.out.println(all_candidates[i].getName());
                    count = count + 1;
                }
            }
            catch (Exception e)
            {

            }
        }

        System.out.println("In total "+count +" users count honestly. ");


        return;

    }

    public static int amountofruns()
    {
        Scanner scanner = new Scanner(System.in); 
        boolean valid = false;
        int election_runs = 0;
        
        while (!valid)
        {
            System.out.println("How many times shall we run the election?");
            String election_runs_string = scanner.nextLine();

            //Validates the integer entered by the user
            try 
            {
                election_runs = Integer.parseInt(election_runs_string);
                if (election_runs<0)
                {
                    System.out.println("Please enter a valid value.");
                }
                else
                {
                    valid = true;
                }
            }
            catch (Exception e)
            {
                System.out.println("Value was not recognised. Please try again.");
            }
        }
        return election_runs;
    }


    public static Candidate[] run_election(Candidate[] all_candidates, Candidate[] running_candidates)
    {
        int election_runs = amountofruns();
        Candidate counter = election_counter(all_candidates);
        Candidate[] election_winners = new Candidate[election_runs];
        Candidate[] votes = new Candidate[all_candidates.length];
        
        for (int i=0;i<all_candidates.length;i++) 
        {
            try
            {
                votes[i] = all_candidates[i].vote(running_candidates);
            }
            catch (Exception e)
            {
                votes[i]=null;
            }
            
        }
        
        for (int j=0;j<election_runs;j++) 
        {
            
            election_winners[j] = counter.selectWinner(votes);
        }
        
        return election_winners;
    }

    public static Candidate election_counter(Candidate[] all_candidates)
    {
        Scanner scanner = new Scanner(System.in);
        boolean valid = false;
        Candidate counter = new Candidate_ec22579();;
        
        while (!valid) 
        {
            System.out.println("Who should count the votes?");
            String votes_counter = scanner.nextLine(); 
            counter = A3.getByUsername(votes_counter, all_candidates);
            
            if (counter == null)
            {
                System.out.println("Error: User not found");
            }
            else
            {
                valid = true;
            }
        }
        
        return counter;
    }

    public static void final_winner(Candidate[] all_winners)
    {
        Candidate winner = all_winners[0];
        int highest_count = 0;
        
        for (int i=0;i<all_winners.length;i++)
        {
            int count = 1;
            
            for (int j=0;j<all_winners.length;j++)
            {
                if (all_winners[i].getName().equals(all_winners[j].getName()))
                {
                    count = count + 1;
                }
            }
            
            if (count > highest_count)
            {
                winner = all_winners[i];
                highest_count = count;
            }
        }
        
        System.out.println("Most common winner is: "+ winner.getName());
        System.out.println("Slogan: "+ winner.getSlogan());
        
        return;
    }


    public static void print_candidates(Candidate[] running_candidates, int array_nextspace)
    {
        System.out.println("Candidates are:");
        for (int i=0;i<array_nextspace;i++)
        {
            System.out.println((i+1)+ ". "+running_candidates[i].getName());
        }
    
    }
    


    public String getName() {
        return "Obi-wan Kenobi";
    }
    
    public String getSlogan() {
        return "May the force be with you!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22579();
        
        if (candidates.length != 0) r = candidates[0];
 
        // Add code that inspects the array candidates
        // calling the various methods of the Candidate objects
        // and finds a Candidate object to vote for.
        
        // If array is empty, return instance of friend's class.
        if (candidates.length == 0) 
            return new Candidate_ec22441();
        
        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("May the force be with you!"))
                return c;
        
        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Rochelle"))
                return c;
        
        return r;
    }
    
    public Candidate selectWinner(Candidate[] votes) {
 
        // If array is empty, return instance of friend's class.
        if (votes.length == 0) 
            return new Candidate_ec22579();

        
        
        // Default to first vote, but this will be over-written.
        Candidate currentWinner = votes[0];
        
        // Count the votes for each object in the array,
        // selecting one with the most.
        int highestCount = 0;
        for (Candidate c : votes) {
            
            int count = 0;
            for (Candidate x : votes)
                if (x==null)
                {

                }
                else if (x == c)
                    count++;
            if (count > highestCount) {
                highestCount = count;
                currentWinner = c;
            }
        }
        
        return currentWinner;

    }
    
}
