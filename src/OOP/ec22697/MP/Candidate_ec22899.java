package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22899 extends Candidate
{

    public static void main(String[] args)
    {

        int counter = 0;

        Candidate[] allCandidates = A3.getCandidateArray();

        int ARRAY_SIZE = allCandidates.length;
        Candidate[] votes = new Candidate[ARRAY_SIZE];

        System.out.println("Would you like to a) add a specific candidate b) add a random candidate c) run the election?");

        Scanner scanner = new Scanner(System.in);
        String y = scanner.nextLine();

        while (!y.equals("c"))
        {
            if (y.equals("a"))
            {
                counter++;
                addSpecificCandidate(scanner,votes,allCandidates);
            }
            else if (y.equals("b")){
                int rand = new Random().nextInt(allCandidates.length);
                votes[counter] = allCandidates[rand];
                counter++;
            }
            else
            {
                System.out.println("Enter correct value");
            }

            System.out.println("Would you like to a) add a specific candidate b) add a random candidate c) run the election?");

            scanner = new Scanner(System.in);
            y = scanner.nextLine();



        }

        runElection(scanner, votes,allCandidates,counter);






    }



    public static void runElection(Scanner scanner, Candidate[] votes, Candidate[] allCandidates, int counter)
    {
        boolean quit = false;
        Candidate voteCounter = null;

        while (!quit)
        {

            System.out.println("Who should count the votes?");
            String username = scanner.nextLine();

            voteCounter = A3.getByUsername(username, allCandidates);

            if (voteCounter == null)
            {
                System.out.println("user not found");
            }
            else
            {
                quit = true;
            }
            
        }

        System.out.println("How many times shall we run the election?");
        int NumOfRuns = scanner.nextInt();


        // inspired by ec22763


        Candidate[] winner = new Candidate[NumOfRuns];
        System.out.println(winner.length);

        Candidate unOfficialWinner;
        for (int i = 0;i<NumOfRuns;i++)
        {
            unOfficialWinner = voteCounter.selectWinner(votes);
            winner[i] = unOfficialWinner;
        }
        System.out.println(winner.length);




        Candidate actualWinner = voteCounter.selectWinner(winner);
        System.out.println("Most common winner is: " + actualWinner.getName());


    }

    public static void addSpecificCandidate(Scanner scanner, Candidate[] votes, Candidate[] allCandidates)
    {
        boolean quit = false;

        while (!quit)
        {
            System.out.println("Please enter a username.");
            String username = scanner.nextLine();
    
            Candidate votee = A3.getByUsername(username, allCandidates);
    
            if (votee == null)
            {
                System.out.println("user not found");
            }
            else
            {
                quit = true;
                addToArray(votes,votee);
            }
    
            
    
        }
        
    }

    public static void addToArray(Candidate[] array, Candidate item) // adds an item to the array
    {
        for (int i = 0; i <= 1000; i++)
        {
            if (array[i] == null)
            {
                array[i] = item;
                break;
            }
        }    
    }

    
    public String getName() {
        return "Eren";
    }
    
    public String getSlogan() {
        return "Tatakae";
    }
    
    public Candidate vote(Candidate[] candidates)
    {        
        // if array is empty return object of my class
        if (candidates.length == 0)
        {
           return new Candidate_ec22899();
        }

        // search for an identical candidate
        for (Candidate c : candidates)
        {
            if (c.getName().equals("Eren") && c.getSlogan().equals("Tatakae"))
            {
                return c;
            }
        }
        
        // returns first item if identical item doesn't exist
        return candidates[0];
    }
    
    public Candidate selectWinner(Candidate[] votes)
    {

        // returns instnace of my class if array is empty
        if (votes.length == 0)
            return new Candidate_ec22899();




        Candidate currentWinner = votes[0];


        int rand = new Random().nextInt(votes.length);
        if (votes[rand] != null)
        {
            currentWinner = votes[rand];
        }



        return currentWinner;
        
    }
    
}
