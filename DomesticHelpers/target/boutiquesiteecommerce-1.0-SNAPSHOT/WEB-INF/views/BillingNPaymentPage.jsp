<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<title>Billing & Payment</title>
<script>

	function validateEmail(email) {
		var pattern = /^\w+@[a-zA-Z0-9_]+?\.[a-zA-Z0-9.]{2,}$/;
		return pattern.test(email);
	}

	function checkform() {

		if (!(document.getElementById("email").value == document
				.getElementById("retypeemail").value)) {
			alert('Email mismatch.');
			return false;
		}

		if (!(validateEmail(document.getElementById("email").value))) {
			alert('Incorrect email format (e.g. user@example.com)');
			return false;
		}

		return true;
	}
</script>
</head>
<body>
	<c:import url="/header" />
	<form action="/DomesticHelpers/reviewnconfirmation" method="POST"
		onsubmit="return checkform()">
		<input type="text" name="id" value="${id}" hidden />
		<input type="text" name="startdate" value="${startdate}" hidden />
		<input type="text" name="totaldays" value="${totaldays}" hidden />

		<div class="container">

			<div class="row">
				<div class="col-md-6 col-centered">
					<p style="font-size: 1.8em;">
						<b>Billing & Payment</b>
					</p>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-6 col-centered">
					<table class="col-md-12">
						<tbody style="font-size: 0.8em;">
							<tr>
								<td colspan="2"><hr></td>
							</tr>

							<!-- Email & Verify Email session -->
							<tr>
								<td style="height: 30px;"><b style="float: right;">E-MAIL:</b></td>
								<td><input type="text" name="email" id="email"
									style="margin-left: 12px;" oncopy="return false"
									oncut="return false" onpaste="return false" required /></td>
							</tr>
							<tr>
								<td style="height: 30px;"><b style="float: right;">VERIFY
										E-MAIL:</b></td>
								<td><input type="text" name="retypeemail" id="retypeemail"
									style="margin-left: 12px;" oncopy="return false"
									oncut="return false" onpaste="return false" required /></td>
							</tr>
							<tr>
								<td colspan="2"><hr></td>
							</tr>

							<!-- First Name & Last Name session -->
							<tr>
								<td style="height: 30px;"><b style="float: right;">FIRST
										NAME:</b></td>
								<td><input type="text" name="firstname" id="firstname"
									style="margin-left: 12px;" required /></td>
							</tr>
							<tr>
								<td style="height: 30px;"><b style="float: right;">LAST
										NAME:</b></td>
								<td><input type="text" name="lastname" id="lastname"
									style="margin-left: 12px;" required /></td>
							</tr>
							<tr>
								<td colspan="2"><hr></td>
							</tr>

							<!-- Address section -->
							<tr>
								<td style="height: 30px;"><b style="float: right;">ADDRESS:</b></td>
								<td><input type="text" name="address" id="address"
									style="margin-left: 12px;" required /></td>
							</tr>
							<tr>
								<td style="height: 30px;"><b style="float: right;">COUNTRY:</b></td>
								<td><select name="country" id="country" class="input-group"
									style="margin-left: 12px;" required>

										<option value="MY">Malaysia</option>
								</select></td>
							</tr>
							<tr>
								<td style="height: 30px;"><b style="float: right;">STATE:</b></td>
								<td><input type="text" name="state" id="state"
									style="margin-left: 12px;" required /></td>
							</tr>
							<tr>
								<td style="height: 30px;"><b style="float: right;">CITY:</b></td>
								<td><input type="text" name="city" id="city"
									style="margin-left: 12px;" required /></td>
							</tr>
							<tr>
								<td style="height: 30px;"><b style="float: right;">ZIP
										CODE:</b></td>
								<td><input type="text" name="zipcode" id="zipcode"
									style="margin-left: 12px;" required /></td>
							</tr>

							<!-- Payment Method -->
							<tr>
								<td colspan="2"><hr></td>
							</tr>

							<tr>
								<td style="height: 30px;"><b style="float: right;">PAYMENT
										METHOD:</b></td>
								<td><div class="radio" style="margin-left: 12px; margin-top: 8px;">
										<input type="radio" name="paymentmethod" id="paymentmethod"
											checked hidden /><label for="paymentmethod" style="margin-left: 6px; margin-top: 6px;">Paypal</label>
									</div></td>
							</tr>

							<!-- Order Summary -->
							<tr>
								<td colspan="2"><hr></td>
							</tr>

							<tr>
								<td style="height: 30px; vertical-align: text-top;"><b style="float: right; margin-top: 12px;">ORDER
										SUMMARY:</b></td>
								<td>
									<table style="margin-left: 12px; margin-top: 12px;">
											<tr>
												<td>
													<p><small><b>${helpersInfoDAO.hi_fullname} (${helpersInfoDAO.hi_category}, Can communicate with ${helpersInfoDAO.hi_languageknown})</small>
													</p>
												</td>
											</tr>
									</table>
								</td>
							</tr>

							<tr>
								<td style="height: 30px;"></td>
								<td><div style="float: right;">
										<i>You will be charged $ ${total}</i>
									</div></td>
							</tr>
							<tr>
								<td></td>
								<td><div style="float: right;">
										<input type="submit" value="Confirm Order"
											class="btn btn-primary active" />
									</div></td>
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