package br.com.bfgex;

public class Range {

    private Integer start;
    
    private Integer end;
    
    private Integer[] interval;
    
    public Range(Integer start, Integer end) {
        if (end < start) {
            throw new IllegalArgumentException("invalid range");
        }
        this.start = start;
        this.end = end;
        this.defineInterval();
    }
    
    private void defineInterval() {
        interval = new Integer[end - start + 1];
        for (int i = 0; i < interval.length; i++) {
            interval[i] = start + i; 
        }
    }
    
    public Integer getStart() {
        return start;
    }
    
    public Integer getEnd() {
        return end;
    }
    
    public Integer[] getInterval() {
        return interval;
    }

    @Override
    public String toString() {
        return "(" + start + ".." + end + ")";
    }
    
    
}
