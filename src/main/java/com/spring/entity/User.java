package com.spring.entity;

import java.io.Serializable;
//import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.spring.enumeration.RoleEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name="users")
public class User implements Serializable {
	
	/**
	 * Khả năng tuần tự hóa của 1 lớp
	 * 
	 * https://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html
	 * -----
	 * Infinite Recursion with Jackson JSON and Hibernate JPA issue
	 * 
	 * https://stackoverflow.com/questions/3325387/infinite-recursion-with-jackson-json-and-hibernate-jpa-issue/18288939#18288939
	 */
	private static final long serialVersionUID = 5514528747731992863L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;	
	
	@Column(name="address")
	private String address;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private RoleEnum role;
	
	@Column(name="created_at")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdAt;
	
	@Column(name="deleted_at")
	private Date deletedAt;
	
	@Column(name="deleted_user")
	private Integer deletedUser;
	
	@OneToMany(mappedBy = "user")
	private List<Order> orders;
	
	
	/**
	 * Method that verifies if the user is admin
	 * 
	 * @since 11/06/2021
	 * 
	 * @return boolean
	 */
	public boolean isAdmin() {
		return RoleEnum.ROLE_ADMIN.toString().equals(this.role.toString());
	}
}
