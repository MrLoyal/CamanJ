package com.meltingice.caman.presets;

import com.meltingice.caman.CamanJ;
import com.meltingice.caman.CamanPreset;

/**
 * Created by MrLoyal on 8/17/2015.
 */
public class SunRise implements CamanPreset {
    @Override
    public void apply(CamanJ caman) {
        int w = caman.getImage().getWidth();
        int h = caman.getImage().getHeight();

        caman.filter(FilterNames.EXPOSURE).set(3.5);
        caman.filter(FilterNames.SATURATION).set(-5);
        caman.filter(FilterNames.VIBRANCE).set(50);
        caman.filter(FilterNames.SEPIA).set(50);
        caman.filter(FilterNames.COLORIZE).set("#e87b22").set(10);
        caman.filter(FilterNames.CHANNELS).set(8).set(1).set(8);
        caman.filter(FilterNames.CONTRAST).set(5);
        caman.filter(FilterNames.GAMMA).set(1.2);
        caman.filter(FilterNames.VIGNETTE).set(w).set(h).set("55%").set(25);
    }
}
