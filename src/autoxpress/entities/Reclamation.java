/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.entities;
//import java.util.Date;
import java.sql.Date;
import java.time.LocalDate;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SingleSelectionModel;
import javafx.util.Callback;

/**
 *
 * @author rania
 */
public class Reclamation {
private int id;
    private String type;
    private String date;
    private String description;

    public Reclamation() {
    }

    public Reclamation(String type, String date, String description) {
        this.type = type;
        this.date = date;
        this.description = description;
    }

    public Reclamation(int id, String type, String date, String description) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }    
     
    
}
