package pt.unl.fct.pds.proj1server.diferentialLogic;

import org.apache.commons.math3.distribution.LaplaceDistribution;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import pt.unl.fct.pds.proj1server.repository.MedDataRepository;
import pt.unl.fct.pds.proj1server.repository.WorkDataRepository;

@Service
public class DiferentialResponse {

    private long count;
    private int sens;
    private int budget;
    private String attribute;
    private String value;

    private final MedDataRepository medRep;
    private final WorkDataRepository workRep;

    @Value("${e.laplace}")
    private int epsilon;

    public DiferentialResponse(String attribute, String value, MedDataRepository medRep, WorkDataRepository workRep) {
        this.count = 0;
        this.sens = 1;
        this.budget = 0;
        this.attribute = attribute;
        this.value = value;

        this.medRep = medRep;
        this.workRep = workRep; 
    }

    public long getCount(String db) {
        realValue(db);
        return this.count;
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
        return laplace.sample();
    }

    private int realValue(String db) {
        switch (db) {
            case "work":
                //this.count = workRep.;
                break;
            case "med":
                break;
            default:
                break;
        }
        return 0;
    }

}
