package com.stackroute.trackservice.service;

import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.exceptions.TrackAlreadyExistsException;
import com.stackroute.trackservice.exceptions.TrackNotFoundException;

import java.util.List;
import java.util.Optional;

/**
 * TrackService Interface provides method declarations for all the operations supported
 * in the application.
 */
public interface TrackService {
    public Track saveTrack(Track track) throws TrackAlreadyExistsException;
    public Track getTrackById(int id) throws TrackNotFoundException;
    public Optional<Track> deleteTrackById(int id) throws TrackNotFoundException;
    public List<Track> getAllTracks() throws Exception;
    public Track updateTrack(int id,Track trackToUpdate) throws TrackNotFoundException;
    public List<Track> selectTrackByName(String trackName ) throws TrackNotFoundException;
    }

