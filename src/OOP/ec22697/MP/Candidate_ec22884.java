package OOP.ec22697.MP;// File Candidate_ec22884.java
//

import java.util.*;

class Candidate_ec22884 extends Candidate {
    
    public static void main (String[] args)
    {
        boolean exit = false;

        //Main loop
        while (!exit)
        {
            exit = Actions();
        }
    }

    //Option set main
    private static boolean Actions()
    {
        char ch = InputChar("Would you like to\na) exit\nb) run same election many times\nc) check who counts honestly\nd) run all possible two-candidate elections\ne) run a higher-order election");

        switch (ch)
        {
            case 'a':
                //exit
                return true;
            case 'b':
                //run same election many times
                ChoiceB();
                break;

            default:
                return false;
        }
        return false;
    }


    //Option set B
    private static void ChoiceB()
    {
        print("= Running Repeated Elections =");
        boolean exit = false;
        Candidate[] candidates = new Candidate[0];

        while (!exit)
        { 
            printCandidateNames(candidates);
            char ch = InputChar("Would you like to a) add a specific candidate b) add a candidate at random c) run the election");

            switch (ch)
            {
                case 'a':
                    candidates = AddSpecificCandidate(candidates);
                    break;
                case 'b':
                    candidates = AddRandomCandidate(candidates);
                    break;
                case 'c':
                    RunTheElection(candidates);
                    exit = true;
                    break;
                default:
                    exit = false;
                    break;
            }
        }


    }

    private static void printCandidateNames(Candidate[] candidates)
    {
        print("Candidates are ");
        if (candidates.length == 0)
        {
            print("Null.");
        }
        else
        {
            for (Candidate c : candidates)
            {
                print(c.getName());
            }
        }
    }

    private static Candidate[] AddSpecificCandidate(Candidate[] candidates)
    {
        Candidate[] ca = new Candidate[candidates.length +1];
        Candidate sCa;

        for (int i = 0; i < candidates.length; i++)
        {
            ca[i] = candidates[i];
        }

        sCa = getCandidate("Please enter the username of the candidate: ", A3.getCandidateArray());

        ca[candidates.length] = sCa;

        return ca;
    }

    private static Candidate[] AddRandomCandidate(Candidate[] candidates)
    {
        Candidate[] ca = new Candidate[candidates.length +1];
        Candidate sCa;

        for (int i = 0; i < candidates.length; i++)
        {
            ca[i] = candidates[i];
        }

        ca[candidates.length] = A3.getCandidateArray()[(new Random()).nextInt(A3.getCandidateArray().length)];

        return ca;
    }

    private static void RunTheElection(Candidate[] candidates)
    {
        int noOfElections = InputInt("How many times should we run this election?");
        Candidate[] winners = new Candidate[noOfElections];
        Candidate selectWca = getCandidate("Who should select the winner?", candidates);

        for (int i = 0; i < noOfElections; i++)
        {
            Candidate[] votes = new Candidate[candidates.length];

            for (int j =0; j < candidates.length; j++)
            {
                votes[j] = candidates[j].vote(candidates);
            }

            winners[i] = selectWca.selectWinner(votes);
        }

        Candidate mostCommonWinner = MostCommonWinner(winners);
        print("Most common winner is " + mostCommonWinner + ".\n\n");
    }

    private static Candidate MostCommonWinner(Candidate[] votes) 
    {
        Candidate r = new Candidate_ec22884();

        if (votes.length != 0)
        {
            int currentMax = 0;
            for (Candidate c : votes)
            {
                int count = 0;
                for (Candidate v : votes)
                {
                    if (c.equals(v))
                    {
                        count++;
                    }
                }

                if (count >currentMax)
                {
                    currentMax = count;
                    r = c;
                }
            }
        }

        return r;
    }

    private static Candidate getCandidate(String query, Candidate[] candidates)
    {
        Candidate ca;

        do
        {
            String input = InputString(query);
            ca = A3.getByUsername(input, candidates);
        } while (ca == null);

        return ca;
    }

    private static int InputInt(String query)
    {
        String input;

        do
        {   
            input = InputString(query);
        }
        while (!IsInt(input));

        return Integer.parseInt(input);
    }

    private static boolean IsInt(String input)
    {
        boolean check = true;
        int index = 0;

        while (index < input.length() && check)
        {
            if (!Character.isDigit(input.charAt(index)))
            {
                print("Please enter an integer");
                check = false;
            }
            index++;
        }

        return check;   
    } 

    private static char InputChar(String query)
    {
        String input;

        do
        {
            input = InputString(query);
        } while (input.length() != 1);

        return input.charAt(0);
    }

    private static String InputString(String query)
    {
        String input;

        do
        {
            Scanner sc = new Scanner(System.in);

            print(query);
            input = sc.nextLine().trim();
        }
        while (input.isEmpty());

        return input;
    }

    private static void print(String a)
    {
        System.out.println(a);
    }
    
    public String getName() {
        return "Batman";
    }
    
    public String getSlogan() {
        return "I'm Batman";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22884();
        
        if (candidates.length != 0)
        {
            for (Candidate c : candidates)
            {
                if (c.getSlogan().equals("I'm Batman"))
                {
                    return c;
                }
            }
            
            for (Candidate c : candidates)
            {
                if (c.getName().equals("Batman") || c.getName().equals("Bruce Wayne"))
                {
                    return c;
                }
            }
        }   
        
        return r;
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22884();
        
        if (votes.length != 0)
        {
            int currentMax = 0;
            for (Candidate c : votes)
            {
                int count = 0;
                for (Candidate v : votes)
                {
                    if (c.equals(v))
                    {
                        count++;
                    }
                }
                if (count >currentMax)
                {
                    currentMax = count;
                    r = c;
                }
            }
        }
        
        return r;
    }
    
}
