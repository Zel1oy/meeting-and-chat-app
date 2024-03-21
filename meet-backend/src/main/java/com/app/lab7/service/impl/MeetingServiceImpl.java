package com.app.lab7.service.impl;

import com.app.lab7.dto.meeting.CreateMeetingDto;
import com.app.lab7.mapper.MeetingMapper;
import com.app.lab7.model.Meeting;
import com.app.lab7.repository.MeetingRepository;
import com.app.lab7.service.MeetingService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MeetingServiceImpl implements MeetingService {
    private final MeetingRepository meetingRepository;
    private final MeetingMapper meetingMapper;

    public List<Meeting> getAll() {
        return meetingRepository.findAll();
    }

    public Meeting getById(Long id) {
        return meetingRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find Meeting with id: " + id));
    }

    public Meeting save(CreateMeetingDto requestMeetingDto) {
        return meetingRepository.save(meetingMapper.toModel(requestMeetingDto));
    }

    public Meeting update(Long id, CreateMeetingDto meeting) {
        Meeting existingMeeting = meetingRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find Meeting with id: " + id));
        existingMeeting.setName(meeting.getName());
        existingMeeting.setStartTime(meeting.getStartTime());
        existingMeeting.setEndTime(meeting.getEndTime());
        return meetingRepository.save(existingMeeting);
    }

    public void deleteById(Long id) {
        meetingRepository.deleteById(id);
    }
}
