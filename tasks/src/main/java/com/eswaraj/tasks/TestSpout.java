package com.eswaraj.tasks;

import java.util.Map;
import java.util.Random;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

public class TestSpout extends BaseRichSpout{

	private SpoutOutputCollector spoutOutputCollector;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void ack(Object arg0) {
		System.out.println("TestSpout:ack "+ arg0);

	}

	public void activate() {
		System.out.println("TestSpout:Activate");

	}

	public void close() {
		System.out.println("TestSpout:Close");

	}

	public void deactivate() {
		System.out.println("TestSpout:deactivate");

	}

	public void fail(Object arg0) {
		System.out.println("TestSpout:fail-"+arg0);

	}

	public void nextTuple() {
		System.out.println("TestSpout:nextTuple");
		Utils.sleep(100); 
		final String[] words = new String[] {"nathan", "mike", "jackson", "golda", "bertels"}; 
		final Random rand = new Random(); 
		final String word = words[rand.nextInt(words.length)]; 
		spoutOutputCollector.emit(new Values(word));

	}

	public void open(Map arg0, TopologyContext arg1, SpoutOutputCollector spoutOutputCollector) {
		System.out.println("TestSpout:open");
		this.spoutOutputCollector = spoutOutputCollector;
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		System.out.println("TestSpout:declareOutputFields");
		declarer.declare(new Fields("word"));
	}

	public Map<String, Object> getComponentConfiguration() {
		System.out.println("TestSpout:getComponentConfiguration");
		return null;
	}

}
