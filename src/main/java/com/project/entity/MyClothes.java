package com.project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.entity.clothes.Clothes;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "my_clothes")
public class MyClothes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long myClothesId;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "clothes_id")
    private Clothes clothes;

    @Column(nullable = false)
    private Character wear;

    @Column(nullable = false)
    private LocalDateTime purchaseTime;
}
