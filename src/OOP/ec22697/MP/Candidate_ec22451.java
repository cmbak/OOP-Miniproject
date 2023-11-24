package OOP.ec22697.MP;

import java.util.Scanner;
import java.util.Random;
class Candidate_ec22451 extends Candidate {



    public static void main(String []a)
    {
        boolean ready = false;
        final int Max = 600;
        int filled = 0;
        Candidate[] candidates = new Candidate[Max];
        String [] vv = new String [600];
        Print("Running Repeated Elections");
        Print("Candidates are");
        vv = PrintCandidates(candidates,filled);
        while(!ready){
            String option = selection();
            if (option.equals("a"))
            {
                AddCandidate(candidates,filled);
                filled ++;
            }
            else if(option.equals("b"))
            {
                RandomCandidate(candidates,filled);
                filled ++;
            }
            else{
                ready = true;
            }
            Print("Running Repeated Elections");
            Print("Candidates are");
            vv = PrintCandidates(candidates,filled);
        }
        if (filled!= 0){
        RunElection(candidates, filled, vv);
        }
        return;
    }





    public static void AddCandidate(Candidate[] candidates, int filled)
    {
        String user = InputString("Please enter a username");
        candidates[filled] = A3.getByUsername(user, A3.getCandidateArray());
        return;
    }




    public static void RandomCandidate(Candidate[] candidates, int filled)
    {
        Random random = new Random();
        Candidate[] candidatesList = A3.getCandidateArray();
        int rand = random.nextInt(candidatesList.length);
        candidates[filled] = (candidatesList[rand]);
        return;
    }




    public static String [] PrintCandidates(Candidate[] candidates, int filled)
    {
        String [] vv = new String[filled];
        for(int i=0;i<filled;i++)
        {
            String v = "";
            try { 
                v = candidates[i].vote(candidates).getName(); 
            } catch (Exception e) { 
                v = "Sussy Baka";
            }
            Print(v);
            vv[i] = v;
        }
        return vv;
    }





    public static String selection()
    {
        String reply = InputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
        while (!(reply.equals("a")|reply.equals("b")|reply.equals("c")))
        {
            reply = InputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
        }
        return reply;
    }
    public static String finalselection()
    {
        String reply = InputString("Would you like to a) exit b) run same election many times c) check who counts honestly");
        while (!(reply.equals("a")|reply.equals("b")|reply.equals("c")))
        {
            reply = InputString("Would you like to a) exit b) run same election many times c) check who counts honestly");
        }
        return reply;
    }





    public static int [] specialwinner(Candidate[] candidates, String whoChecks, int howmany)
    {
        String [] winners = new String [howmany];
        int [] repeats = new int [howmany];
        int mostvotes = 0;
        for (int i = 0;i<howmany;i++)
        {
            winners[i] = "";
            try { 
                winners[i] = A3.getByUsername(whoChecks, A3.getCandidateArray()).selectWinner(candidates).getName(); 
            } catch (Exception e) { 
                winners[i] = "Sussy Baka";
            }
            repeats[i] = 1;
            for (int j=0;j<i;j++)
            {
                if (winners[i].equals(winners[j]))
                {
                    repeats[i]++;
                    if (repeats[i]>mostvotes)
                    {
                        mostvotes = repeats[i];
                    }
                    
                }
            }
        }
        System.out.print("Most common winner is");
            for (int m =0 ; m<howmany;m++)
            {
                if (mostvotes == repeats[m])
                {
                    System.out.println(" "+winners[m]);
                }
            }
            System.out.println("There were no other winners");
        return repeats;
    }




    public static void RunElection(Candidate[] candidates, int filled, String [] vv)
    {
        String whoChecks = InputString("Who should count the votes?");
        int howmany = InputInt("How many times shall we run the election?",0,true);
        int [] repeats = specialwinner(candidates, whoChecks, howmany);

        String option = finalselection();
        while (!option.equals("a"))
        {
            if (option.equals("b"))
            {
                whoChecks = InputString("Who should count the votes?");
                howmany = InputInt("How many times shall we run the election?",0,true);
                repeats = specialwinner(candidates, whoChecks, howmany);
            }
            if (option.equals("c"))
            {
                int realmostvotes = 0;
                int [] realrepeats = new int [howmany];
                for (int k = 0;k<filled;k++)
                {
                    realrepeats[k] = 1;
                    for (int l=0;l<k;l++)
                    {
                        
                        if (vv[l].equals(vv[k]))
                        {
                            realrepeats[k]++;
                            if (realrepeats[k]>realmostvotes)
                            {
                                realmostvotes = realrepeats[k];
                            }
                            
                        }
                    }

                }
                boolean honest = true;
                for (int m =0 ; m<howmany;m++)
                {
                    if (realrepeats[m] != repeats[m])
                    {
                        honest = false;
                    }
                }
                if(honest)
                {
                    Print("User "+whoChecks+" was honest with their winners");
                }
                else{
                    Print("User "+whoChecks+" wasn't honest with their winners");
                }
            }
            option = finalselection();
        }
    }





    public String getName() {
        return "Mr. Bean";
    }




    public String getSlogan() {
        return "Teddy!";
    }




    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22451();
        for(int i=0;i<candidates.length;i++){
            r = candidates[i];
            if (r.getName().equals("Mr. Bean")){
                return r;
            }
        }
        for(int i=0;i<candidates.length;i++){
            r = candidates[i];
            if (r.getSlogan().equals("Teddy!")){
                return r;
            }
        }
        return r;
    }




    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22451();
        if (votes.length == 0){
            return new Candidate_ec22451();  
        }
        else{
            int longest = 0;
            for(Candidate c: votes){
                if (c.getName().length()>longest){
                    longest = c.getName().length();
                    r = c;
                }
            }
        }
        return r;
    }  







    public static void Print(Object message)
    {
        System.out.println(message);
        return;
    }





    public static String InputString(String message)
    {
        Scanner scanner = new Scanner(System.in);
        Print(message);
        return scanner.nextLine();
    }






    public static int InputInt(String message, int minimum, Boolean isThereMin)
    {
        String replyString;
        int replyInt = -1;
        Boolean validInput = false;
        while (! validInput)
        {
            replyString = InputString(message);
            try
            {
                replyInt = Integer.parseInt(replyString);
                if (replyInt>=minimum | ! isThereMin)
                {
                    validInput = true;
                }
                else
                {
                    Print("Invalid Input! Integers only greater than or equal to "+minimum+".");
                }
            }
            catch(Exception e)
            {
                if (isThereMin)
                {
                    Print("Invalid Input! Integers only greater than or equal to "+minimum+".");
                }
                else
                {
                    Print("Invalid Input! Integers only!");
                }
            }
        }
        return replyInt;
    } 





}
