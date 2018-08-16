/**
 * 初始化槽控机基础信息详情对话框
 */
var BaseInfoInfoDlg = {
    baseInfoInfoData : {}
};

/**
 * 清除数据
 */
BaseInfoInfoDlg.clearData = function() {
    this.baseInfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BaseInfoInfoDlg.set = function(key, val) {
    this.baseInfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BaseInfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BaseInfoInfoDlg.close = function() {
    parent.layer.close(window.parent.BaseInfo.layerIndex);
}

/**
 * 收集数据
 */
BaseInfoInfoDlg.collectData = function() {
    this
    .set('id')
    .set('runtime')
    .set('potno')
    .set('yhlcnt')
    .set('setv')
    .set('workv')
    .set('fhlcnt')
    .set('zf')
    .set('djzsp')
    .set('djwd')
    .set('fzb')
    .set('createtime')
    .set('obj1')
    .set('obj2')
    .set('obj3');
}

/**
 * 提交添加
 */
BaseInfoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/baseInfo/add", function(data){
        Feng.success("添加成功!");
        window.parent.BaseInfo.table.refresh();
        BaseInfoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.baseInfoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BaseInfoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/baseInfo/update", function(data){
        Feng.success("修改成功!");
        window.parent.BaseInfo.table.refresh();
        BaseInfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.baseInfoInfoData);
    ajax.start();
}

$(function() {

});
