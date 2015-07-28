package TrackingNumber;

import java.util.ArrayList;

public class TrackingTable {

	ArrayList<TrackingTableRow> rows= new ArrayList<TrackingTableRow>();
	
	public void updateTable (int startRange, int endRange, char statusCode, int trackingCode) {
		TrackingTableRow ttrNew = new TrackingTableRow(startRange, endRange, statusCode, trackingCode);
		rows.add(ttrNew);
		processRows(0, rows.size(), ttrNew);
	}
	
	private void processRows (int startInd, int endInd, TrackingTableRow ttr) {
		for (int ind = startInd; ind < endInd; ind++) {
			if ( ! rows.get(ind).isDeleted) {
				ArrayList<TrackingTableRow> newRows = ttr.processRow(rows.get(ind));
				if (newRows == null) {
					processRows (0, ind, ttr);
				} else {
					for (TrackingTableRow nrow : newRows) {
						rows.add(nrow);
					}
				}
			}
		} 
	}
	
	public String getTrackingTableData () {
		String tableData = "";
		
		for (TrackingTableRow row: rows) {
			if (row.isDeleted) {
				continue;
			} else {
				tableData += row.toString();
			}
		}
		return tableData;
	}
	
}
