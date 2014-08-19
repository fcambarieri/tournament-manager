modules = {
    application {
        resource url:'js/application.js'
    }
	
	commonCss {
		resource url:"css/chico.css"
//		resource url:"css/main.css"
//		resource url:"css/skel.css" 
//		resource url:"css/style.css"
//		resource url:"css/errors.css"
//		resource url:"css/style-desktop.css"
		resource url:"css/loginDropDown.css"
		resource url:'css/jquery-ui.min.css'
//		resource url:"css/jquery-ui.min.css"
	}
	
	commonJs {
//		resource url:'js/jquery.min.js'
		resource url:'js/jquery.scrolly.min.js'
//		resource url:'js/jquery-ui.min.js'
//		resource url:'js/skel.min.js'
//		resource url:'js/init.js'
		resource url:'js/primeui-1.1-min.js'
//		resource url:'js/chico.min.js'
	}
	
	
	tournamentJs {
		dependsOn 'commonJs'
		resource url:'js/tournament.js'
	}
	
	primeJs {
//		resource url:'js/jquery.min.js'
//		resource url:'js/jquery-ui.min.js'
//		resource url:'js/primeui-1.1-min.js'
	}
	
	primeCss {
//		resource url:'css/jquery-ui.min.css'
//		resource url:'css/primeui-1.1-min.css'
	}
}