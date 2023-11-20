package com.study.jpa.chap01_basic.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_id")
    private long id;
    @Column(name = "prod_name", nullable = false, length = 30)
    private String name;

    private int price;

    @Enumerated(EnumType.STRING)
    private Category category;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createDate;

    @UpdateTimestamp
    private LocalDateTime updateDate;

    public enum Category {
        FOOD, FASHION, ELECTRONIC
    }

}