package com.tc.cmcglobal.entities;

import java.util.List;

/**
 * Created by Quan Lee on 7/11/17.
 */

public class Lession {
    public int system_id;
    public String lesson_cd;
    public String lesson_nm;
    public String date;
    public String start_time;
    public String end_time;
    public int class_id;
    public String class_cd;
    public String class_nm;
    public String teacher_id;
    public String teacher_cd;
    public String teacher_name;
    public List<Student> students;
    public List<String> labels;
}
