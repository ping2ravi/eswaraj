package com.eswaraj.tasks;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import backtype.storm.utils.Utils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        TopologyBuilder builder = new TopologyBuilder(); 
        builder.setSpout("words", new TestSpout(), 1); 
        builder.setBolt("exclaim1", new TestBolt(), 1) .shuffleGrouping("words"); 
        builder.setBolt("exclaim2", new PrintTestBolt(), 1) .shuffleGrouping("exclaim1");

        
        Config conf = new Config(); 
        conf.setDebug(false); 
        //conf.setNumWorkers(5);
        conf.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 1);
        System.out.println( "creating Cluster" );
        
        LocalCluster cluster = new LocalCluster(); 
        
        System.out.println( "creating Builder" );
        System.out.println( "Submitting to cluster" );
        cluster.submitTopology("words", conf, builder.createTopology());
        Utils.sleep(10000); 
        System.out.println( "Killing" );
        cluster.killTopology("words"); 
        cluster.shutdown();
        Utils.sleep(10000);
        System.out.println( "Finished" );
    }
}
