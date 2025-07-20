package com.util;

import java.math.BigDecimal;
import java.util.BitSet;

public class Stats {
    public long count = 0;
    public long min = Long.MAX_VALUE;
    public long max = Long.MIN_VALUE;
    public long sum = 0;

    public BigDecimal minDouble = BigDecimal.valueOf(Double.MAX_VALUE);
    public BigDecimal maxDouble = BigDecimal.valueOf(Double.MIN_VALUE);
    public BigDecimal sumDouble = BigDecimal.ZERO;

    public int minLength = Integer.MAX_VALUE;
    public int maxLength = 0;

    public void update(long value) {
        count++;
        min = Math.min(min, value);
        max = Math.max(max, value);
        sum += value;
    }

    public void update(double value) {
        count++;
        BigDecimal valueb = BigDecimal.valueOf(value);
        minDouble = minDouble.compareTo(valueb) >= 0 ? valueb : minDouble;
        maxDouble = maxDouble.compareTo(valueb) <= 0 ? valueb :  maxDouble;
        sumDouble = sumDouble.add(valueb);
    }

    public void update(String value) {
        count++;
        int length = value.length();
        minLength = Math.min(minLength, length);
        maxLength = Math.max(maxLength, length);
    }

    public long getCount() { return count; }
    public long getMin() { return min; }
    public long getMax() { return max; }
    public long getSum() { return sum; }
    public BigDecimal getMinDouble() { return minDouble; }
    public BigDecimal getMaxDouble() { return maxDouble; }
    public BigDecimal getSumDouble() { return sumDouble; }
    public int getMinLength() { return minLength; }
    public int getMaxLength() { return maxLength; }
}