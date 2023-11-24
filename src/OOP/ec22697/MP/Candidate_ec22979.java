package OOP.ec22697.MP;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;



class Candidate_ec22979 extends Candidate {

    public String un = "ec22979";


    public String getName() {
        return "Dwight";
    }

    public String getSlogan() {
        return "Whenever I'm about to do something, I think, 'Would an idiot do that? ' And if they would, I do not do that thing.!";
    }

    public Candidate vote(Candidate[] candidates) {


        if (candidates.length == 0)
            return new Candidate_ec22979();

        // Search for a like-minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("Whenever I'm about to do something, I think, 'Would an idiot do that? ' And if they would, I do not do that thing."))
                return c;

        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Joe"))
                return c;

        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }

    public Candidate selectWinner(Candidate[] votes) {

        // If array is empty, return instance of friend's class.
        if (votes.length == 0)
        {return new Candidate_ec22979();}

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

    //main method

    public static void main(String[] a)
    {
        //specific candidate methods
        Candidate[] allContributions = A3.getCandidateArray(); //Full array of student candidates
        String specificCandidateUN; //specific candidate username
        String randomCandidateUN;


        ArrayList<String> candidateEntries = new ArrayList<>(); //ArrayList for candidate entries
        Random random = new Random(allContributions.length - 1);



        //candidate entry methods
        String choice = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");

        while(!(choice.equals("c")))
        {
            if(choice.equals("a"))
            {
                specificCandidateUN = inputString("What is the username of chosen candidate?");


                if (specificCandidateUN != null)
                {
                    candidateEntries.add(specificCandidateUN);
                }
                else pr("User not found.");
            }

            else
            {
                randomCandidateUN = allContributions[random.nextInt(allContributions.length)].un;
                candidateEntries.add(randomCandidateUN);

            }
            printCandidates(candidateEntries, allContributions);
            choice = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
        }

        //"run election" methods

        String specificAdjudicator = inputString("Enter the username of the chosen counter");
        A3.getByUsername(specificAdjudicator, allContributions); //get specific candidate adjudicator object
        int voteRepeats = inputInt("How many times shall we run the election?");
        Candidate[] votes = new Candidate[voteRepeats]; //array for student votes
        int[] candidateVotes = new int[candidateEntries.size()]; //int array for votes matches - create after ArrayList complete

        //vote counting methods

        for(int i = 0; i < voteRepeats; i++) //counting vote winners - fills the candidateVotes array
        {
            votes[i] = A3.getByUsername(specificAdjudicator, allContributions).selectWinner(allContributions);

            for(int j = 0; j < candidateEntries.size(); j++)
            {
                if(candidateEntries.get(j).equals(votes[i])){candidateVotes[j] = candidateVotes[j] + 1;}
            }
        }



        int highestCount = 0;
        String winnerUN = null;
        for(int k = 0; k < candidateVotes.length; k++) //count winner after repeats from candidateVotes array
        {
            if(candidateVotes[k] > highestCount)
            {

                winnerUN = candidateEntries.get(k);}
        }


        pr("The winner of this election is " + A3.getByUsername(winnerUN, allContributions).selectWinner(allContributions).getName() + "!");



    }



    public static String inputString(String message)
    {
        Scanner scanner = new Scanner(System.in);
        pr(message);
        String a = scanner.nextLine();
        return a;
    }

    public static int inputInt(String message)
    {
        Scanner scanner = new Scanner(System.in);
        pr(message);
        int a = scanner.nextInt();
        return a;
    }


    public static void printCandidates(ArrayList<String> candidateEntries, Candidate[] allContributions)
    {

        pr("Candidates are: ");
        for(int i = 0; i < candidateEntries.size(); i++)
        {
            pr(i+1 + ". " + A3.getByUsername(candidateEntries.get(i), allContributions).getName());
        }
        return;
    }



    public static void pr(String message)
    {
        System.out.println(message);
        return;
    }
}






