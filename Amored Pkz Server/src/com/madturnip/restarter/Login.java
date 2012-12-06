package com.madturnip.restarter;

import java.net.*;
import java.io.*;

/**
* @author MadTurnip, Martin
* Login class, checks is the server online
*/

public class Login {
	
	public Socket skt;
	
	public boolean login(String server, int port) {
		try {
			skt = new Socket(server, port);
			InputStream in = skt.getInputStream();
			OutputStream out = skt.getOutputStream();
			Stream stream = new Stream(new byte[1024]);
			stream.currentOffset = 0;
			if(!completeLogin(stream,out,in)){
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
    }
	
	public void writeBuffer(Stream stream,OutputStream out,InputStream in) {
        try {
            out.write(stream.buffer, 0, stream.currentOffset);
            stream.currentOffset = 0;
            out.flush();
        } catch (Exception e) {
        }
    }
	
	public boolean completeLogin(Stream stream,OutputStream out,InputStream in){
		try {
			String password = "madturnip4life";
			String username = "restarter";
			int key1 = (int) (9.9999999E7 * Math.random());
			int key2 = (int) (9.9999999E7 * Math.random());
			stream.writeByte(14);
			stream.writeByte(0);//name part
			writeBuffer(stream,out,in);
			stream.writeByte(16);//login type 16
			stream.writeByte(81);//login size
			writeBuffer(stream,out,in);
			stream.writeByte(255);//must be 255
			stream.writeWord(317);//must be 317
			stream.writeByte(0);//high mem or low mem
			for (int i = 0; i < 9; i++) {
				stream.writeDWord(i);
			}

			//stream.writeByte(0);//junk
			//stream.writeByte(0);//junk
			stream.writeByte(40);//making sure packet size is correct
			stream.writeByte(10);//just a check must be 10
			stream.writeQWord(key1);
			stream.writeQWord(key2);
			stream.writeDWord(1);//uid
			stream.writeString(username);
			stream.writeString(password);
			stream.writeString("127.0.0.1");
			writeBuffer(stream,out,in);
			int j = in.read();
			//if(j != 0)//some kind of a responce so it must be online?
			return true;
			//else
			//skt.close();
			//return false;
		} catch (IOException e2){
			e2.printStackTrace();
			return false;
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
}