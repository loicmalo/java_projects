package exercice_1;

public class DevelopersOffice extends Office {

    public DevelopersOffice( int nbNetworkOutlets, int nbPowerOutlets, int nbPhoneOutlets, int nbChairs,
            int nbTables ) {
        super( nbNetworkOutlets, nbPowerOutlets, nbPhoneOutlets, nbChairs, nbTables );
        // TODO Auto-generated constructor stub
    }

    public double availableSpaceRate() {
        return this.nbNetworkOutlets - 3 * this.nbPersons +
                this.nbPowerOutlets - 3 * this.nbPersons +
                this.nbPhoneOutlets - this.nbPersons +
                this.nbChairs - 1.5 * this.nbPersons +
                this.nbTables - this.nbPersons;
    }

}
