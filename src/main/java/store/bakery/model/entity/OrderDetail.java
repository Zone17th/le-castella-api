package store.bakery.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Data
public class OrderDetail extends BaseEntity {
    private Integer quantity;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Orders orders;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private SubProduct subProduct;
}
