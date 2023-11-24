package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;

class Candidate_jpp314171 extends Candidate {

    public static String inputString(String message) {
        String answer;
        Scanner scanner = new Scanner(System.in);

        System.out.println(message);
        answer = scanner.nextLine();

        return answer;
    }

    public static int inputInt(String message) {
        String answer = inputString(message);
        return Integer.parseInt(answer);
    }

    public static void main(String[] args) {
        Candidate[] all = A3.getCandidateArray();
        Candidate[] newCandidates = new Candidate[all.length];
        String option = "a";
        int candidateLength = 0;

        System.out.println("= Running Repeated Elections =");

        while (!option.equals("d")) {
            if (candidateLength == 0) {
                System.out.println("None");
            } else {
                for (int i = 1; i <= candidateLength; i++) {
                    System.out.println("Candidates are");
                    System.out.println((i) + ". " + newCandidates[i - 1].getName());
                }
            }

            option = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election d) leave");

            if (option.equals("a")) {
                String name = inputString("Please enter a name.");

                for (int i = 0; i < candidateLength; i++) {
                    if (all[i].equals(name)) {
                        newCandidates[candidateLength] = all[i];
                    }
                }
                candidateLength += 1;
            } else if (option.equals("b")) {
                Random random = new Random();
                int index = random.nextInt(all.length);
                newCandidates[candidateLength] = all[index];
                candidateLength += 1;
            } else if (option.equals("c")) {
                String vote = inputString("Who should count the votes?");
                int times = inputInt("How many times shall we run the election?");
                Candidate counter = A3.getByUsername(vote, newCandidates);

                Candidate[] winner = new Candidate[times];

                for (int i = 0; i < times; i++) {
                    winner[i] = counter.selectWinner(newCandidates);
                }
            } else if (option.equals("d")) {
                break;
            } else {
                option = inputString("please enter a, b, c, d");
            }
        }
    }


    public String getName() {
        return "Zheng (jpp314171)";
    }

    public String getSlogan() {
        return "No slogan (jpp314171)";
    }

    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_jpp314171();

        if (candidates.length == 0) {
            return r;
        }

        for (Candidate c : candidates) {
            if (c.getSlogan().equals("No slogan (jpp314171)")) {
                return c;
            }
        }

        for (Candidate c : candidates) {
            if (c.getName().equals("none") ) {
                return c;
            }
        }

        return candidates[candidates.length - 1];
    }

    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_jpp314171();
    
        if (votes.length == 0) {
            return r;
        }
    
        Candidate currentWinner = votes[0];
    
        int highestCount = 0;
        for (Candidate c : votes) {
            int count = 0;
            for (Candidate x : votes) {
                if (x == c) {
                    count++;
                }
            }
            if (count > highestCount) {
                highestCount = count;
                currentWinner = c;
            }
        }
    
        return currentWinner;
    }

}
