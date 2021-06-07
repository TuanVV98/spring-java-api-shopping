package com.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="orders")
public class Order implements Serializable {

	
	private static final long serialVersionUID = 5514528747731992863L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="address")
	private String address;
	
	@Column(name="created_at")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdAt;
	
	@Column(name="deleted_at")
	private Date deletedAt;
	
	@Column(name="deleted_user")
	private String deletedUser;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(
			name="user_id",
			nullable = false,
			referencedColumnName = "id"
			)
	private User user;
	
	@OneToMany(
			mappedBy = "order",
			fetch = FetchType.LAZY
			)
	private List<OrderDetails> order_detail;
	
	
}
