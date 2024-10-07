

  package ma.projet.test;

import ma.projet.beans.Developpeur;
import ma.projet.beans.Manager;
import ma.projet.service.DeveloppeurService;
import ma.projet.service.ManagerService;

public class Entreprise {

    public static void main(String[] args) {

    // Instanciation des services
    DeveloppeurService developpeurService = new DeveloppeurService();
    ManagerService managerService = new ManagerService();

    try {
        // Créer deux développeurs
        Developpeur dev1 = new Developpeur(1, "Alice", 3000);
        Developpeur dev2 = new Developpeur(2, "Bob", 3200);

        // Sauvegarder les développeurs
        if (developpeurService.create(dev1)) {
            System.out.println("Développeur " + dev1.getNom() + " ajouté avec succès.");
        } else {
            System.out.println("Échec de l'ajout du développeur " + dev1.getNom() + ".");
        }

        if (developpeurService.create(dev2)) {
            System.out.println("Développeur " + dev2.getNom() + " ajouté avec succès.");
        } else {
            System.out.println("Échec de l'ajout du développeur " + dev2.getNom() + ".");
        }

        // Créer un manager qui gère les deux développeurs
        Manager manager = new Manager(1, "Charlie", 5000);
        manager.ajouterDeveloppeur(dev1);
        manager.ajouterDeveloppeur(dev2);

        // Sauvegarder le manager
        if (managerService.create(manager)) {
            System.out.println("Manager " + manager.getNom() + " ajouté avec succès.");
        } else {
            System.out.println("Échec de l'ajout du manager " + manager.getNom() + ".");
        }

        // Créer un 3ème développeur
        Developpeur dev3 = new Developpeur(3, "David", 3500);
        if (developpeurService.create(dev3)) {
            System.out.println("Développeur " + dev3.getNom() + " ajouté avec succès.");
        } else {
            System.out.println("Échec de l'ajout du développeur " + dev3.getNom() + ".");
        }

        // Créer un directeur général qui gère le manager et le 3ème développeur
        Manager directeur = new Manager(1, "Eve", 8000);
        directeur.gererManager(manager);
        directeur.gererAutrePersonne(dev3);

        // Afficher les informations de la hiérarchie
        System.out.println("Directeur Général : " + directeur.getNom() + ", Salaire : " + directeur.getSalaire());
        System.out.println("Manager : " + manager.getNom() + ", Salaire : " + manager.getSalaire());
        for (Developpeur dev : manager.getDeveloppeurs()) {
            System.out.println("  - Développeur : " + dev.getNom() + ", Salaire : " + dev.getSalaire());
        }
        System.out.println("Autre employé géré : " + dev3.getNom() + ", Salaire : " + dev3.getSalaire());

    } catch (Exception e) {
        System.out.println("Une erreur s'est produite : " + e.getMessage());
        e.printStackTrace(); // Affiche la trace de la pile pour déboguer
    }
}

}
  

