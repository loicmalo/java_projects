package exercice_1;

import java.util.ArrayList;
import java.util.HashMap;

public class Society {
    private ArrayList<Office>        offices    = new ArrayList<Office>();
    private HashMap<String, Integer> personnels = new HashMap<String, Integer>();

    public Society( ArrayList<Office> offices ) {
        this.offices = offices;
    }

    public void setPersonnels( String key, int value ) {

        if ( personnels.containsKey( key ) ) {
            value += personnels.get( key );
            personnels.put( key, value );
        } else {
            personnels.put( key, value );
        }

    }

    public String personelsToString() {
        return "Commerciaux : " + this.personnels.get( SalesRepresentativesOffice.class.getName() ) + "\n" +
                "Développeurs : " + this.personnels.get( DevelopersOffice.class.getName() ) + "\n";
    }

    public double availableSpaceRate() {
        double availableSpaceRate = 0.0;
        for ( Office office : offices ) {
            availableSpaceRate += office.availableSpaceRate();
        }

        return availableSpaceRate;
    }

    public String societyAvailableSpaceRateToString() {
        String toString = "";
        for ( Office office : offices )
            toString += "Bureau " + ( offices.indexOf( office ) + 1 ) + ": " + office.availableSpaceRate() + "\n";
        return toString += "Taux général pour la société : " + this.availableSpaceRate() + "\n";
    }

    public int addPersonel( int nbPersonel, String officeName ) {
        for ( Office office : offices ) {
            if ( office.getClass().getName() == officeName ) {
                nbPersonel = office.addPersons( nbPersonel );

                if ( nbPersonel == 0 )
                    return nbPersonel;
            }
        }
        return nbPersonel;
    }

}
