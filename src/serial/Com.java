package serial;

import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import com.fazecast.jSerialComm.SerialPort;

/**
 * Use jSerial Comm to build a connection
 * 
 * 
 */

public class Com {

	// Constants
	private SerialPort comPort = null;
	private OutputStream out = null;
	//private InputStream in = null;

	// SINGLETON IMPLEMENTION
	public static Com instance = null;

	public static Com getInstance() {
		if (instance == null) {
			instance = new Com();
			System.out.println(instance);
		}
		System.out.println(instance);

		return instance;
	}

	/**
	 * Constructor to find the right port
	 */
	private Com() throws ArrayIndexOutOfBoundsException {
		int o = 0;
		for (int i = 0; i < SerialPort.getCommPorts().length; i++) {
			System.out.println(SerialPort.getCommPorts()[i] + "" + i + ";");
			if (SerialPort.getCommPorts()[i].toString().indexOf("USB") != -1) {
				o = i;
			}
		}
		System.out.println("o " + o);
		try {
		comPort = SerialPort.getCommPorts()[o - 1];// to be regulated
		comPort.openPort();
		comPort.setBaudRate(115200);
		comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 100, 0);
		}catch(Exception e) {
			e.printStackTrace();
		}
		//in = comPort.getInputStream();
	}

	public void close() {
		comPort.closePort();
	}

	/**
	 * 
	 * send the command to the system 
	 * Header: character '>' 
	 * command ID - 1u
	 * data_size – 1u, may be zero header
	 * checksum = (command ID + data_size) modulo 256 - 1u 
	 * 
	 * Body: [array of bytes data_size length] body checksum - 1u Checksum
	 * is calculated as a sum of all bytes modulo 256. Example: outgoing command to
	 * read Profile2: 0x3E (>) 0x52 (R) 0x01 0x53 0x01 0x01 command id data size
	 * header checksum data body checksum header body Data type notation • 1u – 1
	 * byte unsigned • 1s – 1 byte signed • 2u – 2 byte unsigned (little-endian
	 * order) • 2s – 2 byte signed (little-endian order) • 4f – float (IEEE-754
	 * standard) • 4s – 4 bytes signed (little-endian order) • string – ASCII
	 * character array, first byte is array size
	 * 
	 * @param cmd
	 * @param data
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public byte[] sendCMD(int cmd, byte[] data) throws IOException, InterruptedException {
		byte[] response = null;
		int checksum = 0;
		int dataSize = data.length;

		out = comPort.getOutputStream();

		// Header
		out.write('>');
		out.write(cmd);
		out.write(dataSize);
		out.write(cmd + dataSize % 256);//header checksum
		// Body
		for (int i = 0; i < dataSize; i++) {
			checksum += data[i];
			out.write((data)[i]);
		}
		out.write(checksum % 256);//body checksum

		Thread.sleep(200); // wait 200ms for response
		response = new byte[comPort.bytesAvailable()];// get response

		comPort.readBytes(response, comPort.bytesAvailable());

		return response;

	}
//get Battery in volt
	public double getBatteryV() throws IOException, InterruptedException {
		byte[] response = this.sendCMD(API.CMD_REALTIME_DATA_3, API.noData); 
		byte a = response[59]; 
		byte b = response[60];
		int c = API.convertTwoBytesToInt(a, b);
		System.out.println("Battery: " + c * 0.01 + "V");
		return c * 0.01;
	}

}
