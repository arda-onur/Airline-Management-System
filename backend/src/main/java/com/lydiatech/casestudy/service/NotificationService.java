package com.lydiatech.casestudy.service;

public interface NotificationService {

    void sendBookingConfirmationEmail(String email, String flightNumber);
}
