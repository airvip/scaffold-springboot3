package wang.diff.scaffold.dao;

import org.apache.ibatis.annotations.Mapper;
import wang.diff.scaffold.entity.Patient;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author airvip
 * @since 2023-09-05
 */
@Mapper
public interface PatientMapper extends BaseMapper<Patient> {

}
