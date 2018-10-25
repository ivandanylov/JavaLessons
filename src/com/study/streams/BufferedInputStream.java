package com.study.streams;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

public class BufferedInputStream extends InputStream {
    private InputStream inputStream;
    private int bufferSize = 1024;
    private byte[] buffer = new byte[bufferSize];
    private int index;
    private int count;

    public BufferedInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public BufferedInputStream(InputStream inputStream, int bufferSize) {
        this.inputStream = inputStream;
        this.bufferSize = bufferSize;
        buffer = new byte[bufferSize];
    }

    @Override
    public int read() throws IOException {
        if (fillIfNeeded() == -1) {
            return -1;
        }

        int value = buffer[index];
        index++;

        return value;
    }

    private int fillIfNeeded() throws IOException {
        if (index == count) {
            count = inputStream.read(buffer);
            index = 0;
        }

        return count;
    }

    @Override
    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    @Override
    public int read(byte[] array, int offset, int length) throws IOException {
        if (fillIfNeeded() == -1) {
            return -1;
        }

        int elementsInBuffer = count - index;

        if (length < elementsInBuffer) {
            System.arraycopy(buffer, index, array, offset, length);
            index += length;

            return length;
        } else {
            System.arraycopy(buffer, index, array, offset, elementsInBuffer);
            index += elementsInBuffer;

            return elementsInBuffer;
        }
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
    }
}