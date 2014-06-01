package com.eswaraj.core.convertors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eswaraj.core.exceptions.ApplicationException;
import com.eswaraj.domain.nodes.division.Boundary;
import com.eswaraj.domain.nodes.division.GeoPoint;
import com.eswaraj.domain.repo.BoundaryRepository;
import com.eswaraj.domain.repo.GeoPointRepository;
import com.eswaraj.web.dto.GeoPointDto;

@Component
public class GeoPointConvertor extends BaseConvertor<GeoPoint, GeoPointDto> {

	@Autowired
	private GeoPointRepository geoPointRepository;
	@Autowired
	private BoundaryRepository boundaryRepository;
	

	@Override
	protected GeoPoint convertInternal(GeoPointDto geoPointDto) throws ApplicationException {
		GeoPoint geoPoint = getObjectIfExists(geoPointDto, "GeoPoint", geoPointRepository) ;
		if(geoPoint == null){
			geoPoint = new GeoPoint();
		}
		BeanUtils.copyProperties(geoPointDto, geoPoint);
		if(geoPointDto.getBoundaryId() != null){
			Boundary boundary = boundaryRepository.findOne(geoPointDto.getBoundaryId());
			if(boundary == null){
				throw new ApplicationException("No such Boundary exists[id="+geoPointDto.getBoundaryId()+"]");
			}
			geoPoint.setBoundary(boundary);
		}
		return geoPoint;
	}

	@Override
	protected GeoPointDto convertBeanInternal(GeoPoint dbDto) {
		GeoPointDto geoPointDto = new GeoPointDto();
		BeanUtils.copyProperties(dbDto, geoPointDto);
		if(dbDto.getBoundary() != null){
			geoPointDto.setBoundaryId(dbDto.getBoundary().getId());	
		}
		return geoPointDto;
	}

}
