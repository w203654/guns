package com.stylefeng.guns.modular.ecc.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.DatasourceEnum;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.mutidatasource.annotion.DataSource;
import com.stylefeng.guns.core.support.DateTime;
import com.stylefeng.guns.core.support.DateTimeKit;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.ecc.service.IBaseInfoService;
import com.stylefeng.guns.modular.ecc.service.IResultInfoService;
import com.stylefeng.guns.modular.system.model.BaseInfo;
import com.stylefeng.guns.modular.system.model.ResultInfo;
import com.stylefeng.guns.modular.system.service.IWorkAreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 槽控机基础信息控制器
 *
 * @author fengshuonan
 * @Date 2018-03-20 00:42:11
 */
@Api(value = "/baseInfo", description = "槽控机基础信息手工请求数据")
@Controller
@RequestMapping("/baseInfo")
public class BaseInfoController extends BaseController {

    private String PREFIX = "/ecc/baseInfo/";

    @Autowired
    private IBaseInfoService baseInfoService;
    @Autowired
    private IResultInfoService resultInfoService;
    @Autowired
    private IWorkAreaService workAreaService;

/*    @InitBinder
    public void InitBinder(WebDataBinder binder) {
        // 不要删除下行注释!!! 将来"yyyy-MM-dd"将配置到properties文件中
        // SimpleDateFormat dateFormat = new
        // SimpleDateFormat(getText("date.format", request.getLocale()));
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }*/

    /**
     * 跳转到槽控机基础信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "baseInfo.html";
    }

    /**
     * 跳转到添加槽控机基础信息
     */
    @RequestMapping("/baseInfo_add")
    public String baseInfoAdd() {
        return PREFIX + "baseInfo_add.html";
    }

    /**
     * 跳转到修改槽控机基础信息
     */
    @RequestMapping("/baseInfo_update/{baseInfoId}")
    public String baseInfoUpdate(@PathVariable Integer baseInfoId, Model model) {
        BaseInfo baseInfo = baseInfoService.selectById(baseInfoId);
        model.addAttribute("item", baseInfo);
        LogObjectHolder.me().set(baseInfo);
        return PREFIX + "baseInfo_edit.html";
    }
    /**
     * 获取前三天槽控机数据
     */
    @RequestMapping(value = "/listThree")
    @ResponseBody
    public Object listThree(@RequestParam(required = false) String potNo){
    List<Map<String, Object>> list=baseInfoService.selectAIF3top3(potNo);
        return list;
    }
    /**
     * 获取槽控机基础信息列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String workCode, @RequestParam(required = false) String potNo, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) String result) {
        //查询区间槽号
        List<String> listPotno = workAreaService.selectPontos(workCode);

        Wrapper<BaseInfo> wrapper = new EntityWrapper<>();


        if (ToolUtil.isNotEmpty(potNo))
            wrapper.like("potno", potNo);

        if (ToolUtil.isNotEmpty(workCode))
            wrapper.in("potno", listPotno);

        if (ToolUtil.isNotEmpty(beginTime) && ToolUtil.isNotEmpty(endTime))
            wrapper.between("runtime",beginTime,endTime);

        wrapper.orderBy("runtime", false);
        wrapper.orderBy("potno", true);
        List<BaseInfo> list=baseInfoService.selectList(wrapper);
        return list;
    }

    /**
     * 读取雷达图右边信息信息
     * @param newDate
     * @param potNo
     * @return
     */
    @RequestMapping(value = "/singleList")
    @ResponseBody
    public Object singleList(@RequestParam(required = false) String newDate, @RequestParam(required = false) String potNo) {
        List<Map<String, Object>> list=baseInfoService.selectSingleTextData(newDate, potNo);
        return list;
    }
    /**
     * 单槽氟化铝料量添加设置
     * @param potNo
     * @return
     */
    @RequestMapping(value = "/selectSingleAIF3TextData")
    @ResponseBody
    public Object selectSingleAIF3TextData(@RequestParam(required = false) String startTime,@RequestParam(required = false) String endTime, @RequestParam(required = false) String potNo) {
        List<Map<String, Object>> list=baseInfoService.selectSingleAIF3TextData(startTime, endTime,potNo);
        return list;
    }

    /**
     * 新增槽控机基础信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BaseInfo baseInfo) {
        baseInfoService.insert(baseInfo);
        return SUCCESS_TIP;
    }

    /**
     * 删除槽控机基础信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer baseInfoId) {
        baseInfoService.deleteById(baseInfoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改槽控机基础信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BaseInfo baseInfo) {
        baseInfoService.updateById(baseInfo);
        return SUCCESS_TIP;
    }

    /**
     * 槽控机基础信息详情
     */
    @RequestMapping(value = "/detail/{baseInfoId}")
    @ResponseBody
    public Object detail(@PathVariable("baseInfoId") Integer baseInfoId) {
        return baseInfoService.selectById(baseInfoId);
    }

    /**
     * 槽控机查询区间从 槽控机
     */
    List<Map<String, Object>> listSub = new ArrayList<>();

    @ApiOperation(value = "第一步：槽控机区间查询", httpMethod = "GET", response = Object.class, notes = "返回JSON格式")
    @RequestMapping(value = "/AgetEccData")
    @ResponseBody
    @DataSource(name = DatasourceEnum.DATA_SOURCE_BIZ)
    public Object AgetEccData(@ApiParam(name = "startDateTime", value = "开始时间:2018-01-01", defaultValue = "2018-01-01", required = false) @RequestParam("startDateTime") String startDateTime,
                             @ApiParam(name = "endDateTime", value = "结束时间:2018-01-10", defaultValue = "2018-01-10", required = false) @RequestParam("endDateTime") String endDateTime) {
        listSub.clear();
        List<Map<String, Object>> list = new ArrayList<>();
        DateTime Date_tmp = DateTimeKit.parse(startDateTime);
        while (DateUtil.compareDate(endDateTime, DateTimeKit.formatDate(Date_tmp))) {
            Date_tmp = DateTimeKit.offsiteDay(Date_tmp, 1);
            System.out.println(DateTimeKit.formatDate(Date_tmp));
            list = baseInfoService.selectEccData(DateTimeKit.formatDate(Date_tmp));
            listSub.addAll(list);
        }

        return listSub;
    }

    /**
     * 导入到系统中
     */
    @ApiOperation(value = "第二步：导入到系统中", httpMethod = "GET", response = Object.class, notes = "返回JSON格式")
    @RequestMapping(value = "/BeccDataToSystem")
    @ResponseBody
    public void BeccDataToSystem() {
        BaseInfo baseInfo = null;
        BaseInfo baseInfoOne = null;
        System.out.println("list:" + listSub);
        for (Map<String, Object> map : listSub) {

            Wrapper<BaseInfo> warp = warp = new EntityWrapper<>();
            warp.eq("Runtime", map.get("Ddate").toString());
            warp.eq("Potno", map.get("PotNo").toString());
            //根据条件查询是否存在实体类 存在更新，不存在插入
            baseInfoOne = baseInfoService.selectOne(warp);

            baseInfo = new BaseInfo();
            baseInfo.setRuntime(map.get("Ddate").toString());
            baseInfo.setPotno(map.get("PotNo").toString());
            baseInfo.setFhlcnt(map.get("FhlCnt").toString());
            baseInfo.setYhlcnt(map.get("YhlCnt").toString());
            baseInfo.setSetv(map.get("SetV").toString());
            baseInfo.setWorkv(map.get("WorkV").toString());
            baseInfo.setAveragev(map.get("AverageV").toString());
            baseInfo.setZf(map.get("ZF").toString());
            baseInfo.setDjzsp(map.get("Djzsp").toString());
            baseInfo.setDjwd(map.get("Djwd").toString());
            baseInfo.setFzb(map.get("Fzb").toString());
            baseInfo.setLsp(map.get("Lsp").toString());
            baseInfo.setPotage(map.get("PotAge").toString());
            baseInfo.setStopage(map.get("StopAge").toString());
            baseInfo.setAge(map.get("Age").toString());
            baseInfo.setObj1(map.get("AIF3set").toString());
            baseInfo.setCreatetime(new Date());

            if (ToolUtil.isEmpty(baseInfoOne)) {
                baseInfoService.insert(baseInfo);
//                baseInfoService.procPotnoStatus(map.get("Ddate").toString(),map.get("PotNo").toString());
            } else {
                if(! (baseInfoOne.getFhlcnt().equals(map.get("FhlCnt").toString()) && baseInfoOne.getYhlcnt().equals(map.get("YhlCnt").toString()) && baseInfoOne.getFzb().equals(map.get("Fzb").toString()))){
                Wrapper<ResultInfo> warpResult = new EntityWrapper<>();
                warpResult.eq("Runtime", map.get("Ddate").toString());
                warpResult.eq("Potno", map.get("PotNo").toString());
                resultInfoService.delete(warpResult);//删除结果
                baseInfoService.deleteById(baseInfoOne.getId());//删除基础表信息
                baseInfoService.insert(baseInfo);//新增基础记录
//                baseInfoService.procPotnoStatus(map.get("Ddate").toString(),map.get("PotNo").toString());
                }
            }

        }
        listSub.clear();
    }
    /**
     * 执行单槽的存储过程
     */
    @ApiOperation(value = "第三步：执行单槽的存储过程", httpMethod = "GET", response = Object.class, notes = "返回JSON格式")
    @RequestMapping(value = "/CinsertProcPotnoStatus")
    @ResponseBody
    public void CinsertProcPotnoStatus(@ApiParam(name = "startDateTime", value = "开始时间:2018-01-01", defaultValue = "2018-01-01", required = false) @RequestParam("startDateTime") String startDateTime) {
        BaseInfo baseInfo = new BaseInfo();
        Wrapper<BaseInfo> wrapper=new EntityWrapper<>();
        wrapper.eq("runtime",startDateTime);
        List<BaseInfo> list = baseInfoService.selectList(wrapper);
        for (BaseInfo info : list){
            baseInfoService.procPotnoStatus(startDateTime,info.getPotno());
        }
    }
}
