package Assignment2;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import XPM.Color;
import XPM.XPM;

public class Main2 {
	public static void generateXPM(String psFileName, int width, int height,
			String outputXPMFileName) {
		XPM xpm = new XPM(height, width, 1);
		xpm.blankCanvas();
		Color black = new Color(0, 0, 0);
		try {
			BufferedReader br = new BufferedReader(new FileReader(psFileName));
			String line;
			while ((line = br.readLine()) != null) {
				String[] lineComponents = line.split(" ");
				if (lineComponents[lineComponents.length-1].equals("Line")) {
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

	public static void main(String[] args) {
		generateXPM("test.ps", 200, 200, "generatedFromPS");

	}

}
