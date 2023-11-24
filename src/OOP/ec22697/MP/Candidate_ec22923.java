package OOP.ec22697.MP;/*
 FEATURES:
 - Create a new candidate list, from which you can add candidates and run elections from
 - Two types of election: "Most common winner, with chosen vote counter" and "Most common winner, with random vote counter"
 - Toggle option for showing 'no name' candidates
 - Effective use of a HashMap in method consolidateWinners
 - Bug Free!!(i hope)
 */

import java.util.*;

class Candidate_ec22923 extends Candidate {

    public String getName() {
        return "Otto";
    }

    public String getSlogan() {
        return "racecar";
    }

    public static void main(String[] args) {
        Candidate[] allContributions = A3.getCandidateArray();
        Candidate[] chosenContributions = new Candidate[]{};
        boolean toggleNoNames = true;
        boolean exit = false;

        while(!exit) {
            char choice = validateChar("Would you like to" +
                    "\n a) Check your candidate list" +
                    "\n b) Check default candidates list" +
                    "\n c) Add a candidate to your candidate list" +
                    "\n d) Run election [most common winner, with chosen vote counter]" +
                    "\n e) Run election [most common winner, with random vote counter]" +
                    "\n f) Show 'no name' candidates (currently "+toggleNoNames+")" +
                    "\n g) EXIT\n", new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g'});

            switch(choice) {
                case 'a' : checkCandidateList(chosenContributions);break;
                case 'b' : checkCandidateList(allContributions);break;
                case 'c' : chosenContributions = addCandidate(chosenContributions, allContributions);break;
                case 'd' :
                    Candidate[] chosenContributionArray = chooseCandidateArray(allContributions, chosenContributions);
                    String voteCounter = validateCandidate("Who should be the vote counter?",chosenContributionArray);
                    runElection(chosenContributionArray, voteCounter, toggleNoNames);break;
                case 'e' :
                    Candidate[] pickContributionArray = chooseCandidateArray(allContributions, chosenContributions);
                    Random random = new Random();
                    int randomIndex = random.nextInt(pickContributionArray.length);
                    String randomVoteCounter = pickContributionArray[randomIndex].un;
                    System.out.println("\n ////// Your random voter is: "+randomVoteCounter + "//////\n");
                    runElection(pickContributionArray, randomVoteCounter, toggleNoNames);
                    break;
                case 'f' : toggleNoNames = !toggleNoNames;break;
                case 'g' : exit = true;break;
            }
        }


    }

    private static void checkCandidateList(Candidate[] candidateArray) {
        if(candidateArray.length == 0) {
            System.out.println("\n --- Your list is empty! --- \n");
        }
        for (int i = 0; i < candidateArray.length; i++) {
            System.out.println(candidateArray[i] + "\t --- Name: "+candidateArray[i].getName()+ "\t | Slogan: "+candidateArray[i].getSlogan());
        }
    }

    private static char validateChar(String message, char[] options) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();

            if(input.length() == 1){
                for (char option : options) {
                    if (option == input.charAt(0)) {
                        return input.charAt(0);
                    }
                }
            }
            System.out.print("\n !!! Invalid input, options are: ");
            for (int i = 0; i < options.length; i++) {
                if (i == options.length - 1) {
                    System.out.print("or " + options[i] + ". !!!\n\n ");
                } else {
                    System.out.print(options[i] + ", ");
                }
            }
        }
    }



    public static int validateInt(String message) {
        Scanner scanner = new Scanner(System.in);
        int userInput;
        while (true) {
            try {
                System.out.print(message);
                userInput = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
        return userInput;
    }

    private static String validateCandidate(String message, Candidate[] candidateArray) {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println(message);
            String input = scanner.nextLine();
            for(Candidate candidate : candidateArray) {
                if(candidate.un.equals(input)) {
                    return input;
                }
            }

            System.out.println("Invalid input, please input a valid candidate name that exists in the chosen candidate list. " +
                    "Example(first candidate from your chosen list): "+candidateArray[0].un);
        }

    }

    private static Candidate[] chooseCandidateArray(Candidate[] defaultList, Candidate[] theirList) {
        while(true)
        {
            char chosenArray = validateChar("Which candidate list would you like to use?" +
                    "\n a) Default candidate list" +
                    "\n b) Your candidate list\n", new char[]{'a','b'});
            if(chosenArray == 'a') {
                return defaultList;
            }
            else if(chosenArray == 'b' && theirList.length == 0){
                System.out.println("\n !!! Your candidate list is empty, please try again. !!!\n");
            }
            else if(chosenArray == 'b') {
                return theirList;
            }
        }

    }

    private static Candidate[] addCandidate(Candidate[] userCandidates, Candidate[] allCandidates) {
        // Use validateCandidate to get a valid candidate name from the user
        String validatedCandidate = validateCandidate("Enter the username of the candidate to add:", allCandidates);

        // Check if the candidate already exists in the userCandidates array
        for (Candidate candidate : userCandidates) {
            if (candidate.un.equals(validatedCandidate)) {
                System.out.println("\n !!! Candidate with username " + validatedCandidate + " already exists in your Candidates list. !!! \n");
                return userCandidates;
            }
        }

        // Search for the candidate with the specified username in the allCandidates array
        for (Candidate candidate : allCandidates) {
            if (candidate.un.equals(validatedCandidate)) {
                // Found the candidate - add them to the userCandidates array
                Candidate[] newUserCandidates = Arrays.copyOf(userCandidates, userCandidates.length + 1);
                newUserCandidates[newUserCandidates.length - 1] = candidate;
                return newUserCandidates;
            }
        }

        return userCandidates;
    }

    private static void runElection(Candidate[] candidateArray, String voteCounter, boolean toggleNoNames) {
        if(candidateArray.length == 0) {
            System.out.println("Your candidate list is empty, no election can be run, please add candidates through option a.");
        }
        int numOfElectionsToRun = validateInt("How many times do you want to run the election?\n");

        Candidate[] winners = new Candidate[numOfElectionsToRun];
        Candidate[] arrayOfVotedCandidates = new Candidate[candidateArray.length];

        // Repeat the election based on the given parameter
        for (int i = 0; i < numOfElectionsToRun; i++) {
            for(int j = 0; j < candidateArray.length; j++) {
                if(candidateArray[j].un.equals("ex20539") || candidateArray[j].un.equals("ec22578")){
                    continue;
                }

                try {
                    Candidate votedCandidate = candidateArray[j].vote(candidateArray);
                    arrayOfVotedCandidates[j] = votedCandidate;
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            }

            // Use the given vote counter to select the winner
            Candidate winner = Objects.requireNonNull(A3.getByUsername(voteCounter, candidateArray)).selectWinner(candidateArray);
            winners[i] = winner;
        }


        System.out.println("\n === ALL WINNERS ===");
        for (int i = 0; i < winners.length; i++) {
            if(!toggleNoNames && winners[i].getName().contains("No name (")) {
                System.out.println("Removed: "+winners[i].getName() + " on election number "+(i+1));
                continue;
            }
            System.out.println("Winner[from election "+(i+1)+"]"+ "\t --- Name: "+winners[i].getName()+ "\t | Slogan: "+winners[i].getSlogan());
        }
        System.out.println();
        consolidateWinners(winners);
    }




    private static void consolidateWinners(Candidate[] winners) {
        if (winners.length == 0) {
            return;
        }

        Map<Candidate, Integer> candidateCount = new HashMap<>();

        // Count occurrences of each candidate in the winners array
        for (Candidate candidate : winners) {
            if (!candidateCount.containsKey(candidate)) {
                candidateCount.put(candidate, 1);
            } else {
                candidateCount.put(candidate, candidateCount.get(candidate) + 1);
            }
        }

        // Find the candidate with the highest count
        Candidate mostCommonWinner = null;
        int mostCommonWinnerCount = 0;
        for (Map.Entry<Candidate, Integer> entry : candidateCount.entrySet()) {
            if (entry.getValue() > mostCommonWinnerCount) {
                mostCommonWinner = entry.getKey();
                mostCommonWinnerCount = entry.getValue();
            }
        }

        Candidate[] consolidatedWinners = new Candidate[1];
        consolidatedWinners[0] = mostCommonWinner;

        System.out.println("=== MOST COMMON WINNER === \n" +
                " --- Name : "+consolidatedWinners[0].getName() + "\t | Total wins = 1\n\n");
        if(mostCommonWinnerCount == 1) {
            System.out.println("There were no duplicate winners, so the most recent election with 1 win was picked.\n\n");
        }
    }



    public Candidate vote(Candidate[] candidates) {

        // If array is empty, return instance of friend's class.
        if (candidates.length == 0)
            return new Candidate_ec22578();

        // String slogan = c.getSlogan().toLowerCase();
        // String name = c.getName().toLowerCase();
        String reverseString = "";

        // Search for a slogan that's also a palindrome
        for (Candidate c : candidates)
        {
            for (int i = c.getSlogan().length() - 1; i >= 0; i--)
            {
                reverseString += c.getSlogan().toLowerCase().charAt(i);
            }
            if (reverseString.equals(c.getSlogan()))
            {
                return c;
            }
        }


        // Otherwise, search for a name that's a palindrome
        for (Candidate c : candidates)
        {
            for (int i =c.getName().length() - 1; i >= 0; i--)
            {
                reverseString += c.getName().toLowerCase().charAt(i);
            }
            if (reverseString.equals(c.getName()))
            {
                return c;
            }
        }

        // Otherwise, default to first candidate on list.
        return candidates[0];
    }

    public Candidate selectWinner(Candidate[] votes) {

        // If array is empty, return instance of friend's class.
        if (votes.length == 0)
            return new Candidate_ec22578();

        Candidate evenWinner = votes[0]; // default winner
        int highestVotes = 0;

        for(int i = 0; i < votes.length; i++) {
            int numVotes = 0;
            for(int j = 0; j < votes.length; j++) {
                if(votes[j] == votes[i]) {
                       numVotes++;
                }
            }
            if(numVotes > highestVotes) {
                highestVotes = numVotes;
                if(highestVotes % 2 == 0) // if the number of votes is an even number, then current winner
                {
                    evenWinner = votes[i];
                }
            }
        }


        return evenWinner;
    }

}
