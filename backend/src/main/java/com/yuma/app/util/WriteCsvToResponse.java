package com.yuma.app.util;

import java.io.PrintWriter;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import com.opencsv.CSVWriter;
import com.yuma.app.document.Consumer;
import com.yuma.app.document.Ingredients;
import com.yuma.app.document.Meal;

@Slf4j
public class WriteCsvToResponse {

	public static void writeConsumers(PrintWriter writer, List<Consumer> consumers)  {

		String[] CSV_HEADER = {"company name","name" ,"meal", "ingredients"};
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
				String companyName = consumer.getCompany();
				for (Meal meal : consumer.getMealList()) {
					String[] data = {
						companyName,
						fullName.toString(),
						meal.getName(),
						getIngredientsListAsText(meal.getIngredients())
					};
					csvWriter.writeNext(data);
				}
			}
			log.info("Write CSV using BeanToCsv successfully!");
		}catch (Exception e) {
			log.info("Writing CSV error!");
			e.printStackTrace();
		}
	}
	
	private static String getIngredientsListAsText(List<Ingredients> ingredients){
		StringBuilder stringBuilder = new StringBuilder();
		
		for (Ingredients ingredient : ingredients){
			stringBuilder.append(ingredient.getName()).append(", ");
		}
		if (ingredients.size()>=1) {
			stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
		}
		
		return "\""+stringBuilder.toString()+"\"";
	}
}
