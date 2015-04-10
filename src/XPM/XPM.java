package XPM;


import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class XPM {
	private int width; /* width in pixels */
	private int height;
	private int cpp; /* number of characters per pixel */
	private int ncolors; /* how many colors we'll be using */
	private ArrayList<Color> colors; /* colors array */
	private ArrayList<Pixel> pixels; /* the area that contains the image, */
	private Color[][] data;

	/* width x height locations for */
	/* indices into colta */

	public XPM(int width, int height, int cpp) {
		super();
		this.width = width;
		this.height = height;
		this.cpp = cpp;
		colors = new ArrayList<Color>();
		pixels = new ArrayList<Pixel>();
		data = new Color[width][height];
	}

	/*
	 * putXPMpixel - writes a pixel at (x,y) in an XPM image It's color will be
	 * the one in the color table
	 */
	public void putXPMpixel(int x, int y, Color color) {
		Pixel p = new Pixel(x, y, color);
		pixels.add(p);
		if (!colors.contains(color)) {
			if (color.getIdentifier() == null) {
				color.setIdentifier(generateIdentifier());
			}
			colors.add(color);
		}

	}

	private String generateIdentifier() {
		int leftLimit = 64;
		int rightLimit = 127;
		int targetStringLength = cpp;
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit
					+ (int) (new Random().nextFloat() * (rightLimit - leftLimit));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();
		return generatedString;
	}

	public void generateData() {

		for (Pixel p : pixels) {
			data[p.getX()][p.getY()] = p.getColor();
		}
		ncolors = colors.size();

	}

	/*
	 * setXPMcolor - fills an element into the colors array ("*colta" from the
	 * XPM structure): red, green, and blue values, as well as the associated
	 * string
	 */
	public void addColor(Color color) {
		colors.add(color);
	}

	public void generateXPMFile(String filename) {
		PrintWriter writer;
		try {
			writer = new PrintWriter(filename + ".xpm");

			String fileContent = "/* XPM */\n" + "static char *egc[] = {\n"
					+ "/* width,height,nrcolors,charsperpixel */\n";
			fileContent += "\" " + width + " " + height + " " + colors.size()
					+ " " + cpp + " \",\n";

			fileContent += "/* colors #RRGGBB */\n";
			for (Color color : colors) {
				fileContent += "\"" + color.getIdentifier() + " c "
						+ color.getHexadecimalCode() + "\",\n";
			}
			fileContent += "/* pixels */\n";
			for (int i = 0; i < height; i++) {
				fileContent += "\"";
				for (int j = 0; j < width; j++) {
					fileContent += data[j][i].getIdentifier();
				}
				fileContent += "\",\n";
			}
			fileContent += " };";
			writer.println(fileContent);
			writer.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getNcolors() {
		return ncolors;
	}

	public void blankCanvas() {
		Color white = new Color(255, 255, 255);

		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++) {
				this.putXPMpixel(i, j, white);
			}
	}

	public void drawLine(int xA, int yA, int xB, int yB, Color color) {
		int width = xB - xA;
		int height = yB - yA;
		int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0;
		if (width < 0)
			dx1 = -1;
		else if (width > 0)
			dx1 = 1;
		if (height < 0)
			dy1 = -1;
		else if (height > 0)
			dy1 = 1;
		if (width < 0)
			dx2 = -1;
		else if (width > 0)
			dx2 = 1;
		int l = Math.abs(width);
		int s = Math.abs(height);
		if (!(l > s)) {
			l = Math.abs(height);
			s = Math.abs(width);
			if (height < 0)
				dy2 = -1;
			else if (height > 0)
				dy2 = 1;
			dx2 = 0;
		}
		int num = l >> 1;
		for (int i = 0; i <= l; i++) {
			putXPMpixel(xA, yA, color);
			num += s;
			if (!(num < l)) {
				num -= l;
				xA += dx1;
				yA += dy1;
			} else {
				xA += dx2;
				yA += dy2;
			}
		}
	}

}
