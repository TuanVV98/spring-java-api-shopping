package com.spring.model;

import com.spring.dto.model.OrderDTO;
import com.spring.dto.model.UserDTO;
import com.spring.enumeration.OrderEnum;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "orders")
public class Order implements Serializable {
    private static final long serialVersionUID = 5514528747731992863L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "sub_total")
    private double subTotal;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderEnum status;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "address")
    private String address;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            columnDefinition = "id"
    )
    private User user;

    public OrderDTO convertEntityToDTO() {
        return new ModelMapper().map(this, OrderDTO.class);
    }

}
