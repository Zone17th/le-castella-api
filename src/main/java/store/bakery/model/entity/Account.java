package store.bakery.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Where;
import store.bakery.model.appEnum.RoleEnum;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Data
public class Account extends BaseEntity {
    @NotNull
    @Pattern(regexp = "\\S*", message = "Username must not contain spaces!")
    private String username;
    @NotNull
    @Pattern(regexp = "\\S*", message = "Password must not contain spaces!")
    private String password;
    @NotNull
    private String fullName;
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Store store;

    @OneToMany(mappedBy = "account")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Where(clause = "deleted = false")
    private List<Orders> orders;
}
