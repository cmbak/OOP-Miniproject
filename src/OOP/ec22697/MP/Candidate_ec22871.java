package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22871 extends Candidate {
    
    public static void main(String[] args){
        Candidate[] candidates=new Candidate[700];
        int candidateCount=0;
        Scanner sc= new Scanner(System.in);
        String userInput;
        while(true){
            System.out.println("Candidates are:");
            if(candidateCount==0){
                System.out.println("None");
            }
            else{
                for (int i = 0; i < candidateCount; i++) {
                    System.out.println(candidates[i].getName());
                }
            }
            System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
            userInput=sc.next();
            if(userInput=="a"){
                System.out.println("Please enter a username.");
                userInput=sc.next();
                candidates[candidateCount].un=userInput;
                candidateCount=candidateCount+1;
            }
            else if(userInput=="b"){
                Random r=new Random();
                candidates[candidateCount].un="ec22945";
            }
            else if(userInput=="c"){
                System.out.println("Who should count the votes?");
                userInput=sc.next();
                Candidate counter=null;
                for(int i=0; i<candidateCount; i++){
                    if(candidates[i].un==userInput){
                        counter=candidates[i];
                        }
                    }
                Candidate currentWinner= candidates[0];
                int count=0;
                int temp=0;
                for(int i=0; i<candidateCount; i++){
                    temp=0;
                    for(int j=0; j<candidateCount; j++){
                        if(candidates[i].un==candidates[j].un){
                            temp=temp+1;
                        }
                    }
                    if(temp>count){
                        currentWinner=candidates[i];
                        count=temp;
                    }
            }
            System.out.println("The winner is "+currentWinner.un);
                }
            }
    }


    public String getName(){
        return "Aleksander";
    }
    
    public String getSlogan(){
        return "Let them eat cake!";
    }
    
    public Candidate vote(Candidate[] candidates){
        
        if(candidates.length==0){
            return new Candidate_ec22945();
        }
        
        for(int i=0; i<candidates.length; i++){
            if(candidates[i].getName().equals("Mateusz")){
                return new Candidate_ec22945();
            }
        }
        
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes){
        
        if(votes.length==0){
            return new Candidate_ec22945();
        }
        
        Candidate currentWinner= votes[0];
        int count=0;
        int temp=0;
        
        for(int i=0; i<votes.length; i++){
            temp=0;
            for(int j=0; j<votes.length; j++){
                if(votes[i]==votes[j]){
                    temp=temp+1;
                }
            }
            if(temp>count){
                currentWinner=votes[i];
                count=temp;
            }
        }
        
        return currentWinner;
    }
}
