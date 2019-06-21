<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>

<link href="/css/bootstrap.min.css" rel="stylesheet">
<script src="/js/jquery-2.2.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<link href="/css/style.css" rel="stylesheet">

<script type="text/javascript">
	$(function() {
		$('button[type=submit]').click(function(e) {
			e.preventDefault();
						
			//Disable submit button
			$(this).prop('disabled',true);
			
			var form = document.forms[0];
			var formData = new FormData(form);
				
			// Ajax call for file uploaling
			var ajaxReq = $.ajax({
				url : 'fileUpload',
				type : 'POST',
				data : formData,
				cache : false,
				contentType : false,
				processData : false,
				xhr: function(){
					//Get XmlHttpRequest object
					 var xhr = $.ajaxSettings.xhr() ;
					
					//Set onprogress event handler 
					 xhr.upload.onprogress = function(event){
						var perc = Math.round((event.loaded / event.total) * 100);
						$('#progressBar').text(perc + '%');
						$('#progressBar').css('width',perc + '%');
					 };
					 return xhr ;
				},
				beforeSend: function( xhr ) {
					//Reset alert message and progress bar
					$('#alertMsg').text('');
					$('#progressBar').text('');
					$('#progressBar').css('width','0%');
                }
			});

			// Called on success of file upload
			ajaxReq.done(function(msg) {
				$('#alertMsg').text(msg);
				$('input[type=file]').val('');
				$('button[type=submit]').prop('disabled',false);
			});
			
			// Called on failure of file upload
			ajaxReq.fail(function(jqXHR) {
				$('#alertMsg').text(jqXHR.responseText+'('+jqXHR.status+
						' - '+jqXHR.statusText+')');
				$('button[type=submit]').prop('disabled',false);
			});
		});
	});
	
	
	
	$(document).ready(function() {

		$('#dlcountry').change(function() {
			$("#myform").attr("action", "/home")
			$("#myform").submit();
		});
		
		
		$('#dlcountry2').change(function() {
						
			debugger;
			
			$.getJSON('/findCity', {
				stateName : $(this).val(),		
				ajax : 'true'	
			
			}, function(data) {
				var html = '<option value="">--- Select ---</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
									
					html += '<option value="' + data[i].id + '">'
							+ data[i].name + '</option>';
				}
				html += '</option>';

				$('#dlcity').html(html);
			});
			
			
		});
		
	});
</script>


</head>


<body>

	<h2>Cascading drop down and picture upload</h2>

	<div class="container" style="margin: 50px; border: 1px solid green;">

		<form:form method="post" modelAttribute="userForm"
			action="fileUpload" id="myform" enctype="multipart/form-data">

			<form:errors path="*" cssClass="errorblock" element="div" />
			

			<table class="table">

				<tr>
					<td>User Name:</td>
					<td><form:input path="username" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><form:password path="password" /></td>
				</tr>
				<tr>
					<td>E-mail:</td>
					<td><form:input path="email" /></td>
				</tr>
				<tr>
					<td>Birthday (mm/dd/yyyy):</td>
					<td><form:input path="birthDate" /></td>
				</tr>

				<tr>
					<td>Country :</td>
					<td><form:select path="country" id="dlcountry">
							<form:option value="NONE" label="--- Select ---" />
							<form:options items="${countryList}" />
						</form:select></td>
					<td><form:errors path="country" cssClass="error" /></td>
				</tr>

				<tr>
					<td>City :</td>
					<td><form:select path="city" id="dlcity">
							<form:option value="NONE" label="--- Select ---" />
							<form:options items="${cityList}" />
						</form:select></td>
					<td><form:errors path="city" cssClass="error" /></td>
				</tr>

				<tr>
					<td>Java Skills :</td>
					<td><form:select path="javaSkills" items="${javaSkillsList}"
							multiple="true" /></td>
					<td><form:errors path="javaSkills" cssClass="error" /></td>
				</tr>

				<tr>
					<td><label>Select File</label></td>

					<td><input class="form-control" type="file" name="file">
						<button class="btn btn-primary" type="submit">Upload</button></td>

				</tr>
				<tr>
					<td></td>
					<td>
						<!-- Bootstrap Progress bar -->
						<div class="progress">
							<div id="progressBar" class="progress-bar progress-bar-success"
								role="progressbar" aria-valuenow="0" aria-valuemin="0"
								aria-valuemax="100" style="width: 0%">0%</div>
						</div>

					</td>
				</tr>

				<tr>


					<td colspan="3">

						<div class="text-left">

						<button class="btn btn-primary" type="submit">Submit</button>
						

						</div>

					</td>


				</tr>
			</table>

		</form:form>

		,
	</div>

</body>
</html>