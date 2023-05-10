/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.entities;

/**
 *
 * @author 21622
 */
public class User {

    private String role;
    private int id_role;

    public User() {
    }

    public User(String role) {
        this.role = role;
    }

    public User(String role, int id_role) {
        this.role = role;
        this.id_role = id_role;
    }

    public String getRole() {
        return role;
    }

    public int getId_role() {
        return id_role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }

    @Override
    public String toString() {
        return "User{" + "role=" + role + ", id_role=" + id_role + '}';
    }
    
}
