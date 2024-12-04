package com.project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "signUp")
public class SignUp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long signUpId;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String telNum;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String detailAddress;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String restaurantAddress;

    @Column(nullable = false)
    private String businessName;

    @Column(nullable = false)
    private String propritor;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String restaurantTelNum;

    @Column(nullable = false)
    private String takeOut;

    @Lob
    private byte[] foreGround;

    @Lob
    private byte[] interior;

    @Lob
    private byte[] menu;
}
