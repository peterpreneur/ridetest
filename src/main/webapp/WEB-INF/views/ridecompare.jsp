<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: broadwells
  Date: 5/8/17
  Time: 1:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Compare</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
          integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
</head>
<body>
<div class="card">
    <div class="card-header">
        Your Trip
    </div>
    <div class="card-block">
        <blockquote class="card-blockquote">
            <p><h6>Pick Up: </h6> ${fromAdd} </p>
            <p><h6>Drop Off: </h6> ${toAdd} </p>
        </blockquote>
    </div>
</div>
<div class="card">
    <div class="row">
        <div class="col-sm-6">
            <div class="card">
                <div class="card-block">
                    <h3 class="card-title">UBER</h3>
                    <p class="card-text"> ${product} <br> No. of Riders: ${cap}</p>
                    <h5 class="card-title" name="price">${price}</h5>
                    <h5 class="card-title" name="time">Driver is ${time} minutes away</h5>
                    <a href="https://m.uber.com/ul/?action=setPickup"  action="finishSelection" class="btn btn-primary" target="_blank">Call Uber</a>
                </div>
            </div>
        </div>
        <div class="col-sm-6">
            <div class="card">
                <div class="card-block">
                    <h3 class="card-title">LYFT</h3>
                    <p class="card-text">${typeOfLyft} <br> No. of Riders: ${capacity} </p>
<<<<<<< Updated upstream
                    <h5 class="card-title">${displayPriceMin}-${displayPriceMax}</h5>
=======
                    <h5 class="card-title">${displayPriceMin} - ${displayPriceMax}</h5>
>>>>>>> Stashed changes
                    <h5 class="card-title">Driver is ${driverETA} minutes away</h5>
                    <a href="https://lyft.com/i" class="btn btn-primary" target="_blank" >Call Lyft</a>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>
