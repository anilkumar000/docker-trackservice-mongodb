package com.stackroute.trackservice.seeddata;

import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.exceptions.TrackAlreadyExistsException;
import com.stackroute.trackservice.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
/*
    * Class implements CommandLineRunner to get called after application context is
    * created and seeds data to the database.
        */
//creates Bean for this class
@Component

//provides .properties file of defined to application environment
@PropertySource("track.properties")

/*If we have properties which are grouped together,
 we can make use of the @ConfigurationProperties annotation which will map these property hierarchies
into Java objects graphs, but it requires standard getter setter methods to map to class properties
 */
@ConfigurationProperties("track")
public class CommandLineRunnerSeedData implements CommandLineRunner {
    int trackId;
    String trackName;
    String comments;

   private TrackService trackService;

    @Autowired
    public CommandLineRunnerSeedData( TrackService trackService){
        this.trackService=trackService;

    }

    @Override
    public void run(String... args) throws Exception {
        Track track1=new Track(trackId,trackName,comments);
        Track track2=new Track(5,"Beautiful","Album by Angemi Remix");
        Track track3=new Track(6,"Psycho","Album of Saaho");
        try {
            trackService.saveTrack(track1);
            trackService.saveTrack(track2);
            trackService.saveTrack(track3);
        }catch (TrackAlreadyExistsException ex){
            ex.printStackTrace();
        }

    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
