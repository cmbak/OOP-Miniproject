package OOP.ec22697.MP;

import java.util.ArrayList;
import java.util.Scanner;

class Candidate_ec22944 extends Candidate
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Candidate[] allContributions = A3.getCandidateArray();
        ArrayList<Candidate> ca = new ArrayList<Candidate>();
        boolean loop = true;
        
        while(loop == true)
        {
            System.out.println("Would you like to a) add a specific candidate b) run the election?");
            char userChoice = scanner.next().charAt(0);


            if(userChoice == 'a')
            {
                ca = addCandidate(ca, allContributions);
                printElectionCandidates(ca);
            }

            if(userChoice == 'b')
            {
                System.out.println("Who should count the votes?");
                String voteCounter = scanner.next() + scanner.nextLine();
                System.out.println("How many times shall we run the election?");
                int numberOfElections = scanner.nextInt();

                Candidate[] winners = new Candidate[numberOfElections];

                for(int i=0; i<numberOfElections; i++)
                {
                    winners[i] = runElection(allContributions, ca, voteCounter);
                }

                Candidate electionWinner = tallyWinners(ca.toArray(new Candidate[0]));
                printWinner(electionWinner);

                loop = false;
            }
    
        }

        System.exit(0);
    
    }


    public static Candidate tallyWinners(Candidate[] winners)
    {
        Candidate currentWinner = winners[0];        
        int highestCount = 0;
        int count = 0;
        for (Candidate c : winners)
        {
            for (Candidate x : winners)
            {
                if (x == c)
                    count++;
            }
            
            if (count > highestCount)
            {
                highestCount = count;
                currentWinner = c;
            }
        }
        
        return currentWinner;

    }

    public static void printWinner(Candidate finalWinner)
    {
        System.out.println("Most common winner is " + finalWinner.getName());

        return;
    }

    public static Candidate runElection(Candidate[] allContributions, ArrayList<Candidate> ca, String voteCounter)
    {
        Candidate[] votes = new Candidate[allContributions.length];

        for(int i = 0; i<allContributions.length; i++)
        {
            try
            {
                allContributions[i].vote(ca.toArray(new Candidate[0]));
            } catch (Exception e)
            {

            }
            
        }

        Candidate pollCounter = A3.getByUsername(voteCounter, allContributions);
        return pollCounter.selectWinner(votes);

    }
    
    public static ArrayList<Candidate> addCandidate(ArrayList<Candidate> ca, Candidate[] allContributions)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a username.");
        String username = scanner.nextLine();
        ca.add(A3.getByUsername(username, allContributions));
        return ca;
    }

    public static void printElectionCandidates(ArrayList<Candidate> ca)
    {
        System.out.println("Candidates are:");

        for(int i = 0; i<ca.size(); i++)
        {
            System.out.println((i+1) + " " + ca.get(i).getName());
        }

        return;
    }


    public String getName()
    {
        return "T Man";
    }

    public String getSlogan()
    {
        return "Another day, another W";
    }

    public Candidate vote(Candidate[] candidates)
    {
        if(candidates.length == 0)
        {
            return new Candidate_ec22479();
        }

        for(Candidate c : candidates)
        {
            if(c.getName().equals("King Julian") && c.getSlogan().equals("I like to move it move it!"))
            {
                return c;
            }
        }

        return candidates[candidates.length-1];
    }

    public Candidate selectWinner(Candidate[] votes)
    {
        if(votes.length == 0)
        {
            return new Candidate_ec22479();
        }

        Candidate currentWinner = votes[0];

        int highestCount = 0;
        for (Candidate c : votes)
        {
            
            int count = 0;
            for (Candidate x : votes)
            {
                if (x == c)
                    count++;
            }
            if (count > highestCount)
            {
                highestCount = count;
                currentWinner = c;
            }
        }
        
        return currentWinner;
    }
    
}
