package serial;

import java.io.IOException;

/**
 * 
 * Add Pid control functions
 * 
 *
 */
public class PidControl {
	public static Com com = Com.getInstance();

	public static void main(String[] args) throws IOException, InterruptedException {

		Com.getInstance().sendCMD(API.CMD_MOTORS_ON, API.noData);

		Com.getInstance().sendCMD(API.CMD_MOTORS_OFF, API.noData);
		Com.getInstance().close();
	}

	public static byte[] buildByte(int Roll_P, int Roll_I, int Roll_D, int Roll_Power, int Roll_Pole, int Pitch_P,
			int Pitch_I, int Pitch_D, int Pitch_Power, int Pitch_Pole, int Yaw_P, int Yaw_I, int Yaw_D, int Yaw_Power,
			int Yaw_Pole) {
		byte[] built = { (byte) 255, (byte) (Roll_P & 0xFF), (byte) (Roll_I & 0xFF), (byte) (Roll_D & 0xFF),
				(byte) (Roll_Power & 0xFF), 1, (byte) (Roll_Pole & 0xFF), (byte) (Pitch_P & 0xFF),
				(byte) (Pitch_I & 0xFF), (byte) (Pitch_D & 0xFF), (byte) (Pitch_Power & 0xFF), 1,
				(byte) (Pitch_Pole & 0xFF), (byte) (Yaw_P & 0xFF), (byte) (Yaw_I & 0xFF), (byte) (Yaw_D & 0xFF),
				(byte) (Yaw_Power & 0xFF), 1, (byte) (Yaw_Pole & 0xFF) };
		return built;

	}

	public static void controlPid(int Roll_P, int Roll_I, int Roll_D, int Roll_Power, int Roll_Pole, int Pitch_P,
			int Pitch_I, int Pitch_D, int Pitch_Power, int Pitch_Pole, int Yaw_P, int Yaw_I, int Yaw_D, int Yaw_Power,
			int Yaw_Pole) throws IOException, InterruptedException {
		Com.getInstance().sendCMD(API.CMD_WRITE_PARAMS, buildByte(Roll_P, Roll_I, Roll_D, Roll_Power, Roll_Pole,
				Pitch_P, Pitch_I, Pitch_D, Pitch_Power, Pitch_Pole, Yaw_P, Yaw_I, Yaw_D, Yaw_Power, Yaw_Pole));

	}
}
