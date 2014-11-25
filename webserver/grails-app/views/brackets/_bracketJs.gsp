<link type="text/css" href="${resource(dir: 'css', file: 'jquery.treetable.css')}" />
<script src="${resource(dir: 'js', file: 'jquery.treetable.js')}"></script>

<script type="text/javascript">
	var url = "${reloadUri}";
	$(document).ready(function() {
         $('#tournament').on('change', function() {
             var id = $(this).val();
             if (id != "null") {
            	 var goTo="?tournamentId="+$(this).val();
                 window.location.href = goTo;	
             }
 
         });

         $("#participants").treetable();
         $("#poomseParticipants").treetable();
	});

	function reloadSettings(tournamentId) {
		var uri = "/tournament/ajaxSettings";
		var params = "tournamentId="+tournamentId;
		ajaxCall(uri,params, sucess, failure);
	}

	function reloadCombatWeight(combatCategoryId) {
		var uri = "/combatWeight/ajaxList";
		var params = "combatCategoryId="+combatCategoryId;
		ajaxCall(uri,params, sucessCombatWeight, failure);
	}

	function sucess(data) {
		updateSelect("belt",data.belts);
		updateSelect("combatCategory",data.combat_categories);
		updateSelect("formCategory",data.poomse_categories);
		clearSelection("combatWeight");
	}

	function sucessCombatWeight(data) {
		updateSelect("combatWeight",data);
	}

	function failure(data) {
		alert(JSON.stringify(data));
	}

	function clearSelection(name) {
		var select = document.getElementById(name);
		select.options.length = 0; // clear out existing items
		select.options.add(new Option("--select item--", "null"));
	}

	function updateSelect(name, data) {
		clearSelection(name);
		var select = document.getElementById(name);
<%--		select.options.length = 0; // clear out existing items--%>
<%--		select.options.add(new Option("--select item--", "null"));--%>
		for (var i in data) {
		      select.options.add(new Option(data[i].description, data[i].id));
		}
		
<%--		var select = $(name);--%>
<%--	    select.empty();--%>
<%--	    $.each(data, function(index, value) {          --%>
<%--	        select.append(--%>
<%--	                $('<option></option>').val(value.id).html(value.name)--%>
<%--	            );--%>
<%--	    });--%>
<%--	    $('#'+name).show();--%>
	}

	function ajaxCall(uri, data, success, error) {
		$.ajax({
	        type:"GET",
	        url : uri,
	        data : data,
	        async: false,
	        success : function(response) {
	            //data = response;
	            success(response);
	            //return response;
	        },
	        error: function(response) {
	            //alert('Error occured');
	        	error(response);
	        }
	    });
	}

	
	
</script>