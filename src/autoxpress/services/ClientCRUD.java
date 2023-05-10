/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.services;

/**
 *
 * @author user
 */


import autoxpress.entities.Client;
import autoxpress.interfaces.ClientInterface;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 *
 * @author 21622
 */
public class ClientCRUD implements ClientInterface<Client> {

    private final String url = "jdbc:mysql://localhost:3306/autoxpress";
    private final String user = "root";
    private final String password = "";

    @Override
    //Addclient
    public void AddClient(Client c) {
        String sql = "INSERT INTO client(nom_client, prenom_client, cin_client, ville_client, telephone_client, email_client, mdp_client) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, c.getNom_client());
            pstmt.setString(2, c.getPrenom_client());
            pstmt.setInt(3, c.getCin_client());
            pstmt.setString(4, c.getVille_client());
            pstmt.setInt(5, c.getTelephone_client());
            pstmt.setString(6, c.getEmail_client());
            pstmt.setString(7, c.getMdp_client());
            pstmt.executeUpdate();
            System.out.println("Le client a été ajouté avec succès !");

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    @Override
    public void UpDateClient(Client c, int id) {
        String sql = "UPDATE client SET nom_client = ?, prenom_client = ?, cin_client = ?, ville_client = ?, telephone_client = ?, email_client = ?, mdp_client = ? WHERE id_client = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, c.getNom_client());
            pstmt.setString(2, c.getPrenom_client());
            pstmt.setInt(3, c.getCin_client());
            pstmt.setString(4, c.getVille_client());
            pstmt.setInt(5, c.getTelephone_client());
            pstmt.setString(6, c.getEmail_client());
            pstmt.setString(7, c.getMdp_client());
            pstmt.setInt(8, id);

            pstmt.executeUpdate();
            System.out.println("Le client a été modifié avec succès ");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void DeleteClient(int id_client) {

        String sql = "DELETE FROM client WHERE id_client = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id_client);
            pstmt.executeUpdate();
            System.out.println(" Le client a été supprimé avec succès");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Client> ClientList() {

        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM client";
        try (Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Client cd = new Client();
                cd.setId_client(rs.getInt("id_client"));
                cd.setNom_client(rs.getString("nom_client"));
                cd.setPrenom_client(rs.getString("prenom_client"));
                cd.setCin_client(rs.getInt("cin_client"));
                cd.setVille_client(rs.getString("ville_client"));
                cd.setTelephone_client(rs.getInt("telephone_client"));
                cd.setEmail_client(rs.getString("email_client"));
                cd.setMdp_client(rs.getString("mdp_client"));
                clients.add(cd);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return clients;
    }

    public ObservableList<Client> getDataClient() {
        ObservableList<Client> list = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Client";
        try (Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Client c = new Client();
                c.setId_client(rs.getInt("id_client"));
                c.setNom_client(rs.getString("nom_client"));
                c.setPrenom_client(rs.getString("prenom_client"));
                c.setCin_client(rs.getInt("cin_client"));
                c.setVille_client(rs.getString("ville_client"));
                c.setTelephone_client(rs.getInt("telephone_client"));
                c.setEmail_client(rs.getString("email_client"));
                c.setMdp_client(rs.getString("mdp_client"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

   // @Override
    
public Client login(String email_client) {
String sql = "select * from Client where email_client='" + email_client + "'";
Connection conn = null;
PreparedStatement pstmt = null;
ResultSet result = null;
Client client = null;
try {
conn = DriverManager.getConnection(url, user, password);
pstmt = conn.prepareStatement(sql);
result = pstmt.executeQuery(sql);
client = new Client();
while (result.next()) {
client.setId_client(result.getInt("id_client"));
client.setNom_client(result.getString("nom_client"));
client.setPrenom_client(result.getString("prenom_client"));
client.setCin_client(result.getInt("cin_client"));
client.setVille_client(result.getString("ville_client"));
client.setTelephone_client(result.getInt("telephone_client"));
client.setEmail_client(result.getString("email_client"));
client.setMdp_client(result.getString("mdp_client"));
}
} catch (SQLException e) {
// Gérer l'exception ici
e.printStackTrace();
} finally {
try {
if (result != null) {
result.close();
}
if (pstmt != null) {
pstmt.close();
}
if (conn != null) {
conn.close();
}
} catch (SQLException e) {
e.printStackTrace();
}
}
return client;
}
    

 
    public ArrayList<Client> getAll() {
        ArrayList<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM client";
        try (Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Client H = new Client();
                H.setId_client(rs.getInt("id_client"));
                H.setNom_client(rs.getString("nom_client"));
                H.setPrenom_client(rs.getString("prenom_client"));

                clients.add(H);
            }
            return clients;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return clients;
    }
 

public String getClientPhoneNumber(String prenom_client) {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    String phoneNumber = null;

    try {
        conn = DriverManager.getConnection(url, user, password);
        String query = "SELECT telephone_client FROM client WHERE prenom_client=?";
        stmt = conn.prepareStatement(query);
        stmt.setString(1, prenom_client);
        rs = stmt.executeQuery();

        if (rs.next()) {
            phoneNumber = rs.getString("telephone_client");
        }

    } catch (SQLException ex) {
        ex.printStackTrace();

    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    return phoneNumber;
}



}
