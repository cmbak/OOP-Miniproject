package OOP.ec22697.MP;// File Candidate_ec22475.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;

public class Candidate_ec22475 extends Candidate {

    public String getName() {
        return "Uto";
    }

    public String getSlogan() {
        return "Road of Resistance";
    }

    public Candidate vote(Candidate[] candidates) {

        for (Candidate c : candidates)
            if (c.getName().equals("Uto"))
                return c;

        Random r = new Random();
        int c = r.nextInt(candidates.length);
        return candidates[c];
    }

    public Candidate selectWinner(Candidate[] votes) {

        int c = 0;

        if (votes.length == 0)
        {
            return new Candidate_ec22475();
        }
        else
        {
            return votes[c];
        }
    }

    public static String inputString (String message)
    {
        String answer;
        Scanner scanner = new Scanner (System.in);

        System.out.println(message);
        answer = scanner.nextLine();

        return answer;
    }

    public static int inputInt (String message)
    {
        String answer = inputString(message);
        return Integer.parseInt(answer);
    }

    //A3 main
    public static void main (String[]args)
    {
        try
        {
            menu();
        }
        catch(Exception e)
        {
            Candidate w = new Candidate_ec22475();
            System.out.println("The Winner is: " + w.getName());
        }
    }

    public static void menu(){

        String StartingQuestion;
        String UserName;
        String VoteCounter;

        int VoteTimes;
        int counter = 0;
        int Common;

        boolean end = false;

        Candidate FinalWinner;
        Candidate[] allCandidates = A3.getCandidateArray();
        Candidate[] ElectedCandidates = new Candidate[allCandidates.length];

        System.out.println("= Running Repeated Elections =");
        System.out.println("Currently there are no Candidates...");

        //Start Looping the election
        while (end != true)
        {
            StartingQuestion = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election? d) exit");

            //Option A, adding a specific Candidate into the pool
            if (StartingQuestion.equals("a"))
            {
                //Asking user to input username then getting the Candidates's name
                UserName = inputString("Please enter a username");
                Candidate user = A3.getByUsername(UserName, allCandidates);

                //Putting the Candidate's name into the ElectedCandidate List
                ElectedCandidates[counter] = user;
                counter++;

                //Printing out all the Candidates Elected Currently
                if (counter != 0)
                {
                    System.out.println("Candidates are:");
                    for(int i = 0; i < counter; i++)
                    {
                        System.out.println((i+1) + ". " + ElectedCandidates[i].getName());
                    }
                }
            }

            //Option B, adding a random Candidate into the pool
            else if (StartingQuestion.equals("b"))
            {
                //Make Random
                Random ran = new Random();
                int RandomCandidate = ran.nextInt(allCandidates.length);

                //Adding new Random Candidates into the ElectedCandidate List
                ElectedCandidates[counter] = allCandidates[RandomCandidate];
                counter++;

                //Printing all the Candidates
                System.out.println("Candidates are:");
                for(int i = 0; i < counter; i ++)
                {
                    System.out.println((i+1) + ". " + ElectedCandidates[i].getName());
                }
            }

            //Option C, Running the election
            else if (StartingQuestion.equals("c"))
            {
                VoteCounter = inputString("Who should count the votes?");
                Candidate VCounter = A3.getByUsername(VoteCounter, allCandidates);

                VoteTimes = inputInt("How many times shall we run the election?");

                //Create a List of winners, then select the one that repeat the most as common winner
                Candidate[] Winners = new Candidate[VoteTimes];

                //Loop selectWinner for how many times the user input
                for(int i = 0; i < VoteTimes; i++)
                {
                    Winners[i] = VCounter.selectWinner(ElectedCandidates);
                }

                FinalWinner = VCounter.selectWinner(Winners);
                System.out.println("The Most Common Winner is: " + FinalWinner.getName());

                counter = 0;
                end = true;
            }
            else if (StartingQuestion.equals("d"))
            {
                end = true;
            }
        }
    }
}
