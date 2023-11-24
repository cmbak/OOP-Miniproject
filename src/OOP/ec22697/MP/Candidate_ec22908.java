package OOP.ec22697.MP;

import java.util.Scanner;
import java.util.Random;

//adapted from ec22586

class Candidate_ec22908 extends Candidate {

    public static void main(String[] args)
    {
        Candidate[] EVERYONE = A3.getCandidateArray();
        Candidate[] electionCandidates = new Candidate[EVERYONE.length];
        int COUNTS = 0;

        System.out.println("= Running Repeated Elections =");
        String choice;
        label:
        while (true)
        {
            presentCandidates(electionCandidates, COUNTS);
            choice = pQuest("Would you like to a) add a specific candidate b) add a candidate at random c) run the election d) exit");
            switch (choice) {
                case "a":
                    COUNTS = addCandidate(electionCandidates, EVERYONE, COUNTS);
                    break;
                case "b":
                    COUNTS = addRandomCandidate(electionCandidates, EVERYONE, COUNTS);
                    break;
                case "c":
                    runElection(electionCandidates, EVERYONE, COUNTS);
                    break;
                case "d":
                    break label;
                default:
                    System.out.println("Please choose option a, b, c or d!");
                    break;
            }
        }
    }



    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22908();

        if (votes.length != 0) {
            int highestCount = 0;
            for (Candidate c : votes) {
                int count = 0;
                for (Candidate x : votes) {
                    if (x == c) count++;
                }
                if (count > highestCount) {
                    highestCount = count;
                    r = c;
                }
            }
        }
        return r;
    }

    private static String pQuest(String question)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println(question);
        return scanner.nextLine();
    }

    private static int pQuestInt(String question)
    {
        int value;
        Scanner scanner = new Scanner(System.in);

        System.out.println(question);
        value = Integer.parseInt(scanner.nextLine());

        return value;
    }

    // Create a copy of array with specific length
    private static Candidate[] copyArray(Candidate[] candidates, int count)
    {
        Candidate[] copy = new Candidate[count];
        for (int i = 0; i < count; i++)
        {
            copy[i] = candidates[i];
        }
        return copy;
    }

    // Displays all the candidates
    public static void presentCandidates(Candidate[] electionCandidates, int COUNTS)
    {
        System.out.println("The Candidates are");
        if (COUNTS == 0)
        {
            System.out.println("None");
        }
        else
        {
            for (int i = 0; i < COUNTS; i++)
            {
                System.out.println((i + 1) + ". " + electionCandidates[i].getName() + ": " + electionCandidates[i].getSlogan());
            }
        }
    }

    private static int addCandidate(Candidate[] electionCandidates, Candidate[] EVERYONE, int COUNTS)
    {
        String candidateName = pQuest("Please enter a username.");
        Candidate candidate = A3.getByUsername(candidateName, EVERYONE);
        if (candidate != null)
        {
            electionCandidates[COUNTS] = candidate;
            return COUNTS + 1;
        }
        else
        {
            System.out.println("Cannot find candidate: " + candidateName);
            return COUNTS;
        }
    }

    private static int addRandomCandidate(Candidate[] electionCandidates, Candidate[] EVERYONE, int COUNTS)
    {
        if (COUNTS == EVERYONE.length)
        {
            System.out.println("Candidates are full.");
            return COUNTS;
        }
        Random randInt = new Random();
        boolean found = false;
        while (true)
        {
            found = false;
            // Get a random candidate
            int randomInt = randInt.nextInt(EVERYONE.length);
            Candidate candidate = EVERYONE[randomInt];
            // Check if there's already one
            for (int i = 0; i < COUNTS; i++)
            {
                if (electionCandidates[i] == candidate)
                {
                    found = true;
                }
            }
            if (!found)
            {
                electionCandidates[COUNTS] = candidate;
                return COUNTS + 1;
            }
        }
    }

    public static void runElection(Candidate[] electionCandidates, Candidate[] EVERYONE, int COUNTS)
    {
        String tellerName = pQuest("Who should count the votes?");
        Candidate teller = A3.getByUsername(tellerName, EVERYONE);
        if (teller != null)
        {
            int count = pQuestInt("How many times shall we run the election?");
            Candidate[] winners = new Candidate[count];
            for (int i = 0; i < count; i++)
            {
                Candidate[] candidates = copyArray(electionCandidates, COUNTS);
                Candidate[] votes = new Candidate[EVERYONE.length];
                for (int j = 0; j < EVERYONE.length; j++)
                {
                    votes[j] = EVERYONE[j].vote(candidates);
                }
                winners[i] = teller.selectWinner(votes);
            }
            getCommonWinner(winners);
        }
        else
        {
            System.out.println("Cannot find candidate: " + tellerName);
        }
    }

    private static void getCommonWinner(Candidate[] winners)
    {
        boolean moreThanOneWinner = false;
        String winnerName = null;
        if (winners.length != 0)
        {
            // Store a copy of winners for counting (since array is modified during counting)
            Candidate[] countWinner = copyArray(winners, winners.length);
            int highestCount = 0;
            for (int i = 0; i < countWinner.length; i++)
            {
                Candidate c = countWinner[i];
                // if c is null, c is removed in previous loop.
                if (c != null)
                {
                    int count = 0;
                    for (int j = 0; j < countWinner.length; j++)
                    {
                        Candidate x = countWinner[j];
                        if (x != null && x.getName() == c.getName())
                        {
                            // Remove counted winner (refer to above).
                            countWinner[j] = null;
                            count++;
                        }
                    }
                    if (count == highestCount)
                    {
                        winnerName = winnerName + " and " + c.getName();
                        moreThanOneWinner = true;
                    }
                    if (count > highestCount)
                    {
                        highestCount = count;
                        winnerName = c.getName();
                        moreThanOneWinner = false;
                    }
                }
            }
        }
        if (winnerName != null)
        {
            if (moreThanOneWinner)
            {
                System.out.println("Most common winners are " + winnerName);
            }
            else
            {
                System.out.println("Most common winner is " + winnerName);
            }
        }
        System.out.println("There were no other winners.");
    }

    public String getName() {
        return "ec22908";
    }

    public String getSlogan() {
        return "XqcL";
    }

    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22908();

        if (candidates.length != 0) {
            r = candidates[0];
            for (Candidate c : candidates) {
                if (c.getName().equals("ec22908"))
                    return c;
            }
        }
        return r;
    }


}
