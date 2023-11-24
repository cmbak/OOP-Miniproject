package OOP.ec22697.MP;

import java.util.Scanner;
import java.util.Random;
import java.util.regex.Pattern;

class Candidate_ec22711 extends Candidate
{  
    private static boolean showErrors = false;

    // Use Standard Out.
    private static <T> void p(T s) { System.out.print(s);}
    private static <T> void pr(T s) { System.out.println(s);}
    
    // Use Standard In.
    private static Scanner sc() {return new Scanner(System.in);}
    //Returns a new random object.
    private static Random rd() {return new Random();}
    
    private static char getChar(String q) {
        pr(q);
        String t = sc().nextLine(); 
        return ( t.length()==0 ? ' ' : t.charAt(0) );   
        //return sc().nextLine().charAt(0);
    }
    
    private static Pattern isNum = Pattern.compile("\\d+");    
    private static int getInt(String q) {
        pr(q);
        String t = sc().nextLine(); 
        return ( !isNum.matcher(t).matches() ? 0 : Integer.parseInt(t) );
        //return sc().nextInt(); 
    }

    private static String getStringInput (String message) {
        pr(message);
        String response = sc().nextLine();
        return response;
    }

    //My main procedure
    public static void main(String[] args)
    {
        pr("= Running Repeated Elections =");
        
        Candidate[] chosenCandidates = new Candidate[546];
        Candidate[] allCandidates = A3.getCandidateArray();

        boolean exit = false;
        char choice = '?';
        
        do {
          choice = getChar("\nWould you like to a) add a specific candidate " + 
                           "b) add a candidate at random " +
                           "c) run the election? " +
                           "d) exit");
                           
          switch (choice) {
                  
            case 'a': addSpecificCandidate(allCandidates, chosenCandidates); break;
                  
            case 'b': addRandomCandidate(allCandidates, chosenCandidates); break;
                  
            case 'c': runTheElection(allCandidates, chosenCandidates); break;
            
            case 'd':
                exit = true;
                pr("Exiting"); break;
                                    
            default: pr("Option '"+choice+"' not available.");
          }
        } while (!exit);
    }
    
    //Adds a user chosen candidate to the array of candidates
    private static void addSpecificCandidate (Candidate[] listOfAllCandidates, Candidate[] listOfChosenCandidates) {
        final String CANDIDATE_NAME_REQUEST = "Please enter a username.";
        String chosenCandidateUsername = getStringInput(CANDIDATE_NAME_REQUEST);
        
        for (Candidate c : listOfAllCandidates) {
            if(c.un.equals(chosenCandidateUsername)) 
            {
                int nextFreeIndex = getNextFreeSpaceInArray(listOfChosenCandidates);
                addCandidateToArray(listOfChosenCandidates, c, nextFreeIndex);
                printCandidateNameAdded(c);
                break;
            }
        }
    }
    
    //Adds a random candidate to the array of candidates.
    private static void addRandomCandidate(Candidate[] listOfAllCandidates, Candidate[] listOfChosenCandidates) {
        final int MAX_NUMBER_OF_CANDIDATES = 546;
        
        int randomInt = getRandNumInRange(MAX_NUMBER_OF_CANDIDATES);
        int nextFreeIndex = getNextFreeSpaceInArray(listOfChosenCandidates);
        
        addCandidateToArray(listOfChosenCandidates, listOfAllCandidates[randomInt], nextFreeIndex);
        
        printCandidateNameAdded(listOfAllCandidates[randomInt]);
    }
    
    private static void runTheElection (Candidate[] listOfAllCandidates, Candidate[] listOfChosenCandidates) {
        String voteCounterUsername = getStringInput("Who should count the votes?");
        int howManyElections = getInt("How many times shall we run the election?");
        
        final int MAX_NUMBER_OF_CANDIDATES = 546;
        int numberOfChosenCandidates = getLengthOfArray(listOfChosenCandidates);
        Candidate[] chosenCandidates = new Candidate[numberOfChosenCandidates];
        
        for(int i=0; i<numberOfChosenCandidates; i++)
            chosenCandidates[i] = listOfChosenCandidates[i];
        
        Candidate[] votes = getVotes(chosenCandidates, chosenCandidates);
                    
        for (Candidate c : listOfAllCandidates) {
            if(c.un.equals(voteCounterUsername)) 
            {
                for (int electionCount = 0; electionCount < howManyElections; electionCount++) {
                    for(int i=0; i<MAX_NUMBER_OF_CANDIDATES; i++) {
                        votes = getVotes(chosenCandidates, chosenCandidates);
                    }

                    pr("The most common winner is " + c.selectWinner(votes).getName());
                }
                break;
            }
        }
    }
    
    private static Candidate[] getVotes(Candidate[] candidates, Citizen[] va) { 

        Candidate[] temp = new Candidate[va.length];    
        int count = 0;
        for (int i=0;i<va.length;i++) {
            if (proper(va[i])) {
                try { 
                    temp[count] = va[i].vote(candidates); 
                    //System.err.print(" "+temp[count].getName());
                    count++; // Won't happen if vote throws an exception.
                }
                catch (Exception e) { // 'Spoilt ballot'
                    if (showErrors) e.printStackTrace();
                }   
            }
        }

        Candidate[] votes = new Candidate[count];    
        for (int i=0;i<count;i++) votes[i] = temp[i];
        return votes;
    }
    private static boolean proper(Person p) {
        return !(p.getName().length() > 6 &&
                   p.getName().substring(0,7).equals("No name"));
    }
    
    private static int getLengthOfArray (Candidate[] givenArray) {
        int length = 0;
            
        for(Candidate c : givenArray) {
            if(c != null) {
                length++; 
            }
            else {
                return length;
            }
        }
        
        return givenArray.length;
    }
    
    //Prints that a candidate has been added to the array of candidates,
    private static void printCandidateNameAdded (Candidate newCandidate) {
        pr(newCandidate.getName() + " has been added to the list!");
    }
    
    //Returns a random number in a given range.
    private static int getRandNumInRange(int maxInt) {
        return rd().nextInt(maxInt);
    }
    
    //Adds a given candidate to a given candidate array at the next free index.
    private static void addCandidateToArray (Candidate[] listOfCandidates, Candidate newCandidate, int index) {
        listOfCandidates[index] = newCandidate;
    }
    
    //Gets next free space in a given array.
    private static int getNextFreeSpaceInArray (Candidate[] array) {
        for (int i=0; i<array.length; i++) {
            if (array[i] == null){
                return i;
            }
        }
        
        return -1;
    }
    

    public String getName() {
        return "Gustavo Fring";
    }

    public String getSlogan() {
        return "Last chance to look at me Hector.";
    }

    public Candidate vote(Candidate[] candidates) {

        // If array is empty, return instance of friend's class.
        if (candidates.length == 0)
            return new Candidate_ec22711();

        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("Last chance to look at me Hector."))
                return c;

        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Walter White"))
                return c;

        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }

    public Candidate selectWinner(Candidate[] votes) { 

        // If array is empty, return instance of friend's class.
        if (votes.length == 0)
            return new Candidate_ec22711();

        // Default to first vote, but this will be over-written.
        Candidate currentWinner = votes[0];

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
            }
        }

        return currentWinner;
    }
    
    

}
