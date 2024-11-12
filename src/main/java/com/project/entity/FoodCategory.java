package com.project.entity;

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
    private Integer schToday;

    @Column(nullable = false)
    private Integer schWeek;

    @Column(nullable = false)
    private Integer schMonth;
}
