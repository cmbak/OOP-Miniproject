package OOP.ec22697.MP;// File Candidate_ec22986.java

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;


class Candidate_ec22986 extends Candidate {
    
    
    public static void main(String [] array)
    {
        Candidate[] A3_array= A3.getCandidateArray();
        ArrayList<Candidate> final_array= new ArrayList<Candidate>();
        Scanner input=new Scanner(System.in);
        String username;
        String option;
        String option_b;
        int counter=0;
        int random;
        boolean valid=true;
        boolean input_validity=true;
        boolean checking_validity=true;
        String checking_username="";
        int election_loops;
        ArrayList <Candidate> winner_array=new ArrayList<Candidate>();
        ArrayList <Candidate>  vote_array=new ArrayList<Candidate>();
        
        
        
        boolean main_while=true;
        

        System.out.println("=Running Repeated Election=");

        print_array(final_array, counter);
        
       
        
        while(main_while)
        {
             System.out.println("Would you like to? \n a) exit \n b) run same election many times");
             option=input.nextLine();
             
             
            
            if(option.equals("a"))
            {
                main_while=false;
                return;
            }
            
            
            
            else if(option.equals("b"))
            {
                valid=true;
                while(valid)
                {
                    System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
                    option_b=input.nextLine();
                    input_validity=true;

                    if(option_b.equals("a"))
                    {
                        
                        while(input_validity)
                        {
                            System.out.println("Please enter a username. ");
                            username=input.nextLine();

                            Candidate select_candidate= A3.getByUsername(username, A3_array);

                            if(select_candidate!=null)
                            {
                                
                                final_array.add(select_candidate);
                                counter++;
                                print_array(final_array, counter);
                                input_validity=false;
                            }
                            else
                            {
                                System.out.println("try again");
                            }

                        }
                     


                    }

                    else if(option_b.equals("b"))
                    {
                        
                        random=random_number(A3_array.length-1);
                        final_array.add(A3_array[random]);
                        counter++;
                        print_array(final_array, counter);
                    }
                   

                     else if(option_b.equals("c"))
                     {
                         Candidate checking_candidate;
                         
                             System.out.println("Who should count the votes?");
                             checking_username=input.nextLine();
                                
                             while(A3.getByUsername(checking_username, A3_array)==null)
                             {
                                 System.out.println("Input a valid username");
                                 checking_username=input.nextLine();
                             }
                             
                             checking_candidate = A3.getByUsername(checking_username, A3_array);
 
                         election_loops=intInput("How many times shall we run the election?");
                         
                        for(int x=0; x<election_loops; x++)
                        {
                             for(int f=0; f<(A3_array.length); f++)
                                 {
                                     try{
                                         vote_array.add(A3_array[f].vote(final_array.toArray(new Candidate[0])));
                                     }

                                     catch(Exception a)
                                     {

                                     }
                                 }
                            try{
                              
                                Candidate winner=checking_candidate.selectWinner(vote_array.toArray(new Candidate[0]));
                            
                                winner_array.add(winner);
                            }
                            catch(Exception a)
                            {
                            }
                        }
                         
                         
                         
                        printWinner(winner_array.toArray(new Candidate[0]));
                        valid=false;
                        
                     }
                
         }      
        }
            
        
            else
            {
                System.out.println("Wrong input, please try again.");
                main_while=true;
            }
            
        }
        return;
    }
        
        private static void printWinner(Candidate[] winners)
        {
               if (winners.length == 0) 
               {
                   System.out.println("no winners");

                   return;
               }
            
            if(winners.length==1)
            {
                System.out.println("The winner is " + winners[0].getName());
                return;
            }
            
           
            Candidate most_frequent_Winner= winners[0];
            int current_frequency=0;
            int highest_frequency=0;
            int i=0;
            
            while(i<winners.length)
            {
                Candidate currentWinner=winners[i];
                
                current_frequency=0;
  
                    
                for(int z=0; z<winners.length; z++)
                {
                    if(currentWinner.getName() == most_frequent_Winner.getName())
                    {
                        if(i!=z)
                        {
                            current_frequency++;
                        }
                    }
                    
                    if(current_frequency>highest_frequency)
                    {
                        most_frequent_Winner=currentWinner;
                        highest_frequency=current_frequency;
                        
                    }
                    
                }
                

                i++;
            }

            System.out.println("The most common winner is " + most_frequent_Winner.getName());

                return;

        }
   

    private static int intInput(String message)
    {
        Scanner input=new Scanner(System.in);
        String answer;
        int answer_int;
        
        System.out.println(message);
        answer=input.nextLine();
        answer_int=Integer.parseInt(answer);
        
        return answer_int;
    }
    
    
    private static void print_array(ArrayList<Candidate> array, int index)
    {
        
        for(int i=0; i<index; i++)
        {
            
            System.out.println(array.get(i).getName());
        }

        return;
    }


    private static int random_number(int limit)
    {
        Random r=new Random();

        return r.nextInt(limit);
    }



    public String getName() {
        return "Novak";
    }
    
    public String getSlogan() {
        return "Play more Tennis!";
    }
    
    public Candidate vote(Candidate[] candidates) {
         
        // If array is empty, return instance of friend's class.
        if (candidates.length == 0) 
            return new Candidate_ec22986();
        
        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("Play more Tennis!"))
                return c;
        
        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Sakibul"))
                return c;
        
        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        // If array is empty, return instance of friend's class.
        if (votes.length == 0) 
            return new Candidate_ec22986();
        
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
