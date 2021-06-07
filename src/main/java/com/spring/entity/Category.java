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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "categories")
public class Category implements Serializable {

	/**
	 * Khả năng tuần tự hóa của 1 lớp
	 * 
	 * https://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html
	 */
	private static final long serialVersionUID = 5514528747731992863L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name="name")
	private String name;

	@Column(name="created_at" )
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdAt;

	@Column(name="deleted_at")
	private Date deletedAt;

	@Column(name="deleted_user")
	private String deletedUser;
	
	@OneToMany(
			fetch = FetchType.LAZY,
			mappedBy = "category"
			)
	private List<Product> product;
}
