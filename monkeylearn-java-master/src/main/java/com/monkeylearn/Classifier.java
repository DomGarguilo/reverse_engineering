package com.monkeylearn;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Classifier {
	
	static MonkeyLearn ml = new MonkeyLearn("1424eef742ffc2adca7641972ef4c0c6861feb99");
	static String modelId = "cl_xzx6BXWL";

	public static void main(String[] args) throws MonkeyLearnException {
		

		String[] data = { "i hated the hotel because the wifi sucked" };
		MonkeyLearnResponse res = ml.classifiers.classify(modelId, data, true);

		JSONArray array = res.arrayResult;
		JSONArray obj = (JSONArray) array.get(0);
		JSONArray obj1 = (JSONArray) obj.get(0);
		JSONObject json = (JSONObject)obj1.get(0);
		
		System.out.println(json.get("category_id"));
				
		System.out.println(obj1.get(0));
		System.out.println(res.arrayResult);
	}
	
	public JSONObject getResult(String[] input) throws MonkeyLearnException {
		//String result = new String();
		MonkeyLearnResponse res = ml.classifiers.classify(modelId, input, true);
		JSONArray temp1 = res.arrayResult;
		JSONArray temp2 = (JSONArray) temp1.get(0);
		JSONArray temp3 = (JSONArray) temp2.get(0);
		JSONObject json = (JSONObject) temp3.get(0);
		/*
		result += "Prediction: " + json.get("label");
		result += "Confidence: " + json.get("confidence");
		result += "Probability: " + json.get("probability");
		result += "Category ID: " + json.get("category_id");
		*/
		return json;
	}

}
