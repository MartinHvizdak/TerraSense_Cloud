package sep4.terrasense_cloud.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sep4.terrasense_cloud.model.Alert;

public interface AlertRepository extends JpaRepository<Alert, Long>{
}
