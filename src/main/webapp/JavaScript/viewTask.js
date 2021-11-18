//for pending records
let email;
$(document).ready(function() {
	$("#bttn").click(function() { 
	$(".main").empty();
	var val=0;
	email=sessionStorage.getItem("session_email");
    modal();
    viewTask(email,val);
	});
	 });

//for all records
$(document).ready(function() {
	$("#allTask").click(function() { 
	$(".main").empty();
	var val=1;
	let email=sessionStorage.getItem("session_email");
    modal();
    viewTask(email,val);
	 });
		});

//Ajax call
function viewTask(email,val){
	$.ajax({
	      url:'../viewTaskServlet',
	      data :{
	    	  email:email,
	    	  status:val
			},
	      type:'post',
	      async:false,
	      success: function (data) {
	    	  if(val==0){
	    	  drawTable(data);
	    	  }
	    	  if(val==1){
	    	  drawTableForAllTask(data);
	    	  }
	    	  
	      },
	      error: function (textStatus, errorThrown) {
	    	  if (val==0){
		    	  drawTable(-1);
		    	  }
		    	  if (val==1){
		    	  drawTableForAllTask(-1);
		    	  }
	      }
});
}

//Modal
function modal(){
	 $(".modal-content").css({"width": "62%", "height": "571px"});
	 $(".modal").css({"padding-top": "60px"});
	var modal = document.getElementById("myModal");
	var btn = document.getElementById("bttn");
    var span;
   
    modal.style.display = "block";
    setTimeout(function() {
	span = document.getElementsByClassName("close")[0];
	span.onclick = function() {
		modal.style.display = "none";
		};	
     },0);

    window.onclick = function(event) {
	if (event.target == modal) {
	modal.style.display = "none";
	}
	};
}


//Creating table 
function drawTable(data)
{  		   
	  if(data!=-1){
		  data = JSON.parse(data);
		  ch=data;	
		  $("#main").html('<span class="close">&times;</span>'+
					'<div id="table">'+ 
		  /*$('#table').html(*/
				  '<table id="example" class="display" style="width:100%">'+
			        '<thead>'+
			        ' <tr>'+
			               '<th>Task</th>'+
			                '<th>DateTime</th>'+
			                '<th>Status</th>'+
			           ' </tr>'+
			        '</thead>'+
			        '<tbody>'				     
		 );		  
		  for(var i=0;i<data.TaskList.length;i++){
			  $('#example').append(
				  '<tr>'+
				  '<td>'+data.TaskList[i].task+'</td>'+
				  '<td>'+data.TaskList[i].DateTime+'</td>'+
				  '<td>'+ 
				    '<label for="priority"></label>'+
			        '<select id="select_'+i+'"  class="completedCheck" name="priority" onChange="change('+i+')">'+
			        '<option  value="Low">not completed</option>'+
			        '<option  value="Medium">completed</option></select>'+
			  	    '</td>'+
				  '</tr>'
			  );
		  }
		  $('#example').append('</tbody></table><div>');
		  $('#example').append('<div id="snackbar">completed the task successfully </div>');
		  showDataTable();
	  }
	  
}

function drawTableForAllTask(data)
{  		   
	  if(data!=-1){
		  data = JSON.parse(data);
		  ch=data;	
		  $("#main").html('<span class="close">&times;</span>'+
					'<div id="table">'+ 
		  /*$('#table').html(*/
				  '<table id="example" class="display" style="width:100%">'+
			        '<thead>'+
			        ' <tr>'+
			               '<th>Task</th>'+
			                '<th>DateTime</th>'+
			                '<th>Status</th>'+
			           ' </tr>'+
			        '</thead>'+
			        '<tbody>'				     
		 );		  
		  for(var i=0;i<data.TaskList.length;i++){
			  $('#example').append(
				  '<tr>'+
				  '<td>'+data.TaskList[i].task+'</td>'+
				  '<td>'+data.TaskList[i].DateTime+'</td>'+
				  '<td>'+"completed"+'</td>'+
				  '</tr>'
			  );
		  }
		  $('#example').append('</tbody></table><div>');
		  showDataTable();
	  }
	  
}

function showDataTable(){
	 $('#example').DataTable(
			 {
			        "order": [[ 1, "asc" ]]
			    }
			 ); 
	 $('#example_length').hide();
};

//After clicking completed in task,call to backend
function change(i)
{
	var s='select_'+i;
    var TaskStatus = $('#'+s+'').find(":selected").text();
    var changedTask=ch.TaskList[i].task;
	var changedTime= ch.TaskList[i].DateTime;
	if (TaskStatus=="completed"){
		updateCompletedTask(changedTask,changedTime);
	}
	modal();
	
	
    
}

function updateCompletedTask(changedTask,changedTime)
{
	let email=sessionStorage.getItem("session_email");
	$.ajax({
	      url:'../updateTaskServlet',
	      data :{
	    	  task:changedTask,
	    	  time:changedTime,
	    	  email:email
			},
	      type:'post',
	      async:false,
	      success: function (data) {
	    	  drawTable(data);
	    	  toast();
	    	  
	      },
	      error: function (textStatus, errorThrown) {
	    	  drawTable(-1);
	      }
});
	
	//for Drawing pie chart again with changed value
	getUserName(email);
	
	}
