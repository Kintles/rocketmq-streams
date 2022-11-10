package org.apache.rocketmq.streams.core.util;
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.rocketmq.streams.core.metadata.StreamConfig;

import java.util.concurrent.atomic.AtomicInteger;

public class OperatorNameMaker {
    public static final String SOURCE_PREFIX = "ROCKETMQ-SOURCE";
    public static final String SHUFFLE_SOURCE_PREFIX = "ROCKETMQ-SHUFFLE-SOURCE";
    public static final String MAP_PREFIX = "ROCKETMQ-MAP";
    public static final String FLAT_MAP_PREFIX = "ROCKETMQ-FLAT-MAP";
    public static final String FILTER_PREFIX = "ROCKETMQ-FILTER";
    public static final String GROUPBY_PREFIX = "ROCKETMQ-GROUPBY";
    public static final String COUNT_PREFIX = "ROCKETMQ-COUNT";
    public static final String FOR_EACH_PREFIX = "ROCKETMQ-FOR-EACH";
    public static final String SINK_PREFIX = "ROCKETMQ-SINK";
    public static final String PRINT_PREFIX = "ROCKETMQ-PRINT";
    public static final String SHUFFLE_SINK_PREFIX = "ROCKETMQ-SHUFFLE-SINK";
    public static final String WINDOW_COUNT_PREFIX = "ROCKETMQ-WINDOW-COUNT";
    public static final String WINDOW_AGGREGATE_PREFIX = "ROCKETMQ-WINDOW-AGGREGATE";
    public static final String NAKED_NODE_PREFIX = "NAKED-NODE";

    public static final String pattern = "%s-%s-%s";

    private static final ThreadLocal<AtomicInteger> index = ThreadLocal.withInitial(() -> new AtomicInteger(0));


    private static int incrementAndGet() {
        return index.get().incrementAndGet();
    }


    public static String makeName(String prefix) {
        String jobId = StreamConfig.getJobId();
        String number = String.format("%010d", incrementAndGet());

        return String.format(pattern, jobId, prefix, number);
    }

    public static String makeCommonName(String prefix, Long index) {
        String jobId = StreamConfig.getJobId();
        String number = String.format("%010d", index);

        return String.format(pattern, jobId, prefix, number);
    }
}
