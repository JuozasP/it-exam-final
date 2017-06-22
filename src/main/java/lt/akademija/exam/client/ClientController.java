package lt.akademija.exam.client;

import io.swagger.annotations.ApiOperation;
import lt.akademija.exam.inventory.InventoryEntity;
import lt.akademija.exam.service.ReportService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;



/**
 Class used to control client Rest services
 */
@RestController
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    final static Logger logger = Logger.getLogger(ClientController.class);
    @Autowired
    private ReportService reportService;

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
    public Client addNewInventory(@PathVariable Long id, String name, int sector, double weight) {
        logger.info("client id: " + id + " new inventory add " );
        InventoryEntity inventoryEntity = new InventoryEntity();
        inventoryEntity.setName(name);
        inventoryEntity.setSector(sector);
        inventoryEntity.setWeight(weight);
        return clientRepository.addInventory(id, inventoryEntity);
    }

    @PostMapping("/api/clients/delete/{id}")
    public Client removeInventory(@PathVariable Long id, @RequestBody InventoryEntity inventoryEntity) {
        logger.info("client id: " + id + "  inventory removed " + inventoryEntity);
        InventoryEntity naujas = new InventoryEntity();
        return clientRepository.removeInventory(id,inventoryEntity);
    }

    @GetMapping("/api/clients/service/inventory/{id}")
    public Integer inventoryCount(@PathVariable Long id){
        return reportService.findClientCount(clientRepository.findById(id));
    }
    @GetMapping("/api/clients/{}")
    public Client getSingleClient(@PathVariable Long id) {
        logger.info("client controller called");
        return clientRepository.findById(id);
    }
}
