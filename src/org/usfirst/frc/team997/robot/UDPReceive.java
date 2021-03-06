package org.usfirst.frc.team997.robot;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import org.usfirst.frc.team997.robot.CustomDashboard;

public class UDPReceive {
	private DatagramSocket dsocket;
	private DatagramPacket packet;
	private byte[] buffer;
	private String string = "";

	public UDPReceive() {
		
		try {
			int port = 10030;

			// Create a socket to listen on the port.
			dsocket = new DatagramSocket(port);
			dsocket.setSoTimeout(2);
			
			// Create a buffer to read datagrams into.
			buffer = new byte[2048];

			// Create a packet to receive data into the buffer
			packet = new DatagramPacket(buffer, buffer.length);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public VisionStorage work() {
		VisionStorage result = null;
		try {
			// Wait to receive a datagram
			dsocket.receive(packet);

			// Convert the contents to a string, and display them
			string += new String(buffer, 0, packet.getLength());
			int packetEnd;
			
			
			if ((packetEnd = string.indexOf(';')) != -1) {
				float hypotnuse = Float.parseFloat(string.substring(0, 8));
				float horizontalOffset = Float.parseFloat(string.substring(9, 17));
				float distance = hypotnuse; //(float) Math.sqrt((hypotnuse*hypotnuse)-(56*56));
				string = string.substring(packetEnd + 1);
				result = new VisionStorage(distance, horizontalOffset);
				CustomDashboard.putNumber("Distance", distance);
				CustomDashboard.putNumber("Horizontal Offset", horizontalOffset);
			} 
			// Reset the length of the packet before reusing it.
			packet.setLength(buffer.length);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}