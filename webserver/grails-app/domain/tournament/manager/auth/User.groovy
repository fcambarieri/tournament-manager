package tournament.manager.auth

import java.util.Date;
import javax.persistence.Transient

class User {

	transient springSecurityService

	String username
	String password
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	
	String firstName
	String lastName
	String email
	@Transient String confirmPassword
	String status
	String verificationCode
	Date dateCreated
	Date lastUpdated
	
	static transients = ['springSecurityService']

	static constraints = {
		username(blank: false, unique: true)
		password(blank:false)
		email(nullable:false, blank:false, size: 5..30, unique: true)
		firstName(nullable:false, blank:false, size: 2..30)
		lastName(nullable:false, blank:false, size: 2..30)
		confirmPassword(blankblank:false, validate : {val, obj ->
			if (confirmPassword != password) {
				return "password is not equal"
			}
		})
		status(nullable:false, blank:false, size: 3..60)
		verificationCode(nullable:false, blank:false, size: 10..60)
	}

	static mapping = {
		password column: '`password`'
		table 'tm_users'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role }
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}
}
