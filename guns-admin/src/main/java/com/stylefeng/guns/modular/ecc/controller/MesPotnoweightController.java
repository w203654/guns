package com.stylefeng.guns.modular.ecc.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.DatasourceEnum;
import com.stylefeng.guns.core.mutidatasource.annotion.DataSource;
import com.stylefeng.guns.core.support.DateTime;
import com.stylefeng.guns.core.support.DateTimeKit;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.dao.BaseInfoMapper;
import com.stylefeng.guns.modular.system.model.BaseInfo;
import com.sun.corba.se.impl.oa.toa.TOA;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.MesPotnoweight;
import com.stylefeng.guns.modular.ecc.service.IMesPotnoweightService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * MES天车出铝量控制器
 *
 * @author fengshuonan
 * @Date 2018-05-30 20:05:16
 */
@Api(value = "/mesPotnoweight", description = "天车基础信息手工请求数据")
@Controller
@RequestMapping("/mesPotnoweight")
public class MesPotnoweightController extends BaseController {

    private String PREFIX = "/ecc/mesPotnoweight/";
    List<Map<String, Object>> listPotno = new ArrayList<>();

    @Autowired
    private IMesPotnoweightService mesPotnoweightService;
    @Autowired
    BaseInfoMapper baseInfoMapper;

    /**
     * 跳转到MES天车出铝量首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "mesPotnoweight.html";
    }

    /**
     * 跳转到添加MES天车出铝量
     */
    @RequestMapping("/mesPotnoweight_add")
    public String mesPotnoweightAdd() {
        return PREFIX + "mesPotnoweight_add.html";
    }

    /**
     * 跳转到修改MES天车出铝量
     */
    @RequestMapping("/mesPotnoweight_update/{mesPotnoweightId}")
    public String mesPotnoweightUpdate(@PathVariable Integer mesPotnoweightId, Model model) {
        MesPotnoweight mesPotnoweight = mesPotnoweightService.selectById(mesPotnoweightId);
        model.addAttribute("item",mesPotnoweight);
        LogObjectHolder.me().set(mesPotnoweight);
        return PREFIX + "mesPotnoweight_edit.html";
    }

    /**
     * 获取MES天车出铝量列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return mesPotnoweightService.selectList(null);
    }

    /**
     * 新增MES天车出铝量
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(MesPotnoweight mesPotnoweight) {
        mesPotnoweightService.insert(mesPotnoweight);
        return SUCCESS_TIP;
    }

    /**
     * 删除MES天车出铝量
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer mesPotnoweightId) {
        mesPotnoweightService.deleteById(mesPotnoweightId);
        return SUCCESS_TIP;
    }

    /**
     * 修改MES天车出铝量
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(MesPotnoweight mesPotnoweight) {
        mesPotnoweightService.updateById(mesPotnoweight);
        return SUCCESS_TIP;
    }

    /**
     * MES天车出铝量详情
     */
    @RequestMapping(value = "/detail/{mesPotnoweightId}")
    @ResponseBody
    public Object detail(@PathVariable("mesPotnoweightId") Integer mesPotnoweightId) {
        return mesPotnoweightService.selectById(mesPotnoweightId);
    }


    /**
     * MES天车出铝量查询区间从 时间区间
     */
    List<Map<String, Object>> listSub = new ArrayList<>();
    @ApiOperation(value="第一步：MES天车出铝量区间查询",httpMethod = "GET",  response = Object.class ,notes = "返回JSON格式")
    @RequestMapping(value = "/getMesData")
    @ResponseBody
    @DataSource(name = DatasourceEnum.DATA_SOURCE_MES)
    public Object getEccData(@ApiParam(name="startDateTime",value = "开始时间:2018-01-01",defaultValue = "2018-01-01",required = false) @RequestParam("startDateTime") String startDateTime,
                             @ApiParam (name="endDateTime",value = "结束时间:2018-01-10",defaultValue = "2018-01-10",required = false) @RequestParam("endDateTime") String endDateTime) {
        listPotno.clear();
        List<Map<String, Object>> list = new ArrayList<>();

        DateTime Date_tmp = DateTimeKit.parse(startDateTime);
        while (DateUtil.compareDate(endDateTime, DateTimeKit.formatDate(Date_tmp))) {
            Date_tmp = DateTimeKit.offsiteDay(Date_tmp, 1);
            System.out.println(DateTimeKit.formatDate(Date_tmp));
            list = mesPotnoweightService.selectMesData(DateTimeKit.formatDate(Date_tmp));
            listPotno.addAll(list);
        }
        return listPotno;
    }

    /**
     * 导入到系统中
     */
    @ApiOperation(value="第二步：导入到系统中",httpMethod = "GET",  response = Object.class ,notes = "返回JSON格式")
    @RequestMapping(value = "/mesDataToSystem")
    @ResponseBody
    public void eccDataToSystem() {
        Wrapper<MesPotnoweight> selectOne =null;
        MesPotnoweight iuMes = new MesPotnoweight();

        for (Map<String, Object> map : listPotno) {
            selectOne = new EntityWrapper<>();
            selectOne.eq("runtime",map.get("RUNTIME").toString());
            selectOne.eq("potno",map.get("POTNO").toString());
            iuMes = mesPotnoweightService.selectOne(selectOne);

            /* 预测产铝量
            String forecast=baseInfoMapper.selectPotnoForecast(map.get("RUNTIME").toString(),map.get("POTNO").toString());*/

            //不存在，则插入新数据 否则更新 计划和实际量
            if (ToolUtil.isEmpty(iuMes)) {
                iuMes = new MesPotnoweight();
                iuMes.setRuntime(map.get("RUNTIME").toString());
                iuMes.setPotno(map.get("POTNO").toString());
                iuMes.setPlanweight(map.get("PLANWEIGHT").toString());
                iuMes.setZweight(map.get("ZWEIGHT").toString());
                iuMes.setCreatedatetime(new Date());
//                预测产铝量
                iuMes.setForecast(baseInfoMapper.selectPotnoForecast(map.get("RUNTIME").toString(),map.get("POTNO").toString()));
                mesPotnoweightService.insert(iuMes);
            } else {
//                 if(!(iuMes.getZweight().equals(map.get("ZWEIGHT").toString()) && iuMes.getPlanweight().equals(map.get("PLANWEIGHT").toString()))){
                iuMes.setPlanweight(map.get("PLANWEIGHT").toString());
                iuMes.setZweight(map.get("ZWEIGHT").toString());
//               预测产铝量
                iuMes.setForecast(baseInfoMapper.selectPotnoForecast(map.get("RUNTIME").toString(),map.get("POTNO").toString()));
                iuMes.setCreatedatetime(new Date());
                mesPotnoweightService.updateById(iuMes);
//                 }
            }

        }
    }
}
