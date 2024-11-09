package com.example.jap.service;

import com.example.jap.domain.Track;
import com.example.jap.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImpl implements TrackService {

    private final TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track track) {
        return trackRepository.save(track);
    }

    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @Override
    public Optional<Track> getTrackById(Long id) {
        return trackRepository.findById(id);
    }

    @Override
    public void deleteTrack(Long id) {
        trackRepository.deleteById(id);
    }

    @Override
    public List<Track> getTracksByArtist(String artist) {
        return trackRepository.findByTrackArtist(artist);
    }

    @Override
    public Track updateTrack(Long id, Track updatedTrack) {
        return trackRepository.findById(id)
                .map(track -> {
                    track.setTrackName(updatedTrack.getTrackName());
                    track.setTrackComments(updatedTrack.getTrackComments());
                    track.setTrackRating(updatedTrack.getTrackRating());
                    track.setTrackArtist(updatedTrack.getTrackArtist());
                    return trackRepository.save(track);
                })
                .orElseThrow(() -> new RuntimeException("Track not found with id: " + id));
    }
}

