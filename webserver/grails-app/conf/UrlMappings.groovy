class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index", controller:"home")
        "500"(view:'/error')
//		"/auth/login"(view:"/auth/login")
		
//		"/login/$action?"(controller: "tmLogin")
//		"/logout/$action?"(controller: "logout")
		
		"/combat_category/$action?"(controller: "combatCategory")
		"/combat_weigth/$action?"(controller: "combatWeigth")
		"/form_category/$action?"(controller: "cormCategory")
		
		
//		"/$controller/css/images/$name" {
//			controller = "resource"
//			action = "img"
//		}
//		
//		"/$controller/css/$name" {
//			controller = "resource"
//			action = "css"
//		}
//		
//		"/$controller/js/$name" {
//			controller = "resource"
//			action = "js"
//		}
//		
		"/$controller/img/$name" {
			controller = "resource"
			action = "img"
		}
//		
//		"/$controller/images/$name" {
//			controller = "resource"
//			action = "img"
//		}
//		/*************/
//		"/css/$name" {
//			controller = "resource"
//			action = "css"
//		}
//		
//		"/js/$name" {
//			controller = "resource"
//			action = "js"
//		}
//		
		"/img/$name" {
			controller = "resource"
			action = "img"
		}
//		
//		"/images/$name" {
//			controller = "resource"
//			action = "img"
//		}
//		
//		name resource: "/resource/$action/$name" {
//			controller = 'resource'
//			action = action
//		}
		
	}
	
	
}
