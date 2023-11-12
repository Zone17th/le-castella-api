package store.bakery.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Where;
import store.bakery.model.appEnum.PaymentMethodEnum;
import store.bakery.model.appEnum.StatusEnum;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Data
public class Orders extends BaseEntity {
    private Long totalPrice;
    private Long discount;
    private LocalDateTime orderDate;
    private LocalDateTime ReceiveDate;
    private String receiverName;
    private String receiverPhone;
    @Enumerated(EnumType.STRING)
    private PaymentMethodEnum paymentMethod;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    private String strData;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Account account;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Where(clause = "deleted = false")
    private List<OrderDetail> orderDetails;
}
