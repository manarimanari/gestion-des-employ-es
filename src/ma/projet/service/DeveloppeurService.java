
package ma.projet.service;

import ma.projet.dao.IDao;
import ma.projet.beans.Developpeur;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeveloppeurService implements IDao<Developpeur> {

    private Connection connection;

    public DeveloppeurService(Connection connection) {
        this.connection = connection;
    }

    public DeveloppeurService() {
        try {
            // Initialisez ici votre connexion à la base de données
            String url = "jdbc:mysql://localhost/entreprise";
            String user = "root";
            String password = "";

            this.connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Erreur de connexion à la base de données : " + e.getMessage());
            this.connection = null; // Gérer les cas où la connexion échoue
        }
    }

    @Override
    public boolean create(Developpeur developpeur) {
        String sql = "INSERT INTO developpeur (nom, salaire) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, developpeur.getNom());
            stmt.setDouble(2, developpeur.getSalaire());
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Developpeur developpeur) {
        String sql = "UPDATE developpeur SET nom = ?, salaire = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, developpeur.getNom());
            stmt.setDouble(2, developpeur.getSalaire());
            stmt.setInt(4, developpeur.getId());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Ajout d'une impression pour le débogage
            return false;
        }
    }

    @Override
    public boolean delete(Developpeur developpeur) {
        String sql = "DELETE FROM developpeur WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, developpeur.getId());
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println("SQL erreur: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Developpeur getById(int id) {
        String sql = "SELECT * FROM developpeur WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Developpeur(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getDouble("salaire")
                   
                );
            }
        } catch (SQLException e) {
            System.out.println("SQL erreur: " + e.getMessage());
        }
        return null; // Retourne null si le développeur n'est pas trouvé
    }

    @Override
    public List<Developpeur> getAll() {
        List<Developpeur> developpeurs = new ArrayList<>();
        String sql = "SELECT * FROM developpeur ";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                developpeurs.add(new Developpeur(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getDouble("salaire")
                    
                ));
            }
        } catch (SQLException e) {
            System.out.println("SQL erreur: " + e.getMessage());
        }
        return developpeurs; // Retourne la liste de tous les développeurs
    }
}
