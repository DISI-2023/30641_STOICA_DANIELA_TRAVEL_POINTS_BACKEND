package com.disi.TravelPoints.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "landmarks")
public class Landmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "location")
    private String location;
    @Column(name = "image")
    private byte[] image;
    @Column(name = "text_description")
    private String textDescription;
    @Column(name = "audio_description")
    private byte[] audioDescription;
    @Column(name = "category")
    private String category;
    @Column(name = "price")
    private float price;
    @ManyToMany(mappedBy = "landmarks")
    Set<Wishlist> wishlists;
    @OneToMany(mappedBy="landmark", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<Review> reviews;
}
