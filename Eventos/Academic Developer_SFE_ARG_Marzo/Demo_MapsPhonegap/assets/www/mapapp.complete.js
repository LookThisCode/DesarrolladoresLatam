var mapdata = { destination: new google.maps.LatLng(59.3327881, 18.064488100000062) };

// Home page
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

//Create the map then make 'displayDirections' request
$('#page-map').live("pageinit", function() {
    $('#map_canvas').gmap({'center' : mapdata.destination })
    .bind('init', function() {
        $('.refresh').trigger('tap');        
    });
});

// Request display of directions, requires jquery.ui.map.services.js
var toggleval = true; // used for test case: static locations
$('.refresh').live("tap", function() {
    
            // START: Tracking location with device geolocation
/*            if ( navigator.geolocation ) { 
                fadingMsg('Using device geolocation to get current position.');
                navigator.geolocation.getCurrentPosition ( 
                    function(position) {
                        $('#map_canvas').gmap('displayDirections', 
                        { 'origin' : new google.maps.LatLng(position.coords.latitude, position.coords.longitude), 
                          'destination' : mapdata.destination, 'travelMode' : google.maps.DirectionsTravelMode.DRIVING},
                        { 'panel' : document.getElementById('dir_panel')},
                              function (result, status) {
                                  if (status === 'OK') {
                                      var center = result.routes[0].bounds.getCenter();
                                      $('#map_canvas').gmap('option', 'center', center);
                                      $('#map_canvas').gmap('refresh');
                                  } else {
                                    alert('Unable to get route');
                                  }
                              }
                           );         
                    }, 
                    function(){ 
                        alert('Unable to get location');
                        $.mobile.changePage($('#page-home'), {}); 
                    }); 
                } else {
                    alert('Unable to get location.');
                }            
*/            // END: Tracking location with device geolocation

            // START: Tracking location with test lat/long coordinates
            // Toggle between two origins to test refresh, force new route to be calculated
            var position = {};
            if (toggleval) {
                toggleval = false;
                position = { coords: { latitude: 57.6969943, longitude: 11.9865 } }; // Gothenburg
            } else {
                toggleval = true;
                position = { coords: { latitude: 58.5365967, longitude: 15.0373319 } }; // Motala
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
                            alert('Unable to get route');
                        }
                    }); 
            // END: Tracking location with test lat/long coordinates
    $(this).removeClass($.mobile.activeBtnClass);
    return false;
});

// Go to map page to see instruction detail (zoom) on map page
$('#dir_panel').live("tap", function() {
    $.mobile.changePage($('#page-map'), {});
});

// Briefly show hint on using instruction tap/zoom
$('#page-dir').live("pageshow", function() {
    fadingMsg("Tap any instruction<br/>to see details on map");
});

