
package ma.projet.beans;
public class Personne {
 private int id;
private String nom;
private double salaire ;


public Personne (int id, String nom, double salaire ) {
this.id = id;
this.nom = nom;
this.salaire  = salaire ;
}
public String getNom() {
return nom;
}
public double getSalaire() {
return salaire ;
}
public int getId() {
return id;
}
public void setNom(String nom) {
this.nom = nom;
}
public void setSalaire(double salaire) {
this.salaire = salaire ;
 }
 void afficherInfo() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}   

   

