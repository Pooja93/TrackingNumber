package TrackingNumber;

import java.util.ArrayList;

public class TrackingTableRow {
	Range range;
	char statusCode;
	int trackingCode;
	boolean isDeleted;
	
	TrackingTableRow(int startRange, int endRange, int trackingCode, char statusCode) {
		range = new Range(); 
		isDeleted = false;
	}
	public ArrayList<TrackingTableRow> split(TrackingTableRow newRow, TrackingTableRow oldRow) {
		int rangelowfirst, rangelowsecond;
		int rangehighfirst, rangehighsecond;
		ArrayList<TrackingTableRow> rows = new ArrayList<TrackingTableRow>();
		if (newRow.range.lo < oldRow.range.lo) {
			rangelowfirst = newRow.range.lo;
			rangelowsecond = oldRow.range.lo;

		} else {
			rangelowfirst = oldRow.range.lo;
			rangelowsecond = newRow.range.lo;
		}
		if (newRow.range.hi < oldRow.range.hi) {
			rangehighfirst = newRow.range.hi;
			rangehighsecond = oldRow.range.hi;
		} else {
			rangehighfirst = oldRow.range.hi;
			rangehighsecond = newRow.range.hi;

		}

		rows.add(new TrackingTableRow(rangelowfirst, rangelowsecond - 1, oldRow.trackingCode, oldRow.statusCode));
		rows.add(new TrackingTableRow(rangelowsecond, rangehighfirst - 1, newRow.trackingCode, newRow.statusCode));
		rows.add(new TrackingTableRow(rangehighfirst, rangehighsecond, oldRow.trackingCode, oldRow.statusCode));
		return rows;

	}

	public ArrayList<TrackingTableRow> processRow (TrackingTableRow old) {
		if (this.range.classify(old.range) == Range.Relation.SAME) {
			old.isDeleted = true;
			return null;
		} 
		if (this.range.classify(old.range) == Range.Relation.SUBSET) {
			return split(this, old);
		}
		if (this.range.classify(old.range) == Range.Relation.SUPERSET) {
			old.isDeleted = true;
			return null;
		}
		if (this.range.classify(old.range) == Range.Relation.LESSOVERLAP) {
			return split(this, old);
		}
		if (this.range.classify(old.range) == Range.Relation.MOREOVERLAP) {
			return split(this, old);
		}
		return null;
	}
}
	

