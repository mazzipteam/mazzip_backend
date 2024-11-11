package com.project.api.user;

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
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true, nullable = false)
    private String email; // TODO: 최종에서는 서식 검사할 것

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String telNum; // TODO: 전화번호 서식 검사

    @Column(nullable = false)
    private String address; // TODO: 가능하면 주소 인증(?)

    @Column(nullable = false)
    private String detailAddress;

    @Column(nullable = false)
    private LocalDateTime createAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private Role role = Role.USER;
}
