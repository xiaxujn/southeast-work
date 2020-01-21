package virtualschoolClient.view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

public class GraphEdit {

    public static void main(String[] args) throws IOException {
        String url = "C:\\Users\\86158\\Desktop\\IMG_7797.JPG";
        BufferedImage avatarImage = ImageIO.read(new File(url));
        int width = 120;
        // ͸���׵�ͼƬ
        BufferedImage formatAvatarImage = new BufferedImage(width, width, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D graphics = formatAvatarImage.createGraphics();
        //��ͼƬ�г�һ���A
        {
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            //��һ�����صĿհ������������Ҫ����Բ��ʱ����������
            int border = 1;
            //ͼƬ��һ��Բ��
            Ellipse2D.Double shape = new Ellipse2D.Double(border, border, width - border * 2, width - border * 2);
            //��Ҫ����������
            graphics.setClip(shape);
            graphics.drawImage(avatarImage, border, border, width - border * 2, width - border * 2, null);
            graphics.dispose();
        }
        //��Բͼ�����ٻ�һ��Բ
        {
            //�´���һ��graphics����������Բ�����о��
            graphics = formatAvatarImage.createGraphics();
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int border = 3;
            //������4.5�����أ�BasicStroke��ʹ�ÿ��Բ鿴����Ĳο��ĵ�
            //ʹ����ʱ��������������һ�����أ���������Լ�ʹ�õ�ʱ�����
            Stroke s = new BasicStroke(4.5F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
            graphics.setStroke(s);
            graphics.setColor(Color.WHITE);
            graphics.drawOval(border, border, width - border * 2, width - border * 2);
            graphics.dispose();
        }
        try (OutputStream os = new FileOutputStream("temp-avatar.png")) {
            ImageIO.write(formatAvatarImage, "PNG", os);
        }
        {
            url = "C:\\Users\\86158\\Desktop\\IMG_7797.JPG";;
            BufferedImage srcImg = ImageIO.read(new File(url));
            //scrImg������֮��û���κ���ɫ
            BufferedImage blankImage = new BufferedImage(srcImg.getWidth(), srcImg.getHeight(), BufferedImage.TYPE_INT_RGB);
            graphics = blankImage.createGraphics();
            graphics.drawImage(srcImg, 0, 0, null);

            int x = (blankImage.getWidth() - width) / 2;
            int y = (blankImage.getHeight() - width) / 2;
            graphics.drawImage(formatAvatarImage, x, y, width, width, null);
            try (OutputStream os = new FileOutputStream("temp-blank.png")) {
                ImageIO.write(blankImage, "PNG", os);
            }
        }

    }
}
