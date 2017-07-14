package com.example.lurzeood.mygrade;

/**
 * Created by Lurzeood on 2017/7/11 0011.
 */

public class GradeDbSchema {

    public static final class GradeTable{
        public static final String NAME = "gradetable";

        public static final class Cols{
            public static final String ID = "id";
            public static final String STUDENT_NAME = "name";
            public static final String SUBJECT = "subject";
            public static final String SCORE = "score";
        }
    }

}
