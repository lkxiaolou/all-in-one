package org.newboo.ringbuffer;

public final class RingBuffer<T> {

    private int bufferSize;
    private AtomicRangeInteger index;
    private final T[] buffer;

    @SuppressWarnings("unchecked")
    public RingBuffer(int bufferSize, AtomicRangeInteger index) {
        this.bufferSize = bufferSize;
        this.index = index;
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
