package OOP.ec22697.MP;// File Candidate_ec22532.java
//
// Machine generated stub for Assignment 2

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Candidate_ec22532 extends Candidate {
    public String getName(){
        return "Usman"; //The string returned by your class's getName() method must not be longer than 15 characters.
    }

    public String getSlogan() {
        return "Deez Nuts"; //The string returned by your getSlogan() must not be longer than 40 characters.
    }

    public Candidate vote(Candidate[] candidates) {
        if (candidates.length == 0){ //When passed an empty array, your vote(Candidate[]) and selectWinner(Candidate[]) methods must not throw an exception.
            return new Candidate_ec22532();
        }

        //When passed a non-empty array, your vote(Candidate[]) and selectWinner(Candidate[]) must return an object from that array, so no write-in votes or surprise winners. (This will be tested.)

        boolean selfFound = false;
        Candidate toReturn = null;
        for (int c = 0; c < candidates.length; c ++){
            if (candidates[c].getName().equals("Usman")){
                selfFound = true;
                toReturn = candidates[c];
            }
        }

        if (selfFound == true){
            return toReturn;
        }
        else {
            Random r = new Random();
            int randomToReturn = r.nextInt(candidates.length);
            return candidates[randomToReturn];
        }
        
    }

    
    public Candidate selectWinner(Candidate[] candidates) {

        if (candidates.length == 0) { //When passed an empty array, your vote(Candidate[]) and selectWinner(Candidate[]) methods must not throw an exception.
            System.out.println("No candidates to choose.");
            return new Candidate_ec22532();

        }
        else{
            ArrayList<Candidate> selectable = new ArrayList<Candidate>();
            for (int c = 0; c < candidates.length; c ++){
                if (candidates[c] != null){
                    selectable.add(candidates[c]);
                }
            }
            Random r = new Random();
            int index = r.nextInt(selectable.size());
            return selectable.get(index);
        }

    }

    

    public static void main(String[] args){
    
        Candidate[] candidates = A3.getCandidateArray(); //Create an array of candidates
        Candidate[] candidates2 = new Candidate[candidates.length];//Create another of same size
        for (int c = 0; c < candidates2.length; c ++){
            candidates2[c] = null; //but all are -1
        }
        //int c = 0;
        //String name = "";

        boolean optionA = false;
        char chosenOption = ' ';


        while (optionA == false){

            //show candidates
            System.out.println("Current candidates: ");
            boolean allNull = true;
            for (int c = 0; c < candidates2.length; c ++){
                if (candidates2[c] != null){System.out.println(candidates2[c].getName());}
                allNull = false;
            }
            if (allNull == true){
                System.out.println("None");
            }


            chosenOption = inputChar("Would you like to \n a) exit \n b) add a specific candidate \n c) add a random candidate \n d) run the election");
            
            
            if (chosenOption == 'a'){
                optionA = true;
            }
            else if (chosenOption == 'b'){
                try{
                Candidate toAdd = optionB(candidates, candidates2);
                if (toAdd != null){
                    boolean ender = false;
                    int counter = 0;
                    do{
                       if (candidates2[counter] == null){
                           candidates2[counter] = toAdd;
                           ender = true;
                       }
                       counter ++;
                    }while ((!ender) && (counter < candidates2.length));
                }
                }
                catch(Exception ex){
                    
                }
            }
            else if (chosenOption == 'c'){
                try{
                Candidate toAdd = optionC(candidates);
                if (toAdd != null){
                    boolean ender = false;
                    int counter = 0;
                    do{
                        if (candidates2[counter] == null){
                            candidates2[counter] = toAdd;
                            ender = true;
                        }
                        counter ++;
                    }while ((!ender) && (counter < candidates2.length));
                }
                }
                catch(Exception ex){
                }
            }
            else if (chosenOption == 'd'){
                try{
                    optionD(candidates, candidates2);
                }
                catch(Exception ex){
                }
                
            }


        }
        



    }

    public static Candidate optionB(Candidate[] candidates, Candidate[] candidates2){ //specific cand
        String nameToAdd = inputString("Please enter a username.");
        Candidate toAdd = null;
        for (int i = 0; i < candidates.length; i ++){
            if (candidates[i].un.equals(nameToAdd)){
                toAdd = candidates[i];
            }
        }

        if (toAdd == null){
            System.out.println("Unknown candidate. Cannot be added.");
        }

        return toAdd;
    }

    public static Candidate optionC(Candidate[] candidates){ //random cand
        Candidate toAdd = candidates[randomInt(candidates.length)];
        return toAdd;
    }

    public static void optionD(Candidate[] candidates, Candidate[] candidates2){ //election


        String name = inputString("Who will count the votes? ");
        Candidate CounterCandidate = A3.getByUsername(name, candidates);
        int electionCycles = inputInt("How many times will the election run?");



        Candidate[] winners = new Candidate[electionCycles];
        for (int i = 0; i < electionCycles; i ++){winners[i] = null;}

        for (int e = 0; e < electionCycles; e++){ //for each election

            Candidate toAdd = CounterCandidate.selectWinner(candidates2); //select a winner
            if (toAdd != null){ //given this winner is not null
                winners[e] = toAdd; //this winner is added to array for this election
                System.out.println("Election " + (e + 1) + ", the winner is: " + toAdd.getName());
            }
            else if (toAdd == null){System.out.println("Election " + (e + 1) + ", the winner is: There is no winner.");}

        }

        Candidate mostCommonWinner = findMostCommonWinner(winners);
        
        if (mostCommonWinner != null){
            System.out.println("The most common winner is: " + mostCommonWinner.getName() );

        }
        else{
            System.out.println("There were no winners");
        }

    }

    public static Candidate findMostCommonWinner(Candidate[] winners){
        Candidate mostCommon = null;
        if (winners == null){
            return null;
        }
        else{
            int noW = winners.length;
            int maxcount = 0;

            for (int i = 0; i < noW; i++){
                int count = 0;
                for (int j = 0; j < noW; j++){
                    if (winners[i] == winners[j]){
                        count ++;
                    }
                }

                if (count > maxcount){
                    maxcount = count;
                    mostCommon = winners[i];
                }
            }
        }

        return mostCommon;


    }



    public static int randomInt(int param){
        Random r = new Random();
        int n = r.nextInt(param);
        return n;
    }


    public static char inputChar(String msg){
        Scanner scanner = new Scanner(System.in);
        System.out.println(msg);
        char c = scanner.nextLine().charAt(0);
        return c;
    }

    public static String inputString(String msg){
        Scanner scanner = new Scanner(System.in);
        System.out.println(msg);
        String s = scanner.nextLine();
        return s;
    }

    public static int inputInt(String msg){
        Scanner scanner = new Scanner(System.in);
        System.out.println(msg);
        String s = scanner.nextLine();
        int n = Integer.parseInt(s);
        return n;
    }

}

