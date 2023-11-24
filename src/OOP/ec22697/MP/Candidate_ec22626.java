package OOP.ec22697.MP;// File Candidate_ec22626.java
//
// Machine generated stub for Assignment 2


import java.util.*;

class Candidate_ec22626 extends Candidate {
    String name;

    public String getName() {
        return "HH";
    }

    public String getSlogan() {
        return "We go jim";
    }

    public Candidate vote(Candidate[] candidates) {
        boolean found = false;
        Candidate returnVal = new Candidate_ec22626();

        if (candidates.length == 0) {
            return new Candidate_ec22626();
        }

        for (Candidate x : candidates) {
            if (x.getName().equals("Naveed")) {
                found = true;
                returnVal = x;
            }
        }
        if (found == true) {
            return returnVal;
        } else {
            for (Candidate c : candidates) {

                if (c.getSlogan().equals("vote for me"))
                    returnVal = c;

            }
            return returnVal;

        }

    }

    public Candidate selectWinner(Candidate[] votes) {
        if (votes.length == 0) {
            return new Candidate_ec22626();
        }

        Candidate currentWinner = votes[0];

        int highestCount = 0;
        for (Candidate x : votes) {
            int count = 0;

            for (Candidate y : votes) {
                if (x == y) {
                    count++;
                }

                if (count > highestCount) {
                    highestCount = count;
                    currentWinner = y;
                }

            }

        }
        return currentWinner;
    }

    public static void main(String[] args) {

        Candidate[] candidates = getCandidateArray();
        Candidate[] storeCandidates = new Candidate[candidates.length];

        //Candidate input = new Candidate_ec22626();

        int valAdded = 0;

        boolean running = true;
        while (running && valAdded <= candidates.length) {

            System.out.println("Candidates are: ");
            if (valAdded > 0) {
                for (int z = 0; z < valAdded; z++) {
                    System.out.println((z + 1) + ") " + storeCandidates[z].getName() + ",");

                }
            } else {
                System.out.println("none");
            }
            String ans = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");

            if (ans.equals("a")) {
                String username = inputString("Please enter a username.");
                boolean found = false;
                for (int j = 0; j < candidates.length; j++) {
                    if (candidates[j].un.equals(username)) {
                        System.out.println("found");
                        storeCandidates[valAdded] = candidates[j];
                        found = true;
                        // break;
                    }
                }
                if (found) {
                    System.out.println("Candidate added: " + storeCandidates[valAdded].un);
                } else if (!candidates[valAdded].un.equals(username)) {
                    System.out.println("Username not found.");
                    valAdded--;
                }

            }

            else if (ans.equals("b")) {

                Random rs = new Random();
                int randomNum = rs.nextInt(valAdded + 1);

                if (candidates[randomNum] != null)

                {
                    storeCandidates[valAdded] = candidates[randomNum];
                    candidates = shiftMeth(randomNum, candidates);

                }

            } else if (ans.equals("c")) {

                String count_votes = inputString("Who should count the votes?");
                int election_run = inputInt("How many times shall we run the election?");
                Candidate[] winners = new Candidate[election_run];
                Candidate count = storeCandidates[0];
                for (int i = 0; i < storeCandidates.length; i++) {
                    if (count_votes.equals(storeCandidates[i].un)) {
                        count = storeCandidates[i];
                    }

                }
                for (int i = 0; i < election_run; i++) {
                    winners[i] = count.selectWinner(storeCandidates);

                }
                commonAns(winners);

                running = false;

            }
            valAdded++;

        }
    }

    public static void commonAns(Candidate[] store) {
        Candidate currentWinner = store[0];

        int highestCount = 0;
        for (int i = 0; i < store.length; i++) {
            int count = 0;

            for (int j = 0; j < store.length; j++) {
                if (store[j] == store[i]) {
                    count++;
                }

                if (count > highestCount) {
                    highestCount = count;
                    currentWinner = store[i];
                }

            }
            
        }
        System.out.println("Most common winner is " + currentWinner.getName() + ". There were no other winners.");
    }

    static <T> T[] shiftMeth(int RandomVal, T[] arr) 
    {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[RandomVal] == arr[i]) {
                    T temporaryVal = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temporaryVal;
                    RandomVal++;

                }

            }

        }
        return arr;
    }


    

    public static int inputInt(String message) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(message);
        return scanner.nextInt();
    }
    public static String inputString (String message) 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        String x;
        x = scanner.nextLine();return x;
    }

    /*public String getUsername() {
        return name;
    }

    public void setUsername(String nm) {
        name = nm;

    }*/

    /*public Candidate creatorMethod(String nm) {
        Candidate_ec22626 player = new Candidate_ec22626();

        player.setUsername(nm);

        return player;

    }*/

    public static Candidate[] getCandidateArray() {

        Candidate[] ca = new Candidate[2];
        ca[0] = new Candidate_ec22837();
        ca[0].un = "ec22837";
        ca[1] = new Candidate_ec22626();
        ca[1].un = "ec22626";

        return ca;

    }
}


