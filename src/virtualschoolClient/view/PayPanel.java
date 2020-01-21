package virtualschoolClient.view;


/**
 * ���������
 * @author CaiQishen
 *
 */
import virtualschoolClient.view.ShopFrame.MyListener;

import javax.swing.JTextPane;

import common.ClientSocket;
import common.Message;
import common.MessageType;
import common.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PayPanel {

	public JPanel subFrame;
	private JTextPane textGdList;
	private ClientSocket skt;
	private User owner;
	/**
	 * Create the application.
	 */
	public PayPanel(String str,MyListener acl,ClientSocket s,float tally,User user) {
		skt=s;
		owner=user;
		initialize(acl,tally);
		textGdList.setBackground(new Color(240,240,245));
		textGdList.setText(str);
		
		
	}

	
	/**
	 * ��ʼ������
	 * @author CaiQishen
	 *
	 */
	private void initialize(final MyListener acl ,final float tally){
		
		subFrame = new JPanel();
		subFrame.setBounds(0, 0, 849, 414);		
		subFrame.setLayout(null);
		
		textGdList = new JTextPane();
		textGdList.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		textGdList.setBounds(10, 62, 363, 277);
		subFrame.add(textGdList);
		
		JLabel label = new JLabel("��������");
		label.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		label.setBounds(147, 37, 68, 15);
		subFrame.add(label);
		
		
		final JButton btnResume = new JButton("����");
		btnResume.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		btnResume.setActionCommand("SHOP_PAY2SHOP");
		btnResume.addActionListener(acl);
		btnResume.setBounds(10, 6, 68, 23);
		subFrame.add(btnResume);
		
		JLabel lblNewLabel_1 = new JLabel("ȡ��ʱ��");
		lblNewLabel_1.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(492, 213, 70, 15);
		subFrame.add(lblNewLabel_1);

		
		JButton btnPay = new JButton("ȷ�ϸ���");
		btnPay.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tally==0)
				{
					JOptionPane.showMessageDialog(null, "����δѡ���κ���Ʒ��", "����", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Message msg=new Message();
				msg.set_type(MessageType.SHOP_PAY);
				msg.set_data(String.valueOf(tally));
				skt.sendMessage(msg);
				msg=skt.receiveMessage();
				if(msg.get_type().equals(MessageType.SHOP_PAY))
				{
					if(msg.get_data().equals("false"))
					{
						JOptionPane.showMessageDialog(null, "�������㣡", "����", JOptionPane.ERROR_MESSAGE);
					}
					else
					{						 
						JOptionPane.showMessageDialog(null, "����ɹ���", "", JOptionPane.INFORMATION_MESSAGE);
						ActionEvent tempAction=new ActionEvent(subFrame,0,"SUCCESS_PAY");						
						acl.actionPerformed(tempAction);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "����������", "����", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnPay.setBounds(280, 363, 93, 23);
		subFrame.add(btnPay);
		
		JComboBox<String> boxLocation = new JComboBox<String>();
		boxLocation.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		boxLocation.setBounds(572, 144, 132, 21);
		boxLocation.addItem("÷԰����");
		boxLocation.addItem("��԰����");
		subFrame.add(boxLocation);
		
		JComboBox<String> boxTime = new JComboBox<String>();
		boxTime.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		boxTime.addItem("11:00-12:00");
		boxTime.addItem("12:00-13:00");
		boxTime.addItem("18:00-19:00");
		
		boxTime.setBounds(572, 210, 132, 21);
		subFrame.add(boxTime);
		
		JLabel lblNewLabel = new JLabel("ȡ���ص�");
		lblNewLabel.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		lblNewLabel.setBounds(492, 147, 70, 15);
		subFrame.add(lblNewLabel);
		
		
		
	
	}
}
