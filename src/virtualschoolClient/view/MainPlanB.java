//登录后主界面
package virtualschoolClient.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import virtualschoolClient.widgt.GBC;

import java.awt.Font;
import java.awt.GridBagLayout;

class MainPlanB extends JFrame implements ActionListener {
	JFrame frame;
    Container c = getContentPane();
    JPanel p0 = new JPanel();
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JPanel p3 = new JPanel();
    JPanel p4 = new JPanel();
    JPanel p5 = new JPanel();
    JLabel index = new JLabel("欢迎使用");
    JButton studentinfomanage = new JButton("信息管理");
    JButton chooseclass = new JButton("选课");
    JButton library = new JButton("图书馆");
    JButton bank = new JButton("银行");
    JButton shop = new JButton("商店");
    Font font1 = new Font("00", Font.BOLD, 15); 
    public MainPlanB() 
    {
    	frame = new JFrame();
		frame.setSize(600,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagLayout layout = new GridBagLayout();
        frame.setLayout(layout);
		
        index.setSize(10, 400);
    	p0.add(index,BorderLayout.LINE_END);
    	GBC temp = new GBC(0,0,3,1);
    	//temp.setFill(GBC.BOTH);
    	temp.setAnchor(GBC.NORTHEAST);
    	frame.add(p0,temp);
    	
    	p1.setLayout(new FlowLayout());
    	p1.add(studentinfomanage,BorderLayout.LINE_START);
    	p2.add(chooseclass,BorderLayout.LINE_END);
    	p3.add(library,BorderLayout.LINE_START);
    	p4.add(bank,BorderLayout.LINE_END);
    	p5.add(shop,BorderLayout.LINE_START);
    	studentinfomanage.setFont(font1);
    	chooseclass.setFont(font1);
    	library.setFont(font1);
    	bank.setFont(font1);
    	shop.setFont(font1);
    	studentinfomanage.addActionListener(this);
    	chooseclass.addActionListener(this);
    	library.addActionListener(this);
    	bank.addActionListener(this);
    	shop.addActionListener(this);
    	
    	frame.add(p1,new GBC(0,1,2,1).setFill(GBC.BOTH));
    	frame.add(p2,new GBC(0,2,2,1).setFill(GBC.BOTH));
    	frame.add(p3,new GBC(0,3,2,1).setFill(GBC.BOTH));
    	frame.add(p4,new GBC(0,4,2,1).setFill(GBC.BOTH));
    	frame.add(p5,new GBC(0,5,2,1).setFill(GBC.BOTH));
    	//frame.add(c);
        frame.setVisible(true);
        frame.setLocation(500,300);
    }
    public void actionPerformed(ActionEvent e) {
    	if (e.getSource() == studentinfomanage) {
    		this.dispose();
    		//open子模块的界面
    		}
    	if (e.getSource() == chooseclass) {
    		this.dispose();
    		//open子模块的界面
    		}
    	if (e.getSource() == library) {
    		this.dispose();
    		//open子模块的界面
    		}
    	if (e.getSource() == bank) {
    		this.dispose();
    		//open子模块的界面
    		}
    	if (e.getSource() == shop) {
    		this.dispose();
    		//open子模块的界面
    		}
    }
	public static void main(String[] arg)
	{	
	    try
	    {
	        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
	        BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
	        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
	        UIManager.put("RootPane.setupButtonVisible",false);
	    }
	    catch(Exception e)
	    {
	        //TODO exception
	    }
	    MainPlanB temp = new MainPlanB();
	}
}
