package OOP.ec22697.MP;

import java.util.*;

class  Candidate_ec22754 extends Candidate {
    public String getName() {
        return "Richard Parker";
    }

    public String getSlogan() {
        return "Science is the new Future";
    }

    public Candidate vote(Candidate[] candidates){

        //if array is empty, return own candidate
        if(candidates.length == 0){
            return new Candidate_ec22754();
        }
        else
        {
            return candidates[candidates.length-1];
        }
    }

    public Candidate selectWinner(Candidate[] votes){
        if(votes.length == 0){
            return new Candidate_ec22754();
        }

        Candidate currentWinner = votes[0];

        // Count the votes for each object in the array,
        // selecting one with the most.
        int highestCount = 0;
        for (Candidate c : votes) {

            int count = 0;
            for (Candidate x : votes){
                if (x == c){
                    count++;
                }
            }
            if (count > highestCount) {
                highestCount = count;
                currentWinner = c;
            }
        }

        return currentWinner;

    }

    //a method to print a message and take an integer input
    public static int inputInt(String message)
    {
        int input = 0;
        boolean valid = false;
        Scanner scanner = new Scanner(System.in);

        print(message);

        while(!valid)
        {
            if(scanner.hasNextInt())
            {
                input = scanner.nextInt();

                if(input<0)
                {
                    print("Invalid input! Please input positive integers only!");
                    scanner.nextLine();
                }
                else
                {
                    valid = true;
                }

            }
            else
            {
                print("Invalid input! Please input positive integers only!");
                scanner.nextLine();
            }
        }

        return input;
    }
    //end

    //a method to print
    public static void print(String message)
    {
        System.out.println(message);
        return;
    }//end

    //a method to print a message and take a string input
    public static String inputString(String message)
    {
        String input;
        Scanner scanner = new Scanner(System.in);

        print(message);
        input = scanner.nextLine();

        return input;
    }//end

    public static void main(String[] args) {
        Candidate[] all = A3.getCandidateArray();
        all[516] = new Candidate_ec22825();
        Candidate[] myCandidates = new Candidate[all.length];
        int listLength = 0;
        String option = "";
        while (!option.equals("c")){
            option = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
            if (option.equals("a")) {

                Candidate addnew = A3.getByUsername(inputString("Please enter a username."), all);
                if (addnew == null) {
                    System.out.println("User not found.");
                } else {
                    myCandidates[listLength] = addnew;
                    listLength++;
                }
            } else if (all.equals("b")) {
                Candidate addnew;
                boolean n = true;
                while(n){
                    addnew = all[(new Random()).nextInt(all.length)];
                    if(addnew != null){
                        n = false;
                        myCandidates[listLength] = addnew;
                        listLength++;
                    }
                }
            }
            for(Candidate c : myCandidates){
                try {
                    print(c.getName());
                }
                catch(NullPointerException e){
                   // System.out.println("new");
                }
            }
        }
        Candidate[] temp= new Candidate[listLength];
        for(int i =0; i<listLength; i++){
             temp[i] = myCandidates[i];
        }




        Candidate voteCounter = null;
        while(voteCounter == null){
            voteCounter = A3.getByUsername(inputString("Who should count the votes?"), all);
        }
        System.out.println("here3");
        int counter = inputInt("How many times shall we run the election?");
        Candidate[] winnerList = new Candidate[counter];
        Candidate[] votes = new Candidate[all.length];
        for(int i = 0; i<counter; i++)
        {

            for(int j = 0; j<all.length; j++)
            {
                try {
                    try{
                        votes[i] = (all[j]).vote(temp);
                        //throw new NullPointerException();
                    }
                    catch (NullPointerException e){

                    }
                    throw new IndexOutOfBoundsException();
                }
                catch (IndexOutOfBoundsException e) {
                    //System.out.println("out of bound");
                }
                catch (NullPointerException e){
                    //System.out.println("illegal arg");
                }
            }

            winnerList[i] = voteCounter.selectWinner(votes);
        }
        try {
            print((voteCounter.selectWinner(winnerList)).getName() + " is the final Winner");
           // throw new NullPointerException();
        }
        catch (NullPointerException e){
            //int ran = (new Random()).nextInt(myCandidates.length);
            print( " is the final Winner");
        }
        return;
    }

}
