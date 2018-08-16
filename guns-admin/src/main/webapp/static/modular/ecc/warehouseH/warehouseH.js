/**
 * 产铝盘存台账历史管理初始化
 */
var WarehouseH = {
    id: "WarehouseHTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
WarehouseH.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '槽号', field: 'potno', visible: true, align: 'center', valign: 'middle'},
            {title: '工区', field: 'workarea', visible: true, align: 'center', valign: 'middle'},
            {title: '一次日期', field: 'onedate', visible: true, align: 'center', valign: 'middle'},
            {title: '一次盘存量', field: 'onevalue', visible: true, align: 'center', valign: 'middle'},
            {title: '二次日期', field: 'twodate', visible: true, align: 'center', valign: 'middle'},
            {title: '二次盘存量', field: 'twovalue', visible: true, align: 'center', valign: 'middle'},
            {title: '产量', field: 'yield', visible: true, align: 'center', valign: 'middle'},
            {title: '阶段效率', field: 'efficiency', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
WarehouseH.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        WarehouseH.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加产铝盘存台账历史
 */
WarehouseH.openAddWarehouseH = function () {
    var index = layer.open({
        type: 2,
        title: '添加产铝盘存台账历史',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/warehouseH/warehouseH_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看产铝盘存台账历史详情
 */
WarehouseH.openWarehouseHDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '产铝盘存台账历史详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/warehouseH/warehouseH_update/' + WarehouseH.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除产铝盘存台账历史
 */
WarehouseH.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/warehouseH/delete", function (data) {
            Feng.success("删除成功!");
            WarehouseH.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("warehouseHId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询产铝盘存台账历史列表
 */
WarehouseH.search = function () {
   var queryData = {};
     queryData['workCode'] = $("#workCode").val();
     queryData['potNo'] = $("#potNo").val();
    WarehouseH.table.refresh({query: queryData});
};

$(function () {
$("#workCode").change(function() {WarehouseH.search();});

    var queryData = {};
   queryData['workCode'] = $("#workCode").val();
   queryData['potNo'] = $("#potNo").val();
    var defaultColunms = WarehouseH.initColumn();
    var table = new BSTable(WarehouseH.id, "/warehouseH/list", defaultColunms);
    table.setPaginationType("client");
    WarehouseH.table = table.init();
});
