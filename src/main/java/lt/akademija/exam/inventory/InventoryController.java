package lt.akademija.exam.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Class used to control Inventory Rest services
 *
 * Created by Juozas on 2017-06-22.
 */
@RestController
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;

    @GetMapping("/api/inventory")
    public List<InventoryEntity> getAllInventoryItems(){
        return inventoryRepository.findAll();
    }

    @DeleteMapping("/api/inventory/{id}")
    public void deleteInventoryItem(@PathVariable Long id){
        inventoryRepository.delete(id);
    }

    @PostMapping("/api/inventory")
    public InventoryEntity addInventoryItem(@RequestBody @Valid InventoryEntity inventoryEntity){
        return inventoryRepository.save(inventoryEntity);
    }


}
