package OOP.ec22697.MP;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

/// Andrei Alexandru Slatinaru - ec22882
class Candidate_ec22882 extends Candidate {

    public static String getStringInput(String message) {
        System.out.println(message);

        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    public static void printCandidates(ArrayList<Candidate> candidates) {
        for (int i = 0; i < candidates.size(); ++i) {
            String name = candidates.get(i) == null ? "NULL :(" : candidates.get(i).getName();
            System.out.println((i + 1) + ". " + name);
        }
    }

    public static int getIntInput(String message) {
        try {
            return Integer.parseInt(getStringInput(message));
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }

    public static ArrayList<Candidate> getCandidates() {

        ArrayList<Candidate> toSelectFrom = new ArrayList<Candidate>();

        while (true) {
            String opt = getStringInput(
                    "Would you like to?\na) Add a specific candidate\nb) Add a candidate at random\nc) Run the election\n>");

            switch (opt.toLowerCase()) {

                case "a":
                    String candidateName = getStringInput("\nPlease enter the name of the candidate.\n>");
                    Candidate candidate = A3.getByUsername(candidateName, A3.getCandidateArray());
                    if (candidate != null) {
                        toSelectFrom.add(candidate);
                        System.out.println("\nAdded " + candidateName + " as a voter and an electable candidate.");
                    }
                    printCandidates(toSelectFrom);
                    break;
                case "b":

                    toSelectFrom.add(A3.getCandidateArray()[new Random().nextInt(A3.getCandidateArray().length)]);
                    printCandidates(toSelectFrom);
                    break;

                case "c":
                    return toSelectFrom;
                default:
                    System.out.println("\nTry Again.");
            }
        }

    }

    public static Candidate getCountingCandidate(ArrayList<Candidate> candidates) {
        Candidate c = null;
        while (c == null) {
            String name = getStringInput("\nWho is counting the votes?\nWrite @@ for a random one.\n>");
            if (name.equals("@@")) {
                name = candidates.get(new Random().nextInt(candidates.size())).un;
            }
            c = A3.getByUsername(name, candidates.toArray(new Candidate[0]));
        }
        return c;
    }

    public static ArrayList<Candidate> getWinners(ArrayList<Candidate> candidates) {

        int n = getIntInput("\nHow many times should we run the election?\n>");
        Candidate coutingC = getCountingCandidate(candidates);

        HashMap<Candidate, Integer> mp = new HashMap<Candidate, Integer>();

        for (int i = 0; i < n; ++i) {

            Candidate[] votes = new Candidate[candidates.size()];

            for (int j = 0; j < candidates.size(); ++j) {
                Candidate c = candidates.get(j);
                if (c == null)
                    continue;
                votes[j] = c.vote(candidates.toArray(new Candidate[0]));
            }

            Candidate winner = coutingC.selectWinner(votes);
            mp.put(winner, mp.getOrDefault(winner, 0) + 1);
        }

        ArrayList<Candidate> winners = new ArrayList<Candidate>();

        int mx = 0;
        for (Map.Entry<Candidate, Integer> it : mp.entrySet()) {
            int cnt = it.getValue();

            if (mx < cnt) {
                winners.clear();
                mx = cnt;
            }
            if (mx == cnt) {
                winners.add(it.getKey());
            }
        }

        return winners;

    }

    public static void solve() {

        while (true) {

            String opt = getStringInput("Would you like to?\na) Exit\nb) Run the same election many times\n>");

            switch (opt.toLowerCase()) {
                case "b":

                    ArrayList<Candidate> toSelectCandidates = getCandidates();

                    ArrayList<Candidate> winners = getWinners(toSelectCandidates);

                    System.out.println("\nWinners are: \n");
                    printCandidates(winners);
                    break;
                default:
                    return;
            }
        }

    }

    public static void main(String args[]) {
        solve();
    }

    public String getName() {
        return "ec22882A";
    }

    public String getSlogan() {
        return "1 2 3";
    }

    public Candidate vote(Candidate[] candidates) {
        if (candidates == null || candidates.length == 0) {
            return new Candidate_ec22882();
        }

        for (Candidate c : candidates) {

            if (c == null)
                continue;

            if (c.getSlogan().equals(getSlogan())) {
                return c;
            }
        }

        return candidates[0];
    }

    public Candidate selectWinner(Candidate[] candidates) {
        if (candidates == null || candidates.length == 0) {
            return new Candidate_ec22882();
        }

        String theOne = "";
        int maxVotes = 0;

        HashMap<String, Integer> mp = new HashMap<String, Integer>();

        for (Candidate c : candidates) {
            if (c == null)
                continue;
            String id = c.toString().split("@", 2)[0];
            int votes = mp.getOrDefault(id, 0);

            mp.put(id, votes + 1);

            if (maxVotes < votes + 1) {
                maxVotes = votes + 1;
                theOne = id;
            }
        }

        for (Candidate c : candidates) {
            if (c == null)
                continue;
            String id = c.toString().split("@", 2)[0];
            if (id.equals(theOne))
                return c;
        }
        return candidates[0];
    }

}
