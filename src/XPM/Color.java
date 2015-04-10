package XPM;


public class Color {

	private int r, g, b; /* the red, green and blue components */
	private String identifier; /* pointer to the character(s) combination */

	public Color(int r, int g, int b, String identifier) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.identifier = identifier;
	}

	public Color(int r, int g, int b) {
		super();
		this.r = r;
		this.g = g;
		this.b = b;
	}

	public String getHexadecimalCode() {
		String hex = String.format("#%02x%02x%02x", r, g, b);
		return hex;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public boolean equals(Object o) {
		if (!(o instanceof Color)) {
			return false;
		}
		Color comparedColor = (Color) o;
		if (comparedColor.r != r || comparedColor.b != b
				|| comparedColor.g != g) {
			return false;
		}
		return true;
	}
}
