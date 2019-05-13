package serial;

import java.io.IOException;

/**
 * main class to test / define some functions
 */
public class test {

	public static Com com = Com.getInstance();
	public static boolean continu = true;

	public static void main(String[] args) throws InterruptedException, IOException {
		

		Com.getInstance().sendCMD(API.CMD_MOTORS_ON, API.noData);
		ControlPack c = new ControlPack();
		Com.getInstance().sendCMD(API.CMD_WRITE_PARAMS,
				PidControl.buildByte(89, 16, 169, 120, 2, 11, 1, 28, 120, 2, 7, 1, 176, 120, 2));

		
		byte[] data = Com.getInstance().sendCMD(API.CMD_REALTIME_DATA_3, API.noData);
		
		System.out.println("data.length" + data.length);
		c.pack();
		c.send();

		Thread.sleep(10000);

//		for (int j = 0; j < 50; j++) {
//
//			data = Com.getInstance().sendCMD(API.CMD_REALTIME_DATA_3, API.noData);
//			System.out.println("data.length" + data.length);
//			for (int i = 0; i < data.length; i++) {
//				PitchAcc[j] = API.convertTwoBytesToInt(data[0], data[1]) / 512 * API.g;
//				PitchGyro[j] = API.convertTwoBytesToInt(data[2], data[3]) * API.factor;
//				RollAcc[j] = API.convertTwoBytesToInt(data[4], data[5]) / 512 * API.g;
//				RollGyro[j] = API.convertTwoBytesToInt(data[6], data[7]) * API.factor;
//				YawAcc[j] = API.convertTwoBytesToInt(data[8], data[9]) / 512 * API.g;
//				YawGyro[j] = API.convertTwoBytesToInt(data[10], data[11]) * API.factor;
//			}
//			Thread.sleep(200);
//		}
//		Plot.drawData(RollGyro, PitchGyro, YawGyro, RollAcc, PitchAcc, YawAcc);
		Com.getInstance().sendCMD(API.CMD_MOTORS_OFF, API.noData);
		Com.getInstance().close();
	}

	public static void connect() throws IOException, InterruptedException {
		Com.getInstance().sendCMD(API.CMD_MOTORS_ON, API.noData);
	}

	public static void disconnect() throws IOException, InterruptedException {
		Com.getInstance().sendCMD(API.CMD_MOTORS_OFF, API.noData);
		//Com.getInstance().close();
	}

	public static void consigne() throws IOException, InterruptedException {
		ControlPack c = new ControlPack();
		c.pack();
		c.send();
	}

	
	public static void consigneSpeed(int vx,int vy,int vz) throws IOException, InterruptedException {
		ControlPack c = new ControlPack(API.SBGC_CONTROL_MODE_SPEED, API.SBGC_CONTROL_MODE_SPEED,
				API.SBGC_CONTROL_MODE_SPEED, (int) (2*30 * API.SBGC_SPEED_SCALE), (int) (2*30 * API.SBGC_SPEED_SCALE),
				(int) (2*30 * API.SBGC_SPEED_SCALE),
				(int)(API.SBGC_SPEED_SCALE*vx),(int)(API.SBGC_SPEED_SCALE*vy), (int)(API.SBGC_SPEED_SCALE*vz));
		c.pack();
		c.send();
	}
	public static void consigne(int roll, int pitch, int yall) throws IOException, InterruptedException {
		ControlPack c = new ControlPack(API.SBGC_CONTROL_MODE_ANGLE, API.SBGC_CONTROL_MODE_ANGLE,
				API.SBGC_CONTROL_MODE_ANGLE, (int) (2*30 * API.SBGC_SPEED_SCALE), (int) (2*30 * API.SBGC_SPEED_SCALE),
				(int) (2*30 * API.SBGC_SPEED_SCALE),
				API.SBGC_DEGREE_TO_ANGLE_INT(6*roll),API.SBGC_DEGREE_TO_ANGLE_INT(6*pitch), API.SBGC_DEGREE_TO_ANGLE_INT(6*yall));
		System.out.println(API.SBGC_DEGREE_TO_ANGLE_INT(6*roll)+"\t"+API.SBGC_DEGREE_TO_ANGLE_INT(6*pitch)+"\t"+API.SBGC_DEGREE_TO_ANGLE_INT(6*yall));
		c.pack();
		c.send();
		Thread.sleep(300);
	}

	public static void automatic() throws IOException, InterruptedException {
		while (continu) {
			Double[] RollGyro = new Double[50];
			Double[] PitchGyro = new Double[50];
			Double[] YawGyro = new Double[50];
			Double[] RollAcc = new Double[50];
			Double[] PitchAcc = new Double[50];
			Double[] YawAcc = new Double[50];
			byte[] data = Com.getInstance().sendCMD(API.CMD_REALTIME_DATA_3, API.noData);
			System.out.println("data.length" + data.length);
			for (int j = 0; j < 50; j++) {
				for (int i = 0; i < data.length; i++) {
					RollAcc[j] = API.convertTwoBytesToInt(data[0], data[1]) / 512 * API.g;
					RollGyro[j] = API.convertTwoBytesToInt(data[2], data[3]) * API.factor;
					PitchAcc[j] = API.convertTwoBytesToInt(data[4], data[5]) / 512 * API.g;
					PitchGyro[j] = API.convertTwoBytesToInt(data[6], data[7]) * API.factor;
					YawAcc[j] = API.convertTwoBytesToInt(data[8], data[9]) / 512 * API.g;
					YawGyro[j] = API.convertTwoBytesToInt(data[10], data[11]) * API.factor;
					// ControlPack c = new
					// ControlPack(API.SBGC_CONTROL_MODE_ANGLE,API.SBGC_CONTROL_MODE_ANGLE,API.SBGC_CONTROL_MODE_ANGLE,
					// (int) (30 * API.SBGC_SPEED_SCALE), (int) (30 * API.SBGC_SPEED_SCALE), (int)
					// (30 * API.SBGC_SPEED_SCALE),
					// API.SBGC_DEGREE_TO_ANGLE_INT(roll),API.SBGC_DEGREE_TO_ANGLE_INT(pitch),API.SBGC_DEGREE_TO_ANGLE_INT(yall)
					// );
					// c.pack();
					// c.send();
				}
				Thread.sleep(200);
			}
		}
	}

}
