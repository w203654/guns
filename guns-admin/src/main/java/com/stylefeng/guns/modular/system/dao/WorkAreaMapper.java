package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.WorkArea;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 车间对槽号 Mapper 接口
 * </p>
 *
 * @author stylefeng123
 * @since 2018-05-28
 */
public interface WorkAreaMapper extends BaseMapper<WorkArea> {

    List<String> selectPotnos(String workCode);
}
