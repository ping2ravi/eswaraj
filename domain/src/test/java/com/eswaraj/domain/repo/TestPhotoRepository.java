package com.eswaraj.domain.repo;


import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.eswaraj.domain.nodes.Photo;
import com.eswaraj.domain.validator.exception.ValidationException;

/**
 * Test for Photo repository
 * @author anuj
 * @data Jan 26, 2014
 */

@ContextConfiguration(locations = { "classpath:eswaraj-domain-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestPhotoRepository {

	@Autowired PhotoRepository photoRepository;

	@Test
	public void shouldSavePhoto() {
		Photo photo = new Photo();
		photo.setOrgUrl("test.url");
		photo = photoRepository.save(photo);
		Photo expectedPhoto = photoRepository.getById(photo.getId());
		assertEquals(expectedPhoto.getOrgUrl(), photo.getOrgUrl());
	}
	
	@Test(expected=ValidationException.class)
	public void shouldCheck_EmptyOrgUrl() {
		Photo photo = new Photo();
		photo = photoRepository.save(photo);
	}
}
