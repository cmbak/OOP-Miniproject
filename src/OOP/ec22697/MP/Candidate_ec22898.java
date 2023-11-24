package OOP.ec22697.MP;

class Candidate_ec22898 extends Candidate
{
    // having print methods with generics
    private static <T> void print(T s) { System.out.print(s);}
    private static <T> void println(T s) { System.out.println(s);}

    public static void main(String[] args)
    {
        final String inputerror = " is invalid please try again...";
        //println("Total candidates" + A3.getCandidateArray().length);
        println("");

        //we build a new candidate list each election
        java.util.ArrayList<Candidate> candidateList = new java.util.ArrayList<Candidate>();

        java.util.ArrayList<String> KeyWords = new java.util.ArrayList<String>();


        boolean exit = false;
        int option = -1;

        do
        {
            option = AskInteger(  "1) add a specific candidate. " +
                                     "\n2) add a random candidate. " +
                                     "\n3) randomise candidates. "   +
                                     "\n4) run a ec22898 election. " +
                                     "\n5) exit. " +
                                     "\n   Enter Option: ",
                    1, 5);
            switch (option)
            {
                case 1:
                    addSpecificCandidate(candidateList);//done
                    break;
                case 2:
                    addRandomCandidate(candidateList);//done
                    break;

                case 3:
                    randomiseCandidates(candidateList);//crashing...
                    break;
                case 4:
                    randomElection(candidateList);//done
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    println("option: " + option + inputerror);
                    break;
            }
        }
        while(!exit);

    }

    //allows the user to add a specific candidate and checks if they are a duplicate
    private static java.util.ArrayList<Candidate> addSpecificCandidate(java.util.ArrayList<Candidate> candidateList)
    {
        println("ADDING A SPECIFIC CANDIDATE...");
//        println(candidateList);//to check if it works


        String candidate_name;
        Candidate newCandidate;
        boolean done = false;
        boolean dup = false;

        do
        {
            candidate_name = AskString("Name of the candidate: ");
            newCandidate = A3.getByUsername(candidate_name, A3.getCandidateArray());
            dup = false;

            if(newCandidate != null)
            {
                if(candidateList.size() == 0 )
                {
                    println("CANDIDATE ADDED...");
                    candidateList.add(newCandidate);
                    done = true;
                }
                else
                {
                    for (Candidate candidate : candidateList)
                    {
                        if (java.util.Objects.equals(candidate.un, newCandidate.un))
                        {
                            dup = true;

                            int option = AskInteger("Candidate is a duplicate... " +
                                            "\n1. Would you like to add another candidate " +
                                            "\n2. Skip" +
                                            "     Enter Option : ",
                                    1, 2);
                            if (option == 2)
                            {
                                done = true;
                            }
                            break;
                        }
                    }

                    if(!dup)
                    {
                        println("CANDIDATE ADDED...");
                        done = true;
                        candidateList.add(newCandidate);
                    }
                }
            }
            else
            {
                println("Candidate is invalid please try again...");
            }
        }
        while(!done);

        //println(candidateList);//to check if it works

        return candidateList;
    }

    private static java.util.ArrayList<Candidate> addRandomCandidate(java.util.ArrayList<Candidate> candidateList)
    {
        java.util.Random rnd = new java.util.Random();

        Candidate[] AllCandidates = A3.getCandidateArray();
        Candidate newCandidate;
        boolean dup = false;
        boolean done = false;

        println(candidateList);//to check if it works

        do
        {
            newCandidate = AllCandidates[rnd.nextInt(AllCandidates.length+1)];

            if(newCandidate != null)
            {
                for (Candidate candidate : candidateList)
                {
                    if (candidate == newCandidate)
                    {
                        dup = true;
                        println("Candidate name: " + newCandidate.getName());
                        break;
                    }
                }

                if(!dup)
                {
                    println("CANDIDATE ADDED...");
                    candidateList.add(newCandidate);
                    done = true;
                }
            }
        }
        while(!done);

        println(candidateList);//to check if it works

        return candidateList;
    }

    public static int GetRandom(int bound)
    {
        java.util.Random rnd = new java.util.Random();
        return rnd.nextInt(bound);
    }

    public static java.util.ArrayList<Candidate> randomiseCandidates(java.util.ArrayList<Candidate> candidateList)
    {
        if(candidateList.size() == 1)
        {
            return candidateList;
        }

        println("RANDOMISING CANDIDATES...");
        println("before: " + candidateList);//to check if it works



        for(int i = 0; i < candidateList.size(); i++)
        {
            int rnd = GetRandom(candidateList.size());
            Candidate current = candidateList.get(rnd);

            //swapping the candidates in the list...
            candidateList.set(rnd, candidateList.get(i));
            candidateList.set(i, current);

            println("doing: " + candidateList);//to check if it works
        }

        println("COMPLETED RANDOMISING CANDIDATES...");

        return candidateList;//this will be the new randomised list...
    }

    private static void randomElection(java.util.ArrayList<Candidate> candidateList)
    {
        String statement;

        if(candidateList.size() == 0)
        {
            statement = "Your candidate list is empty would you like to," +
                        "\n 1) generate a new list" +
                        "\n 2) skip" +
                        "\n   Enter Option: ";
        }
        else
        {
            statement = "1) new list of candidates" +
                        "\n2) use current list " +
                        "\n   Enter Option: ";
        }

        boolean done = false;
        boolean skip = false;
        int length = 0;
        int option;

        while(!done)
        {
            option = AskInteger(statement,1, 2);

            if(option == 1)
            {
                length = AskInteger("How many candidates do you wish to add in your new list (*1-300* inclusive) " +
                                         "\n Enter number: ", 1, 300);

                java.util.ArrayList<Candidate> newList = new java.util.ArrayList<Candidate>();

                //long startTime = System.currentTimeMillis();

                for(int i = 0; i < length; i++)
                {
                    addRandomCandidate(newList);

//                    if((System.currentTimeMillis()-startTime) < 10000)
//                    {
//                        break;
//                    }

                }
                done = true;
            }
            else if(option == 2)
            {
                done = true;
                skip = true;
                println("SKIPPED...");
            }
        }

        if(!skip)
        {
            println("RUNNING RANDOM ELECTION...");

            String candidateCounting;
            candidateCounting = AskString("Who should count the votes?");
            Candidate electionCommisioner = A3.getByUsername(candidateCounting, A3.getCandidateArray());
            java.util.ArrayList<Candidate> winnerList = new java.util.ArrayList<>();
            int times;

            while(electionCommisioner == null)
            {
                candidateCounting = AskString("Who should count the votes?");
                electionCommisioner = A3.getByUsername(candidateCounting, A3.getCandidateArray());
                if(electionCommisioner == null)
                {
                    println("candidate not valid!!! please enter a right Candidate name...");
                }
            }

            times = AskInteger("How many times do you wish to count? (1-2147483647)" +
                                    "\n    Enter Option: ",
                                    1, 2147483647);

            for(int i = 0; i < times; i++)
            {
                for(Candidate candidate : A3.getCandidateArray())
                {
                    try
                    {
                        candidate.vote(candidateList.toArray(new Candidate[0]));
                    }
                    catch (Exception e)
                    {
                        boolean showErrors = false;
                        if (showErrors)
                        {
                            e.printStackTrace();
                        }
                    }
                }

                winnerList.add(electionCommisioner.selectWinner(candidateList.toArray(new Candidate[0])));
            }

            //the process of selecting a common winner is the same as selecting the most voted candidate
            Candidate c = new Candidate_ec22898();
            println("Winner is: " + c.selectWinner(winnerList.toArray(new Candidate[0])));

            println("COMPLETED RANDOM ELECTION...");
        }
    }

    //prints the string passed and returns the user input
    private static String AskString(String text)
    {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        print(text);
        return scanner.nextLine();
    }

    // Asks for an integer input and checks if that input is inside the range that is passed
    private static int AskInteger(String text, int min, int max)
    {
        String input;
        int inputint = 0;
        boolean Valid = false;

        while(!Valid)
        {
            try
            {
                input = AskString(text);
                inputint = Integer.parseInt(input);
                if((inputint >= min) && (inputint <= max))
                {
                    Valid = true;
                }
                else
                {
                    println("Your response must be between (inclusive) " + min + " and " + max);
                }
            }
            catch (NumberFormatException e)
            {
                println("Error 2: Input is invalid please enter an integer and try again...");
            }
        }
        return inputint;
    }

    //this candidate's details...
    public String getName()
    {
        return "Sandeep";
    }

    public String getSlogan()
    {
        return "I will preavail!";
    }



    public Candidate vote(Candidate[] candidates)
    {
        // If array is empty, return instance of this class.
        if (candidates.length == 0)
        {
            return new Candidate_ec22898();
        }


        // Otherwise, search for a like-minded people.
        for (Candidate c : candidates)
        {
            if (c.getName().equals("Don't stop"))
            {
                return c;
            }
        }
        
        //otherwise return the last item in the candidate array
        return candidates[candidates.length-1];
    }

    public Candidate selectWinner(Candidate[] votes)
    {
        println("SELECTING WINNER...\n\n\n");

        int length = votes.length;
        // If array is empty, return instance of this class.
        if (votes.length == 0)
        {
            println("returning empty candidate");
            return new Candidate_ec22898();
        }

        // Default to first vote, but this will be over-written.
        Candidate currentWinner = votes[0];

        //counting the votes of the candidates
        //first add all the unique candidates into a hashmap
        java.util.HashMap<Candidate, Integer> calcMap = new java.util.HashMap<>();
        for(Candidate c : votes)
        {
            if(!calcMap.containsKey(c))
            {
                calcMap.put(c, 0);
            }
//            else // this is only for testing and I've checked it works...
//            {
//                println("dup...");
//            }
        }

        //now we are counting all of the
        for(Candidate c : votes)
        {
            if(calcMap.containsKey(c))
            {
                calcMap.put(c, (calcMap.get(c) + 1) );
            }
        }

        int highestVotes = 0;
        currentWinner = votes[0];

        for(int i = 0; i < calcMap.size(); i++)
        {
            if(calcMap.get(votes[i]) > highestVotes)
            {
                highestVotes = calcMap.get(votes[i]);
                currentWinner = votes[i];
            }
        }

        println("COMPLETED...\n\n\n");

        if(currentWinner != null)
        {
            println("returning a unique candidate");
            return currentWinner;
        }
        else
        {
            println("returning empty candidate");
            return new Candidate_ec22898();
        }
    }
}
