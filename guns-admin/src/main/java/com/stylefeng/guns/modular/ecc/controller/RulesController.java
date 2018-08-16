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
import com.stylefeng.guns.modular.system.model.Rules;
import com.stylefeng.guns.modular.ecc.service.IRulesService;

/**
 * 槽控规则计算控制器
 *
 * @author fengshuonan
 * @Date 2018-03-20 14:15:50
 */
@Controller
@RequestMapping("/rules")
public class RulesController extends BaseController {

    private String PREFIX = "/ecc/rules/";

    @Autowired
    private IRulesService rulesService;

    /**
     * 跳转到槽控规则计算首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "rules.html";
    }

    /**
     * 跳转到添加槽控规则计算
     */
    @RequestMapping("/rules_add")
    public String rulesAdd() {
        return PREFIX + "rules_add.html";
    }

    /**
     * 跳转到修改槽控规则计算
     */
    @RequestMapping("/rules_update/{rulesId}")
    public String rulesUpdate(@PathVariable Integer rulesId, Model model) {
        Rules rules = rulesService.selectById(rulesId);
        model.addAttribute("item",rules);
        LogObjectHolder.me().set(rules);
        return PREFIX + "rules_edit.html";
    }

    /**
     * 获取槽控规则计算列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return rulesService.selectList(null);
    }

    /**
     * 新增槽控规则计算
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Rules rules) {
        rulesService.insert(rules);
        return SUCCESS_TIP;
    }

    /**
     * 删除槽控规则计算
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer rulesId) {
        rulesService.deleteById(rulesId);
        return SUCCESS_TIP;
    }

    /**
     * 修改槽控规则计算
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Rules rules) {
        rulesService.updateById(rules);
        return SUCCESS_TIP;
    }

    /**
     * 槽控规则计算详情
     */
    @RequestMapping(value = "/detail/{rulesId}")
    @ResponseBody
    public Object detail(@PathVariable("rulesId") Integer rulesId) {
        return rulesService.selectById(rulesId);
    }
}
