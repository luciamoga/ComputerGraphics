package Assignment1;
import XPM.Color;
import XPM.XPM;

public class Main1 {
	static public XPM xpm;

	public static void generateRGradientPixels(int startR, int endR,
			int startingPoint, int endPoint, int width) {
		int step = (endR-startR) / (endPoint - startingPoint);
		char charPattern = '0';

		while (startR < endR && startingPoint < endPoint) {
			Color color = new Color(startR, 0, 0, String.valueOf(charPattern));
			for (int i = 0; i < xpm.getHeight(); i++) {
				xpm.putXPMpixel(startingPoint, i, color);
			}
			charPattern++;
			startingPoint++;
			startR += step;
		}
		xpm.generateData();
	}

	public static void main(String[] args) {
		int height = 50, width = 50, cpp = 1;
		xpm = new XPM(height, width, cpp);
		generateRGradientPixels(0, 255, 0, 50, width);
		//generateRGradientPixels(128, 250, 25, 49, width / 2);
		xpm.generateXPMFile("fisiernou");
		System.out.println("Done");
	}

}
