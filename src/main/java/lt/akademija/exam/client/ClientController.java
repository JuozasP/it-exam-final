package lt.akademija.exam.client;

import io.swagger.annotations.ApiOperation;
import lt.akademija.exam.inventory.InventoryEntity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    final static Logger logger = Logger.getLogger(ClientController.class);


    @GetMapping("/api/clients")
    @ApiOperation(value = "Returns all flights that are currently in the list")
    public List<Client> getClients() {
        logger.info("get all clients controller called");
        return clientRepository.findAll();
    }

    @DeleteMapping("/api/clients/{id}")
    public void deleteClient(@PathVariable Long id) {
        logger.info("deleted client with id: " + id);
        clientRepository.delete(id);
    }

    @PostMapping("/api/clients")
    public Client registerClient(@RequestBody @Valid Client client) {
        logger.info("client added " + client);
        return clientRepository.save(client);
    }

    @PostMapping("/api/clients/{id}")
    public Client addNewInventory(@PathVariable Long id, @RequestBody @Valid InventoryEntity inventoryEntity) {
        logger.info("client id: " + id + " new inventory add " + inventoryEntity);
        return clientRepository.addInventory(id, inventoryEntity);
    }

    @PostMapping("/api/clients/delete/{id}")
    public Client removeInventory(@PathVariable Long id, @RequestBody @Valid InventoryEntity inventoryEntity) {
        logger.info("client id: " + id + "  inventory removed " + inventoryEntity);
        return clientRepository.removeInventory(id,inventoryEntity);
    }
}
