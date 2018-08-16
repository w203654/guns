package com.stylefeng.guns.modular.ecc.warpper;

import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;

import java.util.List;
import java.util.Map;

/**
 * 产铝量盘存台账管理的包装类
 *
 * @author fengshuonan
 * @date 2018年6月04日 下午09:47:03
 */
public class WarehouseWarpper extends BaseControllerWarpper {

    public WarehouseWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
//        map.put("yield", ConstantFactory.me().getEccLvSum(map.get("onedate").toString(),map.get("twodate").toString(),map.get("potno").toString()) );
//        map.put("effi", ConstantFactory.me().getEccLvEffi(map.get("onedate").toString(),map.get("twodate").toString(),map.get("potno").toString(),map.get("onevalue").toString(),map.get("twovalue").toString()));
    }

}
