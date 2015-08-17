package com.meltingice.caman.presets;

import com.meltingice.caman.CamanJ;
import com.meltingice.caman.CamanPreset;

/**
 * Created by MrLoyal on 8/17/2015.
 */
public class OrangePeel implements CamanPreset {
    @Override
    public void apply(CamanJ caman) {
        caman.filter(FilterNames.CURVES).set("rgb")
                .set(new Integer[]{0, 0})
                .set(new Integer[]{100, 50})
                .set(new Integer[]{140, 200})
                .set(new Integer[]{255, 255});
        caman.filter(FilterNames.VIBRANCE).set(-30);
        caman.filter(FilterNames.SATURATION).set(-30);
        caman.filter(FilterNames.COLORIZE).set("#ff9000").set(30);
        caman.filter(FilterNames.CONTRAST).set(-5);
        caman.filter(FilterNames.GAMMA).set(1.4);

    }
}
