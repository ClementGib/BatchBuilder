package com.clement.gibert.batch.parameters;

import java.util.HashMap;
import java.util.Map;

public class Parameters {
	private String topic;
	private Map<String, String> definitions = new HashMap<String, String>();

	public Parameters(String topic, Map<String, String> definitions) {
		this.setDefinitions(definitions);
		this.setTopic(topic);
	}

	public Map<String, String> getDefinitions() {
		return definitions;
	}

	public void setDefinitions(Map<String, String> definitions) {
		this.definitions = definitions;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
}
