package sonar.back_end.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String socialId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SocialType socialType;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String profileImage;

    @Builder
    public UserEntity(String socialId, SocialType socialType, String name, String email, String profileImage) {
        this.socialId = socialId;
        this.socialType = socialType;
        this.name = name;
        this.email = email;
        this.profileImage = profileImage;
    }
}

