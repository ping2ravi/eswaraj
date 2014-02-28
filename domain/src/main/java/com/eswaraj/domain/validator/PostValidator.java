package com.eswaraj.domain.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eswaraj.domain.nodes.Post;
import com.eswaraj.domain.validator.exception.ValidationException;

@Component
public class PostValidator extends BaseValidator<Post>{

	@Autowired
	public PostValidator(ValidationManager validationManager) {
		super(Post.class, validationManager);
	}

	public void validateBeforeSave(Post post) throws ValidationException {
		
	}

	public void validateBeforeDelete(Post post) throws ValidationException {
		
	}
}
