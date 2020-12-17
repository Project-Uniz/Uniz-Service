<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#chart{
	width : 500px;
	hight : 300px;

}
</style>
</head>
<body>
	<h1>Chart실험실 </h1>

	<div id=chart>
		
		<canvas id="chartcanvas" height="450" width="600"></canvas>
	
	</div>
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.js"></script>

<script>

var chartLabels = [];

var chartData = [];



$.getJSON("http://localhost:8080/chartList", function(data){

	

	$.each(data, function(inx, obj){

		chartLabels.push(obj.unizkeyword);

		chartData.push(obj.count);

	});

	createChart();

	console.log("create Chart")

});



var lineChartData = {

		labels : chartLabels,

		datasets : [

			{

				label : "Uniz Data",

				fillColor : "rbga(151,187,205,0.2)",

				strokeColor : "rbga(151,187,205,1)",

				pointColor : "rbga(151,187,205,1)",

				pointStrokeColor : "#fff",

				pointHighlightFill : "#fff",

				pointHighlightStroke : "rbga(151,187,205,1)",

				data : chartData

			

		}

			]

}



function createChart(){

	var ctx = document.getElementById("chartcanvas").getContext("2d");

	LineChartDemo = Chart.Line(ctx,{
		
		type : 'pie',
		data : lineChartData,

		options :{

			scales : {

				yAxes : [{

					ticks :{

						beginAtZero : true

					}

				}]

			}

		}

	})



}
</script>

</body>
</html>