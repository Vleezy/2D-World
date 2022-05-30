package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Key extends SuperObject {

	public OBJ_Key() {

		name = "Key"; // name of key object
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png")); // key image

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
