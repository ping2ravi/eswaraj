package com.eswaraj.domain.repo;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.eswaraj.domain.nodes.division.GeoPoint;

/**
 * Repo for geopoint queries
 * @author anuj
 * @data Feb 28, 2014
 */
public interface GeoPointRepository extends GraphRepository<GeoPoint> {
}
