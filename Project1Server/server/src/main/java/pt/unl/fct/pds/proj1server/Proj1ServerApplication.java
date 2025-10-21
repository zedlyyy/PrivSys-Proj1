package pt.unl.fct.pds.proj1server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import pt.unl.fct.pds.proj1server.linkageAttack.LinkageAttack;
import pt.unl.fct.pds.proj1server.mondrainAlgorithm.MondrainAlgorithm;

import java.util.List;

@SpringBootApplication
public class Proj1ServerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context 	= SpringApplication.run(Proj1ServerApplication.class, args);
		LinkageAttack linkageAttackFunction 	= context.getBean(LinkageAttack.class);
		MondrainAlgorithm mondrainAlgorithm 	= context.getBean(MondrainAlgorithm.class);

		//List<String> matches = linkageAttackFunction.linkageAttack();
		/*
		int matchesDeId = linkageAttackFunction.linkageAttackDeId();
		mondrainAlgorithm.mondrainAlgorithmStart();
		int matchesK = linkageAttackFunction.linkageAttackK();
		System.out.println("Linkage attack result: " + matchesDeId);
		System.out.println("Linkage attack result with k-anonymity: " + matchesK);
		*/
	}
}
