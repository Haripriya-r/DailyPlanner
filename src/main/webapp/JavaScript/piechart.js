
	google.charts.load('current', {'packages':['corechart']});
	google.charts.setOnLoadCallback(drawChart);

	
	function drawChart() {
	  var data = google.visualization.arrayToDataTable([
	  ['Task', 'completed'],
	  ['completed', completedTask],
	  ['Pending', notCompletedTask]
	]);

	 
	  var options = {'title':'Task completed Status', 'width':550, 'height':400 , colors: ['#333', '#9d9d9d'] };

	  var chart = new google.visualization.PieChart(document.getElementById('piechart'));
	  chart.draw(data, options);
	}

