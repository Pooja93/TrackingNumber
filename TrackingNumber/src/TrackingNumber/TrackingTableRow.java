package TrackingNumber;

public class TrackingTableRow {
	Range range;
	char statusCode;
	int trackingCode;
	boolean isDeleted;
	
	TrackingTableRow(int startRange, int endRange, int trackingCode, char statusCode) {
		range = new Range(); 
		isDeleted = false;
	}

	public TrackingTableRow processRow (TrackingTableRow old) {
		if (this.range.classify(old.range) == Range.Relation.SAME) {
			old.isDeleted = true;
			return null;
		} 
		if (this.range.classify(old.range) == Range.Relation.SUBSET) {
			return split();
		}
		if (this.range.classify(old.range) == Range.Relation.SUPERSET) {
			old.isDeleted = true;
			return null;
		}
		if (this.range.classify(old.range) == Range.Relation.L) {
			
		}
			
		
		return null;
	}
}
