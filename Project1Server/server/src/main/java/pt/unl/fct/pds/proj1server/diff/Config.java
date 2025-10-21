package pt.unl.fct.pds.proj1server.diff;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@ConfigurationProperties(prefix = "e")
public class Config {
    private double laplace;

    public Config() {
    }

    public double getLaplace(){
        return laplace;
    }
    
    public void setLaplace(double epsilon) {
        this.laplace = epsilon;
    }

}
