package pt.unl.fct.pds.proj1server.mondrainAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public int getBaseEntrySize(){
        return (kValue * 2) - 1; 
    }

    public void mondrainAlgorithmStart() {
        List<MedDataK> medDataKList = medKRep.findAll();
        List<MedDataK> kAnonResult = mondrainAlgorithm(medDataKList);
        medKRep.saveAll(kAnonResult);
    }

    public List<MedDataK> mondrainAlgorithm(List<MedDataK> medDataKRecursive){
        int listSize = medDataKRecursive.size();

        //Base Case
        if (listSize <= getBaseEntrySize()) {
            int maxPostalCode   = Integer.MIN_VALUE;
            int minPostalCode   = Integer.MAX_VALUE;

            for (MedDataK dataEntry : medDataKRecursive){
                dataEntry.setGender("*");

                int postalCodeInt = Integer.parseInt(dataEntry.getPostalCode());

                maxPostalCode = (postalCodeInt > maxPostalCode) ? postalCodeInt : maxPostalCode;
                minPostalCode = (postalCodeInt < minPostalCode) ? postalCodeInt : minPostalCode;
            }
            for (MedDataK dataEntry : medDataKRecursive){
                dataEntry.setPostalCode(Integer.toString(minPostalCode) + "-" + Integer.toString(maxPostalCode));
            }

            return medDataKRecursive;
        }
        else {
            List<MedDataK> leftSideMedDataK     = medDataKRecursive.subList(0, medDataKRecursive.size() / 2); 
            List<MedDataK> rightSideMedDataK    = medDataKRecursive.subList(medDataKRecursive.size() / 2, medDataKRecursive.size());

            List<MedDataK> leftSideMedDataKResult   = mondrainAlgorithm(leftSideMedDataK);
            List<MedDataK> rightSideMedDataKResult  = mondrainAlgorithm(rightSideMedDataK);

            List<MedDataK> unionOfResults = new ArrayList<>(leftSideMedDataKResult);
            unionOfResults.addAll(rightSideMedDataKResult);

            return unionOfResults;
        }
    }

}
