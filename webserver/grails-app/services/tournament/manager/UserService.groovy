package tournament.manager

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import tournament.manager.auth.User

import grails.transaction.Transactional

@Transactional
class UserService {

    def login(String email, String password) {
		String encryptPsw = encryptPsw(password)
		def criteria = User.createCriteria()
		def user = criteria.get {
			eq("email", email)
			eq("password", encryptPsw)
		}
		return user
    }
	
	def encryptPsw(String psw) {
		return psw.encodeAsMD5Hex()	
	
//		String Key = "mybeautifulson";
//		byte[] KeyData = Key.getBytes();
//		SecretKeySpec KS = new SecretKeySpec(KeyData, "Blowfish");
//		Cipher cipher = Cipher.getInstance("Blowfish");
//		cipher.init(Cipher.ENCRYPT_MODE, KS);
//		byte[] encrypted = cipher.doFinal(psw.getBytes());
////		return new String(encrypted)
	}
	
	def createUser(params) {
		User user = new User()
		user.username = params.username
		user.email = params.email
		user.password = params.password
		user.confirmPassword = params.confirmPassword
		user.firstName = params.firstName
		user.lastName = params.lastName
		user.status = "pending_confirmation"
		user.verificationCode = UUID.randomUUID().toString()
		if (!user.save(flush:true)){
			log.error "saving user ${user.email}", user.errors
		}
	}
}
