/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.interfaces;

import java.util.List;
import autoxpress.entities.Messages;
/**
 *
 * @author BKHmidi
 */
public interface MessageInterface <Messages> {
     void AddMessage(Messages m);
      void UpdateMessage(Messages m);
      public Messages ReadMessage(int id_message);
       void DeleteMessage(int id_message);
      public List<Messages> MessageList();
      
    
}
