/**
 * 初始化槽控专家判断库详情对话框
 */
var RulesHelpInfoDlg = {
    rulesHelpInfoData : {}
};

/**
 * 清除数据
 */
RulesHelpInfoDlg.clearData = function() {
    this.rulesHelpInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RulesHelpInfoDlg.set = function(key, val) {
    this.rulesHelpInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RulesHelpInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
RulesHelpInfoDlg.close = function() {
    parent.layer.close(window.parent.RulesHelp.layerIndex);
}

/**
 * 收集数据
 */
RulesHelpInfoDlg.collectData = function() {
    this
    .set('id')
    .set('alias')
    .set('mark')
    .set('createtime')
    .set('updatetime')
    .set('obj1')
    .set('obj2')
    .set('obj3');
}

/**
 * 提交添加
 */
RulesHelpInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/rulesHelp/add", function(data){
        Feng.success("添加成功!");
        window.parent.RulesHelp.table.refresh();
        RulesHelpInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.rulesHelpInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
RulesHelpInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/rulesHelp/update", function(data){
        Feng.success("修改成功!");
        window.parent.RulesHelp.table.refresh();
        RulesHelpInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.rulesHelpInfoData);
    ajax.start();
}

$(function() {

});
