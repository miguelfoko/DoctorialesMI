<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.doctoriales.mi.model.User"%>
<%@ page import="com.doctoriales.mi.model.Student"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Inscription</title>
<meta charset="utf-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/reset.css"
	type="text/css" media="all">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/layout.css"
	type="text/css" media="all">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css"
	type="text/css" media="all">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/formulaire.css"
	type="text/css" media="all">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css"
	type="text/css" media="all">
</head>

<body id="page1">
	<div class="body1">
		<!--div class="container">
			<!-- header -->
		<%-- <%@include file="includeFile/header.jsp" %> --%>
		<header>
			<%-- <%@include file="includeFile/navIndex.jsp" %> --%>
			<div class="container">
				<nav>
					<ul id="menu">
						<li><a href="${pageContext.request.contextPath}/">Accueil</a></li>
						<li><a href="${pageContext.request.contextPath}/registration">Inscription</a></li>
						<li><a
							href="${pageContext.request.contextPath}/uploadArticle">Soumettre
								un article</a></li>
						<li><a
							href="${pageContext.request.contextPath}/updateParameter">modifier
								vos parametres</a></li>
						<li><a href="${pageContext.request.contextPath}/updateArticle">Modifier
								un article</a></li>
					</ul>
				</nav>

			</div>
			<figure>
				<a href="#"><img
					src="${pageContext.request.contextPath}/resources/css/doctoriales2018.jpg"
					alt="" width="100%" height="477px"></a>
			</figure>

		</header>
		<!-- / header -->
	</div>

	<div class="body2" WITH="70%" align="justify">
	<H2>Comité d'organisation</H2>
		<ol>
			<li>Pr Emmanuel NGAMENI</li>
			<li>Pr Marcellin Julius NKENLIFACK</li>
			<li>Pr Célestin LELE</li>
			<li>Pr Clémentin TAYOU DJAMEGNI</li>
			
			<li>Pr Calvin TADMON</li>
			<li>Pr Jean Louis WOUKENG</li>

			<li>Dr Benoit Martin AZANGUEZET</li>
			<li>Dr Alain Bertrand BOMGNI</li>
			<li>Dr Gabriel DEUGOUE</li>
			<li>Dr Pascaline DICKMU LIAKEN</li>
			<li>Dr David DONGO</li>
			<li>Dr Éric FOTSING</li>
			<li>Dr Bernard FOTSING TALLA</li>
			<li>Dr Vianney KENGNE TCHENDJI</li>
			<li>Dr Blaise Blériot KOGUEP NJIONOU</li>
			<li>Dr Cletus KUM</li>
			<li>Dr Jean Pierre LIENOU</li>
			<li>Dr Guiléne MPAME</li>
			<li>Dr Thierry NOULAMO</li>
			<li>Dr Mathias ONABID</li>
			<li>Dr Jean Baptiste PATENOU</li>
			<li>Dr Elie TAGNE FUTE</li>
			<li>Dr Narcisse TALLA TANKAM</li>
			<li>Dr Calvin TCHEKA</li>
			<li>Dr Laurent TCHOUALAG</li>
			<li>Dr Maurice TCHOUPE TCHENDJI</li>
			<li>Dr Berge TSANOU</li> 
			<h2>Secrétariat Scientifique</h2>


			<li>Dr Géraud FOKOU PELAP</li>
			<li>Dr Maurice KENFACK NANGHO</li>
			<li>M. Mathurin SOH</li>

			<li>Dr Francis DJIOFACK</li>
			<li>M. Miguel Landry FOKO SINDJOUNG</li>

		</ol>
	</div>
	<div class="main3">

		<div class="pad_left1">
					<h2>Information récentes</h2>
				</div>
				<ul class="list1">
<%-- 					<li><a href="${pageContext.request.contextPath}/resources/Doctoriales.zip">Télécharger le template des articles</a></li> --%>
					<li><a href="#">Télécharger le template des articles</a></li>
					<li><a href="${pageContext.request.contextPath}/comiteOrganisation">Comité d'organisation</a></li>
					<li><a href="#">Présenter les travaux de recherche </a></li>
					<li><a href="#">Améliorer ses capacités a présenter</a></li>
					<li><a href="#">Se préparer a l'après thèse</a></li>
				</ul>



			</div>

		</div>
		<div class="body2">
			<div class="container">
				<!-- content -->
				<section id="content">
					<div class="wrapper">
						<div class="pad1 pad_top1">
							<article class="cols marg_right1">
								<figure>
									<a href="#"><img
										src="${pageContext.request.contextPath}/resources/css/page1_img1.jpg"
										alt="" width="282px" height="179px"></a>
								</figure>
								<span class="font1">Informatique</span>
							</article>
							<article class="cols marg_right1">
								<figure>
									<a href="#"><img
										src="${pageContext.request.contextPath}/resources/css/images.png"
										alt="" width="282px" height="179px"></a>
								</figure>
								<span class="font1">Mathématique Algèbre</span>
							</article>
							<article class="cols">
								<figure>
									<a href="#"><img
										src="${pageContext.request.contextPath}/resources/css/index.jpeg"
										alt="" width="282px" height="179px"></a>
								</figure>
								<span class="font1">Mathématique Analyse</span>
							</article>
						</div>
					</div>
				</section>
				<!-- content -->
				<!-- footer -->
				<%-- <%@include file="includeFile/footer.jsp" %> --%>
				<!-- 			<footer> -->
				<!-- 				<div class="wrapper"> -->
				<!-- 					<div class="pad1"> -->
				<!-- 						<div class="pad_left1"> -->
				<!-- 							<div class="wrapper"> -->
				<!-- 								<article class="col_1"> -->
				<!-- 									<h3>Etablissement</h3> -->
				<!-- 									<p class="col_address"> -->
				<!-- 										<strong>Faculte<br> Nom<br> filier<br> -->
				<!-- 											niveau -->
				<!-- 										</strong> -->
				<!-- 									</p> -->
				<!-- 									<p> -->
				<!-- 										FS<br> Informatique<br> 4<br> <a href="mailto:">lcenter@mail.com</a> -->
				<!-- 									</p> -->
				<!-- 								</article> -->
				<!-- 								<article class="col_2 pad_left2"> -->
				<!-- 									<h3>Nous joindre:</h3> -->
				<!-- 									<ul class="list2"> -->
				<!-- 										<li><a href="#">Sign Up</a></li> -->
				<!-- 										<li><a href="#">Forums</a></li> -->
				<!-- 										<li><a href="#">Promotions</a></li> -->
				<!-- 										<li><a href="#">Lorem</a></li> -->
				<!-- 									</ul> -->
				<!-- 								</article> -->
				<!-- 								<article class="col_3 pad_left2"> -->
				<!-- 									<h3>pourquoi nous:</h3> -->
				<!-- 									<ul class="list2"> -->
				<!-- 										<li><a href="#">Lorem ipsum dolor </a></li> -->
				<!-- 										<li><a href="#">Aonsect adipisic</a></li> -->
				<!-- 										<li><a href="#">Eiusmjkod tempor </a></li> -->
				<!-- 										<li><a href="#">Incididunt ut labore </a></li> -->
				<!-- 									</ul> -->
				<!-- 								</article> -->
				<!-- 								<article class="col_4 pad_left2"> -->
				<!-- 									<h3>Newsletter:</h3> -->
				<%-- 									<form id="newsletter" method="post"> --%>
				<!-- 										<div class="wrapper"> -->
				<!-- 											<div class="bg"> -->
				<!-- 												<input type="text"> -->
				<!-- 											</div> -->
				<!-- 										</div> -->
				<!-- 										<a href="#" class="button" -->
				<!-- 											onclick="document.getElementById('newsletter').submit()"><span><span><strong>Souscrire</strong></span></span></a> -->
				<%-- 									</form> --%>
				<!-- 								</article> -->
				<!-- 							</div> -->

				<!-- 						</div> -->
				<!-- 					</div> -->
				<!-- 				</div> -->
				<!-- 			</footer> -->
				<!-- / footer -->
			</div>
		</div>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/resources/js/jquery-1.5.2.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/resources/js/cufon-yui.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/resources/js/cufon-replace.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/resources/js/Molengo_400.font.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/resources/js/Expletus_Sans_400.font.js"></script>
		<script type="text/javascript">
		Cufon.now();
	</script>
</body>
</html>