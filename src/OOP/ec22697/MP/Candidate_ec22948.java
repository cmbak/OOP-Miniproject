package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22948 extends Candidate 
{
    
    public String getName() 
    {
        return "SashaMasha";
    }
    
    public String getSlogan() 
    {
        return "Hi, kids!";
    }
    
    public Candidate vote(Candidate[] candidates) 
    {
        
        // If array is empty, return instance of friend's class.
        if (candidates.length == 0) 
            return new Candidate_ec22948();
        
        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("Hi, kids!"))
                return c;
        
        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Egg"))
                return c;
        
        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) 
    {
        
        // If array is empty, return instance of friend's class.
        if (votes.length == 0) 
            return new Candidate_ec22948();
        
        // Default to first vote, but this will be over-written.
        Candidate currentWinner = votes[0];
        
        // Count the votes for each object in the array,
        // selecting one with the most.
        int highestCount = 0;
        for (Candidate c : votes) 
        {
            
            int count = 0;
            for (Candidate x : votes)
                if (x == c)
                    count++;
            if (count > highestCount) 
            {
                highestCount = count;
                currentWinner = c;
            }
        }
    return currentWinner;
        
    }
    


    public static void main(String[] args)
    {
        Candidate[] allCandidates = A3.getCandidateArray();
        int index = 0;
        Candidate[] list = new Candidate[allCandidates.length];
        boolean flag= true;

        while (flag)
        {
            displayCandidates(list, index);
            String choice = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election d) exit");

            if (choice.equals("a"))
            {
                String name = inputString("Please enter a username");
                Candidate specificCandidate = A3.getByUsername(name, allCandidates);
                if (specificCandidate != null)
                {
                    list[index] = specificCandidate;
                    index ++;
                }
                else
                {
                    System.out.println("Not found. Try again.");
                }
            }

            else if (choice.equals("b"))
            {
                if (index == allCandidates.length)
                {
                    System.out.println("Candidates are full.");
                }
                Random randInt = new Random();
                boolean found = false;
                while (!found)
                {
                    found = false;
                    int randomInt = randInt.nextInt(allCandidates.length);
                    Candidate candidate = allCandidates[randomInt];

                    for (int i = 0; i < index; i++)
                    {
                        if (list[i] == candidate)
                        {
                            found = true;
                        }
                    }
                    if (found == false)
                    {
                        list[index] = candidate;
                        index++;
                    }
                }
            }
            else if (choice.equals("c")){
                String name = inputString("Who should count the votes?");
                Candidate voteCount = A3.getByUsername(name, allCandidates);
                Candidate[] finalList = new Candidate[index];
                for (int i = 0 ; i < index; i ++)
                {
                    finalList[i] = list[i];
                }

                int amount = inputInt("How many times shall we run the election?");

                Candidate[] votes = new Candidate[amount*allCandidates.length];
                System.out.println(amount*allCandidates.length);
                System.out.println(votes.length);

                for (int electionNo = 0; electionNo<amount ; electionNo++){

                    for (int i = 0; i< allCandidates.length; i++ ){
                        try
                        {
                            votes[(electionNo*allCandidates.length) + i] = allCandidates[i].vote(finalList);
                        }
                        catch(Exception e)
                        {
                            votes[(electionNo*allCandidates.length) + i] = new Candidate_ec22948();

                        }
                    }
                }
                Candidate winner = voteCount.selectWinner(votes);
                System.out.println("Most common winner is: " + winner.getName());

                index = 0;
                list = null;
            }
            else if(choice.equals("d")){
                flag = false;
            }
        }

    }

    public static void displayCandidates(Candidate[] list, int length){
        System.out.println("Candidates are");
        if (length !=0){
            for (int i =0; i < length; i++){
                System.out.println(list[i].getName()+ ": " + list[i].getSlogan());
            }
        }
        else{
            System.out.println("None");
        }
        return;
    }
    public static String inputString(String message){
        Scanner scanner = new Scanner(System.in);
        String answer;

        System.out.println(message);
        answer = scanner.nextLine();

        return answer;
    }

    public static int inputInt(String message){
        boolean valid = true;
        String ans = "";

        while (valid){
            ans = inputString(message);
            try {
                Integer.parseInt(ans);
                valid = false;
            } catch(NumberFormatException e) {
                System.out.println("Invalid integer input. Please enter an integer.");
            }
        }

        return Integer.parseInt(ans);
    }  
}
