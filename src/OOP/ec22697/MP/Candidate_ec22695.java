package OOP.ec22697.MP;// File Candidate_ec22695.java
//
// Machine generated stub for Assignment 2
//Lab4: Randomn class used to vote in my Candidate Class

import java.util.Random;
import java.util.Vector;
import java.util.Scanner;

class Candidate_ec22695 extends Candidate
{   /*                                              Assignment 3                                              */ 
    public static Vector<Candidate> winning_Candidates = new Vector<Candidate>();
    public static Vector<Candidate> Election_candidates = new Vector<Candidate>();;
    public static Candidate[] TotalCandidates;
    public static Candidate Candidate_WinnerCalculator = new Candidate_ec22695();

        public static void main(String[] args) 
        {
            TotalCandidates = getCandidateArray();
            Boolean Exit = false;
            while(Exit == false)
            {
                Display_ElectionCandidates();
                System.out.println("\nWould you like to 0)exit a) add a specific candidate b) add a candidate at random c) run the election d) clear current Election Candidates?");
                String User_Option =  (new Scanner(System.in)).nextLine();
                Exit = Process_Option_BeforeElection(User_Option);
            }
        }



        //public void Process Option
        public static Boolean Process_Option_BeforeElection(String User_Option)
        {
            if(User_Option.equals("0"))
            {
                return true;
            }
            else if(User_Option.equals("a"))
            {
                Add_new_candidate();
            }
            else if(User_Option.equals("b"))
            {
                Add_new_candidate_Random();
            }
            else if(User_Option.equals("c"))
            {
                if(Election_candidates.size() < 1)
                {
                    System.out.println("A minimum of two candidates required to run an election");
                }
                else
                {
                    RunElection();  
                }
              
            }
            else if(User_Option.equals("d"))
            {
                Election_candidates.clear();
            }
            else
            {
                System.out.println("You must enter 0, a, b, c or d to select and option");
                
            }
            return false;

        }

        //add student at random
        public static void Add_new_candidate_Random()
        {
            //gets a candidate from indexes 0 - 545
            Candidate choosen_Candidate = TotalCandidates[(new Random()).nextInt(546)];
            while(Election_candidates.contains(choosen_Candidate))
            {
                choosen_Candidate = TotalCandidates[(new Random()).nextInt(546)];
            }
            System.out.println("The candidate added was: " + choosen_Candidate.getName());
            Election_candidates.add(choosen_Candidate);
        }

        //Finds Candidate From Total Candidates Array
        public static Candidate get_Choosen_Candidate(String message)
        {
            Boolean ValidInput = false;
            Candidate choosen_Candidate_Object = TotalCandidates[0];
            while(ValidInput == false)
            {
                System.out.println(message);
                String Choosen_Candidate_Username = (new Scanner(System.in)).nextLine();
                for(Candidate candidate : TotalCandidates)
                {
                    if((candidate.un).equals(Choosen_Candidate_Username))
                    {
                        ValidInput = true;
                        choosen_Candidate_Object = candidate;
                    }
                }

                if(ValidInput == false)
                {
                    System.out.println("The entered candidate username was not found");
                }
            }

            return choosen_Candidate_Object;
        }

        // This method adds a new candidate to the election candidate list
        public static void Add_new_candidate()
        {
            System.out.println("You are about to add a new candidate to the election. Type Y to continue. Press any other key to exit.");
            String Confirmation = (new Scanner(System.in)).nextLine();
            if(Confirmation.equals("Y"))
            {
                if(Election_candidates.size() < 546)
                {
                    Candidate choosen_Candidate = get_Choosen_Candidate("\nEnter the username of the Candidate you would like to add: ");
                    if(Election_candidates.contains(choosen_Candidate))
                    {
                        System.out.println("This candidate is already in the election");
                    }
                    else
                    {
                        System.out.println("The candidate added was: " + choosen_Candidate.getName());
                        Election_candidates.add(choosen_Candidate);
                    }
                }
                else
                {
                    System.out.println("You have reached the maximum limit of Election Candidates.");
                }
            }

        }


            //display current candidates
            public static void Display_ElectionCandidates()
            {
                if(Election_candidates.size() == 0)
                {
                    System.out.println("\nThere are no candidates in this election ");
                }
                else
                {
                    System.out.println("\nCurrent Election Candidates: ");
                    for(Candidate candidate : Election_candidates)
                    {
                        System.out.println("\tName: " + candidate.getName());
                        System.out.println("\tSlogan: "+ candidate.getSlogan());
                        System.out.println("");
        
                    }

                }
    
            }

        //Get Winner (via choosen student)
        public static void RunElection()
        {
            System.out.println("\nWho Should Count the Votes");
            Candidate_WinnerCalculator = get_Choosen_Candidate("Enter the username of a candidate who should count the votes: ");
            DoElection_ManyTimes();


            System.out.println();
            Boolean Exit = false;
            while(Exit == false)
            {
                System.out.println("\nWould you like to 0) exit a) run same election many times b) view Candidate Choosing winner c) Change candidate counting the votes  d) View Top 5 winning Candidates");
                String User_Option =  (new Scanner(System.in)).nextLine();
                Exit = Process_Option_DuringElection(User_Option);
            }


        }
        
        public static Boolean Process_Option_DuringElection(String User_Option)
        {
            if(User_Option.equals("0"))
            {
                return true;
            }
            if(User_Option.equals("a"))
            {
                DoElection_ManyTimes();
            }
            else if(User_Option.equals("b"))
            {
                System.out.println("\nThe candidate that is choosing the winner for the election is: ");
                System.out.println("Candidate: " + Candidate_WinnerCalculator.un);
                System.out.println("Name: " + Candidate_WinnerCalculator.getName());
            }
            else if(User_Option.equals("c"))
            {
                System.out.println("\nWho Should Count the Votes");
                Candidate_WinnerCalculator = get_Choosen_Candidate("Enter the username of a candidate who should count the votes: ");
            }
            else if(User_Option.equals("d"))
            {
                DisplayTop5_WinningCandidates();
            }
            else
            {
                
                System.out.println("You must enter 0, a, b, c or d to select and option");
                
            }
            return false;

        }

        //Display Top5 winners from the election
        public static void DisplayTop5_WinningCandidates()
        {
            Vector<Candidate> Clone_Winner_Vector =  new Vector<Candidate>();
            for(Candidate candidate : winning_Candidates)
            {
                Clone_Winner_Vector.add(candidate);
            }

            int Count = 1;
            System.out.println("\nTop5: Winners from the election");
            while(Count <= 5 && Clone_Winner_Vector.size() > 0)
            {
                Candidate[] Winning_Candidates_Array = new Candidate[Clone_Winner_Vector.size()];

                //adding items into Winning_Candidates_Array from Clone_Winner_Vector
                for(int i = 0; i < Clone_Winner_Vector.size(); i++)
                {
                    Winning_Candidates_Array[i] = Clone_Winner_Vector.get(i);
                }

                Candidate TopWinner = (new Candidate_ec22695()).selectWinner(Winning_Candidates_Array);

                System.out.println( Count + ": " + TopWinner.getName());
                while(Clone_Winner_Vector.contains(TopWinner))
                {
                    Clone_Winner_Vector.remove(TopWinner);
                }
                Count++;
            }
        }

        //run election the ammount of times specified by the user
        public static void DoElection_ManyTimes()
        {
            
            winning_Candidates.clear();
            int NumberofTimes = GetInteger("\nHow many times shall we run the election?");
            while(NumberofTimes <= 0 || NumberofTimes > 500)
            {
                System.out.println("You must enter a value between 1-500");
                NumberofTimes = GetInteger("\nHow many times shall we run the election?");
            }

            System.out.println();
            Display_ElectionCandidates();
            for(int i = 0; i < NumberofTimes; i++)
            {


                try
                {
                    Candidate winning_candidate = Candidate_WinnerCalculator.selectWinner(get_votes());
                    winning_Candidates.add(winning_candidate);
                }
                catch(Exception e)
                {
                    Candidate_WinnerCalculator = new Candidate_ec22695();
                    Candidate winning_candidate = Candidate_WinnerCalculator.selectWinner(get_votes());
                    winning_Candidates.add(winning_candidate);
                }


               
            }

            DisplayCommonWinner();

        }
        
        //gets integer from user
        public static int GetInteger(String message)
        {
            int Entered_INT = 0;
            boolean ValidInt = false;
            while(ValidInt == false)
            {
                try
                {
                    System.out.print(message);//buffer
                    Entered_INT = Integer.parseInt((new Scanner(System.in)).nextLine());
                    ValidInt = true;
                }
                catch(NumberFormatException exception)
                {
                    System.out.println("You must enter an integer value");
                }

            }
            
              return Entered_INT;
        }


        //Get Votes from every Candidate
        public static Candidate[] get_votes()
        {
            Candidate[] votes = new Candidate[546];
            Candidate[] Election_Candidate_Array = new Candidate[Election_candidates.size()];

            //adding items into Election_Candidate_Array from Election_candidates (the vector class variable)
            for(int i = 0; i < Election_candidates.size(); i++)
            {
                Election_Candidate_Array[i] = Election_candidates.get(i);
            }

            for(int i = 0; i < 546; i++)
            {
                try 
                {
                    votes[i] = TotalCandidates[i].vote(Election_Candidate_Array);
                } 
                catch (Exception e) //if vote method of students fails, voting will be done from my class
                {
                    votes[i] = (new Candidate_ec22695()).vote(Election_Candidate_Array);
                }
                
            }

            return votes;
        }



        //get most common winner
        public static void DisplayCommonWinner()
        {
            if(winning_Candidates.toArray().length == 0)
            {
                System.out.println("No elections have been won");
            }
            else
            {
                Candidate[] Winning_Candidates_Array = new Candidate[winning_Candidates.size()];

                //adding items into Winning_Candidates_Array from winning_Candidates (the vector class variable)
                for(int i = 0; i < winning_Candidates.size(); i++)
                {
                    Winning_Candidates_Array[i] = winning_Candidates.get(i);
                }
                try
                {

                    Candidate CommonWinner = Candidate_WinnerCalculator.selectWinner(Winning_Candidates_Array);
                    System.out.println("The most common winner in the elections is: ");
                    System.out.println(CommonWinner.getName() +" whos slogan is, " + CommonWinner.getSlogan());
                }
                catch(Exception e)
                {
                    Candidate_WinnerCalculator = new Candidate_ec22695();
                    Candidate CommonWinner = Candidate_WinnerCalculator.selectWinner(Winning_Candidates_Array);
                    System.out.println("The most common winner in the elections is: ");
                    System.out.println(CommonWinner.getName() +" whos slogan is, " + CommonWinner.getSlogan());
                }

            }

        }


        /* From A2 */ 
        public static Candidate[] getCandidateArray() {
    
            Candidate[] ca = new Candidate[546];
        
            ca[0] = new Candidate_ah21407(); ca[0].un = "ah21407";
            ca[1] = new Candidate_bt21057(); ca[1].un = "bt21057";
            ca[2] = new Candidate_cb21793(); ca[2].un = "cb21793";
            ca[3] = new Candidate_cb21806(); ca[3].un = "cb21806";
            ca[4] = new Candidate_ec19096(); ca[4].un = "ec19096";
            ca[5] = new Candidate_ec19389(); ca[5].un = "ec19389";
            ca[6] = new Candidate_ec20258(); ca[6].un = "ec20258";
            ca[7] = new Candidate_ec20306(); ca[7].un = "ec20306";
            ca[8] = new Candidate_ec20315(); ca[8].un = "ec20315";
            ca[9] = new Candidate_ec20405(); ca[9].un = "ec20405";
            ca[10] = new Candidate_ec20589(); ca[10].un = "ec20589";
            ca[11] = new Candidate_ec20615(); ca[11].un = "ec20615";
            ca[12] = new Candidate_ec20803(); ca[12].un = "ec20803";
            ca[13] = new Candidate_ec211030(); ca[13].un = "ec211030";
            ca[14] = new Candidate_ec211032(); ca[14].un = "ec211032";
            ca[15] = new Candidate_ec211044(); ca[15].un = "ec211044";
            ca[16] = new Candidate_ec211045(); ca[16].un = "ec211045";
            ca[17] = new Candidate_ec21322(); ca[17].un = "ec21322";
            ca[18] = new Candidate_ec21324(); ca[18].un = "ec21324";
            ca[19] = new Candidate_ec21328(); ca[19].un = "ec21328";
            ca[20] = new Candidate_ec21347(); ca[20].un = "ec21347";
            ca[21] = new Candidate_ec21349(); ca[21].un = "ec21349";
            ca[22] = new Candidate_ec21362(); ca[22].un = "ec21362";
            ca[23] = new Candidate_ec21370(); ca[23].un = "ec21370";
            ca[24] = new Candidate_ec21385(); ca[24].un = "ec21385";
            ca[25] = new Candidate_ec21403(); ca[25].un = "ec21403";
            ca[26] = new Candidate_ec21413(); ca[26].un = "ec21413";
            ca[27] = new Candidate_ec21416(); ca[27].un = "ec21416";
            ca[28] = new Candidate_ec21469(); ca[28].un = "ec21469";
            ca[29] = new Candidate_ec21480(); ca[29].un = "ec21480";
            ca[30] = new Candidate_ec21490(); ca[30].un = "ec21490";
            ca[31] = new Candidate_ec21493(); ca[31].un = "ec21493";
            ca[32] = new Candidate_ec21494(); ca[32].un = "ec21494";
            ca[33] = new Candidate_ec21499(); ca[33].un = "ec21499";
            ca[34] = new Candidate_ec21500(); ca[34].un = "ec21500";
            ca[35] = new Candidate_ec21504(); ca[35].un = "ec21504";
            ca[36] = new Candidate_ec21521(); ca[36].un = "ec21521";
            ca[37] = new Candidate_ec21551(); ca[37].un = "ec21551";
            ca[38] = new Candidate_ec21564(); ca[38].un = "ec21564";
            ca[39] = new Candidate_ec21571(); ca[39].un = "ec21571";
            ca[40] = new Candidate_ec21575(); ca[40].un = "ec21575";
            ca[41] = new Candidate_ec21578(); ca[41].un = "ec21578";
            ca[42] = new Candidate_ec21582(); ca[42].un = "ec21582";
            ca[43] = new Candidate_ec21603(); ca[43].un = "ec21603";
            ca[44] = new Candidate_ec21636(); ca[44].un = "ec21636";
            ca[45] = new Candidate_ec21637(); ca[45].un = "ec21637";
            ca[46] = new Candidate_ec21645(); ca[46].un = "ec21645";
            ca[47] = new Candidate_ec21670(); ca[47].un = "ec21670";
            ca[48] = new Candidate_ec21693(); ca[48].un = "ec21693";
            ca[49] = new Candidate_ec21725(); ca[49].un = "ec21725";
            ca[50] = new Candidate_ec21814(); ca[50].un = "ec21814";
            ca[51] = new Candidate_ec21823(); ca[51].un = "ec21823";
            ca[52] = new Candidate_ec21825(); ca[52].un = "ec21825";
            ca[53] = new Candidate_ec21841(); ca[53].un = "ec21841";
            ca[54] = new Candidate_ec21859(); ca[54].un = "ec21859";
            ca[55] = new Candidate_ec221002(); ca[55].un = "ec221002";
            ca[56] = new Candidate_ec221003(); ca[56].un = "ec221003";
            ca[57] = new Candidate_ec221004(); ca[57].un = "ec221004";
            ca[58] = new Candidate_ec221006(); ca[58].un = "ec221006";
            ca[59] = new Candidate_ec221008(); ca[59].un = "ec221008";
            ca[60] = new Candidate_ec221011(); ca[60].un = "ec221011";
            ca[61] = new Candidate_ec221012(); ca[61].un = "ec221012";
            ca[62] = new Candidate_ec221013(); ca[62].un = "ec221013";
            ca[63] = new Candidate_ec221014(); ca[63].un = "ec221014";
            ca[64] = new Candidate_ec221015(); ca[64].un = "ec221015";
            ca[65] = new Candidate_ec221016(); ca[65].un = "ec221016";
            ca[66] = new Candidate_ec221017(); ca[66].un = "ec221017";
            ca[67] = new Candidate_ec221021(); ca[67].un = "ec221021";
            ca[68] = new Candidate_ec221022(); ca[68].un = "ec221022";
            ca[69] = new Candidate_ec221023(); ca[69].un = "ec221023";
            ca[70] = new Candidate_ec221024(); ca[70].un = "ec221024";
            ca[71] = new Candidate_ec221025(); ca[71].un = "ec221025";
            ca[72] = new Candidate_ec221026(); ca[72].un = "ec221026";
            ca[73] = new Candidate_ec221028(); ca[73].un = "ec221028";
            ca[74] = new Candidate_ec221085(); ca[74].un = "ec221085";
            ca[75] = new Candidate_ec221088(); ca[75].un = "ec221088";
            ca[76] = new Candidate_ec221099(); ca[76].un = "ec221099";
            ca[77] = new Candidate_ec221136(); ca[77].un = "ec221136";
            ca[78] = new Candidate_ec221148(); ca[78].un = "ec221148";
            ca[79] = new Candidate_ec221149(); ca[79].un = "ec221149";
            ca[80] = new Candidate_ec221150(); ca[80].un = "ec221150";
            ca[81] = new Candidate_ec221151(); ca[81].un = "ec221151";
            ca[82] = new Candidate_ec221156(); ca[82].un = "ec221156";
            ca[83] = new Candidate_ec221159(); ca[83].un = "ec221159";
            ca[84] = new Candidate_ec221162(); ca[84].un = "ec221162";
            ca[85] = new Candidate_ec221166(); ca[85].un = "ec221166";
            ca[86] = new Candidate_ec221174(); ca[86].un = "ec221174";
            ca[87] = new Candidate_ec221183(); ca[87].un = "ec221183";
            ca[88] = new Candidate_ec221185(); ca[88].un = "ec221185";
            ca[89] = new Candidate_ec221193(); ca[89].un = "ec221193";
            ca[90] = new Candidate_ec221204(); ca[90].un = "ec221204";
            ca[91] = new Candidate_ec221207(); ca[91].un = "ec221207";
            ca[92] = new Candidate_ec221208(); ca[92].un = "ec221208";
            ca[93] = new Candidate_ec221218(); ca[93].un = "ec221218";
            ca[94] = new Candidate_ec221247(); ca[94].un = "ec221247";
            ca[95] = new Candidate_ec22371(); ca[95].un = "ec22371";
            ca[96] = new Candidate_ec22406(); ca[96].un = "ec22406";
            ca[97] = new Candidate_ec22409(); ca[97].un = "ec22409";
            ca[98] = new Candidate_ec22411(); ca[98].un = "ec22411";
            ca[99] = new Candidate_ec22412(); ca[99].un = "ec22412";
            ca[100] = new Candidate_ec22413(); ca[100].un = "ec22413";
            ca[101] = new Candidate_ec22414(); ca[101].un = "ec22414";
            ca[102] = new Candidate_ec22415(); ca[102].un = "ec22415";
            ca[103] = new Candidate_ec22418(); ca[103].un = "ec22418";
            ca[104] = new Candidate_ec22419(); ca[104].un = "ec22419";
            ca[105] = new Candidate_ec22420(); ca[105].un = "ec22420";
            ca[106] = new Candidate_ec22421(); ca[106].un = "ec22421";
            ca[107] = new Candidate_ec22422(); ca[107].un = "ec22422";
            ca[108] = new Candidate_ec22423(); ca[108].un = "ec22423";
            ca[109] = new Candidate_ec22424(); ca[109].un = "ec22424";
            ca[110] = new Candidate_ec22425(); ca[110].un = "ec22425";
            ca[111] = new Candidate_ec22426(); ca[111].un = "ec22426";
            ca[112] = new Candidate_ec22429(); ca[112].un = "ec22429";
            ca[113] = new Candidate_ec22430(); ca[113].un = "ec22430";
            ca[114] = new Candidate_ec22431(); ca[114].un = "ec22431";
            ca[115] = new Candidate_ec22432(); ca[115].un = "ec22432";
            ca[116] = new Candidate_ec22433(); ca[116].un = "ec22433";
            ca[117] = new Candidate_ec22434(); ca[117].un = "ec22434";
            ca[118] = new Candidate_ec22435(); ca[118].un = "ec22435";
            ca[119] = new Candidate_ec22436(); ca[119].un = "ec22436";
            ca[120] = new Candidate_ec22437(); ca[120].un = "ec22437";
            ca[121] = new Candidate_ec22439(); ca[121].un = "ec22439";
            ca[122] = new Candidate_ec22440(); ca[122].un = "ec22440";
            ca[123] = new Candidate_ec22441(); ca[123].un = "ec22441";
            ca[124] = new Candidate_ec22442(); ca[124].un = "ec22442";
            ca[125] = new Candidate_ec22443(); ca[125].un = "ec22443";
            ca[126] = new Candidate_ec22445(); ca[126].un = "ec22445";
            ca[127] = new Candidate_ec22446(); ca[127].un = "ec22446";
            ca[128] = new Candidate_ec22447(); ca[128].un = "ec22447";
            ca[129] = new Candidate_ec22448(); ca[129].un = "ec22448";
            ca[130] = new Candidate_ec22449(); ca[130].un = "ec22449";
            ca[131] = new Candidate_ec22450(); ca[131].un = "ec22450";
            ca[132] = new Candidate_ec22451(); ca[132].un = "ec22451";
            ca[133] = new Candidate_ec22452(); ca[133].un = "ec22452";
            ca[134] = new Candidate_ec22454(); ca[134].un = "ec22454";
            ca[135] = new Candidate_ec22455(); ca[135].un = "ec22455";
            ca[136] = new Candidate_ec22456(); ca[136].un = "ec22456";
            ca[137] = new Candidate_ec22459(); ca[137].un = "ec22459";
            ca[138] = new Candidate_ec22461(); ca[138].un = "ec22461";
            ca[139] = new Candidate_ec22462(); ca[139].un = "ec22462";
            ca[140] = new Candidate_ec22463(); ca[140].un = "ec22463";
            ca[141] = new Candidate_ec22464(); ca[141].un = "ec22464";
            ca[142] = new Candidate_ec22465(); ca[142].un = "ec22465";
            ca[143] = new Candidate_ec22466(); ca[143].un = "ec22466";
            ca[144] = new Candidate_ec22467(); ca[144].un = "ec22467";
            ca[145] = new Candidate_ec22468(); ca[145].un = "ec22468";
            ca[146] = new Candidate_ec22469(); ca[146].un = "ec22469";
            ca[147] = new Candidate_ec22471(); ca[147].un = "ec22471";
            ca[148] = new Candidate_ec22472(); ca[148].un = "ec22472";
            ca[149] = new Candidate_ec22473(); ca[149].un = "ec22473";
            ca[150] = new Candidate_ec22474(); ca[150].un = "ec22474";
            ca[151] = new Candidate_ec22475(); ca[151].un = "ec22475";
            ca[152] = new Candidate_ec22476(); ca[152].un = "ec22476";
            ca[153] = new Candidate_ec22477(); ca[153].un = "ec22477";
            ca[154] = new Candidate_ec22478(); ca[154].un = "ec22478";
            ca[155] = new Candidate_ec22479(); ca[155].un = "ec22479";
            ca[156] = new Candidate_ec22480(); ca[156].un = "ec22480";
            ca[157] = new Candidate_ec22484(); ca[157].un = "ec22484";
            ca[158] = new Candidate_ec22485(); ca[158].un = "ec22485";
            ca[159] = new Candidate_ec22486(); ca[159].un = "ec22486";
            ca[160] = new Candidate_ec22487(); ca[160].un = "ec22487";
            ca[161] = new Candidate_ec22488(); ca[161].un = "ec22488";
            ca[162] = new Candidate_ec22489(); ca[162].un = "ec22489";
            ca[163] = new Candidate_ec22492(); ca[163].un = "ec22492";
            ca[164] = new Candidate_ec22493(); ca[164].un = "ec22493";
            ca[165] = new Candidate_ec22494(); ca[165].un = "ec22494";
            ca[166] = new Candidate_ec22495(); ca[166].un = "ec22495";
            ca[167] = new Candidate_ec22496(); ca[167].un = "ec22496";
            ca[168] = new Candidate_ec22497(); ca[168].un = "ec22497";
            ca[169] = new Candidate_ec22501(); ca[169].un = "ec22501";
            ca[170] = new Candidate_ec22503(); ca[170].un = "ec22503";
            ca[171] = new Candidate_ec22504(); ca[171].un = "ec22504";
            ca[172] = new Candidate_ec22507(); ca[172].un = "ec22507";
            ca[173] = new Candidate_ec22508(); ca[173].un = "ec22508";
            ca[174] = new Candidate_ec22510(); ca[174].un = "ec22510";
            ca[175] = new Candidate_ec22513(); ca[175].un = "ec22513";
            ca[176] = new Candidate_ec22514(); ca[176].un = "ec22514";
            ca[177] = new Candidate_ec22515(); ca[177].un = "ec22515";
            ca[178] = new Candidate_ec22517(); ca[178].un = "ec22517";
            ca[179] = new Candidate_ec22518(); ca[179].un = "ec22518";
            ca[180] = new Candidate_ec22519(); ca[180].un = "ec22519";
            ca[181] = new Candidate_ec22520(); ca[181].un = "ec22520";
            ca[182] = new Candidate_ec22521(); ca[182].un = "ec22521";
            ca[183] = new Candidate_ec22522(); ca[183].un = "ec22522";
            ca[184] = new Candidate_ec22524(); ca[184].un = "ec22524";
            ca[185] = new Candidate_ec22525(); ca[185].un = "ec22525";
            ca[186] = new Candidate_ec22526(); ca[186].un = "ec22526";
            ca[187] = new Candidate_ec22529(); ca[187].un = "ec22529";
            ca[188] = new Candidate_ec22530(); ca[188].un = "ec22530";
            ca[189] = new Candidate_ec22532(); ca[189].un = "ec22532";
            ca[190] = new Candidate_ec22533(); ca[190].un = "ec22533";
            ca[191] = new Candidate_ec22534(); ca[191].un = "ec22534";
            ca[192] = new Candidate_ec22535(); ca[192].un = "ec22535";
            ca[193] = new Candidate_ec22537(); ca[193].un = "ec22537";
            ca[194] = new Candidate_ec22541(); ca[194].un = "ec22541";
            ca[195] = new Candidate_ec22542(); ca[195].un = "ec22542";
            ca[196] = new Candidate_ec22543(); ca[196].un = "ec22543";
            ca[197] = new Candidate_ec22545(); ca[197].un = "ec22545";
            ca[198] = new Candidate_ec22546(); ca[198].un = "ec22546";
            ca[199] = new Candidate_ec22547(); ca[199].un = "ec22547";
            ca[200] = new Candidate_ec22548(); ca[200].un = "ec22548";
            ca[201] = new Candidate_ec22549(); ca[201].un = "ec22549";
            ca[202] = new Candidate_ec22551(); ca[202].un = "ec22551";
            ca[203] = new Candidate_ec22552(); ca[203].un = "ec22552";
            ca[204] = new Candidate_ec22553(); ca[204].un = "ec22553";
            ca[205] = new Candidate_ec22556(); ca[205].un = "ec22556";
            ca[206] = new Candidate_ec22557(); ca[206].un = "ec22557";
            ca[207] = new Candidate_ec22558(); ca[207].un = "ec22558";
            ca[208] = new Candidate_ec22559(); ca[208].un = "ec22559";
            ca[209] = new Candidate_ec22560(); ca[209].un = "ec22560";
            ca[210] = new Candidate_ec22561(); ca[210].un = "ec22561";
            ca[211] = new Candidate_ec22562(); ca[211].un = "ec22562";
            ca[212] = new Candidate_ec22563(); ca[212].un = "ec22563";
            ca[213] = new Candidate_ec22566(); ca[213].un = "ec22566";
            ca[214] = new Candidate_ec22568(); ca[214].un = "ec22568";
            ca[215] = new Candidate_ec22569(); ca[215].un = "ec22569";
            ca[216] = new Candidate_ec22570(); ca[216].un = "ec22570";
            ca[217] = new Candidate_ec22572(); ca[217].un = "ec22572";
            ca[218] = new Candidate_ec22573(); ca[218].un = "ec22573";
            ca[219] = new Candidate_ec22576(); ca[219].un = "ec22576";
            ca[220] = new Candidate_ec22578(); ca[220].un = "ec22578";
            ca[221] = new Candidate_ec22579(); ca[221].un = "ec22579";
            ca[222] = new Candidate_ec22581(); ca[222].un = "ec22581";
            ca[223] = new Candidate_ec22582(); ca[223].un = "ec22582";
            ca[224] = new Candidate_ec22583(); ca[224].un = "ec22583";
            ca[225] = new Candidate_ec22585(); ca[225].un = "ec22585";
            ca[226] = new Candidate_ec22586(); ca[226].un = "ec22586";
            ca[227] = new Candidate_ec22587(); ca[227].un = "ec22587";
            ca[228] = new Candidate_ec22588(); ca[228].un = "ec22588";
            ca[229] = new Candidate_ec22589(); ca[229].un = "ec22589";
            ca[230] = new Candidate_ec22591(); ca[230].un = "ec22591";
            ca[231] = new Candidate_ec22592(); ca[231].un = "ec22592";
            ca[232] = new Candidate_ec22593(); ca[232].un = "ec22593";
            ca[233] = new Candidate_ec22595(); ca[233].un = "ec22595";
            ca[234] = new Candidate_ec22596(); ca[234].un = "ec22596";
            ca[235] = new Candidate_ec22597(); ca[235].un = "ec22597";
            ca[236] = new Candidate_ec22598(); ca[236].un = "ec22598";
            ca[237] = new Candidate_ec22599(); ca[237].un = "ec22599";
            ca[238] = new Candidate_ec22600(); ca[238].un = "ec22600";
            ca[239] = new Candidate_ec22601(); ca[239].un = "ec22601";
            ca[240] = new Candidate_ec22602(); ca[240].un = "ec22602";
            ca[241] = new Candidate_ec22603(); ca[241].un = "ec22603";
            ca[242] = new Candidate_ec22605(); ca[242].un = "ec22605";
            ca[243] = new Candidate_ec22607(); ca[243].un = "ec22607";
            ca[244] = new Candidate_ec22609(); ca[244].un = "ec22609";
            ca[245] = new Candidate_ec22612(); ca[245].un = "ec22612";
            ca[246] = new Candidate_ec22613(); ca[246].un = "ec22613";
            ca[247] = new Candidate_ec22614(); ca[247].un = "ec22614";
            ca[248] = new Candidate_ec22615(); ca[248].un = "ec22615";
            ca[249] = new Candidate_ec22616(); ca[249].un = "ec22616";
            ca[250] = new Candidate_ec22617(); ca[250].un = "ec22617";
            ca[251] = new Candidate_ec22618(); ca[251].un = "ec22618";
            ca[252] = new Candidate_ec22619(); ca[252].un = "ec22619";
            ca[253] = new Candidate_ec22621(); ca[253].un = "ec22621";
            ca[254] = new Candidate_ec22623(); ca[254].un = "ec22623";
            ca[255] = new Candidate_ec22625(); ca[255].un = "ec22625";
            ca[256] = new Candidate_ec22626(); ca[256].un = "ec22626";
            ca[257] = new Candidate_ec22627(); ca[257].un = "ec22627";
            ca[258] = new Candidate_ec22628(); ca[258].un = "ec22628";
            ca[259] = new Candidate_ec22629(); ca[259].un = "ec22629";
            ca[260] = new Candidate_ec22630(); ca[260].un = "ec22630";
            ca[261] = new Candidate_ec22632(); ca[261].un = "ec22632";
            ca[262] = new Candidate_ec22634(); ca[262].un = "ec22634";
            ca[263] = new Candidate_ec22635(); ca[263].un = "ec22635";
            ca[264] = new Candidate_ec22636(); ca[264].un = "ec22636";
            ca[265] = new Candidate_ec22637(); ca[265].un = "ec22637";
            ca[266] = new Candidate_ec22638(); ca[266].un = "ec22638";
            ca[267] = new Candidate_ec22640(); ca[267].un = "ec22640";
            ca[268] = new Candidate_ec22641(); ca[268].un = "ec22641";
            ca[269] = new Candidate_ec22642(); ca[269].un = "ec22642";
            ca[270] = new Candidate_ec22645(); ca[270].un = "ec22645";
            ca[271] = new Candidate_ec22646(); ca[271].un = "ec22646";
            ca[272] = new Candidate_ec22648(); ca[272].un = "ec22648";
            ca[273] = new Candidate_ec22649(); ca[273].un = "ec22649";
            ca[274] = new Candidate_ec22651(); ca[274].un = "ec22651";
            ca[275] = new Candidate_ec22652(); ca[275].un = "ec22652";
            ca[276] = new Candidate_ec22653(); ca[276].un = "ec22653";
            ca[277] = new Candidate_ec22655(); ca[277].un = "ec22655";
            ca[278] = new Candidate_ec22656(); ca[278].un = "ec22656";
            ca[279] = new Candidate_ec22657(); ca[279].un = "ec22657";
            ca[280] = new Candidate_ec22658(); ca[280].un = "ec22658";
            ca[281] = new Candidate_ec22660(); ca[281].un = "ec22660";
            ca[282] = new Candidate_ec22661(); ca[282].un = "ec22661";
            ca[283] = new Candidate_ec22662(); ca[283].un = "ec22662";
            ca[284] = new Candidate_ec22663(); ca[284].un = "ec22663";
            ca[285] = new Candidate_ec22664(); ca[285].un = "ec22664";
            ca[286] = new Candidate_ec22666(); ca[286].un = "ec22666";
            ca[287] = new Candidate_ec22668(); ca[287].un = "ec22668";
            ca[288] = new Candidate_ec22669(); ca[288].un = "ec22669";
            ca[289] = new Candidate_ec22671(); ca[289].un = "ec22671";
            ca[290] = new Candidate_ec22672(); ca[290].un = "ec22672";
            ca[291] = new Candidate_ec22675(); ca[291].un = "ec22675";
            ca[292] = new Candidate_ec22677(); ca[292].un = "ec22677";
            ca[293] = new Candidate_ec22678(); ca[293].un = "ec22678";
            ca[294] = new Candidate_ec22679(); ca[294].un = "ec22679";
            ca[295] = new Candidate_ec22680(); ca[295].un = "ec22680";
            ca[296] = new Candidate_ec22692(); ca[296].un = "ec22692";
            ca[297] = new Candidate_ec22693(); ca[297].un = "ec22693";
            ca[298] = new Candidate_ec22695(); ca[298].un = "ec22695";
            ca[299] = new Candidate_ec22696(); ca[299].un = "ec22696";
            ca[300] = new Candidate_ec22697(); ca[300].un = "ec22697";
            ca[301] = new Candidate_ec22699(); ca[301].un = "ec22699";
            ca[302] = new Candidate_ec22700(); ca[302].un = "ec22700";
            ca[303] = new Candidate_ec22701(); ca[303].un = "ec22701";
            ca[304] = new Candidate_ec22702(); ca[304].un = "ec22702";
            ca[305] = new Candidate_ec22703(); ca[305].un = "ec22703";
            ca[306] = new Candidate_ec22704(); ca[306].un = "ec22704";
            ca[307] = new Candidate_ec22707(); ca[307].un = "ec22707";
            ca[308] = new Candidate_ec22708(); ca[308].un = "ec22708";
            ca[309] = new Candidate_ec22709(); ca[309].un = "ec22709";
            ca[310] = new Candidate_ec22711(); ca[310].un = "ec22711";
            ca[311] = new Candidate_ec22712(); ca[311].un = "ec22712";
            ca[312] = new Candidate_ec22716(); ca[312].un = "ec22716";
            ca[313] = new Candidate_ec22717(); ca[313].un = "ec22717";
            ca[314] = new Candidate_ec22718(); ca[314].un = "ec22718";
            ca[315] = new Candidate_ec22719(); ca[315].un = "ec22719";
            ca[316] = new Candidate_ec22720(); ca[316].un = "ec22720";
            ca[317] = new Candidate_ec22721(); ca[317].un = "ec22721";
            ca[318] = new Candidate_ec22722(); ca[318].un = "ec22722";
            ca[319] = new Candidate_ec22723(); ca[319].un = "ec22723";
            ca[320] = new Candidate_ec22724(); ca[320].un = "ec22724";
            ca[321] = new Candidate_ec22726(); ca[321].un = "ec22726";
            ca[322] = new Candidate_ec22736(); ca[322].un = "ec22736";
            ca[323] = new Candidate_ec22738(); ca[323].un = "ec22738";
            ca[324] = new Candidate_ec22739(); ca[324].un = "ec22739";
            ca[325] = new Candidate_ec22740(); ca[325].un = "ec22740";
            ca[326] = new Candidate_ec22741(); ca[326].un = "ec22741";
            ca[327] = new Candidate_ec22742(); ca[327].un = "ec22742";
            ca[328] = new Candidate_ec22743(); ca[328].un = "ec22743";
            ca[329] = new Candidate_ec22745(); ca[329].un = "ec22745";
            ca[330] = new Candidate_ec22746(); ca[330].un = "ec22746";
            ca[331] = new Candidate_ec22748(); ca[331].un = "ec22748";
            ca[332] = new Candidate_ec22749(); ca[332].un = "ec22749";
            ca[333] = new Candidate_ec22751(); ca[333].un = "ec22751";
            ca[334] = new Candidate_ec22752(); ca[334].un = "ec22752";
            ca[335] = new Candidate_ec22753(); ca[335].un = "ec22753";
            ca[336] = new Candidate_ec22754(); ca[336].un = "ec22754";
            ca[337] = new Candidate_ec22757(); ca[337].un = "ec22757";
            ca[338] = new Candidate_ec22758(); ca[338].un = "ec22758";
            ca[339] = new Candidate_ec22760(); ca[339].un = "ec22760";
            ca[340] = new Candidate_ec22761(); ca[340].un = "ec22761";
            ca[341] = new Candidate_ec22763(); ca[341].un = "ec22763";
            ca[342] = new Candidate_ec22764(); ca[342].un = "ec22764";
            ca[343] = new Candidate_ec22765(); ca[343].un = "ec22765";
            ca[344] = new Candidate_ec22766(); ca[344].un = "ec22766";
            ca[345] = new Candidate_ec22770(); ca[345].un = "ec22770";
            ca[346] = new Candidate_ec22771(); ca[346].un = "ec22771";
            ca[347] = new Candidate_ec22772(); ca[347].un = "ec22772";
            ca[348] = new Candidate_ec22776(); ca[348].un = "ec22776";
            ca[349] = new Candidate_ec22777(); ca[349].un = "ec22777";
            ca[350] = new Candidate_ec22778(); ca[350].un = "ec22778";
            ca[351] = new Candidate_ec22779(); ca[351].un = "ec22779";
            ca[352] = new Candidate_ec22783(); ca[352].un = "ec22783";
            ca[353] = new Candidate_ec22784(); ca[353].un = "ec22784";
            ca[354] = new Candidate_ec22786(); ca[354].un = "ec22786";
            ca[355] = new Candidate_ec22787(); ca[355].un = "ec22787";
            ca[356] = new Candidate_ec22789(); ca[356].un = "ec22789";
            ca[357] = new Candidate_ec22790(); ca[357].un = "ec22790";
            ca[358] = new Candidate_ec22791(); ca[358].un = "ec22791";
            ca[359] = new Candidate_ec22792(); ca[359].un = "ec22792";
            ca[360] = new Candidate_ec22793(); ca[360].un = "ec22793";
            ca[361] = new Candidate_ec22795(); ca[361].un = "ec22795";
            ca[362] = new Candidate_ec22796(); ca[362].un = "ec22796";
            ca[363] = new Candidate_ec22797(); ca[363].un = "ec22797";
            ca[364] = new Candidate_ec22798(); ca[364].un = "ec22798";
            ca[365] = new Candidate_ec22801(); ca[365].un = "ec22801";
            ca[366] = new Candidate_ec22802(); ca[366].un = "ec22802";
            ca[367] = new Candidate_ec22804(); ca[367].un = "ec22804";
            ca[368] = new Candidate_ec22806(); ca[368].un = "ec22806";
            ca[369] = new Candidate_ec22807(); ca[369].un = "ec22807";
            ca[370] = new Candidate_ec22808(); ca[370].un = "ec22808";
            ca[371] = new Candidate_ec22809(); ca[371].un = "ec22809";
            ca[372] = new Candidate_ec22811(); ca[372].un = "ec22811";
            ca[373] = new Candidate_ec22813(); ca[373].un = "ec22813";
            ca[374] = new Candidate_ec22814(); ca[374].un = "ec22814";
            ca[375] = new Candidate_ec22815(); ca[375].un = "ec22815";
            ca[376] = new Candidate_ec22816(); ca[376].un = "ec22816";
            ca[377] = new Candidate_ec22817(); ca[377].un = "ec22817";
            ca[378] = new Candidate_ec22818(); ca[378].un = "ec22818";
            ca[379] = new Candidate_ec22819(); ca[379].un = "ec22819";
            ca[380] = new Candidate_ec22820(); ca[380].un = "ec22820";
            ca[381] = new Candidate_ec22821(); ca[381].un = "ec22821";
            ca[382] = new Candidate_ec22824(); ca[382].un = "ec22824";
            ca[383] = new Candidate_ec22825(); ca[383].un = "ec22825";
            ca[384] = new Candidate_ec22826(); ca[384].un = "ec22826";
            ca[385] = new Candidate_ec22827(); ca[385].un = "ec22827";
            ca[386] = new Candidate_ec22828(); ca[386].un = "ec22828";
            ca[387] = new Candidate_ec22830(); ca[387].un = "ec22830";
            ca[388] = new Candidate_ec22836(); ca[388].un = "ec22836";
            ca[389] = new Candidate_ec22837(); ca[389].un = "ec22837";
            ca[390] = new Candidate_ec22838(); ca[390].un = "ec22838";
            ca[391] = new Candidate_ec22840(); ca[391].un = "ec22840";
            ca[392] = new Candidate_ec22841(); ca[392].un = "ec22841";
            ca[393] = new Candidate_ec22842(); ca[393].un = "ec22842";
            ca[394] = new Candidate_ec22843(); ca[394].un = "ec22843";
            ca[395] = new Candidate_ec22845(); ca[395].un = "ec22845";
            ca[396] = new Candidate_ec22846(); ca[396].un = "ec22846";
            ca[397] = new Candidate_ec22851(); ca[397].un = "ec22851";
            ca[398] = new Candidate_ec22852(); ca[398].un = "ec22852";
            ca[399] = new Candidate_ec22857(); ca[399].un = "ec22857";
            ca[400] = new Candidate_ec22858(); ca[400].un = "ec22858";
            ca[401] = new Candidate_ec22859(); ca[401].un = "ec22859";
            ca[402] = new Candidate_ec22860(); ca[402].un = "ec22860";
            ca[403] = new Candidate_ec22861(); ca[403].un = "ec22861";
            ca[404] = new Candidate_ec22862(); ca[404].un = "ec22862";
            ca[405] = new Candidate_ec22863(); ca[405].un = "ec22863";
            ca[406] = new Candidate_ec22864(); ca[406].un = "ec22864";
            ca[407] = new Candidate_ec22865(); ca[407].un = "ec22865";
            ca[408] = new Candidate_ec22866(); ca[408].un = "ec22866";
            ca[409] = new Candidate_ec22867(); ca[409].un = "ec22867";
            ca[410] = new Candidate_ec22868(); ca[410].un = "ec22868";
            ca[411] = new Candidate_ec22871(); ca[411].un = "ec22871";
            ca[412] = new Candidate_ec22872(); ca[412].un = "ec22872";
            ca[413] = new Candidate_ec22873(); ca[413].un = "ec22873";
            ca[414] = new Candidate_ec22874(); ca[414].un = "ec22874";
            ca[415] = new Candidate_ec22875(); ca[415].un = "ec22875";
            ca[416] = new Candidate_ec22876(); ca[416].un = "ec22876";
            ca[417] = new Candidate_ec22877(); ca[417].un = "ec22877";
            ca[418] = new Candidate_ec22879(); ca[418].un = "ec22879";
            ca[419] = new Candidate_ec22880(); ca[419].un = "ec22880";
            ca[420] = new Candidate_ec22881(); ca[420].un = "ec22881";
            ca[421] = new Candidate_ec22882(); ca[421].un = "ec22882";
            ca[422] = new Candidate_ec22883(); ca[422].un = "ec22883";
            ca[423] = new Candidate_ec22884(); ca[423].un = "ec22884";
            ca[424] = new Candidate_ec22885(); ca[424].un = "ec22885";
            ca[425] = new Candidate_ec22887(); ca[425].un = "ec22887";
            ca[426] = new Candidate_ec22888(); ca[426].un = "ec22888";
            ca[427] = new Candidate_ec22889(); ca[427].un = "ec22889";
            ca[428] = new Candidate_ec22890(); ca[428].un = "ec22890";
            ca[429] = new Candidate_ec22891(); ca[429].un = "ec22891";
            ca[430] = new Candidate_ec22892(); ca[430].un = "ec22892";
            ca[431] = new Candidate_ec22893(); ca[431].un = "ec22893";
            ca[432] = new Candidate_ec22894(); ca[432].un = "ec22894";
            ca[433] = new Candidate_ec22895(); ca[433].un = "ec22895";
            ca[434] = new Candidate_ec22897(); ca[434].un = "ec22897";
            ca[435] = new Candidate_ec22898(); ca[435].un = "ec22898";
            ca[436] = new Candidate_ec22899(); ca[436].un = "ec22899";
            ca[437] = new Candidate_ec22900(); ca[437].un = "ec22900";
            ca[438] = new Candidate_ec22901(); ca[438].un = "ec22901";
            ca[439] = new Candidate_ec22902(); ca[439].un = "ec22902";
            ca[440] = new Candidate_ec22903(); ca[440].un = "ec22903";
            ca[441] = new Candidate_ec22904(); ca[441].un = "ec22904";
            ca[442] = new Candidate_ec22905(); ca[442].un = "ec22905";
            ca[443] = new Candidate_ec22906(); ca[443].un = "ec22906";
            ca[444] = new Candidate_ec22907(); ca[444].un = "ec22907";
            ca[445] = new Candidate_ec22908(); ca[445].un = "ec22908";
            ca[446] = new Candidate_ec22909(); ca[446].un = "ec22909";
            ca[447] = new Candidate_ec22910(); ca[447].un = "ec22910";
            ca[448] = new Candidate_ec22911(); ca[448].un = "ec22911";
            ca[449] = new Candidate_ec22912(); ca[449].un = "ec22912";
            ca[450] = new Candidate_ec22913(); ca[450].un = "ec22913";
            ca[451] = new Candidate_ec22914(); ca[451].un = "ec22914";
            ca[452] = new Candidate_ec22917(); ca[452].un = "ec22917";
            ca[453] = new Candidate_ec22919(); ca[453].un = "ec22919";
            ca[454] = new Candidate_ec22920(); ca[454].un = "ec22920";
            ca[455] = new Candidate_ec22922(); ca[455].un = "ec22922";
            ca[456] = new Candidate_ec22923(); ca[456].un = "ec22923";
            ca[457] = new Candidate_ec22925(); ca[457].un = "ec22925";
            ca[458] = new Candidate_ec22926(); ca[458].un = "ec22926";
            ca[459] = new Candidate_ec22927(); ca[459].un = "ec22927";
            ca[460] = new Candidate_ec22928(); ca[460].un = "ec22928";
            ca[461] = new Candidate_ec22929(); ca[461].un = "ec22929";
            ca[462] = new Candidate_ec22930(); ca[462].un = "ec22930";
            ca[463] = new Candidate_ec22932(); ca[463].un = "ec22932";
            ca[464] = new Candidate_ec22933(); ca[464].un = "ec22933";
            ca[465] = new Candidate_ec22934(); ca[465].un = "ec22934";
            ca[466] = new Candidate_ec22935(); ca[466].un = "ec22935";
            ca[467] = new Candidate_ec22937(); ca[467].un = "ec22937";
            ca[468] = new Candidate_ec22938(); ca[468].un = "ec22938";
            ca[469] = new Candidate_ec22939(); ca[469].un = "ec22939";
            ca[470] = new Candidate_ec22940(); ca[470].un = "ec22940";
            ca[471] = new Candidate_ec22941(); ca[471].un = "ec22941";
            ca[472] = new Candidate_ec22942(); ca[472].un = "ec22942";
            ca[473] = new Candidate_ec22943(); ca[473].un = "ec22943";
            ca[474] = new Candidate_ec22944(); ca[474].un = "ec22944";
            ca[475] = new Candidate_ec22945(); ca[475].un = "ec22945";
            ca[476] = new Candidate_ec22946(); ca[476].un = "ec22946";
            ca[477] = new Candidate_ec22947(); ca[477].un = "ec22947";
            ca[478] = new Candidate_ec22948(); ca[478].un = "ec22948";
            ca[479] = new Candidate_ec22949(); ca[479].un = "ec22949";
            ca[480] = new Candidate_ec22950(); ca[480].un = "ec22950";
            ca[481] = new Candidate_ec22952(); ca[481].un = "ec22952";
            ca[482] = new Candidate_ec22954(); ca[482].un = "ec22954";
            ca[483] = new Candidate_ec22955(); ca[483].un = "ec22955";
            ca[484] = new Candidate_ec22957(); ca[484].un = "ec22957";
            ca[485] = new Candidate_ec22958(); ca[485].un = "ec22958";
            ca[486] = new Candidate_ec22959(); ca[486].un = "ec22959";
            ca[487] = new Candidate_ec22960(); ca[487].un = "ec22960";
            ca[488] = new Candidate_ec22962(); ca[488].un = "ec22962";
            ca[489] = new Candidate_ec22963(); ca[489].un = "ec22963";
            ca[490] = new Candidate_ec22965(); ca[490].un = "ec22965";
            ca[491] = new Candidate_ec22967(); ca[491].un = "ec22967";
            ca[492] = new Candidate_ec22970(); ca[492].un = "ec22970";
            ca[493] = new Candidate_ec22971(); ca[493].un = "ec22971";
            ca[494] = new Candidate_ec22972(); ca[494].un = "ec22972";
            ca[495] = new Candidate_ec22973(); ca[495].un = "ec22973";
            ca[496] = new Candidate_ec22974(); ca[496].un = "ec22974";
            ca[497] = new Candidate_ec22975(); ca[497].un = "ec22975";
            ca[498] = new Candidate_ec22976(); ca[498].un = "ec22976";
            ca[499] = new Candidate_ec22977(); ca[499].un = "ec22977";
            ca[500] = new Candidate_ec22978(); ca[500].un = "ec22978";
            ca[501] = new Candidate_ec22979(); ca[501].un = "ec22979";
            ca[502] = new Candidate_ec22981(); ca[502].un = "ec22981";
            ca[503] = new Candidate_ec22982(); ca[503].un = "ec22982";
            ca[504] = new Candidate_ec22984(); ca[504].un = "ec22984";
            ca[505] = new Candidate_ec22986(); ca[505].un = "ec22986";
            ca[506] = new Candidate_ec22987(); ca[506].un = "ec22987";
            ca[507] = new Candidate_ec22990(); ca[507].un = "ec22990";
            ca[508] = new Candidate_ec22992(); ca[508].un = "ec22992";
            ca[509] = new Candidate_ec22993(); ca[509].un = "ec22993";
            ca[510] = new Candidate_ec22995(); ca[510].un = "ec22995";
            ca[511] = new Candidate_ec22998(); ca[511].un = "ec22998";
            ca[512] = new Candidate_eey577(); ca[512].un = "eey577";
            ca[513] = new Candidate_ex20181(); ca[513].un = "ex20181";
            ca[514] = new Candidate_ex20417(); ca[514].un = "ex20417";
            ca[515] = new Candidate_ex20464(); ca[515].un = "ex20464";
            ca[516] = new Candidate_ex20539(); ca[516].un = "ex20539";
            ca[517] = new Candidate_ex21213(); ca[517].un = "ex21213";
            ca[518] = new Candidate_ex21247(); ca[518].un = "ex21247";
            ca[519] = new Candidate_ex21249(); ca[519].un = "ex21249";
            ca[520] = new Candidate_ex21283(); ca[520].un = "ex21283";
            ca[521] = new Candidate_ex21299(); ca[521].un = "ex21299";
            ca[522] = new Candidate_ex21327(); ca[522].un = "ex21327";
            ca[523] = new Candidate_ex21329(); ca[523].un = "ex21329";
            ca[524] = new Candidate_ex21362(); ca[524].un = "ex21362";
            ca[525] = new Candidate_ex21405(); ca[525].un = "ex21405";
            ca[526] = new Candidate_ex21423(); ca[526].un = "ex21423";
            ca[527] = new Candidate_ex21425(); ca[527].un = "ex21425";
            ca[528] = new Candidate_ex21495(); ca[528].un = "ex21495";
            ca[529] = new Candidate_ex21541(); ca[529].un = "ex21541";
            ca[530] = new Candidate_ex21566(); ca[530].un = "ex21566";
            ca[531] = new Candidate_ex21572(); ca[531].un = "ex21572";
            ca[532] = new Candidate_ex21626(); ca[532].un = "ex21626";
            ca[533] = new Candidate_exx054(); ca[533].un = "exx054";
            ca[534] = new Candidate_jpp294904(); ca[534].un = "jpp294904";
            ca[535] = new Candidate_jpp301711(); ca[535].un = "jpp301711";
            ca[536] = new Candidate_jpp306027(); ca[536].un = "jpp306027";
            ca[537] = new Candidate_jpp308479(); ca[537].un = "jpp308479";
            ca[538] = new Candidate_jpp314171(); ca[538].un = "jpp314171";
            ca[539] = new Candidate_jpp317487(); ca[539].un = "jpp317487";
            ca[540] = new Candidate_jpp319457(); ca[540].un = "jpp319457";
            ca[541] = new Candidate_jpp322395(); ca[541].un = "jpp322395";
            ca[542] = new Candidate_jpp324787(); ca[542].un = "jpp324787";
            ca[543] = new Candidate_jpp335448(); ca[543].un = "jpp335448";
            ca[544] = new Candidate_lt19211(); ca[544].un = "lt19211";
            ca[545] = new Candidate_ml21059(); ca[545].un = "ml21059";
            
            return ca;
        }







       /*                                              Assignment 3 END                                          */ 














        //returns my choosen candidate name
        public String getName()
        {
            return "Bob";
        }

        //returns my choosen slogan
        public String getSlogan()
        {
            return "AI for president";
        }

        //if string is empty, votes creates a class mates candidate object
        //else a candidate is randomly voted from array parameter
        public Candidate vote(Candidate[] candidates)
        {
            //creates object if candidate array has no elements inside
            if(candidates.length == 0)
            {
                return new Candidate_ec22770();
            }
            else
            {
                //gets a number between 0 - candidates.length - 1
                int ChoosenIndex = (new Random()).nextInt(candidates.length);
                return candidates[ChoosenIndex];
            }
        }

        //candidate with the highest votes (in the parameter array) is returned from the method.
        public Candidate selectWinner(Candidate[] votes)
        {
            //creates object if candidate array has no elements inside
            if(votes.length == 0)
            {
                return new Candidate_ec22770();
            }
            else //selects majority votes candidate as winer
            {
                Candidate TopVote_Candidate = votes[0];
                int HighestVote_Ammount = 0;

                for(Candidate observingVotes_Candidate : votes) //sets each element as the observing candidate
                {
                    if(observingVotes_Candidate != null)
                    {
                        int Vote_Count = 0;
                        for(Candidate comparingVote : votes)//compares each vote to the observing candidate to get a total of votes (for the observing candidate)
                        {
                            if(comparingVote != null)
                            {
                                if((observingVotes_Candidate.getName()).equals(comparingVote.getName())) //vote count incremented if name is the same.
                                {
                                    Vote_Count++;
                                }
                            }
                        }
    
    
                        if(Vote_Count > HighestVote_Ammount)
                        {
                            HighestVote_Ammount = Vote_Count;
                            TopVote_Candidate = observingVotes_Candidate;
                        }
                    }
                
                }

                return TopVote_Candidate;
            }
        }
}




