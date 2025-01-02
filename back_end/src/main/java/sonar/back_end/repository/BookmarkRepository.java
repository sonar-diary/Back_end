package sonar.back_end.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sonar.back_end.entity.BookmarkEntity;

public interface BookmarkRepository extends JpaRepository<BookmarkEntity, Long> {
}
