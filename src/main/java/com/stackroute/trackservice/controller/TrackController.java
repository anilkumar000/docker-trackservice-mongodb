package com.stackroute.trackservice.controller;

import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.exceptions.TrackAlreadyExistsException;
import com.stackroute.trackservice.exceptions.TrackNotFoundException;
import com.stackroute.trackservice.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class TrackController {
    TrackService trackService;
    @Autowired
    public TrackController( TrackService trackService) {
        this.trackService = trackService;
    }

    /**
     * Insert track into the database
     * @Input Track to be inserted
     * @Output Newly created track
     */
    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track)throws TrackAlreadyExistsException {
//        try{
            Track savedTrack=trackService.saveTrack(track);
            return new ResponseEntity<>(savedTrack, HttpStatus.CREATED);
//        }
//        catch (TrackAlreadyExistsException ex){
//            ex.printStackTrace();
//            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
//
//        }


    }

    /**
     * Search Track
     * @Input id Id of the track
     * @Output Track
     */
    @GetMapping("track/{id}")
    public ResponseEntity<?> getTrackById(@PathVariable int id) throws TrackNotFoundException
    {
//        try {
            Track foundTrackById = trackService.getTrackById(id);

            return new ResponseEntity<>(foundTrackById, HttpStatus.FOUND);

//        }
//        catch (TrackNotFoundException ex){
//            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
//        }
    }

    /**
     * Delete track by ID
     * @Input  Track Id to be deleted
     * @Output Deleted Track
     */
    @DeleteMapping("track")
    public ResponseEntity<?> deleteTrackById(@RequestParam int id)throws TrackNotFoundException {
//        try {
            Track deletedTrack=trackService.deleteTrackById(id).get();
            return new ResponseEntity<>(deletedTrack,HttpStatus.OK);
//        }
//        catch (TrackNotFoundException ex){
//            return new ResponseEntity<>(ex.getMessage(),HttpStatus.CONFLICT);
//        }

    }

    /**
     * Get all tracks
     * @Output All tracks in the database
     */
    @GetMapping("track")
    public ResponseEntity<?> getAllTracks()throws Exception {
//        try {
            List<Track> listOfTracks=trackService.getAllTracks();
            return new ResponseEntity<>(listOfTracks,HttpStatus.OK);
//        }
//        catch (Exception ex){
//            return new ResponseEntity<>("Nothing is present",HttpStatus.INTERNAL_SERVER_ERROR);
//        }

    }

    /**
     * Update Track by Id.
     * @Input Id of the track to be updated.
     * @Input Track object containing updated track details.
     * @Output Updated track
     */
    @PutMapping("track")
    public ResponseEntity<?> updateTrack(@RequestParam int id, @RequestBody Track trackToUpdate) throws TrackNotFoundException{
//        try {
            Track updatedTrack=trackService.updateTrack(id,trackToUpdate);
            return new ResponseEntity<>(updatedTrack,HttpStatus.OK);
//        }catch (TrackNotFoundException ex){
//            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
//        }

    }

    /**
     * Search Track by Name.
     * @Input String  of the track name to search for.
     * @Output List of found tracks
     */
    @GetMapping("tracks/{trackName}")
    public ResponseEntity<?> selectTrackByName(@PathVariable String trackName) throws TrackNotFoundException{
//        try {
            List<Track> foundTracks=trackService.selectTrackByName(trackName);
            return new ResponseEntity<>(foundTracks,HttpStatus.FOUND);
//        }catch (TrackNotFoundException ex){
//            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
//        }

    }

}
