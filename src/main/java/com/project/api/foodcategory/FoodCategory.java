package com.project.api.foodcategory;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "food_category")
public class FoodCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodCategoryId;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private int schToday = 0;

    @Column(nullable = false)
    private int schWeek = 0;

    @Column(nullable = false)
    private int schMonth = 0;
}
