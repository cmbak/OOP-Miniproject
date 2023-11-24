package OOP.ec22697.MP;// File Candidate_ec22497.java
//
// Machine generated stub for Assignment 2

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

class Candidate_ec22497 extends Candidate
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Candidate> candidates = new ArrayList<Candidate>();
        boolean check = true;


        while(check)
        {
            Candidate[] allContributions = A3.getCandidateArray();

            System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
            
            String response = scanner.nextLine();

            if(response.equals("a"))
            {
                System.out.println("Please enter a username.");

                String username = scanner.nextLine();

                Candidate x = A3.getByUsername(username, allContributions);

                candidates.add(x);

                System.out.println("Candidates are");
                for(int i = 0; i < candidates.size(); i++)
                {
                    System.out.println((i+1) + "." + candidates.get(i).getName());
                }
            }
            else if(response.equals("b"))
            {
                int bound = 546;
                Random r = new Random();
                candidates.add(allContributions[r.nextInt(bound)]);

                System.out.println("Candidates are");
                for(int i = 0; i < candidates.size(); i++)
                {
                    System.out.println((i+1) + "." + candidates.get(i).getName());
                }
            }
            else if(response.equals("c"))
            {
                boolean checker = true;
                while(checker)
                {
                    System.out.println("Who should count the votes?");

                    String judge = scanner.nextLine();

                    Candidate counter = A3.getByUsername(judge, allContributions);

                    System.out.println("How many times shall we run the election?");

                    int runElection = Integer.parseInt(scanner.nextLine());

                    Candidate[] candidateArray = candidates.toArray(new Candidate[0]);

                    for(int i = 0; i < runElection; i++)
                    {
                        counter.vote(candidateArray);
                    }

                    System.out.println("Most common winner is " + counter.selectWinner(candidateArray).un + ".");

                    System.out.println();
                    System.out.println("Would you like to ");
                    System.out.println("a) exit ");
                    System.out.println("b) Run the election again");

                    String choice = scanner.nextLine();

                    if(choice.equals("a"))
                    {
                        checker = false;
                    }
                    else if(choice.equals("b"))
                    {
                        checker = true;
                    }
                    else
                    {
                        System.out.println("Invalid Input. END.");
                        System.exit(0);
                    }
                }
            }
            else
            {
                System.out.println("Invalid Input");
            }
        }
    }

    public String getName ()
    {
        return "Faris";
    }
    public String getSlogan ()
    {
        return "Return the monarchy!";
    }
    public Candidate vote(Candidate[] candidates)
    {
        if (candidates.length == 0) 
        {
            return new Candidate_ec22493();
        }

        for (int i = 0; i < candidates.length; i++)
        {
            if (candidates[i].getSlogan().equals("Return the monarchy!"))
            {
                return candidates[i];
            }
        }
        
        for (int p = 0; p < candidates.length; p++)
        {
            if(candidates[p].getName().equals("Muhammed"))
            {
                return candidates[p];
            }
        }

        return candidates[candidates.length - 1];

    }
    public Candidate selectWinner(Candidate[] votes)
    {
        if (votes.length == 0)
        {
            return new Candidate_ec22495();
        }

        Candidate currentWinner = votes[0];

        int highestCount = 0;
        
        for (int i = 0; i < votes.length; i++) 
        {
            int count = 0;
            for (int k = 0; k < votes.length; k++)
            {
                if(i == k)
                {

                }
                else if(votes[i] == votes[k])
                {
                    count = count + 1;
                }
            }
            
            if(count > highestCount)
            {
                highestCount = count;
                currentWinner = votes[i];
            }
        }

        return currentWinner;

    }

}

