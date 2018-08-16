package com.stylefeng.guns.modular.ecc.service;

import com.stylefeng.guns.modular.system.model.MesPotnoweight;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * mes天车出铝量 服务类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-05-30
 */
public interface IMesPotnoweightService extends IService<MesPotnoweight> {

    List<Map<String,Object>> selectMesData(String dateTime);
}
