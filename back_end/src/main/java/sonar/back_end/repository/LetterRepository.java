package sonar.back_end.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sonar.back_end.entity.LetterEntity;

public interface LetterRepository extends JpaRepository<LetterEntity, Long> {
}
