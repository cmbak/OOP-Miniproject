package OOP.ec22697.MP;

import java.util.Scanner;
import java.util.Random;

class Candidate_ec22593 extends Candidate {
    public String getName()
    {
        return "Mahamad";
    }
    public String getSlogan()
    {
        return "Change is afoot";
    }
    public Candidate vote(Candidate[] c){
        if(c.length==0)
            return new Candidate_ec22887();
        for(Candidate candidate:c)
        {
            if((candidate.getSlogan()).equals("Arsenal"))
            {
                return candidate;
            }
            if((candidate.getName()).equals("Saka"))
            {
                return candidate;
            }
        }
        return c[0];
    }


    public Candidate selectWinner(Candidate[] votes) {
        final int arrayLength = votes.length;
        int [] counter= new int[arrayLength];
        int highScore=-1;
        int pos=0;
        if(arrayLength==0)
            return new Candidate_ec22887();

        for(int i=0;i<arrayLength;i++)
        {
            int score=0;
            for(int j=0;j<arrayLength;j++)
            {
                if(votes[i].equals(votes[j]))
                {
                    score++;
                }
            }
            counter[i]=score;
        }

        for(int i=0;i<arrayLength;i++)
        {
            if(counter[i]>highScore)
            {
                highScore=counter[i];
                pos=i;
            }

        }
        return votes[pos];
    }
        public static void print(String message)
    {
        System.out.println(message);
    }
    public static String str_input(String message) {
        print(message);
        String temp;
        Scanner keyboard = new Scanner(System.in);
        temp = keyboard.nextLine();
        return temp;
    }
    public static boolean isCandidateNull(Candidate candidate) {
        if (candidate == null) {
            return true;
        } else {
            return false;
        }
    }
    public static String option1()
    {
        return (str_input("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?")).toLowerCase();
    }
        public static Candidate selectRandomCandidate(Candidate[] candidates) {
        Random rand = new Random();
        int randomIndex = rand.nextInt(candidates.length-1);
        return candidates[randomIndex];
    }
    public static Candidate[] addCandidate(Candidate[] candidates, String candName)
    {
        Candidate toAdd;
        Candidate[] toReturn=new Candidate[candidates.length+1];
        if (candName.equals("-1"))
        {
             toAdd=selectRandomCandidate(candidates);
        }
        else{
            toAdd= A3.getByUsername(candName,candidates);
        }
        System.arraycopy(candidates, 0, toReturn, 0, candidates.length);
        toReturn[candidates.length]=toAdd;
        return toReturn;

    }
    public static void printEntryStatement()
    {
        print("= Running Repeated Elections =");
        print("        Candidates are");
        print("        Please not you need to add at least 4 Candidates random or otherwise to ensure the vote only occurs with your inputted candidates. Otherwise other Random Candidates will be added");
    }
    public static void printCandidateNames(Candidate[]  candidates)
    {

        if (isCandidateNull(candidates[0])){
            print("");
            return;
        }
        for(int i=0;i< candidates.length;i++)
        {
            if (isCandidateNull(candidates[i])){
                return;
            }
            print(i+candidates[i].getName());
        }
    }
    public static String option2p1()
    {
        return str_input("Who should count the votes?");
    }
    public static int int_input(String message) {
        int number = 0;
        boolean is_int = false;
        print(message);
        while (!is_int) {
            try {
                Scanner keyboard = new Scanner(System.in);
                String temp = keyboard.nextLine();
                number = Integer.parseInt(temp);
                is_int = true;
            } catch (NumberFormatException nfe) {
                print("This is not an integer. Please try again.");
            }
        }
        return number;
    }
    public static int option2p2()
    {
        return int_input("How many times shall we run the election?");
    }
    public static Candidate[] runElection(Candidate[] runningCandidates)
    {
        Candidate[] votes= new Candidate[runningCandidates.length];
        int i=0;
        for(Candidate c:runningCandidates)
        {
            votes[i]=c.vote(runningCandidates);
            i++;
        }
        return votes;
    }
        public static Candidate exucteOption2(Candidate[] runningCandidates){
        Candidate chosen=null;
        while (chosen==null)
        {
            chosen= A3.getByUsername(option2p1(), A3.getCandidateArray());
        }
        int totalVotesChosen=option2p2();
        Candidate[] winners= new Candidate[totalVotesChosen];
        for(int i=totalVotesChosen;i>0;i--)
            winners[i-1]=chosen.selectWinner(runElection(runningCandidates));
        return chosen.selectWinner(winners);
    }
    public static void main(String[] args) {
        printEntryStatement();
        String option1="dd";
        Candidate[] candidateArray= A3.getCandidateArray();
        Candidate[] toJoin= new Candidate[545];
        int numberofCandsSelected=0;
        while (!(option1.equals("c")))
        {
            option1=option1();
            printCandidateNames(toJoin);
            if (option1.equals("a"))
            {
                String candNum=str_input("Enter candidate number");
                toJoin=addCandidate(toJoin,"Candidate_"+candNum);
                numberofCandsSelected++;
            }
            if (option1.equals("b"))
            {
                toJoin=addCandidate(toJoin,"-1");
                numberofCandsSelected++;
            }

        }
        //to ensure vote runs even if the user doesn't select any candidates to join (Specific or random)
        if(toJoin[0]==null)
        {
            toJoin=candidateArray;
        }
        //ensures at least 50 candidates play without messing up users choices
        if(numberofCandsSelected>4)
        {
            print("Vote will occur using only the Candidates you chose");
        }
        else {
            for(int i=0;i< 50;i++)
            {

            if(toJoin[i]==null)
            {
                toJoin[i]=selectRandomCandidate(candidateArray);
            }
            }


        }
        Candidate winner=exucteOption2(toJoin);
        print("Most common winner is "+ winner.getName());

    }

        



}
