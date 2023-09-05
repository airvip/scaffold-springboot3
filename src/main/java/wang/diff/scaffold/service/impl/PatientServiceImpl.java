package wang.diff.scaffold.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import wang.diff.scaffold.common.enums.CommonStatus;
import wang.diff.scaffold.controller.model.PatientAddDTO;
import wang.diff.scaffold.controller.model.PatientDTO;
import wang.diff.scaffold.controller.model.PatientPageDTO;
import wang.diff.scaffold.dao.PatientMapper;
import wang.diff.scaffold.entity.Patient;
import wang.diff.scaffold.service.IPatientService;
import wang.diff.scaffold.service.convert.PatientConverter;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author airvip
 * @since 2023-09-05
 */
@Service
@DS("patient")
public class PatientServiceImpl extends ServiceImpl<PatientMapper, Patient> implements IPatientService {

    /*@Resource
    private JdbcTemplate jdbcTemplate;*/

    @Resource
    private PatientMapper patientMapper;

    @Resource
    private PatientConverter patientConverter;


    @Override
    public PatientPageDTO getPage(Integer pageNum, Integer pageSize, String name) {
        PageHelper.startPage(pageNum, pageSize);
        QueryWrapper<Patient> queryWrapper = new QueryWrapper<>();
        if(StringUtils.hasText(name)){
            queryWrapper.like(Patient.NAME, name);
        }
        List<Patient> patients = patientMapper.selectList(queryWrapper);
        PageInfo<Patient> page = new PageInfo<>(patients);
        return patientConverter.covert2PageDto(page);
    }

    @Override
    @DS("patient")
    public PatientDTO addOne(PatientAddDTO patientAddDTO) {
        Patient patient = new Patient();
        patient.setName(patientAddDTO.getName());
        patient.setSex(Byte.valueOf(patientAddDTO.getSex().toString()));
        Date date = new Date();
        patient.setCreateTime(date);
        patient.setUpdateTime(date);
        patient.setStatus(CommonStatus.NORMAL.getCode());

        baseMapper.insert(patient);
        return patientConverter.convert2Dto(patient);
    }
}
