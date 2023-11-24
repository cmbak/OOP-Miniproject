package OOP.ec22697.MP;

import java.util.*;


class Candidate_ec22949 extends Candidate {

    public String getName() {
        return "Yassine Serrhini";
    }

    public String getSlogan() {
        return "Print more money";
    }


    public Candidate vote(Candidate[] candidates) {

        // if array is empty return instance of my own class
        if (candidates.length == 0)
        {
            return new Candidate_ec22949();
        }

        // search for a like minded candidate
        for (Candidate c : candidates)
        {
            if (c.getSlogan().equals("Print more money"))
            {
                return c;
            }
        }

        // search for myself otherwise
        for (Candidate c : candidates)
        {
            if (c.getName().equals("Yassine Serrhini"))
            {
                return c;
            }
        }

        // otherwise return last candidate on list
        return candidates[candidates.length-1];

    }


    public Candidate selectWinner(Candidate[] votes) {

        // if array is empty return instance of myself.
        if (votes.length == 0)
            return new Candidate_ec22949();

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



    /***
     This method validates integer inputs
     ***/
    public static int inputInt(String message)
    {
        Scanner s = new Scanner(System.in);
        System.out.println(message);

        final int IntegerCondition = 0; // no negatives
        final int IntegeterCondition2 = 1000; // arbitrary bound for maximum value

        // using final variables to store constants as bounds for inputs

        int number;

        do
        {
            while (!s.hasNextInt())
            {
                System.out.println("Invalid input");
                s.next();
            }
            number = s.nextInt();

        } while  (!((number >= IntegerCondition ) && (number <= IntegeterCondition2)));
        // continues to iterate until conditions are met

        return number;
    }



    /*****
     This method takes a string as an argument, which gets printed
     and will take an input and return it as a string
     *****/
    public static String inputString(String message)
    {
        String answer;
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        answer = scanner.nextLine();
        return answer;
    }





    public static void main(String[] args)
    {
        final Candidate[] Candidates = A3.getCandidateArray();
        Candidate[] votes = new Candidate[Candidates.length];


        int numberOfCandidates = inputInt("How many candidates are there? ");


        Candidate[] candidatesUser = new Candidate[numberOfCandidates];

        for (int i = 0; i < numberOfCandidates; i++) {
            String candidateUsername = inputString("Enter Candidate Username: ");

            Candidate person = A3.getByUsername(candidateUsername, Candidates);

            if (person == null) {
                System.out.println("Not found. Try Again.");

                i--;

            } else {
                candidatesUser[i] = person;
            }


        }

        System.out.println("List Of Your Candidates: ");

        for (int i = 0; i < candidatesUser.length; i++) {
            System.out.println((i+1) + "-------" + candidatesUser[i].getName());
        }


        Candidate check = new Candidate_ec22949();

        String usernameCounter;

        boolean loopBreak = false;

        while (!loopBreak) {
            usernameCounter = inputString("Which Username would you like to count the votes?");


            check = A3.getByUsername(usernameCounter, candidatesUser);

            if (check == null) {
                System.out.println("Not Found.");

            } else {
                loopBreak = true;

            }


        }
        boolean runs = false;

        int runtimes = 0;

        while (!runs) {
            runtimes = inputInt("How many times would you like to run the election? ");

            if (runtimes > 0) {
                runs = true;
            } else {
                System.out.println("Incorrect input, try again.");
            }

        }

        Candidate[] Winners = new Candidate[runtimes];

        for (int i = 0; i < runtimes; i++) {
            for (int j = 0; j < Candidates.length; j++) {
                try {
                    votes[j] = Candidates[j].vote(candidatesUser);
                } catch (Exception e) {
                    System.out.println("Error with candidate : " + Candidates[j]);
                    j++;
                }


            }

            Winners[i] = check.selectWinner(votes);
        }


        int highestAmount = 0;


        int amount = 0;


        Candidate largestWinner = Winners[0];
        for (int i = 0; i < Winners.length; i++) {
            amount = 1;
            for (int j = 0; j < Winners.length; j++) {
                if (Winners[i] == Winners[j]) amount++;
            }


            if (amount > highestAmount) {
                highestAmount = amount;
                largestWinner = votes[i];
            }

        }
        System.out.println("The winner is  " + largestWinner.getName()+ " !");


    }


}
