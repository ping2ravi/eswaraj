package com.eswaraj.tasks.bolt;

import java.io.FileReader;
import java.util.Map;

import au.com.bytecode.opencsv.CSVReader;
import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Tuple;

import com.eswaraj.web.dto.LocationBoundaryFileDto;

public class SaveLocationBolt implements IRichBolt{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OutputCollector collector;
	
	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
	}

	@Override
	public void execute(Tuple input) {
		try{
			LocationBoundaryFileDto locationBoundaryFileDto = (LocationBoundaryFileDto)input.getValueByField("locationBoundaryFileDto");
			CSVReader csvReader = new CSVReader(new FileReader(locationBoundaryFileDto.getFileNameAndPath()));
			String headers[] = csvReader.readNext();
			//First column will be latitude and second column will be longitude
			String line[];
			while((line = csvReader.readNext()) != null){
				
			}
			
		}catch(Exception ex){
			collector.fail(input);
		}
	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}


}
