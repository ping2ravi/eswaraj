package com.eswaraj.domain.repo;


import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.eswaraj.domain.nodes.Video;
import com.eswaraj.domain.validator.exception.ValidationException;

/**
 * Test for Video repository
 * @author anuj
 * @data Jan 26, 2014
 */

@ContextConfiguration(locations = { "classpath:eswaraj-domain-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestVideoRepository {

	@Autowired VideoRepository videoRepository;

	@Test
	public void shouldSaveVideo() {
		Video video = new Video();
		video.setOrgUrl("test.url.video");
		video = videoRepository.save(video);
		Video expectedVideo = videoRepository.getById(video.getId());
		assertEquals(expectedVideo.getOrgUrl(), video.getOrgUrl());
	}
	
	@Test(expected=ValidationException.class)
	public void shouldCheck_EmptyOrgUrl() {
		Video video = new Video();
		video = videoRepository.save(video);
	}
}
