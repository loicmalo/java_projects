package bases_de_donnees;

public class Eleve {
  //ID
  private int id = 0;
  //Nom de l'élève
  private String nom = "";
  //Prénom de l'élève
  private String prenom = "";
   
  public Eleve(int id, String nom, String prenom) {
    this.id = id;
    this.nom = nom;
    this.prenom = prenom;
  }
  public Eleve(){};
     
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getPrenom() {
    return prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }   
}
