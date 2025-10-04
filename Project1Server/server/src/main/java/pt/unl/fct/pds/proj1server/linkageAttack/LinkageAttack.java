package pt.unl.fct.pds.proj1server.linkageAttack;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import pt.unl.fct.pds.proj1server.model.MedDataDeId;
import pt.unl.fct.pds.proj1server.model.WorkData;
import pt.unl.fct.pds.proj1server.repository.MedDeIdDataRepository;
import pt.unl.fct.pds.proj1server.repository.WorkDataRepository;

@Service
public class LinkageAttack {

    private final WorkDataRepository workDataRepository;
    private final MedDeIdDataRepository medDeIdDataRepository;

    //Spring boot automaticamente injeta as intancias das bd's
    public LinkageAttack (WorkDataRepository workDataRepository, MedDeIdDataRepository medDeIdDataRepository){
        this.workDataRepository     = workDataRepository;
        this.medDeIdDataRepository  = medDeIdDataRepository;
    }

    public List<String> linkageAttack() {
		List<WorkData> workData = workDataRepository.findAll();
		List<MedDataDeId> medDeIdData = medDeIdDataRepository.findAll();

		List<String> matches = new ArrayList<>();

		for (WorkData work : workData) {
			for (MedDataDeId med : medDeIdData) {
				if (work.getGender().equals(med.getGender()) && work.getPostalCode().equals(med.getPostalCode())) {
					String nameFull = work.getFName().concat(" ").concat(work.getLName());
					matches.add(nameFull);
				}
			}
		}
        return matches;
	}

}
