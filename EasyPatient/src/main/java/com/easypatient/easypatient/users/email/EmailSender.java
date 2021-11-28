package com.easypatient.easypatient.users.email;

public interface EmailSender {
    void send(String to, String email);
}
