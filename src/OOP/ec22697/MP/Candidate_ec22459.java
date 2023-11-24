package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;




class Candidate_ec22459 extends Candidate {

    public String getName() {
        return "Kenji lab 4 number2";
    }

    public String getSlogan() {
        return "More Plastic !";
    }

    public Candidate vote(Candidate[] candidates) {
        if (candidates.length == 0 || candidates==null){
            return new Candidate_ec22459();
        }
        for(Candidate c: candidates){
            if (c.getName() == "Armin Shahnami"){
                return c;
            }
        }
        return candidates[candidates.length-1];
    }

    public Candidate selectWinner(Candidate[] votes) {
        if (votes.length == 0 || votes==null){
            return new Candidate_ec22459();
        }

        int maxcount = 0;
        Candidate winner = votes[0];
        for(Candidate a: votes){
            if (a == null) continue;
            int count=0;
            for(Candidate b: votes){
                if (a==b){
                    count++;
                }
            }
            if (count>maxcount){
                winner = a;
                maxcount=count;
            }
        }
        return winner;
    }
    public static String input(String question) {
        String answer;
        Scanner sc = new Scanner(System.in);
        System.out.println(question);
        answer = sc.nextLine();
        return answer;
    }


    public static int electionTimes(){
        int Int = 0;
        String number = "";
        boolean validInput;
        do
        {
            number = input("How many times should we run the election?");
            try
            {
                Int = Integer.valueOf(number);
                validInput = true;
            }
            catch (Exception e)
            {
                System.out.println("Incorrect input, enter an integer");
                validInput = false;
            }
        }while(!(validInput));
        return Int;
    }
    public static String charInput(String message){
        String input = "";
        boolean correctInput;
        do
        {
            input = input(message);
            if (input.equals("a") || input.equals("b") || input.equals("c"))
            {
                correctInput = true;
            }
            else
            {
                correctInput=false;
            }
        }while(!(correctInput));
        return input;
    }
    public static void printCandidates(int count, Candidate[] newElectionList)
    {
        System.out.println("Candidates are");
        if(count == 0 || newElectionList[0]==null)
        {
            System.out.println("None");
        }
        else
        {
            for(int i=0; i<count; i++)
            {
                System.out.println((i+1) + ": " + newElectionList[i].getName());
            }
        }
    }

    public static boolean isRunning2(Candidate newCandidate, Candidate[] newElectionList, int count)
    {
        boolean isRunning = true;
        if(count == 0)
        {
            isRunning = false;
        }
        else
        {
            for (int i = 0; i < count; i++)
            {
                if (newCandidate.equals(newElectionList[i]))
                {
                    isRunning = true;
                    break;
                }
                else
                {
                    isRunning = false;
                }
            }
        }
        return isRunning;
    }


    public static void main(String args[])
    {
        Candidate[] candidateList = A3.getCandidateArray();
        Candidate[] newElectionList = new Candidate[candidateList.length];
        int count = 0;
        boolean exit = true;
        while(exit)
        {
            printCandidates(count,newElectionList);
            String choice = charInput("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
            if(choice.equals("a"))
            {
                String askingCandidate = input("Please enter a username");
                Candidate retrievedCandidate = A3.getByUsername(askingCandidate,candidateList);
                boolean isIn = isRunning2(retrievedCandidate,newElectionList,count);
                if (retrievedCandidate != null)
                {
                    if (isIn == false)
                    {
                        newElectionList[count] = retrievedCandidate;
                        count++;
                    }
                    else
                    {
                        System.out.println("Candidate is already running");
                    }
                }
                else
                {
                    System.out.println("User not found");
                }
                System.out.println("");
            }
            else if(choice.equals("b"))
            {
                Random rand = new Random();
                int cand = rand.nextInt(candidateList.length);
                newElectionList[count] = candidateList[cand];
                count++;
            }
            else if(choice.equals("c"))
            {
                String askingCandidate = input("Who should count the votes?");
                Candidate retrievedCandidate = A3.getByUsername(askingCandidate,candidateList);
                int electionNum = electionTimes();
                Candidate[] winners = new Candidate[candidateList.length];
                Candidate[] votes = new Candidate[candidateList.length];
                for (int i = 0; i<electionNum; i++)
                {
                    for (int j = 0; j<newElectionList.length; j++)
                    {
                        try
                        {
                            votes[j] = candidateList[j].vote(newElectionList);
                        }
                        catch (Exception e)
                        {
                            System.out.println("exception occured for candidate");
                        }
                    }
                    Candidate winner = retrievedCandidate.selectWinner(votes);
                    winners[i] = winner;
                }
                Candidate mostFreq = retrievedCandidate.selectWinner(winners);
                System.out.println("most frequent winner is "+mostFreq.getName());
                System.out.println("");
                String choice2 = charInput("Would you like to\n" +
                        "a) exit\n" +
                        "b) run the same election many times");
                if(choice2 == "a")
                {
                    break;
                }
                else if (choice2 == "b")
                {
                    continue;
                }
            }
        }
    }
}








