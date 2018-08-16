/**
 * 初始化槽控结果展示详情对话框
 */
var ResultInfoInfoDlg = {
    resultInfoInfoData : {}
};

/**
 * 清除数据
 */
ResultInfoInfoDlg.clearData = function() {
    this.resultInfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ResultInfoInfoDlg.set = function(key, val) {
    this.resultInfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ResultInfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ResultInfoInfoDlg.close = function() {
    parent.layer.close(window.parent.ResultInfo.layerIndex);
}

/**
 * 收集数据
 */
ResultInfoInfoDlg.collectData = function() {
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
    .set('result')
    .set('createtime')
    .set('updatetime')
    .set('reason')
    .set('step')
    .set('obj3');
}

/**
 * 提交添加
 */
ResultInfoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/resultInfo/add", function(data){
        Feng.success("添加成功!");
        window.parent.ResultInfo.table.refresh();
        ResultInfoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.resultInfoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ResultInfoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/resultInfo/update", function(data){
        Feng.success("修改成功!");
        window.parent.ResultInfo.table.refresh();
        ResultInfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.resultInfoInfoData);
    ajax.start();
}

$(function() {

});
