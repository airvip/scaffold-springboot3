package wang.diff.scaffold.service.convert;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import wang.diff.scaffold.controller.model.OnePageDataPagination;
import wang.diff.scaffold.controller.model.PatientDTO;
import wang.diff.scaffold.controller.model.PatientPageDTO;
import wang.diff.scaffold.entity.Patient;

@Component
public class PatientConverter {

    private static <T> void fillPageInfo(PatientPageDTO rst, PageInfo<T> pd) {
        final OnePageDataPagination onePageDataPagination = new OnePageDataPagination();
        onePageDataPagination.setTotalSize((int)pd.getTotal());
        onePageDataPagination.setPageNum(pd.getPageNum());
        onePageDataPagination.setPageSize(pd.getPageSize());
        rst.setPagination(onePageDataPagination);
    }


    public PatientPageDTO covert2PageDto(PageInfo<Patient> pageData) {
        final PatientPageDTO rst = new PatientPageDTO();
        fillPageInfo(rst, pageData);
        rst.setList(pageData.getList().stream().map(this::convert2Dto).toList());
        return rst;
    }

    public PatientDTO convert2Dto(Patient patient) {
        PatientDTO patientDTO = new PatientDTO();
        BeanUtils.copyProperties(patient, patientDTO);
        return patientDTO;

    }

}