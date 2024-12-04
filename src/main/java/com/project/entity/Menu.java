package com.project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.entity.restraunt.Restaurant;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id")
    private Long menuId;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "food_category_id")
    private FoodCategory foodCategory;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private char cheap;

    @Lob
    @Column(name = "image",nullable = false, columnDefinition="LONGBLOB")
    private byte[] image;

    @Column(nullable = false)
    private char main;

    @Column(nullable = false)
    private char isReserve;
}
