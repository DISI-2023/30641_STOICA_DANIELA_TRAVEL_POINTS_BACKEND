package com.disi.TravelPoints.repository;

import com.disi.TravelPoints.dto.HourFrequencyVisitDTO;
import com.disi.TravelPoints.dto.MonthFrequencyVisitDTO;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@Repository
@AllArgsConstructor
public class VisitJdbcRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<MonthFrequencyVisitDTO> getMonthsFrequencyPerYear(String year, Long landmarkId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT EXTRACT(MONTH FROM date) as month, COUNT(*) as visit_count " +
                "FROM visits WHERE EXTRACT(YEAR FROM date) = " + year +
                " AND landmark_id = " + landmarkId +
                " GROUP BY EXTRACT(MONTH FROM date) ORDER BY EXTRACT(MONTH FROM date)";

        return jdbcTemplate.query(query, parameters, (rs, rowNum) -> mapResultToMonthFrequencyDTO(rs));
    }

    private MonthFrequencyVisitDTO mapResultToMonthFrequencyDTO(ResultSet rs) throws SQLException {
        return MonthFrequencyVisitDTO
                .builder()
                .month(getMonthFromIndex(rs.getInt("month")))
                .frequency(rs.getInt("visit_count"))
                .build();
    }

    private String getMonthFromIndex(int index) {
        return Month.of(index).getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }

    public List<HourFrequencyVisitDTO> getHourFrequencyPerDay(String year, String month, String day, long landmarkId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT EXTRACT(HOUR FROM date) as hour, COUNT(*) as visit_count " +
                "FROM visits WHERE landmark_id = " + landmarkId +
                " AND EXTRACT(YEAR FROM date) = " + year +
                " AND EXTRACT(MONTH FROM date) = " + month +
                " AND EXTRACT(DAY FROM date) = " + day +
                " GROUP BY EXTRACT(HOUR FROM date) ORDER BY EXTRACT(HOUR FROM date)";

        return jdbcTemplate.query(query, parameters, (rs, rowNum) -> mapResultToHourFrequencyDTO(rs));
    }

    private HourFrequencyVisitDTO mapResultToHourFrequencyDTO(ResultSet rs) throws SQLException {
        return HourFrequencyVisitDTO
                .builder()
                .hour(rs.getInt("hour"))
                .frequency(rs.getInt("visit_count"))
                .build();
    }
}
