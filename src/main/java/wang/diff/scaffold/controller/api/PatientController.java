package wang.diff.scaffold.controller.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import wang.diff.scaffold.controller.PatientApi;
import wang.diff.scaffold.controller.model.PatientAddDTO;
import wang.diff.scaffold.controller.model.PatientDTO;
import wang.diff.scaffold.controller.model.PatientPageDTO;
import wang.diff.scaffold.service.IPatientService;

@RestController
@Tag(name="patient")
public class PatientController implements PatientApi {

    @Resource
    private IPatientService patientService;

    @Override
    public ResponseEntity<PatientDTO> addOnePatient(PatientAddDTO patientAddDTO) {
        PatientDTO patientDTO = patientService.addOne(patientAddDTO);
        return ResponseEntity.ok(patientDTO);
    }

    @Override
    public ResponseEntity<PatientPageDTO> getPagePatient(Integer pageNum, Integer pageSize, String name) {
        PatientPageDTO page = patientService.getPage(pageNum, pageSize, name);
        return ResponseEntity.ok(page);
    }
}
