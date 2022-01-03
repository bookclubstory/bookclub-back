package com.emmett.bookclub.global.mail;

public interface MailService {

    void sendSimpleMessage(String from,
                           String to,
                           String subject,
                           String text);

    void sendSimpleMessage(MailDto mailDto);
}
