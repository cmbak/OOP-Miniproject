package OOP.ec22697.MP;

import java.util.Scanner;
import java.util.Random;

class Candidate_ec22703 extends Candidate {

    public String getName() {
        return "Bob";
    }

    public String getSlogan() {
        return "More Primos!";
    }

    public Candidate vote(Candidate[] candidates) {

        if (candidates.length == 0) {
            return new Candidate_ec22703();
        }

        for (Candidate c : candidates) {
            if (c.getName().equals("Bob")) {
                return c;
            }
        }

        for (Candidate c : candidates) {
            if (c.getSlogan().equals("More Primos")) {
                return c;
            }
        }

        Random r = new Random();
        int c = r.nextInt(candidates.length);
        return candidates[c];
    }

    public Candidate selectWinner(Candidate[] votes) {

        if (votes.length == 0) {
            return new Candidate_ec22703();
        }

        Candidate currentWinner = votes[0];
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

    // Method takes in message as a String parameter and returns textInput
    public static String inputString(String message) {
        Scanner scanner = new Scanner(System.in);
        String textInput;
        System.out.println(message);
        textInput = scanner.nextLine();
        return textInput;
    }

    // Method takes in message as a String paramater which converts the string data
    // type to an integer data type that then return back to the method
    public static int inputInt(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return Integer.parseInt(scanner.nextLine());
    }

    public static void showCandidate(Candidate[] list, int size) {
        System.out.println("Candidates are");
        if (size != 0) {
            for (int i = 0; i < size; i++) {
                System.out.println((i + 1) + list[i].getName());
            }
        } else {
            System.out.println("None");
        }
        return;
    }

    public static void main(String[] args) {
        Candidate[] allCandidates = A3.getCandidateArray();
        Candidate[] list = new Candidate[allCandidates.length];
        boolean loop = true;
        int num = 0;

        while (loop) {
            showCandidate(list, num);
            String decision = inputString(
                    "Would you like to a) add a specific candidate b) add a candidate at random c) run the election d)exit?")
                    .toLowerCase();

            if (decision.equals("a")) {
                String name = inputString("Pleaser enter a username.");
                Candidate inputedCandidate = A3.getByUsername(name, allCandidates);
                if (inputedCandidate != null) {
                    list[num] = inputedCandidate;
                    num++;
                } else {
                    System.out.println("data not found...");
                }
            }

            else if (decision.equals("b")) {

                if (num == allCandidates.length) {
                    System.out.println("Candidates are full.");
                }

                Random randomInt = new Random();
                boolean zed = false;

                while (!zed) {
                    zed = false;
                    int randomNum = randomInt.nextInt(allCandidates.length);
                    Candidate candidate = allCandidates[randomNum];

                    for (int i = 0; i < num; i++) {
                        if (list[i] == candidate) {
                            zed = true;
                        }
                    }
                    if (zed == false) {
                        list[num] = candidate;
                        num++;
                    }
                }
            }

            else if (decision.equals("c")) {
                String name = inputString("Who should count the votes?");
                Candidate voteCounter = A3.getByUsername(name, allCandidates);
                Candidate[] final_List = new Candidate[num];
                for (int i = 0; i < num; i++) {
                    final_List[i] = list[i];
                }

                int numOf_Times = inputInt("How many times shall we run the election?");

                Candidate[] votes = new Candidate[numOf_Times * allCandidates.length];
                System.out.println(numOf_Times * allCandidates.length);
                System.out.println(votes.length);

                for (int i = 0; i < numOf_Times; i++) {
                    for (int j = 0; j < allCandidates.length; j++) {
                        try {
                            votes[(i * allCandidates.length) + j] = allCandidates[j].vote(final_List);
                        } catch (Exception e) {
                            votes[(i * allCandidates.length) + j] = new Candidate_ec22703();

                        }
                    }
                }
                Candidate winner = voteCounter.selectWinner(votes);
                System.out.println("Most common winner is: " + winner.getName());

                num = 0;
                list = null;
            }

            else if (decision.equals("d")) {
                loop = false;
            }
        }
    }
}
