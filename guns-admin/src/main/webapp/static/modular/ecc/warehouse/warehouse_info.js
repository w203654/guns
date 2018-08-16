/**
 * 初始化产铝量盘存台账详情对话框
 */
var WarehouseInfoDlg = {
    warehouseInfoData : {}
};

/**
 * 清除数据
 */
WarehouseInfoDlg.clearData = function() {
    this.warehouseInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WarehouseInfoDlg.set = function(key, val) {
    this.warehouseInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WarehouseInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
WarehouseInfoDlg.close = function() {
    parent.layer.close(window.parent.Warehouse.layerIndex);
}

/**
 * 收集数据
 */
WarehouseInfoDlg.collectData = function() {
    this
    .set('id')
    .set('potno')
    .set('newDate')
    .set('newValue');
}

/**
 * 提交添加
 */
WarehouseInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/warehouse/add", function(data){
        Feng.success("添加成功!");
        window.parent.Warehouse.table.refresh();
        WarehouseInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.warehouseInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
WarehouseInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/warehouse/update", function(data){
        Feng.success("修改成功!");
        window.parent.Warehouse.table.refresh();
        WarehouseInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.warehouseInfoData);
    ajax.start();
}

$(function() {
$("#newDate").val(laydate.now(0, 'YYYY-MM-DD'));

});
