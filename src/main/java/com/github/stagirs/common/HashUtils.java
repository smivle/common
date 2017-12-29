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

import java.security.MessageDigest;

public class HashUtils {
    
    
    
    public static long hash(String key){
       
        MessageDigest messageDigest = null;
        byte[] digest;

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(key.getBytes("UTF-8"));
            digest = messageDigest.digest();
        } catch (Exception e) {
            return 0;
        }
        
        long result1 = 0;       
        for(int i = 0; i < 8; i++){
            result1 = (result1 << 8) + digest[i];
        }
        long result2 = 0;       
        for(int i = 8; i < 16; i++){
            result2 = (result2 << 8) + digest[i];
        }
        return result1 ^ result2;
    }
    
    public static long hash(byte[] ... key){
       
        MessageDigest messageDigest = null;
        byte[] digest;

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            for (byte[] bs : key) {
                messageDigest.update(bs);
            }
            digest = messageDigest.digest();
        } catch (Exception e) {
            return 0;
        }
        
        long result1 = 0;       
        for(int i = 0; i < 8; i++){
            result1 = (result1 << 8) + digest[i];
        }
        long result2 = 0;       
        for(int i = 8; i < 16; i++){
            result2 = (result2 << 8) + digest[i];
        }
        return result1 ^ result2;
    }
}
