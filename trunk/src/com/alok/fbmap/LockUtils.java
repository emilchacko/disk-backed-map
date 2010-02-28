/*
 * Copyright 2009 Alok Singh
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.alok.fbmap;

import java.util.HashMap;
import java.util.Map;

public class LockUtils {
    private Map<Integer, Integer> locks;
    private Map<Integer, Long> accessTimes;
    private int HIGH_WATER_MARK = 1000 * 10 * 10;
    private int LOW_WATER_MARK = 1000* 10 * 5;

    public static LockUtils instance = new LockUtils();

    private LockUtils(){
        locks = new HashMap<Integer, Integer>();
        accessTimes = new HashMap<Integer, Long>();
    }

    public Object getLock(Object key){
        int hash = hash(key);
        synchronized (locks){
            Integer lock = null;
            if(locks.containsKey(hash)){
                lock = locks.get(hash);
            }else{
                lock = new Integer(hash);
                locks.put(hash, lock);
            }
            updateAccessTime(hash);
            return lock;
        }
    }

    private void updateAccessTime(int hash) {
        accessTimes.put(hash, System.currentTimeMillis());
    }

    private int hash(Object key) {
        int h = key.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }


}
