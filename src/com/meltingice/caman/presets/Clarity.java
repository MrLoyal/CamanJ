package com.meltingice.caman.presets;

import com.meltingice.caman.CamanJ;
import com.meltingice.caman.CamanPreset;

/**
 * Created by MrLoyal on 8/14/2015.
 */
public class Clarity implements CamanPreset {
    @Override
    public void apply(CamanJ caman) {
        int w = caman.getImage().getWidth();
        int h = caman.getImage().getHeight();

        caman.filter("vibrance").set(20);
        caman.filter("curves")
                .set("rgb")
                .set(new Integer[]{5, 0})
                .set(new Integer[]{130, 150})
                .set(new Integer[]{190, 220})
                .set(new Integer[]{250, 255});
        caman.filter("sharpen").set(15);
        caman.filter("vignette").set(w).set(h).set(0.45).set(20);
        //caman.filter(FilterNames.GREYSCALE);
        //caman.filter(FilterNames.CONTRAST).set(4);
    }
}
