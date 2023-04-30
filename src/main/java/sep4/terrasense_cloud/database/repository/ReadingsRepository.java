package sep4.terrasense_cloud.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sep4.terrasense_cloud.model.Reading;

public interface ReadingsRepository extends JpaRepository<Reading,Long> {
}
