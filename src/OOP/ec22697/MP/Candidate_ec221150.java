package OOP.ec22697.MP;

import java.util.Scanner;

class Candidate_ec221150 extends Candidate {

    static Candidate[] contributions = A3.getCandidateArray();
    static Candidate[] specificCandidates = new Candidate[contributions.length];
    static int cCount=0;

    private static void addByUn(String name, Candidate[] ca){

        try {
            if (proper(A3.getByUsername(name, contributions))) {
                ca[cCount] = A3.getByUsername(name, contributions);
                cCount++;
            }
        }catch (NullPointerException e) {
            System.out.println("Invalid username!");
        }
    }
    private static void addRandom (Candidate[] ca){
        int randN = (int)(Math.random()*contributions.length);
        if(proper(contributions[randN])){
            ca[cCount] = contributions[randN];
            cCount++;
        }
        else{
            addRandom(ca);
        }
    }

    private static boolean proper(Person p) {
        return !(p.getName().length() > 6 &&
                p.getName().startsWith("No name"));
    }
    public static void mainLoop(){
        Scanner scanner = new Scanner(System.in);

        boolean exit=false;
        String un;
        String vCounter;

        while(!exit){

            System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election? d)Exit");
            String choice = scanner.nextLine();

            switch (choice) {
                case "a" : {

                    System.out.println("Enter username: ");
                    un = scanner.nextLine();

                    try {
                        addByUn(un, specificCandidates);
                    } catch (NullPointerException e) {
                        System.out.println("Invalid username!");
                    }

                    System.out.println("Candidates are: ");
                    for(int k=0;k<cCount;k++) {
                        System.out.println((k + 1) + ". " + specificCandidates[k].getName());
                    }

                }
                case "b" : {
                    addRandom(specificCandidates);
                    System.out.println("Candidates are: ");
                    for (int i = 0; i < cCount; i++) {
                        System.out.println((i + 1) + ". " + specificCandidates[i].getName());
                    }
                }
                case "c" : {
                    try {
                        System.out.println("Who should count the votes? Enter username: \n");
                        vCounter = scanner.nextLine();
                        try {
                            Candidate c = A3.getByUsername(vCounter, A3.getCandidateArray());
                            if (c.getName() != null) {
                                System.out.println(c.getName() + " will count the votes!\n");
                                try {
                                    election(contributions, c);
                                } catch (NullPointerException e) {
                                    System.out.println("Election interrupted!");
                                }
                            }
                        }catch(NullPointerException e) {
                            System.out.println("Can't find a vote counter!\n");
                        }
                    }catch (NullPointerException e) {
                        System.out.println("Invalid username! \n");
                    }
                }
                case "d" : exit = true;
            }
        }
    }

    private static void election(Citizen[] voters, Candidate voteCounter) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many times shall we run the election?");
        try {
            int times = scanner.nextInt();

            try {
                Candidate[] votes = new Candidate[contributions.length];
                Candidate[] tempCandidateList = new Candidate[cCount];

                System.arraycopy(specificCandidates, 0, tempCandidateList, 0, cCount);

                Candidate[] winners = new Candidate[times];
                int contributorCount=0;
                String[] tempArr = new String[voters.length];
                for(int i=0;i<times;i++) {
                    int j = 0;
                    for (Citizen voter : contributions) {
                        try {
                            votes[j] = voter.vote(tempCandidateList);
                            tempArr[contributorCount] = "Votes: " + votes[j].getName();
                            j++;
                            contributorCount++;
                        } catch (Exception e) {
                            System.out.print("");
                        }
                    }
                    winners[i] = voteCounter.selectWinner(votes);

                    String[] validVotes = new String[contributorCount];
                    System.arraycopy(tempArr, 0, validVotes, 0, contributorCount);
                    contributorCount = 0;

                    for (String validVote : validVotes) {
                        System.out.print(validVote+"     ");
                    }

                    System.out.println("\n\nElection " + (i + 1) + " winner is "
                            + winners[i].getName() + " (" + winners[i].un + "): "
                            + winners[i].getSlogan() + "\n");

                }
            }catch (NullPointerException e) {
                System.out.println("Election problem");
            }

        }catch (Exception e) {
            System.out.println("Enter an integer!");
        }




    }

    public static void main (String[] args){

        mainLoop();

    }

    public String getName() {
        return "Dzhengiz";
    }

    public String getSlogan() {
        return "More trees!";
    }

    public Candidate vote(Candidate[] candidates) {

        // If array is empty, return instance of friend's class.
        if (candidates.length == 0)
            return new Candidate_ec22415();

        // Search for a like-minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("More trees!"))
                return c;

        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Piotr"))
                return c;

        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }

    public Candidate selectWinner(Candidate[] votes) {

        // If array is empty, return instance of friend's class.
        if (votes.length == 0)
            return new Candidate_ec22415();

        Candidate currentWinner = new Candidate_ec221150();

        // Count the votes for each object in the array,
        // selecting one with the most.
        int highestCount = 0;
        for (Candidate c : votes) {

            int count = 0;
            for (Candidate x : votes){
                if (x == c){
                    count++;
                }
                if (count > highestCount) {
                    highestCount = count;
                    currentWinner = c;
                }
            }
        }

        return currentWinner;
    }

}
