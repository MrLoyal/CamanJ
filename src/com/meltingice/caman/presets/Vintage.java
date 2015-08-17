package com.meltingice.caman.presets;

import com.meltingice.caman.CamanJ;
import com.meltingice.caman.CamanPreset;

/**
 * Created by MrLoyal on 8/15/2015.
 */
public class Vintage implements CamanPreset {
    @Override
    public void apply(CamanJ caman) {
        int w = caman.getImage().getWidth();
        int h = caman.getImage().getHeight();

        caman.filter("greyscale");
        caman.filter("contrast").set(5);
        caman.filter("noise").set(3);
        caman.filter("sepia").set(100);
        caman.filter("channels").set(8).set(2).set(4);
        caman.filter("gamma").set(0.87);
        caman.filter("vignette").set(w).set(h).set("40%").set(30);
    }
}
