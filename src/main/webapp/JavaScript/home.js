var notCompletedTask;
var completedTask;

window.onload=function(){
	if(sessionStorage.length==0)
	{
	 window.location = '../HTML/login.html';
	}
var session_email = sessionStorage.getItem("session_email");
getUserName(session_email);

};

//Ajax Call
function getUserName(session_email)
{	
	$.ajax({
	      url:'../homeServlet',
	      data :{
			    email:session_email
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
		  data = JSON.parse(data);
		  ch=data;
		  
	      if(data!=""){
	    	  
	    	  sessionStorage.setItem("username",data.name);
	    	  sessionStorage.setItem("mobile",data.mobile);
	    	  calculatePerctange(data);	  
	      }
	  }
};

//Piechart Value Calculation
function calculatePerctange(data)
{  
	var count=0;
    var todayCountComp=0;
    var todayCount=0;
	var TaskList=data.TaskList;
	
	if(TaskList.length==0){
		completedTask=50;
		notCompletedTask=50;
	}
	else{
	  var length=TaskList.length;
      var todayDate = new Date().toISOString().slice(0, 10);
	  for(i=0;i<length;i++)
	  {
		 if( data.TaskList[i].completed==1)
			 count++;
		 if(data.TaskList[i].DateTime.includes(todayDate))
		{
		if(data.TaskList[i].completed==1)
			todayCountComp++;
	    else
	    	todayCount++;
	     }
	}
	completedTask=Math.round((count/length)*100);
    notCompletedTask=Math.round(((length-count)/length )*100);
	}
    document.getElementById("notCompleted").innerHTML = todayCount;
    document.getElementById("completed").innerHTML =todayCountComp ;
    
    drawChart();
	
	}

//For showing Success Messages
function toast() {
	  var x = document.getElementById("snackbar");
	  x.className = "show";
	  setTimeout(function(){ x.className = x.className.replace("show", ""); }, 1000);
	}

//SignOut 
function SignOut(){
sessionStorage.clear();
window.open("http://localhost:8080/ToDoApp/HTML/login.html","_self");
};


