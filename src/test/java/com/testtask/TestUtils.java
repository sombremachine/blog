package com.testtask;

import com.testtask.model.BlogEntity;

import java.util.Arrays;
import java.util.List;

public class TestUtils {

    public static final BlogEntity TOPIC1 = new BlogEntity(1, "topic_1", "thdrthdbth");
    public static final BlogEntity TOPIC2 = new BlogEntity(2, "topic_2", "dfghndhd");
    public static final BlogEntity TOPIC3 = new BlogEntity(3, "topic_3", "imi ty tt htj ");

    public static final int TOPIC1_ID = 1;
    public static final int TOPIC2_ID = 2;
    public static final int TOPIC3_ID = 3;

    public static final List<BlogEntity> TOPICS = Arrays.asList(TOPIC1, TOPIC2, TOPIC3);

}
