package pt.unl.fct.pds.proj1server.linkageAttack;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import pt.unl.fct.pds.proj1server.model.MedDataDeId;
import pt.unl.fct.pds.proj1server.model.MedDataK;
import pt.unl.fct.pds.proj1server.model.WorkData;
import pt.unl.fct.pds.proj1server.repository.MedDataKRepository;
import pt.unl.fct.pds.proj1server.repository.MedDeIdDataRepository;
import pt.unl.fct.pds.proj1server.repository.WorkDataRepository;

@Service
public class LinkageAttack {
	
    private final WorkDataRepository workDataRepository;
    private final MedDeIdDataRepository medDeIdDataRepository;
	private final MedDataKRepository medDataKRepository;
	
    public LinkageAttack (WorkDataRepository workDataRepository, MedDeIdDataRepository medDeIdDataRepository, MedDataKRepository medDataKRepository) {
        this.workDataRepository     = workDataRepository;
        this.medDeIdDataRepository  = medDeIdDataRepository;
		this.medDataKRepository	 = medDataKRepository;
    }

	public WorkDataRepository getWorkDataRepository() {
		return workDataRepository;
	}

    public int linkageAttackDeId() {
		List<WorkData> workData = workDataRepository.findAll();
		List<MedDataDeId> medData = medDeIdDataRepository.findAll();

		List<String> matches = new ArrayList<>();

		for (WorkData work : workData) {
			for (MedDataDeId med : medData) {
				if (work.isLinkedDeId(med)) {				
					String nameFull = work.getFName().concat(" ").concat(work.getLName());
					if (!matches.contains(nameFull)) 
						matches.add(nameFull);
				}
			}
		}
        return matches.size();
	}

	public int linkageAttackK() {
		List<WorkData> workData = workDataRepository.findAll();
		List<MedDataK> medData = medDataKRepository.findAll();

		List<String> matches = new ArrayList<>();

		for (WorkData work : workData) {
			for (MedDataK med : medData) {
				if (work.isLinkedK(med)) {				
					String nameFull = work.getFName().concat(" ").concat(work.getLName());
					if (!matches.contains(nameFull)) 
						matches.add(nameFull);
				}
			}
		}
        return matches.size();
	}

}
