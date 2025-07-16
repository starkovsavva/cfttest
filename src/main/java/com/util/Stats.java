package com.util;

public class Stats {
    public long count = 0;
    public long min = Long.MAX_VALUE;
    public long max = Long.MIN_VALUE;
    public long sum = 0;

    public double minDouble = Double.MAX_VALUE;
    public double maxDouble = Double.MIN_VALUE;
    public double sumDouble = 0.0;

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
        minDouble = Math.min(minDouble, value);
        maxDouble = Math.max(maxDouble, value);
        sumDouble += value;
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
    public double getMinDouble() { return minDouble; }
    public double getMaxDouble() { return maxDouble; }
    public double getSumDouble() { return sumDouble; }
    public int getMinLength() { return minLength; }
    public int getMaxLength() { return maxLength; }
}