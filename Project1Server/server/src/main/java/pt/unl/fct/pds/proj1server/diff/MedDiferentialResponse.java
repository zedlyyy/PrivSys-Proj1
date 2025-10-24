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
    private final MedDataRepository medRep;

    private Config laplaceConfig;

    public MedDiferentialResponse(String attribute, String value, MedDataRepository medRep, Config laplaceConfig) {
        this.sens = 1;
        this.budget = 0;
        this.attribute = attribute;
        this.value = value;
        this.medRep = medRep;

        this.laplaceConfig = laplaceConfig;
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
        double x = this.sens / laplaceConfig.getLaplace();
        LaplaceDistribution laplace = new LaplaceDistribution(0, x);
        laplaceConfig.setLaplace(laplaceConfig.getLaplace() + CONFIDENTIALITY_LOSS);
        setBudget(laplaceConfig.getLaplace());
        return laplace.sample();
    }

    public double getCountPlusNoise() {
        return getCount() + laplace();
    }

}
