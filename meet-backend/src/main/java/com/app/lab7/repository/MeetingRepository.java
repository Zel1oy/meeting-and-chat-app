package com.app.lab7.repository;

import com.app.lab7.model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    @Query("FROM Meeting m WHERE m.endTime < :currentDate")
    List<Meeting> findAllByEndTimeBeforeNow(Date currentDate);
}
