package bases_de_donnees;


//CTRL + SHIFT + O pour générer les imports
public class FirstTest { 
public static void main(String[] args) {
  //Testons des élèves
  DAO<Eleve> eleveDao = new EleveDAO(SdzConnection.getInstance());
  for(int i = 1; i < 5; i++){
    Eleve eleve = eleveDao.find(i);
    System.out.println("Elève N°" + eleve.getId() + "  - " + eleve.getNom() + " " + eleve.getPrenom());
  }
    
  System.out.println("\n********************************\n");
    
  //Voyons voir les professeurs
  DAO<Professeur> profDao = new ProfesseurDAO(SdzConnection.getInstance());
  for(int i = 4; i < 8; i++){
    Professeur prof = profDao.find(i);
    System.out.println(prof.getNom() + " " + prof.getPrenom() + " enseigne : ");
    for(Matiere mat : prof.getListMatiere())
      System.out.println("\t * " + mat.getNom());
  }
    
  System.out.println("\n********************************\n");
    
  //Et là, c'est la classe
  DAO<Classe> classeDao = new ClasseDAO(SdzConnection.getInstance());
  Classe classe = classeDao.find(11);
    
  System.out.println("Classe de " + classe.getNom());
  System.out.println("\nListe des élèves :");
  for(Eleve eleve : classe.getListEleve())
    System.out.println("  - " + eleve.getNom() + " " + eleve.getPrenom());
    
  System.out.println("\nListe des professeurs :");
  for(Professeur prof : classe.getListProfesseur())
    System.out.println("  - " + prof.getNom() + " " + prof.getPrenom());      
}
}
