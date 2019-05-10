package ult;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class ReadFile {

    public ReadFile() {

        File f=new File("src/data.txt");
        System.out.println(f.getAbsolutePath());

        try (BufferedReader br = new BufferedReader( new FileReader(f))) {

            // read line by line
            String line;
           
            while ((line = br.readLine()) != null) {
            	String[] parts = line.split("\t");
            	Cmd cmd=new Cmd();  
            	cmd.setTime(Double.parseDouble(parts[0]));
            	cmd.setVx(Integer.parseInt(parts[1]));
            	cmd.setVy(Integer.parseInt(parts[2]));
            	cmd.setVz(Integer.parseInt(parts[3]));
            	Database.list.add(cmd);
            	for(String o:parts) {
            		try {
            		System.out.println(Integer.parseInt(o));
            		Integer.parseInt(o);
            		}catch(NumberFormatException e) {
                		System.out.println(Double.parseDouble(o));

            		}
            	}
            }

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        System.out.println();

    }

}