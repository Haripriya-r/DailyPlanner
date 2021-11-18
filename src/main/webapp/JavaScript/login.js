let error=false;


//Validating input values from signin page
function validation(){
  error=false;

  var username=document.getElementById("usernameSignUp").value;
  verifyUsername(username,"userSignupMessage");

  var mobile=document.getElementById("mobileSignUp").value;	
  verifyMobile(mobile,"mobileSignUpMessage");

  var email=document.getElementById("emailSignup").value;	
  verifyEmail(email,"emailSignupMessage");

  var pw = document.getElementById("passwordSignUp").value;
  verifyPassword(pw,"passwordSignupMessage");

if(!error)
  {
   loadSignup();	
  }
}

//Validating input values from signin page
function validationLogin(){
	error=false;
	
	var emailId=document.getElementById("EmailLogin").value;
	verifyEmail(emailId,"emailLoginMessage");
	
	var pw = document.getElementById("passwordLogin").value;
    verifyPassword(pw,"passwordLoginMessage");
	
	if(!error){
		loadLogin();
	}
}



function verifyUsername(username,spanid)
{
document.getElementById(spanid).innerHTML = "";
  
  if(username == "") {  
     document.getElementById(spanid).innerHTML = "Username should not be empty!";  
	 error=true;
     
  }  	
}
function verifyPassword(pw,spanid) {  
    
  document.getElementById(spanid).innerHTML = "";
  
  
  if(pw == "") {  
     document.getElementById(spanid).innerHTML = "Password should not be empty!";  
	 error=true;
    
  }  
   
 
  if(pw.length < 4) {  
     document.getElementById(spanid).innerHTML = "Password length must be atleast 4 characters!";	
     error=true;	 
     
  }  
}

/*function VerifyRepeatPassword(pw){
	var pwrepeat=document.getElementById("RpPasswordSignUp").value; 
	document.getElementById("RpPasswordMessage").innerHTML = "";
	
  if (pw!=pwrepeat)
  {
	document.getElementById("RpPasswordMessage").innerHTML = "Password didn't match!";
	 error=true;
     //return false;  
  }  
  
 
      
}*/
function verifyMobile(mobile,spanid){
	document.getElementById(spanid).innerHTML ="";
	if (!/^\d{10}$/.test(mobile)) {
		document.getElementById(spanid).innerHTML = "Invalid Mobile address!";	 
		error=true; 
	} 
	
}


function verifyEmail(email,spanid) {
	document.getElementById(spanid).innerHTML ="";
	
	if (!/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email))
  {
    document.getElementById(spanid).innerHTML = "Invalid email address!";	 
	error=true;
	
  }
   
	
}

//Ajax Call for login page
function loadLogin()
{
	let email=document.getElementById("EmailLogin").value;
	let password=document.getElementById("passwordLogin").value;
	$.ajax({
		      url:'../loginServlet',
		      data :{
				    email:email,
					password:password,
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
		    	 sessionStorage.setItem("session_email",email);
		    	 window.location = '../HTML/home.html';
		     }else{
		    	 document.getElementById("passwordLoginMessage").innerHTML = "Invalid email address or password";
		     }  
		  }
}

//Ajax call for SignUp page
function loadSignup()
{	
    let username=document.getElementById("usernameSignUp").value;
	let password=document.getElementById("passwordSignUp").value;
	let mobile=document.getElementById("mobileSignUp").value;
	let email=document.getElementById("emailSignup").value;
	
	$.ajax({
		      url:'../signupServlet',
		      data :{
				    userName:username,
					password:password,
					mobile:mobile,
					email:email
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
		    	 sessionStorage.setItem("session_email",email);
		    	 window.location = '../HTML/home.html';
		    	
		     }
		     else if(data=="-1"){
		    	 document.getElementById("emailSignupMessage").innerHTML = "User Already Exists";
		     }
		    else{
		     document.getElementById("emailSignupMessage").innerHTML = "Something went wrong";
		    }  
		  }
	
}