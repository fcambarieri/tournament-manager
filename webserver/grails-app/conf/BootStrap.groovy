import tournament.manager.auth.Role

class BootStrap {

    def init = { servletContext ->
		["ROLE_ADMIN","ROLE_USER"].each { name ->
			if (Role.findByAuthority(name) == null) {
				new Role(authority: name).save(flush: true, failOnError:true)
			}
		}
//		if (Role.findByAuthority('ROLE_ADMIN') == null) {
//			new Role(authority: 'ROLE_ADMIN').save(flush: true)
//		}
//		if (Role.findByAuthority('ROLE_USER') == null) {
//			new Role(authority: 'ROLE_USER').save(flush: true)
//		}
    }
    def destroy = {
    }
}
