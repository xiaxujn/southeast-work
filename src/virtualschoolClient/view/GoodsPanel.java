package virtualschoolClient.view;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import java.util.List;
import java.util.regex.Pattern;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;

import common.*;
import common.User.UserStatus;
import virtualschoolClient.view.ShopFrame.MyListener;
import vo.GoodsMessage;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
/**
 * 商品选择界面类
 * @author CaiQishen
 *
 */
public class GoodsPanel {

	private User owner;
	public JPanel subFrame;
	int page=0;
	private JLabel textTally ;
	private JLabel numGd1 ;
	private JLabel numGd2 ;
	private JLabel numGd3 ;
	private JLabel numGd4 ;
	private JLabel numGd5 ;
	private JLabel numGd6 ;

	private JLabel nameGd1;
	private JLabel nameGd2;
	private JLabel nameGd3;
	private JLabel nameGd4;
	private JLabel nameGd5;
	private JLabel nameGd6;

	private JLabel priceGd1;
	private JLabel priceGd2;
	private JLabel priceGd3;
	private JLabel priceGd4;
	private JLabel priceGd5;
	private JLabel priceGd6;

	private JLabel image1;
	private JLabel image2;
	private JLabel image3;
	private JLabel image4;
	private JLabel image5;
	private JLabel image6;
	private JLabel lblBalance;
	public Trolley trolley=null;
	private List<Goods>goodsSupply=new ArrayList<Goods>();//提供的商品
	private ClientSocket skt;

	public GoodsPanel(MyListener acl,ClientSocket s,User user) {
		skt=s;
		owner=user;		
		initData();
		initialize(acl);

	}
	
	/**
	 * 向服务端发送修改价格请求
	 * @author CaiQishen
	 *
	 */
	public void sendSetPrice(int i)
	{
		if(owner.get_status()!=UserStatus.ADMINISTRATOR)
			return;
		String price = JOptionPane.showInputDialog("输入新的价格");
		if(price== null||price.equals(""))
			return;
		Pattern pattern = Pattern.compile("^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$"); // 判断是否为浮点数
		if(!pattern.matcher(price).matches())
		{
			JOptionPane.showMessageDialog(null, "非法输入！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}

		Message msg=new Message();
		msg.set_type(MessageType.SHOP_SET_PRICE);
		msg.set_data(goodsSupply.get(page+i).name+"#"+price);
		if(skt.sendMessage(msg))
		{
			System.out.println(22);
			goodsSupply.get(page+i).price=Float.valueOf(price);
			System.out.println(goodsSupply.get(page+i).price);
			trolley.goodsChosen.get(page+i).price=Float.valueOf(price);
			updateShop();
		}


	}
	
	
	/**
	 * 获取购物车商品列表生成的String类型订单
	 * @author CaiQishen
	 *
	 */
	public String getTrolleyStr()
	{
		trolley.updateTally();
		return trolley.toString();
	}
	
	/**
	 * 获取购物车总价格
	 * @author CaiQishen
	 *
	 */
	public float getTally()
	{
		return trolley.priceTally;
	}
	
	/**
	 * 刷新已选商品和总价格
	 * @author CaiQishen
	 *
	 */
	private void updateNum()
	{
		textTally.setText("￥ "+String.valueOf(trolley.priceTally));
		numGd1.setText(String.valueOf(trolley.goodsChosen.get(0+page).num));
		numGd2.setText(String.valueOf(trolley.goodsChosen.get(1+page).num));
		numGd3.setText(String.valueOf(trolley.goodsChosen.get(2+page).num));
		numGd4.setText(String.valueOf(trolley.goodsChosen.get(3+page).num));
		numGd5.setText(String.valueOf(trolley.goodsChosen.get(4+page).num));
		numGd6.setText(String.valueOf(trolley.goodsChosen.get(5+page).num));


	}
	
	/**
	 * 刷新商店界面的商品信息、购物车信息
	 * @author CaiQishen
	 *
	 */
	private void updateShop()
	{	
		nameGd1.setText(goodsSupply.get(0+page).name);
		nameGd2.setText(goodsSupply.get(1+page).name);
		nameGd3.setText(goodsSupply.get(2+page).name);
		nameGd4.setText(goodsSupply.get(3+page).name);
		nameGd5.setText(goodsSupply.get(4+page).name);
		nameGd6.setText(goodsSupply.get(5+page).name);

		priceGd1.setText(String.valueOf(goodsSupply.get(0+page).price));
		priceGd2.setText(String.valueOf(goodsSupply.get(1+page).price));
		priceGd3.setText(String.valueOf(goodsSupply.get(2+page).price));
		priceGd4.setText(String.valueOf(goodsSupply.get(3+page).price));
		priceGd5.setText(String.valueOf(goodsSupply.get(4+page).price));
		priceGd6.setText(String.valueOf(goodsSupply.get(5+page).price));

		image1.setIcon(goodsSupply.get(0+page).photo);
		image2.setIcon(goodsSupply.get(1+page).photo);
		image3.setIcon(goodsSupply.get(2+page).photo);
		image4.setIcon(goodsSupply.get(3+page).photo);
		image5.setIcon(goodsSupply.get(4+page).photo);
		image6.setIcon(goodsSupply.get(5+page).photo);
		updateNum();

	}
	
	
	/**
	 * 从客户端获取商品信息
	 * @author CaiQishen
	 *
	 */
	private Boolean loadGoodsSupply()//TO-DO
	{
		Message msg=new Message();
		msg.set_type(MessageType.SHOP_LOAD_IMG);
		skt.sendMessage(msg);


		GoodsMessage gds[]=new GoodsMessage[12];
		gds=skt.receiveObject(gds);

		for(int i=0;i<12;i++)
		{
			goodsSupply.add(new Goods(gds[i]));
		}


		return true;
	}
	/**
	 * 更新用户余额等信息
	 * @author CaiQishen
	 *
	 */
	public Boolean updateUserInfo()
	{
		Message msg=new Message();
		msg.set_type(MessageType.SHOP_GET_BALANCE);
		skt.sendMessage(msg);
		msg=skt.receiveMessage();
		System.out.println(msg.get_data());
		if(msg.get_type().equals(MessageType.SHOP_GET_BALANCE))
		{
			lblBalance.setText(msg.get_data());
			return true;
		}
		return false;
	}
	
	/**
	 * 初始化用户信息，商品信息等数据
	 * @author CaiQishen
	 *
	 */
	private void initData()
	{
		loadGoodsSupply();
		trolley=new Trolley();

	}



	/**
	 * 商品选择界面类
	 * @author CaiQishen
	 *
	 */
	private void initialize(MyListener acl) {//插件调用

		subFrame = new JPanel();
		subFrame.setBounds(0, 0, 849, 414);

		subFrame.setLayout(null);
		//===============================加号按钮==============================================//
		JButton btnAddGoods1 = new JButton("+");
		btnAddGoods1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				trolley.addGoods1(0);				
			}
		});
		btnAddGoods1.setBounds(219, 137, 47, 23);
		subFrame.add(btnAddGoods1);

		JButton btnAddGoods2 = new JButton("+");
		btnAddGoods2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				trolley.addGoods1(1);

			}
		});
		btnAddGoods2.setBounds(388, 137, 47, 23);
		subFrame.add(btnAddGoods2);

		JButton btnAddGoods3 = new JButton("+");
		btnAddGoods3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				trolley.addGoods1(2);	
			}
		});

		btnAddGoods3.setBounds(568, 137, 47, 23);
		subFrame.add(btnAddGoods3);

		JButton btnAddGoods4 = new JButton("+");
		btnAddGoods4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				trolley.addGoods1(3);	
			}
		});
		btnAddGoods4.setBounds(219, 323, 47, 23);
		subFrame.add(btnAddGoods4);

		JButton btnAddGoods5 = new JButton("+");
		btnAddGoods5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				trolley.addGoods1(4);	
			}
		});
		btnAddGoods5.setBounds(388, 323, 47, 23);
		subFrame.add(btnAddGoods5);

		JButton btnAddGoods6 = new JButton("+");
		btnAddGoods6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				trolley.addGoods1(5);	
			}
		});
		btnAddGoods6.setBounds(568, 323, 47, 23);
		subFrame.add(btnAddGoods6);

		//===================减号按钮======================================//
		JButton btnSubGoods1 = new JButton("-");
		btnSubGoods1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				trolley.subGoods1(0);
			}
		});
		btnSubGoods1.setBounds(109, 137, 47, 23);
		subFrame.add(btnSubGoods1);

		JButton btnSubGoods2 = new JButton("-");
		btnSubGoods2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				trolley.subGoods1(1);
			}
		});
		btnSubGoods2.setBounds(298, 137, 47, 23);
		subFrame.add(btnSubGoods2);

		JButton btnSubGoods3 = new JButton("-");
		btnSubGoods3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				trolley.subGoods1(2);
			}
		});
		btnSubGoods3.setBounds(468, 137, 47, 23);
		subFrame.add(btnSubGoods3);

		JButton btnSubGoods4 = new JButton("-");
		btnSubGoods4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				trolley.subGoods1(3);
			}
		});
		btnSubGoods4.setBounds(109, 323, 47, 23);
		subFrame.add(btnSubGoods4);

		JButton btnSubGoods5 = new JButton("-");
		btnSubGoods5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				trolley.subGoods1(4);
			}
		});
		btnSubGoods5.setBounds(298, 323, 47, 23);
		subFrame.add(btnSubGoods5);

		JButton btnSubGoods6 = new JButton("-");
		btnSubGoods6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				trolley.subGoods1(5);
			}
		});
		btnSubGoods6.setBounds(468, 323, 47, 23);
		subFrame.add(btnSubGoods6);
		//===================================确认购买按钮=====================================//
		JButton btnPurchase = new JButton("付款");
		btnPurchase.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		btnPurchase.setActionCommand("SHOP_SHOP2PAY");
		btnPurchase.addActionListener(acl);
		btnPurchase.setBounds(699, 354, 93, 27);
		subFrame.add(btnPurchase);
		//===================================计算总价label=====================================//
		textTally = new JLabel();
		textTally.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textTally.setBounds(711, 324, 68, 21);
		subFrame.add(textTally);
		//=============================商品名、价格=============================================================//		
		nameGd1 = new JLabel();
		nameGd1.setHorizontalAlignment(SwingConstants.CENTER);
		nameGd1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		nameGd1.setText(goodsSupply.get(0).name);
		nameGd1.setBounds(109, 163, 157, 23);
		subFrame.add(nameGd1);

		priceGd1 = new JLabel();
		priceGd1.setHorizontalAlignment(SwingConstants.CENTER);
		priceGd1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		priceGd1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sendSetPrice(0);
			}
		});
		priceGd1.setText(String.valueOf(goodsSupply.get(0).price));
		priceGd1.setBounds(109, 184, 157, 23);
		subFrame.add(priceGd1);

		nameGd2 = new JLabel();
		nameGd2.setHorizontalAlignment(SwingConstants.CENTER);
		nameGd2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		nameGd2.setText(goodsSupply.get(1).name);
		nameGd2.setBounds(298, 163, 137, 23);
		subFrame.add(nameGd2);

		priceGd2 = new JLabel();
		priceGd2.setHorizontalAlignment(SwingConstants.CENTER);
		priceGd2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		priceGd2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sendSetPrice(1);
			}
		});
		priceGd2.setText(String.valueOf(goodsSupply.get(1).price));
		priceGd2.setBounds(298, 184, 137, 23);
		subFrame.add(priceGd2);

		nameGd3 = new JLabel();
		nameGd3.setHorizontalAlignment(SwingConstants.CENTER);
		nameGd3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		nameGd3.setText(goodsSupply.get(2).name);
		nameGd3.setBounds(468, 163, 147, 23);
		subFrame.add(nameGd3);

		priceGd3 = new JLabel();
		priceGd3.setHorizontalAlignment(SwingConstants.CENTER);
		priceGd3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		priceGd3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sendSetPrice(2);
			}
		});
		priceGd3.setText(String.valueOf(goodsSupply.get(2).price));
		priceGd3.setBounds(468, 184, 147, 23);
		subFrame.add(priceGd3);

		nameGd4 = new JLabel();
		nameGd4.setHorizontalAlignment(SwingConstants.CENTER);
		nameGd4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		nameGd4.setText(goodsSupply.get(3).name);
		nameGd4.setBounds(109, 352, 157, 23);
		subFrame.add(nameGd4);

		priceGd4 = new JLabel();
		priceGd4.setHorizontalAlignment(SwingConstants.CENTER);
		priceGd4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		priceGd4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sendSetPrice(3);
			}
		});
		priceGd4.setText(String.valueOf(goodsSupply.get(3).price));
		priceGd4.setBounds(109, 374, 157, 25);
		subFrame.add(priceGd4);

		nameGd5 = new JLabel();
		nameGd5.setHorizontalAlignment(SwingConstants.CENTER);
		nameGd5.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		nameGd5.setText(goodsSupply.get(4).name);
		nameGd5.setBounds(298, 352, 137, 23);
		subFrame.add(nameGd5);

		priceGd5 = new JLabel();
		priceGd5.setHorizontalAlignment(SwingConstants.CENTER);
		priceGd5.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		priceGd5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sendSetPrice(4);
			}
		});
		priceGd5.setText(String.valueOf(goodsSupply.get(4).price));
		priceGd5.setBounds(298, 377, 137, 23);
		subFrame.add(priceGd5);

		nameGd6 = new JLabel();
		nameGd6.setHorizontalAlignment(SwingConstants.CENTER);
		nameGd6.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		nameGd6.setText(goodsSupply.get(5).name);
		nameGd6.setBounds(468, 352, 147, 23);
		subFrame.add(nameGd6);

		priceGd6 = new JLabel();
		priceGd6.setHorizontalAlignment(SwingConstants.CENTER);
		priceGd6.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		priceGd6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sendSetPrice(5);
			}
		});
		priceGd6.setText(String.valueOf(goodsSupply.get(5).price));
		priceGd6.setBounds(468, 375, 147, 23);
		subFrame.add(priceGd6);
		//=============================剩余数量=============================================================//

		numGd1 = new JLabel();
		numGd1.setHorizontalAlignment(SwingConstants.CENTER);
		numGd1.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		numGd1.setBounds(159, 141, 61, 15);
		subFrame.add(numGd1);

		numGd2 = new JLabel();
		numGd2.setHorizontalAlignment(SwingConstants.CENTER);
		numGd2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		numGd2.setBounds(348, 141, 40, 15);
		subFrame.add(numGd2);

		numGd3 = new JLabel();
		numGd3.setHorizontalAlignment(SwingConstants.CENTER);
		numGd3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		numGd3.setBounds(514, 141, 54, 15);
		subFrame.add(numGd3);

		numGd4 = new JLabel();
		numGd4.setHorizontalAlignment(SwingConstants.CENTER);
		numGd4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		numGd4.setBounds(159, 327, 61, 15);
		subFrame.add(numGd4);

		numGd5 = new JLabel();
		numGd5.setHorizontalAlignment(SwingConstants.CENTER);
		numGd5.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		numGd5.setBounds(341, 327, 47, 15);
		subFrame.add(numGd5);

		numGd6 = new JLabel();
		numGd6.setHorizontalAlignment(SwingConstants.CENTER);
		numGd6.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		numGd6.setBounds(514, 327, 54, 15);
		subFrame.add(numGd6);

		//===================商品图片========================================//
		image1 = new JLabel("");
		image1.setIcon(goodsSupply.get(0).photo);
		image1.setBounds(137, 33, 100, 100);
		subFrame.add(image1);

		image2 = new JLabel("");		
		image2.setBounds(317, 33, 100, 100);
		image2.setIcon(goodsSupply.get(1).photo);
		subFrame.add(image2);

		image3 = new JLabel("");
		image3.setBounds(490, 33, 100, 100);
		image3.setIcon(goodsSupply.get(2).photo);
		subFrame.add(image3);

		image4 = new JLabel("");
		image4.setBounds(137, 217, 100, 100);
		image4.setIcon(goodsSupply.get(3).photo);
		subFrame.add(image4);

		image5 = new JLabel("");
		image5.setBounds(317, 219, 100, 100);
		image5.setIcon(goodsSupply.get(4).photo);
		subFrame.add(image5);

		image6 = new JLabel("");
		image6.setBounds(490, 219, 100, 100);
		image6.setIcon(goodsSupply.get(5).photo);
		subFrame.add(image6);
		//=================用户信息====================//	
		JLabel lblName = new JLabel(owner.get_id());
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblName.setBounds(699, 137, 93, 27);
		subFrame.add(lblName);

		JLabel label_1 = new JLabel("余额:");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label_1.setBounds(711, 184, 54, 23);
		subFrame.add(label_1);

		lblBalance = new JLabel("");
		lblBalance.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblBalance.setBounds(748, 184, 71, 23);
		subFrame.add(lblBalance);

		JButton button = new JButton("回到首页");
		button.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		button.setActionCommand("SHOP_SHOP2MAIN");
		button.addActionListener(acl);
		button.setBounds(699, 33, 93, 27);
		subFrame.add(button);

		final JLabel lblType1 = new JLabel("食品 --<");
		lblType1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblType1.setBounds(23, 174, 87, 40);
		subFrame.add(lblType1);

		final JLabel lblType2 = new JLabel("其他");
		lblType2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblType1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				page=0;
				lblType1.setText("食品 --<");
				lblType2.setText("其他");
				updateShop();
			}
		});
		lblType2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				page=6;
				lblType1.setText("食品");
				lblType2.setText("其他 --<");
				updateShop();
			}
		});
		
		lblType2.setBounds(23, 228, 87, 40);
		subFrame.add(lblType2);


		updateShop();
		updateUserInfo();

	}

	//==============子类==================//
	/**
	 * GoodsPanel子类 实现购物车功能，存储订单信息
	 * @author CaiQishen
	 *
	 */
	public class Trolley//购物车
	{
		private List<Goods> goodsChosen=new ArrayList<Goods>();
		public float priceTally=(float)0.0;

		public Trolley() {
			goodsChosen=goodsSupply;
			for(Goods gd:goodsChosen)
			{
				gd.set_num(0);
			}		
		}
		
		/**
		 * 清空购物车
		 * @author CaiQishen
		 *
		 */
		public void clearTrolley()
		{
			for(Goods gd:goodsChosen)
			{
				gd.set_num(0);
			}		
			updateTally();
		}
		
		/**
		 * 将订单转为String传给付款界面
		 * @author CaiQishen
		 *
		 */
		public String toString()//转为订单
		{
			String str="\n";
			for(Goods gd:goodsChosen)
			{
				if(gd.get_num()>0)
				{
					String tStr="   ";//每一行
					tStr+=(gd.get_name()+" × "+gd.get_num()+"  ");
					while(tStr.length()<24)
					{
						
						tStr+="一" ;
					}
					tStr+="  ";
					tStr+=(gd.get_price()*gd.get_num()+"\n");
					str+=tStr;
					
				}
			}
			str+="\n   合计  --------------------------------- "+priceTally;

			return str;
		}
		
		/**
		 * 刷新购物车总价格
		 * @author CaiQishen
		 *
		 */
		public void updateTally()
		{
			priceTally=0;
			for(Goods tGood:goodsChosen)
			{
				priceTally+=tGood.get_price()*tGood.get_num();
			}
			
			updateNum();
		}
		
		/**
		 * 添加商品
		 * @author CaiQishen
		 *
		 */
		public Boolean addGoods1(int i)
		{
			goodsChosen.get(i+page).addNum(1);
			updateTally();
			return true;
		}
		
		/**
		 * 删除商品
		 * @author CaiQishen
		 *
		 */
		public Boolean subGoods1(int i)
		{
			goodsChosen.get(i+page).subNum(1);
			updateTally();
			return true;
		}
	}

	
	/**
	 * GoodsPanel内部类，用于实现商品属性，包括名称、价格
	 * @author CaiQishen
	 *
	 */
	public class Goods//商品
	{
		private String name;
		private float price;
		private ImageIcon photo;
		private Integer num;


		public Goods(GoodsMessage gd)
		{
			this.name=gd.name;
			this.price=gd.price;
			this.photo=gd.photo;
			this.num=gd.num;
		}
		public Goods(Goods gd)//创建单件商品（加入购物车
		{
			this.name=gd.get_name();
			this.price=gd.get_price();
			this.photo=gd.get_photo();
			this.num=1;//！！！！！
		}

		/**
		 * 获取商品名称
		 * @author CaiQishen
		 *
		 */
		public String get_name()
		{
			return this.name;
		}
		
		/**
		 * 获取商品图片
		 * @author CaiQishen
		 *
		 */
		public ImageIcon get_photo()
		{
			return this.photo;
		}
		
		/**
		 * 获取商品价格
		 * @author CaiQishen
		 *
		 */
		public float get_price()
		{
			return this.price;
		}
		public Integer get_num()
		{
			return this.num;
		}
		public void set_num(int n)
		{
			this.num=n;
		}
		
		
		/**
		 * 增加商品在购物车中数量
		 * @author CaiQishen
		 *
		 */
		public void addNum(int n)
		{
			this.num+=n;
		}
		
		
		/**
		 * 减少商品在购物车中数量
		 * @author CaiQishen
		 *
		 */
		public void subNum(int n)
		{
			if(this.num>0)
			{this.num-=n;}
		}
	}
}
