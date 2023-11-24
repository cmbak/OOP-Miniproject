package OOP.ec22697.MP;// File Candidate_ec221099.java
//
// Machine generated stub for Assignment 2

import java.util.Scanner;
import java.util.Random;

class Candidate_ec221099 extends Candidate {
    
    public String getName() {
        return "Timmy";
    }
    
    public String getSlogan() {
        return "Make Queen's Great Again!";
    }
    
    public static String inputString (String message)
    {
    // make a scanner to get input from user
        String answer;
        Scanner scanner = new Scanner(System.in);

        print(message);
        answer = scanner.nextLine();
        scanner.close();

        return answer;
    }

    public static void print (String message)
    {
    // shorter way to print
        System.out.println(message);
        
        return;

    }

    public static int inputInt (String question)
    {
        String answer = "";
        int number = 0;
        boolean input = true;
        
        Scanner scanner = new Scanner(System.in);
        print(question);
        answer = scanner.nextLine();
        
        try{
            Integer.parseInt(answer);
        }catch(NumberFormatException ex) {
            input = false;
        }
        if (input == true){
            number = Integer.parseInt(answer);    
        }     
        while (input == false){
            input = true;
            print("Invalid input. Try again.");
            print(question);
            answer = scanner.nextLine();
            
            try{
                Integer.parseInt(answer);    
            }catch(NumberFormatException ex) {
                input = false;
            }
            if (input == true){
            number = Integer.parseInt(answer);
            }   
        }
        scanner.close();
        return number;
    }

    public static void printCandidate(Candidate[] candidates, int number)
    {
        for(int i=0;i<number;i++)
        {
            if(candidates[i]==null)
            {
                print("null");
            }
            else
            {
                print(candidates[i].getName());
            }
        }
    }

    public static Candidate addCandidate(String username)
    {
        Candidate[] allCandidates = A3.getCandidateArray();
        return A3.getByUsername(username, allCandidates);
    }

    public static Candidate addRandomCandidate()
    {
        Candidate[] allCandidates = A3.getCandidateArray();
        Random random = new Random();
        int index = random.nextInt(allCandidates.length);
        return allCandidates[index];
    }

    public static Candidate[] getVote(Candidate[] candidates, int numberCandidates)
    {
        Candidate[] votes = new Candidate[numberCandidates];
        for(int i=0;i<numberCandidates;i++)
        {
            try
            {
                votes[i] = candidates[i].vote(candidates);
            }
            catch(Exception e)
            {
                votes[i] = null;
            }
        }
        return votes;
    }

    public static void getWinner(Candidate[] votes)
    {
        Candidate[] allCandidates = A3.getCandidateArray();
        String username = inputString("Who's counting the votes?");
        Candidate counter = A3.getByUsername(username, allCandidates);
        int repeat = inputInt("How many elections?");
        Candidate winners[] = new Candidate[repeat];
        for(int i=0; i<repeat; i++)
        {
            try
            {
                winners[i] = counter.selectWinner(votes);
            }
            catch (Exception e)
            {
                winners[i] = null;
            }
        }
        Candidate candidate = winners[0];
        Candidate currentWinner = winners[0];
        int modeFreq = searchVotes(candidate, winners);
        for(int i=1;i<winners.length;i++)
        {
            candidate = votes[i];
            int current_votes = searchVotes(candidate, winners);
            if(modeFreq<current_votes)
            {
                currentWinner = candidate;
                modeFreq = current_votes;
            }
        }
        if (currentWinner == null)
        {
            print("noone won the election!");
        }
        else
        {
            print(currentWinner.getName() + " won the election!");
        }
        return;
    }

    public static int searchVotes(Candidate target, Candidate[] votes)
    {
        int numberVotes = 0;
        for(int i=0;i<votes.length;i++)
        {
            if(votes[i].equals(target))
            {
                numberVotes = numberVotes + 1;
            }
        }
        return numberVotes;
    }

    public static void election()
    {
        Candidate allCandidates[] = A3.getCandidateArray();
        Candidate candidates[] = new Candidate[allCandidates.length];
        
        boolean end = false;
        int number = 0;
        String word = "";
        
        while(!end)
        {
            printCandidate(candidates, number);
            word = inputString("Choose election type \n a) no election(exit) \n b) add a specific candidate \n c) add a candidate at random \n d) run the election");
        
            if(word.equals("a")){
            System.exit(0);
            }else if(word.equals("b")){
                String username = inputString("Please enter a username");
                candidates[number] = addCandidate(username);
                number++;
            }else if(word.equals("c")){
                candidates[number] = addRandomCandidate();
                number++;
            }else if(word.equals("d")){
                Candidate[] votes = getVote(candidates, number);
                getWinner(votes);
            }  
        } 
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        // If array is empty, return instance of friend's class.
        if (votes.length == 0) 
            return new Candidate_ec221099();
        
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

    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22914();

        if (candidates.length != 0) {
            r = candidates[0];
        }
        else {
            r = candidates[candidates.length / 2];
        }


        return r;
    }

    public static void main(String[] a)
    {
        election();
    }
    
}