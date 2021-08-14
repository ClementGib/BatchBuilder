package com.clement.gibert.batch.builder;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.jberet.job.model.Job;
import org.jberet.job.model.JobBuilder;
import org.jberet.job.model.Step;
import org.jberet.job.model.StepBuilder;

import com.clement.gibert.batch.parameters.Parameters;
import com.clement.gibert.xutools.utils.exceptions.TechnicalException;

public class JobBuilderServiceImpl implements JobBuilderService {

	@Override
	public Job buildJob(Parameters parameters) throws Exception {
		Job job = null;
		Instant date = Instant.now();
		try {
			String JobName = parameters.getTopic() + " (" + date + ")";
			JobBuilder builder = new JobBuilder(JobName).restartable(true).step(getStarterStep(JobName));
			getSteps(parameters).forEach(step -> builder.step(step));
			builder.step(getClosingStep(JobName));
		} catch (Exception exception) {
			throw new TechnicalException("An technical error occured during construction of Jobs");
		}
		return job;
	}

	private Step getStarterStep(String JobName) {
		return new StepBuilder("starter").batchlet("startBatchlet", new String[] { "jobName", JobName }).build();
	}

	private List<Step> getSteps(Parameters parameters) {
		List<Step> steps = new ArrayList<Step>();
		parameters.getDefinitions().forEach((word, definition) -> steps.add(createStep(word, definition)));
		return steps;
	}

	private Step createStep(String word, String defintion) {
		return new StepBuilder("add" + word)
				.batchlet("addwordBatchlet", new String[] { "word", word }, new String[] { "definition", defintion })
				.build();
	}

	private Step getClosingStep(String JobName) {
		return new StepBuilder("closure").batchlet("endBatchlet", new String[] { "jobName", JobName }).build();
	}
}
