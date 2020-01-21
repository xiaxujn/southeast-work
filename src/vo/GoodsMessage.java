package vo;

import javax.swing.ImageIcon;

/**
 * 传递商品类的信息
 * @author CaiQishen
 *
 */
public class GoodsMessage implements java.io.Serializable {


	public String name;
	public float price;
	public ImageIcon photo;//商品真实图片，不是地址
	public Integer num;
	
}
