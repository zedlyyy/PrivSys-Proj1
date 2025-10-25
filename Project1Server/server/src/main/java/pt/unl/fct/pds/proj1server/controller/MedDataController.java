package pt.unl.fct.pds.proj1server.controller;

import pt.unl.fct.pds.proj1server.model.MedData;
import pt.unl.fct.pds.proj1server.diff.Config;
import pt.unl.fct.pds.proj1server.diff.MedDiferentialResponse;
import pt.unl.fct.pds.proj1server.model.CountRequest;
import pt.unl.fct.pds.proj1server.model.CountResponse;
import pt.unl.fct.pds.proj1server.repository.MedDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/meddata")
public class MedDataController {

    @Autowired
    private MedDataRepository medDataRepository;

    @Autowired
    private Config laplaceConfig;

    @GetMapping
    public List<MedData> getAllMedDatas() {
        return medDataRepository.findAll();
    }

    @GetMapping("/{id}")
    public MedData getMedDataById(@PathVariable Long id) {
        return medDataRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "MedData not found with id: " + id));
    }

    @PostMapping("/count")
    public CountResponse getMedDataCount(@RequestBody CountRequest countRequest) {
        MedDiferentialResponse response = new MedDiferentialResponse(countRequest.getAttribute(), countRequest.getValue(), medDataRepository, laplaceConfig);
        return new CountResponse(countRequest.getAttribute(), response.getCountPlusNoise(), response.getCountSens(), response.getBudget());
    }

    @PostMapping("/average")
    public CountResponse getMedDataAverage(@RequestBody CountRequest countRequest) {
        MedDiferentialResponse response = new MedDiferentialResponse(countRequest.getAttribute(), countRequest.getValue(), medDataRepository, laplaceConfig);
        return new CountResponse(countRequest.getAttribute(), response.getCountPlusNoise(), response.getCountSens(), response.getSumSens(), response.getBudget());
    }
}
