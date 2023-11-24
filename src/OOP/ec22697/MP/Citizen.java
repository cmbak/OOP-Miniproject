package OOP.ec22697.MP;

interface Citizen extends Person {
    Candidate vote(Candidate[] candidates);
    Candidate selectWinner(Candidate[] votes);
}
