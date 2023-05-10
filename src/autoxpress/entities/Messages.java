/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.entities;
import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author BKHmidi
 */
public class Messages {

    private int id;
    private String contenu;
    private Timestamp  date_message;
    private int id_rec_id ;
    private int id_admin_id;

    public Messages() {
    }

    public Messages(int id, String contenu, Timestamp date_message, int id_rec_id, int id_admin_id) {
        this.id = id;
        this.contenu = contenu;
        this.date_message = date_message;
        this.id_rec_id = id_rec_id;
        this.id_admin_id = id_admin_id;
    }

    public Messages(String contenu, Timestamp date_message, int id_rec_id, int id_admin_id) {
        this.contenu = contenu;
        this.date_message = date_message;
        this.id_rec_id = id_rec_id;
        this.id_admin_id = id_admin_id;
    }

    public Messages(int aInt, String string, Timestamp timestamp) {
    }

    public Messages(String string, Timestamp timestamp) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Timestamp getDate_message() {
        return date_message;
    }

    public void setDate_message(Timestamp date_message) {
        this.date_message = date_message;
    }

    public int getId_rec_id() {
        return id_rec_id;
    }

    public void setId_rec_id(int id_rec_id) {
        this.id_rec_id = id_rec_id;
    }

    public int getId_admin_id() {
        return id_admin_id;
    }

    public void setId_admin_id(int id_admin_id) {
        this.id_admin_id = id_admin_id;
    }

    @Override
    public String toString() {
        return "Messages{" + "id=" + id + ", contenu=" + contenu + ", date_message=" + date_message + ", id_rec_id=" + id_rec_id + ", id_admin_id=" + id_admin_id + '}';
    }

}
