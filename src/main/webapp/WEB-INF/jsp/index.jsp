<%--
  Created by IntelliJ IDEA.
  User: vegasjm
  Date: 05/11/2017
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>My Video Club</title>

  <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/css/custom/table.css" />"/>
  <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/css/custom/videorental.css" />"/>
  <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/css/external/ace.min.css" />"/>
  <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/css/external/bootstrap.min.css" />"/>
  <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/css/external/jquery.gritter.css" />"/>

  <script src="<c:url value="/js/external/jquery-1.5.2.min.js" />" type="text/javascript"></script>
  <script src="<c:url value="/js/external/jquery.tooltip.js" />" type="text/javascript"></script>
  <script src="<c:url value="/js/external/jquery.ui.min.js" />" type="text/javascript"></script>
  <script src="<c:url value="/js/external/jquery.gritter.min.js" />" type="text/javascript"></script>
  <script src="<c:url value="/js/custom/tableGenerator.js" />" type="text/javascript"></script>
  <script src="<c:url value="/js/custom/videorental.js" />" type="text/javascript"></script>
</head>
<br>
<div id="container">
  <h3>My Video Club</h3> <button id="restartDatabase" value="Restart Database">Restart Database</button>
  <div id="containerBody">
    <div id="video-rental-customers-table" class="myTable"></div>
    <div id="video-rental-customers-transactions-table" class="myTable"></div>
  </div>
</div>


<div id="video-rental-add-customer-dialog" title="Add Customer">
  <div>
    <label for="nameNewCusto">Name</label><input type="text" id="nameNewCusto" name="nameNewCusto"/>
    <label for="surnameNewCusto">Surname</label><input type="text" id="surnameNewCusto" name="surnameNewCusto"/>
  </div>
  <div style="clear: both"></div>
  <br/>
</div>

<div id="video-rental-add-customer-transaction-dialog" title="Add Customer Transaction">
  <div>
    <label for="movieNewCustoTx">Movie</label><Select id="movieNewCustoTx" name="movieNewCustoTx"></Select>
    <label for="nDaysNewCustoTx">Days</label><input type="text" id="nDaysNewCustoTx" name="nDaysNewCustoTx" value="0"/>
    <label for="nExtraDaysNewCustoTx">Extra Days</label><input type="text" id="nExtraDaysNewCustoTx" name="nExtraDaysNewCustoTx" value="0"/>
  </div>
  <div style="clear: both"></div>
  <br/>
</div>
</body>
</html>