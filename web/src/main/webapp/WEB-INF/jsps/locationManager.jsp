<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html data-ng-app>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Location Manager</title>


<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css' />
<link href='http://fonts.googleapis.com/css?family=Roboto:400,500,700' rel='stylesheet' type='text/css' />
<link href='http://fonts.googleapis.com/css?family=PT+Sans:700,400' rel='stylesheet' type='text/css' />
<link href='http://fonts.googleapis.com/css?family=Pontano+Sans' rel='stylesheet' type='text/css' />
<link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600' rel='stylesheet' type='text/css' />

<!-- Styles -->
<link rel="stylesheet" href="http://wordpress.techinflo.com/eSwaraj/backend/css/bootstrap.min.css" type="text/css" /><!-- Bootstrap -->
<link rel="stylesheet" href="http://wordpress.techinflo.com/eSwaraj/backend/font-awesome-4.0.3/css/font-awesome.css" type="text/css" /><!-- Font Awesome -->

<link rel="stylesheet" type="http://wordpress.techinflo.com/eSwaraj/backend/text/css" media="all" href="css/daterangepicker-bs3.css" /><!-- Date Range Picker -->
<link rel="stylesheet" href="http://wordpress.techinflo.com/eSwaraj/backend/css/style.css" type="text/css" /><!-- Style -->	
<link rel="stylesheet" href="http://wordpress.techinflo.com/eSwaraj/backend/css/responsive.css" type="text/css" /><!-- Responsive -->	



 
<!-- Script -->
<script src="http://wordpress.techinflo.com/eSwaraj/backend/js/jquery-1.10.2.js"></script><!-- Jquery -->
<script type="text/javascript" src="http://wordpress.techinflo.com/eSwaraj/backend/js/bootstrap.min.js"></script><!-- Bootstrap -->
<script type="text/javascript" src="http://wordpress.techinflo.com/eSwaraj/backend/js/script.js"></script><!-- Script -->
<script src="http://wordpress.techinflo.com/eSwaraj/backend/js/enscroll-0.5.2.min.js"></script> <!-- Custom Scroll bar -->
<script src="http://wordpress.techinflo.com/eSwaraj/backend/js/moment.js"></script> <!-- Date Range Picker -->
<script src="http://wordpress.techinflo.com/eSwaraj/backend/js/daterangepicker.js"></script><!-- Date Range Picker -->
  <!-- 2 load the theme CSS file -->
  <link rel="stylesheet" href="http://wordpress.techinflo.com/eSwaraj/backend/dist/themes/default/style.min.css" />
   <!-- 4 include the jQuery library -->

  <!-- 5 include the minified jstree source -->
  <script src="http://wordpress.techinflo.com/eSwaraj/backend/dist/jstree.min.js"></script>
  <script src="http://wordpress.techinflo.com/eSwaraj/backend/js/angular.js"></script>
 <style>
      html, body, #map-canvas {
        height: 500px;
        margin: 0px;
        padding: 0px
      }
    </style>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
    <script>
function initialize(a, b) {
  var location = new google.maps.LatLng(a, b);
  var mapOptions = {
    zoom: 13,
    center: location
  }

  var map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
	
	
	var markersArray = [];
	function placeMarker(locations) {
            // first remove all markers if there are any
            deleteOverlays();

            var marker = new google.maps.Marker({
                position: locations, 
                map: map
            });

            // add marker in markers array
            markersArray.push(marker);

            //map.setCenter(location);
        }
	function deleteOverlays() {
            if (markersArray) {
                for (i in markersArray) {
                    markersArray[i].setMap(null);
                }
            markersArray.length = 0;
            }
        }
		
  var ctaLayer = new google.maps.KmlLayer({
    url: 'http://wordpress.techinflo.com/eSwaraj/backend/KML/03052014-0172ksv.kml'
   });
  ctaLayer.setMap(map);
}



    </script>


</head>
<body>
		<div class="responsive-menu">
			<div class="responsive-menu-dropdown blue">
				<a title="" class="blue">MENU <i class="fa fa-align-justify"></i></a>
			</div>
			
	</div>
	<header>
		<div class="logo">
			<img src="images/logo.png" alt="" />
		</div>
			
		
		
	</header><!-- Header -->

	<div class="menu">
		<div class="menu-profile" id="intro3">
			<img src="images/logo.jpg" alt="" />
			<span><i class="fa fa-plus"></i></span>
			<div class="menu-profile-hover">
				<h1>India</h1>
				<p><i class="fa fa-map-marker"></i>eSwaraj</p>
				<a href="index.html" title=""><i class="fa fa-power-off"></i></a>
				<div class="menu-profile-btns">
							
					
				</div>
			</div>
		</div>
		<ul>
			<li id="intro4"><a href="#" title=""><i class="fa fa-desktop"></i>Location</a>
				<ul>
					<li><a href="location_manager.html" title="">Location Manager</a></li>
				</ul>			
			</li>
			
		</ul>
	</div><!-- Right Menu -->

	<div class="wrapper">
		<div class="container">
			<div class="col-md-6">
				<div class="heading-sec" id="intro6">
					<h1>Profile<i>India</i></h1>
				</div>
			</div>
		
			
		<div class="row">
			<div class="col-md-12 profile-margin">
			<div class="col-md-4">
			<h1>Location Manager</h1>
			
			<script type="text/javascript">
 $(document).ready(function(){
$('#js_tree').jstree({ 'core' : {
	"check_callback" : true,
    'data' : [
       {
				   'text'    	:'India',
				   'id'  	 	:'IN',			
				   'icon' 	 	:'country',			    
				   'children'   :[{'text':'Uttar Pradesh','id':'UP','li_attr':{'title':'Uttar Pradesh','info':'About UP','population':'10M UP','area':'34MSQ UP','child_type':'district','center_lat':'26.7905172094698','center_long':'80.9003376960754'}},{'text':'Delhi','id':'DEL','li_attr':{'title':'Delhi','info':'About Delhi','population':'1M DEl','area':'4MSQ DEL','child_type':'district','center_lat':'28.647210004919998','center_long':'77.22633361816406'}}],
				   'li_attr':{'title':'India','population':'10M','area':'134MSQ','child_type':'state','center_lat':'28.647210004919998','center_long':'77.22633361816406'}  
				   // attributes for the generated LI node
				   
				 }
    ],
	"plugins" : [ "types","contextmenu","html_data", "ui"]}}).bind("select_node.jstree", function (e, data) {  
		var parent = $('#js_tree').jstree('get_selected');
		//alert("node_clicked "+parent);
			
		$('#node_title').html($('#'+parent).attr('title'));
		$('#selected_node_title').val($('#'+parent).attr('title'));
		
		//$('#node_info').html($('#'+parent).attr('info'));
		$('#selected_node_title').val($('#'+parent).attr('title'));
		
		$('#node_area').val($('#'+parent).attr('area'));
		$('#selected_node_area').val($('#'+parent).attr('area'));
		
		$('#node_pop').val($('#'+parent).attr('population'));
		$('#selected_node_pop').val($('#'+parent).attr('population'));
		
		$('#node_latd').val($('#'+parent).attr('center_lat'));
		$('#selected_node_latd').val($('#'+parent).attr('center_lat'));
		
		$('#node_long').val($('#'+parent).attr('center_long'));
		$('#selected_node_long').val($('#'+parent).attr('center_long'));
		

		google.maps.event.addDomListener(window, 'load', initialize($('#'+parent).attr('center_lat'), $('#'+parent).attr('center_long')));
		
		 google.maps.event.addListener(window, "load", function(event)
            {
                // place a marker
                placeMarker(event.locations);
				
				// display the lat/lng in your form's lat/lng fields
                $('#'+parent).attr('center_lat') = event.latLng.lat();
                $('#'+parent).attr('center_long') = event.latLng.lng();
			});
		
		
		$('#node_add_btn').html('Add '+$('#'+parent).attr('child_type'));
		
		if($("#"+parent+" ul" ).children().length == 0){
		
			//this need to be replaced by WEB API-1, json response expected as below 
			 var newNode = 
				 {
				   'text'    	:'India',
				   'id'  	 	:'IN',			
				   'icon' 	 	:'country',			    
				   'children'   :[{'text':'Uttar Pradesh','id':'UP','li_attr':{'title':'Uttar Pradesh','info':'About UP','population':'10M UP','area':'34MSQ UP','child_type':'district','center_lat':'26.7905172094698','center_long':'80.9003376960754'}},{'text':'Delhi','id':'DEL','li_attr':{'title':'Delhi','info':'About Delhi','population':'1M DEl','area':'4MSQ DEL','child_type':'district','center_lat':'28.647210004919998','center_long':'77.22633361816406'}}],
				   'li_attr':{'title':'India','population':'10M','area':'134MSQ','child_type':'state','center_lat':'28.647210004919998','center_long':'77.22633361816406'}  
				   // attributes for the generated LI node
				   
				 };
		     var sel = $('#js_tree').jstree(true).create_node(parent, newNode);
		}	
		
      })  
	  
});

function add_child_node(){

var selected_node =  $('#js_tree').jstree('get_selected');
var new_node = {'text':$("#new_node_title").val(),
'id':$("#new_node_code").val(),
'li_attr':{'title':$("#new_node_title").val(),'info':$("#new_node_info").val(),'population':$("#new_node_pop").val(),'area':$("#new_node_area").val(),'child_type':$("#new_node_child_type").val(),'center_lat':$("#new_node_latd").val(),'center_long':$("#new_node_long").val()}};
//alert("selected node" + selected_node);
var sel = $('#js_tree').jstree(true).create_node(selected_node, new_node);
}

function update_selected_node(new_node_title,new_node_info,new_node_area,new_node_pop,new_node_latd,new_node_long,new_node_add_btn){
var selected_node =  $('#js_tree').jstree('get_selected');

}


$(document).ready(function(){
	$("#edit_node").click(function() {
		$('#tab1 input').attr("disabled", false);
		$('#save_bttn').removeAttr('style');
	});	
});
</script>


  
 <!-- the tree is populated via ajax -->
			<div id="js_tree">
				
				
			</div>	
					
			</div>
			
				<div class="col-md-5">
					<a href="#" class="active"><h1 id="node_title">India</h1>	
					</a>
						<button id="edit_node">Edit Node</button>
							<div id="tab1">
								<table style="padding:10px;">
								<tr>
								<td>Area :</td>
								<td><input type="text" name="node_area" id="node_area" value="" disabled="disabled"></td>
								</tr>
								<tr>
								<td>Population</td>
								<td><input type="text" name="node_pop" id="node_pop" value="" disabled="disabled"></td>
								</tr>
								<tr>
								<td>Latitude</td>
								<td><input type="text" name="node_latd" id="node_latd" value="" disabled="disabled"></td>
								</tr>
								<tr>
								<td>Longitude</td>
								<td><input type="text" name="node_long" id="node_long" value="" disabled="disabled"></td>
								</tr>
								
								<tr>
								<td>Upload KML<input type="file" id="new_node_kml"/></td>
								<td id="save_bttn" style="display:none;"><a id="save_node_btn" class="btn blue"  href="#save_node_form" data-toggle="modal">Save</a></td>
								</tr>
								<tr><td><a id="node_add_btn" class="btn pink" href="#add-node" data-toggle="modal">Add Child Node</a></td></tr>
								</table>
								
							</div>
							
						
						
					
					
		<div aria-hidden="true" role="dialog"  class="modal fade" id="add-node" style="display: none;">
			<div class="modal-dialog" style="top:30px;">
				<div class="modal-content">
					<div class="modal-header blue">
					  <button aria-hidden="true" data-dismiss="modal" class="close" type="button">—</button>
					  <h4 class="modal-title">Add Child Node</h4>
					</div>
					<div class="modal-body">
					  <input type="text" id="new_node_title" placeholder="Node Title" />
					  <input type="text" id="new_node_code" placeholder="Code" />
					  <input type="text" id="new_node_info" placeholder="Info" />
					  <input type="text" id="new_node_area" placeholder="Area" />
					  <input type="text" id="new_node_pop" placeholder="Population" />
					  <input type="text" id="new_node_latd" placeholder="Latitude" />
					  <input type="text" id="new_node_long" placeholder="Longitude" />
					  <input type="text" id="new_node_child_type" placeholder="Child Type" />
					  <input type="text" placeholder="Centre lat-long" />
					  Upload KML<input type="file" id="new_node_kml"/>
					</div>
					 				  
					</div>
					<div class="modal-footer">
					  <button data-dismiss="modal" class="btn btn-default black" type="button">Close</button>
					  <button class="btn btn-primary blue" type="button" onclick="add_child_node();" >Save changes</button>
					</div>
				</div><!-- /.modal-content -->
			</div>
			<div class="">
				<div id="map-canvas"></div>	
			</div>
			
		</div>
			</div>
			
				
			</div>
		</div>
		
		
		</div><!-- Container -->
	</div><!-- Wrapper -->



</body>
</html>