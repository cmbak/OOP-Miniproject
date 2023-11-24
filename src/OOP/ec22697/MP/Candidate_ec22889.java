package OOP.ec22697.MP;

class Candidate_ec22889 extends Candidate {
    public String getName(){
        return "GK";
    }

    public String getSlogan(){
        return "More bananas!";
    }

    public Candidate vote(Candidate[] a){
        if (a.length == 0){
            return new Candidate_ec22889();
        }

        for (int i=0;i<a.length;i++){
            if ((a[i].getSlogan()).equals("More bananas!")){
                return a[i];
            }
        }

        return a[0];
    }

    public Candidate selectWinner(Candidate[] a){
        Candidate currentWinner = a[0];
        int highestCount = 0;
        for (int i=0;i<a.length;i++) {
            
            int count = 0;
            for (int j=0;j<a.length;j++)
                if (a[i] == a[j])
                    count++;
            if (count > highestCount) {
                highestCount = count;
                currentWinner = a[i];
            }
        }
        
        return currentWinner;
    }
}
