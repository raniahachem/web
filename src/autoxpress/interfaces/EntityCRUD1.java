/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.interfaces;

import java.util.List;

/**
 *
 * @author user
 * @param <T>
 */
public interface EntityCRUD1<T> {
    public void addEntity(T t);
    public void ModifierOff(T t);
    public void SupprimerOff(T t);
    public List<T> entitiesList();
    
}
