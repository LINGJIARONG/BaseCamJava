package ult;

public class Cmd {

	private double time;
	private int vx;
	private int vy;
	private int vz;
	public Cmd() {
		
	}
	public Cmd(float time, int vx, int vy,int vz) {
		super();
		this.time = time;
		this.vx = vx;
		this.vy = vy;
		this.vz=vz;
	}
	public double getTime() {
		return time;
	}
	public void setTime(double d) {
		this.time = d;
	}
	public int getVx() {
		return vx;
	}
	public void setVx(int vx) {
		this.vx = vx;
	}
	public int getVy() {
		return vy;
	}
	public void setVy(int vy) {
		this.vy = vy;
	}
	public int getVz() {
		return vz;
	}
	public void setVz(int vz) {
		this.vz = vz;
	}
	
}
