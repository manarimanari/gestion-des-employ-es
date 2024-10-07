
package ma.projet.service;

import ma.projet.dao.IDao;
import ma.projet.beans.Manager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ma.projet.connexion.Connexion;

public class ManagerService implements IDao<Manager> {

    private Connection connection;

    public ManagerService(Connection connection) {
        this.connection = connection;
    }

    public ManagerService() {
        
    try {
        // Initialisez ici votre connexion à la base de données
        String url = "jdbc:mysql://localhost/entreprise";
        String user = "root";
        String password = "root";

        this.connection = DriverManager.getConnection(url, user, password);
    } catch (SQLException e) {
        System.out.println("Erreur de connexion à la base de données : " + e.getMessage());
        this.connection = null; // Gérer les cas où la connexion échoue
    }
    }
public boolean create(Manager o) {
        boolean r = false;
        String req = "INSERT INTO manager (nom, salaire) VALUES (?, ?)";
        try (Connection cn = Connexion. getConnection();
             PreparedStatement pst = cn.prepareStatement(req)) {
            pst.setString(1, o.getNom());
            pst.setDouble(2, o.getSalaire());
            int n = pst.executeUpdate();
            if (n == 1) {
                r = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManagerService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
}
@Override
    public boolean update(Manager manager) {
        String sql = "UPDATE manager SET nom = ?, salaire = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, manager.getNom());
            stmt.setDouble(2, manager.getSalaire());
            stmt.setInt(4, manager.getId());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("SQL erreur" + e.getMessage());
            return false;
        }
    }
@Override
    public boolean delete(Manager manager) {
        String sql = "DELETE FROM manager WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, manager.getId());
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
System.out.println("SQL erreur" + e.getMessage());  
return false;
        }
    }
 @Override
public Manager getById(int id) {
    String sql = "SELECT * FROM manager WHERE id = ?";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Manager(
                rs.getInt("id"),
                rs.getString("nom"),
                rs.getDouble("salaire")
            );
        }
    } catch (SQLException e) {
        System.out.println("SQL erreur: " + e.getMessage());
    }
    return null; // Retourne null si le manager n'est pas trouvé
}

@Override
public List<Manager> getAll() {
    List<Manager> managers = new ArrayList<>();
    String sql = "SELECT * FROM manager";
    try (PreparedStatement stmt = connection.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            managers.add(new Manager(
                rs.getInt("id"),
                rs.getString("nom"),
                rs.getDouble("salaire")
            ));
        }
    } catch (SQLException e) {
        System.out.println("SQL erreur: " + e.getMessage());
    }
    return managers; // Retourne la liste de tous les managers
}
}