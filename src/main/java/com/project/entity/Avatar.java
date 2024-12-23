package com.project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "avatar")
public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long avatarId;

    @Column(nullable = false)
    private String name;

    @Builder.Default
    @Column(nullable = false)
    private int level = 0;

    @Builder.Default
    @Column(nullable = false)
    private int experience = 0;

    @Builder.Default
    @Column(nullable = false)
    private int evolution = 1;

    @Builder.Default
    @Column(nullable = false)
    private LocalDateTime eatDate = LocalDateTime.now();

    @Column(nullable = false)
    private int eatCount;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "avatar", cascade = CascadeType.ALL)
    private List<MyClothes> myClothes;
}
