package interfacegui;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ImageButton extends JButton{

	public ImageButton(String path) throws IOException{
		super(new ImageIcon(ImageIO.read(new File(path))));
		setBorder(BorderFactory.createEmptyBorder());
		setContentAreaFilled(false);
	}	
}
