package com.meltingice.caman.presets;

import com.meltingice.caman.CamanJ;
import com.meltingice.caman.CamanPreset;

/**
 * Created by MrLoyal on 8/17/2015.
 */
public class OldBoot implements CamanPreset {
    @Override
    public void apply(CamanJ caman) {
        int w = caman.getImage().getWidth();
        int h = caman.getImage().getHeight();

        caman.filter(FilterNames.SATURATION).set(-20);
        caman.filter(FilterNames.VIBRANCE).set(-50);
        caman.filter(FilterNames.GAMMA).set(1.1);
        caman.filter(FilterNames.SEPIA).set(30);
        caman.filter(FilterNames.CHANNELS).set(-10).set(1).set(5); // R-G-B order
        caman.filter(FilterNames.CURVES).set("rgb")
                .set(new Integer[]{0, 0})
                .set(new Integer[]{80, 50})
                .set(new Integer[]{128, 230})
                .set(new Integer[]{255, 255});
        caman.filter(FilterNames.VIGNETTE).set(w).set(h).set("60%").set(30);

    }
}
