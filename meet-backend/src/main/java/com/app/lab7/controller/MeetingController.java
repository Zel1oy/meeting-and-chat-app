package com.app.lab7.controller;

import com.app.lab7.dto.meeting.CreateMeetingDto;
import com.app.lab7.model.Meeting;
import com.app.lab7.service.MeetingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/meetings")
public class MeetingController {
    private final MeetingService meetingService;

    @GetMapping
    public List<Meeting> getMeetings() {
        return meetingService.getAll();
    }

    @GetMapping("/{id}")
    public Meeting getMeetingById(@PathVariable Long id) {
        return meetingService.getById(id);
    }

    @PostMapping
    public Meeting create(@RequestBody @Valid CreateMeetingDto meeting) {
        return meetingService.save(meeting);
    }

    @PutMapping("/{id}")
    public Meeting update(@PathVariable Long id, @RequestBody @Valid CreateMeetingDto meeting) {
        return meetingService.update(id, meeting);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        meetingService.deleteById(id);
    }
}
