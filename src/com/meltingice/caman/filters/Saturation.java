/**
 * CamanJ - Java Image Manipulation
 * Ported from the CamanJS Javascript library
 *
 * Copyright 2011, Ryan LeFevre
 * Licensed under the new BSD License
 * See LICENSE for more info.
 * 
 * Project Home: http://github.com/meltingice/CamanJ
 */
package com.meltingice.caman.filters;

import com.meltingice.caman.CamanFilter;
import com.meltingice.caman.exceptions.InvalidArgumentsException;
import com.meltingice.caman.util.CamanUtil;

/**
 * Adjusts the saturation of the image. Negative values will desaturate, while
 * positive values will saturate. If you are looking to make an image greyscale,
 * use the {@link Greyscale#Greyscale()} filter instead.
 * 
 * Params: (double)
 * 
 * @author Ryan LeFevre
 * @version 1.0
 */
public class Saturation extends CamanFilter {
	private double arg;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.meltingice.caman.CamanFilter#precomputeParams()
	 */
	@Override
	public void precomputeParams() throws InvalidArgumentsException {
		arg = getParamDouble(0) * - 0.01;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.meltingice.caman.CamanFilter#process(int[])
	 */
	@Override
	public int[] process(int[] rgb) {
		int max = Math.max(Math.max(rgb[0], rgb[1]), rgb[2]);
		int diff;

		for (int i = 0; i < 3; i++) {
			if (rgb[i] != max) {
				diff = max - rgb[i];
				rgb[i] += diff * arg;
			}
		}

		return CamanUtil.clampRGB(rgb);
	}
}
