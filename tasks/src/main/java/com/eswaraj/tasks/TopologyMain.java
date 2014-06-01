package com.eswaraj.tasks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;


public class TopologyMain {
	public static void main(String[] args) throws InterruptedException {
         
        //Topology definition
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("word-reader",new TestSpout());
		builder.setBolt("word-normalizer", new TestBolt()).shuffleGrouping("word-reader");
		builder.setBolt("word-counter", new PrintTestBolt(),1).fieldsGrouping("word-normalizer", new Fields("word"));
		
		Logger logger = LoggerFactory.getLogger("test");
		logger.info("Test");
        //Configuration
		Config conf = new Config();
		conf.put("wordsFile", "/Users/ravi/Documents/github/others/examples-ch02-getting_started/src/main/resources/words.txt");
		conf.setDebug(false);
        //Topology run
		conf.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 1);
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("Getting-Started-Toplogie", conf, builder.createTopology());
		logger.info("waiting for 10 seconds to finish");
		Thread.sleep(10000);
		logger.info("shutdown started");
		cluster.shutdown();
	}
}
