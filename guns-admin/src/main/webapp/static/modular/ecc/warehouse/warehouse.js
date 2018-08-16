/**
 * 产铝量盘存台账管理初始化
 */
var Warehouse = {
    id: "WarehouseTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Warehouse.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
             {title: '工区', field: 'workarea', visible: true, align: 'center', valign: 'middle'},
            {title: '槽号', field: 'potno', visible: true, align: 'center', valign: 'middle'},
//            {title: '工区代码', field: 'workcode', visible: true, align: 'center', valign: 'middle'},
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
Warehouse.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Warehouse.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加产铝量盘存台账
 */
Warehouse.openAddWarehouse = function () {
    var index = layer.open({
        type: 2,
        title: '添加产铝量盘存台账',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/warehouse/warehouse_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看产铝量盘存台账详情
 */
Warehouse.openWarehouseDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '产铝量盘存台账详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/warehouse/warehouse_update/' + Warehouse.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除产铝量盘存台账
 */
Warehouse.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/warehouse/delete", function (data) {
            Feng.success("删除成功!");
            Warehouse.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("warehouseId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询产铝量盘存台账列表
 */
Warehouse.search = function () {
   var queryData = {};
     queryData['workCode'] = $("#workCode").val();
     queryData['potNo'] = $("#potNo").val();
    Warehouse.table.refresh({query: queryData});
};

$(function () {
//初始化表格
 initTable();

$("#workCode").change(function() {Warehouse.search();});
$("#potNo").change(function() {Warehouse.search();});

});
//初始化表格
function initTable(){
 var queryData = {};
   queryData['workCode'] = $("#workCode").val();
   queryData['potNo'] = $("#potNo").val();
    var defaultColunms = Warehouse.initColumn();
    var table = new BSTable(Warehouse.id, "/warehouse/list", defaultColunms);
    table.setPaginationType("client");
    table.setQueryParams(queryData);
    Warehouse.table = table.init();
}