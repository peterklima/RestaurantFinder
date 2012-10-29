package at.fhooe.im620.restaurantfinder.converter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import at.fhooe.im620.restaurantfinder.bo.DayRange;

@FacesConverter("at.fhooe.im620.restaurantfinder.converter.ClosedDayConverter")
public class ClosedDayConverter implements Converter {

	private static final DayRangeComparator dayRangeComparator = new DayRangeComparator();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		List<DayRange> closedDayList = convertToSortedList((Set<DayRange>) value);
		String output = "";
		if (closedDayList.isEmpty()) {
			output = "<i>none</i><br />";
		} else {
			for (DayRange closed : closedDayList) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
				String startDay = dateFormat.format(closed.getStartDay());
				String endDay = dateFormat.format(closed.getEndDay());
				output += startDay;
				if (!startDay.equals(endDay)) {
					output += " - " + endDay;
				}
				output += "<br />";
			}
		}
		return output;
	}

	private List<DayRange> convertToSortedList(Set<DayRange> closedDays) {
		List<DayRange> closedDayList = new ArrayList<DayRange>(closedDays);
		java.util.Collections.sort(closedDayList, dayRangeComparator);
		return closedDayList;
	}

	static class DayRangeComparator implements Comparator<DayRange> {
		@Override
		public int compare(DayRange rangeA, DayRange rangeB) {
			return rangeA.getStartDay().compareTo(rangeB.getStartDay());
		}

	}
}
