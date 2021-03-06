/**
 * Copyright (c) 2007-2009, JAGaToo Project Group all rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * 
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * 
 * Neither the name of the 'Xith3D Project Group' nor the names of its
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) A
 * RISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE
 */
package org.jagatoo.util.streams;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.jagatoo.util.arrays.ArrayUtils;

/**
 * Contains static utility methods for Stream.
 * 
 * @author Marvin Froehlich (aka Qudus)
 */
public class StreamUtils
{
    /**
     * Skips and discards the given number of bytes from the given stream.
     * 
     * @param in
     * @param toSkip
     * 
     * @throws IOException
     */
    public static final void skipBytes( InputStream in, long toSkip ) throws IOException
    {
        while ( toSkip > 0L )
        {
            long skipped = in.skip( toSkip );
            
            if ( skipped > 0 )
                toSkip -= skipped;
            else if ( skipped < 0 )
                toSkip = 0;
        }
    }
    
    /**
     * Skips and discards the given number of bytes from the given stream.
     * 
     * @param in
     * @param toSkip
     * 
     * @throws IOException
     */
    public static final void skipBytes( BufferedInputStream in, long toSkip ) throws IOException
    {
        while ( toSkip > 0L )
        {
            long skipped = in.skip( toSkip );
            
            if ( skipped > 0 )
                toSkip -= skipped;
            else if ( skipped < 0 )
                toSkip = 0;
        }
    }
    
    /**
     * Reads one byte from the InputStream.
     * 
     * @param in
     * 
     * @return the read byte.
     * 
     * @throws IOException
     */
    public static final byte readByte( InputStream in ) throws IOException
    {
        return ( (byte)in.read() );
    }
    
    /**
     * Reads one byte from the InputStream.
     * 
     * @param in
     * 
     * @return the read byte.
     * 
     * @throws IOException
     */
    public static final byte readByte( BufferedInputStream in ) throws IOException
    {
        return ( (byte)in.read() );
    }
    
    /**
     * Reads one unsigned byte from the InputStream stored in a short-value
     * to preserve the sign.
     * 
     * @param in
     * 
     * @return the read unsigned byte as a short.
     * 
     * @throws IOException
     */
    public static final short readUnsignedByte( InputStream in ) throws IOException
    {
        int b = in.read();
        
        return ( (short)( b & 0xFF ) );
    }
    
    /**
     * Reads one unsigned byte from the InputStream stored in a short-value
     * to preserve the sign.
     * 
     * @param in
     * 
     * @return the read unsigned byte as a short.
     * 
     * @throws IOException
     */
    public static final short readUnsignedByte( BufferedInputStream in ) throws IOException
    {
        int b = in.read();
        
        return ( (short)( b & 0xFF ) );
    }
    
    /**
     * Reads two bytes from the InputStream stored in a short-value.
     * 
     * @param in
     * 
     * @return the read short.
     * 
     * @throws IOException
     */
    public static final short readShort( InputStream in ) throws IOException
    {
        int s1 = ( in.read() & 0xFF ) << 8;
        int s2 = ( in.read() & 0xFF );
        
        return ( (short)( s1 | s2 ) );
    }
    
    /**
     * Reads two bytes from the InputStream stored in a short-value.
     * 
     * @param in
     * 
     * @return the read short.
     * 
     * @throws IOException
     */
    public static final short readShort( BufferedInputStream in ) throws IOException
    {
        int s1 = ( in.read() & 0xFF ) << 8;
        int s2 = ( in.read() & 0xFF );
        
        return ( (short)( s1 | s2 ) );
    }
    
    /**
     * Reads two bytes from the InputStream, convertes them to a short
     * and stores them to an int to preserve the sign.
     * 
     * @param in
     * 
     * @return the read unsigned short (as an int).
     * 
     * @throws IOException
     */
    public static final int readUnsignedShort( InputStream in ) throws IOException
    {
        int high = (int)in.read();
        int low = (int)in.read();
        
        return ( ( ( high & 0xFF ) << 8 ) | ( low & 0xFF ) );
    }
    
    /**
     * Reads two bytes from the InputStream, convertes them to a short
     * and stores them to an int to preserve the sign.
     * 
     * @param in
     * 
     * @return the read unsigned short (as an int).
     * 
     * @throws IOException
     */
    public static final int readUnsignedShort( BufferedInputStream in ) throws IOException
    {
        int high = (int)in.read();
        int low = (int)in.read();
        
        return ( ( ( high & 0xFF ) << 8 ) | ( low & 0xFF ) );
    }
    
    /**
     * Reads two bytes from the InputStream stored in a short-value.
     * 
     * @param in
     * 
     * @return the read short.
     * 
     * @throws IOException
     */
    public static final short readSwappedShort( InputStream in ) throws IOException
    {
        int s2 = ( in.read() & 0xFF );
        int s1 = ( in.read() & 0xFF ) << 8;
        
        return ( (short)( s1 | s2 ) );
    }
    
    /**
     * Reads two bytes from the InputStream stored in a short-value.
     * 
     * @param in
     * 
     * @return the read short.
     * 
     * @throws IOException
     */
    public static final short readSwappedShort( BufferedInputStream in ) throws IOException
    {
        int s2 = ( in.read() & 0xFF );
        int s1 = ( in.read() & 0xFF ) << 8;
        
        return ( (short)( s1 | s2 ) );
    }
    
    /**
     * Reads two bytes from the InputStream, convertes them to a short
     * and stores them to an int to preserve the sign.
     * 
     * @param in
     * 
     * @return the read unsigned short (as an int).
     * 
     * @throws IOException
     */
    public static final int readSwappedUnsignedShort( InputStream in ) throws IOException
    {
        int low = (int)in.read();
        int high = (int)in.read();
        
        return ( ( ( high & 0xFF ) << 8 ) | ( low & 0xFF ) );
    }
    
    /**
     * Reads two bytes from the InputStream, convertes them to a short
     * and stores them to an int to preserve the sign.
     * 
     * @param in
     * 
     * @return the read unsigned short (as an int).
     * 
     * @throws IOException
     */
    public static final int readSwappedUnsignedShort( BufferedInputStream in ) throws IOException
    {
        int low = (int)in.read();
        int high = (int)in.read();
        
        return ( ( ( high & 0xFF ) << 8 ) | ( low & 0xFF ) );
    }
    
    /**
     * Reads one (signed) int from the stream.
     * 
     * @param in
     * 
     * @return the read int.
     * 
     * @throws IOException
     */
    public static final int readInt( InputStream in ) throws IOException
    {
        int i4 = ( in.read() & 0xFF ) << 24;
        int i3 = ( in.read() & 0xFF ) << 16;
        int i2 = ( in.read() & 0xFF ) << 8;
        int i1 = ( in.read() & 0xFF );
        
        return ( i4 | i3 | i2 | i1 );
    }
    
    /**
     * Reads one (signed) int from the stream.
     * 
     * @param in
     * 
     * @return the read int.
     * 
     * @throws IOException
     */
    public static final int readInt( BufferedInputStream in ) throws IOException
    {
        int i4 = ( in.read() & 0xFF ) << 24;
        int i3 = ( in.read() & 0xFF ) << 16;
        int i2 = ( in.read() & 0xFF ) << 8;
        int i1 = ( in.read() & 0xFF );
        
        return ( i4 | i3 | i2 | i1 );
    }
    
    /**
     * Reads one (signed) int from the stream.
     * 
     * @param in
     * 
     * @return the read int.
     * 
     * @throws IOException
     */
    public static final int readSwappedInt( InputStream in ) throws IOException
    {
        int i4 = ( in.read() & 0xFF );
        int i3 = ( in.read() & 0xFF ) << 8;
        int i2 = ( in.read() & 0xFF ) << 16;
        int i1 = ( in.read() & 0xFF ) << 24;
        
        return ( i4 | i3 | i2 | i1 );
    }
    
    /**
     * Reads one (signed) int from the stream.
     * 
     * @param in
     * 
     * @return the read int.
     * 
     * @throws IOException
     */
    public static final int readSwappedInt( BufferedInputStream in ) throws IOException
    {
        int i1 = ( in.read() & 0xFF );
        int i2 = ( in.read() & 0xFF ) << 8;
        int i3 = ( in.read() & 0xFF ) << 16;
        int i4 = ( in.read() & 0xFF ) << 24;
        
        return ( i4 | i3 | i2 | i1 );
    }
    
    /**
     * Reads bytesToRead bytes from the stream.
     * 
     * @param in
     * @param bytesToRead
     * @param buffer
     * @param bufferOffset
     * 
     * @throws IOException
     */
    public static final void readBytes( InputStream in, int bytesToRead, byte[] buffer, int bufferOffset ) throws IOException
    {
        int bytesRead = 0;
        int read;
        do
        {
            read = in.read( buffer, bufferOffset + bytesRead, bytesToRead );
            bytesRead += read;
            bytesToRead -= read;
        }
        while ( ( bytesToRead > 0 ) && ( read > 0 ) );
    }
    
    /**
     * Reads bytesToRead bytes from the stream.
     * 
     * @param in
     * @param bytesToRead
     * @param buffer
     * @param bufferOffset
     * 
     * @throws IOException
     */
    public static final void readBytes( BufferedInputStream in, int bytesToRead, byte[] buffer, int bufferOffset ) throws IOException
    {
        int bytesRead = 0;
        int read;
        do
        {
            read = in.read( buffer, bufferOffset + bytesRead, bytesToRead );
            bytesRead += read;
            bytesToRead -= read;
        }
        while ( ( bytesToRead > 0 ) && ( read > 0 ) );
    }
    
    /**
     * Reads a String from the InputStream.
     * The string is expected to be 0-terminated.
     * 
     * @param in
     * @param maxLength
     * @param alwaysReadMaxLength
     * 
     * @return the read String.
     * 
     * @throws IOException
     */
    public static final String readCString( InputStream in, int maxLength, boolean alwaysReadMaxLength ) throws IOException
    {
        byte[] bytes = new byte[ maxLength ];
        
        if ( alwaysReadMaxLength )
        {
            int toRead = maxLength;
            while ( toRead > 0 )
            {
                int read = in.read( bytes, maxLength - toRead, toRead );
                toRead -= read;
            }
            
            int nullIndex = ArrayUtils.indexOf( bytes, (byte)0 );
            if ( nullIndex == -1 )
                return ( new String( bytes ) );
            
            return ( new String( bytes, 0, nullIndex ) );
        }
        
        for ( int i = 0; i < maxLength; i++ )
        {
            int ib = in.read();
            if ( ib == -1 )
                return ( null );
            byte b = (byte)ib;
            if ( b == (byte)0 )
                return ( new String( bytes, 0, i ) );
            
            bytes[i] = b;
        }
        
        return ( new String( bytes ) );
    }
    
    /**
     * Builds a byte-array from the given InputStream.<br>
     * The byte-array is created with a size of <code>initialSize</code> and is
     * enlarged on demand.<br>
     * The InputStream is NOT closed at the end.
     * 
     * @param in the InputStream to get data from
     * @param initialSize the initial size of the output byte-array
     * @return the filled and correctly sized byte-array
     * 
     * @throws IOException
     */
    public static final byte[] buildByteArray( InputStream in, int initialSize ) throws IOException
    {
        int a = in.available();
        
        byte[] buffer = new byte[ Math.min( a, initialSize ) ];
        
        int offset = 0;
        int n;
        
        while ( ( n = in.read( buffer, offset, a ) ) > 0 )
        {
            if ( n <= 0 )
                break;
            
            offset += n;
            
            a = in.available();
            
            if ( offset + a > buffer.length )
            {
                //byte[] newBuffer = new byte[ (buffer.length * 3) / 2 + 1 ];
                byte[] newBuffer = new byte[ Math.max( buffer.length << 1, offset + a ) ];
                System.arraycopy( buffer, 0, newBuffer, 0, offset );
                buffer = newBuffer;
            }
        }
        
        if ( buffer.length == offset )
            return ( buffer );
        
        byte[] copy = new byte[ offset ];
        System.arraycopy( buffer, 0, copy, 0, offset );
        
        return ( copy );
    }
    
    /**
     * This calls {@link #buildByteArray(InputStream, int)} with an initialSize
     * of in.available().
     * 
     * @param in the InputStream to get data from
     * @return the filled and correctly sized byte-array
     * 
     * @throws IOException
     */
    public static final byte[] buildByteArray( InputStream in ) throws IOException
    {
        return ( buildByteArray( in, in.available() ) );
    }
}
