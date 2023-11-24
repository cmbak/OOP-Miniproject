package OOP.ec22697.MP;// File Candidate_ec22751.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;
class Candidate_ec22751 extends Candidate
{
    public String getSlogan()
    {
    return "Second Modification Good Times";
    }
    
    public String getName()
    {
    return "Second Ralph";
    }
    
    public Candidate vote(Candidate[] candidates)
    {
        if (candidates.length == 0)
        {
        return new Candidate_ec22751(); // if empty return class
        }
        for (Candidate can : candidates)
        {
            if (getSlogan().equals("Modified Good Times"))
            {
            return can;
            }
            
            if (can.getName().equals("This is a modified version Ralph"))
            {
            return can;
            }
        }  
        return candidates[candidates.length - 1];
    }
    public Candidate selectWinner(Candidate[] votes) //method to ditermine winner based on highest votes
{
            
            Candidate MostVotes = votes[0];
            int Highestvote_counter = 0;
            
            if(votes.length == 0) 
            //creates an object of someone else if no elements are inside
            {
                return new Candidate_ec22751();
            }
            else //the person whith the most votes as winner
            {
                

                for (Candidate observe : votes) {
                    int vote_counter = 0;
                    for(Candidate comparingVote : votes)
                    //Go over the elements in the array of votes
                        if( observe == comparingVote)
                            vote_counter = vote_counter + 1;
                        
                    if(vote_counter > Highestvote_counter){
                        Highestvote_counter = vote_counter;
                        MostVotes = observe;
                    }

                
                }
                return MostVotes;
   }
   }
 public static void main(String[] args) {
        final Candidate[] ALLCANDIDATES  = A3.getCandidateArray();
        Candidate[] Votes = new Candidate[ALLCANDIDATES.length];  

        // Enter candidates
        int numberofcandidates = inputInt("How many candidates do you wish to enter?"); 
        Candidate[] participants = new Candidate[numberofcandidates];
        boolean stop = true;
        while (stop){
            String ans = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election");
        if (ans.equals("a"))
        { //put inside
            for (int i = 0; i < numberofcandidates; i++) 
            {
                String name = inputString("Enter candidate username: "); 
                Candidate thechosenone = A3.getByUsername(name , ALLCANDIDATES); 
                participants[i] = thechosenone;
             }

        // Print candidates
            print("Candidates are:");
            for (Candidate thechosenone : participants) 
            {
            print(thechosenone.getName());
            }
        }
        else if (ans.equals("b"))
        {
            print("The random candidate is: ");
            Random random = new Random();
            int randomcandidate = random.nextInt(ALLCANDIDATES.length);
            print(randomcandidate);
        }

        else if (ans.equals("c"))
        {
            Candidate thecounter;
            String ans2 = inputString("Who should count the votes?");
            for (int i =0 ; i<ALLCANDIDATES.length ; i++)
            {
                if (ans2.equals(ALLCANDIDATES[i]))
                {
                    thecounter = A3.getByUsername(ans2, ALLCANDIDATES);
                }
            }

            int loops = inputInt("How many times should we run the election?");
        

        // Decide how many runs
        boolean stop2 = true;
        int counter = 0;
        Candidate[] winners = new Candidate[loops];
// number of runs is loops
        // Run the election
        for (int k = 0; k < loops; k++) {

            for (int i = 0; i < ALLCANDIDATES.length; i++) {
                Votes[i] = ALLCANDIDATES[i].vote(participants);
                counter = counter + 1;
            }
        }

        // Print most common winner
        int mostwins = 0; // hiighest freq
        int timeschosen= 0; // freq
        Candidate Finalist = winners[0]; //bigwinner
        for (int i = 0; i < winners.length; i++) {
            timeschosen = 1;
            for (int j = 0; j < winners.length; j++) {
                if (winners[i] == winners[j]) timeschosen++;
            }
            if (timeschosen > mostwins) {
                mostwins = timeschosen;
                Finalist = participants[i];
            }
        }
        print("The most frequent winner was " + Finalist.getName());

    }
    }
}

public static int [] AddCandidates( int numberofcandidates , Candidate[] ALLCANDIDATES )  //method to add candidates that returns an array and fills up the candidates in that array
{
    int [] numofcand = new int[ALLCANDIDATES.length];
    for (int i = 0; i < ALLCANDIDATES.length ; i++)
    {
        Random random = new Random();
         numofcand[i]= random.nextInt(ALLCANDIDATES.length);
    }
return numofcand;
}

    // Simple generic print 
    static <T> void print(T mes) {
        System.out.println(mes);
    }

    // Returns next string from user
    static <T> String inputString(T mes) {
        Scanner sc = new Scanner(System.in);

        print(mes);

        return sc.nextLine();
    }

    // Returns next int from user
    static <T> int inputInt(T mes) {
        Scanner sc = new Scanner(System.in);
        
        print(mes);
        int integer = Integer.parseInt(sc.nextLine());
            
        return integer;
    }
}


