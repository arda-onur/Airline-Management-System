package com.lydiatech.casestudy.service.scheduledImpl;

import com.lydiatech.casestudy.enums.BookingStatus;
import com.lydiatech.casestudy.repository.BookingRepository;
import com.lydiatech.casestudy.service.ScheduledWaitlistService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.time.ZonedDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ScheduledWaitlistServiceImpl implements ScheduledWaitlistService {
    private final BookingRepository bookingRepository;


    @Override
    @Transactional
    @Scheduled(cron = "0 0 0  * * *")
    public void removeExpiredWaitlisted() {
        ZonedDateTime now = ZonedDateTime.now()
                .withHour(0).withMinute(0).withSecond(0).withNano(0);

        ZonedDateTime startOfYesterday = now.minusDays(1)
                .withHour(0).withMinute(0).withSecond(1).withNano(0);

        ZonedDateTime endOfYesterday = now.minusDays(1)
                .withHour(23).withMinute(59).withSecond(59).withNano(0);

        this.bookingRepository.deleteAllByBookingStatusAndCreationDateBetween(
                BookingStatus.WAITLISTED,
                Date.from(startOfYesterday.toInstant()), Date.from(endOfYesterday.toInstant())
        );

    }
}
