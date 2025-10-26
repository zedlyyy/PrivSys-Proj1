package pt.unl.fct.pds.proj1server.diff;

import org.apache.commons.math3.distribution.LaplaceDistribution;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.annotation.PostConstruct;
import pt.unl.fct.pds.proj1server.repository.MedDataRepository;

public class MedDiferentialResponse {

    private static int AGE_SUM_SENS = 125;
    private static int COUNT_SENS = 1;  
    private static int ALL_AGE_DATAPOINTS = 0;
    private int countSens;
    private int sumSens;
    private double budget;
    private String attribute;
    private String value;
    private final double CONFIDENTIALITY_LOSS = 0.1;
    private final MedDataRepository medRep;

    private Config laplaceConfig;

    public MedDiferentialResponse(String attribute, String value, MedDataRepository medRep, Config laplaceConfig) {
        this.countSens = COUNT_SENS;
        this.budget = 0;
        this.sumSens = AGE_SUM_SENS;
        this.attribute = attribute;
        this.value = value;
        this.medRep = medRep;

        this.laplaceConfig = laplaceConfig;
    }

    private long getCount() {
        long countAttribute = 0;

        if(Integer.parseInt(value) == ALL_AGE_DATAPOINTS) {
            return medRep.getTotalAgeDataPoints();
        }

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

    private double getSumAge() {
        return medRep.getTotalAge();
    }

    public int getCountSens() {
        return this.countSens;
    }

    public int getSumSens() {
        return this.sumSens;
    }

    public double getBudget() {
        return this.budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    private double laplace(int sens) {
        double x = sens / laplaceConfig.getLaplace();
        LaplaceDistribution laplace = new LaplaceDistribution(0, x);
        laplaceConfig.setLaplace(laplaceConfig.getLaplace() + CONFIDENTIALITY_LOSS);
        setBudget(laplaceConfig.getLaplace());
        return laplace.sample();
    }

    private double getSumPlusNoise() {
        return getSumAge() + laplace(this.sumSens);
    }

    public double getCountPlusNoise() {
        return getCount() + laplace(this.countSens);
    }

    public double getAveragePlusNoise() {
        return getSumPlusNoise() / getCountPlusNoise();
    }
    



}
