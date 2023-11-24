package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22838 extends Candidate
{
    public String getName()
    {
        return "Abdul";
    }

    public String getSlogan()
    {
        return "Winning isn't everything";
    }

    public Candidate vote(Candidate[] candidates)
    {
        if (candidates.length == 0)
        {
            return new Candidate_ec22838();
        }

        Random random = new Random();
        int pos = random.nextInt(candidates.length);
        return candidates[pos];
    }

    public Candidate selectWinner(Candidate[] votesCast)
    {
        if (votesCast.length == 0)
        {
            return new Candidate_ec22838();
        }

        Random random = new Random();
        int pos = random.nextInt(votesCast.length);
        return votesCast[pos];
    }
    
    public static int getNumber(String message)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        String number = scanner.nextLine();
        while ((! number.matches("[0-9]+")) || Integer.parseInt(number) == 0)
        {
            System.out.println("That wasn't a suitable number");
            System.out.println("Try again: ");
            number = scanner.nextLine();
        }

        return Integer.parseInt(number);
    }
    
    public static String getChoice(String message)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        String choice = scanner.nextLine();
        while (! choice.matches("[a-b]"))
        {
            System.out.println("That wasn't an option");
            System.out.println("Try again: ");
            choice = scanner.nextLine();
        }

        return choice;
    }

    public static String getString(String message)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        String text = scanner.nextLine();
        return text;
    }

    public static void main(String[] args)
    {
        int exceptions = 0;
        
        //allclasses is an array of all candidate classes
        //allusernames is an array of all candidate usernames
        
        Candidate[] allclasses = A3.getCandidateArray();

        String[] allusernames = new String[allclasses.length];

        for (int x = 0; x <= allclasses.length - 1; x ++)
        {
            allusernames[x] = allclasses[x].un;
        }
        
        //Ask the user whether to use all available candidates or a select amount (which they will manually enter)
        
        String choice = getChoice("a) Election with everyone b) Custom election with specific candidates");
        
        Candidate[] candidates = new Candidate[allclasses.length];
        
        if (choice.equals("b"))
        {
            int number_of_candidates = getNumber("How many candidates do you want in the election?");

            //If they want a select amount, candidates is an array of all those candidates they wish to enter into the election

            candidates = new Candidate[number_of_candidates];

            String username = "";

            for (int x = 1; x <= number_of_candidates; x++)
            {
                String endloop = "no";

                while (endloop.equals("no"))
                {
                    username = getString("Enter username of candidate " + x + ": ");

                    for (Candidate c : allclasses)
                    {
                        String current_username = c.un;

                        if (current_username.equals(username))
                        {
                            endloop = "yes";
                            candidates[x - 1] = c;

                        }
                    }

                    if (endloop.equals("no"))
                    {
                        System.out.println("That wasn't a username");
                    }
                }
            }
        }
        else if (choice.equals("a"))
        {
            candidates = allclasses;
        }
        
        //Choose who votes (from among the candidates in the array 'candidates')

        String counter = "";

        Candidate vote_counter = A3.getByUsername("ec22838", allclasses);

        String endloop = "no";

        while (endloop.equals("no"))
        {
            counter = getString("Who should count the votes? ");

            for (Candidate c : candidates)
            {
                if ((c.un).equals(counter))
                {
                    endloop = "yes";
                    vote_counter = A3.getByUsername(counter, allclasses);
                }
            }

            if (endloop.equals("no"))
            {
                System.out.println("They're not in this election");
            }
        }
        
        //How many times? Each time has its own winner
        //winners is an array of all winners (from their individual elections)

        int number_of_elections = getNumber("How many times should we run the election?");

        Candidate[] winners = new Candidate[number_of_elections];
        
        for (Candidate w: winners)
        {
            w = new Candidate_ec22838();
        }

        for (int x = 1; x <= number_of_elections; x ++)
        {
            Candidate[] votes = new Candidate[candidates.length];
            
            for (Candidate v: votes)
            {
                v = new Candidate_ec22838();
            }
            
            for (int y = 0; y <= candidates.length - 1; y ++)
            {
                try
                {
                    votes[y] = candidates[y].vote(candidates);
                }
                catch (Exception e)
                {
                    exceptions = exceptions + 1;
                }
            }

            try
            {
                winners[x - 1] = vote_counter.selectWinner(votes);
            }
            catch (Exception e)
            {
                exceptions = exceptions + 1;
            }
        }
        
        //Most common winner?

        int highest = 0;
        Candidate finalwinner = A3.getByUsername("ec22838", allclasses);

        for (Candidate w : winners)
        {
            int count = 0;

            for (Candidate ow: winners)
            {
                try
                {
                    String current_username = w.un;
                
                    if (current_username.equals(ow.un))
                    {
                        count = count + 1;
                    }

                    if (count > highest)
                    {
                        highest = count;
                        finalwinner = A3.getByUsername(w.un, allclasses);
                    }
                }
                catch (Exception e)
                {
                    exceptions = exceptions + 1;
                }
            }
        }

        System.out.println("There were " + exceptions + " errors in total");
        System.out.println("The winner was: " + finalwinner.getName());
    }
}
