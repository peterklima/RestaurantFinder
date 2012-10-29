package at.fhooe.im620.restaurantfinder.converter;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import at.fhooe.im620.restaurantfinder.bo.BusinessHours;
import at.fhooe.im620.restaurantfinder.bo.Weekday;

@FacesConverter("at.fhooe.im620.restaurantfinder.converter.BusinessHourConverter")
public class BusinessHourConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		Set<BusinessHours> hours = (Set<BusinessHours>) value;
		Map<Weekday, ArrayList<String>> hoursSorted = new EnumMap<Weekday, ArrayList<String>>(Weekday.class);
		for (BusinessHours hour : hours) {
			Weekday weekday = hour.getWeekday();
			ArrayList<String> businessHourList = getOrCreateBusinessHourList(hoursSorted, weekday);
			businessHourList.add(hour.getStart() + " - " + hour.getEnd());
			hoursSorted.put(weekday, businessHourList);
		}

		String output = "";
		for (Weekday day : Weekday.values()) {
			ArrayList<String> hoursOfDay = hoursSorted.get(day);
			output += day.getLabel() + ": ";
			if (hoursOfDay == null || hoursOfDay.isEmpty()) {
				output += "closed";
			} else {
				for (String hour : hoursOfDay) {
					output += hour + ",";
				}
				output = output.substring(0, output.length() - 1);
			}
			output += "<br />";
		}
		return output;
	}

	private ArrayList<String> getOrCreateBusinessHourList(Map<Weekday, ArrayList<String>> hoursSorted, Weekday weekday) {
		ArrayList<String> businessHourList = hoursSorted.get(weekday);
		if (businessHourList == null) {
			businessHourList = new ArrayList<String>();
		}
		return businessHourList;
	}

}
