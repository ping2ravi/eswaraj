package com.eswaraj.tasks;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class PrintTestBolt implements IRichBolt {

	OutputCollector outputCollector;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void cleanup() {
		System.out.println("PrintTestBolt:cleanup");

	}

	public void execute(Tuple tuple) {
		System.out.println("PrintTestBolt:execute");
		System.out.println(tuple.getString(0));
		outputCollector.ack(tuple);
	}

	public void prepare(Map arg0, TopologyContext arg1, OutputCollector outputCollector) {
		System.out.println("PrintTestBolt:prepare");
		this.outputCollector = outputCollector;

	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		System.out.println("PrintTestBolt:declareOutputFields");
		declarer.declare(new Fields("word"));
	}

	public Map<String, Object> getComponentConfiguration() {
		System.out.println("PrintTestBolt:getComponentConfiguration");
		return null;
	}

}
