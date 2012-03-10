var mapdata = { destination: new google.maps.LatLng(-31.642567539017584, -60.704827308654785) };

//Home page
$('#page-home').live("pageinit", function() {
    $('#map_square').gmap(
        { 'center' : mapdata.destination,
          'zoom' : 12,
          'mapTypeControl' : false,
          'navigationControl' : false,
          'streetViewControl' : false
        })
        .bind('init', function(evt, map) {
            $('#map_square').gmap('addMarker',
                { 'position': map.getCenter(),
                  'animation' : google.maps.Animation.DROP
 });                                                                                                                                                                                                               
        });
    $('#map_square').click( function() {
        $.mobile.changePage($('#page-map'), {});
    });
});



function fadingMsg (locMsg) {
    $("<div class='ui-loader ui-overlay-shadow ui-body-e ui-corner-all'><h1>" + locMsg + "</h1></div>")
    .css({ "display": "block", "opacity": 0.9, "top": $(window).scrollTop() + 100 })
    .appendTo( $.mobile.pageContainer )
    .delay( 2200 )
    .fadeOut( 1000, function(){
        $(this).remove();
   });
}

// Create the map then make 'displayDirections' request
$('#page-map').live("pageinit", function() {
    $('#map_canvas').gmap({'center' : mapdata.destination })
    .bind('init', function() {
        $('.refresh').trigger('tap');        
    });
});

//Create the fullscreen map, request display of directions
var toggleval = true; // used for test case: static locations
$('.refresh').live("tap", function() {

            // START: Tracking location with test lat/long coordinates
            // Toggle between two origins to test refresh, force new route to be calculated
            var position = {};
            if (toggleval) {
                toggleval = false;
                position = { coords: { latitude: -31.63858509100513, longitude: -60.71152210235596 } }; // Suipache y Urquiza
            } else {
                toggleval = true;
                position = { coords: { latitude: -31.633140918256558, longitude: -60.70195198059082 } }; // Mariano Comas y 25
            }
            $('#map_canvas').gmap('displayDirections',
                { 'origin' : new google.maps.LatLng(position.coords.latitude, position.coords.longitude),
                  'destination' : mapdata.destination,
                  'travelMode' : google.maps.DirectionsTravelMode.DRIVING },
                  { 'panel' : document.getElementById('dir_panel') },
                    function (result, status) {
                        if (status === 'OK') {
                            var center = result.routes[0].bounds.getCenter();
                            $('#map_canvas').gmap('option', 'center', center);
                            $('#map_canvas').gmap('refresh');
                        } else {
                            alert('No se encuentra la ruta');
                        }
                    });
            // END: Tracking location with test lat/long coordinates
    $(this).removeClass($.mobile.activeBtnClass);
    return false;
});


