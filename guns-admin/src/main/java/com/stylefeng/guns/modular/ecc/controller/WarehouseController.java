package com.stylefeng.guns.modular.ecc.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.IConstantFactory;
import com.stylefeng.guns.core.support.DateTimeKit;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.ecc.service.IBaseInfoService;
import com.stylefeng.guns.modular.ecc.service.IWarehouseHService;
import com.stylefeng.guns.modular.system.model.WarehouseForRadar;
import com.stylefeng.guns.modular.system.model.WarehouseH;
import com.stylefeng.guns.modular.system.model.WorkArea;
import com.stylefeng.guns.modular.system.service.IWorkAreaService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Warehouse;
import com.stylefeng.guns.modular.ecc.service.IWarehouseService;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * 产铝量盘存台账控制器
 *
 * @author fengshuonan
 * @Date 2018-05-15 23:56:17
 */
@Controller
@RequestMapping("/warehouse")
public class WarehouseController extends BaseController {

    private String PREFIX = "/ecc/warehouse/";

    @Autowired
    private IBaseInfoService baseInfoService;
    @Autowired
    private IWarehouseService warehouseService;
    @Autowired
    private IWarehouseHService warehouseServiceH;
    @Autowired
    private IWorkAreaService workAreaService;
    @Autowired
    IConstantFactory constantFactory;

    /**
     * 跳转到产铝量盘存台账首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "warehouse.html";
    }

    /**
     * 跳转到单槽管理复盘控制面版
     */
    @RequestMapping("/singleControl")
    public String singleControl() {
        return PREFIX + "singleControl.html";
    }
    /**
     * 跳转到单槽电解槽单槽建议出铝指示量页面
     */
    @RequestMapping("/singlePotnoLv")
    public String singlePotnoLv() {
        return PREFIX + "singlePotnoLv.html";
    }

    /**
     * 跳转电解槽电压设置建议
     */
    @RequestMapping("/voltageSetting")
    public String voltageSetting() {
        return PREFIX + "voltageSetting.html";
    }
    /**
     * 跳转电解槽氟化铝料量添加设置
     */
    @RequestMapping("/aluSetting")
    public String aluSetting() {
        return PREFIX + "aluSetting.html";
    }
    /**
     * 跳转到车间槽状态展示
     */
    @RequestMapping("/workshopStatus")
    public String workshopStatus() {
        return PREFIX + "workshopStatus.html";
    }
    /**
     * 跳转到参数分析界面
     */
    @RequestMapping("/parameterUnalysis")
    public String parameterUnalysis() {
        return PREFIX + "parameterUnalysis.html";
    }

    /**
     * 跳转到添加产铝量盘存台账
     */
    @RequestMapping("/warehouse_add")
    public String warehouseAdd() {
        return PREFIX + "warehouse_add.html";
    }

    /**
     * 跳转到修改产铝量盘存台账
     */
    @RequestMapping("/warehouse_update/{warehouseId}")
    public String warehouseUpdate(@PathVariable Integer warehouseId, Model model) {
        Warehouse warehouse = warehouseService.selectById(warehouseId);
        model.addAttribute("item", warehouse);
        LogObjectHolder.me().set(warehouse);
        return PREFIX + "warehouse_edit.html";
    }

    /**
     * 获取产铝量盘存台账列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String workCode, String potNo) {
        //查询区间槽号
        List<String> listPotno = workAreaService.selectPontos(workCode);

        //查询槽号根据条件
        Wrapper<Warehouse> wrapper = new EntityWrapper<>();
        if (ToolUtil.isNotEmpty(potNo))
            wrapper.like("potno", potNo);

        if (ToolUtil.isNotEmpty(workCode))
            wrapper.in("potno", listPotno);

        List<Map<String, Object>> list = warehouseService.selectMaps(wrapper);
        return list;
//        return new WarehouseWarpper(list).warp();
    }

    /**
     * 获取产铝量盘存台账列表
     */
    @RequestMapping(value = "/singleList")
    @ResponseBody
    public Object singleList(String newDate, String potNo) {
        List<Map<String, Object>> list=warehouseService.selectSingleTable(newDate, potNo);
        return list;
    }
    /**
     * 获取单槽指示出铝量
     */
    @RequestMapping(value = "/singlePotnoData")
    @ResponseBody
    public Object singlePotnoData(String newDate, String potNo) {
        List<Map<String, Object>> list=warehouseService.singlePotnoData(newDate, potNo);
        return list;
    }
    /**
     * 单槽指示高效率7台
     */
    @RequestMapping(value = "/singlePotnoHeight")
    @ResponseBody
    public Object singlePotnoHeight(String newDate) {
        List<Map<String, Object>> list=warehouseService.singlePotnoHeight(newDate);
        String[] type = list.get(0).get("type").toString().split(",");
        String[] val =  list.get(0).get("val").toString().split(",");
        Map<String,Object> map=new HashMap<>();
        map.put("type",type);
        map.put("val",val);
        return map;
    }
    /**
     * 单槽指示低效率7台
     */
    @RequestMapping(value = "/singlePotnoLow")
    @ResponseBody
    public Object singlePotnoLow(String newDate) {
        List<Map<String, Object>> list=warehouseService.singlePotnoLow(newDate);
        String[] type = list.get(0).get("type").toString().split(",");
        String[] val =  list.get(0).get("val").toString().split(",");
        Map<String,Object> map=new HashMap<>();
        map.put("type",type);
        map.put("val",val);
        return map;
    }

    /**
     * 获取效率参数分析
     */
    @RequestMapping(value = "/parameterUnalysisPotnoList")
    @ResponseBody
    public Object parameterUnalysisPotnoList(String newDate, String workCode, String startInt, String endInt) {
        if(ToolUtil.isOneEmpty(startInt,endInt)){startInt="0";endInt="500";}
        List<Map<String, Object>> list=warehouseService.parameterUnalysisPotnoList(newDate, workCode,Integer.parseInt(startInt),Integer.parseInt(endInt));
        return list;
    }
    /**
     * 获取效率参数分析
     */
    @RequestMapping(value = "/parameterUnalysisPotnoAvage")
    @ResponseBody
    public Object parameterUnalysisPotnoAvage(String newDate, String workCode, String startInt, String endInt) {
        if(ToolUtil.isOneEmpty(startInt,endInt)){startInt="0";endInt="500";}
        List<Map<String, Object>> list=warehouseService.parameterUnalysisPotnoAvage(newDate, workCode,Integer.parseInt(startInt),Integer.parseInt(endInt));
        return list;
    }


    /**
     * 获取产铝量盘存台雷达图
     */
    @RequestMapping(value = "/singleListForRadar")
    @ResponseBody
    public Object singleListForRadar(String newDate, String potNo) {

        List<Map<String, Object>> list = warehouseService.selectSingleRadar(newDate, potNo);
        List<Double> lists =null;
        if(ToolUtil.isNotEmpty(list)){
        WarehouseForRadar obj = (WarehouseForRadar) list.get(0);
        lists = new ArrayList<>();
        lists.add(obj.getDjwd());
        lists.add(obj.getYhlcnt());
        lists.add(obj.getFzb());
        lists.add(obj.getDypl());
        lists.add(obj.getDjzsp());
        lists.add(obj.getFhlcnt());
        lists.add(obj.getZf());
        }
        return lists;
    }

    /**
     * 新增产铝量盘存台账
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Warehouse warehouse) {
        warehouseService.insert(warehouse);
        return SUCCESS_TIP;
    }

    /**
     * 删除产铝量盘存台账
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer warehouseId) {
        warehouseService.deleteById(warehouseId);
        return SUCCESS_TIP;
    }

    /**
     * 修改产铝量盘存台账
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@RequestParam int id, @RequestParam String potno, @RequestParam String newDate, @RequestParam String newValue) throws InvocationTargetException, IllegalAccessException {

//       得到对像根据ID
        Warehouse warehouseOne = warehouseService.selectById(id);

//        查询区间内停槽的天数
        String hour=baseInfoService.selectPotnoDays(DateTimeKit.formatDate(warehouseOne.getTwodate()),newDate,potno);


//        复制属性
        WarehouseH warehouseH = new WarehouseH();
        BeanUtils.copyProperties(warehouseH, warehouseOne);
//        插入历史
        warehouseServiceH.insert(warehouseH);
        //更新
        Warehouse warehouse = new Warehouse();
        warehouse.setId(id);
        warehouse.setOnedate(warehouseOne.getTwodate());
        warehouse.setOnevalue(warehouseOne.getTwovalue());
        warehouse.setTwodate(DateTimeKit.parse(newDate));
        warehouse.setTwovalue(Float.parseFloat(newValue.trim()));
        warehouse.setYield(constantFactory.getEccLvSum(DateTimeKit.formatDate(warehouseOne.getTwodate()), newDate, warehouseOne.getPotno()));
        warehouse.setEfficiency(constantFactory.getEccLvEffi(hour,DateTimeKit.formatDate(warehouseOne.getTwodate()), newDate, warehouseOne.getPotno(),warehouseOne.getTwovalue().toString(),newValue.trim()));
        warehouseService.updateById(warehouse);
        return SUCCESS_TIP;
    }

    /**
     * 产铝量盘存台账详情
     */
    @RequestMapping(value = "/detail/{warehouseId}")
    @ResponseBody
    public Object detail(@PathVariable("warehouseId") Integer warehouseId) {
        return warehouseService.selectById(warehouseId);
    }

    /**
     * 参数分析 散点图
     */
    @RequestMapping(value = "/selectCategoryDJWD")
    @ResponseBody
    public Object selectCategoryDJWD(@RequestParam("newDate") String newDate, @RequestParam("workCode") String workCode){
        Map<String,Object> map=new HashMap<>();
        List<String> listDJWD = warehouseService.scatterDJWD(newDate, workCode);
        List<String> listFZB = warehouseService.scatterFZB(newDate, workCode);
        List<String> listSETV = warehouseService.scatterSETV(newDate, workCode);

        map.put("djwd",listDJWD);
        map.put("fzb",listFZB);
        map.put("setv",listSETV);
        return map;
    }
}
