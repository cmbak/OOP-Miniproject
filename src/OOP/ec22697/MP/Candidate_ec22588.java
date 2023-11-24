package OOP.ec22697.MP;// File Candidate_ec22588.java
//
// Machine generated stub for Assignment 2
// This will return JJ with YEEHAW



import java.util.Random;
import java.util.Scanner;
class Candidate_ec22588 extends Candidate {
    public String getName(){
        return "JJ";}

    public String getSlogan(){
        return "YeeHaw!";}

    public Candidate vote(Candidate[] candidates){
        if(candidates.length == 0)
        return new Candidate_ec22588();

    for(Candidate c: candidates)
    if(c.getSlogan().equals("Yeehaw!"))
        return c;

    for(Candidate c: candidates)
    if(c.getName().equals("JJ"))
        return c;

    Random rm = new Random();
    int cd = rm.nextInt(candidates.length);
    return candidates[cd];
    }

    public Candidate selectWinner(Candidate[] votes)
    {
        if(votes.length == 0)
        return new Candidate_ec22588();

        Candidate currentWinner = votes[0];

        int highestCount = 0;

        for(Candidate c : votes){
            int count = 0;

        for(Candidate x : votes)
            if(x == c)
            count ++;
            for (Candidate x : votes)
                if (x == c)
                    count++;
         if (count >= highestCount) {
            highestCount = count;
             currentWinner = c;
            }
        }

        return currentWinner;
    }
    public static String inputString(String message)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        String ans = scanner.nextLine();
        return ans;
    }
    
    public static int inputInt(String message)
    {
        int num = Integer.parseInt(inputString(message));
        return num;
    }
    
    public static Candidate[] shiftArray(Candidate[] winners, int ArrayLength)
    {
        Candidate[] copy = new Candidate[ArrayLength]; //adapted from ec22881.java
        for(int i = 0; i<winners.length; i++)
        {
            copy[i] = winners[i];
        }
        return copy;
    }
    
    public static void showcurrentcandidates(Candidate[] candidateelections, int numofcandidates)
    {
        if(numofcandidates == 0)
        {
            System.out.println("No candidates selected");
        }
        else
        {
            for(int i = 0; i<candidateelections.length; i++)
            {
                System.out.println(i+1 + "." + candidateelections[i].getName());
            }
        }
    }
    
    public static int addCandidate(Candidate[] allcontributions, Candidate[] candidateelections, int numofcandidates)
    {
        String specificName = inputString("Which specific user would you like to include?");
        Candidate specificCandidate = A3.getByUsername(specificName, allcontributions); //adapted from Lab4 example on JHUB.
        if (specificCandidate != null)
        {
            candidateelections[numofcandidates] = specificCandidate;
            numofcandidates = numofcandidates +1;
            return numofcandidates;
        }
        
        else
        {
            System.out.println("User not found.");
            return numofcandidates;
        }
    }
    
    public static int addRandomCandidate(Candidate[] allcontributions, Candidate[] candidateelections, int numofcandidates)
    {
        
        Random r = new Random();
        boolean status = true;
        
        while(status!=false)
        {
            int randomnum = r.nextInt(allcontributions.length);
            Candidate randomCandidate = allcontributions[randomnum];
            
            for(int i = 0; i<numofcandidates; i++)
            {
                if(candidateelections[i] == randomCandidate)
                {
                    status = true;
                }
            }
            if(status == false)
            {
                candidateelections[numofcandidates] = randomCandidate;
                numofcandidates = numofcandidates + 1;
                return numofcandidates;
            }
        }
        return numofcandidates;
    }
        
    public static void runElection(Candidate[] allcontributions, Candidate[] candidateelections, int numofcandidates)
    {
        String specificName = inputString("Which specific user would you like to include?");
        Candidate votecounter = A3.getByUsername(specificName, allcontributions);
        if(votecounter!=null)
        {
            int count = inputInt("How many times would you like to run this election?");
            Candidate[] winners = new Candidate[count];
            for(int i = 0; i<count; i++)
            {
                Candidate[] votes = new Candidate[allcontributions.length];
                for(int v = 0; v<allcontributions.length; v++)
                {
                    votes[v] = allcontributions[v].vote(candidateelections);
                }
            winners[i].selectWinner(votes);
            }
        FindCommonWinner(winners);
        }
        else
        {
            System.out.println("Candidate named " + specificName + " not found.");
        }
    }
    
    public static void FindCommonWinner(Candidate[] winners)
    {
        boolean onewinner = true; //code adapted from ec22881.java
        String winner = "";
        if(winners.length !=0)
        {
            Candidate[] countWinner = shiftArray(winners, winners.length);
            int highestCount = 0;
            for(int i =0; i<countWinner.length; i++)
            {
                Candidate c = countWinner[i];
                if(c!=null)
                {
                    int count =0;
                    for(int j =0; j<countWinner.length; j++)
                    {
                        Candidate y = countWinner[j];
                        if(y!=null && c.getName() == y.getName())
                        {
                            countWinner[j] = null;
                            count++;
                        }
                    }
                    if(count == highestCount)
                    {
                        winner = winner + c.getName();
                        onewinner = false;
                    }
                    if(count> highestCount)
                    {
                        highestCount = count;
                        winner = c.getName();
                        onewinner = true;
                    }
                }
            }
        }
        if(winner !=null)
        {
            if(onewinner ==false)
            {
                System.out.println("Most common winners are " + winner);
            }
            else
            {
                System.out.println("Most common winner is " + winner);
            }
        }
        System.out.println("No most common winners");
    }
    public static void main(String[] args)
    {
        Candidate[] allcontributions = A3.getCandidateArray();
        Candidate[] candidateelections= new Candidate[allcontributions.length];
        int numofcandidates = 0;
        final String STOP = "d";
        String ans ="";
        System.out.println("=Running repeated elections=");
        while(ans!= STOP)
        {
            showcurrentcandidates(candidateelections, numofcandidates); 
            ans = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election? d)exit?");
            if(ans == "a")
            {
            numofcandidates = addCandidate(allcontributions, candidateelections, numofcandidates);
            }
            else if(ans == "b")
            {
            numofcandidates = addRandomCandidate(allcontributions, candidateelections, numofcandidates);
            }
            else if(ans == "c")
            {
            runElection(allcontributions, candidateelections, numofcandidates);
            }
            else
            {
            System.out.println("This isn't one of the options. Try again.");
            }
        }
    }
}
