package task8.project.model;

import task8.utils.ConfigFileReader;
import task8.utils.ImageUtils;
import task8.utils.Logger;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Image {

    private final static int VALUE_WHITE_COLOR = 765;
    private File fileImage;
    private BufferedImage bufferedImage;

    public Image(String photoName) {
        fileImage = ImageUtils.getImageFile(photoName);
        bufferedImage = ImageUtils.getBufferedImage(photoName);
    }

    public Image(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public File getFileImage() {
        return fileImage;
    }

    @Override
    public boolean equals(Object obj) {
        Image other = (Image) obj;
        if (bufferedImage.getHeight() != other.bufferedImage.getHeight()) return false;
        if (bufferedImage.getWidth() != other.bufferedImage.getWidth()) return false;
        long amountPixel = bufferedImage.getHeight() * bufferedImage.getWidth();
        long amountColorDifferences = 0;
        for (int x = 0; x < bufferedImage.getWidth(); x++) {
            for (int y = 0; y < bufferedImage.getHeight(); y++) {
                Color color1 = new Color(bufferedImage.getRGB(x, y));
                Color color2 = new Color(other.bufferedImage.getRGB(x, y));
                amountColorDifferences += Math.abs(color1.getBlue() - color2.getBlue());
                amountColorDifferences += Math.abs(color1.getGreen() - color2.getGreen());
                amountColorDifferences += Math.abs(color1.getRed() - color2.getRed());
            }
        }
        long whiteColor = VALUE_WHITE_COLOR * amountPixel;
        double result = (whiteColor - amountColorDifferences) * 100.0 / (whiteColor);
        Logger.info("image match on: " + result + "%");
        return result > ConfigFileReader.getPhotoIdentityPercentage();
    }
}

