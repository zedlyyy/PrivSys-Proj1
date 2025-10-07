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
	
    public LinkageAttack (WorkDataRepository workDataRepository, MedDeIdDataRepository medDeIdDataRepository){
        this.workDataRepository     = workDataRepository;
        this.medDeIdDataRepository  = medDeIdDataRepository;
    }

	public WorkDataRepository getWorkDataRepository() {
		return workDataRepository;
	}

    public int linkageAttack() {
		List<WorkData> workData = workDataRepository.findAll();
		List<MedDataDeId> medData = medDeIdDataRepository.findAll();

		//List<String> matches = new ArrayList<>();

		int matches = 0;
		for (WorkData work : workData) {
			for (MedDataDeId med : medData) {
				if (work.isLinked(med)) {
					matches++;
					/* 
					String nameFull = work.getFName().concat(" ").concat(work.getLName());
					if (!matches.contains(nameFull)) 
						matches.add(nameFull);
					*/
				}
			}
		}
        return matches;
	}

}
