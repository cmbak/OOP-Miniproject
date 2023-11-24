package OOP.ec22697.MP;// File Candidate_ec22770.java
//
// Machine generated stub for Assignment 2

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

class Candidate_ec22770 extends Candidate 
{
    
    static ArrayList<Candidate> eCandidates = new ArrayList<Candidate>();
    //Hold all candidates in the module
    static Candidate[] CandidateArray = new Candidate[546];
    static Candidate CountVotesCandidate;



    public static void main(String[] args) 
    {
        CandidateArray = A3.getCandidateArray();
        AddCandidatesToELection();
    
    }

    //Adding Candidates Method
    public static void AddCandidatesToELection()
    {
        Boolean WantsToQuit = false;
        while(WantsToQuit == false)
        {
            displayelection();
            WantsToQuit = AddCandidateMenu();
        }



    }

    public static void AddSpecficCandidate()
    {
        
        boolean CandidateFound = false;
        while(CandidateFound == false)
        {
            System.out.println("Enter Username");
            String input = (new Scanner(System.in)).nextLine();

            Candidate searchCandidate = A3.getByUsername(input, CandidateArray);
            if(searchCandidate == null)
            {
                System.out.println("Not found");
                System.out.println("Type y if you want to continue adding a candidate. Press any other key to exit.");
                input = (new Scanner(System.in)).nextLine();
                if(input.equals("y") == false)
                {
                    CandidateFound = true;
                }
            }
            else{
                if(eCandidates.contains(searchCandidate))
                {
                    System.out.println("The candidate entered is already in the election!");
                }
                else
                {
                    CandidateFound = true;
                    eCandidates.add(searchCandidate);
                    System.out.println("Updated candidate list: ");
                    displayelection();
                }
                //display candiates here
                System.out.println("Type y if you want to add another candidate. Press any other key to exit.");
                input = (new Scanner(System.in)).nextLine();
                if(input.equals("y") == true)
                {
                    CandidateFound = false;
                }
            }

        }

    }

    public static void addrandom()
    {
        
        Candidate RandomCandidate = CandidateArray[(new Random()).nextInt(CandidateArray.length)];
        while(eCandidates.contains(RandomCandidate))
        {
            RandomCandidate = CandidateArray[(new Random()).nextInt(CandidateArray.length)];
        }
        eCandidates.add(RandomCandidate);
       
    }

    public static void displayelection()
    {
        System.out.println("Display Election Candidates");
        if(eCandidates.size() == 0)
        {
            System.out.println("None");
        }
        for (int i = 0; i < eCandidates.size(); i++)
        {
            System.out.println("Candidate Name - " + (eCandidates.get(i)).getName());
        }
    }

    public static void runelection()
    {
        boolean goBack = false;
        GetVoterCandidate();
        displayelection();
        RunElectionManyTimes();
        while(goBack == false)
        {

            System.out.println("Would you like to a) Go Back  b)run same election many times  c) check who counts");
            String input = (new Scanner(System.in)).nextLine();

            switch(input)
            {
                case "a": 
                    goBack = true;
                    break;
                
                case "b": 
                    displayelection();
                    RunElectionManyTimes();
                    break;
                case "c":
                    System.out.println("The candidate who is counting the votes is: ");
                    System.out.println(CountVotesCandidate.getName());
                    break;

                default:
                    System.out.println("You must select an option");
                    break;

            }
        }

       

        


    }


    public static void GetVoterCandidate()
    {
        System.out.println("Who would you like to count the votes.");
        boolean CandidateFound = false;
        while(CandidateFound == false)
        {
            System.out.println("Enter Username");
            String input = (new Scanner(System.in)).nextLine();

            Candidate searchCandidate = A3.getByUsername(input, CandidateArray);
            if(searchCandidate == null)
            {
                System.out.println("Not found");
                
            }
            else
            {
                    CandidateFound = true;
                    CountVotesCandidate = searchCandidate;
            }

        }
    }




    public static Candidate[] getVotes()
    {
        Candidate[] eCandidatesArray = new Candidate[eCandidates.size()];
        eCandidatesArray = eCandidates.toArray(eCandidatesArray);
        Candidate[] votes = new Candidate[CandidateArray.length];
        for(int i = 0; i < CandidateArray.length; i++)
        {
            try
            {
               votes[i] =  CandidateArray[i].vote(eCandidatesArray);
            }
            catch(Exception ex)
            {
                votes[i] = (new Candidate_ec22770()).vote(CandidateArray);
            }
        }

        return votes;
    }

    public static Candidate getWinnerFromCountVoteCandidate(Candidate[] votes)
    {
        try 
        {
            return CountVotesCandidate.selectWinner(votes);
        } 
        catch (Exception e) 
        {
            CountVotesCandidate = new Candidate_ec22770();
            return CountVotesCandidate.selectWinner(votes);
        }
    }

    

    public static void RunElectionManyTimes()
    {
        ArrayList<Candidate> WinnersOfElections = new ArrayList<Candidate>();
        int NumberofElections = GetNumberofTimesToRunElection();
        for(int i = 0; i < NumberofElections; i++)
        {
            WinnersOfElections.add(getWinnerFromCountVoteCandidate(getVotes()));
        }

        Candidate[] WinnersOfElectionsArray = new Candidate[WinnersOfElections.size()];
        WinnersOfElectionsArray = WinnersOfElections.toArray(WinnersOfElectionsArray);

        Candidate MostCommonWinner = getWinnerFromCountVoteCandidate(WinnersOfElectionsArray);

        System.out.println("The Most Common winer is " + MostCommonWinner.getName());
    }


    public static int GetNumberofTimesToRunElection()
    {
        Boolean IntegerEntered = false;
        int input = 0;
        while(IntegerEntered == false)
        {
            try 
            {
                System.out.println("Input how many times you would like to run election");
                input = Integer.parseInt((new Scanner(System.in)).nextLine());
                if(input <= 0)
                {
                    System.out.println("Input is too small");
                }
                else if(input > 1000)
                {
                    System.out.println("Input is too big");
                }
                else
                {
                    IntegerEntered = true;
                }

            }
            catch (Exception e)
            {
                System.out.println("You must Enter an integer");
            }
        }
        
        return input;
    }


    //AddCandidateMenu
    public static Boolean AddCandidateMenu()
    {
        final boolean Quit = true;
        final boolean DontQuit = false;
        System.out.println("Would you like to a) add a specfic candidate b) add a candidate at random c) run the election d) Quit");
        String input = (new Scanner(System.in)).nextLine();
        switch(input)
        {
            case "a":
                AddSpecficCandidate();

                break;

            case "b":
                addrandom();
                break;

            case "c":
                runelection();

                break; 

            case "d":
                return Quit;

            default:
                System.out.println("Incorrect Input");
                break;

        }

        return DontQuit;
        
    }


    ///Running Election Method
















    
    public String getName() {
        return "Justin";
    }
    
    public String getSlogan() {
        return "Justin Life";
    }
    
    public Candidate vote(Candidate[] candidates) {

        if (candidates.length == 0) 
        {
            return new Candidate_ec22695();
        }

        for (int i = 0; i < candidates.length; i++)
        {
            if (candidates[i].equals("Bob"))
            {
                return candidates[i];
            }
        }
        //return candidates[1];
        return new Candidate_ec22770();

    }
    
    public Candidate selectWinner(Candidate[] votes) {
        // If array is empty, return instance of friend's class.
        if (votes.length == 0) 
        {
            return new Candidate_ec22695();
        }
       
        int HighestValue = 0;
        Candidate HighestCandidate = votes[0];
        for (int i=0; i<votes.length; i++)
        {
            int count = 0;
            for (int z = 0; z<votes.length; z++)
            {
                if (votes[i] == votes[z]) 
                {
                    count ++;
                }
            }
            if(count > HighestValue)
            {
                HighestValue = count;
                HighestCandidate = votes[i];
            }


        }
        return HighestCandidate;
    }
    
}
