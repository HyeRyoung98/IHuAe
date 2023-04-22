package com.example.ihuae.Util;

import android.provider.BaseColumns;

public final class DBContract {
    private DBContract() {}
    public static final String INTEGER = " INTEGER";
    public static final String TEXT = " TEXT";
    public static final String BLOB = " BLOB";
    public static final String coma = ",";

    /**
     * TABLE_NAME : tbCalendar 달력 테이블
     * 1. DateID 날짜ID
     * 2. CalendarYear 년
     * 3. CalendarMonth 월
     * 4. CalendarDay 일
     */
    public static class CalendarEntry implements BaseColumns {
        public static final String TABLE_NAME = "tbCalendar";
        public static final String COLUMN_NAME_1 = "DateID";
        public static final String COLUMN_NAME_2 = "CalendarYear";
        public static final String COLUMN_NAME_3 = "CalendarMonth";
        public static final String COLUMN_NAME_4 = "CalendarDay";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME
                        + " ("
                        + _ID + " INTEGER PRIMARY KEY,"
                        + COLUMN_NAME_1 + INTEGER + coma
                        + COLUMN_NAME_2 + INTEGER + coma
                        + COLUMN_NAME_3 + INTEGER + coma
                        + COLUMN_NAME_4 + INTEGER
                        + ")";
    }

    /**
     * TABLE_NAME : tbIcon 감정 아이콘 테이블
     * 1. IconID
     * 2. EmoIC
     * 3. IconGuide
     */
    public static class IconEntry implements BaseColumns {
        public static final String TABLE_NAME = "tbIcon";
        public static final String COLUMN_NAME_1 = "EmoIC";
        public static final String COLUMN_NAME_2 = "IconGuide";


        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME
                        + " ("
                        + _ID + " INTEGER PRIMARY KEY,"
                        + COLUMN_NAME_1 + INTEGER + coma
                        + COLUMN_NAME_2 + TEXT
                        + ")";
    }

    /**
     * TABLE_NAME : tbEmo 오늘의 기분 테이블
     * 1. DateID 날짜ID
     * 2. IconID 오늘의 기분 아이콘 ID
     * 3. EmoTxt 오늘의 기분 텍스트
     */
    public static class EmoEntry implements BaseColumns {
        public static final String TABLE_NAME = "tbEmo";
        public static final String COLUMN_NAME_1 = "DateID";
        public static final String COLUMN_NAME_2 = "IconID";
        public static final String COLUMN_NAME_3 = "EmoTxt";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME
                        + " ("
                        + _ID + " INTEGER PRIMARY KEY,"
                        + COLUMN_NAME_1 + INTEGER + coma
                        + COLUMN_NAME_2 + INTEGER + coma
                        + COLUMN_NAME_3 + TEXT
                        + ")";
    }

    /**
     * TABLE_NAME : tbQnA 문답 테이블
     * 1. DateID 날짜ID
     * 2. Question 질문
     * 3. Answer 답변
     */
    public static class QnAEntry implements BaseColumns {
        public static final String TABLE_NAME = "tbQnA";
        public static final String COLUMN_NAME_1 = "DateID";
        public static final String COLUMN_NAME_2 = "Question";
        public static final String COLUMN_NAME_3 = "Answer";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME
                        + " ("
                        + _ID + " INTEGER PRIMARY KEY,"
                        + COLUMN_NAME_1 + INTEGER + coma
                        + COLUMN_NAME_2 + TEXT + coma
                        + COLUMN_NAME_3 + TEXT
                        + ")";
    }

    /**
     * TABLE_NAME : tbGuideCard 가이드카드 테이블
     * 1. DateID 날짜ID
     * 2. Content 가이카드 내용
     * 3. Image 가이드카드 이미지
     */
    public static class GuideCardEntry implements BaseColumns {
        public static final String TABLE_NAME = "tbGuideCard";
        public static final String COLUMN_NAME_1 = "DateID";
        public static final String COLUMN_NAME_2 = "Content";
        public static final String COLUMN_NAME_3 = "Image";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME
                        + " ("
                        + _ID + " INTEGER PRIMARY KEY,"
                        + COLUMN_NAME_1 + INTEGER + coma
                        + COLUMN_NAME_2 + TEXT + coma
                        + COLUMN_NAME_3 + INTEGER
                        + ")";
    }

    /**
     * TABLE_NAME : tbDairy 일기(오늘의 기록) 테이블
     * 1. DateID 날짜ID
     * 2. Content 일기 내용
     * 3. RegDate 등록일
     * 4. EdtDate 최종 수정일
     */
    public static class DairyEntry implements BaseColumns {
        public static final String TABLE_NAME = "tbDairy";
        public static final String COLUMN_NAME_1 = "DateID";
        public static final String COLUMN_NAME_5 = "Title";
        public static final String COLUMN_NAME_2 = "Content";
        public static final String COLUMN_NAME_3 = "RegDate";
        public static final String COLUMN_NAME_4 = "EdtDate";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME
                        + " ("
                        + _ID + " INTEGER PRIMARY KEY,"
                        + COLUMN_NAME_1 + INTEGER + coma
                        + COLUMN_NAME_5 + TEXT + coma
                        + COLUMN_NAME_2 + TEXT + coma
                        + COLUMN_NAME_3 + BLOB + coma
                        + COLUMN_NAME_4 + BLOB
                        + ")";
    }

    /**
     * TABLE_NAME : tbMessage 메신저(감정 억제기) 테이블
     * 1. DateID 날짜ID
     * 2. Content 메신저 내용
     * 3. RegDate 등록일
     * 4. DueDelDate 삭제 예정일
     */
    public static class MessageEntry implements BaseColumns {
        public static final String TABLE_NAME = "tbMessage";
        public static final String COLUMN_NAME_1 = "DateID";
        public static final String COLUMN_NAME_2 = "Content";
        public static final String COLUMN_NAME_3 = "RegDate";
        public static final String COLUMN_NAME_4 = "DueDelDate";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME
                        + " ("
                        + _ID + " INTEGER PRIMARY KEY,"
                        + COLUMN_NAME_1 + INTEGER + coma
                        + COLUMN_NAME_2 + TEXT + coma
                        + COLUMN_NAME_3 + BLOB + coma
                        + COLUMN_NAME_4 + BLOB
                        + ")";
    }


}