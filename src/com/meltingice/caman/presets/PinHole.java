package com.meltingice.caman.presets;

import com.meltingice.caman.CamanJ;
import com.meltingice.caman.CamanPreset;

/**
 * Created by MrLoyal on 8/17/2015.
 */
public class PinHole implements CamanPreset {
    @Override
    public void apply(CamanJ caman) {
        int w = caman.getImage().getWidth();
        int h = caman.getImage().getHeight();

        caman.filter(FilterNames.GREYSCALE);
        caman.filter(FilterNames.SEPIA).set(10);
        caman.filter(FilterNames.EXPOSURE).set(10);
        caman.filter(FilterNames.CONTRAST).set(15);
        caman.filter(FilterNames.VIGNETTE).set(w).set(h).set("60%").set(35);
    }
}
