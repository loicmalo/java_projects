package exercice_1;

public class SalesRepresentativesOffice extends Office {

    public SalesRepresentativesOffice( int nbNetworkOutlets, int nbPowerOutlets, int nbPhoneOutlets, int nbChairs,
            int nbTables ) {
        super( nbNetworkOutlets, nbPowerOutlets, nbPhoneOutlets, nbChairs, nbTables );
        // TODO Auto-generated constructor stub
    }

    public double availableSpaceRate() {
        return this.nbNetworkOutlets - this.nbPersons +
                this.nbPowerOutlets - this.nbPersons +
                this.nbPhoneOutlets - 2 * this.nbPersons +
                this.nbChairs - 2 * this.nbPersons +
                this.nbTables - this.nbPersons;
    }

}
