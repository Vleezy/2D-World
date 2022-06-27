package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Boots extends SuperObject{

	public OBJ_Boots() {

		name = "Boots"; // name of key object
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png")); // boots image

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
	}
}
