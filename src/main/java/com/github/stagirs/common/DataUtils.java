/*
 * Copyright 2016 Dmitriy Malakhov.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.stagirs.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class DataUtils {
    public static final byte[] toBytes(int value) {
        return new byte[] {
                (byte)(value >>> 24),
                (byte)(value >>> 16),
                (byte)(value >>> 8),
                (byte)value};
    }
    public static final byte[] toBytes(long value) {
        return new byte[] {
                (byte)(value >>> 56),
                (byte)(value >>> 48),
                (byte)(value >>> 40),
                (byte)(value >>> 32),
                (byte)(value >>> 24),
                (byte)(value >>> 16),
                (byte)(value >>> 8),
                (byte)value};
    }
    
    public static int toInt(byte[] b){    
        int MASK = 0xFF;
        int result = 0;   
            result = b[3] & MASK;
            result = result + ((b[2] & MASK) << 8);
            result = result + ((b[1] & MASK) << 16);
            result = result + ((b[0] & MASK) << 24);            
        return result;
    }
    
    public static long toLong(byte[] b){    
        long MASK = 0xFF;
        long result = 0;   
            result = b[7] & MASK;
            result = result + ((b[6] & MASK) << 8);
            result = result + ((b[5] & MASK) << 16);
            result = result + ((b[4] & MASK) << 24);    
            result = result + ((b[3] & MASK) << 32);  
            result = result + ((b[2] & MASK) << 40);  
            result = result + ((b[1] & MASK) << 48);  
            result = result + ((b[0] & MASK) << 56);  
        return result;
    }
    
    public static int readInt(InputStream is) throws IOException{
        byte[] b = new byte[4];
        read(is, b);
        return toInt(b);
    }
    
    public static long readLong(InputStream is) throws IOException{
        byte[] b = new byte[8];
        read(is, b);
        return toLong(b);
    }
    
    public static byte[] readBytes(InputStream is) throws IOException{
        int size = readInt(is);
        if(size == 0){
            return null;
        }
        boolean neg = size < 0;
        if(neg){
            size = -size;
        }
        
        byte[] b = new byte[size];
        read(is, b);
        return neg ? null : b;
    }
    
    public static void read(InputStream is, byte[] bytes) throws IOException{
        int start = 0;
        do{
            int count = is.read(bytes, start, bytes.length - start);
            start += count;
        }while(start < bytes.length);
    }
    
    public static void writeBytes(OutputStream os, byte[] b) throws IOException{
        os.write(toBytes(b.length));
        if(b.length > 0){
            os.write(b);
        }
        os.flush();
    }
    
    public static String readString(InputStream is) throws IOException{
        byte[] b = readBytes(is);
        if(b == null){
            return null;
        }
        return new String(b, Charset.forName("utf-8"));
    }
    
    public static void emptyRecord(OutputStream os) throws IOException{
        os.write(toBytes(0));
        os.flush();
    }
}
