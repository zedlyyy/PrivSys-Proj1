package pt.unl.fct.pds.proj1server.mondrainAlgorithm;

import org.springframework.stereotype.Service;

import pt.unl.fct.pds.proj1server.repository.MedDeIdDataRepository;
import pt.unl.fct.pds.proj1server.repository.WorkDataRepository;

import java.util.List;
import pt.unl.fct.pds.proj1server.model.MedDataK;
import pt.unl.fct.pds.proj1server.repository.MedDataKRepository;

@Service
public class MondrainAlgorithm {
    
    private final int k;

    private final MedDataKRepository medKRep;

    public MondrainAlgorithm(int k, MedDataKRepository medKRep) {
        this.k = k;
    }

    public List<MedDataK> mondrainAlgorithm() {
        List<MedDataK> medDataKList = medKRep.findAll();
        return null;
    }
    
}
