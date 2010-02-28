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

package com.alok.fbmap.io;

import com.alok.fbmap.Record;

public interface DiskIO extends Iterable<Record> {

    Record lookup(long location);

    long write(Record r);

    void update(Record r);

    long size();

    void close();

    void vacuum(RecordFilter filter) throws Exception;

    void clear();

    public interface RecordFilter{
        public boolean accept(Record r);

        void update(Record r, long newLocation);
    }
}
