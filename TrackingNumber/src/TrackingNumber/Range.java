/* ----------------------------------------------------*
 * Represents the closed interval [a, b] where a, b    *
 * are integers.                                       *
 * ----------------------------------------------------*/
package TrackingNumber;

public class Range {

    private int lo;
    private int hi;

    public static enum Relation {
        SUBSET, SUPERSET, LESSOVERLAP, MOREOVERLAP, LESSDISJOINT, MOREDISJOINT, SAME;
    }

    private boolean contains(int x) {
        return this.lo <= x && x <= this.hi;
    }
    private boolean contains(Range r) {
        return this.lo <= r.lo && r.hi <= this.hi;
    }
    private boolean equals(Range r) {
        return this.lo == r.lo && this.hi == r.hi;
    }
    private boolean isDisjoint(Range r) {
        return this.lo > r.hi || this.hi < r.lo;
    }
    private boolean isOverlapping(Range r) {
        return !(this.isDisjoint(r));
    }
    private boolean lessThan(Range r) {
        return this.lo < r.lo;
    }
    
    public Relation classify(Range r) {
        if (this.equals(r))
            return Relation.SAME;
        if (this.contains(r))
            return Relation.SUPERSET;
        if (r.contains(this))
            return Relation.SUBSET;
        if (this.isDisjoint(r))
            if (this.lo > r.hi)
                return Relation.MOREDISJOINT;
            else
                return Relation.LESSDISJOINT;
        if (this.lessThan(r))
            return Relation.LESSOVERLAP;
        return Relation.MOREOVERLAP;
    }
}
