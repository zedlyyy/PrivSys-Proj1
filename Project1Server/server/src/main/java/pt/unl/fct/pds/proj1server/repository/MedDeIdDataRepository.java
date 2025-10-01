package pt.unl.fct.pds.proj1server.repository;

import pt.unl.fct.pds.proj1server.model.MedDataDeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedDeIdDataRepository extends JpaRepository<MedDataDeId, Long> {
    // Custom query methods can be defined here
}