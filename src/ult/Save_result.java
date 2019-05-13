package ult;
import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;

public class Save_result {

	public static void  write_result(double roll,double pitch,double yaw,double v_roll,double v_pitch,double v_yall) {
		try { 
			File writename = new File("src/result.txt"); // 
			if(!writename.exists()) {
				writename.createNewFile();
				System.out.println("new file");
			}
			BufferedWriter out = new BufferedWriter(new FileWriter(writename,true));
			out.write(roll+"\t"+pitch+"\t"+yaw+"\t"+v_roll+"\t"+v_pitch+"\t"+v_yall+"\r\n"); // \r\n 
			out.flush(); //  
			out.close(); //  
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

}
