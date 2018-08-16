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
            /*{title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},*/
            {title: '槽号', field: 'potno', visible: true, align: 'center', valign: 'middle'},
            {title: '槽类别', field: 'result', visible: true, align: 'center', valign: 'middle'},
            {title: '槽温', field: 'djwd', visible: true, align: 'center', valign: 'middle'},
            {title: '分子比', field: 'fzb', visible: true, align: 'center', valign: 'middle'},
            {title: '氟盐设置', field: 'fhlcnt', visible: true, align: 'center', valign: 'middle'},
            {title: '取样时间', field: 'runtime', visible: true, align: 'center', valign: 'middle'}

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
     queryData['newDate'] = $("#newDate").val();
     queryData['potNo'] = $("#potNo").val();
    Warehouse.table.refresh({query: queryData});

    //初始化 雷达图左侧文本框
    initSingleText();
};
$(function () {
//初始化 常用属性
  init();
//单槽管理复盘表格初始化
 initTable();

  //初始化 雷达图左侧文本框
  initSingleText();
});

//初始化 常用属性
function init(){
$("#startTime").val(laydate.now(0, 'YYYY-MM-DD'));
$("#endTime").val(laydate.now(1, 'YYYY-MM-DD'));
initSelect();
$("#workCode").change(function() {initSelect();Warehouse.search();});
$("#potNo").change(function() {Warehouse.search();});
}

//单槽管理复盘表格初始化
function initTable(){
var queryData = {};
     queryData['potNo'] = $("#potNo").val();
    var defaultColunms = Warehouse.initColumn();
    var table = new BSTable(Warehouse.id, "/baseInfo/listThree", defaultColunms);
    table.height=210;
    table.showRefresh=false;
    table.showColumns=false;
    table.pagination=false;
    table.setPaginationType("client");
    table.setQueryParams(queryData);
    Warehouse.table = table.init();
    }

//初始化 选择框
function initSelect(){
var ajax = new $ax(Feng.ctxPath + "/workArea/findPotNoByWorkCode", function (data) {
       Feng.success("获取成功!");
       $("#potNo").empty();
       var option ='';
           $.each(data,function(i,obj){
            option += "<option values="+obj.potno+">"+obj.potno+"</option>";
           });
       $("#potNo").html(option);
   }, function (data) {
       Feng.error("获取失败!"+ data.responseJSON.message + "!");
   });
   ajax.set("workCode",$("#workCode").val());
   ajax.start();
}

//初始化 雷达图左侧文本框
function initSingleText(){
var ajax = new $ax(Feng.ctxPath + "/baseInfo/selectSingleAIF3TextData", function (data) {
       Feng.success("获取成功!");
        console.log(data);
       $("#c_value").html(data[0].c_value+"KG");
       $("#f_value").html(data[0].f_value==null?'0':data[0].f_value+"KG");
       $("#f_mark").html(data[0].f_mark==null?'无':data[0].f_mark);
       $("#c_fzb").val(data[0].c_fzb);
       $("#c_djwd").val(data[0].c_djwd);
       $("#q_fzb").val(data[0].q_fzb);
       $("#q_djwd").val(data[0].q_djwd);
       $("#fzb_qs").val(data[0].fzb_qs);
       $("#djwd_qs").val(data[0].djwd_qs);
       $("#qjxh").val(data[0].qjxh);
   }, function (data) {
       Feng.error("获取失败!"+ data.responseJSON.message + "!");
   });
   ajax.set("startTime",$("#startTime").val());
   ajax.set("endTime",$("#endTime").val());
   ajax.set("potNo",$("#potNo").val());
   ajax.start();
}

