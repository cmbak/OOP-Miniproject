package OOP.ec22697.MP;// File Candidate_ex21566.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

class Candidate_ex21566 extends Candidate {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        char choice;
        boolean exit = false;

        do {
            displayChoices();
            System.out.print(">");
            choice = scanner.nextLine().charAt(0);

            switch (choice) {
                case 'a':
                    exit = true;
                    break;

                case 'b':
                    b();
                    break;

                case 'c':
                    System.out.println("Not implemented.");
                    break;

                case 'd':
                    System.out.println("Not implemented.");
                    break;

                case 'e':
                    System.out.println("Not implemented.");
                    break;

                case 'f':
                    System.out.println("Not implemented.");
                    break;
                default:
                    System.out.println("Not a choice.");
                    break;
            }
        } while(!exit);

        scanner.close();
        System.out.println("Exiting program...");
    }

    public static String getAnswer(String question) {
        System.out.println(question);
        System.out.print(">");
        return scanner.nextLine();
    }

    public static void displayChoices() {
        System.out.println();
        System.out.println("Would you like to\n" +
                "a) exit\n" +
                "b) run same election many times\n" +
                "c) check who counts honestly\n" +
                "d) run all possible two-candidate elections\n" +
                "e) run a higher-order election\n" +
                "f) ..."
        );
    }

    public static void b() {
        Vector<Candidate> candidatesInPool = new Vector<>();
        Candidate candidates[] = A3.getCandidateArray();
        Candidate candidate;
        String username;
        char choice;
        
        System.out.println("\n= Running Repeated Elections =");

        do {
            displayCandidates(candidatesInPool);

            choice = getAnswer("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?").charAt(0);

            switch (choice) {
                case 'a':
                    username = getAnswer("Please enter a username.");
                    candidate = A3.getByUsername(username, candidates);
                    
                    if (candidate != null) {
                        if (!candidatesInPool.contains(candidate)) {
                            candidatesInPool.add(candidate);
                        } else System.out.println(candidate.getName() + " is already on the pool.");
                    } else {
                        System.out.println("Candidate is on the list of possible candidates.");
                    }
                    break;
                    
                case 'b':
                    while (true) {
                        int index = new Random().nextInt(candidates.length);
                        candidate = candidates[index];

                        if (candidatesInPool.contains(candidates[index])) 
                            System.out.println(candidate.getName() + " is already on the pool.");
                        else {
                            candidatesInPool.add(candidate);
                            break;
                        }
                    }
                    break;
                
                case 'c':
                    username = getAnswer("Who should count the votes?");
                    Candidate candidateToCount = A3.getByUsername(username, candidates);
                    String numOfTimesStr = getAnswer("How many times shall we run the election");

                    if (candidateToCount != null && isInt(numOfTimesStr)) {
                        int numOfTimes = Integer.parseInt(numOfTimesStr);
                        if (numOfTimes != 0) {

                            Candidate winners[] = new Candidate[numOfTimes];

                            for (int i = 0; i < numOfTimes; i++) {
                                Candidate votes[] = new Candidate[candidates.length];
                                System.out.println(votes.length);
                                for (int j = 0; j < votes.length; j++) {
                                    votes[j] = candidates[j].vote(candidatesInPool.toArray(new Candidate[candidatesInPool.size()]));
                                    System.out.println(j);
                                }

                                Candidate winner = candidateToCount.selectWinner(votes);
                                winners[i] = winner;
                            }

                            Candidate finalWinners[] = allMostFrequent(winners, new Candidate[0]);

                            if (finalWinners.length == 1) {
                                System.out.println("Most common winner is" + finalWinners[0].getName());
                                System.out.println("There were no other winners.");
                            } else {
                                System.out.println("There were " + finalWinners.length + " winners.");
                                for (int i = 0; i < finalWinners.length; i++) {
                                    System.out.println((i + 1) + ". " + finalWinners[i]);
                                }
                            }
                        }
                    }
                    break;

                default:
                    System.out.println("Not a choice.");
                    break;
            }

        } while(choice != 'c');
    }

    public static void displayCandidates(Vector<Candidate> candidates) {
        System.out.println("Candidates are");

        if (candidates.size() == 0) System.out.println("None");
        else 
            for (int i = 0; i < candidates.size(); i++) 
                System.out.println((i + 1) + ". " + candidates.get(i).getName());        
    }

    public static boolean isInt(String a) {
        for(char c : a.toCharArray()) {
            if(!Character.isDigit(c))
                return false;
        }
        return true;
    }
    
    @Override
    public String getName() { // Returns a name
        return "David J";
    }

    @Override
    public String getSlogan() { // Returns a slogan
        return "No more university fees!";
    }

    @Override // tries to vote a candidate from the array following some criteria.
    public Candidate vote(Candidate candidates[]) {

        if (candidates.length == 0) return new Candidate_ex21423();  // if array empty return a friend's new candidate object.


        for (Candidate c: candidates) {
            // trying to vote for someone who cares for trees.
            if (c.getSlogan().equals("More trees!"))
                return c;
        }

        for (Candidate c: candidates) {
            // trying to vote for a friend.
            if (c.getName().equals("Yash"))
                return c;
        }


        return new Candidate_ex21423();  // if array empty return a friend's new candidate object.
    }

    @Override // Select a candidate from the array argument with the most votes.
    public Candidate selectWinner(Candidate votes[]) {

        if (votes.length == 0) return new Candidate_ex21423();
        
        Candidate mostFrequentCandidates[] = allMostFrequent(votes, new Candidate[0]);
        
        // if the array of most frequent candidates contains more than one candidate, then return one of them at random.
        return mostFrequentCandidates[new Random().nextInt(mostFrequentCandidates.length)];
    }

    // Returns array containing the number of occurrences of each object of type T.
    private static <T> int[] eachNumberOfTimes(T[] a) {
        int[] r = new int[a.length];
        
        // Loop that calls numberOfTimes.
        for (int i = 0; i < a.length; i++) {
            int counter = 0;
            for (int j = 0; j < a.length; j++) 
                if (a[i] == a[j]) counter++;
            
            r[i] = counter;
        }
        
        return r;
    }

    // Returns an array with the most with the most occurrences of type T.
    private static <T> T[] allMostFrequent(T[] a, T[] dummy) { // dummy should have length zero.

        // Don't have to know in advance how long a Vector is.
        Vector<T> r = new Vector<T>(); // Compiles without knowing type T in advance. 
        
        int numOfTimes[] = eachNumberOfTimes(a);
        int maxCount = 0;
    
        for (int i: numOfTimes) 
            if (i > maxCount) maxCount = i;
        
        for (int i = 0; i < a.length; i++) 
            if (numOfTimes[i] == maxCount) 
                if (!r.contains(a[i])) 
                    r.add(a[i]);
                
        return r.toArray(dummy); // Here, dummy only tells toArray what type to make its result (it's a hack Java uses).
    }
    
}
