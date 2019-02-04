package com.yuma.app.util;

import java.io.PrintWriter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.CSVWriter;
import com.yuma.app.document.Consumer;
import com.yuma.app.document.Ingredients;
import com.yuma.app.document.Meal;

public class WriteCsvToResponse {

	private static final Logger LOGGER = LoggerFactory.getLogger(WriteCsvToResponse.class);

	public static void writeConsumers(PrintWriter writer, List<Consumer> consumers)  {

		String[] CSV_HEADER = {"name" ,"meal", "ingredients"};
		try (
			CSVWriter csvWriter = new CSVWriter(writer,
				CSVWriter.DEFAULT_SEPARATOR,
				CSVWriter.DEFAULT_ESCAPE_CHARACTER,
				CSVWriter.DEFAULT_LINE_END)
		){
			csvWriter.writeNext(CSV_HEADER);
			
			for (Consumer consumer : consumers) {
				StringBuilder fullName = new StringBuilder();
				fullName.append(consumer.getFirstName().toUpperCase()).append(" ").append(consumer.getLastName());
				for (Meal meal : consumer.getMealList()) {
					String[] data = {
						fullName.toString(),
						meal.getName(),
						getIngredientsListAsText(meal.getIngredients())
					};
					csvWriter.writeNext(data);
				}
			}
			
			LOGGER.info("Write CSV using BeanToCsv successfully!");
		}catch (Exception e) {
			LOGGER.info("Writing CSV error!");
			e.printStackTrace();
		}
	}
	
	private static String getIngredientsListAsText(List<Ingredients> ingredients){
		StringBuilder stringBuilder = new StringBuilder();
		
		for (Ingredients ingredient : ingredients){
			stringBuilder.append(ingredient.getName()).append(", ");
		}
		stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
		
		return "\""+stringBuilder.toString()+"\"";
	}
}
