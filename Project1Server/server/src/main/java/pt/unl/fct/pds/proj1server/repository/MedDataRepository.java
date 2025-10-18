package pt.unl.fct.pds.proj1server.repository;

import pt.unl.fct.pds.proj1server.model.MedData;
import org.springframework.data.jpa.repository.JpaRepository;
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
}
