package OOP.ec22697.MP;// File Candidate_ec221028.java
//
// Machine generated stub for Assignment 3
// Sefa A. Y.
// test 3

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

class Candidate_ec221028 extends Candidate {

    public static void main (String[] args){
		Candidate myself = new Candidate_ec221028();
		final Candidate trustedFairJudge = new Candidate_eey577(); //known to have a fair selectWinner method
		Candidate[] allCandidates = A3.getCandidateArray();
		allCandidates = filterNoNameCandidates(allCandidates);
		ArrayList<Candidate> participants = new ArrayList<Candidate>();
	
		String separator = System.lineSeparator();
        boolean exit = false;
        char choice = ' ';
        do { // main option menu, adapted from A3.java
          choice = getChar(separator + "Would you like to:" + separator +
							"a) add a specific candidate, " + separator +
                            "b) add a candidate at random, " + separator +
							"c) diplay participating candidates, " + separator +
							"d) run an election, " + separator +
							"e) run election n times and find most common winner" + separator +
							"f) exit program");
          switch (choice) {
                  
            case 'a': addSpecificCandidate(participants, allCandidates); break;
                  
            case 'b': addRandomCandidate(participants, allCandidates); break;
                  
            case 'c': printList(participants.toArray(new Candidate[participants.size()] )); break;
                  
            case 'd': runElection(participants.toArray(new Candidate[participants.size()] ), allCandidates, trustedFairJudge); break;
			
			case 'e': runElectionNtimes(participants.toArray(new Candidate[participants.size()] ), allCandidates, trustedFairJudge); break;
	
			case 'f': exit = true; break;
                  
            default: pr("Option '"+choice+"' not available.");
          }
        } while (!exit);
		return;
	}
	
	private static void printList(Candidate[] candidates){
		System.out.println(System.lineSeparator() + "Current Participants: ");
		for (Candidate candidate: candidates){
			System.out.print(candidate.getName() + "(" + candidate.un + ")" + ", " );
		}
		System.out.println("");
		return;
	}
	
	private static void runElection(Candidate[] participants, Candidate[] allCa, Candidate judge){
		if (participants == null || participants.length == 0){
			System.out.println("No participants have been selected yet.");
			return;
		}
        Candidate w = judge.selectWinner(getVotes(participants, allCa));
        pr("The winner is "+w.getName()+" ("+w.un+"): "+w.getSlogan());
		return;
	}
	
	private static void runElectionNtimes(Candidate[] participants, Candidate[] allCa, Candidate judge){
		if (participants == null || participants.length == 0){
			System.out.println("No participants have been selected yet.");
			return;
		}
		System.out.println("How many times would you like to run the election: ");
		int n = inputPositiveInt();
		Candidate[] candidates = new Candidate[n];
		for (int i=0;i<n;i++) {   
            Candidate w = judge.selectWinner(getVotes(participants, allCa));
            candidates[i] = w;
        }
		
		Candidate w = judge.selectWinner(candidates);
		
		pr("The most common winner is "+w.getName()+" ("+w.un+"): "+w.getSlogan());
		return;
	}
	
	// validate user input as integer
	public static int inputPositiveInt(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Input a whole number:");
		boolean valid = false;
		int answer = 0;
		String temporary;
		
		while (valid == false){
			temporary = scanner.nextLine();
			try {
				answer = Integer.parseInt(temporary);
				if (answer < 1){
					System.out.println("Input is not in specified range.");
					throw new Exception();
				}
				valid = true;
			} catch (Exception e){
				System.out.println("Invalid input. Please enter a whole number:");
			}
		}
		return answer;
    }
	
	private static void addSpecificCandidate(ArrayList<Candidate> participants, Candidate[] allCa){	
		String separator = System.lineSeparator();
        boolean exit = false;
        char choice = ' ';
        do { // select candidate option menu
          choice = getChar("Would you like to search for a candidate by their username, name, or slogan? (u/n/s)");
          switch (choice) {
                  
            case 'u': choice = 'u'; exit = true; break;
                  
            case 'n': choice = 'n'; exit = true; break;
                  
            case 's': choice = 's'; exit = true; break;
                  
            default: pr("Option '"+choice+"' not available.");
          }
        } while (!exit);
		
		boolean foundNew = false;
		boolean alreadyIn;
		String target = "";
		Scanner sc = new Scanner(System.in);
		Candidate selectedCandidate = new Candidate_ec221028(); // placeholder so compiler is happy
		while (!foundNew){
			alreadyIn = false;
			System.out.println("Enter term to search for:");
			target = sc.nextLine();
			switch(choice){
				case 'u': selectedCandidate = A3.getByUsername(target, allCa) ; break;
                  
				case 'n': selectedCandidate = getByCandidateName(target, allCa) ; break;
                  
				case 's': selectedCandidate = getBySlogan(target, allCa) ; break;
			}
			
			// check candidate exists
			if (selectedCandidate == null){
				System.out.println("Sorry that candidate does not exist.");
				continue;
			}
			
			// check if candidate already in, no dupes
			for (Candidate c: participants.toArray(new Candidate[participants.size()] )){
				if (selectedCandidate == c){
					System.out.println("Sorry that candidate is already in.");
					alreadyIn = true;
				}
			}
			
			if (alreadyIn == false){
				participants.add(selectedCandidate);
				foundNew = true;
				System.out.println(System.lineSeparator() + "Candidate " + selectedCandidate.getName() + " was selected to participate.");
			}
		}
		return;
	
	}
	
	private static void addRandomCandidate(ArrayList<Candidate> participants, Candidate[] allCa){
		boolean foundNew = false;
		boolean alreadyIn;
		while (!foundNew){
			alreadyIn = false;
			Random rng = new Random();
			int index = rng.nextInt(allCa.length);
			
			for (Candidate c: participants.toArray(new Candidate[participants.size()] )){
				if (allCa[index] == c){
					alreadyIn = true;
				}
			}
			
			if (alreadyIn == false){
				participants.add(allCa[index]);
				foundNew = true;
				System.out.println(System.lineSeparator() + "Candidate " + allCa[index].getName() + " was selected to participate.");
			}
		}
		return;
	}
	
	// used to get real candidates only
	private static Candidate[] filterNoNameCandidates(Candidate[] candidates){
		String temp = "";
		ArrayList<Candidate> realCandidates = new ArrayList<Candidate>();
		for (int i = 0; i < candidates.length; i++){
			temp = candidates[i].getName();
			
			if (temp.length() > 6){
				if (temp.substring(0,7).equals("No name")){
				continue; // not added to new array if they have no name
				}
			}
			
			realCandidates.add(candidates[i]);
        }
		return realCandidates.toArray(new Candidate[realCandidates.size()] );
	}
	
	//adapted from A3.java getByUsername method
	public static Candidate getByCandidateName(String name, Candidate[] ca) {
        for (Candidate c : ca) if (c.getName().equals(name)) return c;
        return null;
    }
	
	//adapted from A3.java getByUsername method
	public static Candidate getBySlogan(String name, Candidate[] ca) {
        for (Candidate c : ca) if (c.getSlogan().equals(name)) return c;
        return null;
    }
	
	// code adapted from A3.java
	private static Candidate[] getVotes(Candidate[] candidates, Citizen[] va) { 
        boolean showErrors = false;
        Candidate[] temp = new Candidate[va.length];    
        int count = 0;
        for (int i=0;i<va.length;i++) {
                try { 
                    temp[count] = va[i].vote(candidates); 
                    //System.err.print(" "+temp[count].getName());
                    count++; // Won't happen if vote throws an exception.
                }
                catch (Exception e) { // 'Spoilt ballot'
                    if (showErrors) e.printStackTrace();
                }   
        }
            
        Candidate[] votes = new Candidate[count];    
        for (int i=0;i<count;i++) votes[i] = temp[i];
        return votes;
    }
	
	// code from A3.java
	// Use Standard Out.
    private static <T> void p(T s) { System.out.print(s);}
    private static <T> void pr(T s) { System.out.println(s);}
    
	// code from A3.java
    // Use Standard In.
    private static Scanner sc() {return new Scanner(System.in);}
	
	// code originally from A3.java
	private static char getChar(String q) {
        pr(q);
        String t = sc().nextLine(); 
        return ( t.length()==0 ? ' ' : t.charAt(0) );
    }
    
    public String getName() {
        return "Mr. Bean";
    }
    
    public String getSlogan() {
        return "Teddy!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec221028();
        if (candidates.length == 0 || candidates == null){
			return r;
		}
		
		// return first candidate with a name exactly six letters long and slogan longer than 10 letters long
		// if no such candidate exists reduce requirement for slogan length until slogan length == 0
		// if no such candidate with name length 6 exists (unlikely) return my own class instance (vote Mr. Bean)
		ArrayList<Candidate> tempList = new ArrayList<Candidate>();
		for (int i = 0; i < candidates.length; i++){
			if (candidates[i].getName().length() == 6){
				tempList.add(candidates[i]);
			}
		}
		
		for (int necessarySloganLength = 10; necessarySloganLength > 0; necessarySloganLength--){
			for (int i = 0; i < tempList.size(); i++){
				if (tempList.get(i).getSlogan().length() > necessarySloganLength){
					return tempList.get(i);
				}
			}
		}
        
        return r;
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec221028();
		if (votes.length == 0 || votes == null){
			return r;
		}
		// winner is decided by candidate with longest slogan, he is very persuasive, and his name easy to remember!
		// name is 2x weighted, shorter name more important than long slogan.
		// score is decided by slogan length - (name length * 2) [long slogan and short name results in higher score]
		int highestScore = -1000;
		int highestScorerIndex = 0;
		int currentVoteScore = 0;
		
		for (int i = 0; i < votes.length; i++){
			currentVoteScore = votes[i].getSlogan().length() - (votes[i].getName().length() * 2);
			
			if (currentVoteScore > highestScore){
				highestScore = currentVoteScore;
				highestScorerIndex = i;
			}
		} 
		
		r = votes[highestScorerIndex];	
        return r;
    }   
}
