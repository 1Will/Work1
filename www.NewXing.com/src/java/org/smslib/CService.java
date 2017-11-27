// SMSLib for Java
// An open-source API Library for sending and receiving SMS via a GSM modem.
// Copyright (C) 2002-2006, Thanasis Delenikas, Athens/GREECE
// Web Site: http://www.smslib.org
//
// SMSLib is distributed under the LGPL license.
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
// 
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
// 
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA

package org.smslib;

import java.io.*;
import java.util.*;
import org.apache.log4j.*;

/**
*	This is the main SMSLib service class.
*/
public class CService
{
	/**
	*	Dummy synchronization object.
	*/
	public static Object _SYNC_;

	/**
	*	Product name.
	*/
	public static final String _name = "SMSLib for Java";

	/**
	*	Product version.
	*/
	public static final String _version = "v2.0.0 (B1)";

	/**
	*	Receive modes.
	*	<p>
	*	Sync: Synchronous Reading.<br />
	*	AsyncCnmi: Asynchronous, based on CNMI indications.<br />
	*	AsyncPoll: Asynchronous, based on polling.
	*
	*	@see	#setReceiveMode(ReceiveMode)
	*	@see	#getReceiveMode()
	*/
	public static enum ReceiveMode { Sync, AsyncCnmi, AsyncPoll }

	private static final String LOG_CONF = "smslib-log.conf";

	private int keepAliveInterval = 30 * 1000;
	private int asyncPollInterval = 10 * 1000;
	
	private CIncomingMessage.MessageClass asyncRecvClass = CIncomingMessage.MessageClass.All;

	private int retriesNoResponse = 4;
	private int delayNoResponse = 2000;

	private int retriesCmsErrors = 4;
	private int delayCmsErrors = 2000;

	private Logger log;

	private static final String VALUE_NOT_REPORTED = "* N/A *";

	private String smscNumber;
	private String simPin;
	private ReceiveMode receiveMode;
	private AbstractATHandler atHandler;
	private CNewMsgMonitor newMsgMonitor;
	private CSerialDriver serialDriver;
	private boolean connected;
	private CDeviceInfo deviceInfo;
	private CKeepAliveThread keepAliveThread;
	private CReceiveThread receiveThread;
	private ISmsMessageListener messageHandler;
	private int outMpRefNo;

	/**
	*	CService constructor.
	*
	*	@param	port	The comm port to use (i.e. COM1, /dev/ttyS1 etc).
	*	@param	baud	The baud rate. 57600 is a good number to start with.
	*	@param	gsmDeviceManufacturer	The manufacturer of the modem (i.e. Wavecom, Nokia, Siemens, etc).
	*	@param	gsmDeviceModel	The model (i.e. M1306B, 6310i, etc).
	*/
	public CService(String port, int baud, String gsmDeviceManufacturer, String gsmDeviceModel)
	{
		_SYNC_ = new Object();

		log = Logger.getLogger("org.smslib");
		if (new File(LOG_CONF).exists()) PropertyConfigurator.configure(LOG_CONF);
		else
		{
			BasicConfigurator.configure();
			log.setLevel(Level.WARN);
			log.setLevel(Level.DEBUG);
		}

		smscNumber = "";
		simPin = null;

		connected = false;
		serialDriver = new CSerialDriver(port, baud, log);
		deviceInfo = new CDeviceInfo();
		newMsgMonitor = new CNewMsgMonitor();

		log.info(_name + " / " + _version);
		log.info("Using port: " + port + " @ " + baud + " baud.");
		log.info("JRE Version: " + System.getProperty("java.version"));
		log.info("JRE Impl Version: " + System.getProperty("java.vm.version"));
		log.info("O/S: " + System.getProperty("os.name") + " / " + System.getProperty("os.arch") + " / " + System.getProperty("os.version"));

		try
		{
			atHandler = AbstractATHandler.load(serialDriver, log, this, gsmDeviceManufacturer, gsmDeviceModel);
			log.info("Using " + atHandler.getDescription() + " AT handler.");
		}
		catch (Exception ex)
		{
			log.fatal("CANNOT INITIALIZE HANDLER (" + ex.getMessage() + ")");
		}

		receiveMode = ReceiveMode.Sync;
		receiveThread = null;

		outMpRefNo = new Random().nextInt();
		if (outMpRefNo < 0) outMpRefNo *= -1;
		outMpRefNo %= 65536;
	}

	/**
	*	Return the status of the connection.<p>
	*	<strong>Warning</strong>: The method return the "theoretical" status of the connection, without testing the actual connection at the time of the call.
	*
	*	@return	True if the GSM device is connected.
	*	@see #connect()
	*	@see #disconnect()
	*/
	public boolean getConnected() { return connected; }

	/**
	*	Returns the DeviceInfo class.
	*
	*	@see CDeviceInfo
	*/
	public CDeviceInfo getDeviceInfo() { return deviceInfo; }

	/**
	*	Sets the SMSC number. Needed in rare cases - normally, you should <strong>not</strong> set the SMSC number yourself and let the GSM device get it from its SIM.
	*
	*	@param	smscNumber	The SMSC number (international format).
	*	@see	#getSmscNumber()
	*/
	public void setSmscNumber(String smscNumber) { this.smscNumber = smscNumber; }

	/**
	*	Returns the SMSC number previously set with setSmscNumber() call.
	*
	*	@return The SMSC number.
	*	@see #setSmscNumber(String)
	*/
	public String getSmscNumber() { return smscNumber; }

	/**
	*	Sets the SIM Pin.
	*
	*	@param simPin	The SIM pin code.
	*	@see	#getSimPin()
	*/
	public void setSimPin(String simPin) { this.simPin = simPin; }

	/**
	*	Returns the SIM Pin previously set with setSimPin().
	*
	*	@return The SIM Pin code.
	*	@see #setSimPin(String)
	*/
	public String getSimPin() { return simPin; }

	public void setMessageHandler(ISmsMessageListener messageHandler) { this.messageHandler = messageHandler; }
	protected ISmsMessageListener getMessageHandler() { return messageHandler; }

	/**
	*	Sets the Async Poll Interval, in seconds, i.e. every how many seconds will SMSLib
	*	poll the GSM modem for new messages.
	*
	*	@param	secs	The interval in seconds.
	*	@see	#getAsyncPollInterval()
	*	@see	#setAsyncRecvClass(CIncomingMessage.MessageClass)
	*	@see	#getAsyncRecvClass()
	*/
	public void setAsyncPollInterval(int secs) { this.asyncPollInterval = secs * 1000; }
	/**
	*	Returns the Async Poll Interval, in seconds.
	*
	*	@return	The Poll Interval in seconds.
	*	@see	#setAsyncPollInterval(int)
	*	@see	#setAsyncRecvClass(CIncomingMessage.MessageClass)
	*	@see	#getAsyncRecvClass()
	*/
	public int getAsyncPollInterval() { return (asyncPollInterval / 1000); }

	public void setAsyncRecvClass(CIncomingMessage.MessageClass msgClass) { asyncRecvClass = msgClass; }
	public CIncomingMessage.MessageClass getAsyncRecvClass() { return asyncRecvClass; }

	/**
	*	Sets the Keep-Alive Interval, i.e. every how many seconds the Keep-Alive thread will run
	*	and send a dummy OK command to the GSM modem. This is used to keep the serial port
	*	alive and prevent it from timing out. The interval is, by default, set to 30 seconds which
	*	should be enough for all modems.
	*
	*	@param	secs	The Keep-Alive Interval in seconds.
	*	@see	#getKeepAliveInterval()
	*/
	public void setKeepAliveInterval(int secs) { this.keepAliveInterval = secs * 1000; }
	/**
	*	Returns the Keep-Alive Interval, in seconds.
	*
	*	@return	The Keep-Alive Interval in seconds.
	*	@see	#setKeepAliveInterval(int)
	*/
	public int getKeepAliveInterval() { return keepAliveInterval / 1000; }

	public void setRetriesNoResponse(int retries) { this.retriesNoResponse = retries; }
	public int getRetriesNoResponse() { return retriesNoResponse; }

	public void setDelayNoResponse(int delay) { this.delayNoResponse = delay * 1000; }
	public int getDelayNoResponse() { return delayNoResponse; }

	public void setRetriesCmsErrors(int retries) { this.retriesCmsErrors = retries; }
	public int getRetriesCmsErrors() { return retriesCmsErrors; }

	public void setDelayCmsErrors(int delay) { this.delayCmsErrors = delay * 1000; }
	public int getDelayCmsErrors() { return delayCmsErrors; }

	/**
	*	Returns the Log4J logger object used by SMSLib.
	*
	*	@return The Log4J logger object.
	*/
	public Logger getLogger() { return log; }

	/**
	*	Sets the receive mode.
	*
	*	@param	receiveMode	The Receive Mode.
	*/
	public void setReceiveMode(ReceiveMode receiveMode) throws Exception
	{
		this.receiveMode = receiveMode;
		if (connected)
		{
			if (receiveMode == ReceiveMode.AsyncCnmi)
			{
				if (!atHandler.enableIndications()) log.warn("Could not enable CMTI indications, continuing without them...");
			}
			else
			{
				if (!atHandler.disableIndications()) log.warn("Could not disable CMTI indications, continuing without them...");
			}
		}
	}

	/**
	*	Returns the Receive Mode.
	*
	*	@return	The Receive Mode.
	*/
	public ReceiveMode getReceiveMode() { return receiveMode; }

	/**
	*	Connects to the GSM modem.<p>
	*	The connect() function should be called before any operations. Its purpose is to open the serial link, check for modem existence, initialize modem, start background threads and prepare for subsequent operations.
	*
	*	@see #disconnect()
	*	@throws NotConnectedException	No compatible modem (or no modem at all) found.
	*	@throws AlreadyConnectedException	If already connected.
	*	@throws NoPinException		If PIN is requested from the modem but no PIN is defined.
	*	@throws InvalidPinException	If the defined PIN is not accepted by the modem.
	*	@throws NoPduSupportException	The modem does not support PDU mode - fatal error!
	*/
	public void connect() throws Exception
	{
		synchronized (_SYNC_)
		{
			if (getConnected()) throw new AlreadyConnectedException();
			else try
			{
				serialDriver.open();
				atHandler.sync();
				serialDriver.emptyBuffer();
				atHandler.reset();
				serialDriver.setNewMsgMonitor(newMsgMonitor);
				if (atHandler.isAlive())
				{
					if (atHandler.waitingForPin())
					{
						if (getSimPin() == null) throw new NoPinException();
						else if (!atHandler.enterPin(getSimPin())) throw new InvalidPinException();
					}
					atHandler.init();
					atHandler.echoOff();
					waitForNetworkRegistration();
					atHandler.setVerboseErrors();
					if (!atHandler.setPduMode()) throw new NoPduSupportException();
					connected = true;
					setReceiveMode(receiveMode);
					refreshDeviceInfo();

					receiveThread = new CReceiveThread();
					receiveThread.start();
					keepAliveThread = new CKeepAliveThread();
					keepAliveThread.start();
				}
				else throw new NotConnectedException("GSM device is not responding.");
			}
			catch (Exception e)
			{
				disconnect();
				throw e;
			}
		}
	}

	/**
	*	Disconnects from the GSM modem.<p>
	*	This should be the last function called. Closes serial connection, shuts down background threads and performs clean-up.<p>
	*	<strong>Notes</strong>
	*	<ul>
	*		<li>Do not connect and disconnect continously - at least if you can avoid it. It takes time and resources. Connect once and stay connected.</li>
	*	</ul>
	*
	*	@see	#connect()
	*/
	public void disconnect()
	{
		serialDriver.killMe();
		if (receiveThread != null)
		{
			receiveThread.killMe();
			receiveThread.interrupt();
			while (!receiveThread.killed()) try { receiveThread.join(); } catch (Exception e) {}
			receiveThread = null;
		}
		if (keepAliveThread != null)
		{
			keepAliveThread.killMe();
			keepAliveThread.interrupt();
			while (!keepAliveThread.killed()) try { keepAliveThread.join(); } catch (Exception e) {}
			keepAliveThread = null;
		}
		try { serialDriver.close(); } catch (Exception e) {}
		connected = false;
	}

	/**
	*	Reads SMS messages from the GSM modem.<p>
	*	This method fills the supplied list with all messages of the specified message class.
	*
	*	@param messageList	The list to be populated with messages.
	*	@param messageClass	The message class of the messages to read
	*	@throws NotConnectedException	Either connect() is not called or modem has been disconnected.
	*	@see CIncomingMessage
	*	@see CIncomingMessage.MessageClass
	*	@see #sendMessage(COutgoingMessage)
	*/
	public void readMessages(List<CIncomingMessage> messageList, CIncomingMessage.MessageClass messageClass) throws Exception
	{
		int i, j, memIndex;
		String response, line, pdu;
		BufferedReader reader;
		CIncomingMessage mpMsg = null;
		String memLoc;

		synchronized (_SYNC_)
		{
			if (getConnected())
			{
				atHandler.switchToCmdMode();
				memLoc = atHandler.getMemoryLocations();
				for (int ml = 0; ml < (memLoc.length() / 2); ml++)
				{
					if (atHandler.setMemoryLocation(memLoc.substring((ml * 2), (ml * 2) + 2)))
					{
						response = atHandler.listMessages(messageClass);
						response = response.replaceAll("\\s+OK\\s+", "\nOK");
						reader = new BufferedReader(new StringReader(response));
						for (;;)
						{
							line = reader.readLine();
							if (line == null) break;
							line = line.trim();
							if (line.length() > 0) break;
						}
						while (true)
						{
							if (line == null) break;
							line = line.trim();
							if (line.length() <= 0 || line.equalsIgnoreCase("OK")) break;
							i = line.indexOf(':');
							j = line.indexOf(',');
							memIndex = Integer.parseInt(line.substring(i + 1, j).trim());
							pdu = reader.readLine();
							try
							{
								if (isIncomingMessage(pdu))
								{
									CIncomingMessage msg;

									msg = new CIncomingMessage(pdu, memIndex, memLoc.substring((ml * 2), (ml * 2) + 2));
									if (msg.getMpRefNo() == 0)
									{
										messageList.add(msg);
										deviceInfo.getStatistics().incTotalIn();
									}
									else
									{
										if (msg.getMpSeqNo() == 1)
										{
											if (mpMsg == null)
											{
												mpMsg = new CIncomingMessage(pdu, memIndex, memLoc.substring((ml * 2), (ml * 2) + 2));
												mpMsg.setMpMemIndex(memIndex);
											}
										}
										else if ((msg.getMpRefNo() == mpMsg.getMpRefNo()) && (msg.getMpSeqNo() == mpMsg.getMpSeqNo() + 1))
										{
											mpMsg.addText(msg.getText());
											mpMsg.setMpSeqNo(msg.getMpSeqNo());
											mpMsg.setMpMemIndex(memIndex);
											if (mpMsg.getMpSeqNo() == mpMsg.getMpMaxNo())
											{
												mpMsg.setMemIndex(-1);
												messageList.add(mpMsg);
												mpMsg = null;
											}
										}
									}
								}
								else if (isStatusReportMessage(pdu))
								{
									messageList.add(new CStatusReportMessage(pdu, memIndex, memLoc.substring((ml * 2), (ml * 2) + 2)));
									deviceInfo.getStatistics().incTotalIn();
								}
							}
							catch (Exception e)
							{
								log.error("Unhandled SMS in inbox, skipping...");
							}
							line = reader.readLine();
						}
						reader.close();
					}
				}
			}
			else throw new NotConnectedException();
		}
	}

	/**
	*	Sends an SMS message from the GSM modem.<p>
	*	This method actually wraps the message in a list and calls #sendMessage(List) that does the job.
	*
	*	@param	message	The message to be sent.
	*	@throws NotConnectedException	Either connect() is not called or modem has been disconnected.
	*	@see	#sendMessage(List)
	*	@see	#readMessages(List, CIncomingMessage.MessageClass)
	*/
	public void sendMessage(COutgoingMessage message) throws Exception
	{
		List<COutgoingMessage> messageList;

		messageList = new LinkedList<COutgoingMessage>();
		messageList.add(message);
		sendMessage(messageList);
	}

	/**
	*	Sends a list of messages from the GSM modem.<p>
	*	The function iterates through the supplied list of COutgoingMessage objects and  tries to send them.<p>
	*	Upon succesful sending, each COutgoingMessage object should have its RefNo and DispatchDate fields set to specific values. Upon failure, the RefNo will be set to 0 and DispatchDate set to null.
	*
	*	@param	messageList		A list of COutgoingMessage objects presenting messages that will be sent out.
	*	@throws NotConnectedException	Either connect() is not called or modem has been disconnected.
	*	@see	#sendMessage(COutgoingMessage)
	*	@see	COutgoingMessage
	*	@see	CWapSIMessage
	*	@see	#readMessages(List, CIncomingMessage.MessageClass)
	*/
	public void sendMessage(List<COutgoingMessage> messageList) throws Exception
	{
		COutgoingMessage message;
		int i, j, refNo;
		String pdu;

		if (getConnected())
		{
			synchronized (_SYNC_)
			{
				atHandler.keepGsmLinkOpen();
			}
			for (i = 0; i < messageList.size(); i++)
			{
				message = messageList.get(i);
				if (!message.isBig())
				{
					pdu = message.getPDU(smscNumber, 0, 0);
					j = pdu.length();
					j /= 2;
					if (smscNumber == null) ; // Do nothing on purpose!
					else if (smscNumber.length() == 0) j--;
					else
					{
						int smscNumberLen = smscNumber.length();
						if (smscNumber.charAt(0) == '+') smscNumberLen--;
						if (smscNumberLen % 2 != 0) smscNumberLen++;
						int smscLen = (2 + smscNumberLen) / 2;
						j = j - smscLen - 1;
					}
					synchronized (_SYNC_)
					{
						refNo = atHandler.sendMessage(j, pdu);
					}
					if (refNo >= 0)
					{
						message.setRefNo(refNo);
						message.setDispatchDate(new Date());
						deviceInfo.getStatistics().incTotalOut();
					}
					else if (refNo == -2)
					{
						disconnect();
						break;
					}
					else message.setDispatchDate(null);
				}
				else
				{
					for (int partNo = 1; partNo <= message.getNoOfParts(); partNo++)
					{
						pdu = message.getPDU(smscNumber, outMpRefNo, partNo);
						j = pdu.length();
						j /= 2;
						if (smscNumber == null) ; // Do nothing on purpose!
						else if (smscNumber.length() == 0) j--;
						else
						{
							int smscNumberLen = smscNumber.length();
							if (smscNumber.charAt(0) == '+') smscNumberLen--;
							if (smscNumberLen % 2 != 0) smscNumberLen++;
							int smscLen = (2 + smscNumberLen) / 2;
							j = j - smscLen - 1;
						}
						synchronized (_SYNC_)
						{
							refNo = atHandler.sendMessage(j, pdu);
						}
						if (refNo >= 0)
						{
							message.setRefNo(refNo);
							message.setDispatchDate(new Date());
							deviceInfo.getStatistics().incTotalOut();
						}
						else if (refNo == -2)
						{
							disconnect();
							break;
						}
						else message.setDispatchDate(null);
					}
					outMpRefNo = (outMpRefNo + 1) % 65536;
				}
			}
		}
		else throw new NotConnectedException();
	}

	protected void deleteMessage(int memIndex, String memLocation) throws Exception
	{
		synchronized (_SYNC_)
		{
			if (getConnected()) atHandler.deleteMessage(memIndex, memLocation);
			else throw new NotConnectedException();
		}
	}

	/**
	*	Deletes a message from the modem's memory.<p>
	*	<strong>Warning</strong>: Do not pass invalid CIncomingMessage objects to this call - You may corrupt your modem's storage!<p>
	*	Delete operations are irreversible.
	*
	*	@param message	The CIncomingMessage object previously read with readMessages() call.
	*	@throws NotConnectedException	Either connect() is not called or modem has been disconnected.
	*	@see	CIncomingMessage
	*	@see	#readMessages(List, CIncomingMessage.MessageClass)
	*/
	public void deleteMessage(CIncomingMessage message) throws Exception
	{
		synchronized (_SYNC_)
		{
			if (message.getMemIndex() >= 0) deleteMessage(message.getMemIndex(), message.getMemLocation());
			else if ((message.getMemIndex() == -1) && (message.getMpMemIndex().length() != 0))
			{
				StringTokenizer tokens = new StringTokenizer(message.getMpMemIndex(), ",");
				while (tokens.hasMoreTokens())
					deleteMessage(Integer.parseInt(tokens.nextToken()), message.getMemLocation());
			}
		}
	}

	/**
	*	Deletes ALL messages of the specified message class.<p>
	*	Delete operations are irreversible.
	*
	*	@param	messageClass	The message class.
	*	@throws NotConnectedException	Either connect() is not called or modem has been disconnected.
	*	@see CIncomingMessage.MessageClass
	*/
	public void deleteMessages(CIncomingMessage.MessageClass messageClass) throws Exception
	{
		List<CIncomingMessage> msgList;

		synchronized (_SYNC_)
		{
			msgList = new LinkedList<CIncomingMessage>();
			readMessages(msgList, messageClass);
			for (int i = 0; i < msgList.size(); i++)
				deleteMessage(msgList.get(i));
		}
	}

	/**
	*	Reads (or refreshes) all GSM modem information (like manufacturer, signal level, etc).<p>
	*	This method is called automatically upon connection. Should you require fresh info, you should
	*	call it yourself when you need it.
	*
	*	@throws NotConnectedException	Either connect() is not called or modem has been disconnected.
	*	@see CDeviceInfo
	*/	
	public void refreshDeviceInfo() throws Exception
	{
		synchronized (_SYNC_)
		{
			if (getConnected())
			{
				if (deviceInfo.manufacturer.length() == 0) deviceInfo.manufacturer = getManufacturer();
				if (deviceInfo.model.length() == 0) deviceInfo.model = getModel();
				if (deviceInfo.serialNo.length() == 0) deviceInfo.serialNo = getSerialNo();
				if (deviceInfo.imsi.length() == 0) deviceInfo.imsi = getImsi();
				if (deviceInfo.swVersion.length() == 0) deviceInfo.swVersion = getSwVersion();
				deviceInfo.gprsStatus = getGprsStatus();
				deviceInfo.batteryLevel = getBatteryLevel();
				deviceInfo.signalLevel = getSignalLevel();
			}
			else throw new NotConnectedException();
		}
	}

	public void catchAsyncException(Exception e) { e.printStackTrace(); }

	protected boolean isAlive()
	{
		boolean alive;

		if (!connected) alive = false;
		else try { alive = atHandler.isAlive(); } catch (Exception e) { alive = false; }
		return alive;
	}

	private boolean waitForNetworkRegistration() throws Exception
	{
		StringTokenizer tokens;
		String response, flag;

		while (true)
		{
			response = atHandler.getNetworkRegistration();
			if (response.indexOf("ERROR") > 0) return false;
			response = response.replaceAll("\\s+OK\\s+", "");
			response = response.replaceAll("\\s+", "");
			response = response.replaceAll("\\+CREG:", "");
			tokens = new StringTokenizer(response, ",");
			try
			{
				tokens.nextToken();
				flag = tokens.nextToken();
				switch (Integer.parseInt(flag))
				{
					case 0:
						log.error("GSM: Auto-registration disabled!");
						throw new OopsException("GSM Network Auto-Registration disabled!");
					case 1:
						log.info("GSM: Registered to home network.");
						return true;
					case 2:
						log.warn("GSM: Not registered, searching for network...");
						break;
					case 3:
						log.error("GSM: Network registration denied!");
						throw new OopsException("GSM Network Registration denied!");
					case 4:
						log.error("GSM: Unknown registration error!");
						throw new OopsException("GSM Network Registration error!");
					case 5:
						log.info("GSM: Registered to foreign network (roaming).");
						return true;
				}
			}
			catch (Exception e)
			{
				log.warn("waitForNetworkRegistration(): Invalid response, keep searching for network...");
			}
			Thread.sleep(5000);
		}
	}

	/**
	*	Send a custom AT command to the modem and returns its response.
	*
	*	@param	cmd	The custom AT command
	*	@return	The modem response
	*/
	public String sendCustomCmd(String cmd) throws Exception
	{
		synchronized (_SYNC_) { return atHandler.send(cmd); }
	}

	private String getManufacturer() throws Exception
	{
		String response;

		response = atHandler.getManufacturer();
		if (response.matches("\\s*[\\p{ASCII}]*\\s+ERROR(?:: \\d+)?\\s+")) return VALUE_NOT_REPORTED;
		response = response.replaceAll("\\s+OK\\s+", "");
		response = response.replaceAll("\\s+", "");
		return response;
	}

	private String getModel() throws Exception
	{
		String response;

		response = atHandler.getModel();
		if (response.matches("\\s*[\\p{ASCII}]*\\s+ERROR(?:: \\d+)?\\s+")) return VALUE_NOT_REPORTED;
		response = response.replaceAll("\\s+OK\\s+", "");
		response = response.replaceAll("\\s+", "");
		return response;
	}

	private String getSerialNo() throws Exception
	{
		String response;

		response = atHandler.getSerialNo();
		if (response.matches("\\s*[\\p{ASCII}]*\\s+ERROR(?:: \\d+)?\\s+")) return VALUE_NOT_REPORTED;
		response = response.replaceAll("\\s+OK\\s+", "");
		response = response.replaceAll("\\s+", "");
		return response;
	}

	private String getImsi() throws Exception
	{
		return "** MASKED **";
		// IMSI is masked on purpose.
		// Uncomment following code for IMSI to be reported.
		/*
		 * String response; response = atHandler.getImsi(); if
		 * (response.matches("\\s*[\\p{ASCII}]*\\s+ERROR(?:: \\d+)?\\s+"))
		 * return VALUE_NOT_REPORTED; response =
		 * response.replaceAll("\\s+OK\\s+", ""); response =
		 * response.replaceAll("\\s+", ""); return response;
		 */
	}

	private String getSwVersion() throws Exception
	{
		String response;

		response = atHandler.getSwVersion();
		if (response.matches("\\s*[\\p{ASCII}]*\\s+ERROR(?:: \\d+)?\\s+")) return VALUE_NOT_REPORTED;
		response = response.replaceAll("\\s+OK\\s+", "");
		response = response.replaceAll("\\s+", "");
		return response;
	}

	private boolean getGprsStatus() throws Exception
	{
		return (atHandler.getGprsStatus().matches("\\s*CGATT:\\s1"));
	}

	private int getBatteryLevel() throws Exception
	{
		String response;
		StringTokenizer tokens;

		response = atHandler.getBatteryLevel();
		if (response.matches("\\s*[\\p{ASCII}]*\\s+ERROR(?:: \\d+)?\\s+")) return 0;
		response = response.replaceAll("\\s+OK\\s+", "");
		response = response.replaceAll("\\s+", "");
		tokens = new StringTokenizer(response, ":,");
		tokens.nextToken();
		tokens.nextToken();
		return Integer.parseInt(tokens.nextToken());
	}

	private int getSignalLevel() throws Exception
	{
		String response;
		StringTokenizer tokens;

		response = atHandler.getSignalLevel();
		if (response.matches("\\s*[\\p{ASCII}]*\\s+ERROR(?:: \\d+)?\\s+")) return 0;
		response = response.replaceAll("\\s+OK\\s+", "");
		response = response.replaceAll("\\s+", "");
		tokens = new StringTokenizer(response, ":,");
		tokens.nextToken();
		return (Integer.parseInt(tokens.nextToken().trim()) * 100 / 31);
	}

	private boolean isIncomingMessage(String pdu)
	{
		int index, i;

		i = Integer.parseInt(pdu.substring(0, 2), 16);
		index = (i + 1) * 2;

		i = Integer.parseInt(pdu.substring(index, index + 2), 16);
		if ((i & 0x03) == 0) return true;
		else return false;
	}

	private boolean isStatusReportMessage(String pdu)
	{
		int index, i;

		i = Integer.parseInt(pdu.substring(0, 2), 16);
		index = (i + 1) * 2;

		i = Integer.parseInt(pdu.substring(index, index + 2), 16);
		if ((i & 0x02) == 2) return true;
		else return false;
	}

	public boolean received(CIncomingMessage message) { return false; }

	private class CKeepAliveThread extends Thread
	{
		private boolean stopFlag;

		private boolean stopped;

		public CKeepAliveThread()
		{
			stopFlag = false;
			stopped = false;
		}

		public void killMe() { stopFlag = true; }
		public boolean killed() { return stopped; }

		public void run()
		{
			while (!stopFlag)
			{
				try { sleep(keepAliveInterval); } catch (Exception e) {}
				if (stopFlag) break;
				synchronized (_SYNC_)
				{
					if (getConnected()) try
					{
						log.info("** Keep-Live **");
						atHandler.isAlive();
					}
					catch (Exception e) {}
				}
			}
			stopped = true;
		}
	}

	private class CReceiveThread extends Thread
	{
		private boolean stopFlag;
		private boolean stopped;

		public CReceiveThread()
		{
			stopFlag = false;
			stopped = false;
		}

		public void killMe() { stopFlag = true; }
		public boolean killed() { return stopped; }

		public void run()
		{
			List<CIncomingMessage> messageList;

			messageList = new LinkedList<CIncomingMessage>();
			while (!stopFlag)
			{
				int state = newMsgMonitor.waitEvent(asyncPollInterval);
				if (stopFlag) break;
				if (getConnected() && (receiveMode == ReceiveMode.AsyncCnmi || receiveMode == ReceiveMode.AsyncPoll))
				{
					try
					{
						if (state == CNewMsgMonitor.DATA && !atHandler.dataAvailable() && newMsgMonitor.getState() != CNewMsgMonitor.CMTI) continue;

						newMsgMonitor.reset();
						messageList.clear();
						readMessages(messageList, asyncRecvClass);
						for (int i = 0; i < messageList.size(); i++)
						{
							CIncomingMessage message = messageList.get(i);
							if (getMessageHandler() == null)
							{
								if (received(message)) deleteMessage(message);
							}
							else
							{
								if (getMessageHandler() != null && getMessageHandler().received(CService.this, message)) deleteMessage(message);
							}

						}
					}
					catch (Exception e)
					{
						catchAsyncException(e);
					}
				}
			}
			stopped = true;
		}
	}

	public static void main(String[] args)
	{
		System.out.println(_name + " " + _version);
		System.out.println("	Java API for sending / receiving SMS messages via GSM modem.");
		System.out.println("	This software is distributed under the LGPL license.");
		System.out.println("");
		System.out.println("Copyright (C) 2002-2006, Thanasis Delenikas, Athens / GREECE.");
		System.out.println("Visit http://www.smslib.org for latest information.\n");
	}
}
