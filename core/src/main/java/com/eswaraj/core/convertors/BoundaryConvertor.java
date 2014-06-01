package com.eswaraj.core.convertors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eswaraj.core.exceptions.ApplicationException;
import com.eswaraj.domain.nodes.division.Boundary;
import com.eswaraj.domain.repo.BoundaryRepository;
import com.eswaraj.web.dto.BoundaryDto;

@Component
public class BoundaryConvertor extends BaseConvertor<Boundary, BoundaryDto> {

	@Autowired
	private BoundaryRepository boundaryRepository;
	

	@Override
	protected Boundary convertInternal(BoundaryDto boundaryDto) throws ApplicationException {
		Boundary boundary = getObjectIfExists(boundaryDto, "Boundary", boundaryRepository) ;
		if(boundary == null){
			boundary = new Boundary();
		}
		BeanUtils.copyProperties(boundaryDto, boundary);
		return boundary;
	}

	@Override
	protected BoundaryDto convertBeanInternal(Boundary dbDto) {
		BoundaryDto boundaryDto = new BoundaryDto();
		BeanUtils.copyProperties(dbDto, boundaryDto);
		return boundaryDto;
	}

}
