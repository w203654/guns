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
            {title: '类别', field: 'types', visible: true, align: 'center', valign: 'middle'},
            {title: '槽温', field: 'djwd', visible: true, align: 'center', valign: 'middle'},
            {title: 'AIF3料量', field: 'fhlcnt', visible: true, align: 'center', valign: 'middle'},
            {title: '分子比', field: 'fzb', visible: true, align: 'center', valign: 'middle'},
            {title: '电压偏离', field: 'dypl', visible: true, align: 'center', valign: 'middle'},
            {title: '质水平', field: 'djzsp', visible: true, align: 'center', valign: 'middle'},
            {title: 'Al2O3料量', field: 'yhlcnt', visible: true, align: 'center', valign: 'middle'},
            {title: '噪声值', field: 'zf', visible: true, align: 'center', valign: 'middle'}

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
    //槽控雷达图
    initRadar();
    //初始化 雷达图左侧文本框
    initSingleText();
};
$(function () {
//初始化 常用属性
  init();
//单槽管理复盘表格初始化
 initTable();
//槽控雷达图
  initRadar();
  //初始化 雷达图左侧文本框
  initSingleText();
});

//初始化 常用属性
function init(){
$("#newDate").val(laydate.now(0, 'YYYY-MM-DD'));
initSelect();
$("#workCode").change(function() {initSelect();Warehouse.search();});
$("#potNo").change(function() {Warehouse.search();});
}

//单槽管理复盘表格初始化
function initTable(){
var queryData = {};
     queryData['newDate'] = $("#newDate").val();
     queryData['potNo'] = $("#potNo").val();
    var defaultColunms = Warehouse.initColumn();
    var table = new BSTable(Warehouse.id, "/warehouse/singleList", defaultColunms);
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
var ajax = new $ax(Feng.ctxPath + "/baseInfo/singleList", function (data) {
       Feng.success("获取成功!");
           $.each(data,function(i,obj){
//           console.log(obj);
           switch(obj.type)
           {
           case 'drcl':
             $("#drcl").val(obj.val);
             break;
           case 'drxl':
              $("#drxl").val(obj.val);
             break;
           case 'ljxl':
             $("#ljxl").val(obj.val);
             break;
           case 'ljdh':
             $("#ljdh").val(obj.val);
             break;
           case 'pjdy':
             $("#pjdy").val(obj.val);
             break;
           case 'zcll':
            $("#zcll").val(obj.val);
            break;
           default:

           }

           });
   }, function (data) {
       Feng.error("获取失败!"+ data.responseJSON.message + "!");
   });
   ajax.set("newDate",$("#newDate").val());
   ajax.set("potNo",$("#potNo").val());
   ajax.start();
}

//槽控雷达图
function initRadar(){
var dom = document.getElementById("container");
var myChart = echarts.init(dom);
var app = {};
option = null;
option = {
    title: {
        text: '槽控雷达图'
    },

    tooltip: {},
    legend: {
        data: ['实际','最低', '最高']
    },
    radar: {
        // shape: 'circle',
        name: {
            textStyle: {
                color: '#fff',
                backgroundColor: '#999',
                borderRadius: 3,
                padding: [3, 5]
           }
        },
        indicator: [
           { name: '槽温', max: 3 , min: -1},
           { name: 'Al2o3料量', max: 3, min: -1},
           { name: '分子比', max: 3, min: -1},
           { name: '电压偏离',max: 3, min: -1},
           { name: '质水平', max: 3, min: -1},
           { name: 'AlF3料量', max: 3, min: -1 },
           { name: '噪声值', max: 3, min: -1}
        ]
    },
    series: [{
        name: '最低 vs 最高',
        type: 'radar',
        // areaStyle: {normal: {}},
        data : [
            {
                 value : [1.88,0.15,1.33,0.88,0.67,0.87,0.6],
                 name : '实际'
             } ,
            {
                value : [0,0,0,0,0,0,0],
                name : '最低'
            },
             {
                value : [1,1,1,1,1,1,1],
                name : '最高'
            }
        ]
    }]
};
if (option && typeof option === "object") {
    myChart.setOption(option, true);
}

 $.get('/warehouse/singleListForRadar',{newDate: $("#newDate").val(), potNo: $("#potNo").val()}).done(function (data) {
// console.log(data);
    // 填入数据
    myChart.setOption({
        series: [{
            // 根据名字对应到相应的系列
//          name: '最低 vs 最高',
            data:[{value : data,name : '实际'},{value : [0,0,0,0,0,0,0],name : '最低'}, {value : [1,1,1,1,1,1,1],name : '最高'}]
        }]
         });
     });
}