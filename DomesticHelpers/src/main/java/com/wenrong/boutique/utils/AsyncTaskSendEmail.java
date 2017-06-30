package com.wenrong.boutique.utils;

import javax.mail.MessagingException;

// Send email in background
public class AsyncTaskSendEmail extends Thread {
	String receipient;
	String title;
	String message;

	public AsyncTaskSendEmail(String receipient, String title, String message) {
		this.receipient = receipient;
		this.title = title;
		this.message = message;
	}

	@Override
	public void run() {
		System.out.println("Email sending to " + receipient + " (" + title + ")");
		try {
			Mail.Send(receipient, "", title, message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
