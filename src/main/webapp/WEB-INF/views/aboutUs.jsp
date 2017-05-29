<%--
  Created by IntelliJ IDEA.
  User: nickk
  Date: 5/26/2017
  Time: 11:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meet the Creators</title>
    <style>
        div.show-image {
            position: relative;
            float:left;
            margin:5px;
        }
        div.show-image1 {
            position: relative;
            float:left;
            margin:5px;
        }
        div.show-image2 {
            position: relative;
            float:left;
            margin:5px;
        }
        div.show-image3 {
            position: relative;
            float:left;
            margin:5px;
        }
        div.show-image:hover img{
            opacity:0.5;
        }
        div.show-image1:hover img{
            opacity:0.5;
        }
        div.show-image2:hover img{
            opacity:0.5;
        }
        div.show-image3:hover img{
            opacity:0.5;
        }
        div.show-image:hover input {
            display: block;
        }
        div.show-image1:hover input {
            display: block;
        }
        div.show-image2:hover input {
            display: block;
        }
        div.show-image3:hover input {
            display: block;
        }
        div.show-image input {
            position:absolute;
            display:none;
        }
        div.show-image1 input {
            position:absolute;
            display:none;
        }
        div.show-image2 input {
            position:absolute;
            display:none;
        }
        div.show-image3 input {
            position:absolute;
            display:none;
        }
        div.show-image input.linkedin {
            top:0;
            left:0;
        }
        div.show-image1 input.linkedin {
            top:0;
            left:0;
        }
        div.show-image2 input.linkedin {
            top:0;
            left:0;
        }
        div.show-image3 input.linkedin {
            top:0;
            left:0;
        }
        div.show-image input.github {
            top:0;
            left:79%;
        }
        div.show-image1 input.github {
            top:0;
            left:79%;
        }
        div.show-image2 input.github {
            top:0;
            left:79%;
        }
        div.show-image3 input.github {
            top:0;
            left:79%;
        }
    </style>
</head>
<body>
<div align="center">
    <h1>Meet The Creators</h1> <br>
</div>

<div class="show-image">
    <img src="https://www.grandcircus.co/wp-content/uploads/2017/05/Stephanie-Broadwell.jpg" />
    <input class="linkedin" type ="button"  value="LinkedIn" onclick="location.href='https://www.linkedin.com/in/stephanie-broadwell-15a14b6b/';"/>
    <input class="github" type ="button"  value="GitHub" onclick="location.href='https://github.com/broadwells';"/></div>

<div class="show-image1">
    <img src="https://www.grandcircus.co/wp-content/uploads/2017/05/Allie-Born.jpg"/>
    <input class="linkedin" type ="button"  value="LinkedIn" onclick="location.href='https://www.linkedin.com/in/alexandra-born/';"/>
    <input class="github" type ="button"  value="GitHub" onclick="location.href='https://github.com/allieborn/';"/></div>

<div class="show-image2">
    <img src="https://www.grandcircus.co/wp-content/uploads/2017/05/Peter-Setiawan.jpg"/>
    <input class="linkedin" type ="button"  value="LinkedIn" onclick="location.href='https://www.linkedin.com/in/psetiawan/';"/>
    <input class="github" type ="button"  value="GitHub" onclick="location.href='https://github.com/peterpreneur';"/></div>

<div class="show-image3">
    <img src="https://www.grandcircus.co/wp-content/uploads/2017/05/Nick-Katsarelas.jpg"/>
    <input class="linkedin" type ="button"  value="LinkedIn" onclick="location.href='https://www.linkedin.com/in/nick-katsarelas-11790a134/';"/>
    <input class="github" type ="button"  value="GitHub" onclick="location.href='https://github.com/nickkatz22';"/></div>

</body>
</html>
