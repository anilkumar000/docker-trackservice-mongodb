package com.stackroute.trackservice.seeddata;

import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.exceptions.TrackAlreadyExistsException;
import com.stackroute.trackservice.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
/*
    * Class listens to Application Context events i.e. when application
    * starts or reloads,it seeds predefined data into the database.
        */
@Component
public class ApplicationListenerSeedData implements ApplicationListener<ContextRefreshedEvent> {
    //   TrackService to perform operations on DB.
    private TrackService trackService;

//    Using @PropertySource and @Value but @value is only apllicable to class level variables
    @Value("${trackId}")
    int id;


//    Using Environment Interface but getProperty of this method only returns String objects
    @Autowired
    public Environment environment;
    @Autowired
    public ApplicationListenerSeedData( TrackService trackService) {
        this.trackService = trackService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Track track1=new Track(id,environment.getProperty("trackName"),environment.getProperty("comments"));
        Track track2=new Track(2,"Beautiful Life","Album by Angemi Remix");
        Track track3=new Track(3,"Psycho","Album of Saaho");
        try {
            trackService.saveTrack(track1);
            trackService.saveTrack(track2);
            trackService.saveTrack(track3);
        }catch (TrackAlreadyExistsException ex){
            ex.printStackTrace();
        }


    }
}
