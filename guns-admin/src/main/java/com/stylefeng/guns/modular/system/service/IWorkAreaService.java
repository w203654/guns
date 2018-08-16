package com.stylefeng.guns.modular.system.service;

import com.stylefeng.guns.modular.system.model.WorkArea;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 车间对槽号 服务类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-05-28
 */
public interface IWorkAreaService extends IService<WorkArea> {
    List<String > selectPontos(@Param("workCode") String workCode);

}
