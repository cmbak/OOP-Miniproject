package OOP.ec22697.MP;// File Candidate_ec22720.java
//
// Machine generated stub for Assignment 2

import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

class Candidate_ec22720 extends Candidate {
    public static void main(String[] args)
    {
        Candidate_ec22720 our_candidate = new Candidate_ec22720();
        Candidate[] all_candidates = A3.getCandidateArray();
        Candidate[] voting_candidates = new Candidate[0];

        while (true)
        {
            String option = our_candidate.inputStr("Would you like to:\na) add a specific candidate\nb) add a candidate at random\nc) run the election");
            if (option.equals("a"))
            {
                String username = our_candidate.inputStr("Enter the username of the candidate you would like to add: ");
                Candidate new_candidate = A3.getByUsername(username, all_candidates);
                if (new_candidate == null)
                {
                    System.out.println("Invalid username");
                }
                else
                {
                    voting_candidates = Arrays.copyOf(voting_candidates, voting_candidates.length + 1);
                    voting_candidates[voting_candidates.length - 1] = new_candidate;
                }
            }
            else if (option.equals("b"))
            {
                int number_to_add = our_candidate.inputInt("Enter the number of candidates you would like to add (> 0): ");
                if (number_to_add < 1)
                {
                    System.out.println("Invalid number");
                }
                else
                {
                    voting_candidates = Arrays.copyOf(voting_candidates, voting_candidates.length + number_to_add);
                    Random rand = new Random();
                    for (int i = voting_candidates.length - number_to_add; i < voting_candidates.length; i++)
                    {
                        voting_candidates[i] = all_candidates[rand.nextInt(all_candidates.length)];
                    }
                }
            }
            else if (option.equals("c"))
            {
                String username = our_candidate.inputStr("Who should run the election?");
                Candidate election_runner = A3.getByUsername(username, all_candidates);

                int election_runs = our_candidate.inputInt("How many elections should we do?");
                if (election_runs < 1)
                {
                    System.out.println("Invalid input, defaulting to one run.");
                    election_runs = 1;
                }

                if (election_runner == null)
                {
                    System.out.println("Invalid username, defaulting to ec22720");
                    election_runner = our_candidate;
                }

                Candidate[] winners = new Candidate[election_runs];
                Random rand = new Random();

                for (int i = 0; i < election_runs; i++)
                {
                    Candidate[] votes = new Candidate[all_candidates.length];
                    for (int j = 0; j < votes.length; j++)
                    {
                        try 
                        {
                            votes[j] = all_candidates[i].vote(voting_candidates);
                        }
                        catch (Exception e)
                        {
                            System.out.println("Error: " + e.getMessage());
                            System.out.println("Defaulting to random");
                            votes[j] = voting_candidates[rand.nextInt(voting_candidates.length)];
                        }
                        
                    }

                    Candidate winner = election_runner.selectWinner(votes);
                    winners[i] = winner;
                    System.out.println("The winner is " + winner.getName() + " with " + winner.getSlogan());
                }

                Candidate overall_winner = our_candidate.oneMostFrequent(winners);
                System.out.println("The most common winner is " + overall_winner.getName() + " with " + overall_winner.getSlogan());

                if (our_candidate.inputStr("Would you like to:\na) exit\nb) go back to main menu").equals("a"))
                {
                    break;
                }
            }
            else
            {
                System.out.println("Invalid option");
            }
        }
    }

    public String inputStr(String prompt)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int inputInt(String prompt)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        
        if (!scanner.hasNextInt())
        {
            System.out.println("Invalid input");
            return 0;
        }

        return scanner.nextInt();
    }

    public String getName()
    {
        return "Mr. Write In";
    }
    
    public String getSlogan()
    {
        return "No longer writing!";
    }
    
    public Candidate vote(Candidate[] candidates)
    {
        Candidate r = new Candidate_ec22720();
        
        // Vote for the first occurance of Mr. Write In
        for (int i = 0; i < candidates.length; i++)
        {
            if (candidates[i].getName().equals("Mr. Write In"))
            {
                return candidates[i];
            }
        }
        
        return r;
    }

    public static <T> int numberOfTimes(T x, T[] a)
    {
        int r = 0;

        for (int i = 0; i < a.length; i++)
        {
            if (a[i] == x)
            {
                r++;
            }
        }

        return r;
    }

    public <T> T oneMostFrequent(T[] a)
    {
        T r = a[0];
        int rCount = numberOfTimes(r, a);

        // Find the item with the most occurrences using `numberOfTimes`
        for (int i = 0; i < a.length; i++)
        {
            int currentCount = numberOfTimes(a[i], a);
            if (currentCount > rCount)
            {
                r = a[i];
                rCount = currentCount;
            }
        }

        return r;
    }
    
    public Candidate selectWinner(Candidate[] votes)
    {
        Candidate r = new Candidate_ec22720();
        
        if (votes.length != 0) r = oneMostFrequent(votes);
        
        return r;
    }
    
}
