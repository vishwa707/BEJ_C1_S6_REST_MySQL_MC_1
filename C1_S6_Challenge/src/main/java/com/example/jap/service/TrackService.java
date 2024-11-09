package com.example.jap.service;

import com.example.jap.domain.Track;

import java.util.List;
import java.util.Optional;

public interface TrackService {
    Track saveTrack(Track track);
    List<Track> getAllTracks();
    Optional<Track> getTrackById(Long id);
    void deleteTrack(Long id);
    List<Track> getTracksByArtist(String artist);
    Track updateTrack(Long id, Track updatedTrack);
}

