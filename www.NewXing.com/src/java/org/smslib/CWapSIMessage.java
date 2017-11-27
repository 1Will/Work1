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

import java.util.*;
import java.net.*;

public class CWapSIMessage extends COutgoingMessage implements java.io.Serializable
{
	private static final long serialVersionUID = 8448376950690775771L;

	public static enum Signals { None, Low, Medium, High, Delete }

	//private static final String PDU_PATTERN = "DC0601 AE 02056A0045C6{URL}0AC307{DATECRT}10C307{DATEEXP}0103{TEXT}0101";
	private static final String PDU_PATTERN = "25060803AE81EAAF82B48401056A0045C6{URL1}03{URL2}00080103{TEXT}000101";
	private String siPdu = "";

	private URL hRef;
	private Date createDate, expireDate;
	Signals signal;
	String text;

	private static final String[][] protocolBytes = 
	{
		{ "http://www.", "0D" },
		{ "https://www.", "0F" },
		{ "http://", "0C" },
		{ "https://", "0E" }
	};

	private static final String[][] domainBytes = 
	{
		{ ".com/", "0C" },
		{ ".edu/", "0D" },
		{ ".net/", "0E" },
		{ ".org/", "0F" }
	};

	public CWapSIMessage(String recipient, URL hRef, Date createDate, Date expireDate, Signals signal, String text)
	{
		super();

		this.hRef = hRef;
		this.createDate = createDate;
		this.expireDate = expireDate;
		this.signal = signal;
		this.text = text;
		setMessageEncoding(CMessage.MessageEncoding.Enc8Bit);
		setSourcePort(9200);
		setDestinationPort(2948);
		this.type = CMessage.MessageType.WapPushSI;
		this.recipient = recipient;

		fixPdu();
	}

	public CWapSIMessage(String recipient, URL hRef, String text)
	{
		this(recipient, hRef, null, null, null, text);
	}

	protected String getPDUData()
	{
		return siPdu;
	}

	private void fixPdu()
	{
		String s,url;
		int i;
		char c;

		siPdu = PDU_PATTERN;
		if (createDate != null) siPdu = siPdu.replace("{DATECRT}", formatDate(createDate));
		if (expireDate != null) siPdu = siPdu.replace("{DATEEXP}", formatDate(expireDate));

		s = "";
		for (i = 0; i < text.length(); i++)
		{
			c = text.charAt(i);
			s = s + ((Integer.toHexString(c).length() < 2) ? "0" + Integer.toHexString(c) : Integer.toHexString(c));
		}
		siPdu = siPdu.replace("{TEXT}", s);

		url = hRef.toString();

		for (i = 0; i < 4; i ++)
		{
			if (url.indexOf(protocolBytes[i][0]) == 0)
			{
				siPdu = siPdu.replace("{URL1}", protocolBytes[i][1]);
				url = url.replace(protocolBytes[i][0], "");
				break;
			}
		}

		s = "";
		for (i = 0; i < url.length(); i++)
		{
			c = url.charAt(i);
			s = s + ((Integer.toHexString(c).length() < 2) ? "0" + Integer.toHexString(c) : Integer.toHexString(c));
		}
		siPdu = siPdu.replace("{URL2}", s);
	}

	private String formatDate(Date d)
	{
		String strDate = "", tmp = "";
		Calendar cal = Calendar.getInstance();

		cal.setTime(d);

		strDate = String.valueOf(cal.get(Calendar.YEAR));
		tmp = String.valueOf(cal.get(Calendar.MONTH) + 1); if (tmp.length() != 2) tmp = "0" + tmp; strDate += tmp;
		tmp = String.valueOf(cal.get(Calendar.DATE)); if (tmp.length() != 2) tmp = "0" + tmp; strDate += tmp;
		tmp = String.valueOf(cal.get(Calendar.HOUR_OF_DAY)); if (tmp.length() != 2) tmp = "0" + tmp; strDate += tmp;
		tmp = String.valueOf(cal.get(Calendar.MINUTE)); if (tmp.length() != 2) tmp = "0" + tmp; strDate += tmp;
		tmp = String.valueOf(cal.get(Calendar.SECOND)); if (tmp.length() != 2) tmp = "0" + tmp; strDate += tmp;
		return strDate;
	}
}
