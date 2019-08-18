package bases_de_donnees;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Professeur {
    // ID
    private int               id          = 0;
    // Nom du professeur
    private String            nom         = "";
    // Pr�nom du professeur
    private String            prenom      = "";
    // Liste des mati�res dispens�es
    private Set<Matiere>      listMatiere = new HashSet<Matiere>();
    private ArrayList<String> list        = new ArrayList<String>();

    public Professeur( int id, String nom, String prenom ) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Professeur() {
    }

    public int getId() {

        return id;
    }

    public void setId( int id ) {
        this.id = id;

    }

    public String getNom() {
        return nom;
    }

    public void setNom( String nom ) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom( String prenom ) {
        this.prenom = prenom;
    }

    public Set<Matiere> getListMatiere() {
        return listMatiere;
    }

    public void setListMatiere( Set<Matiere> listMatiere ) {
        this.listMatiere = listMatiere;
    }

    // Ajoute une mati�re � un professeur
    public void addMatiere( Matiere matiere ) {
        this.listMatiere.add( matiere );
    }

    // Retire une mati�re � un professeur
    public void removeMatiere( Matiere matiere ) {
        this.listMatiere.remove( matiere );
    }
}