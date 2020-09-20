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
	String[] seconds = { "59秒", "58秒", "57秒", "56秒", "55秒", "54秒", "53秒", "52秒", "51秒", "50秒", "49秒", "48秒", "47秒",
			"46秒", "45秒", "44秒", "43秒", "42秒", "41秒", "40秒", "39秒", "38秒", "37秒", "36秒", "35秒", "34秒", "33秒", "32秒",
			"31秒", "30秒", "29秒", "28秒", "27秒", "26秒", "25秒", "24秒", "23秒", "22秒", "21秒", "20秒", "19秒", "18秒", "17秒",
			"16秒", "15秒", "14秒", "13秒", "12秒", "11秒", "10秒", "9秒", "8秒", "7秒", "6秒", "5秒", "4秒", "3秒", "2秒", "1秒",
			"0秒" };
	String[] minutes = { "59分", "58分", "57分", "56分", "55分", "54分", "53分", "52分", "51分", "50分", "49分", "48分", "47分",
			"46分", "45分", "44分", "43分", "42分", "41分", "40分", "39分", "38分", "37分", "36分", "35分", "34分", "33分", "32分",
			"31分", "30分", "29分", "28分", "27分", "26分", "25分", "24分", "23分", "22分", "21分", "20分", "19分", "18分", "17分",
			"16分", "15分", "14分", "13分", "12分", "11分", "10分", "9分", "8分", "7分", "6分", "5分", "4分", "3分", "2分", "1分",
			"0分" };
	String[] hours = { "23时", "22时", "21时", "20时", "19时", "18时", "17时", "16时", "15时", "14时", "13时", "12时", "11时", "10时",
			"9时", "8时", "7时", "6时", "5时", "4时", "3时", "2时", "1时", "0时" };
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
		mc.setTitle("罗马时钟");
		mc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mc.setResizable(false);
		Toolkit kit = Toolkit.getDefaultToolkit();
		int x = (kit.getScreenSize().width - mc.getWidth()) / 2;
		int y = (kit.getScreenSize().height - mc.getHeight()) / 2;
		mc.setLocation(x, y); // 将窗口显示在屏幕中央
		mc.setVisible(true);
		new Thread(mc.r).start();
	}
}
