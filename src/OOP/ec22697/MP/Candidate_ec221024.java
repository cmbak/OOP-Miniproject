package OOP.ec22697.MP;

import java.util.Scanner;
import java.util.Random;

public class Candidate_ec221024 extends Candidate {


    private static int integerInput(String message)
    {
        Scanner keyboard = new Scanner(System.in);// Create scanner
        int input = 0;
        boolean invalidInput = true;

        while (invalidInput) {

            System.out.println(message); // Print message

            if (keyboard.hasNextInt())
            {
                input = keyboard.nextInt();

                if (input < 0)
                { // Check if user entered a positive number aas scores can be equal to 0
                    System.out.println("ERROR! Please enter a positive value!");// Print error message
                }
                else
                {
                    invalidInput = false;
                }
            }
            else
            {
                keyboard.next();
                System.out.println("ERROR! You have not inputted an integer!");// Print error message
            }
        }
        return input;
    }

    private static String stringInput(String message){

        Scanner keyboard = new Scanner(System.in);
        System.out.println(message);
        String input = keyboard.nextLine();

        while (input == null | input.isEmpty()){  // the isEmpty to check if the string is empty

            System.out.println("ERROR! Please use one of the options shown above!");  // Error message

            input = keyboard.nextLine();
        }

        return input;
    }

    public static <T> int numberOfoccurrences(T x, T[] a) {
        int occurrences = 0;

        for(int i = 0; i < a.length; i++){

            if (x == a[i]){
                occurrences = occurrences + 1;
            }
        }
        return occurrences;
    }

    public static Candidate FinalWinner(Candidate[] candidates) {
        int maxCount = 0;
        Candidate popular = candidates[0];

        for (int i = 0; i < candidates.length; i++) {
            int count = numberOfoccurrences(candidates[i], candidates);
            if (count > maxCount) {
                maxCount = count;
                popular = candidates[i];
            }
        }
        return popular;
    }

    private static void addRandomCand(Candidate[] ElectedCandidates, Candidate[] AllCandidates, int CandidatesCount){


        int position = new Random().nextInt(AllCandidates.length);
        ElectedCandidates[CandidatesCount] = AllCandidates[position];
        System.out.println(AllCandidates[position].getName() + " has been added to the election by random.");


    }

    /*
    Doesnt check the array to find the candidate
    
    private static void addSpecificCand(String name, Candidate[] ElectedCandidates,Candidate[] AllCandidates, int CandidatesCount){

        Candidate temp = A3.getByUsername(name,AllCandidates);
        if (temp != null){

            System.out.println("Cannot find candidate");

        } else {

            ElectedCandidates[CandidatesCount] = temp;
            CandidatesCount++;
        }
    }
    */

    private static int addSpecificCand(String username, Candidate[] ElectedCandidates, Candidate[] AllCandidates, int CandidatesCount) {

        Candidate candidateToAdd = A3.getByUsername(username, AllCandidates);
        if (candidateToAdd == null) {
            //CandidatesCount = CandidatesCount - 1;
            System.out.println("Cannot find candidate " + username);
        } else {
            ElectedCandidates[CandidatesCount] = candidateToAdd;
            System.out.println(candidateToAdd.getName() + " was added to list of candidates");
            CandidatesCount++;
        }
        return CandidatesCount;
    }
      
     public static void main(String [] a){

        try {
            boolean exit = false;
            Candidate[] AllCandidates = A3.getCandidateArray();
            Candidate[] ElectedCandidates = new Candidate[AllCandidates.length];
            int CandidatesCount = 0;


            while(!exit){

                String choice = stringInput("Would you like to: a) add a specific candidate b) add a candidate at random c) run the election d) EXIT?");

                if (choice.equals("a")){

                    String username = stringInput("Please enter a username.");
                    CandidatesCount = addSpecificCand(username, ElectedCandidates, AllCandidates, CandidatesCount);
                    /*try {
                        System.out.println(ElectedCandidates[CandidatesCount - 1]);
                    }catch (NullPointerException npe){
                        System.out.println("error");
                    }*/

                }else if (choice.equals("b")){

                    addRandomCand(ElectedCandidates, AllCandidates, CandidatesCount);
                    CandidatesCount++;
                    System.out.println(ElectedCandidates[CandidatesCount - 1]);

                }else if (choice.equals("c")){

                    if(CandidatesCount>0) {
                        System.out.println("there are " + CandidatesCount + " people in the election");

                        String who = stringInput("Who should count the votes?(enter student ID 'ec12345')");
                        Candidate judge = A3.getByUsername(who, AllCandidates);
                        int ElectionRuns = integerInput("How many times shall we run the election?");
                        Candidate[] Winner = new Candidate[ElectionRuns];

                        for (int i = 0; i < ElectionRuns; i++) {
                            Candidate[] temp = new Candidate[AllCandidates.length];
                            for (int j = 0; j < AllCandidates.length; j++) {
                                try {
                                    temp[j] = AllCandidates[j].vote(ElectedCandidates);
                                } catch (Exception e) {
                                    temp[j] = new Candidate_ec221024();
                                }
                            }
                            Winner[i] = judge.selectWinner(temp);
                            System.out.println("The winner of election " + (i + 1) + " is " + Winner[i].getName() + ".");
                        }

                        Candidate popular = FinalWinner(Winner);
                        System.out.println("The Winning candidate is " + popular.getName() + ".");

                    }else{
                        System.out.println("Theres no one in the elections currently, add someone to the election first!");
                    }

                }else if (choice.equals("d")){
                    exit = true;
                    System.out.println("You have chosen to exit the election.");

                }else {

                    System.out.println("That wasn't an option!");

                }


            }
        } catch (Exception e) {
                 System.out.println("Something went wrong.");
        }
    }


    

     

    //A2 CODE IS HERE--------------------------
    
    public String getName() {
        return "Aadam";
    }
    
    public String getSlogan() {
        return "Okaaay lets gooo!";
    }

    public Candidate vote(Candidate[] candidates) {

        // If array is empty, return instance of this class.
        if (candidates.length == 0)
            return new Candidate_ec221024();

        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("Okaaay lets gooo!"))
                return c;

        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Ayo"))
                return c;



        // Otherwise, default to random candidate on list.
        Random rand = new Random();
        int c = rand.nextInt(candidates.length);
        return candidates[c];
    }


    public Candidate selectWinner(Candidate[] votes) {
        
        // If array is empty, return instance of this class.
        if (votes.length == 0) 
            return new Candidate_ec221024();
        
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
        

        
