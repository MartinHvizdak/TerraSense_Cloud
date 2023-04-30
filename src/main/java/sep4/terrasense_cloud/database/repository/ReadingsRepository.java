package sep4.terrasense_cloud.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sep4.terrasense_cloud.model.Reading;

import java.util.ArrayList;

public interface ReadingsRepository extends JpaRepository<Reading,Long> {
    ArrayList<Reading> findAllByOrderByTimeDesc();
}
