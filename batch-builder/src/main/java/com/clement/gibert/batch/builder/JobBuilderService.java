package com.clement.gibert.batch.builder;

import org.jberet.job.model.Job;

import com.clement.gibert.batch.parameters.Parameters;

public interface JobBuilderService {

	/***
	 * Build job correspondant aux parametres
	 * 
	 * @param name
	 */
	public Job buildJob(Parameters parameters) throws Exception;
}
