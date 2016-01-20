<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="mastermenu" value="${sessionScope['mastermenu']}" />
<c:set var="bookingmenu" value="${sessionScope['bookingmenu']}" />
<c:set var="daytourmenu" value="${sessionScope['daytourmenu']}" />
<c:set var="financemenu" value="${sessionScope['financemenu']}" />
<c:set var="reportmenu" value="${sessionScope['reportmenu']}" />
<c:set var="checkingmenu" value="${sessionScope['checkingMenu']}" />
<c:set var="accountmenu" value="${sessionScope['accountmenu']}" />

<nav class="navbar " style="margin-bottom: -5px; height: 5px;" role="navigation">
	<div class="navbar-header">
		<a class="navbar-brand" style="padding: 0px" href="#"> <img
                        src="${pageContext.request.contextPath}/img/smilogo.jpg" style="width: 55px;height: 40px"/>
		</a>
	</div>
	<form class="nav navbar-nav navbar-right" style="margin-right: 0px">
		<!--
        <li id="fat-menu" class="dropdown">
            <a id="drop3" href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" role="button" aria-expanded="false">
                <span style="padding-right: 10px;" class="glyphicon glyphicon-envelope" aria-hidden="true"></span><span  class="badge" style="background-color:#cd0a0a">3</span>
            </a>
            
            <ul class="dropdown-menu" role="menu" aria-labelledby="drop3">
                <li role="presentation"><a role="menuitem" tabindex="-1" href="#">warning 1</a></li>
                <li role="presentation"><a role="menuitem" tabindex="-1" href="#">ticket due date</a></li>
                <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Something else here</a></li>
                <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Alert</a></li>
                <li role="presentation" class="divider"></li>
                <li role="presentation"><a role="menuitem" tabindex="-1" data-toggle="modal" data-target="#alertModal" >Add new message</a></li>
            </ul>
            
        </li>
        -->
                <li st><a href="#" style="font-size: small;">User: ${sessionScope['username']} </a></li>
		<li><a href="#" style="font-size: small;">Role: ${sessionScope['rolename']} </a></li>
		<li><a href="logout.smi" style="font-size: small;"><i class="glyphicon glyphicon-lock"></i>
				Logout</a></li>
	</form>
</nav>


<nav class="navbar navbar-inverse " role="navigation" id="menuTop">

	<!-- Brand and toggle get grouped for better mobile display -->
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target="#bs-example-navbar-collapse-1">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="" style="font-size: 14px;" >HOME</a>
	</div>

	<script type="text/javascript" charset="utf-8">
		$(document).ready(
                    function() {
                            var masterMenuList = '<c:forEach var="name" items="${mastermenu}"><c:out value="${name}" />:</c:forEach>';
                            var reportMenuList = '<c:forEach var="name" items="${reportmenu}"><c:out value="${name}" />:</c:forEach>';
                            var bookingMenuList = '<c:forEach var="name" items="${bookingmenu}"><c:out value="${name}" />:</c:forEach>';
                            var daytourMenuList = '<c:forEach var="name" items="${daytourmenu}"><c:out value="${name}" />:</c:forEach>';
                            var CheckingMenuList = '<c:forEach var="name" items="${checkingmenu}"><c:out value="${name}" />:</c:forEach>';
                            var FinanceMenuList = '<c:forEach var="name" items="${financemenu}"><c:out value="${name}" />:</c:forEach>';
                            var AccountMenuList = '<c:forEach var="name" items="${accountmenu}"><c:out value="${name}" />:</c:forEach>';
                            var masterMenu = [
                                            "<li><a id='menu-mairticket' href='Mairticket.smi'> Air ticket</a></li>",
                                            "<li><a id='menu-mcity' href='MCity.smi'> Others</a></li>",
                                            "<li><a id='menu-mhotel' href='MHotel.smi'> Hotel</a></li>",
                                            "<li><a id='menu-mproduct' href='Product.smi'> Product</a></li>",
                                            "<li><a id='menu-mplace' href='Place.smi'> Place</a></li>",
                                            "<li><a id='menu-magent' href='Agent.smi'> Agent</a></li>",
                                            "<li><a id='menu-mstaff' href='MStaff.smi'>Staff</a></li>",
                                            "<li><a id='menu-mgalileo' href='MGalileo.smi'>Configure Galileo</a></li>",
                                            "<li><a id='menu-mamadeus' href='MAmadeus.smi'>Configure Amadeus</a></li>",
                                            "<li><a id='menu-mdaytour' href='MDaytour.smi'>Tour</a></li>",
                                            "<li><a id='menu-mcommission' href='MCommission.smi'>Commission</a></li>",
                                            "<li><a id='menu-mbank' href='MBank.smi'>Bank</a></li>",
                                         //   "<li><a id='menu-maccountcode' href='MAccountCode.smi'>Account Code</a></li>",
                                            "<li><a id='menu-maparcode' href='APARCode.smi'>AP/AR Code</a></li>",         
                                            "<li><a id='menu-stock' href='SearchStock.smi'>Stock</a></li>",
                                            "<li><a id='menu-DefineVar' href='DefineVar.smi'>Define Variable</a></li>",
                                            "<li><a id='menu-mhost' href='MHost.smi'>Host</a></li>",
                                    //	"<li><a id='menu-maccount' href='MAccountCode.smi'>Account</a></li>",
                                            "<li><a id='menu-mpackage' href='MPackage.smi'>Package</a></li>" ];

                            var bookingMenu = [ "<li><a id='menu-book' href='Book.smi'> Booking</a></li>",
                                               "<li><a id='menu-information' href='BookInformation.smi'>Information</a></li>" ];
                                           
                            var daytourMenu = [ "<li><a a id='menu-daytouredit' href='DaytourOperationDetail.smi?action=edit'> Day tour</a></li>",
                                                "<li><a id='menu-other' href='DaytourOperationOther.smi'> Other</a></li>"];
                                            
                            var reportMenu = [
                                            "<li><a id='menu-checkingairticket' href='TicketFareSummaryReport.smi'>Checking AirTicket</a></li>",
                                            "<li><a id='menu-hotel' href='HotelSumReport.smi'> Hotel</a></li>",
                                            "<li><a id='menu-land' href=''>Land</a></li>",
                                            "<li><a id='menu-other' href=''>Others</a></li>",
                                            "<li><a id='menu-financeandaccount' href='InvoiceMonthly.smi'>Finance And Account</a></li>",
                                            "<li><a id='menu-outboundsummary' href='OutboundSummary.smi'>Outbound Summary</a></li>",
                                            "<li><a id='menu-bookingsummary' href='AirTicketMonthReport.smi'>Booking Summary</a></li>",
                                            "<li><a id='menu-checkingoutbound' href='OverdueSummary.smi'>Checking Outbound</a></li>"];
                            var CheckingMenu = [
                                            "<li><a id='menu-packagetourhotel' href='PaymentTourHotel.smi'> PackageTour/Hotel </a></li>",
                                            "<li><a id='menu-airticket' href='AddTicketFare.smi'> AirTicket </a></li>",
                                            "<li><a id='menu-outbound' href='PaymentOutbound.smi'> Outbound </a></li>" ];
                            var FinanceMenu = [
                                            "<li><a id='menu-invoice' href='SearchInvoice.smi'> Invoice</a></li>",
                                            "<li><a id='menu-creditnote' href='SearchCreditNote.smi'> Credit Note</a></li>",
                                            "<li><a id='menu-receipt' href='SearchReceipt.smi'> Receipt</a></li>",
                                            "<li><a id='menu-taxinvoice' href='SearchTaxInvoice.smi'> Tax Invoice</a></li>",
                                            "<li><a id='menu-receivetable' href='ReceiveTableWO.smi'> Receive Table</a></li>",
                                            "<li><a id='menu-lockandunlockbooking' href='LockUnlockBooking.smi'>Lock And UnLock Booking</a></li>" ];
                            var AccountMenu = [
                                            "<li><a id='menu-narvanainterface' href='APMonitor.smi'> Nirvana Interface </a></li>",
                                            "<li><a id='menu-taxinvoicereport' href='PostSaleVat.smi'>Sale Vat Report</a></li>" ];                
                            var menuString = getActiveMenuString(
                                            masterMenuList, masterMenu);
                            document.getElementById("menuMaster").innerHTML = menuString;

                            var bookingMenuString = getActiveMenuString(
                                            bookingMenuList, bookingMenu);
                            document.getElementById("bookingMenu").innerHTML = bookingMenuString;

                            var daytourMenuString = getActiveMenuString(
                                            daytourMenuList, daytourMenu);
                            document.getElementById("daytourMenu").innerHTML = daytourMenuString;

                            var reportMenuString = getActiveMenuString(
                                            reportMenuList, reportMenu);
                            document.getElementById("reportMenu").innerHTML = reportMenuString;

                            var checkingMenuString = getActiveMenuString(
                                            CheckingMenuList, CheckingMenu);

                            document.getElementById("checkingMenu").innerHTML = checkingMenuString;

                            var financeMenuString = getActiveMenuString(
                                            FinanceMenuList, FinanceMenu);
                            document.getElementById("financeMenu").innerHTML = financeMenuString;

                            var accountMenuString = getActiveMenuString(
                                            AccountMenuList, AccountMenu);
                            document.getElementById("accountMenu").innerHTML = accountMenuString;    
                    });
		function getActiveMenuString(activeList, menuList) {
			var menuString = "";
			var activeMenu = activeList.split(':');
			menuString = "";
			if (activeMenu[0].trim().length > 0) {
				for (j = 0; j < activeMenu.length; j++) {
					for (i = 0; i < menuList.length; i++) {
						var has = menuList[i].indexOf(activeMenu[j]);
						if (has > 0) {
							menuString += menuList[i].trim();
							break;
						}
					}
				}
			}
			return menuString;
		}
	</script>

	<!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" style="position: absolute; padding: 0px 0px 0px 140px;">
		<ul class="nav navbar-nav" id="navtop">
			<li class="dropdown"><a href="#" class="dropdown-header"
                            data-toggle="dropdown" style="height: 44px;"> <span
					class=""></span> MASTER <span
					class="caret"></span></a>
				<ul class="dropdown-menu" role="menu" id="menuMaster">
				</ul></li>
			<li class="dropdown"><a href="#" class="dropdown-header"
				data-toggle="dropdown" id="BookingMenu" style="height: 44px;"> <span
					class=""></span> BOOKING <span
					class="caret"></span></a>
				<ul class="dropdown-menu" role="menu" id="bookingMenu">

				</ul></li>

			<li class="dropdown" ><a href="#" class="dropdown-header"
				data-toggle="dropdown" style="height: 44px;"> <span class=""></span>
					OPERATION <span class="caret"></span></a>
				<ul class="dropdown-menu" role="menu" id="daytourMenu">
				</ul></li>

			<li class="dropdown" ><a href="#" class="dropdown-header"
				data-toggle="dropdown" style="height: 44px;"> <span class=""></span>
					CHECKING <span class="caret"></span></a>
				<ul class="dropdown-menu" role="menu" id="checkingMenu">
				</ul></li>

			<li class="dropdown" ><a href="#" class="dropdown-header"
				data-toggle="dropdown" style="height: 44px;"> <span class=""></span>
					FINANCE & CASHIER <span class="caret"></span></a>
				<ul class="dropdown-menu" role="menu" id="financeMenu">


				</ul></li>
                        <li class="dropdown" ><a href="#" class="dropdown-header"
				data-toggle="dropdown" style="height: 44px;"> <span class=""></span>
					ACCOUNTING <span class="caret"></span></a>
				<ul class="dropdown-menu" role="menu" id="accountMenu">


				</ul></li>        
			<li class="" >
				<!--<a href="AirTicketMonthReport.smi" >Report</a>--> <a href="#"
				class="dropdown-header" data-toggle="dropdown" style="height: 44px;"> <span
					class=""></span> REPORT <span
					class="caret"></span></a>
				<ul class="dropdown-menu" role="menu" id="reportMenu">


				</ul>
			</li>

		</ul>

	</div>


	<!-- /.navbar-collapse -->

	<!-- /.container -->
</nav>


<div class="modal fade" id="alertModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">warning message</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" role="form">
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">Supject</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputPassword3"
								placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Due
							date</label>
						<div class="col-sm-10">
							<input type="date" class="form-control" id="inputEmail3"
								placeholder="Email">
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">message</label>
						<div class="col-sm-10">
							<textarea class="form-control" rows="5" id="comment"></textarea>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary">Save changes</button>
				<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>




