package com.stylefeng.guns.modular.ecc.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.RulesHelp;
import com.stylefeng.guns.modular.ecc.service.IRulesHelpService;

/**
 * 槽控专家判断库控制器
 *
 * @author fengshuonan
 * @Date 2018-03-20 14:16:42
 */
@Controller
@RequestMapping("/rulesHelp")
public class RulesHelpController extends BaseController {

    private String PREFIX = "/ecc/rulesHelp/";

    @Autowired
    private IRulesHelpService rulesHelpService;

    /**
     * 跳转到槽控专家判断库首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "rulesHelp.html";
    }

    /**
     * 跳转到添加槽控专家判断库
     */
    @RequestMapping("/rulesHelp_add")
    public String rulesHelpAdd() {
        return PREFIX + "rulesHelp_add.html";
    }

    /**
     * 跳转到修改槽控专家判断库
     */
    @RequestMapping("/rulesHelp_update/{rulesHelpId}")
    public String rulesHelpUpdate(@PathVariable Integer rulesHelpId, Model model) {
        RulesHelp rulesHelp = rulesHelpService.selectById(rulesHelpId);
        model.addAttribute("item",rulesHelp);
        LogObjectHolder.me().set(rulesHelp);
        return PREFIX + "rulesHelp_edit.html";
    }

    /**
     * 获取槽控专家判断库列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return rulesHelpService.selectList(null);
    }

    /**
     * 新增槽控专家判断库
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(RulesHelp rulesHelp) {
        rulesHelpService.insert(rulesHelp);
        return SUCCESS_TIP;
    }

    /**
     * 删除槽控专家判断库
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer rulesHelpId) {
        rulesHelpService.deleteById(rulesHelpId);
        return SUCCESS_TIP;
    }

    /**
     * 修改槽控专家判断库
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(RulesHelp rulesHelp) {
        rulesHelpService.updateById(rulesHelp);
        return SUCCESS_TIP;
    }

    /**
     * 槽控专家判断库详情
     */
    @RequestMapping(value = "/detail/{rulesHelpId}")
    @ResponseBody
    public Object detail(@PathVariable("rulesHelpId") Integer rulesHelpId) {
        return rulesHelpService.selectById(rulesHelpId);
    }
}
