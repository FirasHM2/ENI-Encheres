<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>ENI Encheres</title>
<style>
.jumbotron {
  background-image: url("https://happier1.imgix.net/img/blog/20150319-20150319-shutterstock_81215629.jpg?w=700");
  background-size: cover;
  height:250px;
}
.container {
margin-top : 10%;
}
</style>

</head>

<body>
<jsp:include page="/WEB-INF/Page_acceuil/navBar.jsp" />
<div class="container" style="width: 500px">
<div class="jumbotron text-center">
	</div>

<form action="${pageContext.request.contextPath}/Connection" method ="POST">

  <div class="form-group">
  
    <label for="exampleInputEmail1">Identifiant :</label>
    <input type="text" class="form-control" name="Identifiant">
    
  </div>
  
  <div class="form-group">
  
    <label for="exampleInputPassword1">Mot de passe :</label>
    <input type="password" class="form-control" name="Mdp">
    
  </div>
  
  <div class="form-group form-check">
  
    <input type="checkbox" class="form-check-input" id="exampleCheck1" name="SaveMe">
    <label class="form-check-label" for="exampleCheck1">Se souvenir de moi</label>
    
  </div>
  
  <button name="button_Connexion" type="submit" class="btn btn-primary">Connexion</button>
  
</form>
<p> ${msg} </p>

</div>

</body>

</html>