package bases_de_donnees;

import java.util.HashSet;
import java.util.Set;

//CTRL + SHIFT + O pour générer les imports
public class Classe {
//ID
private int id = 0;
//Nom du professeur
private String nom = "";
//Liste des professeurs
private Set<Professeur> listProfesseur = new HashSet<Professeur>();
//Liste des élèves
private Set<Eleve> listEleve = new HashSet<Eleve>();

public Classe(int id, String nom) {
  this.id = id;
  this.nom = nom;
}
public Classe(){}

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

public Set<Professeur> getListProfesseur() {
  return listProfesseur;
}

public void setListProfesseur(Set<Professeur> listProfesseur) {
  this.listProfesseur = listProfesseur;
}

public void addProfesseur(Professeur prof) {
  if(!listProfesseur.contains(prof))
    listProfesseur.add(prof);
}

public void removeProfesseur(Professeur prof ) {
  this.listProfesseur.remove(prof);
}

public Set<Eleve> getListEleve() {
  return listEleve;
}

public void setListEleve(Set<Eleve> listEleve) {
  this.listEleve = listEleve;
}

//Ajoute un élève à la classe
public void addEleve(Eleve eleve){
  if(!this.listEleve.contains(eleve))
    this.listEleve.add(eleve);
}

//Retire un élève de la classe
public void removeEleve(Eleve eleve){
  this.listEleve.remove(eleve);
}

public boolean equals(Classe cls){
  return this.getId() == cls.getId();
}   
}