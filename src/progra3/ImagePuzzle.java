package progra3;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImagePuzzle {

	private BufferedImage[] imageParts;

	public ImagePuzzle(String imagePath, int fil, int col) {

		try {
			//cargar imagen
			InputStream imgStream = getClass().getResourceAsStream(imagePath);
			BufferedImage imagen = ImageIO.read(imgStream);

			//calcula el ancho y el alto de la imagen

			int ancho = imagen.getWidth() / col;
			int alto = imagen.getHeight() / fil;

			imageParts = new BufferedImage[fil * col];
			int part = 0;

			//Divide la imagen en partes
			for(int i = 0; i < fil; i++) {
				for(int j = 0; j < col; j++) {
					imageParts[part] = imagen.getSubimage(j*ancho, i*alto, ancho, alto);
					part++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public BufferedImage[] getImageParts() {
		return imageParts;
	}

}
