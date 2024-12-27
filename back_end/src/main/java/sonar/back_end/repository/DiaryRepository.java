package sonar.back_end.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sonar.back_end.entity.DiaryEntity;

import java.time.LocalDate;
import java.util.List;

public interface DiaryRepository extends JpaRepository<DiaryEntity, Long> {
    List<DiaryEntity> findByUserIdAndDiaryDateBetween(
            Long userId,
            LocalDate startDate,
            LocalDate endDate
    );

    List<DiaryEntity> findByUserIdAndDiaryDate(Long userId, LocalDate date);

}
