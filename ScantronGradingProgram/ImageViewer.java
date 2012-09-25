import javax.media.jai.PlanarImage;
import com.sun.media.jai.codec.ByteArraySeekableStream;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecoder;
import com.sun.media.jai.codec.SeekableStream;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.awt.Image;
import java.awt.image.*;

import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class ImageViewer  {

    static Image load(byte[] data) throws Exception{
        Image image = null;
        SeekableStream stream = new ByteArraySeekableStream(data);
        String[] names = ImageCodec.getDecoderNames(stream);
        ImageDecoder dec = 
        ImageCodec.createImageDecoder(names[0], stream, null);
        RenderedImage im = dec.decodeAsRenderedImage();
        image = PlanarImage.wrapRenderedImage(im).getAsBufferedImage();
        return image;
    }
    
    static BufferedImage getImage(String path) throws Exception{
        FileInputStream in = new FileInputStream(path);
        FileChannel channel = in.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate((int)channel.size());
        channel.read(buffer);
        BufferedImage image = (BufferedImage) load(buffer.array());
        return image;
    }
    
    static void drawImage(BufferedImage img) {
        JOptionPane.showMessageDialog(null,new JLabel(new ImageIcon(img)));
    }
}