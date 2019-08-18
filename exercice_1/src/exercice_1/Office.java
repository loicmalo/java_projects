package exercice_1;

public class Office {
    protected int nbNetworkOutlets;
    protected int nbPowerOutlets;
    protected int nbPhoneOutlets;
    protected int nbChairs;
    protected int nbTables;
    protected int nbPersons;

    public Office( int nbNetworkOutlets, int nbPowerOutlets, int nbPhoneOutlets, int nbChairs, int nbTables ) {
        super();
        this.nbNetworkOutlets = nbNetworkOutlets;
        this.nbPowerOutlets = nbPowerOutlets;
        this.nbPhoneOutlets = nbPhoneOutlets;
        this.nbChairs = nbChairs;
        this.nbTables = nbTables;
    }

    public double availableSpaceRate() {
        return this.nbNetworkOutlets - this.nbPersons +
                this.nbPowerOutlets - this.nbPersons +
                this.nbPhoneOutlets - this.nbPersons +
                this.nbChairs - this.nbPersons +
                this.nbTables - this.nbPersons;

    }

    public boolean checkAvailableSpaceRate( int $nbPersons ) {
        if ( this.availableSpaceRate() > 0 )
            return true;
        else
            return false;
    }

    public String availableSpaceRateToString() {
        return "Taux d'espace disponible : " + this.availableSpaceRate();

    }

    public int addPersons( int nbPersons ) {
        int totalPersons = this.nbPersons + nbPersons;
        for ( int i = this.nbPersons + 1; i <= totalPersons; i++ ) {
            if ( checkAvailableSpaceRate( i ) ) {
                this.nbPersons++;
                nbPersons--;
            } else
                break;
        }
        return nbPersons;
    }

    public int getPersons() {
        return this.nbPersons;
    }
}
