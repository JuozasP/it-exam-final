package lt.akademija.exam.client;

import lt.akademija.exam.inventory.InventoryEntity;
import lt.akademija.exam.inventory.InventoryRepository;
import lt.akademija.exam.service.ReportService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Class used to query in memory database for client entity
 *
 * @author ggrazevicius
 */
@Repository
public class ClientRepository {

    final static Logger logger = Logger.getLogger(ClientRepository.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public Client get(Long id) {
        return entityManager.find(Client.class, id);
    }

    @Transactional
    public Client save(Client client) {
        return entityManager.merge(client);
    }

    @Transactional
    public void delete(Long id) {
        entityManager.remove(entityManager.find(Client.class, id));
    }

    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return entityManager.createNamedQuery("findAllClients").getResultList();
    }

    @Transactional
    public Client addInventory(Long id, InventoryEntity addedInventory) {
        Client client = entityManager.find(Client.class, id);
        List<InventoryEntity> inventory = new ArrayList<>();
        inventoryRepository.save(addedInventory);
        inventory.addAll(client.getInventoryEntity());
        inventory.add(addedInventory);
        client.setInventoryEntity(inventory);
        entityManager.persist(client);
        return client;

    }

    public Client removeInventory(Long id, InventoryEntity addedInventory) {
        Client client = entityManager.find(Client.class, id);
        ListIterator<InventoryEntity> iterator = client.getInventoryEntity().listIterator();
        while(iterator.hasNext()){
            InventoryEntity inventory = iterator.next();
            if(inventory.equals(addedInventory)){
                logger.debug("inventory removed " + addedInventory);
                iterator.remove();
            }
        }
        return client;
    }

    public Client findById(Long id){
        return entityManager.find(Client.class, id);
    }

}
