import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

class SpriteImage {
    private BufferedImage image;
    public int tileWidth = 1;
    public int tileHeight = 1;
    
    public SpriteImage(Image sourceImage, int initialTileWidth, int initialTileHeight) {
        image = toBuffered(sourceImage);
        
        tileWidth = initialTileWidth;
        tileHeight = initialTileHeight;
    }
    
    private BufferedImage toBuffered(Image input) {
        BufferedImage converted = new BufferedImage(input.getWidth(null), input.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = converted.createGraphics();
        g.drawImage(input, 0, 0, null);
        g.dispose();
        
        return converted;
    }
    
    public BufferedImage getAbsolute(int x, int y, int width, int height) {
        return image.getSubimage(x, y, width, height);
    }
    
    public BufferedImage getScaled(BufferedImage input, int width, int height) {
        return toBuffered(input.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH));
    }
    
    public BufferedImage get(int tileX, int tileY, int tileRelativeWidth, int tileRelativeHeight) {
        return getAbsolute(tileWidth * tileX, tileHeight * tileY, tileWidth * tileRelativeWidth, tileHeight * tileRelativeHeight);
    }
}