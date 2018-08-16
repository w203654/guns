/**
 * 产铝量盘存台账管理初始化
 */
var Warehouse = {
    id: "WarehouseTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

var WarehouseA = {
    id: "WarehouseTableA",	//表格id
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
            {title: '月份', field: 'runtime', visible: true, align: 'center', valign: 'middle'},
            {title: '槽号', field: 'potno', visible: true, align: 'center', valign: 'middle'},
            {title: '效率', field: 'val', visible: true, align: 'center', valign: 'middle',formatter: function (value, row, index) {return value+'%';}}

    ];
};
/**
 * 初始化表格的列A
 */
WarehouseA.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            /*{title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},*/
        {title: '类别', field: 'types', visible: true, align: 'center', valign: 'middle'},
        {title: '槽温', field: 'djwd', visible: true, align: 'center', valign: 'middle'},
        {title: 'AIF3料量', field: 'fhlcnt', visible: true, align: 'center', valign: 'middle'},
        {title: '分子比', field: 'fzb', visible: true, align: 'center', valign: 'middle'},
//        {title: '电压偏离', field: 'dypl', visible: true, align: 'center', valign: 'middle'},
        {title: '质水平', field: 'djzsp', visible: true, align: 'center', valign: 'middle'},
        {title: 'Al2O3料量', field: 'yhlcnt', visible: true, align: 'center', valign: 'middle'},
        {title: '平均电压', field: 'averagev', visible: true, align: 'center', valign: 'middle'},
        {title: '噪声值', field: 'zf', visible: true, align: 'center', valign: 'middle'}
]};
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
     queryData['workCode'] = $("#workCode").val();
     queryData['startInt'] = $("#startInt").val();
     queryData['endInt'] = $("#endInt").val();
    Warehouse.table.refresh({query: queryData});

     var queryDataA = {};
         queryDataA['newDate'] = $("#newDate").val();
         queryDataA['workCode'] = $("#workCode").val();
         queryDataA['startInt'] = $("#startInt").val();
         queryDataA['endInt'] = $("#endInt").val();
    WarehouseA.table.refresh({query: queryDataA});

//     initTable();
    initTableA();
    initScatter();

};
$(function () {
//初始化 常用属性
  init();
//单槽管理复盘表格初始化
 initTable();
 //单槽管理复盘表格初始化A
  initTableA();
   //初始化散点图
  initScatter();

});

//初始化 常用属性
function init(){
$("#newDate").val(laydate.now(0, 'YYYY-MM-DD'));


}

//单槽管理复盘表格初始化
function initTable(){
var queryData = {};
     queryData['newDate'] = $("#newDate").val();
     queryData['workCode'] = $("#workCode").val();
     queryData['startInt'] = $("#startInt").val();
     queryData['endInt'] = $("#endInt").val();
    var defaultColunms = Warehouse.initColumn();
    var table = new BSTable(Warehouse.id, "/warehouse/parameterUnalysisPotnoList", defaultColunms);
//    table.height=216;
    table.showRefresh=false;
    table.showColumns=false;
//    table.pagination=false;
    table.setPaginationType("client");
    table.setQueryParams(queryData);
    Warehouse.table = table.init();
    }

    //单槽管理复盘表格初始化A
    function initTableA(){
    var queryData = {};
         queryData['newDate'] = $("#newDate").val();
         queryData['workCode'] = $("#workCode").val();
         queryData['startInt'] = $("#startInt").val();
         queryData['endInt'] = $("#endInt").val();
        var defaultColunms = WarehouseA.initColumn();
        var tableA = new BSTable(WarehouseA.id, "/warehouse/parameterUnalysisPotnoAvage", defaultColunms);
        tableA.height=150;
        tableA.showRefresh=false;
        tableA.showColumns=false;
        tableA.pagination=false;
        tableA.setPaginationType("client");
        tableA.setQueryParams(queryData);
        WarehouseA.table = tableA.init();
        }
        //初始化散点图
   function initScatter(){
   var dom = document.getElementById("container");
   var myChart = echarts.init(dom);
   var app = {};
   option = null;
   option = {
       title : {
           text: '槽温、分子、电压比分布',
           subtext: '抽样调查来自: 槽控机系统'
       },
       grid: {
           left: '3%',
           right: '7%',
           bottom: '3%',
           containLabel: true
       },
       tooltip : {
           // trigger: 'axis',
           showDelay : 0,
           formatter : function (params) {
               if (params.value.length > 1) {
                   return params.seriesName + ' :<br/>'
                   + params.value[0] + '效率, '
                   + params.value[1] + '温度， '
                   + params.value[2] + '槽号 ';
               }
               else {
                   return params.seriesName + ' :<br/>'
                   + params.name + ' : '
                   + params.value + '  ';
               }
           },
           axisPointer:{
               show: true,
               type : 'cross',
               lineStyle: {
                   type : 'dashed',
                   width : 1
               }
           }
       },
       toolbox: {
           feature: {
               dataZoom: {},
               brush: {
                   type: ['rect', 'polygon', 'clear']
               }
           }
       },
       brush: {
       },
       legend: {
           data: ['槽温','分子比','电压'],
           left: 'center'
       },
       xAxis : [
           {
               type : 'value',
               scale:true,
               axisLabel : {
                   formatter: '{value} '
               },
               splitLine: {
                   show: false
               }
           }
       ],
       yAxis : [
           {
               type : 'value',
               scale:true,
               axisLabel : {
                   formatter: '{value} '
               },
               splitLine: {
                   show: false
               }
           }
       ],
       series : [
           {
               name:'槽温',
               type:'scatter',
               data: [[161.2, 51.6], [167.5, 59.0], [159.5, 49.2], [157.0, 63.0], [155.8, 53.6],
                   [170.0, 59.0], [159.1, 47.6], [166.0, 69.8], [176.2, 66.8], [160.2, 75.2]
               ],
               markArea: {
                   silent: true,
                   itemStyle: {
                       normal: {
                           color: 'transparent',
                           borderWidth: 1,
                           borderType: 'dashed'
                       }
                   },
                   data: [[{
                       name: '槽温分布区间',
                       xAxis: 'min',
                       yAxis: 'min'
                   }, {
                       xAxis: 'max',
                       yAxis: 'max'
                   }]]
               },
               markPoint : {
                   data : [
                       {type : 'max', name: '最大值'},
                       {type : 'min', name: '最小值'}
                   ]
               },
               markLine : {
                   lineStyle: {
                       normal: {
                           type: 'solid'
                       }
                   },
                   data : [
                       {type : 'average', name: '平均值'},
                       { xAxis: 93 }
                   ]
               }
           },
           {
               name:'分子比',
               type:'scatter',
               data: [[174.0, 65.6], [175.3, 71.8], [193.5, 80.7], [186.5, 72.6], [187.2, 78.8],
                   [181.5, 74.8], [184.0, 86.4], [184.5, 78.4], [175.0, 62.0], [184.0, 81.6]
               ],
               markArea: {
                   silent: true,
                   itemStyle: {
                       normal: {
                           color: 'transparent',
                           borderWidth: 1,
                           borderType: 'dashed'
                       }
                   },
                   data: [[{
                       name: '分子比分布区间',
                       xAxis: 'min',
                       yAxis: 'min'
                   }, {
                       xAxis: 'max',
                       yAxis: 'max'
                   }]]
               },
               markPoint : {
                   data : [
                       {type : 'max', name: '最大值'},
                       {type : 'min', name: '最小值'}
                   ]
               },
               markLine : {
                   lineStyle: {
                       normal: {
                           type: 'solid'
                       }
                   },
                   data : [
                       {type : 'average', name: '平均值'},
                       { xAxis: 93 }
                   ]
               }
           },
           {
                          name:'电压',
                          type:'scatter',
                          data: [[174.0, 65.6], [175.3, 71.8], [193.5, 80.7], [186.5, 72.6], [187.2, 78.8],
                              [181.5, 74.8], [184.0, 86.4], [184.5, 78.4], [175.0, 62.0], [184.0, 81.6]
                          ],
                          markArea: {
                              silent: true,
                              itemStyle: {
                                  normal: {
                                      color: 'transparent',
                                      borderWidth: 1,
                                      borderType: 'dashed'
                                  }
                              },
                              data: [[{
                                  name: '分子比分布区间',
                                  xAxis: 'min',
                                  yAxis: 'min'
                              }, {
                                  xAxis: 'max',
                                  yAxis: 'max'
                              }]]
                          },
                          markPoint : {
                              data : [
                                  {type : 'max', name: '最大值'},
                                  {type : 'min', name: '最小值'}
                              ]
                          },
                          markLine : {
                              lineStyle: {
                                  normal: {
                                      type: 'solid'
                                  }
                              },
                              data : [
                                  {type : 'average', name: '平均值'},
                                  { xAxis: 93 }
                              ]
                          }
                      }
       ]
   };
   ;
   if (option && typeof option === "object") {
       myChart.setOption(option, true);
   }
   $.get('/warehouse/selectCategoryDJWD',{newDate: $("#newDate").val(), workCode: $("#workCode").val()}).done(function (data) {
   console.log(data);
   var djwdJson=JSON.parse("["+data.djwd.toString()+"]");
   var fzbJson=JSON.parse("["+data.fzb.toString()+"]");
    var setvJson=JSON.parse("["+data.setv.toString()+"]");
       // 填入数据
       myChart.setOption({
           series: [{
               // 根据名字对应到相应的系列
               name: '槽温',
               data: djwdJson
           },{name: '分子比',data: fzbJson},{name: '电压',data: setvJson}]
       });
       });
   }



