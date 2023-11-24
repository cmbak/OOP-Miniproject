package OOP.ec22697.MP;//Refrenced from examples provided

import java.util.Scanner;

class Candidate_ec22828 extends Candidate {
    
    public String getName() {
        return "Robin";
    }
    
    public String getSlogan() {
        return "More trees!";
    }
    
    
    
    public Candidate vote(Candidate[] candidates) {
      Candidate r = new Candidate_ec22828();

        if (candidates.length != 0) r = candidates[candidates.length-1];
        
        return r;

    }
    
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate currentWinner = new Candidate_ec22828();
        
        if(votes.length != 0) {

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
        }
        return currentWinner;
    }
    
    
    //adapted from many other pull requests
    
   public static int inputInt(String message)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println(message);
        int in = scanner.nextInt();
        return in;
    }
    
    public static String inputString (String message)  //takes in string values
{
       String answer;
       Scanner scanner = new Scanner(System.in);

       System.out.println(message);
       answer = scanner.nextLine();

       return answer;
}

    public static void main(String[] a)
    {
        Candidate[] allCandidates = A3.getCandidateArray();
        String addedCandidate;
        int candidateNum;

        candidateNum = inputInt("How many candidates will be in the election?");
        Candidate[] candidateList = new Candidate[candidateNum];
        System.out.println("Add candidates to the election: ");

        for (int i = 0; i < candidateNum; i++)
        {
            addedCandidate = inputString("Enter a username");
            candidateList[i] = A3.getByUsername(addedCandidate, allCandidates);
        }

        System.out.println("The candidates are:");
        for(int j = 0; j < candidateNum; j++)
        {
            int currentOrderNum = j+1;
            System.out.println(currentOrderNum + ". " + candidateList[j].getName());
        }


        String voteCounterName = inputString("Who should count the votes?");
        Candidate voteCounter = A3.getByUsername(voteCounterName, candidateList);
        

        int electionNum = inputInt("How many times should the election run?");
        Candidate[] votes = new Candidate[candidateNum * electionNum];

        for (int x = 0; x < electionNum; x++)
        {
            for (int z = 0; z < candidateNum; z++)
            {
                votes[z+(x*candidateNum)] = candidateList[z].vote(candidateList);
            }
        }


        Candidate winner = voteCounter.selectWinner(votes);
        System.out.println("The winner was " + winner.getName());
        return;
    }
    

    
}
