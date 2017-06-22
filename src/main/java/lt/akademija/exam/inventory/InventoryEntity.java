package lt.akademija.exam.inventory;

import lt.akademija.exam.client.Client;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 *
 *
 * Created by Juozas on 2017-06-22.
 */
@Entity
public class InventoryEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private double weight;

    @Min(1)
    @Max(40)
    private int sector;



    private Date placedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getSector() {
        return sector;
    }

    public void setSector(int sector) {
        this.sector = sector;
    }

    public Date getPlacedDate() {
        return placedDate;
    }

    public void setPlacedDate(Date placedDate) {
        this.placedDate = placedDate;
    }

    public InventoryEntity() {
    }


}
