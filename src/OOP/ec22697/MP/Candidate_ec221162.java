package OOP.ec22697.MP;

import java.util.Scanner;
import java.util.Random;

class Candidate_ec221162 extends Candidate {

    public static void main(String[] args) {
        String userChoice;
        int count = 0;
        Candidate[] AllCandidates = A3.getCandidateArray();
        Candidate[] CandidatesList = new Candidate[AllCandidates.length];
        String username;
        boolean done = false;
        System.out.println("Running election loop");
        while (!done)
        {
            System.out.println("Candidates are");
            if (count == 0) {
                System.out.println("None");
            }
            else {
                for(int i = 0; i < count;i++) {
                    System.out.println(CandidatesList[i].getName());
                }
            }

            userChoice = inputStr("Would you like to a) add a specific candidate b) add a candidate at random c) run the election d) exit?");

            switch(userChoice) {
                case ("a"):
                    username = inputStr("Enter username");
                    for (Candidate candidate : AllCandidates) {
                        if (candidate.un.equals(username)) {
                            CandidatesList[count] = candidate;
                        }
                    }
                    count += 1;
                    break;
                case ("b"):
                    Random rand = new Random();
                    int randomCand = rand.nextInt(AllCandidates.length);
                    CandidatesList[count] = AllCandidates[randomCand];
                    count += 1;
                    break;
                case ("c"):
                    String userCounter = inputStr("Who should count the votes");
                    int timesRan = inputInt("How many times shall we run the election");
                    for (Candidate allCandidate : AllCandidates) {
                        if (allCandidate.un.equals(userCounter)) {
                            Candidate[] FinalCandidates = new Candidate[count];
                            for(int j = 0; j < timesRan; j++) {
                                for (int z = 0; z < count; z++) {
                                    FinalCandidates[z] = CandidatesList[z];
                                }
                            }
                            Candidate winner = allCandidate.selectWinner(FinalCandidates);
                            System.out.println("The most common winner is " + winner.getName());
                            done = true;
                        }
                    }
                    break;
                case ("d"):
                    System.exit(0);
                    break;
            }
        }
    }
    public static String inputStr (String message)
    {
        String answer;
        Scanner sc = new Scanner(System.in);

        System.out.println(message);
        answer = sc.nextLine();

        return answer;
    }
    public static int inputInt (String message)
    {
        int answer;
        Scanner sc = new Scanner(System.in);

        System.out.println(message);
        answer = sc.nextInt();

        return answer;
    }


    public String getName() {
        return "Guts";
    }

    public String getSlogan() {
        return "Charge it";
    }
    public Candidate vote(Candidate[] candidates) {
        if (candidates.length == 0) {
            return new Candidate_ec221162();
        } else {
            for (Candidate c : candidates)
                if (c.getSlogan().equals("Bread"))
                 return c;
            for (Candidate c : candidates)
                if (c.getName().equals("John"))
                 return c;
            return candidates[0];
        }
    }
    public Candidate selectWinner(Candidate[] votes) {
        Candidate Winner = new Candidate_ec221162();

        if(votes.length == 0){
            return Winner;
        }

        Candidate[] aCountName = votes;
        int[] aCount = new int [votes.length];
        int store = 0;
        for (int i = 0; i < votes.length; i++) {
            for (int j = 0; j < votes.length; j++) {
                if(aCountName[j] == votes[i]){
                    aCount[i]++;
                }
            }
        }
        for (int i = 0; i < aCount.length; i++) {
            if(store < aCount[i]){
                store = aCount[i];
                Winner = votes[i];
            }
        }
        return Winner;
    }
}
