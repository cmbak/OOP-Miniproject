package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;

public class Candidate_ec22907 extends Candidate {

    public String getName() {
        return "bigT";
    }

    public String getSlogan() {
        return "let's go!";
    }

    public Candidate vote(Candidate[] candidates) {
        Random r = new Random();
        // when array is empty, add me as the candidate.
        if (candidates.length == 0)
            return new Candidate_ec22907();
        int x = r.nextInt(candidates.length);
        return candidates[x];

    }

    public Candidate selectWinner(Candidate[] candidates) {
        int topVote = 0;
        Candidate winner = new Candidate_ec22907();

        if (candidates.length <= 0) {
            return new Candidate_ec22907();
        }

        for (Candidate x : candidates) {
            int count = 0;

            for (Candidate y : candidates) {
                if (x == y) {
                    count = count + 1;
                }
            }
            if (count > topVote) {
                topVote = count;
                winner = x;
            }
        }
        return winner;
    }

    // this method will get the input value from user.
    public static String getStr(String a) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(a);
        return scanner.nextLine();
    }

    // this method will get int, very similar with the one above.
    public static int getInt(String a) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(a);
        return scanner.nextInt();
    }

    public static void main(String[] args) {
        Candidate[] allCandidates = A3.getCandidateArray();
        String candidateName;
        int candidateCount;

        candidateCount = getInt("How many people are in the election? Please enter a number");
        Candidate[] candidatesList = new Candidate[candidateCount];

        for (int i = 0; i < candidateCount; i++) {
            System.out.print("add more candidates: ");
            candidateName = getStr("Please enter the candidate name: ");
            candidatesList[i] = A3.getByUsername(candidateName, allCandidates);

        }
        // choose the counter
        String counterName = getStr("Who is the counter?");
        Candidate counter = A3.getByUsername(counterName, candidatesList);

        int electionsNumber = getInt("How many times will the election process?");
        Candidate[] v = new Candidate[electionsNumber * candidateCount];

        for (int j = 0; j < electionsNumber; j++) {
            for (int z = 0; z < electionsNumber; z++) {
                v[z + (j * candidateCount)] = candidatesList[z].vote(candidatesList);
            }
        }
        // Winner come out
        Candidate w = counter.selectWinner(v);
        System.out.println(
                "Out final winner is " + w.getName() + ". Thanks for joining this election, hope you win next time:) ");
    }
}
