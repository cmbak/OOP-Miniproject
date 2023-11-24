package OOP.ec22697.MP;// File Candidate_ec22561.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
class Candidate_ec22561 extends Candidate {
    
    public boolean equals(Object obj) {
        if((obj == null) || !(super.getClass().getSimpleName() == obj.getClass().getSimpleName())){
            return false;
        }
        else{
            return super.equals(obj);
        }
    }
    public String getName(){
        return "Abdirahman";
    }
    public String getSlogan(){
        return "Better Education";
    }
    public Candidate vote(Candidate[] candidates){
        if(candidates.length == 0){
            return new Candidate_ec22561();
        }
        for(int i=0;i< candidates.length;i++){
            if(candidates[i].getSlogan().equals("Better Education")){
                return candidates[i];
            }
        }
        return new Candidate_ec22561();
    }
    public Candidate selectWinner(Candidate[] votesCast) {
        if(votesCast.length == 0){
            return new Candidate_ec22561();
        }
        Candidate winner = votesCast[0];
        int highest_count = 0;
        for(int i=0;i< votesCast.length;i++){
            int count = 0;
            for(int j=i+1;j< votesCast.length;j++){
                if(votesCast[i].equals(votesCast[j])){
                    count +=1;
                }
            }
            if(count >= highest_count){
                highest_count = count;
                winner = votesCast[i];
            }
        }
        return winner;
    }
    static String input(String message){
        Scanner scan = new Scanner(System.in);
        System.out.println(message);
        return scan.nextLine();
    }
    static Candidate ArrayFinder(String username, Candidate[] all){
        for(int i=0;i< all.length;i++){
            if(all[i].un.equals(username)){
                return all[i];
            }
        }
        return (new Candidate_ec22561());
    }
    static Candidate[] ListtoArray(ArrayList<Candidate> candidates){
        int num = candidates.size();
        Candidate[] new_candiates = new Candidate[num];
        for(int i=0;i<num;i++){
            new_candiates[i] = candidates.get(i);
        }
        return new_candiates;
    }
    static void OptionA(Candidate[] all, ArrayList<Candidate> candidates){
        String username = input("Enter a candidate username.");
        Candidate[] contenders = ListtoArray(candidates);
        candidates.add(ArrayFinder(username,contenders));
    }
    static void OptionB(Candidate[] all, ArrayList<Candidate> candidates){
        Random ran = new Random();
        candidates.add(all[ran.nextInt()]);
    }
    static void OptionC(Candidate[] all, ArrayList<Candidate> candidates){
        String username = input("Who should count the votes?");
        Candidate judge = ArrayFinder(username,all);
        Candidate[] votesCast = new Candidate[all.length];
        Candidate[] contenders = ListtoArray(candidates);
        int num = Integer.valueOf(input("Hoe many times would you like to run the election?"));
        Candidate[] winners = new Candidate[num];
        Candidate vote;
        for(int i=0;i<num;i++){
            for(int j=0;i<all.length;i++){
                try{
                    vote = all[j].vote(contenders);
                    votesCast[j] = vote;
                }
                catch(Exception e){
                    votesCast[j] = new Candidate_ec22561();
                }
            }
            winners[i] = judge.selectWinner(all);
        }
        System.out.println("Most common winner is "+ judge.selectWinner(winners));
    }
    public static void main(String [] args){
        Candidate[] allContributions = A3.getCandidateArray();
        ArrayList<Candidate> candidates = new ArrayList<Candidate>();
        String answer = "nothing";
        String answer2 = "Non";
        int count = 0;
        while(!(answer.equals("a"))){
            while(!(answer2.equals("c"))){
                answer2 = input("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
                if(answer2.equals("a")) OptionA(allContributions,candidates);
                if(answer2.equals("b")) OptionB(allContributions,candidates);
                if(answer2.equals("c")) OptionC(allContributions,candidates);
            }
            answer = input("Would you like to\na) exit\nb) run election again?");
            if(answer.equals("b")) OptionC(allContributions,candidates);
            else break;
        }
    }
}
