package com.spring.dto.model;

import com.spring.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
//@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;

    @NotNull(message = "Model cannot be null.")
    @Length(min = 1, max = 100, message = "Username must contain between 1 and  100 characters.")
    private String model;

    @NotNull(message = "Name cannot be null.")
    @Length(min = 1, max = 50, message = "Username must contain between 1 and  50 characters.")
    private String name;

    private String image;

    @NotNull(message = "Price cannot be null.")
    private double price;

    @NotNull(message = "Description cannot be null.")
    @Length(min = 1, max = 550, message = "Username must contain between 1 and  550 characters.")
    private String description;


    private Date createdAt;


    private LocalDateTime updatedAt;


    private LocalDateTime deletedAt;

    @NotNull(message = "User id cannot be null.")
    private Long userID;

    @NotNull(message = "Category id cannot be null.")
    private Long categoryID;

    private String categoryName;

    public ProductDTO(Long id, String model, String name, String image, double price, String description,
                      Date createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt, Long userID,
                      Long categoryID, String categoryName) {
        this.id = id;
        this.model = model;
        this.name = name;
        this.image = image;
        this.price = price;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.userID = userID;
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }

    public Product convertDTOToEntity(){
        return new ModelMapper().map(this,Product.class);
    }
}
