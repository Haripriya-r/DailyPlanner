$(document).ready(function() {
 $("#myBtn").click(function() {
	 $(".main").empty();
	 modal();
	 $(".modal-content").css({"width": "42%", "height": "417px"});
	 $(".modal").css({"padding-top": "100px"});
	 
	 
 $("#main").html(
		    '<span class="close">&times;</span>'+
			'<label for="user" class="customLabel" placeholder="Task">Add Your Task Here</label><br></br>'+
		    '<input class="info" id="task"> </input>'+
		    '<span id ="taskError" class="error"> </span> <br></br>'+ 
			'<label for="user" class="customLabel" > Add Date</label><br></br>'+
		    '<input class="DateTime" id="currentDate" ></input>'+
			'<span id ="DateError" class="error"> </span> <br></br>'+ 
			'<label for="user" class="customLabel" >Time to finish</label><br></br>'+
			'<input class="DateTime" id="currentTime"></input><br></br>'+
			'<button class="btn" onclick="validation()"> Add Task</button>'+
			'<div id="snackbar">Task Added Successfully</div>'
	); 
    addTimeDate();
    
 });
});

//For Modal
function modal(){
	
    var btn = document.getElementById("myBtn");
	
	btn.onclick = function() {
	var modal = document.getElementById("myModal");
    var span;
	modal.style.display = "block";
	
	setTimeout(function() {
		span = document.getElementsByClassName("close")[0];
		span.onclick = function() {
	    modal.style.display = "none";
		};	
	},0);
	};
	
    window.onclick = function(event) {
	if (event.target == modal) {
	 modal.style.display = "none";
	}
	};
	
}
 
//Getting current date and time
function addTimeDate(){
var today = new Date();
var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
  document.getElementById("currentTime").value = time;
 
 var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
  document.getElementById("currentDate").value = date;

}


//For validating the input values
function validation(){
	 	let error=false;
	    let task=document.getElementById("task").value;
	    let date=document.getElementById("currentDate").value;
		let time=document.getElementById("currentTime").value;
		
		document.getElementById("taskError").innerHTML = "";
		document.getElementById("DateError").innerHTML = "";
		  
		  
		  if(task == "") {  
		     document.getElementById("taskError").innerHTML = "Task Should not be empty";  
		     error=true;
		     
		  }
		  
		  if(date == "") {  
			     document.getElementById("DateError").innerHTML = "Date Should not be empty";  
			     error=true;
				 
		  }
		  
		  if(date!="")
			  {
			  var dateSplit =date.split('/');
			  if(dateSplit==0)
				  {
			  document.getElementById("DateError").innerHTML = "Date Should be entered in yyyy-mm-dd format";  
			     error=true;
				  }
			
			  var today = new Date();
			  today.setHours(0,0,0,0);
			  var varDate = new Date(date);
	

			  if(varDate < today) {
			      document.getElementById("DateError").innerHTML = "Date entered shoud be greater than todays date";  
				  error=true;			 
				  }
			  
			  }
		  if(!error){
			  addTask();
		  }
		  
	  
  }
  
//Ajax Call
  function addTask(){
	  
	    let task=document.getElementById("task").value;
	    let date=document.getElementById("currentDate").value;
		let time=document.getElementById("currentTime").value;
		let email=sessionStorage.getItem("session_email");
		$.ajax({
			      url:'../addTaskServlet',
			      data :{
			    	  email:email,
			    	  task:task,
			    	  date:date,
			    	  time:time
					},
			      type:'post',
			      async:false,
			      success: function (data) {
			          callbackfn(data)
			      },
			      error: function (textStatus, errorThrown) {
			          callbackfn("Error getting the data")
			      }

			   });
			  function callbackfn(data)
			  {  
				  
			     if(data=="1"){
			    	  toast();
			     } 
			  }
			  //Changing the Home Page Values after new task addition
			  getUserName(email);
  }
  
	  
  
  