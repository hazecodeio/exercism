public class CircularBuffer<T> {

    int size;
    int capacity;
    int front;
    T[] data;

    public CircularBuffer(int capacity) {
        this.capacity = capacity;
        data = (T[]) new Object[capacity];
    }


    public void write(T elem) throws BufferIOException {
        if (size == data.length)
            throw new BufferIOException("Tried to write to full buffer");

        int available = (front + size) % data.length;
        data[available] = elem;
        size++;
    }

    public T read() throws BufferIOException {
        if (size == 0)
            throw new BufferIOException("Tried to read from empty buffer");
        T elem = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        return elem;
    }

    public void clear() {
        size = 0;
        front = 0;
        data = (T[]) new Object[capacity];
    }

    public void overwrite(T elem) throws BufferIOException {
        if (size == data.length)
            read();
        write(elem);
    }
}