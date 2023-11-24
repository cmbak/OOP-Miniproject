package OOP.ec22697.MP;// File Candidate_ec22914.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;
class Candidate_ec22914 extends Candidate
{
    public String getName() {
        return "Saad";
    }

    public static String inputString(String message)
    {
        String answer;
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        answer = scanner.nextLine();

        return answer;
    }

    public static int inputint(String message)
    {
        int answer;
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        answer = Integer.parseInt(scanner.nextLine());

        return answer;
    }

    public static void print_candidate(Candidate[] candidates, int number)
    {
        for(int i=0;i<number;i++)
        {
            if(candidates[i]==null)
            {
                System.out.println("null");
            }
            else
            {
                System.out.println(candidates[i].getName());
            }
        }
    }

    public static Candidate add_candidate(String username)
    {
        Candidate[] all_candidates = A3.getCandidateArray();
        return A3.getByUsername(username, all_candidates);
    }

    public static Candidate add_RandomCandidate()
    {
        Candidate[] all_candidates = A3.getCandidateArray();
        Random random = new Random();
        int index = random.nextInt(all_candidates.length);
        return all_candidates[index];
    }

    public static Candidate[] getvote(Candidate[] candidates, int num_candidate)
    {
        Candidate[] votes = new Candidate[num_candidate];
        for(int i=0;i<num_candidate;i++)
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

    public static void getwinner(Candidate[] votes)
    {
        Candidate[] all_candidates = A3.getCandidateArray();
        String username = inputString("Who is going to count the votes?");
        Candidate counter = A3.getByUsername(username, all_candidates);
        int repeat = inputint("How many times do you want to run the election?");
        Candidate[] winners = new Candidate[repeat];
        for(int i=0;i<repeat;i++)
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
        Candidate target = winners[0];
        Candidate current_winner = winners[0];
        int most_common = search_votes(target, winners);
        for(int i=1;i<winners.length;i++)
        {
            target = votes[i];
            int current_votes = search_votes(target, winners);
            if(most_common<current_votes)
            {
                current_winner = target;
                most_common = current_votes;
            }
        }
        if (current_winner == null)
        {
            System.out.println("null won the election!");
        }
        else
        {
            System.out.println(current_winner.getName() + " won the election!");
        }
        return;
    }

    public static int search_votes(Candidate target, Candidate[] votes)
    {
        int num_votes = 0;
        for(int i=0;i<votes.length;i++)
        {
            if(votes[i].equals(target))
            {
                num_votes = num_votes + 1;
            }
        }
        return num_votes;
    }

    public static void election()
    {
        Candidate[] all_candidates = A3.getCandidateArray();
        Candidate[] candidates = new Candidate[all_candidates.length];
        boolean loop = true;
        int number = 0;
        while(loop == true) {
            print_candidate(candidates, number);
            String userInput = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
            if (userInput.equals("a")) {
                String username = inputString("Please enter a username");
                candidates[number] = add_candidate(username);
                number++;
            } else if (userInput.equals("b")) {
                candidates[number] = add_RandomCandidate();
                number++;
            } else if (userInput.equals("c")) {
                Candidate[] votes = getvote(candidates, number);
                getwinner(votes);
            }
        }
    }
    public String getSlogan() {
        return "make qmul great again";
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
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22914();

        if (votes.length != 0) {
            r = votes[0];
        }
        else {
            r = votes[votes.length / 2];
        }


        return r;
    }
    public static void main(String[] a)
    {
        election();
    }

}
