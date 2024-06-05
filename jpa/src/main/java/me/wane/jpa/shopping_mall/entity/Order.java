package me.wane.jpa.shopping_mall.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDate orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public void setMember(Member member) {
//        if (this.member != null) {
//            this.member.getOrders().remove(this);
//        }
//        this.member = member;
//        member.getOrders().add(this);

        this.member = member;

        if (!member.getOrders().contains(this)) {
            member.getOrders().add(this);
        }
    }
}
