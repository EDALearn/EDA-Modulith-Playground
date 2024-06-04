package io.zenwave360.example.delivery.adapters.web.mappers;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.TimeZone;
import org.mapstruct.Mapper;

@Mapper
public interface BaseMapper {

    default Instant asInstant(OffsetDateTime date) {
        return date != null ? date.toInstant() : null;
    }

    default OffsetDateTime asOffsetDateTime(Instant date) {
        return date != null ? OffsetDateTime.ofInstant(date, TimeZone.getTimeZone("UTC").toZoneId()) : null;
    }

}
