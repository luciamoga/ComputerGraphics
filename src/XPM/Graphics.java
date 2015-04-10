package XPM;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Graphics {
	private XPM xpm;

	public Graphics(XPM xpm) {
		super();
		this.xpm = xpm;
	}

	public void generateRGradientPixels(int startR, int endR,
			int startingPoint, int endPoint, int width) {
		int step = (endR - startR) / (endPoint - startingPoint);
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

	public void createXPMFromPS(String psFileName, int width, int height,
			String outputXPMFileName) {
		XPM xpm = new XPM(height, width, 1);
		xpm.blankCanvas();
		Color black = new Color(0, 0, 0);
		try {
			BufferedReader br = new BufferedReader(new FileReader(psFileName));
			String line;
			while ((line = br.readLine()) != null) {
				String[] lineComponents = line.split(" ");
				if (lineComponents[lineComponents.length - 1].equals("Line")) {
					int xA = width - Integer.parseInt(lineComponents[0]);
					int yA = height - Integer.parseInt(lineComponents[1]);
					int xB = width - Integer.parseInt(lineComponents[2]);
					int yB = height - Integer.parseInt(lineComponents[3]);
					xpm.drawLine(xA, yA, xB, yB, black);
				}

			}
			br.close();
			xpm.generateData();
			xpm.generateXPMFile(outputXPMFileName);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public XPM getXpm() {
		return xpm;
	}

	public void setXpm(XPM xpm) {
		this.xpm = xpm;
	}

}
