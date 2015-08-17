package com.meltingice.caman.filters;

import com.company.Main;
import com.meltingice.caman.CamanFilter;
import com.meltingice.caman.exceptions.InvalidArgumentsException;
import com.meltingice.caman.exceptions.InvalidPluginException;
import com.meltingice.caman.util.Bezier;
import com.meltingice.caman.util.CamanUtil;
import com.meltingice.caman.util.Logger;
import sun.rmi.runtime.Log;

/**
 * Created by MrLoyal on 8/14/2015.
 *
 */
public class Vignette extends CamanFilter {
    private int imageWidth;
    private int imageHeight;
    private double size;
    private double strength;

    private int centerX, centerY;
    private double start, end;
    private Bezier bezier;

    @Override
    public void precomputeParams() throws InvalidArgumentsException {
        imageWidth = getParamInt(0);
        imageHeight = getParamInt(1);

        try{
            String sizeStr = (String) params.get(2);
            size = Double.parseDouble(sizeStr.substring(0, sizeStr.length()-1))/100;
        } catch (ClassCastException e){
            size = getParamDouble(2);
        }


        strength = (float)getParamInt(3) / 100.0f;

        centerX = imageWidth /2;
        centerY = imageHeight /2;


        if (imageHeight > imageWidth){
            size = size * imageWidth;
        } else {
            size = size * imageHeight;
        }

        start = Math.sqrt(Math.pow(centerX, 2) + Math.pow(centerY, 2));
        System.out.println("Size param = " +size);
        end = start - size;

        Logger.d("Start = " + start);
        Logger.d("Size = " + size);
        Logger.d("end = " + end);


        bezier = new Bezier(new int[]{0, 1}, new int[]{30, 30}, new int[]{70, 60}, new int[]{100, 80});
    }

    public int[] processVignette(int x, int y, int[] rgb) {
        double dist = calculateDistance(x, y, centerX, centerY);

        if (dist > end) {
            String debug = (x + " " + y + ":  (" + centerX + ", " + centerY + ") " + rgb[0] + " " + rgb[1] + " " + rgb[2] + "  --> ");
            double div = Math.max(1, (bezier.query((int)Math.round((((float)dist - (float)end) / size) * 100.0f)) / 10.0f) * strength);
            rgb[0] = (int) (Math.pow(((float)rgb[0]) / 255.0f, div) * 255);
            rgb[1] = (int) (Math.pow(((float)rgb[1]) / 255.0f, div) * 255);
            rgb[2] = (int) (Math.pow(((float)rgb[2]) / 255.0f, div) * 255);
            Logger.d(debug+ rgb[0] + " " + rgb[1] + " " + rgb[2]);


        }
        return CamanUtil.clampRGB(rgb);
    }

    private double calculateDistance(int x1, int y1, int x2, int y2){
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
