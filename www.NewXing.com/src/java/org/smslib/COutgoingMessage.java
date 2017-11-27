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

public class COutgoingMessage extends CMessage implements java.io.Serializable
{
	private static final long serialVersionUID = 8448376950650775771L;

	private Date dispatchDate;
	private int validityPeriod;
	private boolean statusReport;
	private boolean flashSms;
	private int srcPort;
	private int dstPort;

	public COutgoingMessage()
	{
		super(MessageType.Outgoing, null, null, null, null);

		validityPeriod = -1;
		statusReport = false;
		flashSms = false;
		srcPort = -1;
		dstPort = -1;
		dispatchDate = null;
		setDate(new Date());
	}

	public COutgoingMessage(String recipient, String text)
	{
		super(MessageType.Outgoing, new Date(), null, recipient, text);

		validityPeriod = -1;
		statusReport = false;
		flashSms = false;
		srcPort = -1;
		dstPort = -1;
		dispatchDate = null;
		setDate(new Date());
	}

	protected boolean isBig() throws Exception
	{
		int messageLength;

		switch (getType())
		{
			case WapPushSI:
				messageLength = getPDUData().length() / 2;
				break;
			case Outgoing:
				messageLength = getText().length();
				break;
			default: throw new OopsException();
		}
		return (messageLength > maxSize() ? true : false);
	}

	protected int getNoOfParts() throws Exception
	{
		int noOfParts = 0;
		int partSize;
		int messageLength;

		partSize = maxSize() - 8;
		switch (getType())
		{
			case WapPushSI:
				messageLength = getPDUData().length() / 2;
				break;
			case Outgoing:
				messageLength = getText().length();
				break;
			default: throw new OopsException();
		}
		noOfParts = messageLength / partSize;
		if ((noOfParts * partSize) < (messageLength)) noOfParts++;
		return noOfParts;
	}

	private int maxSize() throws Exception
	{
		int size;

		switch (getMessageEncoding())
		{
			case Enc7Bit: size = 160; break;
			case Enc8Bit: size = 140; break;
			case EncUcs2: size = 70; break;
			default: throw new OopsException();
		}
		if ((getSourcePort() != -1) && (getDestinationPort() != -1)) size -= 8;
		return size;
	}

	private String getPart(String txt, int partNo) throws Exception
	{
		String textPart;
		int partSize;

		textPart = txt;
		if (partNo != 0)
		{
			partSize = maxSize() - 8;
			if (((partSize * (partNo - 1)) + partSize) > txt.length()) textPart = txt.substring(partSize * (partNo - 1));
			else textPart = txt.substring(partSize * (partNo - 1), (partSize * (partNo - 1)) + partSize);
		}
		return textPart;
	}

	private String getPDUPart(String txt, int partNo) throws Exception
	{
		String textPart;
		int partSize;

		textPart = txt;
		if (partNo != 0)
		{
			partSize = maxSize() - 8;
			partSize *= 2;
			if (((partSize * (partNo - 1)) + partSize) > txt.length()) textPart = txt.substring(partSize * (partNo - 1));
			else textPart = txt.substring(partSize * (partNo - 1), (partSize * (partNo - 1)) + partSize);
		}
		return textPart;
	}

	public String getPDU(String smscNumber, int mpRefNo, int partNo) throws Exception
	{
		String pdu, udh;
		String str1, str2;
		int i, high, low;
		char c;

		pdu = "";
		udh = "";
		if ((smscNumber != null) && (smscNumber.length() != 0))
		{
			str1 = "91" + toBCDFormat(smscNumber.substring(1));
			str2 = Integer.toHexString(str1.length() / 2);
			if (str2.length() != 2) str2 = "0" + str2;
			pdu = pdu + str2 + str1;
		}
		else if ((smscNumber != null) && (smscNumber.length() == 0)) pdu = pdu + "00";
		if (((srcPort != -1) && (dstPort != -1)) || (isBig()))
		{
			if (statusReport) pdu = pdu + "71";
			else pdu = pdu + "51";
		}
		else
		{
			if (statusReport) pdu = pdu + "31";
			else pdu = pdu + "11";
		}
		pdu = pdu + "00";
		str1 = getRecipient();
		if (str1.charAt(0) == '+')
		{
			str1 = toBCDFormat(str1.substring(1));
			str2 = Integer.toHexString(getRecipient().length() - 1);
			str1 = "91" + str1;
		}
		else
		{
			str1 = toBCDFormat(str1);
			str2 = Integer.toHexString(getRecipient().length());
			str1 = "81" + str1;
		}
		if (str2.length() != 2) str2 = "0" + str2;

		pdu = pdu + str2 + str1;
		pdu = pdu + "00";
		switch (getMessageEncoding())
		{
			case Enc7Bit:
				if (flashSms) pdu = pdu + "10";
				else pdu = pdu + "00";
				break;
			case Enc8Bit:
				if (flashSms) pdu = pdu + "14";
				else pdu = pdu + "04";
				break;
			case EncUcs2:
				if (flashSms) pdu = pdu + "18";
				else pdu = pdu + "08";
				break;
			default: throw new OopsException();
		}

		pdu = pdu + getValidityPeriodBits();

		if ((srcPort != -1) && (dstPort != -1))
		{
			String s;

			udh += "060504";
			s = Integer.toHexString(dstPort);
			while (s.length() < 4)
				s = "0" + s;
			udh += s;
			s = Integer.toHexString(srcPort);
			while (s.length() < 4)
				s = "0" + s;
			udh += s;
		}

		if (isBig())
		{
			String s;

			if ((srcPort != -1) && (dstPort != -1)) udh = "0C" + udh.substring(2) + "0804";
			else udh += "060804";
			s = Integer.toHexString(mpRefNo);
			while (s.length() < 4)
				s = "0" + s;
			udh += s;
			s = Integer.toHexString(getNoOfParts());
			while (s.length() < 2)
				s = "0" + s;
			udh += s;
			s = Integer.toHexString(partNo);
			while (s.length() < 2)
				s = "0" + s;
			udh += s;
		}

		switch (getMessageEncoding())
		{
			case Enc7Bit:
				str2 = textToPDU(getPart(getText(), partNo));
				i = CGSMAlphabet.stringToBytes(getPart(getText(), partNo), new byte[400]);
				if ((srcPort != -1) && (dstPort != -1)) str1 = Integer.toHexString(i + 8);
				else if (isBig()) str1 = Integer.toHexString(i + 8);
				else str1 = Integer.toHexString(i);
				break;
			case Enc8Bit:
				switch (getType())
				{
					case Outgoing:
						str1 = getPart(getText(), partNo);
						str2 = "";
						for (i = 0; i < str1.length(); i++)
						{
							c = str1.charAt(i);
							str2 = str2 + ((Integer.toHexString(c).length() < 2) ? "0" + Integer.toHexString(c) : Integer.toHexString(c));
						}
						if ((srcPort != -1) && (dstPort != -1)) str1 = Integer.toHexString(str1.length() + 7);
						else if (isBig()) str1 = Integer.toHexString(str1.length() + 7);
						else str1 = Integer.toHexString(str1.length());
						break;
					case WapPushSI:
						str2 = getPDUPart(getPDUData(), partNo);
						if ((isBig()) && ((srcPort != -1) && (dstPort != -1))) str1 = Integer.toHexString((str2.length() / 2) + 13);
						else if ((isBig()) || ((srcPort != -1) && (dstPort != -1))) str1 = Integer.toHexString((str2.length() / 2) + 7);
						else str1 = Integer.toHexString((str2.length() / 2) + 7);
						break;
					default:
						throw new OopsException();
				}
				break;
			case EncUcs2:
				str1 = getPart(getText(), partNo);
				str2 = "";
				for (i = 0; i < str1.length(); i++)
				{
					c = str1.charAt(i);
					high = c / 256;
					low = c % 256;
					str2 = str2 + ((Integer.toHexString(high).length() < 2) ? "0" + Integer.toHexString(high) : Integer.toHexString(high));
					str2 = str2 + ((Integer.toHexString(low).length() < 2) ? "0" + Integer.toHexString(low) : Integer.toHexString(low));
				}
				if ((srcPort != -1) && (dstPort != -1)) str1 = Integer.toHexString((str1.length() * 2) + 7);
				else if (isBig()) str1 = Integer.toHexString((str1.length() * 2) + 7);
				else str1 = Integer.toHexString(str1.length() * 2);
				break;
			default:
				throw new OopsException();
		}
		if (str1.length() != 2) str1 = "0" + str1;
		if (((srcPort != -1) && (dstPort != -1)) || (isBig())) pdu = pdu + str1 + udh + str2;
		else pdu = pdu + str1 + str2;
		return pdu.toUpperCase();
	}

	protected String getPDUData() throws Exception
	{
		throw new OopsException("The called method should be overriden!");
	}

	private String getValidityPeriodBits()
	{
		String bits;
		int value;

		if (validityPeriod == -1) bits = "FF";
		else
		{
			if (validityPeriod <= 12) value = (validityPeriod * 12) - 1;
			else if (validityPeriod <= 24) value = (((validityPeriod - 12) * 2) + 143);
			else if (validityPeriod <= 720) value = (validityPeriod / 24) + 166;
			else value = (validityPeriod / 168) + 192;
			bits = Integer.toHexString(value);
			if (bits.length() != 2) bits = "0" + bits;
			if (bits.length() > 2) bits = "FF";
		}
		return bits;
	}

	private String textToPDU(String txt) throws Exception
	{
		String pdu, str1;
		byte[] bytes, oldBytes, newBytes;
		BitSet bitSet;
		int i, j, value1, value2;

		bytes = new byte[400];
		i = CGSMAlphabet.stringToBytes(txt, bytes);
		oldBytes = new byte[i];
		for (j = 0; j < i; j++)
			oldBytes[j] = bytes[j];
		bitSet = new BitSet(oldBytes.length * 8);

		value1 = 0;
		for (i = 0; i < oldBytes.length; i++)
			for (j = 0; j < 7; j++)
			{
				value1 = (i * 7) + j;
				if ((oldBytes[i] & (1 << j)) != 0) bitSet.set(value1);
			}
		value1++;

		if (((value1 / 56) * 56) != value1) value2 = (value1 / 8) + 1;
		else value2 = (value1 / 8);
		if (value2 == 0) value2 = 1;

		newBytes = new byte[value2];
		for (i = 0; i < value2; i++)
			for (j = 0; j < 8; j++)
				if ((value1 + 1) > ((i * 8) + j)) if (bitSet.get(i * 8 + j)) newBytes[i] |= (byte) (1 << j);

		pdu = "";
		for (i = 0; i < value2; i++)
		{
			str1 = Integer.toHexString(newBytes[i]);
			if (str1.length() != 2) str1 = "0" + str1;
			str1 = str1.substring(str1.length() - 2, str1.length());
			pdu += str1;
		}
		return pdu;
	}

	private String toBCDFormat(String s)
	{
		String bcd;
		int i;

		if ((s.length() % 2) != 0) s = s + "F";
		bcd = "";
		for (i = 0; i < s.length(); i += 2)
			bcd = bcd + s.charAt(i + 1) + s.charAt(i);
		return bcd;
	}

	/**
	*	Returns the message recipient number. Number is in international format.
	*
	*	@return	The Recipient's number.
	*/
	public String getRecipient() { return recipient; }
	public int getValidityPeriod() { return validityPeriod; }
	public boolean getStatusReport() { return statusReport; }
	public boolean getFlashSms() { return flashSms; }
	public int getSourcePort() { return srcPort; }
	public int getDestinationPort() { return dstPort; }
	public Date getDispatchDate() { return dispatchDate; }

	/**
	*	Sets the Recipient's number. The number should be in international format.
	*
	*	@param	recipient	The Recipient's number.
	*/
	public void setRecipient(String recipient) { this.recipient = recipient; }
	public void setValidityPeriod(int hours) { this.validityPeriod = hours; }
	public void setStatusReport(boolean statusReport) { this.statusReport = statusReport; }
	public void setFlashSms(boolean flashSms) { this.flashSms = flashSms; }
	public void setSourcePort(int port) { this.srcPort = port; }
	public void setDestinationPort(int port) { this.dstPort = port; }
	public void setDispatchDate(Date date) { this.dispatchDate = date; }
}
