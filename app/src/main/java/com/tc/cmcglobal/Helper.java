package com.sm.cmcglobal;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.view.inputmethod.InputMethodManager;

import java.io.ByteArrayOutputStream;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Helper class that contains methods used by more classes
 * @author gondzo
 */
public class Helper {

    /**
     * Hide keyboard
     * @param ctx
     */
    public static void hideSoftKeyBoard(Activity ctx) {
        try {
            InputMethodManager imm = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);

            if (imm.isAcceptingText()) { // verify if the soft keyboard is open
                imm.hideSoftInputFromWindow(ctx.getCurrentFocus().getWindowToken(), 0);
            }
        }catch (Exception ex){
            //ignore
        }
    }

    /**
     * Convert Bitmap to byte array
     * @param imageBitmap
     * @return
     */
    public static byte[] getBytes(Bitmap imageBitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    /**
     * Set grayscale filter to a Drawable
     * @param drawable
     * @return
     */
    public static Drawable convertToGrayscale(Drawable drawable)
    {
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);

        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);

        drawable.setColorFilter(filter);

        return drawable;
    }

    /**
     * Format a currency with US locale
     * @param c
     * @return
     */
    public static String formatCurrency(int c){
        NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
        String s = n.format(c);
        s=s.substring(0,s.length()-3);
        return s;
    }
}
