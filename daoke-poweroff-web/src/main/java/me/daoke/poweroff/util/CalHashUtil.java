package me.daoke.poweroff.util;

import java.util.zip.CRC32;

/**
 * User: chenlong
 * Date: 2015/5/29
 * Time: 16:31
 */
public class CalHashUtil {

    /**
     * 加法hash
     *
     * @param key   字符串
     * @param prime 一个质数
     * @return hash结果
     */
    public static int additiveHash(String key, int prime) {
        int hash, i;
        for (hash = key.length(), i = 0; i < key.length(); i++)
            hash += key.charAt(i);
        return (hash % prime);
    }


    /**
     *
     * @param key
     * @return
     */
    public static int newCompatHashingAlg( String key ) {
        CRC32 checksum = new CRC32();
        checksum.update( key.getBytes() );
        int crc = (int) checksum.getValue();

        return (crc >> 16) & 0x7fff;
    }






}
