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

package org.smslib.handler;

import org.smslib.*;
import org.apache.log4j.*;

public class CATHandler extends AbstractATHandler
{
	public CATHandler(CSerialDriver serialDriver, Logger log, CService srv)
	{
		super(serialDriver, log, srv);
	}

	protected String getMemoryLocations()
	{
		return memoryLocations;
	}

	protected boolean dataAvailable() throws Exception
	{
		return serialDriver.dataAvailable();
	}

	protected void sync() throws Exception
	{
		Thread.sleep(500);
		serialDriver.send("AT\r");
		serialDriver.send("AT\r");
		serialDriver.send("AT\r");
		serialDriver.send("AT\r");
		Thread.sleep(500);
	}

	protected void reset() throws Exception
	{
	}

	protected void echoOff() throws Exception
	{
		serialDriver.send("ATE0\r");
		serialDriver.getResponse();
	}

	protected void init() throws Exception
	{
	}

	protected boolean isAlive() throws Exception
	{
		serialDriver.send("AT\r");
		return (serialDriver.getResponse().matches("\\s*[\\p{ASCII}]*\\s+OK\\s"));
	}

	protected boolean waitingForPin() throws Exception
	{
		serialDriver.send("AT+CPIN?\r");
		return (serialDriver.getResponse().indexOf("SIM PIN") >= 0);
	}

	protected boolean enterPin(String pin) throws Exception
	{
		serialDriver.send("AT+CPIN=\"{1}\"\r".replace("{1}", pin));
		Thread.sleep(10000);
		if (serialDriver.getResponse().indexOf("OK") > 0)
		{
			Thread.sleep(10000);
			return true;
		}
		else return false;
	}

	protected boolean setVerboseErrors() throws Exception
	{
		serialDriver.send("AT+CMEE=1\r");
		return (serialDriver.getResponse().matches("\\s+OK\\s+"));
	}

	protected boolean setPduMode() throws Exception
	{
		serialDriver.send("AT+CMGF=0\r");
		return (serialDriver.getResponse().matches("\\s+OK\\s+"));
	}

	protected boolean enableIndications() throws Exception
	{
		serialDriver.send("AT+CNMI=1,1,0,0,0\r");
		return (serialDriver.getResponse().matches("\\s+OK\\s+"));
	}

	protected boolean disableIndications() throws Exception
	{
		serialDriver.send("AT+CNMI=0,0,0,0,0\r");
		return (serialDriver.getResponse().matches("\\s+OK\\s+"));
	}

	protected String getManufacturer() throws Exception
	{
		serialDriver.send("AT+CGMI\r");
		return serialDriver.getResponse();
	}

	protected String getModel() throws Exception
	{
		serialDriver.send("AT+CGMM\r");
		return serialDriver.getResponse();
	}

	protected String getSerialNo() throws Exception
	{
		serialDriver.send("AT+CGSN\r");
		return serialDriver.getResponse();
	}

	protected String getImsi() throws Exception
	{
		serialDriver.send("AT+CIMI\r");
		return serialDriver.getResponse();
	}

	protected String getSwVersion() throws Exception
	{
		serialDriver.send("AT+CGMR\r");
		return serialDriver.getResponse();
	}

	protected String getBatteryLevel() throws Exception
	{
		serialDriver.send("AT+CBC\r");
		return serialDriver.getResponse();
	}

	protected String getSignalLevel() throws Exception
	{
		serialDriver.send("AT+CSQ\r");
		return serialDriver.getResponse();
	}

	protected boolean setMemoryLocation(String mem) throws Exception
	{
		serialDriver.send("AT+CPMS=\"" + mem + "\"\r");
		return (serialDriver.getResponse().matches("\\s*[\\p{ASCII}]*\\s+OK\\s"));
	}

	protected void switchToCmdMode() throws Exception
	{
		serialDriver.send("+++");
		Thread.sleep(1000);
	}

	protected boolean keepGsmLinkOpen() throws Exception
	{
		serialDriver.send("AT+CMMS=1\r");
		return (serialDriver.getResponse().matches("\\s+OK\\s+"));
	}

	protected int sendMessage(int size, String pdu) throws Exception
	{
		int responseRetries, errorRetries;
		String response;
		int refNo;

		errorRetries = 0;
		while (true)
		{
			responseRetries = 0;
			serialDriver.send("AT+CMGS=\"{1}\"\r".replace("\"{1}\"", "" + size));
			Thread.sleep(300);
			while (!serialDriver.dataAvailable())
			{
				responseRetries++;
				if (responseRetries == srv.getRetriesNoResponse()) throw new NoResponseException();
				log.warn("ATHandler().SendMessage(): Still waiting for response (I) (" + responseRetries + ")...");
				Thread.sleep(srv.getDelayNoResponse());
			}
			responseRetries = 0;
			serialDriver.clearBuffer();
			serialDriver.send(pdu);
			serialDriver.send((char) 26);
			response = serialDriver.getResponse();
			while (response.length() == 0)
			{
				responseRetries++;
				if (responseRetries == srv.getRetriesNoResponse()) throw new NoResponseException();
				log.warn("ATHandler().SendMessage(): Still waiting for response (II) (" + responseRetries + ")...");
				Thread.sleep(srv.getDelayNoResponse());
				response = serialDriver.getResponse();
			}
			if (response.indexOf("OK\r") >= 0)
			{
				int i;
				String tmp = "";

				i = response.indexOf(":");
				while (!Character.isDigit(response.charAt(i)))
					i++;
				while (Character.isDigit(response.charAt(i)))
				{
					tmp += response.charAt(i);
					i++;
				}
				refNo = Integer.parseInt(tmp);
				break;
			}
			else if (response.indexOf("ERROR") >= 0)
			{
				String err = response.replaceAll("\\s+", "");
				errorRetries++;
				if (errorRetries == srv.getRetriesCmsErrors())
				{
					log.error("CMS Errors [ " + err + " ]: Quit retrying, message lost...");
					refNo = -1;
					break;
				}
				else
				{
					log.warn("CMS Errors [ " + err + " ]: Retrying...");
					Thread.sleep(srv.getDelayCmsErrors());
				}
			}
			else refNo = -1;
		}
		return refNo;
	}

	protected String listMessages(CIncomingMessage.MessageClass messageClass) throws Exception
	{
		switch (messageClass)
		{
			case All: serialDriver.send("AT+CMGL=4\r"); break;
			case RecvUnread: serialDriver.send("AT+CMGL=0\r"); break;
			case RecvRead: serialDriver.send("AT+CMGL=1\r"); break;
		}
		return serialDriver.getResponse();
	}

	protected boolean deleteMessage(int memIndex, String memLocation) throws Exception
	{
		if (!setMemoryLocation(memLocation)) throw new org.smslib.OopsException("CATHandler.deleteMessage() : Memory Location not found!!!");
		serialDriver.send("AT+CMGD={1}\r".replace("{1}", "" + memIndex));
		return (serialDriver.getResponse().matches("\\s+OK\\s+"));
	}

	protected String getGprsStatus() throws Exception
	{
		serialDriver.send("AT+CGATT?\r");
		return serialDriver.getResponse();
	}

	protected String send(String s) throws Exception
	{
		serialDriver.send(s);
		return serialDriver.getResponse();
	}

	protected String getNetworkRegistration() throws Exception
	{
		serialDriver.send("AT+CREG?\r");
		return serialDriver.getResponse();
	}
}
