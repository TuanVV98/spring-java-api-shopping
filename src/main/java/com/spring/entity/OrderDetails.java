package com.spring.entity;

import java.io.Serializable;
//import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="order_details")
public class OrderDetails implements Serializable {
	
	private static final long serialVersionUID = 5514528747731992863L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="price")
	private float price;
	
	@Column(name="quanlity")
	private Integer quanlity;
	
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
			name="order_id",
			nullable = false,
			referencedColumnName = "id"
			)
	private Order order;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(
			name="product_id",
			nullable = false,
			referencedColumnName = "id"
			)
	private Product product;
}
