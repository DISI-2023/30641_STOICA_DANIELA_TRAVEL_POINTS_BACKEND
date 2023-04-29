package com.disi.TravelPoints.model;


;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "start_date")
    private Timestamp start;
    @Column(name = "end_date")
    private Timestamp end;
    @Column(name = "discount")
    private Float discount;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "landmark_id", nullable = false)
    private Landmark landmark;
}
