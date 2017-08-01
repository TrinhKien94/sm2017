package com.tc.cmcglobal;

import android.content.Context;
import android.support.annotation.NonNull;


import java.util.Date;

/**
 * Data api class
 * Returns mock data
 * @author gondzo
 */
public class API {

//    /**
//     * The context
//     */
//    private Context context;
//
//    /**
//     * Default constructor
//     */
//    public API(Context ctx){
//        context=ctx;
//    }
//
//    /**
//     * Login to the application
//     * @param username
//     * @param password
//     * @return user info if login successful, null otherwise
//     */
//    public UserInfo login(String username, String password){
//        if (username.equals(context.getString(R.string.test_username)) && password.equals(context.getString(R.string.test_password))){
//            UserInfo ui = new UserInfo();
//            ui.setName("Test User");
//            ui.setImageResourceId(R.drawable.profile);
//            return ui;
//        }
//        return null;
//    }
//
//    /**
//     * Get available deals
//     * @return available deals
//     */
//    public Deals getDeals(){
//        Deals deals = new Deals();
//        deals.getProducts().add(getProduct("#123456","CLIO R.S. 200","Renault",false,15000,new int[]{R.drawable.sample1,R.drawable.sample2,R.drawable.sample3,R.drawable.sample4},false,
//                "The Renault Clio is a supermini car, produced by the French automobile manufacturer Renault. It was launched in 1990, and was in its fourth generation in 2012."));
//        deals.getProducts().add(getProduct("#321654","Corvette 2016", "Chevrolet", true, 24500, new int[]{R.drawable.sample2,R.drawable.sample1,R.drawable.sample3,R.drawable.sample4},false,
//                "The Renault Clio is a supermini car, produced by the French automobile manufacturer Renault. It was launched in 1990, and was in its fourth generation in 2012."));
//        deals.getProducts().add(getProduct("#789123","Cerato Kupa", "Kia", false, 21000, new int[]{R.drawable.sample3,R.drawable.sample1,R.drawable.sample2,R.drawable.sample4},true,
//                "The Renault Clio is a supermini car, produced by the French automobile manufacturer Renault. It was launched in 1990, and was in its fourth generation in 2012."));
//        deals.getProducts().add(getProduct("#456789","BMW 3-Series","BMW",true,34000,new int[]{R.drawable.sample4,R.drawable.sample1,R.drawable.sample2,R.drawable.sample3},true,
//                "The Renault Clio is a supermini car, produced by the French automobile manufacturer Renault. It was launched in 1990, and was in its fourth generation in 2012."));
//        return deals;
//    }
//
//
//    /**
//     * Get deals that match a filter
//     * @param filter the filter
//     * @return matching deals
//     */
//    public Deals getDeals(Filter filter){
//        Deals deals = getDeals();
//        Deals d = new Deals();
//        for(Product p:deals.getProducts()){
//            if (filter!=null) {
//                if (filter.getBrand() != null && filter.getBrand().size() > 0) {
//                    if (!filter.getBrand().contains(p.getBrand())) {
//                        continue;
//                    }
//                }
//                if (filter.getPriceMax() != null) {
//                    if (filter.getPriceMax() < p.getPrice()) {
//                        continue;
//                    }
//                }
//                if (filter.getPriceMin() != null) {
//                    if (filter.getPriceMin() > p.getPrice()) {
//                        continue;
//                    }
//                }
//                if (filter.getDate() != null) {
//                    if (filter.getDate().getTime() > p.getDate().getTime()) {
//                        continue;
//                    }
//                }
//                if (filter.getProductState()!=null){
//                    if (filter.getProductState()== ProductState.AVAILABLE && p.isSold()){
//                        continue;
//                    }
//                    if (filter.getProductState()== ProductState.SOLD && !p.isSold()){
//                        continue;
//                    }
//                }
//            }
//            d.getProducts().add(p);
//        }
//        return d;
//    }
//
//    /**
//     * Get available deals
//     * @return available deals
//     * Deal ~ Lesson
//     *
//     */
//    public Lessons getLessons(){
//        Lessons lessons = new Lessons();
//        lessons.getStudents().add(getStudent(1, "Hello W", "Class Englia", 1,"ss"));
//        lessons.getStudents().add(getStudent(12, "Hello W", "Class Englia", 1,"sss"));
//        lessons.getStudents().add(getStudent(13, "Hello W", "Class Englia", 1,"sss"));
//        lessons.getStudents().add(getStudent(14, "Hello W", "Class Englia", 1,"sss"));
//        return lessons;
//    }
//
//
//    /**
//     * Get deals that match a filter
//     * @param filter the filter
//     * @return matching deals
//     */
//    public Lessons getLessons(Filter filter){
//        Lessons lessons = getLessons();
//        Lessons d = new Lessons();
//        for(Student p:lessons.getStudents()){
//            if (filter!=null) {
//
//            }
//            d.getStudents().add(p);
//        }
//        return d;
//    }
//
//
//    /**
//     * Create a product
//     * @return
//     */
//    @NonNull
//    private Product getProduct(String id,String title,String brand, boolean sold, int price, int[] resIds, boolean favourite,String description) {
//        Product p = new Product();
//        p.setId(id);
//        p.setTitle(title);
//        p.setBrand(brand);
//        p.setDate(new Date());
//        p.setSold(sold);
//        p.setPrice(price);
//        p.setImageResourceIds(resIds);
//        p.setFavourite(favourite);
//        p.setDescription(description);
//        return p;
//    }
//
//    /**
//     * Create a product
//     * @return
//     */
//    @NonNull
//    private Student getStudent(int student_id_s,String student_name_s,String student_class_s, int student_status_s, String student_img_src_s) {
//        Student p = new Student();
//        p.setStudentId(student_id_s);
//        p.setStudentName(student_name_s);
//        p.setStudentClass(student_class_s);
//        p.setStudentStatus(student_status_s);
//        p.setStudentImgSrc(student_img_src_s);
//        return p;
//
//    }
}
