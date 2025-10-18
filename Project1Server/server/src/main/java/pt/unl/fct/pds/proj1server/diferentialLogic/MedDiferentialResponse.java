package pt.unl.fct.pds.proj1server.diferentialLogic;

import org.apache.commons.math3.distribution.LaplaceDistribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.unl.fct.pds.proj1server.repository.MedDataRepository;

@Service
public class MedDiferentialResponse {

    private int sens;
    private int budget;
    private String attribute;
    private String value;
    private final double CONFIDENTIALITY_LOSS = 0.1; 

    @Autowired
    private Config laplaceConfig;

    private final MedDataRepository medRep;

    private double epsilon;

    public MedDiferentialResponse(String attribute, String value, MedDataRepository medRep) {
        this.sens = 1;
        this.budget = 0;
        this.epsilon = laplaceConfig.getEpsilon();
        this.attribute = attribute;
        this.value = value;


        this.medRep = medRep;
    }

    private long getCount() {
        long countAttribute = 0;

        switch (attribute) {
            case "name":
                countAttribute = medRep.countByName(value);
                break;
            case "age":
                int valueToInt = Integer.parseInt(attribute);
                countAttribute = medRep.countByAge(valueToInt);
                break;
            case "address":
                countAttribute = medRep.countByAddress(value);
                break;
            case "email":
                countAttribute = medRep.countByEmail(value);
                break;
            case "gender":
                countAttribute = medRep.countByGender(value);
                break;
            case "postalCode":
                countAttribute = medRep.countByPostalCode(value);
                break;
            case "diagnosis":
                countAttribute = medRep.countByDiagnosis(value);
                break;
            default:
                break;
        }
        return countAttribute;
    }

    public int getSens() {
        return this.sens;
    }

    public double getBudget() {
        return this.budget;
    }

    private double laplace() {
        double x = this.sens / this.epsilon;
        LaplaceDistribution laplace = new LaplaceDistribution(x, 0);
        laplaceConfig.setEpsilon(laplaceConfig.getEpsilon() + CONFIDENTIALITY_LOSS);
        return laplace.sample();
    }

    public int getCountPlusNoise() {
        return (int) (getCount() + laplace());
    }

}
