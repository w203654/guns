

/**
 * 查询产铝量盘存台账列表
 */
function search(){
 initSingleText();
 initBar01();
 initBar02();
};
$(function () {
//初始化 常用属性
  init();

//槽控雷达图
//  initRadar();
  //初始化 雷达图左侧文本框
  initSingleText();
  initBar01();
  initBar02();
});

//初始化 常用属性
function init(){
$("#newDate").val(laydate.now(0, 'YYYY-MM-DD'));
initSelect();
$("#workCode").change(function() {initSelect();});

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
var ajax = new $ax(Feng.ctxPath + "/warehouse/singlePotnoData", function (data) {
//清除文本框
        $("#yhlcnt").val('');
        $("#djwd").val('');
        $("#lsp").val('');
        $("#zsxl").val('');
        $("#zsl").val('');

       Feng.success("获取成功!");
           $.each(data,function(i,obj){
//           console.log(obj);
           switch(obj.type)
           {
           case 'zsxl':
             $("#zsxl").val(obj.val);
             break;
           case 'lsp':
              $("#lsp").val(obj.val);
             break;
           case 'yhlcnt':
             $("#yhlcnt").val(obj.val);
             break;
           case 'djwd':
             $("#djwd").val(obj.val);
             break;
           case 'zsl':
             $("#zsl").val(obj.val);
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


function initBar01(){
var dom = document.getElementById("container01");
var myChart = echarts.init(dom);
var app = {};
option = null;
app.title = '坐标轴刻度与标签对齐';

option = {
 title:{text:'月度累计最高效率七台槽',left:'center'},
    color: ['#3398DB'],
    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis : [
        {
            type : 'category',
            data : ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
            axisTick: {
                alignWithLabel: true
            }
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'效率',
            type:'bar',
            label: {
                normal: {
                    show: true,
                    position: 'top'
                        }
                    },
            barWidth: '60%',
            data:[10, 52, 200, 334, 390, 330, 220]
        }
    ]
};
;
if (option && typeof option === "object") {
    myChart.setOption(option, true);
}
 $.get('/warehouse/singlePotnoHeight',{newDate: $("#newDate").val()}).done(function (data) {
    // 填入数据
    myChart.setOption({
        xAxis: [{
                data:data.type
            }],
            series: [{
                name: '效率',
                data: data.val
            }]
         });
     });
}

function initBar02(){
var dom = document.getElementById("container02");
var myChart = echarts.init(dom);
var app = {};
option = null;
app.title = '坐标轴刻度与标签对齐';

option = {
 title:{text:'月度累计最低效率七台槽',left:'center'},
    color: ['#3398DB'],
    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis : [
        {
            type : 'category',
            data : ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
            axisTick: {
                alignWithLabel: true
            }
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'效率',
            type:'bar',
            label: {
            normal: {
                show: true,
                position: 'top'
                    }
                },
            barWidth: '60%',
            data:[10, 52, 200, 334, 390, 330, 220]
        }
    ]
};
;
if (option && typeof option === "object") {
    myChart.setOption(option, true);
}

 $.get('/warehouse/singlePotnoLow',{newDate: $("#newDate").val()}).done(function (data) {
    // 填入数据
    myChart.setOption({
        xAxis: [{
                data:data.type
            }],
            series: [{
                name: '效率',
                data: data.val
            }]
         });
     });
}