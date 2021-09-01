package com.spring.model;


import com.spring.dto.model.UserDTO;
import com.spring.enumeration.RoleEnum;
import com.spring.enumeration.UserStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 5514528747731992863L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private UserStatusEnum status;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private RoleEnum role;

    @Column(name="registered_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date registeredAt;

    @Column(name = "updated_at")
    private LocalDateTime updateAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @OneToOne(
                fetch = FetchType.LAZY,
                cascade = CascadeType.ALL,
                mappedBy = "user"
            )
    private UserProfile userProfile;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "user"
    )
    private VerificationToken verificationToken;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "user"
    )
    private List<Product> products;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "user"
    )
    private List<Order> order;

    public boolean isAdmin(){
        return RoleEnum.ROLE_ADMIN.toString().equals(this.role.toString());
    }

    public UserDTO convertEntityToDTO(){
        return new ModelMapper().map(this,UserDTO.class);
    }

}
