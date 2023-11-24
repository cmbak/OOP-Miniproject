package OOP.ec22697.MP;// File Candidate_ec22890.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;

public class Candidate_ec22890 extends Candidate {
    
    public static void main(String[] args)
    {
        boolean finished = false;
        Candidate[] all_Candidates = A3.getCandidateArray();
        Candidate[] all_votes = new Candidate[1];

        while(!finished)
        {
            String user_input = store_string("Would you like to a) add a specific candidate b) add a candidate at random c) run the election d) quit election");

            if (user_input.equals("a"))
            {
                String username = store_string("Please enter a username.");

                Candidate temp = A3.getByUsername(username, all_Candidates);
    
                if (temp != null)
                {
                    all_votes = add_candidate(all_votes, temp.vote(all_Candidates));
                }
                
                else
                {
                    System.out.println("please enter a valid username next time");
                }
            }
            else if (user_input.equals("b"))
            {
                Random random = new Random();

                int num_candidates = store_integer("How many random candidates do you want to add?");

                for(int i = 0; i < num_candidates; i++)
                { 
                    all_votes = add_candidate(all_votes, all_Candidates[random.nextInt(all_Candidates.length)].vote(all_Candidates));
                }
            }
            else if (user_input.equals("c"))
            {
                String username = store_string("Who do you want to run the election?");
                Candidate main_election_runner = A3.getByUsername(username, all_Candidates);

                int num_times = store_integer("How many times shall we run the election?");

                Candidate[] winners = new Candidate[num_times];
                    
                for(int i = 0; i < num_times; i++)
                {
                    winners[i] = main_election_runner.selectWinner(all_votes);
                }
                

                Candidate most_common_winner = most_common(winners);

                System.out.println("Most common winner is " + most_common_winner.getName());
                System.out.println("There were no other winners");
            }

            else if (user_input.equals("d"))
            {
                finished = true;
            }
        }
    }




    public static Candidate most_common(Candidate[] winners)
    {
        int current_highest = 0;
        int index = 0;

        for(int i = 0; i<winners.length; i++)
        {
            int count = 0;

            for(int j = 0; j<winners.length;j++)
            {
                if ((winners[i] == winners[j]))
                {
                    count++;
                }
            }
            
            if ((count > current_highest) && (index != i))
            {
                index = i;
            }

        }

        return (winners[index]);
    }

    public static int store_integer(String message){        
        Scanner scanner = new Scanner(System.in);
        int number = 0;

        System.out.println(message);

        if (scanner.hasNextInt())
        {
            number = Integer.parseInt(scanner.nextLine());

            if (number>0)
            {
                return number;
            }
            number = store_integer(message);
        }

        else
        {
            number = store_integer("please enter a valid number");
        }
    
        return number;
    }

    public static String store_string(String message)
    {
        Scanner scanner = new Scanner(System.in);

        String string;
            
        System.out.println(message);  

        string = scanner.nextLine();

        return string;
    }

    public static Candidate[] add_candidate(Candidate[] x, Candidate y){
        Candidate[] new_array = new Candidate[x.length + 1];
        for (int i = 0; i < x.length; i++){
            new_array[i] = x[i];
        }
        new_array[x.length] = y;

        return new_array;
    }

    public String getName() {
        return "Mathew";
    }
    
    public String getSlogan() {
        return "Enjoy life!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        if (candidates.length == 0) 
            return new Candidate_ec22890();
        
        for (Candidate c : candidates)
            if (c.getSlogan().equals("Enjoy life!"))
                return c;
        
        for (Candidate c : candidates)
            if (c.getName().equals("kim"))
                return c;
        
        return candidates[candidates.length-1];
        
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
       if (votes.length == 0) 
        {
            return new Candidate_ec22890();
        }

        int most_votes = 0;
        Candidate winning_candinate = votes[0]; 
    
        for(int i = 0; i < votes.length; i++)
        {
            int total_votes = 0;

            for(int j = 0; j < votes.length; j++)
            {
                if((votes[i] == votes[j]))
                {
                    total_votes++;
                }
            }

            if(total_votes >= most_votes)
            {
                winning_candinate = votes[i];
                most_votes = total_votes;
            } 
        }

        return winning_candinate;
    }
    

    
}
