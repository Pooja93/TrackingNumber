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
	
public splitInterval(rowObject robj1, rowObject robj2){
    int LOW;
    int HIGH;

    if(rObj1.low<rObj2.low){
            LOW=robj1.low;
            HIGH=robj1.high;
        }
    else if(rObj1.low>rObj2.low){
            LOW=robj2.low;
            HIGH=robj2.high;
        }    
             
    updateTableRow(LOW,(rObj2.low)-1);
    updateTableRow(rObj2.low,rObj2.high);
    updateTableRow(rObj2.high+1,HIGH);
        }
        
    if((rObj1.low<rObj2.low)&&(rObj2.low<rObj1.high)){   //more overlap
            updateTableRow(rObj1.low,(rObj2.low)-1);
            updateTableRow(rObj2.low,rObj1.high);
            updateTableRow((rObj1.high)+1,rObj2.high);
        }
    else if((rObj2.low<rObj1.low)&&(rObj2.high<rObj1.high)){   //less overlap
            updateTableRow(rObj2.low,(rObj1.high)-1);
            updateTableRow(rObj1,high,rObj2.high);
            updateTableRow(rObj2.high+1,rObj1.high);
        }
        
    }
    
}
