<!DOCTYPE	html> 

<html>		
<head>				
	<meta	charset="utf-8">				
	<meta	http-equiv="X-UA-Compatible"	content="IE=edge">				
	<meta	name="viewport"	content="width=device-width,	initial-scale=1">				
	<title>Hello	World!</title>
				<link	rel="stylesheet"	href="css/bootstrap.css">
				<link	rel="stylesheet"	href="css/bootstrap.min.css">
				<link	rel="stylesheet"	href="css/bootstrap-grid.css">
				<link	rel="stylesheet"	href="css/bootstrap-grid.min.css">
				<link	rel="stylesheet"	href="css/bootstrap-reboot.min.css">
				<link	rel="stylesheet"	href="css/bootstrap-reboot.css">
				<!--[if	lt	IE	9]>						
				<script	src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"> </script>						
				<script	src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"> </script>				
				<![endif]-->		
</head>		
<body>
	<div class="container">
		<div class="jumbotron">
			<div class="row">
				<div class="col-md-3">
					<img src="/images/unical5.png"	class="img-responsive">
				</div>
				<div class="col-md-9">
					<div class="row ">
						<h1>University of Calabar, Calabar</h1>
					</div>
					<div class="row">
						<h5>Results Processing System</h5>
					</div>
				</div>
			</div>
			<div class="row text-center">
				<div class="col-md-3">
					<a href="/">Home</a>
				</div>
				<div class="col-md-3">
					<a href="new-student">Registration</a>
				</div>
				<div class="col-md-3">
					<a href="#">User Guide</a>
				</div>
				<div class="col-md-3">
					<a href="#">About</a>
				</div>
			</div>
		</div>
	</div>
	<c:choose>
		<c:when test="${mode == 'MODE_HOME'}">
			<div class="container text-center">
				<h1>You are welcome to the result system</h1>
			</div>
		</c:when>
		<c:when test="${mode == 'MODE_STUDENTS'}">
			<div class="container">
		<div class="jumbotron">
			<h3>Student</h3>
			<div class="table-response">
				<table class="table table-striped table-bordered text-left">
					<thead><tr>
						<th>id</th>
						<th>surname</th>
						<th>firstname</th>
						<th>middlename</th>
						<th>matric No</th>
						<th>sex</th>
						<th>session</th>
					</tr></thead>
					<tbody>
						<c:forEach var="students" items="${student}">
						<tr>
						<td>${student.studentId}</td>
						<td>${student.surname}</td>
						<td>${student.firstname}</td>
						<td>${student.middlename}</td>
						<td>${student.matricNo}</td>
						<td>${student.sex}</td>
						<td>${student.session}</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
		</c:when>
		<c:when test="${mode == 'MODE_NEW' || mode == 'MODE_UPDATE'}">
			<div class="container">
		<h1>Manage Student</h1>
		<form class="form-horizontal" method="POST" action="save-student">
			<input type="hidden" name="id" value="${student.studentId}"/>
			<div class="form-group">
				<label class="control-label col-md-3">Surname</label>
				<div class="col-md-5">
					<input type="text" class="form-control" name="surname" value="${student.surname}"/>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3">firstname</label>
				<div class="col-md-5">
					<input type="text" class="form-control" name="firstname" value="${student.firstname}"/>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3">middlename</label>
				<div class="col-md-5">
					<input type="text" class="form-control" name="middlename" value="${student.middlename}"/>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3">matric No</label>
				<div class="col-md-5">
					<input type="text" class="form-control" name="matricNo" value="${student.matricNo}"/>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3">sex</label>
				<div class="col-md-5">
					<input type="text" class="form-control" name="sex" value="${student.sex}"/>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3">Session</label>
				<div class="col-md-5">
					<input type="text" class="form-control" name="session" value="${student.session}"/>
				</div>
			</div>
			
			<div class="form-group">
				<input type="submit" class="btn btn-primary" value="save"/>
			</div>
			
		</form>
	</div>
		</c:when>
		<c:when test="${mode == 'MODE-HOME'}">
		
		</c:when>
	</c:choose>
	
	
	<div class="container">
      <footer class="jumbotron my-5 pt-5 text-muted text-center text-small">
        <p class="mb-1">&copy; 2017-2018 Company Name</p>
        <ul class="list-inline">
          <li class="list-inline-item"><a href="#">Privacy</a></li>
          <li class="list-inline-item"><a href="#">Terms</a></li>
          <li class="list-inline-item"><a href="#">Support</a></li>
        </ul>
      </footer></div>
    
	<script	src="js/jquery-3.3.1.js"></script>
	<script>window.jQuery || document.write('<script src="js/jquery-3.3.1.js"><\/script>')</script>				
	<script	src="js/bootstrap.js"></script>
	<script src="js/bootstrap.min.js"></script>	
	<script>
      // Example starter JavaScript for disabling form submissions if there are invalid fields
      (function() {
        'use strict';

        window.addEventListener('load', function() {
          // Fetch all the forms we want to apply custom Bootstrap validation styles to
          var forms = document.getElementsByClassName('needs-validation');

          // Loop over them and prevent submission
          var validation = Array.prototype.filter.call(forms, function(form) {
            form.addEventListener('submit', function(event) {
              if (form.checkValidity() === false) {
                event.preventDefault();
                event.stopPropagation();
              }
              form.classList.add('was-validated');
            }, false);
          });
        }, false);
      })();
    </script>	
</body> 
</html>
