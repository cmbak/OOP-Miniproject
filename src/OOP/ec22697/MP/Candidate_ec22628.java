package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22628 extends Candidate {
    
    public String getName() {
        return "L";
    }
    
    public String getSlogan() {
        return "Justice For All!";
    }

    public static String stringInput(String question)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println(question);
        String input = scanner.nextLine();
        return input;
    }

    //idea inspired by ec22974
    public static void main(String[] args)
    {
        Candidate[] everyCandidate = A3.getCandidateArray();
        Candidate[] electionCandidate = new Candidate[everyCandidate.length];
        int counter = 0;
        boolean escape = false;
        String option;

        while (!escape)
        {
            displayCandidates(electionCandidate, counter);
            option = stringInput("Would you like to:\na) add a specific candidate\nb) add a candidate at random\nc) run the election");
            switch (option)
            {
                case "a":
                    String username = stringInput("Please enter a username.");
                    Candidate specificCandidate = A3.getByUsername(username, everyCandidate);
                    if (specificCandidate != null)
                    {
                        electionCandidate[counter] = specificCandidate;
                        counter++;
                    }
                    else
                    {
                        System.out.println("User not found");
                    }
                    break;

                case "b":
                    Random random = new Random();
                    int number = random.nextInt(everyCandidate.length);
                    electionCandidate[counter] = everyCandidate[number];
                    counter++;
                    break;
                
                case "c":
                    String user = stringInput("Who should count the votes?");
                    Candidate voteCounter = A3.getByUsername(user, everyCandidate);
                    if (voteCounter == null)
                    {
                        System.out.println("User not found. ec22628 will count");
                        user = "ec22628";
                        voteCounter = A3.getByUsername(user, everyCandidate);
                    }
                    int electionLoop = Integer.parseInt(stringInput("How many times should we run the election?"));

                    Candidate[] votes = new Candidate[electionLoop * (counter-1)];
                    for (int i = 0; i <= electionLoop; i++)
                    {
                        for (int j = 0; j < counter; j++)
                        {
                            try {
                                votes[(counter*i)+j] = electionCandidate[j].vote(electionCandidate);
                            }
                            catch (Exception e)
                            {
                                votes[i] = new Candidate_ec22628();
                            }
                        }
                    }
                    try {
                        Candidate winner = voteCounter.selectWinner(votes);
                        System.out.println("The winner is "+ winner.getName());
                    }

                    catch (Exception e)
                    {
                        Candidate ec22628 = new Candidate_ec22628();
                        Candidate winner = ec22628.selectWinner(votes);
                        System.out.println("The winner is "+ winner.getName());
                    }
                    break;

                default:
                    System.out.println("Enter a valid option");
            }
        }
    }

    public static void displayCandidates(Candidate[] electionCandidate, int counter)
    {
        System.out.println("Candidates are");
        for (int i = 0; i < counter; i++)
        {
            System.out.println((i+1) + ". " + electionCandidate[i].getName());
        }
        return;
    }

    //from sample ac_1234
    public Candidate vote(Candidate[] candidates) {
        
        // First search for self on the list of candidates.
        for (Candidate c : candidates)
            if (c.getName().equals("L"))
                return c;
        
        // Otherwise, default to ramdom candidate on list.
        Random r = new Random();
        int c = r.nextInt(candidates.length);
        return candidates[c];
    }
    //from sample ac_1235
    public Candidate selectWinner(Candidate[] votes) {
        
        // If array is empty, return instance of this class.
        if (votes.length == 0) 
            return new Candidate_ec22628();
        
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
            if (count >= highestCount) {
                highestCount = count;
                currentWinner = c;
            }
        }
        
        return currentWinner;
    }
}
