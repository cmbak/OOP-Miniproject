package OOP.ec22697.MP;

import java.util.HashSet; // Import the HashSet class
import java.util.Random;
import java.util.Scanner;

class Candidate_ec22883 extends Candidate {
    // some code taken from A3.java by Adam Eppendahl and modified by me
    
    private static <T> void pr(T s) { System.out.println(s);}
    
    private static Scanner sc() {return new Scanner(System.in);}
    
    // i can try to make input for chars and strings together
    private static char getChar(String q) 
    {
        pr(q);
        String t = sc().nextLine(); 
        return ( t.length()==0 ? ' ' : t.charAt(0) );   
        //return sc().nextLine().charAt(0);
    }
    
    private static String inpStr(String q) 
    {
        pr(q);
        String inp = sc().nextLine();
        return inp;
    }
    
    private static int inpInt(String q) 
    {
        pr(q);
        String inp = sc().nextLine();
        int answerInt=0;
        try {
            answerInt = Integer.parseInt(inp);    
        } catch (Exception e) {
            answerInt=inpInt("Invalid input. Try again:");
        }
        if (answerInt<1)
        {
            answerInt=inpInt("Invalid input. Try again:");
        }
        return answerInt;
    }
    
    
    
    public static void main(String[] args) 
    {
        boolean exit = false;
        char choice = '?';
        Candidate[] all = A3.getCandidateArray();
        Candidate[] realCs=new Candidate[all.length];
        int count=0;
        
        
        do {
          choice = getChar("\nWould you like to a) add a specific candidate "
                           +"b) run the election? "
                          +"c) exit");
          switch (choice) {
                  
            case 'c': exit = true; break;
                  
            case 'b': runElections(all, realCs);; break;
                  
            case 'a': count=addCandidate(all, realCs, count); break;
                                    
            default: pr("Option '"+choice+"' not available.");
          }
        } while (!exit);
    }
    

    public static void runElections(Candidate[] all, Candidate[] realCs)
    {
        if (realCs.length == 0) return;
        String uInput = inpStr("Who should count the votes?");
        int count=0;
        Candidate counterC = A3.getByUsername(uInput, all);
        if (counterC==null)
        {
            pr("User not found");
            return;
        }
        int times = inpInt("How many times shall we run the election?");
        Candidate[] winners = new Candidate[times];
        for (int i=0; i<times; i++)
        {
            winners[count]= counterC.selectWinner(getVotes(realCs, all));
            count++;
        }
        Candidate frequent=oneMostFrequentB(winners);
        pr("The most common winner: " + frequent.getName());
    }
    
    public static <T> int numberOfTimes(T x, T[] a) 
    {
        int r = 0;

        for(int i = 0; i < a.length; i++) 
        {
            if(x == a[i]) r++;
        }

        return r;
    }
    
   
    public static <T> T oneMostFrequentB(T[] a) {
    
    // Default to first item. Finally we use assignment (=) at type T!
    T r = a[0]; 
    int largestCountSoFar = 1; 
    
    java.util.Set<T> counted = new HashSet<T>();
    // 'counted' is a set of elements of type T. Implemented using hashes.
    // We will learn about this library later in the course. 
    
    for(int i = 0; i < a.length; i++) {
        // If we have already counted a[i], meaning that it is in the set 'counted'
        // then continue. The keyword 'continue' means "skip this loop iteration". In
        // other words, don't execute what comes below, but increase 'i' by 1, get back
        // to the beginning of the loop. 
        if (counted.contains(a[i]))
            continue;
            
        if(numberOfTimes(a[i], a) > largestCountSoFar) {
            r = a[i];
            largestCountSoFar = numberOfTimes(a[i], a);
        }
        // Remember that we have counted a[i] by putting it in the set 'counted'. 
        counted.add(a[i]);
    }

    return r;
    }
    
    public static Candidate[] getVotes(Candidate[] realCs, Candidate[] all)
    {
        Candidate[] temp = new Candidate[realCs.length];    
        int count = 0;
        for (int i=0;i<realCs.length;i++) {
            try { 
                temp[count] = realCs[i].vote(all); 
                count++; 
            }
            catch (Exception e) { // 'Spoilt ballot'
            }   
        }
            
        Candidate[] votes = new Candidate[count];    
        for (int i=0;i<count;i++) votes[i] = temp[i];
        return votes;
        
    }
    
    public static int addCandidate(Candidate[] all, Candidate[] realCs, int count)
    {
        String uInput = inpStr("Please enter a username:");
        Candidate addC = A3.getByUsername(uInput, all);
        if (addC==null)
        {
            pr("user not found");
            return count;
        }
        else
        {
            realCs[count] = addC;
            count++;  
            pr("added");
            return count;
        }
    }
    



    
    // A2 
    
    public String getName() {
        return "Kerete";
    }
    
    public String getSlogan() {
        return "Hi, kids!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        if (candidates.length == 0) 
            return new Candidate_ec22883();
        
        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("Hi, kids!"))
                return c;
        
        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("SashaMasha"))
                return c;
        
        // Otherwise, default to random on list.
        Random n = new Random();
        int randomised=n.nextInt(candidates.length);
        return candidates[randomised];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        if (votes.length == 0) 
            return new Candidate_ec22883();
        
        Random n = new Random();
        Candidate currentWinner = votes[n.nextInt(votes.length-1)];

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
