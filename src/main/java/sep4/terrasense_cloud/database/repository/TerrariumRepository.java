package sep4.terrasense_cloud.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import sep4.terrasense_cloud.model.Customer;
import sep4.terrasense_cloud.model.Terrarium;

public interface TerrariumRepository extends JpaRepository<Terrarium, Long> {
    Terrarium getTerrariumByCustomerAndId(Customer customer, Long terrariumId);

    @Modifying
    @Query("UPDATE Terrarium t SET t.name = ?2, t.minTemperature = ?3, t.maxTemperature = ?4, t.minHumidity = ?5, t.maxHumidity = ?6, t.minCO2 = ?7, t.maxCO2 = ?8 WHERE t.id = ?1")
    void alterTerrarium(Long id, String name, double minTemperature, double maxTemperature , double minHumidity, double maxHumidity, int minCO2, int maxCO2);

    @Modifying
    @Query("UPDATE Terrarium t SET t.customer = null WHERE t.id = ?1")
    void deleteTerrarium(Long id);
}
