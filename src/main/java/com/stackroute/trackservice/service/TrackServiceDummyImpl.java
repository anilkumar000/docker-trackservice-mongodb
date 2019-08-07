package com.stackroute.trackservice.service;

import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.exceptions.TrackAlreadyExistsException;
import com.stackroute.trackservice.exceptions.TrackNotFoundException;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Profile("dummy")
@Service
public class TrackServiceDummyImpl implements TrackService {
    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        return null;
    }

    @Override
    public Track getTrackById(int id) throws TrackNotFoundException {
        return null;
    }

    @Override
    public Optional<Track> deleteTrackById(int id) throws TrackNotFoundException {
        return Optional.empty();
    }

    @Override
    public List<Track> getAllTracks() throws Exception {
        return null;
    }

    @Override
    public Track updateTrack(int id, Track trackToUpdate) throws TrackNotFoundException {
        return null;
    }

    @Override
    public List<Track> selectTrackByName(String trackName) throws TrackNotFoundException {
        return null;
    }
}
