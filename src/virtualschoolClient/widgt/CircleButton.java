package virtualschoolClient.widgt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.BevelBorder;
 
/**
 * ����һ��Բ�εİ�ťʱ����Ҫ�������£� ��һ����������һ���ʵ��Ļ滭�����Ի���һ��Բ�Ρ�
 * �ڶ�����������һЩ�¼�ʹ��ֻ�е�����Բ�ΰ�ť�ķ�Χ�е�ʱ�ť�Ż�������Ӧ
 */
public class CircleButton extends JButton {

	public CircleButton(String label) {
        super(label);
 
        // ��ȡ��ť����Ѵ�С
        Dimension size = getPreferredSize();
//        size.width = size.height = Math.max(size.width, size.height);
        size.width = size.height =100;
        setPreferredSize(size);
        this.setFont(new Font("Dialog",Font.ROMAN_BASELINE,25));
        
        this.setBorder(new BevelBorder(BevelBorder.RAISED));
        setContentAreaFilled(false);
        this.setBorderPainted(true); // �����Ʊ߿�
        this.setFocusPainted(false); // �����ƽ���״̬
    }
 
	// ��Բ�İ�ť�ı����ͱ�ǩ
    protected void paintComponent(Graphics g) {
 
    	if(getModel().isPressed())
    	{
    		if ( this.getText().equals("<html>ѧ����Ϣ<br>����ϵͳ</html>")) {   //���ʱ��ɫ
    			g.setColor(new Color(255,165,0));             
    		} else if (this.getText().equals("ѡ��ϵͳ")) {
    			g.setColor(new Color(238,130,238));
    		} else if (this.getText().equals("ͼ���")) {
    			g.setColor(new Color(255,215,0));
    		} else if (this.getText().equals("����")) {
    			g.setColor(new Color(255,218,185));
    		} else {
    			g.setColor(new Color(0,191,255));
    		}
    	}
    	else if (getModel().isRollover()) {
    		if ( this.getText().equals("ѧ����Ϣ����ϵͳ")) {
    			g.setColor(new Color(255,140,0)); // �ƶ�ʱ��ɫ
    		} else if (this.getText().equals("ѡ��ϵͳ")) {
    			g.setColor(new Color(238,130,238));
    		} else if (this.getText().equals("ͼ���")) {
    			g.setColor(new Color(255,215,0));
    		} else if (this.getText().equals("����")) {
    			g.setColor(new Color(255,218,185));
    		} else {
    			g.setColor(new Color(0,191,255));
    		}
        } else {
            //g.setColor(getBackground());
        	Color temp = new Color(248,248,255);
        	g.setColor(temp);
        }
        // fillOval������һ�����ε�������Բ��������������Բ��
        // ������Ϊ������ʱ����������Բ����Բ
        g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
 
        super.paintComponent(g);
    }

 
    // �ü򵥵Ļ�����ť�ı߽硣
    protected void paintBorder(Graphics g) {
//        g.setColor(Color.white);
        // drawOval���������ε�������Բ��������䡣ֻ����һ���߽�
        g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
    }
 
    // shape�������ڱ��水ť����״�����������������ť�¼�
    Shape shape;
 
    public boolean contains(int x, int y) {
 
        if ((shape == null) || (!shape.getBounds().equals(getBounds()))) {
            // ����һ����Բ�ζ���
            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        }
        // �ж�����x��y�����Ƿ����ڰ�ť��״�ڡ�
        return shape.contains(x, y);
    }
    
    public static void main(String[] args) {
        JButton button = new CircleButton("����");
        button.setBackground(Color.orange);
 
        JFrame frame = new JFrame("Բ�ΰ�ť");
        //frame.getContentPane().setBackground(Color.pink);
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(button);
        frame.setSize(400,400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
 
}
