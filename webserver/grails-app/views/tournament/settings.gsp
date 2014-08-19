<%@ page import="tournament.manager.Tournament"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main" />
<title>Tournament Manager</title>
</head>
<body>

	<g:render template="/home/templates/nav" />

<div id="default">
            <ul>
                <li><a href="#tab1">Belts</a></li>
                <li><a href="#tab2">Forms</a></li>
                <li><a href="#tab3">Combat</a></li>
            </ul>
            <div>
                <div id="tab1">
                    <g:render template="/belt/list" />
                    <button id="btn-show" class="btn">Add</button>
                </div>
                <div id="tab2">
                    <g:render template="/formCategory/list" />
                </div>
                <div id="tab3">
                    <g:render template="/combatCategory/list" />
                </div>
            </div>
</div>
<div id="dlg" title="Add Belt">
	<g:render template="/belt/form" />
</div>

	<!-- Work -->
	<div class="wrapper style1">
		<article id="tournaments">



<%--			<div id="accordion-1">--%>
<%--				<h3>Belts</h3>--%>
<%--				<div>--%>
<%--					<p>--%>
<%--						<g:render template="/belt/list" />--%>
<%--						<button id="btn-show" class="btn" onClick="openDialog('Belt','/tournament-manager/belt/create')">Add</button>--%>
<%--						--%>
<%--						<div id="dlg" title="Godfather I">  --%>
<%--						    <p>The story begins as Don Vito Corleone, the head of a New York Mafia family, oversees his daughter's wedding.   --%>
<%--						    His beloved son Michael has just come home from the war, but does not intend to become part of his father's business.   --%>
<%--						    Through Michael's life the nature of the family business becomes clear. The business of the family is just like the head of the family,   --%>
<%--						    kind and benevolent to those who give respect,   --%>
<%--						    but given to ruthless violence whenever anything stands against the good of the family.</p>  --%>
<%--						</div> --%>
<%--						<button id="btn-show" class="btn">Add</button>--%>
<%--					</p>--%>
<%--				</div>--%>
<%--				<h3>Forms</h3>--%>
<%--				<div>--%>
<%--					<p>--%>
<%--						<g:render template="/formCategory/list" />--%>
<%--					</p>--%>
<%--				</div>--%>
<%--				<h3>Combat</h3>--%>
<%--				<div>--%>
<%--					<p>--%>
<%--						<g:render template="/combatCategory/list" />--%>
<%--					</p>--%>
<%----%>
<%--				</div>--%>
<%--			</div>--%>


			<div class="row double">
				<div class="12u">
					<ul class="actions">

					</ul>
				</div>
			</div>
		</article>
	</div>
	<g:render template="/home/templates/footer" />
	<script>
		$(function() {
			//$("#accordion-1").accordion();
			$("#default").puitabview();
		});


		$(function() {
		    $('#dlg').puidialog({
		        showEffect: 'fade',
		        hideEffect: 'fade',
		        minimizable: true,
		        maximizable: true,
		        modal: true,
		        buttons: [{
		                text: 'Yes',
		                icon: 'ui-icon-check',
		                click: function() {
		                    $('#dlg').puidialog('hide');
		                }
		            },
		            {
		                text: 'No',
		                icon: 'ui-icon-close',
		                click: function() {
		                    $('#dlg').puidialog('hide');
		                }
		            }
		        ]
		    });

		    $('#btn-show').puibutton({
		        icon: 'ui-icon-arrow-4-diag',
		        click: function() {
		            $('#dlg').puidialog('show');
		        }
		    });
		});
		

<%--		    function openDialog(title,url) {--%>
<%--		        $('.opened-dialogs').dialog("close");--%>
<%----%>
<%--		        $('<div class="opened-dialogs">').html('loading...').dialog({--%>
<%--		            position:  ['center',20],--%>
<%--		            open: function () {--%>
<%--		                $(this).load(url);--%>
<%----%>
<%--		            },--%>
<%--		            close: function(event, ui) {--%>
<%--		                    $(this).remove();--%>
<%--		            },--%>
<%----%>
<%--		            title: title,--%>
<%--		            minWidth: 600            --%>
<%--		        });--%>
<%----%>
<%--		        return false;--%>
<%--		    }--%>
		
	</script>
</body>
</html>
