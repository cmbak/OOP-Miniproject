package OOP.ec22697.MP;// File Candidate_ec22789.java
//

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Candidate_ec22789 extends Candidate {
    public String getName() {
        return "Kristoph";
    }

    public String getSlogan() {
        return "Keep Your Phone on Silent!";
    }


    /*assignment 3*/
        public static void main(String[] args){
             Candidate[] allCandidates = A3.getCandidateArray();
             Candidate[] myCanadidates = new Candidate[allCandidates.length];
             int myArraySize = 0;

             boolean exit = false;
             while(!exit){
                 char chosenOption = validateCharInput("a. Add a specific candidate \n" +
                         "b. Add a random candidate\n" +
                         "c. Run election\n" +
                         "d. EXIT", new char[] {'a','b','c','d'});
                 switch(chosenOption){
                     case 'a':
                         myCanadidates = addSpecific(myCanadidates, myArraySize);
                         displayCandidates(myCanadidates, myArraySize);
                         myArraySize++;
                         break;
                     case 'b':
                         myCanadidates= addRandom(myCanadidates, myArraySize);
                         displayCandidates(myCanadidates,myArraySize);
                         myArraySize++;
                         break;
                     case 'c':
                         runElection(myCanadidates, myArraySize);
                         break;
                     case 'd': exit=true;break;
                 }

             }
            /*
            System.out.println(addRandom().getName()+ " " + addRandom().getSlogan());
            Candidate specific = addSpecific();
            System.out.println(specific.getName() + specific.getSlogan());
            */

        }

        public static void runElection(Candidate[] cands, int size){
            if(size== 0)System.out.println("No candidates to run Election");
            else{
                String countVotes = inputString("Who should count the votes? ");
                int timesOfVote = inputInteger("How many times do you want to run the election? ");
                mostWinner(countVotes, timesOfVote, cands, size);
            }
        }

    public static void mostWinner(String countVotes, int timesOfVote, Candidate[] cands, int size){
        Candidate[] newCands = new Candidate[size];
        for(int i=0; i<size; i++){
            newCands[i]= cands[i];
        }
        Candidate[] winners=new Candidate[timesOfVote];
        for(int i=0; i<winners.length; i++){
            winners[i]= Objects.requireNonNull(
                    A3.getByUsername(countVotes, A3.getCandidateArray())).selectWinner(votesArray(newCands));
        }
        System.out.println("The most common winner is " + realWinner(winners).getName());

    }

    public static Candidate realWinner(Candidate[] votes) {

        // If array is empty, return instance of friend's class.
        if (votes.length == 0)
            return new Candidate_ec22790();

        // Default to first vote, but this will be over-written.
        Candidate winningCandidate = votes[0];

        // Count the votes for each object in the array,
        // selecting one with the most.
        int mostVotes= 0;
        for (Candidate c : votes) {

            int count = 0;
            for (Candidate x : votes)
                if (x == c)
                    count++;
            if (count >= mostVotes) {
                mostVotes = count;
                winningCandidate = c;
            }
        }

        return winningCandidate;
    }

    public static Candidate[] votesArray(Candidate[] cands){
        Candidate[] votes=new Candidate[cands.length];
        for(int i=0; i<cands.length; i++){
            if(cands[i].vote(cands)!=null) {
                votes[i] = cands[i].vote(cands);
            }
        }
        return votes;
    }

    public static String inputString(String message){
        System.out.println(message);
        return (new Scanner(System.in).nextLine());
    }


    public static void displayCandidates(Candidate[] cands, int size){
        System.out.println("Candidates are: ");
        for(int i=0; i<(size+1); i++){
            System.out.println(cands[i] + " | "+ cands[i].getName() + "  |  " + cands[i].getSlogan());
        }
    }



    public static Candidate[] addRandom(Candidate[] candidates, int index){
            Candidate[] allCandidates= A3.getCandidateArray();
            candidates[index] =  allCandidates[(new Random().nextInt(A3.getCandidateArray().length))];
            return candidates;
        }


    public static Candidate[] addSpecific(Candidate[] candidates, int index){

           while(true){
               System.out.println("Enter valid username");
               String scan=( new Scanner(System.in)).nextLine();
               for(Candidate cand : A3.getCandidateArray()){
                   if(cand.un.equals(scan)){
                      candidates[index]= cand;
                      return candidates;
                   }
               }
               System.out.println("Doesn't exist. ");
           }
        }

        public static char validateCharInput(String all, char[] chars){
            System.out.println(all);
           while(true){
               String scan = (new Scanner(System.in)).nextLine();
               if(scan.length() == 1){
                   for(char aChar : chars){
                       if(aChar == scan.charAt(0)) return aChar;
                   }
               }
               else System.out.println("Invalid option. Enter again : ");
           }
        }

        public static int inputInteger(String message){
            int num = Integer.parseInt(inputString(message));
            while(num<=0){
                num = Integer.parseInt(inputString("Invalid input! Enter a number(>0): "));
            }
            return num;
        }


    /* ends here*/




    public Candidate vote(Candidate[] candidates) {

        // If array is empty, return instance of friend's class.
        if (candidates.length == 0)
            return new Candidate_ec22790();

        // choose at random from list.
        Random luckyCandidate = new Random();
        return candidates[luckyCandidate.nextInt(candidates.length) ];
    }


    public Candidate selectWinner(Candidate[] votes) {
        return realWinner(votes);
    }
}
