package exercice_1;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main( String[] args ) {
        // TODO Auto-generated method stub
        ArrayList<Office> offices = new ArrayList<Office>();
        offices.add( new SalesRepresentativesOffice( 5, 5, 10, 10, 5 ) );
        offices.add( new SalesRepresentativesOffice( 5, 5, 10, 10, 5 ) );
        offices.add( new SalesRepresentativesOffice( 5, 5, 10, 10, 5 ) );
        offices.add( new DevelopersOffice( 6, 6, 2, 3, 2 ) );
        offices.add( new DevelopersOffice( 6, 6, 2, 3, 2 ) );

        Society society = new Society( offices );
        Random rand = new Random();

        System.out.println(
                society.personelsToString() +
                        society.societyAvailableSpaceRateToString() + "\n" +
                        "--------------------------------\n" );
        int i = 0;
        String officeName = "";

        while ( society.availableSpaceRate() > 0.0 && i < 1000 ) {
            int profession = rand.nextInt( 2 );
            int nbPersonnel = rand.nextInt( 3 ) + 1;

            officeName = ( profession == 0 ) ? SalesRepresentativesOffice.class.getName()
                    : DevelopersOffice.class.getName();
            int nbExtraPersonnel = society.addPersonel( nbPersonnel, officeName );
            society.setPersonnels( officeName, nbPersonnel - nbExtraPersonnel );

            if ( officeName == SalesRepresentativesOffice.class.getName() ) {
                System.out.println( "Recrutement de " + ( nbPersonnel - nbExtraPersonnel ) + " commerciaux.\n" );
            } else {
                System.out.println( "Recrutement de " + ( nbPersonnel - nbExtraPersonnel ) + " développeurs.\n" );
            }

            System.out.println( society.personelsToString() );
            System.out.println( society.societyAvailableSpaceRateToString() );
            System.out.println( "----------------------------\n" );
            i++;

        }

        String message = ( society.availableSpaceRate() <= 0 ) ? "Tous les bureaux sont pleins !"
                : "1000 itérations : un problème est survenu !";
        System.out.println( message );

    }

}
