package sonar.back_end.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sonar.back_end.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
