package serial;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
/**
 * A thread to draw a graphe 
 * 
 *
 */
public class GrapheThread extends Thread {
	volatile boolean  stop=false;
	public static Com com = Com.getInstance();
	private JPanel dataDiagramme;

	public GrapheThread(JPanel dataDiagramme) {
		this.dataDiagramme=dataDiagramme;
	}
	public void cut() {
		stop=true;
	}
	
	public void run() {
		
		while (!stop) {
			
			System.out.println("this is a test!");
			Double [] RollGyro=new Double[5];
			Double [] PitchGyro=new Double[5];
			Double [] YawGyro=new Double[5];
			Double [] RollAcc=new Double[5];
			Double [] PitchAcc=new Double[5];
			Double [] YawAcc=new Double[5];

			try {
				byte[] data = Com.getInstance().sendCMD(API.CMD_REALTIME_DATA_3, API.noData);
				System.out.println("data.length"+data.length);
				//Com.getInstance().sendCMD(API.CMD_MOTORS_ON, API.noData);
				Thread.sleep(1000);

				for(int j=0;j<5;j++) {
					if(stop)
						break;
					data = Com.getInstance().sendCMD(API.CMD_REALTIME_DATA_3, API.noData);
					System.out.println("data.length"+data.length);
					for(int i=0;i<data.length;i++) {
						PitchAcc[j]=API.convertTwoBytesToInt(data[0], data[1])/512*API.g;
						PitchGyro[j]=API.convertTwoBytesToInt(data[2], data[3])*API.factor;
						RollAcc[j]=API.convertTwoBytesToInt(data[4], data[5])/512*API.g;
						RollGyro[j]=API.convertTwoBytesToInt(data[6], data[7])*API.factor;
						YawAcc[j]=API.convertTwoBytesToInt(data[8], data[9])/512*API.g;
						YawGyro[j]=API.convertTwoBytesToInt(data[10], data[11])*API.factor;
					}
					Thread.sleep(100);
				}
				dataDiagramme.removeAll();
				dataDiagramme.setLayout(new java.awt.BorderLayout());
				ChartPanel CP = new ChartPanel(Plot.drawData(RollGyro, PitchGyro, YawGyro, RollAcc, PitchAcc, YawAcc));
				dataDiagramme.add(CP,BorderLayout.CENTER);
				dataDiagramme.validate();
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
