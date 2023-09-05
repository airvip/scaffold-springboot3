package wang.diff.scaffold.service;

import com.baomidou.mybatisplus.extension.service.IService;
import wang.diff.scaffold.controller.model.PatientAddDTO;
import wang.diff.scaffold.controller.model.PatientDTO;
import wang.diff.scaffold.controller.model.PatientPageDTO;
import wang.diff.scaffold.entity.Patient;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author airvip
 * @since 2023-09-05
 */
public interface IPatientService extends IService<Patient> {

    PatientPageDTO getPage(Integer pageNum, Integer pageSize, String name);

    PatientDTO addOne(PatientAddDTO patientAddDTO);

}
