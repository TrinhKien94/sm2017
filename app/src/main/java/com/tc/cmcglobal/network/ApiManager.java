    package com.tc.cmcglobal.network;

    /**
     * Created by Quan Lee on 7/9/17.
     */

    public class ApiManager {
        public static String urlProd = "http://student.angularjs.com.vn";
        public static String urlDev= "http://192.168.10.18";
        public static String baseUrl = urlDev;

        public static String getUrlLogin(){
            //String url = "http://student.angularjs.com.vn/?test=11";
            String url = baseUrl+"/api/v1/users/login";
            return url;
        }
        public static String getUrlGetClass(){
            //String url = "http://student.angularjs.com.vn/?test=21";
            String url = baseUrl+"/api/v1/class/getAll";
            return url;
        }

        public static String getUrlGetLesson(){

            //Sring url = "http://student.angularjs.com.vn/?test=31";
            String url = baseUrl+"/api/v1/lesson/getAll";
            return url;
        }

        public static String getUrlGetLessonDetail(int lessonId){
            //String url = "http://student.angularjs.com.vn/?test=32";
            String url = baseUrl+"/api/v1/lesson/getListUsers/" +lessonId;
            return url;
        }

        public  static String getUrlAttendance(){
            //String url = "http://student.angularjs.com.vn/?test=11";
            String url = baseUrl+"/api/v1/lesson/checkin";
            return url;
        }

        public static String getUrlSetting(int mode){
            return mode == 1 ? urlProd: mode == 0 ? urlDev:"";
        }
    }