package com.spring.repository;

import com.spring.dto.model.ProductDTO;
import com.spring.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    @Query(value = "SELECT new com.spring.dto.model.ProductDTO("
            + " p.id, p.model, p.name, p.image, p.price, p.description, p.createdAt,"
            + " p.updatedAt, p.deletedAt, p.user.id, c.id, c.name) "
            + "FROM Product p "
            + "INNER JOIN Category c ON p.category.id = c.id "
            + "WHERE p.deletedAt IS NULL ")
    List<ProductDTO> findByDeletedAtIsNull();

    @Query(value = "SELECT new com.spring.dto.model.ProductDTO("
            + " p.id, p.model, p.name, p.image, p.price, p.description, p.createdAt,"
            + " p.updatedAt, p.deletedAt, p.user.id, c.id, c.name) "
            + "FROM Product p "
            + "INNER JOIN Category c ON p.category.id = c.id "
            + "WHERE p.deletedAt IS NOT NULL ")
    List<ProductDTO> findByDeletedAtIsNotNull();

    @Query(value = "SELECT p FROM Product p "
                    + "INNER JOIN Category c ON p.category.id = c.id "
                    + "WHERE c.name =:name "
                    + "AND p.deletedAt IS NULL ")
    List<Product> findByCategory(@Param("name") String name);

    @Query(value = "SELECT p FROM Product p WHERE p.model =:model AND p.deletedAt IS NULL")
    Optional<Product> findByModelAndDeletedAtIsNull(@Param("model") String model);

    @Query(value = "SELECT p FROM Product p WHERE p.model =:model ")
    Optional<Product> findByModel(@Param("model") String model);
}
