package gui;





public class Modele {
	/*
	 * les angles des trois axes
	 */
	private int angleRoll;
	private int anglePitch;
	private int angleYaw;
	
	/*
	 * les PID des trois axes
	 */
	private int P_Roll;
	private int I_Roll;
	private int D_Roll;
	
	private int P_Pitch;
	private int I_Pitch;
	private int D_Pitch;
	
	private int P_Yaw;
	private int I_Yaw;
	private int D_Yaw;
	
	/*
	 * les puissances des trois axes
	 */
	private int powerRoll;
	private int powerPitch;
	private int powerYaw;
	
	/*
	 * les nombres de pôles des trois axes
	 */
	private int nbPoleRoll;
	private int nbPolePitch;
	private int nbPoleYaw;
	
	
	
	//----------------------------------- les constructeurs ---------------------------------------------
	/**
	 * 
	 * par default 
	 */
	public Modele() {
		this.angleRoll = 0;
		this.anglePitch = 0;
		this.angleYaw = 0;
		P_Roll = 0;
		I_Roll = 0;
		D_Roll = 0;
		P_Pitch = 0;
		I_Pitch = 0;
		D_Pitch = 0;
		P_Yaw = 0;
		I_Yaw = 0;
		D_Yaw = 0;
		this.powerRoll = 0;
		this.powerPitch = 0;
		this.powerYaw = 0;
		this.nbPoleRoll = 0;
		this.nbPolePitch = 0;
		this.nbPoleYaw = 0;
	}
	
	public Modele(int angleRoll, int anglePitch, int angleYaw, int p_Roll, int i_Roll, int d_Roll, int p_Pitch,
			int i_Pitch, int d_Pitch, int p_Yaw, int i_Yaw, int d_Yaw, int powerRoll, int powerPitch, int powerYaw,
			int nbPoleRoll, int nbPolePitch, int nbPoleYaw) {
		this.angleRoll = angleRoll;
		this.anglePitch = anglePitch;
		this.angleYaw = angleYaw;
		P_Roll = p_Roll;
		I_Roll = i_Roll;
		D_Roll = d_Roll;
		P_Pitch = p_Pitch;
		I_Pitch = i_Pitch;
		D_Pitch = d_Pitch;
		P_Yaw = p_Yaw;
		I_Yaw = i_Yaw;
		D_Yaw = d_Yaw;
		this.powerRoll = powerRoll;
		this.powerPitch = powerPitch;
		this.powerYaw = powerYaw;
		this.nbPoleRoll = nbPoleRoll;
		this.nbPolePitch = nbPolePitch;
		this.nbPoleYaw = nbPoleYaw;
	}
	
	
	// ----------------------------------- getters and setters ----------------------------------------------------------

	public int getAngleRoll() {
		return angleRoll;
	}

	public int getAnglePitch() {
		return anglePitch;
	}

	public int getAngleYaw() {
		return angleYaw;
	}

	public int getP_Roll() {
		return P_Roll;
	}

	public int getI_Roll() {
		return I_Roll;
	}

	public int getD_Roll() {
		return D_Roll;
	}

	public int getP_Pitch() {
		return P_Pitch;
	}

	public int getI_Pitch() {
		return I_Pitch;
	}

	public int getD_Pitch() {
		return D_Pitch;
	}

	public int getP_Yaw() {
		return P_Yaw;
	}

	public int getI_Yaw() {
		return I_Yaw;
	}

	public int getD_Yaw() {
		return D_Yaw;
	}

	public int getPowerRoll() {
		return powerRoll;
	}

	public int getPowerPitch() {
		return powerPitch;
	}

	public int getPowerYaw() {
		return powerYaw;
	}

	public int getNbPoleRoll() {
		return nbPoleRoll;
	}

	public int getNbPolePitch() {
		return nbPolePitch;
	}

	public int getNbPoleYaw() {
		return nbPoleYaw;
	}

	public void setAngleRoll(int angleRoll) {
		this.angleRoll = angleRoll;
	}

	public void setAnglePitch(int anglePitch) {
		this.anglePitch = anglePitch;
	}

	public void setAngleYaw(int angleYaw) {
		this.angleYaw = angleYaw;
	}

	public void setP_Roll(int p_Roll) {
		P_Roll = p_Roll;
	}

	public void setI_Roll(int i_Roll) {
		I_Roll = i_Roll;
	}

	public void setD_Roll(int d_Roll) {
		D_Roll = d_Roll;
	}

	public void setP_Pitch(int p_Pitch) {
		P_Pitch = p_Pitch;
	}

	public void setI_Pitch(int i_Pitch) {
		I_Pitch = i_Pitch;
	}

	public void setD_Pitch(int d_Pitch) {
		D_Pitch = d_Pitch;
	}

	public void setP_Yaw(int p_Yaw) {
		P_Yaw = p_Yaw;
	}

	public void setI_Yaw(int i_Yaw) {
		I_Yaw = i_Yaw;
	}

	public void setD_Yaw(int d_Yaw) {
		D_Yaw = d_Yaw;
	}

	public void setPowerRoll(int powerRoll) {
		this.powerRoll = powerRoll;
	}

	public void setPowerPitch(int powerPitch) {
		this.powerPitch = powerPitch;
	}

	public void setPowerYaw(int powerYaw) {
		this.powerYaw = powerYaw;
	}

	public void setNbPoleRoll(int nbPoleRoll) {
		this.nbPoleRoll = nbPoleRoll;
	}

	public void setNbPolePitch(int nbPolePitch) {
		this.nbPolePitch = nbPolePitch;
	}

	public void setNbPoleYaw(int nbPoleYaw) {
		this.nbPoleYaw = nbPoleYaw;
	}
	
	
	
	
	
	
}
