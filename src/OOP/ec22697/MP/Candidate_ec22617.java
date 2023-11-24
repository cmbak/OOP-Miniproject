package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;
import java.util.ArrayList;

class Candidate_ec22617 extends Candidate {
    public static Candidate_ec22617 Myself = new Candidate_ec22617();

    public static void main(String args[]) {
        char selection = 'b';

        while (selection != 'a') {
            System.out.printf("Would you like to%n"
                    + "\ta) Exit%n"
                    + "\tb) Run the same election multiple times%n"
                    + "\tc) Check who counts honestly%n"
                    + "\td) Run all possible candidate pairs%n"
                );

            System.out.print("Your choice: ");
            selection = nextChar();

            switch (selection) {
                case 'a': break;
                case 'b': 
                          runSameElectionMultipleTimes();
                          break;
                case 'c': 
                          checkWhoCountsHonestly();
                          break;
                case 'd': 
                          runAllPossibleCandidatePairs();
                          break;
                default: 
                          System.out.println("'" + selection + "' is not a valid option!");
                          break;
            }
        }
    }

    static Candidate[] candidateSelector() {
        char selection = 'a';
        ArrayList<Candidate> candidates = new ArrayList<Candidate>();

        while (selection != 'd') {
            System.out.printf("Would you like to:%n"
                    + "\ta) Add a specific candidate%n"
                    + "\tb) Add a candidate at random%n"
                    + "\tc) Add all possible candidates%n"
                    + "\td) Run the election%n"
                    );

            selection = nextChar();

            switch (selection) {
                case 'a': {
                            try {
                                Candidate candidateToAdd = getSpecificCandidate();
                                for (int i = 0; i < candidates.size() + 1; i++) {
                                    if (i == candidates.size()) {
                                        // not in array so fine to add
                                        System.out.println("Adding " + candidateToAdd.un + " (" + candidateToAdd.getName() + ")");
                                        candidates.add(candidateToAdd);
                                        break;
                                    } else if (candidateToAdd.un.equals(candidates.get(i).un)) {
                                        System.err.println("Candidate already in array!");
                                        break;
                                    }
                                }
                            } catch (NoSuchElementException e) {
                                System.err.println(e.getMessage());
                            }
                            break;
                      }
                case 'b': {
                            try {
                                Candidate candidateToAdd = addCandidateAtRandom(candidates.toArray(new Candidate[0]));
                                System.out.println("Adding " + candidateToAdd.un + " (" + candidateToAdd.getName() + ")");
                                candidates.add(candidateToAdd);
                            } catch (IllegalArgumentException e) {
                                System.err.println(e.getMessage());
                            }
                            break;
                      }

                case 'c': {
                              Candidate[] allCandidates = A3.getCandidateArray();

                              if (candidates.size() == 0) {
                                  candidates.addAll(Arrays.asList(allCandidates));
                              } else {
                                  for (Candidate c : allCandidates) {
                                      for (int i = 0; i < (candidates.size() + 1); i++) {
                                          if (i == candidates.size()) {
                                              // not in array for sure
                                              candidates.add(c);
                                          }
                                          else if (c.un.equals(candidates.get(i).un))
                                              break;
                                      }
                                  }
                              }
                              break;
                      }
                case 'd':
                  break;
                default:
                    System.out.println("'" + selection + "' is not an option!");
                    break;
            }
        }

        return candidates.toArray(new Candidate[0]);
    }

    static Candidate getSpecificCandidate() throws NoSuchElementException{
        System.out.print("Enter a qmul identifier (typically in the pattern on ec\\d{5}): ");
        String candidateString = nextCandidate();

        Candidate[] allCandidates = A3.getCandidateArray();
        for (Candidate c : allCandidates) {
            if (c.un.equals(candidateString)) {

                return c;
            }
        }

        throw new NoSuchElementException("Candidate " + candidateString + " could not be found");
    }

    static Candidate addCandidateAtRandom(Candidate[] candidates) throws IllegalArgumentException {
        Random r = new Random();
        Candidate[] allCandidates = A3.getCandidateArray();

        for (Candidate candidateWhoExists : candidates) {
            for (int i = 0; i < allCandidates.length; i++) {
                if (allCandidates[i].un.equals(candidateWhoExists.un)) {
                    shiftToBackArray(allCandidates, i);
                    break;
                }
            }
        }

        if (allCandidates.length == candidates.length) {
            throw new IllegalArgumentException("All the candidates are already in the array");
        }

        return allCandidates[r.nextInt(allCandidates.length - candidates.length)];
    }

    static Candidate getWinner(Candidate[] votes) throws IllegalArgumentException {

        if (votes == null || votes.length == 0) throw new IllegalArgumentException("Array is null or has no length");

        HashMap<Candidate, Integer> candidateVotes = new HashMap<Candidate, Integer>();

        for (Candidate c : votes) {
            if (c == null) {
                // ignore null candidates
                continue;
            } else if (!candidateVotes.containsKey(c)) {
                candidateVotes.put(c, 1);
            } else {
                candidateVotes.put(c, (candidateVotes.get(c) + 1));
            }
        }

        HashMap.Entry<Candidate, Integer> mvc = null; 
        for (HashMap.Entry<Candidate, Integer> candidatesVotes : candidateVotes.entrySet()) {
            if (mvc == null) 
                mvc = candidatesVotes;
            else if (mvc.getValue() < candidatesVotes.getValue()) 
                mvc = candidatesVotes;
        }

        return mvc.getKey();
    }

    static void runSameElectionMultipleTimes() {
        Candidate[] candidates = candidateSelector();

        System.out.print("Times to run election: ");
        int count = nextInt();
        Candidate[] winners = new Candidate[count];


        for (int i = 0; i < count; i++) {
            Candidate[] votes = new Candidate[candidates.length];
            for (int j = 0; j < candidates.length; j++) {
                try {
                    votes[i] = candidates[i].vote(candidates);
                } catch (Exception e) {
                    for (StackTraceElement elem : e.getStackTrace()) System.err.println(elem);
                    System.err.println(e.getMessage() + "\n");
                    votes[i] = null;
                }
            }

            winners[i] = getWinner(votes);
            System.out.println("The winner of election " + i + " was " + winners[i].getName() + "!");
        }

        System.out.println("Overall, the winner was: " + getWinner(winners).getName());
    }


    static void checkWhoCountsHonestly() {
        Candidate[] allCandidates = A3.getCandidateArray();

        Candidate[] votes = new Candidate[allCandidates.length];
        for (int i = 0; i < allCandidates.length; i++) {
            try {
                votes[i] = allCandidates[i].vote(allCandidates);
            } catch (Exception e) {
                for (StackTraceElement elem : e.getStackTrace()) System.err.println(elem);
                System.err.println(e.getMessage() + "\n");
                votes[i] = null;
            }
        }

        Candidate realWinner = getWinner(votes);
        for (Candidate adjudicator : allCandidates) {
            try {
                Candidate supposedWinner = adjudicator.selectWinner(votes);

                if (supposedWinner == realWinner) {
                    System.out.println(adjudicator.getName() + " is counting honestly");
                } else {
                        System.out.println(adjudicator.getName() + " is telling lies about who won :(");
                }
            } catch (Exception e) {
                for (StackTraceElement elem : e.getStackTrace()) System.err.println(elem);
                System.err.println(e.getMessage());
            }
        }
    }


    static void runAllPossibleCandidatePairs() {
        Candidate[] allCandidates = A3.getCandidateArray();
        Candidate[] candidates = A3.getCandidateArray();

        while (candidates.length > 1) {
            Candidate[] newCandidates = new Candidate[(candidates.length + 1) / 2];

            if (candidates.length % 2 != 0) {
                newCandidates[candidates.length / 2] = candidates[candidates.length - 1];
            }

            for (int i = 0; (i + 1) < candidates.length; i += 2) {
                Candidate[] electives = {candidates[i], candidates[i + 1]};
                Candidate[] votes = new Candidate[allCandidates.length];

                for (int j = 0; j < allCandidates.length; j++) {
                    try {
                        votes[j] = allCandidates[j].vote(electives);
                    } catch (Exception e) {
                        for (StackTraceElement elem : e.getStackTrace()) System.err.println(elem);
                        System.err.println(e.getMessage() + "\n");
                        votes[i] = null;
                    }
                }

                newCandidates[i / 2] = getWinner(votes);
            }

            candidates = newCandidates;
        }

        System.out.println("The winner of the possible pair elections is: " + candidates[0].getName());
    }

    static int nextInt() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            if (scanner.hasNextInt())
                return scanner.nextInt();
            else
                scanner.next();
        }
        return 0;
    }

    static char nextChar() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String next = scanner.next();
            return next.charAt(next.length() - 1);
        }
        return 'z';
    }

    static String nextCandidate() {
        Pattern id_pattern = Pattern.compile("[a-z]{2,3}[0-9]{3,6}");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            if (scanner.hasNext(id_pattern))
                return scanner.next(id_pattern);
            else
                scanner.next();
        }

        return "ec22617"; // :trollge:
    }

    public String getName() {
        return new String("Zak");
    }

    public String getSlogan() {
        return new String("No war but the class war!");
    }

    enum Method {
        vote,
        selectWinner
    }

    boolean isInvalidDelegatee(Candidate candidate) {
        return candidate == null || candidate.getClass() == this.getClass();
    }

    Candidate delegate(Candidate[] candidates, Method method, Candidate delegatee) {
        Candidate candidate;

        // we can delegate :smiling_imp:
        switch (method) {
            case vote:
                candidate = delegatee.vote(candidates);
                break;

            case selectWinner:
                candidate = delegatee.selectWinner(candidates);
                break;

            default:
                candidate = delegatee;
                System.err.println("Some method type " + method + " in " + this.getClass() + " has not been implemented, returning " + candidate);
                break;
        }

        return candidate;
    }

    Candidate delegateFromArray(Candidate[] candidates, Method method) {
        Random r = new Random();
        Candidate candidate = null;

        // loop until we can delegate, or we run out of things in the array which are invalid
        for (int invalid_count = 0; invalid_count < candidates.length; invalid_count++) {
            int randomly_selected = r.nextInt(candidates.length - invalid_count);
            candidate = candidates[randomly_selected];

            if (isInvalidDelegatee(candidate)) {
                shiftToBackArray(candidates, randomly_selected);
                continue;
            }

            return delegate(candidates, method, candidate);
        }

        // we can't delegate return something
        return candidate;
    }

    static <T> void shiftToBackArray(T[] array, int index) {
        for (int i = index; (i + 1) < array.length; i++) {
            T temp = array[i];
            array[i] = array[i + 1];
            array[i + 1] = temp;
        }
    }

    public Candidate vote(Candidate[] candidates) {
        return delegateFromArray(candidates, Method.vote);
    }

    public Candidate selectWinner(Candidate[] candidates) {
        return delegateFromArray(candidates, Method.selectWinner);
    }
}
