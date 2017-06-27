<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<link rel="icon" href="/GoldArmor/resources/images/company_logo.png">
<title>Review & Confirmation</title>
</head>
<body>
	<c:import url="/header" />
	<form action="/GoldArmor/checkout" method="POST"
		onsubmit="return checkform()">
		<input type="text" name="arf" value="8888" hidden/>
		<input type="text" name="promotioncode" value="${promotioncode}" hidden />
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<ol class="breadcrumb">
						<li><font color="#2E86C1"><i
								class="fa fa-shopping-cart" aria-hidden="true"></i>&nbsp;Shopping
								Cart</font></li>
						<li><font color="#2E86C1"><i class="fa fa-money"
								aria-hidden="true"></i>&nbsp;Billing & Payment</font></li>
						<li class="active"><i class="fa fa-download"
							aria-hidden="true"></i>&nbsp;Download & Install</li>
					</ol>
				</div>
			</div>

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
							<th>PRODUCT / SERVICES</th>
							<th>PRICE</th>
						</thead>
						<tbody style="font-size: 0.8em; margin-top: 12px;">
							<tr style="height: 16px;"></tr>
							<c:forEach items="${shoppingCartDAOList}" var="shoppingCartDAO"
								varStatus="loop">
								<tr>
									<td><b>${loop.index + 1}.
											${shoppingCartDAO.getProductName()}</b> (up to
										${shoppingCartDAO.getNumberofdevices()} device(s),
										${shoppingCartDAO.getNumberofyears()} year(s))</td>
									<td>$ ${shoppingCartDAO.getProductPrice()}</td>
								</tr>
							</c:forEach>

							<tr>
								<td colspan="2"><hr></td>
							</tr>
							<tr>
								<td></td>
								<td>TAXES &nbsp;&nbsp; $ 0.00</td>
							</tr>
							<tr>
								<td></td>
								<td>DISCOUNT &nbsp;&nbsp; $ ${discount}</td>
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
									<p style="margin-top: -12px;">
										By placing this order you agree to the <a
											href="/GoldArmor/termsofservices">Terms of Services
											Agreement</a>
									</p>
								</td>
							</tr>
							<tr>
								<td colspan="2" style="text-align: center;"><input
									type="text" name="email" value="${email}" hidden /> <input
									type="text" name="personalname" value="${personalname}" hidden />
									<input type="text" name="companyname" value="${companyname}"
									hidden /> <input type="text" name="address" value="${address}"
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
				<div style="border-style: dashed; margin-top: 30px;">
					<div style="margin: 8px;">
						<p style="margin-top: 30px;">
							Checkout: <b>Continue to PayPal Site</b>
						</p>
						<p>To finish the order, please follow these steps:</p>
						<ol>
							<li>Click on the "Check Out" button above to go to the
								PayPal website.</li>
							<li>If you have a PayPal account, enter your email address
								and password to log in. If you do not have a PayPal account,
								enter the information to create one.</li>
							<li>Confirm your payment method and complete payment. PayPal
								will email you a receipt and return you to our order page.</li>
						</ol>
					</div>
				</div>
				<div style="margin-top: 12px;">
					<p>
						<b>What will happen after I place my order?</b>
					</p>
					<p style="margin-top: -12px;">After placing your order, you
						will receive an email confirmation and more information regarding
						the purchased products/services.</p>
				</div>
			</div>
		</div>
	</form>
	<c:import url="/footer" />
</body>
</html>