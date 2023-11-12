package store.bakery.model.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.Order;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import store.bakery.model.appEnum.SizeEnum;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Data
public class SubProduct extends BaseEntity {
    private Long price;
    @Enumerated(EnumType.STRING)
    private SizeEnum size;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Product product;

    @OneToMany(mappedBy = "subProduct", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<OrderDetail> orderDetails;
}
