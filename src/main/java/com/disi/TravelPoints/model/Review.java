package com.disi.TravelPoints.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "comment")
    private String comment;
    @Column(name = "rating")
    private Integer rating;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="landmark_id", nullable = false)
    private Landmark landmark;
}
