/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.util;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

public class TextTable<T> {

    private List<T> values;
    private ColumnValueProvider[] columnValueProviders;

    public TextTable(Collection<T> values, ColumnValueProvider<T>... columnValueProviders) {
        this.columnValueProviders = columnValueProviders;
        this.values = new ArrayList<T>(values);
    }

    public TextTable(T[] values, ColumnValueProvider<T>... columnValueProviders) {
        this(Arrays.asList(values), columnValueProviders);
    }

    public void print(OutputStream outputStream) {
        PrintWriter writer = new PrintWriter(outputStream);
        int[] maxLengths = new int[columnValueProviders.length];
        List<List<String>> tableData = new ArrayList<List<String>>();
        List<String> titles = new ArrayList<String>();
        for (int i = 0, columnValuesLength = columnValueProviders.length; i < columnValuesLength; i++) {
            ColumnValueProvider columnValueProvider = columnValueProviders[i];
            maxLengths[i] = columnValueProvider.columnName.length();
            titles.add(columnValueProvider.columnName);
        }
        for (T value : values) {
            List<String> rowValues = new ArrayList<String>();
            for (int i = 0, columnValueProvidersLength = columnValueProviders.length; i < columnValueProvidersLength; i++) {
                ColumnValueProvider columnValueProvider = columnValueProviders[i];
                String cellValue = String.valueOf(columnValueProvider.valueOf(value));
                if (cellValue.length() > maxLengths[i]) {
                    maxLengths[i] = cellValue.length();
                }
                rowValues.add(cellValue);
            }
            tableData.add(rowValues);
        }
        printHeader(writer, maxLengths);
        print(writer, titles, maxLengths);
        printHeaderUnderline(writer, maxLengths);
        for (List<String> row : tableData) {
            writer.println();
            print(writer, row, maxLengths);
        }
        printTableEnd(writer, maxLengths);
        writer.println();
        writer.flush();
    }

    private void printTableEnd(PrintWriter writer, int[] maxLengths) {
        writer.println();
        writer.print(" \u2514\u2500");
        for (int i = 0; i < maxLengths.length; i++) {
            for(int c=0; c<maxLengths[i]; c++) {
                writer.print('\u2500');
            }
            if(i<(maxLengths.length-1)) {
                writer.print("\u2500\u2534\u2500");
            } else {
                writer.print("\u2500\u2518");
            }
        }
        writer.println();
    }

    private void printHeaderUnderline(PrintWriter writer, int[] maxLengths) {
        writer.println();
        writer.print(" \u251c\u2500");
        for (int i = 0; i < maxLengths.length; i++) {
            for(int c=0; c<maxLengths[i]; c++) {
                writer.print('\u2500');
            }
            if(i<(maxLengths.length-1)) {
                writer.print("\u2500\u253C\u2500");
            } else {
                writer.print("\u2500\u2524");
            }
        }
    }

    private void printHeader(PrintWriter writer, int[] maxLengths) {
        writer.print(" \u250c\u2500");
        for (int i = 0; i < maxLengths.length; i++) {
            for(int c=0; c<maxLengths[i]; c++) {
                writer.print('\u2500');
            }
            if(i<(maxLengths.length-1)) {
                writer.print("\u2500\u252C\u2500");
            } else {
                writer.print("\u2500\u2510");
            }
        }
        writer.println();
    }

    private void print(PrintWriter writer, List<String> values, int[] maxLengths) {
        writer.print(" \u2502 ");
        for (int i = 0, valuesSize = values.size(); i < valuesSize; i++) {
            String value = values.get(i);
            writer.print(value);
            for(int c=0; c<(maxLengths[i] - value.length()); c++) {
                writer.print(" ");
            }
            writer.print(" \u2502 ");
        }
    }

    public String toString() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        print(outputStream);
        return new String(outputStream.toByteArray());
    }

    public static abstract class ColumnValueProvider<T> {

        private final String columnName;

        public ColumnValueProvider(String columnName) {
            this.columnName = columnName;
        }

        public abstract String valueOf(T object);
    }

}
