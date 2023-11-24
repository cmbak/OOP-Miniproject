package OOP.ec22697.MP;// File Candidate_ec22518.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
class Candidate_ec22518 extends Candidate {
    
    public static void main(String [] args)
    {
        Candidate[] allContributions = A3.getCandidateArray();
        
        ArrayList<Candidate> candidates = new ArrayList<Candidate>();
        
        final char STOP_CODE = 'a';
        
        char keep_going='b';
        
        char input = 'z';
        
        while(keep_going!=STOP_CODE)
        {
            input='z';
            while(input!='c')
            {
                         printCandidates(candidates);
                        printOptions();
                         input = getChar();

                        if(input=='a')
                        {
                            String specific_name = getString("Enter username");

                            while(!(checkUsername(specific_name, allContributions)))
                               {

                                       specific_name=getString("Please enter a valid username");        
                               }

                                    add_Candidate(candidates, getSpecificCandidate(specific_name, allContributions));
                        }

                        else if(input=='b')
                        {
                            add_Candidate(candidates, getRandomCandidate(allContributions));

                        }

                        else{

                            if(input=='c')
                            {
                                String specific_voter = getString("who should count the votes?");

                                 while(!(checkUsername(specific_voter, allContributions)))
                               {

                                       specific_voter=getString("Please enter a valid username");        
                               }



                                int how_many= getInt("How many times should we run the election?");


                                Candidate votee = A3.getByUsername(specific_voter, allContributions);
                                runElection(how_many,candidates, votee, allContributions);

                            }
                        }
                                     
                }
          
             print("Would you like to: " + "\n"+ "a) exit" + "\n" + "b) run the election again");
            
                           keep_going = getChar();
                           
                           while(!(keep_going=='a' || keep_going=='b'))
                           {
                               print("Please enter a valid option [a-b]");
                               keep_going=getChar();
                           }
        }
        
        
        
    }
    
    
    private static void runElection(int how_many_elections, ArrayList<Candidate> candidates, Candidate specific_votee, Candidate[] allContributions)
                           {
                               
                               ArrayList<Candidate> winners = new ArrayList<Candidate>();
                               ArrayList<Candidate> ballotbox = new ArrayList<Candidate>();
        
                                Candidate[] candidate = candidates.toArray(new Candidate[0]);
        
        
                                    if(candidate.length==0)
                                    {
                                        print("Since there are no candidates, there is no winner");
                                    }
                               
                                    else
                                    {
                                        for(int i=0; i<how_many_elections;i++)
                                       {
                                          for(int j=0; j<allContributions.length; j++)
                                          {    


                                              try
                                              {
                                              ballotbox.add(allContributions[j].vote(candidate));
                                              }

                                               catch(Exception a)
                                               {

                                               }

                                           }

                                           Candidate[] votes = ballotbox.toArray(new Candidate[0]);
                                           winners.add(specific_votee.selectWinner(votes));

                                           }

                                       Candidate[] wins = winners.toArray(new Candidate[0]);
                                        printMostCommonWinner(wins);
                                    }
                                
                        
                               
                           }
    private static void printCandidates(ArrayList<Candidate> candidates)
    {
        print("Candidates are ");
        
        if(candidates.size()==0)
        {
            print("None");
            
        }
        
        else
        {
            for(int i=0; i<candidates.size(); i++)
            {
                print(candidates.get(i).getName());
            }
        }
        return;
    }
    
    
  private static <T> void print(T a)
    {
        System.out.println(a);
    }
    
    private static void add_Candidate(ArrayList<Candidate> candidates, Candidate new_candidate)
    {
        candidates.add(new_candidate);
        return;
    }
    
    private static void printOptions()
    {
        print("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
    }
    
    private static char getChar()
    {
        String answer;
        
        Scanner scanner = new Scanner(System.in);
        
        answer = scanner.nextLine();
        
        while(!(answer.length()==0 || answer.charAt(0)=='a' || answer.charAt(0)=='b'|| answer.charAt(0)=='c'))
        {
            print("Please input a valid option [a-c]");
            answer=scanner.nextLine();
        }
        return(answer.charAt(0));
    }
    
    private static String getString(String output)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println(output);
        return scanner.nextLine();
    }
                           
    private static int getInt(String output)
                           {
                               
                               String answer = getString(output);
                               int number;
                               try{
                                   
                                   number = Integer.parseInt(answer);
                                   
                               }
                               
                               catch(Exception a)
                               {
                                   
                                   number=getInt("Please enter a valid integer");
                               }
                               
                               return number;
                           }
                   
    private static boolean checkUsername(String specific_name, Candidate[] allCandidates)
                   {
                       Candidate specific_candidate = A3.getByUsername(specific_name, allCandidates);
                       
                       if(specific_candidate!=null)
                       {
                           return true;
                       }
                       
                       return false;
                   }
    
    private static Candidate getSpecificCandidate(String specific_name, Candidate[] allCandidates)
                   {
                       return A3.getByUsername(specific_name, allCandidates);
                   }
                                     
                                    
    private static Candidate getRandomCandidate(Candidate[] allCandidates)
     {
         Random num = new Random();
         
         int random_number = num.nextInt(allCandidates.length);
         while(allCandidates[random_number]==null)
         {
             random_number = num.nextInt(allCandidates.length);
         }
         
         return allCandidates[random_number];
    }
                           
    private static void printMostCommonWinner(Candidate[] winners)
                           {
                                
                                if(winners.length==0)
                                {
                                    print("There is no winner");
                                    
                                    return;
                                }
        
                                if(winners.length==1)
                                {
                                    print("The winner is " + winners[0].getName());
                                    return;
                                }
                                Candidate currentWinner = winners[0];
                                int highest_count =0;

                                for(int i=0; i<winners.length; i++)
                                {
                                        int current_count=0;
                                        for(int j=0; j<winners.length; j++)
                                        {
                                        if(winners[i]==winners[j])
                                            {
                                                    current_count++;
                                            }
                                        }

                                        if(current_count>highest_count)
                                        {
                                            currentWinner=winners[i];
                                            highest_count=current_count;
                                        }
                                }
                               
                               print("The most common winner is " + currentWinner.getName());
                           }
    
   public String getName() {
        return "Sisi";
    }
    
    public String getSlogan() {
        return "More Bridges!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        // If array is empty, return instance of this class.
        if (candidates.length == 0) 
            return new Candidate_ec22518();
        
        // First search for Utfur on the list of candidates.
         int count=0;
        
       for(int i=0; i<candidates.length; i++)
       {
            if (candidates[i].getSlogan().equals("More Bridges!"))
            {
                return candidates[i];
            }
       }
        
        
        for(int j=0; j<candidates.length ; j++)
        {
        
            if(candidates[j].getName().equals("Andrew"))
            {
                return candidates[j];
            }
        }
            
            
        // Otherwise, choose the last candidate
        return candidates[0]; 
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        // If array is empty, return instance of this class.
        if (votes.length == 0) 
            return new Candidate_ec22518();
        
        Candidate currentWinner = votes[0];
	int highest_count =0;

	for(int i=0; i<votes.length; i++)
	{
    		int current_count=0;
    		for(int j=0; j<votes.length; j++)
    		{
        	if(votes[i]==votes[j])
        		{
            			current_count++;
        		}
    		}
    
    		if(current_count>highest_count)
    		{
        		currentWinner=votes[i];
        		highest_count=current_count;
    		}
	}
        
        return currentWinner;
    }
    
}
