package tournament.manager.web

public class Minimizer {
	
	public String minimize(String input) {

		StringBuilder output = new StringBuilder(input.length());

		int inputLength = input.length();

		boolean skip = false;

		int outLen = 0;

		for (int i = 0; i < inputLength - 1 ; i++) {
			skip = false;
			char c = input.charAt(i);
			char n = input.charAt(i+1);

			// los \n \t \r se tiran a la basura
			// if(c == (byte)'\t' || c == (byte)'\n' || c == (byte)'\r')
			// continue;
			if (c == '\n' || c == '\r') {
				skip = true;
			}

			if ((c == ' ' && n == '\r') || (c == ' ' && n == '\n')
			|| (c == '\t' && n == '\n') || (c == '\t' && n == '\n')
			|| ((c == ' ' || c == '\t') && (n == ' ' || n == '\t'))) {
				skip = true;
			}

			if (!skip) {
				output.append(c);
				outLen++;
			}
		}

		char lastChar = input.charAt(inputLength - 1);
		if (lastChar != ' ' && lastChar != '\n'
		&& lastChar != '\r'
		&& lastChar != '\t') {
			output.append(lastChar);
			outLen++;
		}

		return output.toString();
	}
}
