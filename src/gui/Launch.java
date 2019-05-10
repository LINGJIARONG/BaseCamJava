package gui;

import java.awt.EventQueue;


public class Launch {
	//private Modele m;
	//private Controleur c;
	private Vue v;
	public Launch() {
		v=new Vue();
		v.setVisible(true);
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Launch();
			}
		});
	}
}
