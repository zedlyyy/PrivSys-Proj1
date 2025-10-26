package pt.unl.fct.pds.proj1server.repository;

import pt.unl.fct.pds.proj1server.model.MedData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MedDataRepository extends JpaRepository<MedData, Long> {
    // Custom query methods can be defined here
    long countByName(String name);
    long countByAge(int age);
    long countByAddress(String address);
    long countByEmail(String email);
    long countByGender(String gender);
    long countByPostalCode(String postalCode);
    long countByDiagnosis(String diagnosis);

    @Query("SELECT COUNT(md.age) FROM MedData md WHERE md.age IS NOT NULL")
    int getTotalAgeDataPoints();
    
    @Query("SELECT SUM(md.age) FROM MedData md")
    Long getTotalAge();

    @Query("SELECT COUNT(md.age) FROM MedData md WHERE md.diagnosis = :diagnosis")
    int getTotalAgeDataPointsByDiagnosis(@Param("diagnosis") String diagnosis);

    @Query("SELECT SUM(md.age) FROM MedData md WHERE md.diagnosis = :diagnosis")
    int getSumAgeByDiagnosis(@Param("diagnosis") String diagnosis);

}
