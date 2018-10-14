<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<title>获取公交站点坐标</title>
<style type="text/css">
html,body{ height: 100%;}
#results,#coordinate{ display: inline-block; width: 45%; min-height: 200px; border:1px solid #e4e4e4; vertical-align: top;}
</style>
<script src="http://api.map.baidu.com/api?v=1.3" type="text/javascript"></script>
</head>
<body>
<p><label for="busId">公交线路：</label><input type="text" value="521" id="busId" /><input type="button" id="btn-search" value="查询" /></p>
<div id="results"></div>
<div id="coordinate"></div>



<script type="text/javascript">

    var test = ${lineInfo};
	var tempVar;
	var lines = new Array("昌13路");
	var lineInfo_0 = new Object();
	var lineInfo_1 = new Object();

	person=new Object();
	person.firstname="Bill";
	person.lastname="Gates";
	
	(function(){
		//绑定按钮点击事件
		document.getElementById('btn-search').onclick = function(){
			for(var i=0;i<lines.length;i++){
				/*var busline = start();
				//在回调函数中返回公交列表结果
				busline.getBusList(lines[i]);*/

				console.info(test);
			}
		}
	})();
	
	function lookProperty(obj){

	　　var ob=eval(obj);

	　　var property="";

	　　for(var p in ob){

	　　	property+=p+"\n";

	　　}

	　　alert(property);

	};
	
	//获取站点信息
	function getCoordinate(result){
		console.info(result);
		var coordinate = document.getElementById("coordinate");
		lineInfo_0 = result[0];
		lineInfo_1 = result[1];
		//获取上行站点信息
		var stations = result[0]._stations;
		//获取下行站点信息
		var stations_1 = result[1]._stations;
		lookProperty(result[0]);
		var html = [];
		html.push(lineInfo_0.name);
		stations.forEach(function(item){
			html.push('<li>' + item.name + ' ' + item.position.lng + ' ' + item.position.lat + '</li>');
		});
		html.push("<br>");
		html.push(lineInfo_1.name);
		stations_1.forEach(function(item){
			html.push('<li>' + item.name + ' ' + item.position.lng + ' ' + item.position.lat + '</li>');
		});
		coordinate.innerHTML = '<ul>' + html.join('') + '</ul>';
	};
	
	function start(){
		//创建线路搜索对象并设置搜索区域(模糊查询)
		var busline = new BMap.BusLineSearch('北京市',{
			
			//结果展示元素
			renderOptions:{panel:"results"},
			
			//公交列表查询后的回调函数
			onGetBusListComplete: function(result){
			
				if(result) {
					tempVar = result;//此时的结果并不包含坐标信息，所以getCoordinate函数不能在此调用。通过跟踪变量，坐标是在onGetBusListComplete之后才被百度的包添加进来的
					//获取上行站点信息
					busline.getBusLine(result.getBusListItem(0));
					//获取下行站点信息
					busline.getBusLine(result.getBusListItem(1));
				}
			},
			
			// api文档中一共有四个回调，除了onGetBusListComplete和onBusLineHtmlSet之外，还有onBusListHtmlSet和onGetBusLineComplete，
			// 经过测试只有在onBusLineHtmlSet这一步（线路格式化完毕）的时候，才会将坐标添加到tempVar中
			// 所以上面busline.getBusLine(result.getBusListItem(0));是必须的，不然没有办法获得坐标列表
			//公交线路结果页渲染后回调函数
			onBusLineHtmlSet : function(){
				try{
					if(tempVar[1] != undefined){
						getCoordinate(tempVar);
					}
				}catch(e){
				}
			},
			
			//获取线路信息完成时调用
			onGetBusLineComplete:function(line){
				/*if(name_0 == undefined){
					name_0 = line.name;
					return;
				}
				name_1 = line.name;*/
			}
		});
		
		return busline;
			
	};
</script>
</body>
</html> 