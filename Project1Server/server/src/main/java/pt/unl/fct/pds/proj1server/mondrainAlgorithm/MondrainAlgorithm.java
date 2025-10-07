package pt.unl.fct.pds.proj1server.mondrainAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import pt.unl.fct.pds.proj1server.model.MedDataK;
import pt.unl.fct.pds.proj1server.repository.MedDataKRepository;

@Service
public class MondrainAlgorithm {

    private final MedDataKRepository medKRep;

    @Value("${k.value}")
    private int kValue;

    public MondrainAlgorithm(MedDataKRepository medKRep) {
        this.medKRep = medKRep;
    }

    public List<MedDataK> mondrainAlgorithm() {
        List<MedDataK> medDataKList = medKRep.findAll();
        return medDataKList;
    }

}
