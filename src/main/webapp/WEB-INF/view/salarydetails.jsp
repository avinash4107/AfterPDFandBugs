<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Centraprise</title>
<link rel="icon" href="/images/logo.gif">
<link href="/css/bootstrap.min.css" rel="stylesheet" />
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" />
<link href="/css/styles.css" rel="stylesheet">

<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css"
	rel="stylesheet" />
<script src="/javascript/2.1.1.jquery.min.js"></script>

<!-- Custom Theme Style -->
<link href="/css/styles.css" rel="stylesheet">
<META NAME="DESCRIPTION" CONTENT=" ">
<META NAME="KEYWORDS" CONTENT="">

<script type="text/javascript"> 
var dt_obj= new Date();
function addOption(selectbox,text,value )
{
var optn = document.createElement("OPTION");
optn.text = text;
optn.value = value;
selectbox.options.add(optn);
}
///////////// date //////////////////
var today_date= dt_obj.getDate();
function addOption_list1(){
for (var i=1; i < 32;++i){
addOption(document.drop_list.dt_list, i, i);
if(i== today_date){document.drop_list.dt_list.options[i].selected=true;}
}
addOption_list2();
}
//////////////End of Date //////////


///////////// Month //////////////////
var current_month=dt_obj.getMonth() +1;
function addOption_list2(){
var month = new Array("January","February","March","April","May","June","July","August","September","October","November","December");
for (var i=0; i < month.length;++i){
addOption(document.drop_list.Month_list, month[i], month[i]);
if(i== current_month){document.drop_list.Month_list.options[i].selected=true;}
}
addOption_list3();

}
//////////////End of Month //////////


</script>

</head>
<body lang="en" onload=addOption_list1();>
	<!--For for adding user -->
	<div class="container">
		<div class="wrapper">
			<div class="row row-offcanvas row-offcanvas-left">
				<!-- sidebar -->
				<div class="sidebar-offcanvas" id="sidebar">
					<a href="">
						<div class="logo">
							<img src="/images/logo.gif" />
						</div>
					</a>
					<ul class="nav" id="myMenu">
						<li id="item2"><a href="/emp"><i class="fa fa-home"></i>
								<span class="collapse in hidden-xs">Home</span></a></li>
						<li id="item3"><a href="/addemployee"><i
								class="fa fa-users"></i> <span class="collapse in hidden-xs">Employee
									Details</span></a></li>
						<li id="item4"><a href="/salarystructure"><i
								class="fa fa-calendar"></i> <span class="collapse in hidden-xs">Month
									End Process</span></a></li>
						<li id="item5"><a href="/saveDeductionInfo"><i
								class="fa fa-money"></i> <span class="collapse in hidden-xs">IT
									Benefit</span></a></li>
						<li id="item5"><a href="/finalsalaryinfo"><i
								class="fa fa-money"></i> <span class="collapse in hidden-xs">Salary
									Structure</span></a></li>
						<li id="item5"><a href="/leaveInfo"><i
								class="fa fa-minus"></i> <span class="collapse in hidden-xs">Leave
									Management</span></a></li>
						<li id="item1"><a href="/logout"><i
								class="fa fa-sign-out"></i><span class="collapse in hidden-xs">
									Log Out</span></a></li>
					</ul>


				</div>
				<!-- sidebar -->

				<div class="user-list" id="user_list">
					<!-- Header -->
					<div class="top-nav">
						<!-- top tiles -->
						<div class="tile_count">

							<div class="col-md-2 col-sm-4 col-xs-6 title_stats_count">
								<div class="count_bottom">
									<i class="fa fa-user"></i> Total Employees
								</div>
								<a href=""><div class="count green"></div> </a>
							</div>
							<div class="col-md-2 col-sm-4 col-xs-6 title_stats_count">
								<div class="count_center">
									<i class="fa fa-user"></i> Managers
								</div>
								<a href=""><div class="count green"></div></a>
							</div>
							<div
								class="col-md-2 col-sm-4 col-xs-6 title_stats_count noborder">
								<div class="count_bottom">
									<i class="fa fa-users"></i> Total Project Groups
								</div>
								<a href="">
									<div class="count green"></div>
								</a>

							</div>


						</div>
					</div>
					<!-- Header -->

					<div class="user-table">
						<div class="text-left-grp">Monthly Salary Details</div>

						<div class="form-fields">
							<form:form name="drop_list" role="form" action="/saveSaloryInfo"
								modelAttribute="salaryInfo" method="post"
								onsubmit="return validate();">
								<div class="form-group">
									<div>
										<label for="basic salary" class="lable-name">Month * </label>
										<SELECT NAME="dt_list">
											<Option value="">Date</option>
										</SELECT> <SELECT NAME="Month_list">
											<Option value="">Month</option>
										</SELECT>
									</div>
								</div>

								<div class="form-group">
									<label for="employee number" class="lable-name">Employee
										Number *</label>
									<form:select class="input-name" id="employeenumber"
										name="employeenumber" path="employeenumber" size="1">
										<form:option value="" />
										<form:options items="${employees}" />
									</form:select>
								</div>
								<div class="form-group">
									<div>
										<label for="basic salary" class="lable-name">Basic
											Salary * </label> <input type="text" class="input-name" id="basic"
											name="basic" required="" />

									</div>
								</div>
								<div class="form-group">
									<div>
										<label for="special allowance" class="lable-name">Special
											Allowance * </label> <input type="text" class="input-name"
											id="allowance" name="allowance" required="" />
									</div>
								</div>

								<div class="form-group">
									<div>
										<label for="incentive" class="lable-name">Incentive *
										</label> <input type="text" class="input-name" id="incentive"
											name="incentive" required="" />
									</div>
								</div>

								<div class="form-group">
									<div>
										<label for="special payment" class="lable-name">Special
											Payment * </label> <input type="text" class="input-name"
											id="specialpayment" name="specialpayment" required="" />
									</div>
								</div>
								<div class="ln_solid"></div>
								<div class="buttons">
									<input class="btn btn-info" id="submit" name="submit"
										type="submit" value="Add Salary" onclick="return submit();">
								</div>
							</form:form>
							<div class="footer">
								<p>
									Copyright &copy;
									<script>document.write(new Date().getFullYear())</script>
									All Rights Reserved.
								</p>
							</div>
						</div>
					</div>

				</div>
			</div>
</body>
</html>











