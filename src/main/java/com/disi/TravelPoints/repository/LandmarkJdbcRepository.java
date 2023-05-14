package com.disi.TravelPoints.repository;

import com.disi.TravelPoints.dto.LandmarkDetails;
import com.disi.TravelPoints.dto.MostVisitedLandmarkDTO;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@AllArgsConstructor
public class LandmarkJdbcRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<LandmarkDetails> getLandmarksByCategoryAndLocation(String category, String location) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        boolean isFirstCondition = false;
        StringBuilder query = new StringBuilder("SELECT * FROM landmarks ");
        if (category != null) {
            query.append("WHERE category = " + "'").append(category).append("' ");
            isFirstCondition = true;
        }
        if (location != null) {
            if (!isFirstCondition) {
                query.append("WHERE ");
            }
            else {
                query.append("AND ");
            }
            query.append("location = " + "'").append(location).append("'");
        }

        return jdbcTemplate.query(query.toString(), parameters, (rs, rowNum) -> mapLandmarkToLandmarkDetails(rs));
    }

    public List<MostVisitedLandmarkDTO> getFirstFiveMostVisitedLandmarks() {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT l.*, COUNT(v.landmark_id) as visit_count " +
                "FROM landmarks l " +
                "INNER JOIN visits v ON l.id = v.landmark_id " +
                "GROUP BY l.id " +
                "ORDER BY COUNT(v.landmark_id) DESC " +
                "LIMIT 5";
        return jdbcTemplate.query(query, parameters, (rs, rowNum) -> mapLandmarkToMostVisitedLandmarkDTO(rs));
    }

    private LandmarkDetails mapLandmarkToLandmarkDetails(ResultSet rs) throws SQLException {
        return LandmarkDetails
                .builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .location(rs.getString("location"))
                .image(rs.getBytes("image"))
                .textDescription(rs.getString("text_description"))
                .audioDescription(rs.getBytes("audio_description"))
                .price(rs.getFloat("price"))
                .category(rs.getString("category"))
                .build();
    }

    private MostVisitedLandmarkDTO mapLandmarkToMostVisitedLandmarkDTO(ResultSet rs) throws SQLException {
        final LandmarkDetails landmark = LandmarkDetails
                .builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .location(rs.getString("location"))
                .image(rs.getBytes("image"))
                .textDescription(rs.getString("text_description"))
                .audioDescription(rs.getBytes("audio_description"))
                .price(rs.getFloat("price"))
                .category(rs.getString("category"))
                .build();
        final Integer visitCount = rs.getInt("visit_count");
        return MostVisitedLandmarkDTO.builder().landmarkDetails(landmark).visitCount(visitCount).build();
    }
}
