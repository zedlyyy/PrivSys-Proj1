package pt.unl.fct.pds.proj1server.repository;

import pt.unl.fct.pds.proj1server.model.MedDataK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedDataKRepository extends JpaRepository<MedDataK, Long> {
    // Custom query methods can be defined here
}
