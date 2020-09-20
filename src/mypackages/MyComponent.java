package mypackages;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class MyComponent extends JComponent {

	private static final int DEFAULT_WIDTH = 700;
	private static final int DEFAULT_HEIGHT = 700;
	private MyClock mc;

	public MyComponent(MyClock mc) {
		this.mc = mc;
	}

	public void paintComponentFirst() throws InterruptedException {
		Graphics2D g2 = (Graphics2D) this.getGraphics();
		var font = new Font("华文彩云", Font.ITALIC, 12);
		g2.setFont(font);
		for (int i = 0; i < 24; i++) {
			for (int j = 0; j <= i; j++) {
				g2.drawString("8023", (int) (mc.listp3.get(i).x), (int) (mc.listp3.get(i).y));
			}
			Thread.sleep(30);
		}
		for (int i = 0; i < 60; i++) {
			for (int j = 0; j <= i; j++) {
				g2.drawString("love", (int) (mc.listp2.get(i).x), (int) (mc.listp2.get(i).y));
			}
			Thread.sleep(30);
		}
		for (int i = 0; i < 60; i++) {
			for (int j = 0; j <= i; j++) {
				g2.drawString("9420", (int) (mc.listp.get(i).x), (int) (mc.listp.get(i).y));
			}
			Thread.sleep(30);
		}
		mc.first = false;
		Thread.sleep(500);
	}

	public void paintComponent(Graphics g) {
		if(mc.first == true) {
			try {
				paintComponentFirst();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Graphics2D g2 = (Graphics2D) g;
		int s = mc.second;
		int m = mc.minute;
		int h = mc.hour;
		for (int i = 0; i < 60; i++) {
			if (i == 0) {
				var font = new Font("华文彩云", Font.ITALIC + Font.BOLD, 16);
				g2.setFont(font);
			} else {
				var font = new Font("华文彩云", Font.ITALIC, 12);
				g2.setFont(font);
			}
			g2.drawString(mc.seconds[59 - (s++) % 60], (int) (mc.listp.get(i).x), (int) (mc.listp.get(i).y));
			g2.drawString(mc.minutes[59 - (m++) % 60], (int) (mc.listp2.get(i).x), (int) (mc.listp2.get(i).y));
		}
		for (int i = 0; i < 24; i++) {
			if (i == 0) {
				var font = new Font("华文彩云", Font.ITALIC + Font.BOLD, 16);
				g2.setFont(font);
			} else {
				var font = new Font("华文彩云", Font.ITALIC, 12);
				g2.setFont(font);
			}
			g2.drawString(mc.hours[23 - (h++) % 24], (int) (mc.listp3.get(i).x), (int) (mc.listp3.get(i).y));
		}
		g2.draw(new Line2D.Double(340, 335, 340 + 350, 335));
	}

	public Dimension getPreferredSize() {
		return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
}
