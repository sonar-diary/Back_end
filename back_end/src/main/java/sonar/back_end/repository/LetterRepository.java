package sonar.back_end.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sonar.back_end.entity.LetterEntity;

import java.time.LocalDate;
import java.util.List;

public interface LetterRepository extends JpaRepository<LetterEntity, Long> {
    List<LetterEntity> findByUserIdAndLetterDateBetween(
            Long userId,
            LocalDate startDate,
            LocalDate endDate
    );

    List<LetterEntity> findByUserIdAndLetterDate(Long userId, LocalDate date);
}
