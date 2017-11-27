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

/**
*	This is the parent class defining a generic SMS message.
*	In almost all cases, you will not work with this class, except for calling some methods for
*	accessing info fields common for all types of messages.
*
*	@see	CIncomingMessage
*	@see	COutgoingMessage
*	@see	CStatusReportMessage
*	@see	CWapSIMessage
*/
public class CMessage implements java.io.Serializable
{
	private static final long serialVersionUID = 7045837392224825217L;

	/**
	*	Enumeration holding the possible message encoding settings.
	*/
	public static enum MessageEncoding { Enc7Bit, Enc8Bit, EncUcs2 }

	/**
	*	Enumeration holding the possible message types.
	*/
	public static enum MessageType { Incoming, Outgoing, StatusReport, WapPushSI }

	protected MessageType type;
	protected int refNo;
	protected String id;
	protected Date date;
	protected String originator;
	protected String recipient;
	protected String text;
	protected MessageEncoding messageEncoding;

	public CMessage(MessageType type, Date date, String originator, String recipient, String text)
	{
		this.type = type;
		this.id = null;
		this.refNo = -1;
		this.date = date;
		this.originator = originator;
		this.recipient = recipient;
		this.text = text;
		this.messageEncoding = MessageEncoding.Enc7Bit;
	}

	/**
	*	Returns the message type.
	*
	*	@return	The message type.
	*	@see	MessageType
	*/
	public MessageType getType() { return type; }

	/**
	*	Returns the message SMSC Reference Number.
	*
	*	@return	The SMSC Ref Number.
	*/
	public int getRefNo() { return refNo; }

	/**
	*	Returns the message id.
	*
	*	@return	The message id.
	*	@see	#setId(String)
	*/
	public String getId() { return id; }

	/**
	*	Returns the message date.
	*
	*	@return The message date.
	*/
	public Date getDate() { return date; }

	/**
	*	Returns the message text.
	*
	*	@return	The message text.
	*/
	public String getText() { return text; }

	/**
	*	Returns the message encoding.
	*
	*	@return	The message encoding.
	*	@see	MessageEncoding
	*/
	public MessageEncoding getMessageEncoding() { return messageEncoding; }

	/**
	*	Sets the id of the message.<p>
	*	The message id is a user-defined id field. It is not used in actual sending or receiving of a
	*	message. So, even if you don't define it, SMSLib will work normally. Use it if you need to
	*	differentiate messages in some way.
	*
	*	@param	id	The message id.
	*	@see	#getId()
	*/
	public void setId(String id) { this.id = id; }

	/**
	*	Sets the message text.
	*
	*	@param	text	The message text.
	*/
	public void setText(String text) { this.text = text; }

	/**
	*	Sets the message (create) date.
	*
	*	@param	date	The message date.
	*/
	public void setDate(Date date) { this.date = date; }

	/**
	*	Sets the message encoding.
	*
	*	@param	messageEncoding	The message encoding.
	*	@see	MessageEncoding
	*/
	public void setMessageEncoding(MessageEncoding messageEncoding) { this.messageEncoding = messageEncoding; }

	protected void setRefNo(int refNo) { this.refNo = refNo; }
	protected void addText(String text) { this.text += text; }

	/**
	*	A message-to-string mapping function. Used for debugging and for easy viewing of
	*	of message object's info fields.
	*/
	public String toString()
	{
		String str = "";

		str += "===============================================================================";
		str += "\n";
		str += "<< MESSAGE DUMP >>";
		str += "\n";
		str += "-------------------------------------------------------------------------------";
		str += "\n";
		str += " Type: " + (type == MessageType.Incoming ? "Incoming" : (type == MessageType.Outgoing ? "Outgoing" : "Status Report"));
		str += "\n";
		str += " Encoding: " + (messageEncoding == MessageEncoding.Enc7Bit ? "7-bit" : (messageEncoding == MessageEncoding.Enc8Bit ? "8-bit" : "UCS2 (Unicode)"));
		str += "\n";
		str += " Date: " + date;
		str += "\n";
		str += " Originator: " + originator;
		str += "\n";
		str += " Recipient: " + recipient;
		str += "\n";
		str += " Text: " + text;
		str += "\n";
		str += " SMSC Ref No: " + refNo;
		str += "\n";
		if (type == MessageType.Incoming)
		{
			CIncomingMessage msg = (CIncomingMessage) this;
			str += " Memory Index: " + msg.getMemIndex();
			str += "\n";
			str += " Multi-part Memory Index: " + msg.getMpMemIndex();
			str += "\n";
			str += " Memory Location: " + msg.getMemLocation();
			str += "\n";
		}
		if (type == MessageType.Outgoing)
		{
			COutgoingMessage msg = (COutgoingMessage) this;
			str += " Dispatch Date: " + msg.getDispatchDate();
			str += "\n";
			str += " Validity Period (Hours): " + msg.getValidityPeriod();
			str += "\n";
			str += " Status Report: " + msg.getStatusReport();
			str += "\n";
			str += " Source / Destination Ports: " + msg.getSourcePort() + " / " + msg.getDestinationPort();
			str += "\n";
			str += " Flash SMS: " + msg.getFlashSms();
			str += "\n";
		}
		if (type == MessageType.StatusReport)
		{
			CStatusReportMessage msg = (CStatusReportMessage) this;
			str += " Date Sent: " + msg.getDateOriginal();
			str += "\n";
			str += " Date Received (by recipient): " + msg.getDateReceived();
			str += "\n";
		}

		str += "===============================================================================";
		str += "\n";
		return str;
	}
}