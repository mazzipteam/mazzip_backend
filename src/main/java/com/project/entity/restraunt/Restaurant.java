package com.project.entity.restraunt;

import com.project.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import static com.project.entity.restraunt.Region.성북구;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantId;

    @Column(unique = true, nullable = false)
    private String name; // 동일 이름의 경우 지점으로 다르게 명시할 것

    @Column(nullable = false)
    private String address; // TODO: 가능하면 주소 인증(?)

    @Column(nullable = false)
    private Double rating;

    @Column(nullable = false)
    private String businessName;

    @Column(nullable = false)
    private String propritor;

    @Column(nullable = false)
    private Category category;

    @Column(nullable = false)
    private Region region; // 성북구 맛집이므로, "성북구" 고정

    @Column(nullable = false)
    private String latLng; // 위경도에 대한 JSON 값 저장 // 주소와 일치해야함

    @Column(nullable = false)
    private String telNum; // TODO: 전화번호 서식 검사

    @Column(nullable = false)
    private Character takeOut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
