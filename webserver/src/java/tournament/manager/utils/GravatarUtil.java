package tournament.manager.utils;

public class GravatarUtil {

	public static String gravatarImage(String email) {
		String sEmail = email.toLowerCase();
		String hash = MD5Util.md5Hex(sEmail);
		String uri = "http://www.gravatar.com/avatar/".concat(hash);
		return uri;
	}
}
