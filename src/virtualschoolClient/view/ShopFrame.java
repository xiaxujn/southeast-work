package virtualschoolClient.view;
import javax.swing.JFrame;

import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;

import common.*;

import virtualschoolClient.view.GoodsPanel;
import virtualschoolClient.view.PayPanel;


/**
 * 用于管理商店的两个Panel
 * @author CaiQishen
 *
 */
public class ShopFrame {

	private User user;
	private JFrame mainFrame;
	private GoodsPanel goodsPanel;
	private PayPanel payPanel;
	public MyListener actionListener=new MyListener();
	private ClientSocket skt;
	
	public static void main(String[] args) {
	//	TestFrame window=new TestFrame();
		
		
	}
	public ShopFrame(User us, ClientSocket cs ,JFrame frame)
	{
//		skt=new ClientSocket();//连接
		skt = cs;
		user = us;
		mainFrame = frame;

		mainFrame.setSize(865, 453);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
		mainFrame.setVisible(true);
		goodsPanel=new GoodsPanel(actionListener,skt,user);
		mainFrame.getContentPane().add(goodsPanel.subFrame);
		payPanel=null;
		mainFrame.setLocationRelativeTo(null);
		mainFrame.repaint();
		
		goodsPanel.subFrame.setVisible(true);
		
	}
	
	/**
	 * 回调函数，切换商店各个页面的Listener
	 * @author CaiQishen
	 *
	 */
	class MyListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			switch(e.getActionCommand())
			{
			case "SHOP_SHOP2PAY":
			{
				goodsPanel.subFrame.setVisible(false);
				payPanel=new PayPanel(goodsPanel.getTrolleyStr(),actionListener,skt,goodsPanel.getTally(),user);
				mainFrame.getContentPane().add(payPanel.subFrame);
				payPanel.subFrame.setVisible(true);
				break;
			}
			case "SHOP_PAY2SHOP":
			{
				payPanel.subFrame.setVisible(false);
				goodsPanel.subFrame.setVisible(true);
				break;
			}
			case "SHOP_SHOP2MAIN":
			{
				if(payPanel!=null) {
					payPanel.subFrame.setVisible(false);
				}
				goodsPanel.subFrame.setVisible(false);
				Main main = new Main(user,skt,mainFrame);
				main.setVisible(true);
				break;
			}
			case "SUCCESS_PAY":
			{
				payPanel.subFrame.setVisible(false);
				goodsPanel.updateUserInfo();
				goodsPanel.trolley.clearTrolley();
				goodsPanel.subFrame.setVisible(true);

			}
			default:
			{
				System.out.print(e.getActionCommand());
				break;
			}
			}
		}


	}
}
