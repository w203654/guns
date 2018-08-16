/**
 * 初始化槽控规则计算详情对话框
 */
var RulesInfoDlg = {
    rulesInfoData : {}
};

/**
 * 清除数据
 */
RulesInfoDlg.clearData = function() {
    this.rulesInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RulesInfoDlg.set = function(key, val) {
    this.rulesInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RulesInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
RulesInfoDlg.close = function() {
    parent.layer.close(window.parent.Rules.layerIndex);
}

/**
 * 收集数据
 */
RulesInfoDlg.collectData = function() {
    this
    .set('id')
    .set('djwd')
    .set('fhlcnt')
    .set('fzb')
    .set('setv')
    .set('djzsp')
    .set('yhlcnt')
    .set('zf')
    .set('workv')
    .set('result')
    .set('alias')
    .set('createtime')
    .set('updatetime')
    .set('reason')
    .set('step')
    .set('obj3');
}

/**
 * 提交添加
 */
RulesInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/rules/add", function(data){
        Feng.success("添加成功!");
        window.parent.Rules.table.refresh();
        RulesInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.rulesInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
RulesInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/rules/update", function(data){
        Feng.success("修改成功!");
        window.parent.Rules.table.refresh();
        RulesInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.rulesInfoData);
    ajax.start();
}

$(function() {

});
