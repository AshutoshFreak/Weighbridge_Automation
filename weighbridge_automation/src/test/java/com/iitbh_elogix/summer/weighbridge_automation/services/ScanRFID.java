package com.iitbh_elogix.summer.weighbridge_automation.services;

import java.util.List;
import java.math.BigInteger;
import javax.smartcardio.*;

import com.iitbh_elogix.summer.weighbridge_automation.dto.RFID;

public class ScanRFID {

	static String bin2hex(byte[] data) {
		return String.format("%0" + (data.length * 2) + "X", new BigInteger(1, data));
	}

	static RFID scannedRFID = null;

	public static void main(String[] args) throws CardException, InterruptedException {
//		try 
		{

			// Display the list of terminals
			TerminalFactory factory = TerminalFactory.getDefault();
			List<CardTerminal> terminals = factory.terminals().list();
			System.out.println("Terminals: " + terminals);
			CardTerminal terminal;
			Card card = null;
			CardChannel channel;
			while (true) {
				try {
					// Use the first terminal
					Thread.sleep(2000);
					terminal = terminals.get(0);

					// Connect with the card
					card = terminal.connect("*");
					break;
				} catch (Exception e) {
					continue;
				}
			}
			System.out.println("Card: " + card);
			channel = card.getBasicChannel();

			// Send test command
			ResponseAPDU response = channel.transmit(
					new CommandAPDU(new byte[] { (byte) 0xFF, (byte) 0xCA, (byte) 0x00, (byte) 0x00, (byte) 0x00 }));
			System.out.println("Response: " + response.toString());

			if (response.getSW1() == 0x63 && response.getSW2() == 0x00)
				System.out.println("Failed");

			String rfidUUID = bin2hex(response.getData());

			VerifyRFID obj = new VerifyRFID();
			scannedRFID = obj.getRFID(rfidUUID);

			System.out.println((obj.isValid(scannedRFID)) ? "Valid" : "Not Valid");

			// Disconnect the card
			card.disconnect(false);

		}
//		catch (Exception e) {
//
//			System.out.println("Error: " + e.toString());
//
//		}
	}
}