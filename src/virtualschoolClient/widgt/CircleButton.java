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
 * 制作一个圆形的按钮时，需要做两件事： 第一件事是重载一个适当的绘画方法以画出一个圆形。
 * 第二件事是设置一些事件使得只有当你点击圆形按钮的范围中的时侯按钮才会作出响应
 */
public class CircleButton extends JButton {

	public CircleButton(String label) {
        super(label);
 
        // 获取按钮的最佳大小
        Dimension size = getPreferredSize();
//        size.width = size.height = Math.max(size.width, size.height);
        size.width = size.height =100;
        setPreferredSize(size);
        this.setFont(new Font("Dialog",Font.ROMAN_BASELINE,25));
        
        this.setBorder(new BevelBorder(BevelBorder.RAISED));
        setContentAreaFilled(false);
        this.setBorderPainted(true); // 不绘制边框
        this.setFocusPainted(false); // 不绘制焦点状态
    }
 
	// 画圆的按钮的背景和标签
    protected void paintComponent(Graphics g) {
 
    	if(getModel().isPressed())
    	{
    		if ( this.getText().equals("<html>学生信息<br>管理系统</html>")) {   //点击时变色
    			g.setColor(new Color(255,165,0));             
    		} else if (this.getText().equals("选课系统")) {
    			g.setColor(new Color(238,130,238));
    		} else if (this.getText().equals("图书馆")) {
    			g.setColor(new Color(255,215,0));
    		} else if (this.getText().equals("银行")) {
    			g.setColor(new Color(255,218,185));
    		} else {
    			g.setColor(new Color(0,191,255));
    		}
    	}
    	else if (getModel().isRollover()) {
    		if ( this.getText().equals("学生信息管理系统")) {
    			g.setColor(new Color(255,140,0)); // 移动时颜色
    		} else if (this.getText().equals("选课系统")) {
    			g.setColor(new Color(238,130,238));
    		} else if (this.getText().equals("图书馆")) {
    			g.setColor(new Color(255,215,0));
    		} else if (this.getText().equals("银行")) {
    			g.setColor(new Color(255,218,185));
    		} else {
    			g.setColor(new Color(0,191,255));
    		}
        } else {
            //g.setColor(getBackground());
        	Color temp = new Color(248,248,255);
        	g.setColor(temp);
        }
        // fillOval方法画一个矩形的内切椭圆，并且填充这个椭圆，
        // 当矩形为正方形时，画出的椭圆便是圆
        g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
 
        super.paintComponent(g);
    }

 
    // 用简单的弧画按钮的边界。
    protected void paintBorder(Graphics g) {
//        g.setColor(Color.white);
        // drawOval方法画矩形的内切椭圆，但不填充。只画出一个边界
        g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
    }
 
    // shape对象用于保存按钮的形状，有助于侦听点击按钮事件
    Shape shape;
 
    public boolean contains(int x, int y) {
 
        if ((shape == null) || (!shape.getBounds().equals(getBounds()))) {
            // 构造一个椭圆形对象
            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        }
        // 判断鼠标的x、y坐标是否落在按钮形状内。
        return shape.contains(x, y);
    }
    
    public static void main(String[] args) {
        JButton button = new CircleButton("测试");
        button.setBackground(Color.orange);
 
        JFrame frame = new JFrame("圆形按钮");
        //frame.getContentPane().setBackground(Color.pink);
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(button);
        frame.setSize(400,400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
 
}
