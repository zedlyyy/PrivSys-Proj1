package pt.unl.fct.pds.proj1server.diff;

import org.apache.commons.math3.distribution.LaplaceDistribution;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.annotation.PostConstruct;
import pt.unl.fct.pds.proj1server.repository.MedDataRepository;

public class MedDiferentialResponse {

    private int sens;
    private double budget;
    private String attribute;
    private String value;
    private final double CONFIDENTIALITY_LOSS = 0.1;
    private double epsilon;
    private final MedDataRepository medRep;

    @Autowired
    private Config laplaceConfig;

    @PostConstruct
    public void initLaplace() {
        this.epsilon = laplaceConfig.getLaplace();
    }

    public MedDiferentialResponse(String attribute, String value, MedDataRepository medRep) {
        this.sens = 1;
        this.budget = 0;
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
                int valueToInt = Integer.parseInt(value);
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

    public void setBudget(double budget) {
        this.budget = budget;
    }

    private double laplace() {
        double x = this.sens / this.epsilon;
        LaplaceDistribution laplace = new LaplaceDistribution(0, x);
        laplaceConfig.setLaplace(laplaceConfig.getLaplace() + CONFIDENTIALITY_LOSS);
        setBudget(laplaceConfig.getLaplace());
        return laplace.sample();
    }

    public int getCountPlusNoise() {
        return (int) (getCount() + laplace());
    }

}
