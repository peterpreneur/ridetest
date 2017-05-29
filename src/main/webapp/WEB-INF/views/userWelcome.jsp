<%--
  Created by IntelliJ IDEA.
  User: nickk
  Date: 5/25/2017
  Time: 10:23 AM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <title>Address Form</title>

    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">

    <link type="text/css" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500">
    <link type="text/css" rel="stylesheet" src="/resources/style.css">

    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">

</head>

<body>

<form name="form" method="post" action="/ridecompare" onsubmit="return validateForm()">

<h1>Please Enter Current Location</h1><br>
    <div class="container">
        <div id="locationField1">
            <input id="autocomplete" name="pickup" placeholder="Enter your address" onFocus="geolocate()" type="text"
                   size="45"/>
        </div>
    </div>


    <input type="hidden" class="field" id="street_number" name="streetNum" disabled="true"/>

    <input type="hidden" class="field" id="route" name="routee" disabled="true"/>

    <input type="hidden" class="field" id="locality" name="local" disabled="true"/>

    <input type="hidden" class="field" id="administrative_area_level_1" name="nope" disabled="true"/>

    <input type="hidden" class="field" id="postal_code" name="postal" disabled="true"/>

    <input type="hidden" class="field" name="count" id="country" disabled="true"/>


    <h1>Please Enter Destination</h1><br>
    <div class="container">
        <div id="locationField2">
            <input id="autocomplete1" placeholder="Enter your address" onFocus="geolocate()" type="text" size="45"/>
        </div>
    </div>


    <input type="hidden" class="field" id="street_number1" name="strtN" disabled="true"/>

    <input type="hidden" class="field" id="route1" name="rou" disabled="true"/>

    <input type="hidden" class="field" id="locality1" name="loca" disabled="true"/>

    <input type="hidden" class="field" id="administrative_area_level_11" name="yep" disabled="true"/></td>

    <input type="hidden" class="field" id="postal_code1" name="posta" disabled="true"/>

    <input type="hidden" class="field" name="userCountry" id="country1" disabled="true"/>

    <fieldset>
        <legend>Filters</legend>
        <h4>Number of Riders</h4>
        <input type="radio" name="capSeat" value="1">4 Riders<br>
        <input type="radio" name="capSeat" value="2">6 Riders<br>
        <input type="radio" name="capSeat" value="3">All Types<br>
    </fieldset>

    <input type="submit" value="Submit" align="center"/>

</form>

    <script>
    // This example displays an address form, using the autocomplete feature
    // of the Google Places API to help users fill in the information.

    // This example requires the Places library. Include the libraries=places
    // parameter when you first load the API. For example:
    // <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&libraries=places">

    var placeSearch, autocomplete, autocomplete2;
    var componentForm = {
        street_number: 'short_name',
        route: 'long_name',
        locality: 'long_name',
        administrative_area_level_1: 'short_name',
        country: 'long_name',
        postal_code: 'short_name'
    };
    var componentForm2 = {
        street_number: 'short_name',
        route: 'long_name',
        locality: 'long_name',
        administrative_area_level_1: 'short_name',
        country: 'long_name',
        postal_code: 'short_name'
    };

    function initAutocomplete() {
        // Create the autocomplete object
        autocomplete = new google.maps.places.Autocomplete(
            /** @type {!HTMLInputElement} */(document.getElementById('autocomplete')),
            {types: ['geocode']['establishment']});

        // When the user selects an address from the dropdown, populate the address
        // fields in the form.
        autocomplete.addListener('place_changed', fillInAddress);

        autocomplete2 = new google.maps.places.Autocomplete(
            /** @type {!HTMLInputElement} */(document.getElementById('autocomplete1')),
            {types: ['geocode']['establishment']});

        // When the user selects an address from the dropdown, populate the address
        // fields in the form.
        autocomplete2.addListener('place_changed', fillInAddress2);
    }

    function fillInAddress() {
        // Get the place details from the autocomplete object.
        var place = autocomplete.getPlace();

        for (var component in componentForm) {
            document.getElementById(component).value = '';
            document.getElementById(component).disabled = false;
        }

        // Get each component of the address from the place details
        // and fill the corresponding field on the form.
        for (var i = 0; i < place.address_components.length; i++) {
            var addressType = place.address_components[i].types[0];
            if (componentForm[addressType]) {
                var val = place.address_components[i][componentForm[addressType]];
                document.getElementById(addressType).value = val;
            }
        }
    }
    function fillInAddress2() {

        // Get the place details from the autocomplete object.
        var place = autocomplete2.getPlace();
//        console.log(place);
        for (var component in componentForm2) {
//            console.log(component);
            document.getElementById(component + "1").value = '';
            document.getElementById(component + "1").disabled = false;
        }

        // Get each component of the address from the place details
        // and fill the corresponding field on the form.
        for (var i = 0; i < place.address_components.length; i++) {
            var addressType = place.address_components[i].types[0];
//            console.log(addressType);
            if (componentForm2[addressType]) {
                var val = place.address_components[i][componentForm2[addressType]];
                document.getElementById(addressType + "1").value = val;
            }
        }
    }

    // Bias the autocomplete object to the user's geographical location,
    // as supplied by the browser's 'navigator.geolocation' object.
    function geolocate() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function (position) {
                var geolocation = {
                    lat: position.coords.latitude,
                    lng: position.coords.longitude
                };
                var circle = new google.maps.Circle({
                    center: geolocation,
                    radius: position.coords.accuracy
                });
                autocomplete.setBounds(circle.getBounds());
                autocomplete2.setBounds(circle.getBounds());
            });
        }
    }


    function validateForm() {
        var fromStreetNum = document.forms["form"]["streetNum"].value;
        var toStreetNum = document.forms["form"]["strtN"].value;
        var fromStreetName = document.forms["form"]["routee"].value;
        var toStreetName = document.forms["form"]["rou"].value;
        var fromCity = document.forms["form"]["local"].value;
        var toCity = document.forms["form"]["loca"].value;
        var fromState = document.forms["form"]["administrative_area_level_1"].value;
        var toState = document.forms["form"]["administrative_area_level_11"].value;

        if (autocomplete != ({types:['establishment']}) || (autocomplete2 != ({types:['establishment']}))){

            if (fromStreetNum == "") {
                alert("Looks like you didn't enter a complete address...");
                return false;
            }
            if (toStreetNum == "") {
                alert("Looks like you didn't enter a complete address...");
                return false;
            }
            if (fromStreetName == "") {
                alert("Looks like you didn't enter a complete address...");
                return false;
            }
            if (toStreetName == "") {
                alert("Looks like you didn't enter a complete address...");
                return false;
            }
            if (fromCity == "") {
                alert("Looks like you didn't enter a complete address...");
                return false;
            }
            if (toCity == "") {
                alert("Looks like you didn't enter a complete address...");
                return false;
            }
            if (fromState == "") {
                alert("Looks like you didn't enter a complete address...");
                return false;
            }
            if (toState == "") {
                alert("Looks like you didn't enter a complete address...");
                return false;
            }
        }return true;
    }



</script>
<br>
<!--script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDDj_Zhpzv6SH4i-uPWy10gXtOpXfEZYw8&libraries=places&callback=initAutocomplete"
        async defer></script-->
<script src="https://maps.googleapis.com/maps/api/js?key=${GoogleAPIKey}&libraries=places&callback=initAutocomplete" async defer>
</script>
</body>
</html>
