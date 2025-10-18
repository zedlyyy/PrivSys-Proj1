package pt.unl.fct.pds.proj1server.diferentialLogic;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "e")
public class Config {
    private double laplace;

    public double getEpsilon(){
        return laplace;
    }
    
    public void setEpsilon(double epsilon) {
        this.laplace = epsilon;
    }

}
