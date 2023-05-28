package sep4.terrasense_cloud.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import sep4.terrasense_cloud.model.Animal;

import java.time.LocalDate;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    @Modifying
    @Query("UPDATE Animal a SET a.name = ?2, a.picture = ?3, a.gender = ?4, a.species = ?5, a.dateOfBirth = ?6 WHERE a.id = ?1")
    void alterAnimal(Long id, String name, String picture, String gender , String species, LocalDate dateOfBirth);
}
