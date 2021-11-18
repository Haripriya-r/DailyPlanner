$(document).ready(function() {
 $("#ProfileBttn").click(function() {
	 $(".main").empty();
	 $(".modal-content").css({"width": "24%", "height": "419px"});
	 $(".modal").css({"padding-top": "100px"});
	
	 
	 var username=sessionStorage.getItem("username");
	 var email=sessionStorage.getItem("session_email");
	 var mobile=sessionStorage.getItem("mobile");
	 
 $("#main").html(
		 '<span class="close">&times;</span>'+
		 '<div class="card">'+
		 '<img src="../Images/user.jpg" alt="John" style="width:68%; margin: auto; display: block;">'+
		 '<h1 style="text-align: center; margin-top: -7px;">'+username+'</h1><br>'+
		 '<p class="title" style=""><i class="fa fa-Home  icon" ></i> '+ 'Home' +'</p>'+
		 '<p class="title" style=""><i class="fa fa-envelope  icon" ></i> '+email +'</p>'+
		 '<p class="title" style=""><i class="fa fa-phone  icon" ></i> '+mobile +'</p>'+
         '</div>'
           );
    
 });
});


var btn = document.getElementById("ProfileBttn");
	
btn.onclick = function()
 {
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
	
	

 