package task8.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImageUtils {

    public static File getImageFile(String photoName) {
        String path = ImageUtils.class.getClassLoader().getResource(photoName).getFile();
        return new File(path);
    }

    public static BufferedImage downloadImage(String href) throws IOException {
        URL url = new URL(href);
        return ImageIO.read(url);
    }

    public static BufferedImage getBufferedImage(String photoName) {
        BufferedImage read = null;
        try {
            read = ImageIO.read(getImageFile(photoName));
            return read;
        } catch (IOException e) {
            Logger.error("failed to get BufferedImage");
        }
        return read;
    }
}

