package OOP.ec22697.MP;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

class Candidate_ec22578 extends Candidate {
    public String getName() {
        return "Kermit";
    }

    public String getSlogan() {
        return "It's easy being green!";
    }


    //Main method offers a menu where you can run an election from a pool of candidates
    public static void main(String[] args) {
        Candidate[] contestants = {};
        String userChoice=menu1();
        int minCandAdded=0;

        while(!(userChoice.equals("c"))||minCandAdded==0){
            if(userChoice.equals("a")){
                contestants=addCandidate(contestants);
                printCands(contestants);
                minCandAdded=1;
            }
            else if(userChoice.equals("b")){
               contestants=addRandomCand(contestants);
               printCands(contestants);
               minCandAdded=1;
            }
            else if(minCandAdded==0){
                System.out.println("Please add a candidate before trying to run an election");
            }

            userChoice=menu1();
        }

        runElection(contestants);
        String secondMenuChoice=menu2();
        while(!(secondMenuChoice.equals("a"))) {
            if(secondMenuChoice.equals("b")){
                runElection(contestants);
            }
            if(secondMenuChoice.equals("c")){
                printHonnestCands(disqualify(contestants));
            }
            if(secondMenuChoice.equals("d")){
                runElection(A3.getCandidateArray());
            }
            if(secondMenuChoice.equals("e")){
                printIfNamePalindrome(contestants);
            }
            if(secondMenuChoice.equals("f")){
                printIfNamePalindrome(A3.getCandidateArray());
            }
            secondMenuChoice=menu2();

        }
    }


    //Displays the final menu for the program, allowing the user many options
    public static String menu2(){
        String menu=userInput("Would you like to:\na) exit\nb)run election again\nc)see who is honest\nd)run a full election" +
                "\ne)which names are palindromes(selected list)\nf)which names are palindromes(full list)");
        while(!(menu.equals("a")||menu.equals("b")||menu.equals("c")||menu.equals("d")||menu.equals("e")||menu.equals("f"))){
            menu=userInput("Invalid input\nWould you like to:\na) exit\nb)run election again\nc)see who is honest" +
                    "\nd)run a full election\ne)which names are palindromes(selected list)\nf)which names are palindromes(full list)");
        }
        return menu;
    }

    //Is a name in the array is a palindrome it is printed
    public static void printIfNamePalindrome(Candidate[] candidates){
        for(Candidate c:candidates){
            if(isItAPalindrome(c.getName())){
                System.out.println(c.getName());
            }
        }
    }


    //Ask for a vote counter and runs the election a number of times displaying the most common winner
    public static void runElection(Candidate[] contestants){
        String voteCounter = candNumb("Who should count the votes? ");
        int numbRuns = Integer.parseInt(userInput("How many times shall we run the election? "));
        while(numbRuns<=0){
            System.out.println("Election must be run at least once");
            numbRuns = Integer.parseInt(userInput("How many times shall we run the election? "));
        }
        commonWinner(voteCounter, numbRuns, contestants);
    }


    //Validates the candidate number as input
    public static String candNumb(String message){
        String userName=userInput(message);
        while(A3.getByUsername(userName, A3.getCandidateArray())==null){
            userName=userInput("Invalid....." + message);
        }
        return userName;
    }

    //Returns most common winner in array
    public static void commonWinner(String voteCounter, int numbRuns, Candidate[] contestants){
        Candidate[] winners=new Candidate[numbRuns];
        for(int i=0; i<winners.length; i++){
            winners[i]= Objects.requireNonNull(A3.getByUsername(voteCounter, A3.getCandidateArray())).selectWinner(voting(contestants));
        }
        System.out.println("The most common winner is " + highest(winners).getName());

    }


    //Creates an array containing all the votes of the candidates
    public static Candidate[] voting(Candidate[] contestants){
        Candidate[] votes=new Candidate[contestants.length];
        for(int i=0; i<contestants.length; i++){
            if(contestants[i].vote(contestants)!=null) {
                votes[i] = contestants[i].vote(contestants);
            }
        }
        return votes;
    }


    //Returns the most common element in array
    public static Candidate highest(Candidate[] winners){
        Candidate currentWinner=winners[0];
        int highestCount = 0;
        for (Candidate c : winners) {
            int count = 0;
            for (Candidate x : winners)
                if (x == c)
                    count++;
            if (count > highestCount) {
                highestCount = count;
                currentWinner = c;
            }
        }

        return currentWinner;
    }


    public static String userInput(String message){
        Scanner input = new Scanner(System.in);
        System.out.println(message);
        return input.nextLine();
    }

    //Extends an array
    public static Candidate[] addToArray(Candidate[] array, Candidate candidate){
        Candidate[] newArray=new Candidate[array.length+1];
        for(int i=0; i<array.length; i++){
            newArray[i]=array[i];
        }
        newArray[array.length]=candidate;
        return newArray;
    }

    //Adds a random cand to array
    public static Candidate[] addRandomCand(Candidate[] contestants) {
        return addToArray(contestants, A3.getCandidateArray()[randomNumbGen(A3.getCandidateArray().length)]);
    }

    //prints all elements in an array
    public static void printCands(Candidate[] contestants){
        System.out.println("Candidates: ");
        for(Candidate c:contestants){
            System.out.println(c.getName());
        }
    }

    //Generate random number

    public static int randomNumbGen(int limit){
        Random random= new Random();
        return random.nextInt(limit);
    }

    //Add a specific candidate to array
    public static Candidate[] addCandidate(Candidate[] contestants){
        return addToArray(contestants, A3.getByUsername(candNumb("Please enter a username."), A3.getCandidateArray()));


    }

    //Displays and validates menu 1
    public static String menu1(){
        String userChoice=userInput("Would you like to:\na) add a specific candidate\nb) add a candidate at random\nc) run the election?");
        while(!(userChoice.equals("a")||userChoice.equals("b")||userChoice.equals("c"))){
            userChoice=userInput("Invalid input try again......?");
        }
        return userChoice;
    }



    public Candidate vote(Candidate[] candidates) {
        // Default return a friend
        Candidate vote=new Candidate_ec22923();
        if (candidates.length == 0) {
            return vote;
        }
        for (Candidate c : candidates) {
            if (c.selectWinner(candidates) != trueWinner(candidates)) {
                vote = c;
            }
        }
        // Otherwise, see if there name is a palindrome like bob
        for (Candidate c : candidates) {
            if (isItAPalindrome(c.getName())){
                vote= c;
            }
        }
        return vote;
    }


    //Checks if a string is a palindrome
    public static Boolean isItAPalindrome(String string){
        StringBuilder backwards= new StringBuilder();
        for(int i=string.length()-1; i>=0; i--){
            backwards.append(string.charAt(i));
        }
        return string.equals(backwards.toString());
    }

    //Disqualify everyone who lies apart from me (like a true politician)
    public static Candidate[] disqualify(Candidate[] votes){
        int numberOfLies=0;
        for(Candidate c: votes) {
            if (!c.getName().equals("Kermit") && c.selectWinner(votes) != trueWinner(votes)) {
                numberOfLies++;
            }
        }


        Candidate[] honestCandidates = new Candidate[votes.length-numberOfLies+1];
        int counter=0;
        for(Candidate c: votes){
            if((c.getName().equals("Kermit"))){
                honestCandidates[counter]=c;
                counter++;

            }
            else if ((c.selectWinner(votes) == trueWinner(votes))) {
                honestCandidates[counter]=c;
                counter++;
            }
        }
        return honestCandidates;
    }

    //Prints honnest candidates
    public static void printHonnestCands(Candidate[] votes){
        System.out.println("The following are honest!");
        for(Candidate c:votes){
            if(c!=null) {
                System.out.println(c.getName());
            }
        }
    }
    //Returns the true winner of the election given an array
    public static Candidate trueWinner(Candidate[] votes) {

        // If array is empty, return instance of friend's class.
        if (votes.length == 0)
            return new Candidate_ec22923();

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

    
    public Candidate selectWinner(Candidate[] votes) {
        return trueWinner(votes);
    }


}

