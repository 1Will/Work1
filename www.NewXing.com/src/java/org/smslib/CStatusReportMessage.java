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
import java.text.*;

public class CStatusReportMessage extends CIncomingMessage implements java.io.Serializable
{
	private static final long serialVersionUID = -5114334786015571983L;

	private Date dateOriginal;
	private Date dateReceived;

	protected CStatusReportMessage(String pdu, int memIndex, String memLocation)
	{
		super(MessageType.StatusReport, memIndex, memLocation);

		dateOriginal = null;
		dateReceived = null;

		int index, i, j, k;

		i = Integer.parseInt(pdu.substring(0, 2), 16);
		index = (i + 1) * 2;
		index += 2;
		refNo = Integer.parseInt(pdu.substring(index, index + 2), 16);
		index += 2;

		i = Integer.parseInt(pdu.substring(index, index + 2), 16);
		j = index + 4;
		recipient = "";
		for (k = 0; k < i; k += 2)
			recipient = recipient + pdu.charAt(j + k + 1) + pdu.charAt(j + k);
		recipient = "+" + recipient;
		if (recipient.charAt(recipient.length() - 1) == 'F')
		{
			recipient = recipient.substring(0, recipient.length() - 1);
			i++;
		}

		index = j + i;

		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		String dateOctect = pdu.substring(index, index + 12);
		StringBuffer dateOk = new StringBuffer();
		for (int x = 0; x < 12; x = x + 2) 	dateOk.append(new char[] { dateOctect.charAt(x + 1), dateOctect.charAt(x) });

		try { this.dateOriginal = sdf.parse(dateOk.toString()); } catch (ParseException e) { this.dateOriginal = null; }
		index += 14;

		dateOctect = pdu.substring(index, index + 12);
		dateOk = new StringBuffer();
		for (int x = 0; x < 12; x = x + 2) 	dateOk.append(new char[] { dateOctect.charAt(x + 1), dateOctect.charAt(x) });

		try { this.dateReceived = sdf.parse(dateOk.toString()); } catch (ParseException e) { this.dateReceived = null; }
		index += 14;

		i = Integer.parseInt(pdu.substring(index, index + 2), 16);
		if ((i & 0x60) == 0) text = "00 - Succesful Delivery.";
		if ((i & 0x20) == 0x20) text = "01 - Errors, will retry dispatch.";
		if ((i & 0x40) == 0x40) text = "02 - Errors, stopped retrying dispatch.";
		if ((i & 0x60) == 0x60) text = "03 - Errors, stopped retrying dispatch.";

		date = null;
	}

	public String getOriginator() { return "TO:" + recipient; }

	public Date getDateOriginal() { return dateOriginal; }
	public Date getDateReceived() { return dateReceived; }
}
