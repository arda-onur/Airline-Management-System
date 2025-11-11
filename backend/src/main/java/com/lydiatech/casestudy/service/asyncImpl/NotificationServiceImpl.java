package com.lydiatech.casestudy.service.asyncImpl;

import com.lydiatech.casestudy.service.NotificationService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Override
    @Async
    public void sendBookingConfirmationEmail(String email, String flightNumber) {
        System.out.println("Sending confirmation email" + email + "with flight number" + flightNumber);
    }
}
