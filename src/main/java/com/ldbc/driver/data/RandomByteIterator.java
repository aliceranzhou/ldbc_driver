package com.ldbc.driver.data;

import org.apache.commons.math3.random.RandomDataGenerator;

// generates a random sequence of bytes.
public class RandomByteIterator extends ByteIterator
{
    private int length;
    private int offset;
    private int bufferOffset;
    private byte[] buffer;

    private final RandomDataGenerator rdg;

    public RandomByteIterator( int length, RandomDataGenerator rdg )
    {
        this.length = length;
        this.buffer = new byte[6];
        this.bufferOffset = buffer.length;
        this.rdg = rdg;
        fillBytes();
        this.offset = 0;
    }

    @Override
    public boolean hasNext()
    {
        return ( offset + bufferOffset ) < length;
    }

    private void fillBytes()
    {
        if ( bufferOffset == buffer.length )
        {
            fillBytesImpl( buffer, 0 );
            bufferOffset = 0;
            offset += buffer.length;
        }
    }

    private void fillBytesImpl( byte[] buffer, int base )
    {
        int bytes = rdg.nextInt( 0, 2 ^ 32 );
        try
        {
            buffer[base + 0] = (byte) ( ( ( bytes ) & 31 ) + ' ' );
            buffer[base + 1] = (byte) ( ( ( bytes >> 5 ) & 31 ) + ' ' );
            buffer[base + 2] = (byte) ( ( ( bytes >> 10 ) & 31 ) + ' ' );
            buffer[base + 3] = (byte) ( ( ( bytes >> 15 ) & 31 ) + ' ' );
            buffer[base + 4] = (byte) ( ( ( bytes >> 20 ) & 31 ) + ' ' );
            buffer[base + 5] = (byte) ( ( ( bytes >> 25 ) & 31 ) + ' ' );
        }
        catch ( ArrayIndexOutOfBoundsException e )
        { /* ignore it */
            // TODO wtf is going on here?!
        }
    }

    public byte nextByte()
    {
        fillBytes();
        bufferOffset++;
        return buffer[bufferOffset - 1];
    }

    @Override
    public int nextBuffer( byte[] buffer, int bufferOffset )
    {
        int ret;
        if ( length - offset < buffer.length - bufferOffset )
        {
            ret = (int) ( length - offset );
        }
        else
        {
            ret = buffer.length - bufferOffset;
        }
        int i;
        for ( i = 0; i < ret; i += 6 )
        {
            fillBytesImpl( buffer, i + bufferOffset );
        }
        offset += ret;
        return ret + bufferOffset;
    }

    @Override
    public long bytesRemaining()
    {
        return length - offset - bufferOffset;
    }
}
