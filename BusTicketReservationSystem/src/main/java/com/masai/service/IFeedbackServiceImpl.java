package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.FeedbackException;
import com.masai.exception.UserException;
import com.masai.model.Bus;
import com.masai.model.Feedback;
import com.masai.model.User;
import com.masai.repository.BusDao;
import com.masai.repository.FeedbackDao;
import com.masai.repository.UserRepo;

@Service
public class IFeedbackServiceImpl implements IFeedbackService{

	@Autowired
	private FeedbackDao fdao;
	
	@Autowired
	private UserRepo udao;
	
	@Autowired
	private BusDao bdao;
	
	@Override
	public Feedback addFeedback(Integer userLoginId, Integer busId, Feedback feedback) throws FeedbackException, UserException, BusException {
		
		User u = udao.findById(userLoginId).orElseThrow(() -> new UserException("User with Id " + userLoginId + " not found"));
		
		Bus b = bdao.findById(busId).orElseThrow(() -> new BusException("Bus with Id " + busId + " not found"));
		
		Feedback f = fdao.save(feedback);
		return f;
	}

	@Override
	public Feedback updateFeedback(Feedback feedback) throws FeedbackException {
		Feedback f = fdao.findById(feedback.getFeedbackId()).orElseThrow(() -> new FeedbackException("Feedback with Id " + feedback.getFeedbackId() + " does not exist"));
		
		Feedback updated = fdao.save(feedback);
		
		return updated;
	}

	@Override
	public Feedback viewFeedback(Integer feedbackId) throws FeedbackException {
		Feedback f = fdao.findById(feedbackId).orElseThrow(() -> new FeedbackException("Feedback with Id " + feedbackId + " does not exist"));
		return f;
	}

	@Override
	public List<Feedback> viewAllFeedback() throws FeedbackException {
		List<Feedback> f= fdao.findAll();
		
		if (!f.isEmpty()) return f;
		else throw new FeedbackException("Feedback not found");
	}

	
	
	
}