import tournament.manager.auth.Role
import tournament.manager.auth.User
import tournament.manager.auth.UserRole

class BootStrap {

    def init = { servletContext ->
		["ROLE_ADMIN","ROLE_USER"].each { name ->
			if (Role.findByAuthority(name) == null) {
				new Role(authority: name).save(flush: true, failOnError:true)
			}
		}
		
		if (User.findByEmail("fcambarieri@gmail.com") == null) {
			User test = new User(username:"fer",password:"pass", email:"fcambarieri@gmail.com",enabled:true,
				accountExpired : false , accountLocked : false ,passwordExpired : false, firstName:"fer",lastName:"fer", status:"pending",verificationCode:"verificationCode", confirmPassword:"pass")
			test.save(flush: true, failOnError:true)
			UserRole.create(test, Role.findByAuthority("ROLE_USER"), true)
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
