package org.newboo.queue;

public final class MyRingBuffer<T> {

    private int bufferSize;
    private AtomicRangeInteger index;
    private final T[] buffer;

    @SuppressWarnings("unchecked")
    public MyRingBuffer(int bufferSize) {
        this.bufferSize = bufferSize;
        this.index = new AtomicRangeInteger(0, bufferSize);
        this.buffer = (T[]) new Object[bufferSize];
    }

    public final void offer(final T data) {
        buffer[index.incrementAndGet()] = data;
    }

    public final T poll(int index) {
        T tmp = buffer[index];
        buffer[index] = null;
        return tmp;
    }

    public int getBufferSize() {
        return bufferSize;
    }
}
