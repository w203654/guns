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
            {title: '车间类别', field: 'type', visible: true, align: 'center', valign: 'middle'},
            {title: '正常槽', field: 'zcc', visible: true, align: 'center', valign: 'middle'},
            {title: '冷槽', field: 'lc', visible: true, align: 'center', valign: 'middle'},
            {title: '热槽', field: 'rc', visible: true, align: 'center', valign: 'middle'},
            {title: '无序槽', field: 'wxc', visible: true, align: 'center', valign: 'middle'}
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
      queryData['startTime'] = $("#startTime").val();
      queryData['endTime'] = $("#endTime").val();
    Warehouse.table.refresh({query: queryData});
    //槽控雷达图
    initRadar();

};
$(function () {
//初始化 常用属性
  init();
//单槽管理复盘表格初始化
 initTable();
//槽控饼图
  initRadar();

});

//初始化 常用属性
function init(){
$("#startTime").val(laydate.now(0, 'YYYY-MM-DD'));
$("#endTime").val(laydate.now(0, 'YYYY-MM-DD'));

}

//单槽管理复盘表格初始化
function initTable(){
var queryData = {};
     queryData['startTime'] = $("#startTime").val();
     queryData['endTime'] = $("#endTime").val();
    var defaultColunms = Warehouse.initColumn();
    var table = new BSTable(Warehouse.id, "/resultInfo/selectWorkshopCount", defaultColunms);
    table.height=240;
    table.showRefresh=false;
    table.showColumns=false;
    table.pagination=false;
    table.setPaginationType("client");
    table.setQueryParams(queryData);
    Warehouse.table = table.init();
    }

function initRadar(){
var dom01 = document.getElementById("container01");
var dom02 = document.getElementById("container02");
var dom03 = document.getElementById("container03");
var dom04 = document.getElementById("container04");
var dom05 = document.getElementById("container05");
var myChart01 = echarts.init(dom01);
var myChart02 = echarts.init(dom02);
var myChart03 = echarts.init(dom03);
var myChart04 = echarts.init(dom04);
var myChart05 = echarts.init(dom05);
var app = {};
option = null;
option = {
    title : {
        text: '一车间',
        subtext: '槽状态统计',
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
 /*   legend: {
        orient: 'vertical',
        left: 'left',
        data: ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
    },*/
    series : [
        {
            name: '槽控机',
            type: 'pie',
            radius : '55%',
            center: ['50%', '60%'],
            data:[
                {value:335, name:'正常槽'},
                {value:310, name:'冷槽'},
                {value:234, name:'热槽'},
                {value:135, name:'无序槽'}
            ],
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};
;
if (option && typeof option === "object") {
    myChart01.setOption(option, true);
    myChart02.setOption(option, true);
    myChart03.setOption(option, true);
    myChart04.setOption(option, true);
    myChart05.setOption(option, true);
}

$.get('/resultInfo/selectCategoryCount',{startTime: $("#startTime").val(), endTime: $("#endTime").val(),workCode:1}).done(function (data) {
    // 填入数据
    myChart01.setOption({
        series: [{
            // 根据名字对应到相应的系列
         <!--   name: '访问来源',-->
            data: data.data
        }],
        title:[{text:'一车间'}]
    });
    });

$.get('/resultInfo/selectCategoryCount',{startTime: $("#startTime").val(), endTime: $("#endTime").val(),workCode:2}).done(function (data) {
    // 填入数据
    myChart02.setOption({
        series: [{
            // 根据名字对应到相应的系列
         <!--   name: '访问来源',-->
            data: data.data
        }],title:[{text:'二车间'}]
    });
    });

$.get('/resultInfo/selectCategoryCount',{startTime: $("#startTime").val(), endTime: $("#endTime").val(),workCode:3}).done(function (data) {
    // 填入数据
    myChart03.setOption({
        series: [{
            // 根据名字对应到相应的系列
         <!--   name: '访问来源',-->
            data: data.data
        }],title:[{text:'三车间'}]
    });
    });

$.get('/resultInfo/selectCategoryCount',{startTime: $("#startTime").val(), endTime: $("#endTime").val(),workCode:4}).done(function (data) {
    // 填入数据
    myChart04.setOption({
        series: [{
            // 根据名字对应到相应的系列
         <!--   name: '访问来源',-->
            data: data.data
        }],title:[{text:'四车间'}]
    });
    });

$.get('/resultInfo/selectCategoryCount',{startTime: $("#startTime").val(), endTime: $("#endTime").val(),workCode:""}).done(function (data) {
    // 填入数据
    myChart05.setOption({
        series: [{
            // 根据名字对应到相应的系列
         <!--   name: '访问来源',-->
            data: data.data
        }],title:[{text:'全部车间'}]
    });
    });
}

