package OOP.ec22697.MP;// File Candidate_ec22450.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;
class Candidate_ec22450 extends Candidate
{
    String name;
    String slogan;
    public static void main (String[] args)
    {
       Scanner newScanner=new Scanner(System.in);
       Candidate[] allCandidates= A3.getCandidateArray();
       Candidate[] candidatesToVoteFor=new Candidate[546];
       int numberOfCandidatesToVoteFor=0;
       String userOption=" ";
       String username="";
       Candidate newCandidate=new Candidate_ec22450();
       while(userOption.equals("d"))
       {    
           if(candidatesToVoteFor.length==0)
           {
               System.out.println("There are currently no candidates to vote for. Please add a candidate.");
           }
           else
           {
               System.out.println("The candidates are:");
               for(int i=0;i<numberOfCandidatesToVoteFor;i++)
               {
                 System.out.println((i+1)+".) "+candidatesToVoteFor[i].getName());
               }
           }
           //Adapted from example
           System.out.println("Would you like to: (a)add another candidate, (b)add a random candidate, (c) run an election, or exit(d)?");
           userOption=newScanner.nextLine();
           if(userOption.toLowerCase().equals("a"))
           {
               System.out.println("Please enter a username.");
               username=newScanner.nextLine();
               newCandidate= A3.getByUsername(username,allCandidates);
               candidatesToVoteFor[numberOfCandidatesToVoteFor]=newCandidate;
               numberOfCandidatesToVoteFor++;
               System.out.println("The candidates are:");
               for(int i=0;i<numberOfCandidatesToVoteFor;i++)
               {
                 System.out.println((i+1)+".) "+candidatesToVoteFor[i].getName());
               }
           }
           else if(userOption.toLowerCase().equals("b"))
           {
               Random randomNumberStream=new Random();
               int randomNumber=randomNumberStream.nextInt(546);
               newCandidate=allCandidates[randomNumber];
               candidatesToVoteFor[numberOfCandidatesToVoteFor]=newCandidate;
               numberOfCandidatesToVoteFor++;
               System.out.println("The candidates are:");
               for(int i=0;i<numberOfCandidatesToVoteFor;i++)
               {
                 System.out.println((i+1)+".) "+candidatesToVoteFor[i].getName());
               }
           }
           else if(userOption.toLowerCase().equals("c"))
           {
                   System.out.println("Please enter the username of the one counting the votes.");
                   username=newScanner.nextLine();
                   newCandidate= A3.getByUsername(username,allCandidates);
                   if(newCandidate!=null)
                   {
                        System.out.println("The most common winner is: "+newCandidate.selectWinner(candidatesToVoteFor));
                   }
           }
       }
    }
    public String getName()
    {
        name="Faizan";
        return this.name;
    }

    public String getSlogan()
    {
        slogan="Better pay for everyone!";
        return this.slogan;
    }


    public Candidate vote(Candidate[] candidates) {
        Candidate toVoteFor = new Candidate_ec22450();

        if (candidates.length != 0) 
        {
            for(int i=0;i<candidates.length;i++)
            {
                if(candidates[i].getName().contains("E"))
                {
                    toVoteFor=candidates[i];
                }
            }
            toVoteFor=new Candidate_ec22493();
        }
        else
        {
            toVoteFor=new Candidate_ec22493();
        }
        return toVoteFor;

        // Add code that inspects the array candidates
        // calling the various methods of the Candidate objects
        // and finds a Candidate object to vote for.
    }

    public Candidate selectWinner(Candidate[] votes) {
        Candidate winner = new Candidate_ec22450();
        int numberOfVotes;
        int highestVotes=0;
        Candidate temporaryWinner=new Candidate_ec22450();
        if (votes.length != 0) 
        {
            for(int i=0;i<votes.length-1;i++)
            {
                numberOfVotes=0;
                for(int j=1;j<votes.length;j++)
                {
                    if(votes[i].getName().equals(votes[j].getName()))
                    {
                        numberOfVotes++;
                    }
                    if(numberOfVotes>highestVotes)
                    {
                        temporaryWinner=votes[i];
                    }
                }
            }
            winner= temporaryWinner;
        }
        else
        {
           winner=new Candidate_ec22493();
        }



        // Add code that either counts the votes
        // or uses some other way to return an element 
        // of the array vote as the winner of an election.

        return winner;
    }
}
