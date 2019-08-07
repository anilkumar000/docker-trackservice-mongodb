package com.stackroute.trackservice.repostiory;

import com.stackroute.trackservice.domain.Track;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface to perform database operations with a dynamic DB
 */
@Repository
public interface TrackRepository extends MongoRepository<Track,Integer> {
// @Query annotation is used to pass queries to parse the data in DB
//    @Query("select t from Track t where t.trackName like %?1%")
    List<Track> findBy(String trackName);
}