package com.meltingice.caman.presets;

import com.meltingice.caman.CamanJ;
import com.meltingice.caman.CamanPreset;

/**
 * Created by MrLoyal on 8/13/2015.
 */
public class Lomo implements CamanPreset {
    @Override
    public void apply(CamanJ caman) {
        int w = caman.getImage().getWidth();
        int h = caman.getImage().getHeight();

        caman.filter(FilterNames.BRIGHTNESS).set(15);
        caman.filter(FilterNames.EXPOSURE).set(15);
        caman.filter(FilterNames.CURVES)
                .set("rgb")
                .set(new Integer[] {0, 0})
                .set(new Integer[] {200, 0})
                .set(new Integer[]{155, 255})
                .set(new Integer[]{255, 255});
        caman.filter(FilterNames.SATURATION).set(-20);
        caman.filter(FilterNames.GAMMA).set(1.8);
        caman.filter(FilterNames.VIGNETTE).set(w).set(h).set(0.5).set(60);
        caman.filter(FilterNames.BRIGHTNESS).set(5);
    }
}
