package com.stackroute.trackservice.service;

import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.exceptions.TrackAlreadyExistsException;
import com.stackroute.trackservice.exceptions.TrackNotFoundException;
import com.stackroute.trackservice.repostiory.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implements TrackService to provide basic insert, delete, get operations on Tracks
 */
@Service
//serves to create beans of only activated profiles but whereas in @Primary and @Qualifier tells to use the respective beans
@Profile("MainService")
public class TrackServiceImpl implements TrackService {
    private TrackRepository trackRepository;

    public TrackServiceImpl() {
    }

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    /**
     * Insert track into the database.
     *
     * @Input Track to be inserted in the database
     * @Output Track created after inserting into the database.
     */
    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        if (trackRepository.existsById(track.getTrackId())) {
            throw new TrackAlreadyExistsException("Track with Same ID already exists!");
        } else {
            return trackRepository.save(track);

        }

    }

    /**
     * Finds the track in the database using the trackId.
     *
     * @Input ID of the track to get from database
     * @Output Track object
     */
    @Override
    public Track getTrackById(int id) throws TrackNotFoundException {
        Track foundTrack = null;
        if (trackRepository.existsById(id)) {
            throw new TrackNotFoundException("Track you searched for, Is not available");
        } else {
            foundTrack = trackRepository.findById(id).get();
            if (foundTrack == null) {
                throw new TrackNotFoundException("Track is null");
            }
        }
        return foundTrack;
    }


    /**
     * First checks the track is present in the database and stores in a Optional.
     * If the track is present then deletes the track in the database and return the track
     * else return null.
     *
     * @Input Id of the track to be deleted.
     * @Output Track if deleted or null if the track is not found in the database.
     */
    @Override
    public Optional<Track> deleteTrackById(int id) throws TrackNotFoundException {
        if (!trackRepository.existsById(id)) {
            throw new TrackNotFoundException("Track you want to delete, Does not exist");
        }
        Optional<Track> optionalTrack = trackRepository.findById(id);
        trackRepository.delete(optionalTrack.get());
        return optionalTrack;

    }

    /**
     * Returns all the tracks in the database.
     *
     * @Output List of Tracks as List<Track>
     */
    @Override
    public List<Track> getAllTracks() throws Exception {
        if (trackRepository.findAll().isEmpty()) {
            throw new Exception();
        }
        return trackRepository.findAll();

    }

    /**
     * Finds the Track by Id as a reference and updated it's fields and save's it.
     *
     * @Input Id of the track to be updated
     * @Input Track object containing the updated details
     * @Output Updated track.
     */
    @Override
    public Track updateTrack(int id, Track trackToUpdate) throws TrackNotFoundException {
        if (!trackRepository.existsById(id)) {
            throw new TrackNotFoundException("Track to update doesn't exist,try to create new!");
        }
        trackRepository.findById(id).get().setTrackName(trackToUpdate.getTrackName());
        trackRepository.findById(id).get().setComments(trackToUpdate.getComments());
        return trackRepository.save(trackRepository.findById(id).get());

    }

    @Override
    public List<Track> selectTrackByName(String trackname) throws TrackNotFoundException {

        List<Track> foundTracks = trackRepository.findBy(trackname);
        if (foundTracks.isEmpty()) {
            throw new TrackNotFoundException("No Track exists");
        }

        return foundTracks;

}

 }
