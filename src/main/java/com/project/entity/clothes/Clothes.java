package com.project.entity.clothes;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "clothes")
public class Clothes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clothesId;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private Category category;

    @Column(nullable = false)
    private byte[] image;

    @Column(nullable = false)
    private Integer cost;
}
