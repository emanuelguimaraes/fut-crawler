package br.com.fifa.futcrawler.domain.attributes.chemistry;

import br.com.fifa.futcrawler.domain.attributes.*;

public class ChemistryPlayer extends Chemistry {

    private Pace<Integer> pace;
    private Shooting<Integer> shooting;
    private Passing<Integer> passing;
    private Dribbling<Integer> dribbling;
    private Defending<Integer> defending;
    private Physicality<Integer> physicality;

    public ChemistryPlayer(ChemistryType name, Pace<Integer> pace, Shooting<Integer> shooting,
                           Passing<Integer> passing, Dribbling<Integer> dribbling,
                           Defending<Integer> defending, Physicality<Integer> physicality) {
        super(name);
        this.pace = pace;
        this.shooting = shooting;
        this.passing = passing;
        this.dribbling = dribbling;
        this.defending = defending;
        this.physicality = physicality;
    }

    public Pace<Integer> getPace() {
        return pace;
    }

    public Shooting<Integer> getShooting() {
        return shooting;
    }

    public Passing<Integer> getPassing() {
        return passing;
    }

    public Dribbling<Integer> getDribbling() {
        return dribbling;
    }

    public Defending<Integer> getDefending() {
        return defending;
    }

    public Physicality<Integer> getPhysicality() {
        return physicality;
    }
}
