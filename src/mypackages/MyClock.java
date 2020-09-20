package mypackages;

import java.util.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.Toolkit;

public class MyClock extends JFrame {

	private MyComponent myc;
	public LinkedList<Pair> listp;
	public LinkedList<Pair> listp2;
	public LinkedList<Pair> listp3;
	private double x;
	private double y;
	public static int second;
	public static int minute;
	public static int hour;
	public static boolean first;
	String[] seconds = { "59��", "58��", "57��", "56��", "55��", "54��", "53��", "52��", "51��", "50��", "49��", "48��", "47��",
			"46��", "45��", "44��", "43��", "42��", "41��", "40��", "39��", "38��", "37��", "36��", "35��", "34��", "33��", "32��",
			"31��", "30��", "29��", "28��", "27��", "26��", "25��", "24��", "23��", "22��", "21��", "20��", "19��", "18��", "17��",
			"16��", "15��", "14��", "13��", "12��", "11��", "10��", "9��", "8��", "7��", "6��", "5��", "4��", "3��", "2��", "1��",
			"0��" };
	String[] minutes = { "59��", "58��", "57��", "56��", "55��", "54��", "53��", "52��", "51��", "50��", "49��", "48��", "47��",
			"46��", "45��", "44��", "43��", "42��", "41��", "40��", "39��", "38��", "37��", "36��", "35��", "34��", "33��", "32��",
			"31��", "30��", "29��", "28��", "27��", "26��", "25��", "24��", "23��", "22��", "21��", "20��", "19��", "18��", "17��",
			"16��", "15��", "14��", "13��", "12��", "11��", "10��", "9��", "8��", "7��", "6��", "5��", "4��", "3��", "2��", "1��",
			"0��" };
	String[] hours = { "23ʱ", "22ʱ", "21ʱ", "20ʱ", "19ʱ", "18ʱ", "17ʱ", "16ʱ", "15ʱ", "14ʱ", "13ʱ", "12ʱ", "11ʱ", "10ʱ",
			"9ʱ", "8ʱ", "7ʱ", "6ʱ", "5ʱ", "4ʱ", "3ʱ", "2ʱ", "1ʱ", "0ʱ" };
	Runnable r = () -> {
		while (true) {
			second = (++second) % 60;
			if (second == 0) {
				minute = (++minute) % 60;
				if (minute == 0) {
					hour = (++hour) % 24;
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			myc.repaint();
		}
	};

	public MyClock(LinkedList<Pair> listp, LinkedList<Pair> listp2, LinkedList<Pair> listp3) {
		this.listp = listp;
		this.listp2 = listp2;
		this.listp3 = listp3;
		myc = new MyComponent(this);
		for (int i = 0; i < 60; i++) {
			double rad = i * 6 * Math.PI / 180;
			this.x = 340 + 320 * Math.cos(rad);
			this.y = 330 + 320 * Math.sin(rad);
			listp.addFirst(new Pair(this.x, this.y));
			this.x = 340 + 280 * Math.cos(rad);
			this.y = 330 + 280 * Math.sin(rad);
			listp2.addFirst(new Pair(this.x, this.y));
		}
		listp.addFirst(listp.getLast());
		listp2.addFirst(listp2.getLast());
		for (int i = 0; i < 24; i++) {
			double rad = i * 15 * Math.PI / 180;
			this.x = 340 + 240 * Math.cos(rad);
			this.y = 330 + 240 * Math.sin(rad);
			listp3.addFirst(new Pair(this.x, this.y));
		}
		listp3.addFirst(listp3.getLast());
		myc.setBackground(Color.black);
		add(myc);
		pack();
	}

	public static void main(String[] args) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		hour = calendar.get(Calendar.HOUR_OF_DAY);
		minute = calendar.get(Calendar.MINUTE);
		second = calendar.get(Calendar.SECOND);
		MyClock mc = new MyClock(new LinkedList<Pair>(), new LinkedList<Pair>(), new LinkedList<Pair>());
		mc.first = true;
		mc.setTitle("����ʱ��");
		mc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mc.setResizable(false);
		Toolkit kit = Toolkit.getDefaultToolkit();
		int x = (kit.getScreenSize().width - mc.getWidth()) / 2;
		int y = (kit.getScreenSize().height - mc.getHeight()) / 2;
		mc.setLocation(x, y); // ��������ʾ����Ļ����
		mc.setVisible(true);
		new Thread(mc.r).start();
	}
}
