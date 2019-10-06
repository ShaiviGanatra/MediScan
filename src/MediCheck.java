import java.io.File; 

import net.sourceforge.tess4j.Tesseract; 
import net.sourceforge.tess4j.TesseractException; 

public class MediCheck { 
	public static void main(String[] args) 
	{ 
		Tesseract tesseract = new Tesseract(); 
		try { 

			tesseract.setDatapath("C:\\Users\\New\\Desktop\\KJSCE HACK 4.0\\Tess4J"); 

			// the path of your tess data folder 
			// inside the extracted file 
			String text 
				= tesseract.doOCR(new File("C:\\Users\\New\\Desktop\\KJSCE HACK 4.0\\domstalsmall.jpg")); 

			// path of your image file 
			System.out.print(text); 
		} 
		catch (TesseractException e) { 
			e.printStackTrace(); 
		} 
	}
}
