package sonar.back_end.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sonar.back_end.entity.SocialType;
import sonar.back_end.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findBySocialIdAndSocialType(String socialId, SocialType socialType);
}
