package com.app.lab7.mapper;

import com.app.lab7.config.MapperConfig;
import com.app.lab7.dto.meeting.CreateMeetingDto;
import com.app.lab7.model.Meeting;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface MeetingMapper {
    Meeting toModel(CreateMeetingDto createMeetingDto);
}
