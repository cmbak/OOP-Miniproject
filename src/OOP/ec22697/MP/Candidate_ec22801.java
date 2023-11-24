package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

class integerAndStringArray {
    String[] stringArray;
    Integer[] intArray;
}
class Candidate_ec22801 extends Candidate {
    
    private static Scanner sc() {return new Scanner(System.in);}

    private static void pr(String q) {System.out.println(q);}
    
    private static String getString(String q) {
        pr(q);
        return sc().nextLine();
    }

    private static char getChar(String q) {
        pr(q);
        String t = sc().nextLine(); 
        return ( t.length()==0 ? ' ' : t.charAt(0) );   
        //return sc().nextLine().charAt(0);
    }

    private static boolean proper(Person p) {
        return !(p.getName().length() > 6 &&
                   p.getName().substring(0,7).equals("No name"));
    }

    public String getName() {
        return "Viet";
    }

    public String getSlogan() {
        return "Tax the rich!";
    }

    private static Pattern isNum = Pattern.compile("\\d+");    
    private static int getInt(String q) {
        pr(q);
        String t = sc().nextLine(); 
        return ( !isNum.matcher(t).matches() ? 0 : Integer.parseInt(t) );
        //return sc().nextInt(); 
    }

    private static int getLimitInt(int limit) {
        int returnTemp;
        pr("How many candidates? (1 to " + limit + ")");
        while (true) {
            try {
                returnTemp = sc().nextInt();
                // If else statement to verify whether a number is between 1 and 4 or not
                if (returnTemp >= 1 && returnTemp <= limit) {
                    break;
                } else {
                    // If the input is not between 1 and 4, it will ask the user to reenter the input
                    pr("The number of candidates outside the range. Please reenter: ");
                }
            } catch (Throwable e) {
                // If the input is not an integer, it will ask the user to reenter the input
                pr("Invalid input. Please reenter: ");
                sc().nextLine();
            }
        }
        //sc1.close();
        return returnTemp;
    }

    private static Candidate[] randomCandidates(int number, Candidate[] ca) {
        
        int seed = getInt("Enter random seed or 0 for no seed. (The same seed will produce the same candidates.)");
        
        Candidate[] candidates = new Candidate[number];
        
        Random rs = ( seed == 0 ? new Random() : new Random(seed));
        
        pr("The candidates are " + number);
        for (int i=0;i<number;i++) {
            do { candidates[i] = ca[rs.nextInt(ca.length)];}
            while (!proper(candidates[i]));
            
            pr(candidates[i].getName());
        } 
        return candidates;
    }
    
    public static Candidate[] candidateOption() {
        Candidate[] listCandidates = A3.getCandidateArray();
        Character candidateSelection = getChar("Do you want to \n(a) select specific candidates for the election \n(b) or all candidates for the election");
        boolean gotDetails = false;
        while (gotDetails==false) {
            switch (candidateSelection) {
                case 'a':
                    int numberCandidates = getInt("How many candidates you wish to be in the election?");
                    listCandidates = new Candidate[numberCandidates];
                    for (int i=0;i<numberCandidates;i++) {
                        listCandidates[i] = A3.getByUsername(getString("Enter username of candidate "+(i+1)), A3.getCandidateArray());
                    }
                    gotDetails = true;
                    break;
                
                case 'b':
                    listCandidates = A3.getCandidateArray();
                    gotDetails = true;
                    break;
                
                default: pr("Option '"+candidateSelection+"' not available."); candidateSelection = getChar("Do you want to \n(a) select specific candidates for the election \n(b) or all candidates for the election");
            }
        }
        return listCandidates;
    }

    public Candidate vote(Candidate[] candidates) {
        
        // If array is empty, return instance of friend's class.
        if (candidates.length == 0) {
            // Randomly choose between three options.
            Random r = new Random();
            int c = r.nextInt(3);
            if (c == 0)
                return new Candidate_ec22801();
            else if (c == 1)
                return new Candidate_ec22802();
            else
                return new Candidate_ec22804();
        }
        
        Random r = new Random();
        int randomPos = r.nextInt(candidates.length);
        Candidate slogans = candidates[randomPos];
        randomPos = r.nextInt(candidates.length);
        Candidate secondSlogan = candidates[randomPos];
        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals(slogans.getSlogan())) {
                return c;
            } else if (c.getSlogan().equals(secondSlogan.getSlogan())) {
                return c;
            }
        
        randomPos = r.nextInt(candidates.length);
        Candidate friends = candidates[randomPos];
        randomPos = r.nextInt(candidates.length);
        Candidate secondFriends = candidates[randomPos];
        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals(friends.getName())) {
                return c;
            } else if (c.getName().equals(secondFriends.getName())) {
                return c;
            }
          
        // Otherwise, default to last candidate on list.
        return candidates[candidates.length - 1];
    }

    public Candidate selectWinner(Candidate[] votes) {

        // If array is empty, return instance of this class.
        if (votes.length == 0) {
            Random r = new Random();
            int c = r.nextInt(2);
            if (c == 0)
                return new Candidate_ec22802();
            else
                return new Candidate_ec22804();
        }

        // Default to first vote, but this will be over-written.
        Candidate currentWinner = new Candidate_ec22801();

        // Count the votes for each object in the array,
        // selecting one with the most.
        int highestCount = 0;
        for (Candidate c : votes) {

            int count = 0;
            for (Candidate x : votes)
                if (x == c)
                    count++;
            if (count > highestCount) {
                highestCount = count;
                currentWinner = c;
            } else if (count == highestCount) {
                // If there is a tie, choose at random.
                Random r = new Random();
                if (r.nextBoolean()) {
                    highestCount = count;
                    currentWinner = c;
                }
            }
        }
        if (highestCount < 3) {
            Random r = new Random();
            int c = r.nextInt(3);
            if (c == 0)
                return new Candidate_ec22801();
            else if (c == 1)
                return new Candidate_ec22802();
            else
                return new Candidate_ec22804();
        }
        return currentWinner;
    }

    private static Candidate[] randomElection(Candidate[] listCandidates, Citizen[] voters, int totalOfCandidates) {

        int number = getLimitInt(totalOfCandidates);
        Candidate[] candidates = randomCandidates(number, listCandidates);

        Candidate[] ballotBox = new Candidate[voters.length];
        for (int i=0;i<voters.length;i++) {
            // handling java.lang.ArrayIndexOutOfBoundsException
            Candidate v = null;
            try {
                try {
                    v = voters[i].vote(candidates);
                } catch (Throwable e) {
                    ;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                ;
            }
            
            try {
                if (v == null) {
                    int c = new Random().nextInt(100);
                    v = voters[c].vote(candidates);
                }
                System.out.println(voters[i].getName() +" voted for " + v.getName() +
                                  ", whose slogan is '" + v.getSlogan() + "'.");
                ballotBox[i] = v; 
            } catch (Exception e) {
                ;
            }
        }

        return ballotBox;
    }

    public static void displayCandidates(Candidate[] candidates) {
        for (int i = 0; i < candidates.length; i++) {
            System.out.println("[" + i + "] Candidate: " + candidates[i].getName() + " ("+candidates[i].un+")"+ ", slogan for the campaign: "
                    + candidates[i].getSlogan());
        }
    }
    
    public static int checkTie(Integer[] candidateCount) {
        int numberOfWinners = 0;
        for (int i = 0; i < candidateCount.length; i++) {
            if (candidateCount[i] == candidateCount[0])
                numberOfWinners++;
        }
        return numberOfWinners;
    }

    public static void selectWinnerByUser(Candidate[] ballotBox, Candidate voters) {
        boolean exits = false;
        do {
            try {
                try {
                    Person winner = voters.selectWinner(ballotBox);
                    System.out.println(voters.getName() + " says " + winner.getName() +
                                " wins the election.");
                    exits = true;
                } catch (Throwable e) {
                    pr("This user experienced an error. Please try again.");
                    exits = true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                pr("This user experienced an error. Please try again.");
                exits = true;
            }
        } while (!exits);
    }

    public static void selectWinnerFromAll(Candidate[] ballotBox, Citizen[] voters) {
        for (int i = 0; i < voters.length; i++) {
            Person winner = null;
            try {
                try {
                    winner = voters[i].selectWinner(ballotBox);
                } catch (Throwable e) {
                    ;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                ;
            }
            if (winner == null) {
                ;
            } else {
                System.out.println(voters[i].getName() + " says " + winner.getName() +
                        " wins the election.");
            }
        }
    }

    public static void displayVoteResult (String[] candidateName, Integer[] candidateCount) {
        // Display the results.
        for (int i = 0; i < candidateName.length; i++) {
            System.out.println(candidateName[i] + " received " + candidateCount[i] + " votes.");
        }
    }

    public static void getPopularSlogan(Candidate[] ballotBox, Candidate[] listOfCandidates, int numberOfWinners) {
        // Display which slogan was the most popular.
        System.out.println("");
        System.out.println("Which slogan was the most popular?");
        String[] sloganName = new String[listOfCandidates.length];
        Integer[] sloganCount = new Integer[listOfCandidates.length];
        for (int i = 0; i < listOfCandidates.length; i++) {
            System.out.println("Processing slogan " + listOfCandidates[i].getSlogan() + "...");
            int count = 0;
            for (int j = 0; j < ballotBox.length; j++) {
                try {
                    if (listOfCandidates[i].getSlogan().equals(ballotBox[j].getSlogan()))
                    count++;
                } catch (Exception e) {
                    ;
                }
            
            }

            // Add count and slogan name to each list, make sure the count is in order descending, handling null
            // values.
            for (int z = 0; z < sloganCount.length; z++) {
                if (sloganCount[z] == null) {
                    sloganCount[z] = count;
                    sloganName[z] = listOfCandidates[i].getSlogan();
                    break;
                } else if (count > sloganCount[z]) {
                    for (int y = sloganCount.length - 1; y > z; y--) {
                        sloganCount[y] = sloganCount[y - 1];
                        sloganName[y] = sloganName[y - 1];
                    }
                    sloganCount[z] = count;
                    sloganName[z] = listOfCandidates[i].getSlogan();
                    break;
                }
            }
        }

        System.out.println("");
        String mostPopularSlogan = "";
        if (numberOfWinners == 1) {
            mostPopularSlogan = "The slogan '" + sloganName[0] + "' was the most popular.";
            System.out.println(mostPopularSlogan);
        } else {
            for (int i = 0; i < numberOfWinners; i++) {
                mostPopularSlogan = mostPopularSlogan + "'" + sloganName[i] + "' ";
            }
            System.out.println("Slogans " + mostPopularSlogan + "were the most popular.");
        }
    }

    public static integerAndStringArray countingVote(Candidate[] ballotBox, Candidate[] listOfCandidates) {
        String[] candidateName = new String[listOfCandidates.length];
        Integer[] candidateCount = new Integer[listOfCandidates.length];
        for (int i = 0; i < listOfCandidates.length; i++) {
            System.out.println("Processing votes for candidate " + listOfCandidates[i].getName() + "...");
            int count = 0;
            for (int j = 0; j < ballotBox.length; j++) {
                try {
                    if (listOfCandidates[i].getName().equals(ballotBox[j].getName()))
                    count++;
                } catch (Exception e) {
                    ;
                }
            }

            // Add count and candidate name to each list, make sure the count is in order descending, handling null
            // values.
            for (int z = 0; z < candidateCount.length; z++) {
                if (candidateCount[z] == null) {
                    candidateCount[z] = count;
                    candidateName[z] = listOfCandidates[i].getName();
                    break;
                } else if (count > candidateCount[z]) {
                    for (int y = candidateCount.length - 1; y > z; y--) {
                        candidateCount[y] = candidateCount[y - 1];
                        candidateName[y] = candidateName[y - 1];
                    }
                    candidateCount[z] = count;
                    candidateName[z] = listOfCandidates[i].getName();
                    break;
                }
            }
        }
        integerAndStringArray result = new integerAndStringArray();
        result.intArray = candidateCount;
        result.stringArray = candidateName;
        return result;
    }

    public static void whoTellsTheTruth(Candidate[] ballotBox, Citizen[] voters, int numberOfWinners, String[] candidateName) {
        for (int i = 0; i < voters.length; i++) {
            try {
                if (numberOfWinners > 1) {
                    for (int j = 0; j < numberOfWinners; j++) {
                        if (voters[i].selectWinner(ballotBox).getName().equals(candidateName[j])) {
                            System.out.println(voters[i].getName() + " is telling the truth.");
                            break;
                        } else if (j == numberOfWinners - 1) {
                            System.out.println(voters[i].getName() + " is lying.");
                        }
                    }
                } else if (voters[i].selectWinner(ballotBox).getName().equals(candidateName[0])) {
                    System.out.println(voters[i].getName() + " is telling the truth.");
                } else {
                    System.out.println(voters[i].getName() + " is lying.");
                }
            } catch (Exception e) {
                System.out.println(voters[i].getName() + " is lying.");
            }
        }
    }

    public static Candidate getByUsername(String name, Candidate[] ca) {
        for (Candidate c : ca) if (c.un.equals(name)) return c;
        return null;
    }

    public static void displayAllVoters (Citizen[] voters) {
        for (int i = 0; i < voters.length; i++) {
            System.out.println("[" + i + "] " + voters[i].getName());
        }
    }

    public static void main(String[] args) {
        Citizen[] voters = A3.getCandidateArray();
        Candidate[] listCandidates = candidateOption();
        System.out.println("List of candidates:");
        displayCandidates(listCandidates);
        Candidate[] ballotBox = new Candidate[0];
        int numberOfWinners = 0;
        String[] candidateName = new String[listCandidates.length];
        Integer[] candidateCount = new Integer[listCandidates.length];
        boolean ranElection = false;
        integerAndStringArray result = new integerAndStringArray();

        boolean exit = false;
        char choice = '?';
        do {
          choice = getChar("\nChoose election type: \na) no election (exit) " + 
                           "\nb) run an election " + 
                           "\nc) display candidates " + 
                           "\nd) select winner by user " +
                           "\ne) select winner from all " +
                           "\nf) display vote result " +
                           "\ng) get popular slogan " + 
                           "\nh) who tells the truth" +
                           "\ni) display all voters ");

          switch (choice) {
                  
            case 'a': exit = true; break;
                  
            case 'b': ballotBox = randomElection(listCandidates, voters, listCandidates.length);
            ranElection = true;
            result = countingVote(ballotBox, listCandidates);
            candidateName = result.stringArray;
            candidateCount = result.intArray;
            numberOfWinners = checkTie(candidateCount);
            break;

            case 'c': displayCandidates(listCandidates); break;

            
            case 'g': if (ranElection) {
                getPopularSlogan(ballotBox, listCandidates, numberOfWinners);
                break;
            } else {
                System.out.println("No election has been run yet.");
                break;
            }

            case 'd': if (ranElection) {
                Candidate userSelected = getByUsername(getString("Enter username: "), listCandidates);
                selectWinnerByUser(ballotBox, userSelected);
                break;
            } else {
                System.out.println("No election has been run yet.");
                break;
            }

            case 'e': if (ranElection) {
                selectWinnerFromAll(ballotBox, voters);
                break;
            } else {
                System.out.println("No election has been run yet.");
                break;
            }
            
            case 'i': displayAllVoters(voters); break;
            
            case 'h': whoTellsTheTruth(ballotBox, voters, numberOfWinners, candidateName);
            break;

            case 'f': if (ranElection) {
                displayVoteResult(candidateName, candidateCount);
                break;
            } else {
                System.out.println("No election has been run yet.");
                break;
            }

            default: pr("Option '"+choice+"' not available."); choice = getChar("Do you want to \n(a) select specific candidates for the election \n(b) or all candidates for the election");
          }
        } while (!exit);
    }

}
