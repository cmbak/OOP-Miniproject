package OOP.ec22697.MP;

import java.util.Scanner;
import java.util.Random;
class Candidate_ec22627 extends Candidate {

	public String getName() {
		//"James" probably best
		return "Flello";
	}

	public String getSlogan() {
		//"More trees!" likely best. Used semi-obscure reference instead
		return "For a brighter future, and a better life";
	}

	public Candidate vote(Candidate[] candidates) {
		Candidate r = new Candidate_ec22627();
		int iLength = 0 ;

		if (candidates.length != 0) r = candidates[0];

		for(int i = 0 ; i < candidates.length; i++)
		{
			if(candidates[i].getSlogan().length() > iLength)
			{
				//Vote for the person with the longest slogan
				r = candidates[i] ;
				iLength = r.getSlogan().length();
			}
		}

		return r;
	}

	public Candidate selectWinner(Candidate[] votes) {
		Candidate r = new Candidate_ec22627();
		int iVotes = 0 ;
		int iWinningVotes = 0 ;
		if (votes.length != 0) r = votes[0];

		for(int i = 0 ; i < votes.length ; i++)
		{
			iVotes = 0 ;

			for(int j = i ; j < votes.length ; j++)
			{
				if(votes[i] == votes[j])
				{
					iVotes++;
				}
			}
			if(iVotes > iWinningVotes)
			{
				iWinningVotes = iVotes ;
				r = votes[i] ;
			}
		}
		return r;
	}

	private static Candidate[] addRandom(Candidate[] candidates , Candidate[] electives , int iLength , int iSeed)
	{
		if(iSeed != 0)
		{
			electives[iLength] = candidates[new Random(iSeed).nextInt(candidates.length)] ;
		}
		else
		{
			electives[iLength] = candidates[new Random().nextInt(candidates.length)] ;
		}
		return electives ;
	}

	//From lesson 1
	private static<T> int numberOfTimes(T x, T[] a) {
		int r = 0;

		// Paste here.
		for(int i = 0 ; i < a.length ; i++)
		{
			if(x == a[i])
			{
				r++ ;
			}
		}
		return r;
	}
	//From lesson 1
	private static <T> T oneMostFrequent(T[] a) {

		T r = a[0]; 
		int iCount = 0 ;
		int iLargestCount = 0 ;
		for(int i = 0 ; i < a.length ; i++)
		{
			iCount = numberOfTimes(a[i] , a) ;
			if(iCount > iLargestCount)
			{
				iLargestCount = iCount ;
				r = a[i] ;
			}
		}
		return r;
	}

	private static void runElection(Candidate[] candidates , Candidate[] electives , int iLength , int iRepetitions , Candidate counter)
	{
		int i = 0 ;
		Candidate[] winners = new Candidate[iRepetitions] ;
		Candidate mostCommon ;

		if(iLength > 0)
		{
			for(i = 0 ; i < iRepetitions ; i++)
			{
				winners[i] = counter.selectWinner(electives) ;
			}

			mostCommon = oneMostFrequent(winners);
			System.out.println("The most common winner is " + mostCommon.getName() + ".") ;
			System.out.println("Other winners:");
			for(i = 0 ; i < iLength ; i++)
			{
				if(winners[i] != mostCommon)
				{
					System.out.println(winners[i].getName());
				}
			}
		}
		else
		{
			System.out.println("Nobody entered the election") ;
		}
	}

	public static void main(String[] args)
	{
		int i = 0, iLength = 0, iSeed = 0, iRepetitions = 0 ;
		Candidate[] candidates = A3.getCandidateArray() ;
		Candidate[] electives = new Candidate[candidates.length] ;
		String szChoice = "" ;
		String szName = "" ;
		Scanner szKeyboard = new Scanner(System.in) ;

		System.out.println("= Running Repeated Elections =") ;
		do
		{
			szChoice = "" ;
			System.out.println("Candidates are") ;
			if(electives[0] == null)
			{
				System.out.println("none") ;
			}
			else
			{
				for(i = 0 ; i < iLength ; i++) 
				{
					System.out.println(i+1 + ") " + electives[i].getName()) ;
				}
			}
			System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
			szChoice = szKeyboard.nextLine() ;

			switch(szChoice)
			{
			case "a":
				System.out.println("Please enter a username.") ;
				szChoice = szKeyboard.nextLine() ;
				electives[iLength] = A3.getByUsername(szChoice , candidates) ;
				iLength++;
				break;
			case "b":
				System.out.println("Enter random seed or 0 for no seed. (The same seed will produce the same candidates.)") ;
				iSeed = Integer.parseInt(szKeyboard.nextLine()) ;

				electives = addRandom(candidates , electives , iLength , iSeed) ;
				iLength++ ;
				break ;
			case "c":
				System.out.println("Who should do the counting? ");
				szName = szKeyboard.nextLine() ;
				System.out.println("How many times shall we run the election?");
				iRepetitions = Integer.parseInt(szKeyboard.nextLine()) ;

				runElection(candidates , electives , iLength , iRepetitions , A3.getByUsername(szName, candidates)) ;
				break ;
			}
		}while(!szChoice.equals("c")) ;
	}
}
