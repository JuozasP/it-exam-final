package lt.akademija.exam.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Juozas on 2017-06-22.
 */
@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity,Long>{
}
