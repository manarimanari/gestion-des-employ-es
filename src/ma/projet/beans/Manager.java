
package ma.projet.beans;
import java.util.ArrayList;
import java.util.List;

public class Manager extends Personne {
    private List<Developpeur> developpeurs; // Liste pour stocker les développeurs

    // Constructeur par défaut
    public Manager() {
        super(0, "", 0);
        this.developpeurs = new ArrayList<>(); // Initialisation de la liste
    }

    public Manager(int id, String nom, double salaire) {
        super(id, nom, salaire);
        this.developpeurs = new ArrayList<>(); // Initialisation de la liste
    }

    // Récupérer la liste des développeurs
    public List<Developpeur> getDeveloppeurs() {
        return developpeurs;
    }

    // Ajouter un développeur à la liste
    public void ajouterDeveloppeur(Developpeur dev1) {
        if (dev1 != null) {
            developpeurs.add(dev1);
            System.out.println("Développeur ajouté : " + dev1.getNom());
        } else {
            System.out.println("Impossible d'ajouter un développeur null.");
        }
    }

    // Gérer un manager (peut-être pour une structure hiérarchique)
    public void gererManager(Manager manager) {
        // Logique pour gérer un autre manager
        // Cela pourrait être une liste de managers sous ce manager
        System.out.println("Gestion du manager : " + manager.getNom());
    }

    // Gérer une autre personne (développeur, dans ce cas)
    public void gererAutrePersonne(Developpeur dev3) {
        if (dev3 != null) {
            System.out.println("Gestion de l'autre personne : " + dev3.getNom());
        } else {
            System.out.println("Impossible de gérer une personne null.");
        }
    }
}
