package com.app.lab7.service;

import com.app.lab7.dto.meeting.CreateMeetingDto;
import com.app.lab7.model.Meeting;

import java.util.List;

public interface MeetingService {
    List<Meeting> getAll();

    Meeting getById(Long id);

    Meeting save(CreateMeetingDto meeting);

    Meeting update(Long id, CreateMeetingDto meeting);

    void deleteById(Long id);
}
