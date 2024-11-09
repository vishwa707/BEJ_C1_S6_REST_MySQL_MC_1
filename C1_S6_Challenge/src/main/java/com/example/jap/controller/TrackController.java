package com.example.jap.controller;

import com.example.jap.service.TrackService;
import com.example.jap.domain.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tracks")
public class TrackController {

    private final TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping
    public ResponseEntity<Track> createTrack(@RequestBody Track track) {
        return new ResponseEntity<>(trackService.saveTrack(track), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Track>> getAllTracks() {
        return new ResponseEntity<>(trackService.getAllTracks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Track> getTrackById(@PathVariable Long id) {
        return trackService.getTrackById(id)
                .map(track -> new ResponseEntity<>(track, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/artist/{artist}")
    public ResponseEntity<List<Track>> getTracksByArtist(@PathVariable String artist) {
        return new ResponseEntity<>(trackService.getTracksByArtist(artist), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Track> updateTrack(@PathVariable Long id, @RequestBody Track updatedTrack) {
        return new ResponseEntity<>(trackService.updateTrack(id, updatedTrack), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrack(@PathVariable Long id) {
        trackService.deleteTrack(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

