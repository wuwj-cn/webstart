package com.saber.comm;

import java.applet.Applet;
import java.awt.Graphics;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.TooManyListenersException;

import javax.comm.CommDriver;
import javax.comm.CommPortIdentifier;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;
import javax.comm.UnsupportedCommOperationException;

/**
 * 串口通信类
 * 
 * @author wuwj
 */
public class Comm extends Applet implements Runnable, SerialPortEventListener {

	private static final String LIB_PATH_SUFFIX = "system32";
	private static final String DLL_FILE = "win32com.dll";
	private String driverName = "com.sun.comm.Win32Driver";

	static CommPortIdentifier portId;
	static Enumeration portList;

	InputStream inputStream;
	SerialPort serialPort;
	Thread readThread;

	static {
		System.setSecurityManager(null); // 禁用安全管理器(必须写)
	}

	@Override
	public void init() {
		downloadDll();
		try {
			System.loadLibrary("win32com");
			CommDriver driver = (CommDriver) Class.forName(driverName)
					.newInstance();
			driver.initialize();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	@Override
	public void start() {

		portList = CommPortIdentifier.getPortIdentifiers();

		while (portList.hasMoreElements()) {
			portId = (CommPortIdentifier) portList.nextElement();
			System.out.println(portId.getName());

			while (portList.hasMoreElements()) {
				portId = (CommPortIdentifier) portList.nextElement();
				if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
					 if (portId.getName().equals("COM1")) {
//					if (portId.getName().equals("/dev/term/a")) {
						try {
							serialPort = (SerialPort) portId.open("SimpleReadApp", 2000);
						} catch (PortInUseException e) {}
						try {
							inputStream = serialPort.getInputStream();
						} catch (IOException e) {}
						try {
							serialPort.addEventListener(this);
						} catch (TooManyListenersException e) {}
						serialPort.notifyOnDataAvailable(true);
						try {
							serialPort.setSerialPortParams(9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
						} catch (UnsupportedCommOperationException e) {}
						readThread = new Thread(this);
						readThread.start();
					}
				}
			}
		}
	}

	@Override
	public void destroy() {
		try {
			if (inputStream != null) {
				inputStream.close();
			}
			if (serialPort != null) {
				serialPort.close();
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	@Override
	public void paint(Graphics g) {
		String dirs = System.getProperty("java.library.path");
		String path = super.getAppletContext().toString();
		g.drawString(dirs, 20, 10);
		g.drawString(path, 20, 30);
	}

	public void downloadDll() {
		try {
			String dirs = System.getProperty("java.library.path");
			String[] libs = dirs.split(";");
			String libPath = "";
			for (String lib : libs) {
				if (lib.toLowerCase().endsWith(LIB_PATH_SUFFIX)) {
					libPath = lib;
					break;
				}
			}
			File dll = new File(libPath, DLL_FILE);
			if (!dll.exists()) {
				URL url = new URL(super.getCodeBase() + DLL_FILE);
				InputStream is = url.openConnection().getInputStream();
				FileOutputStream fos = new FileOutputStream(dll);
				byte[] buf = new byte[256];
				int len = 0;
				while ((len = is.read(buf)) != -1) {
					fos.write(buf, 0, len);
				}
				fos.flush();
				fos.close();
				is.close();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void serialEvent(SerialPortEvent event) {
		switch (event.getEventType()) {
		case SerialPortEvent.BI:
		case SerialPortEvent.OE:
		case SerialPortEvent.FE:
		case SerialPortEvent.PE:
		case SerialPortEvent.CD:
		case SerialPortEvent.CTS:
		case SerialPortEvent.DSR:
		case SerialPortEvent.RI:
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
			break;
		case SerialPortEvent.DATA_AVAILABLE:
			byte[] readBuffer = new byte[20];

			try {
				while (inputStream.available() > 0) {
					int numBytes = inputStream.read(readBuffer);
				}
				System.out.print(new String(readBuffer));
			} catch (IOException e) {
			}
			break;
		}
	}

	@Override
	public void run() {
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
		}
	}

}
