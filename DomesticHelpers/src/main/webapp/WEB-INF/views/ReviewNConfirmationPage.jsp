<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<title>Review & Confirmation</title>
</head>
<body>
	<c:import url="/header" />
	<form action="/DomesticHelpers/checkout" method="POST">
		<input type="text" name="id" value="${id}" hidden />
		<input type="text" name="startdate" value="${startdate}" hidden />
		<input type="text" name="totaldays" value="${totaldays}" hidden />
		<div class="container">

			<div class="row">
				<div class="col-md-6">
					<p style="font-size: 1.8em;">
						<b>Review & Confirmation</b>
					</p>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-12 col-centered">
					<table class="col-md-12">
						<thead>
							<th>SERVICES</th>
							<th>PRICE</th>
						</thead>
						<tbody style="font-size: 0.8em; margin-top: 12px;">
							<tr style="height: 16px;"></tr>
								<tr>
									<td>${description}</td>
									<td>$ ${total}</td>
								</tr>

							<tr>
								<td colspan="2"><hr></td>
							</tr>
							<tr>
								<td></td>
								<td><b>TOTAL &nbsp;&nbsp; $ ${total}</b></td>
							</tr>
							<tr style="height: 30px;"></tr>
							<tr>
								<td>
									<p>
										<font color="gray">EMAIL</font>
									</p>
									<p style="margin-top: -12px;">${email}</p> <br>
									<p>
										<font color="gray">ADDRESS</font>
									</p>
									<p style="margin-top: -12px;">
										${personalname}<br> ${companyname}<br> ${address}<br>
										${state}<br> ${city}<br> ${zipcode}<br>
										${country}
									</p>
								</td>
								<td></td>
							</tr>
							<tr>
								<td colspan="2" style="text-align: center;">
									<p>Payment method: PayPal</p>
								</td>
							</tr>
							<tr>
								<td colspan="2" style="text-align: center;"><input
									type="text" name="email" value="${email}" hidden /> <input
									type="text" name="personalname" value="${personalname}" hidden />
									<input type="text" name="address" value="${address}"
									hidden /> <input type="text" name="country" value="${country}"
									hidden /> <input type="text" name="state" value="${state}"
									hidden /> <input type="text" name="city" value="${city}" hidden />
									<input type="text" name="zipcode" value="${zipcode}" hidden />
									<button type="submit" class="btn btn-primary active">
										<i class="fa fa-paypal" aria-hidden="true"></i>&nbsp;&nbsp;Check Out
									</button></td>
							</tr>
						</tbody>
					</table>

				</div>

			</div>
		</div>
	</form>
	<c:import url="/footer" />
</body>
</html>