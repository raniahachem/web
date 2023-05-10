package autoxpress.interfaces;

import java.util.List;

/**
 *
 * @author 21622
 */
public interface ClientInterface <Client>{
    void AddClient(Client c);
    void UpDateClient (Client c, int id);
    void DeleteClient (int id_client);
    public List <Client> ClientList();
    
    
}