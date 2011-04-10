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
package com.meltingice.caman;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import javax.imageio.ImageIO;

import com.meltingice.caman.exceptions.InvalidArgumentsException;
import com.meltingice.caman.util.CamanUtil;

/**
 * The main CamanJ class that is interacted with.
 * 
 * @author Ryan LeFevre
 * @version 1.0
 */
public class CamanJ {
	/**
	 * The Image object that stores important information about the current
	 * image loaded with this CamanJ object
	 */
	private Image image;

	/**
	 * The render queue of filters
	 */
	private Queue<CamanFilter> filters;

	/**
	 * A flag that says if this image has been rendered or not yet.
	 */
	private boolean isRendered = false;

	/**
	 * Creates a new standard CamanJ object
	 * 
	 * @param file
	 *            The filename of the image to load
	 */
	public CamanJ(String file) {
		try {
			this.image = new Image(file);
		} catch (IOException e) {
			System.err.println("CamanJ: Unable to load image from file");
		}

		this.filters = new LinkedList<CamanFilter>();
	}

	/**
	 * Lazy-loads a filter using reflection and adds it to the filter list so
	 * that it can be executed later. All filters at this time must reside in
	 * the package com.meltingice.caman.filters. In the future, this will
	 * support loading from JAR files for easily extensibility.
	 * 
	 * @param name
	 *            The name of the filter to instantiate
	 * @return The CamanFilter object
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("rawtypes")
	private CamanFilter loadFilter(String name) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {

		Class c1 = Class.forName("com.meltingice.caman.filters."
				+ CamanUtil.getFilterName(name));
		CamanFilter plugin = (CamanFilter) c1.newInstance();

		System.out.println("CamanJ: loaded filter " + name);
		filters.add(plugin);
		return plugin;
	}

	/**
	 * Loads a filter and returns it so that its parameters (if any) can be set.
	 * 
	 * @param name
	 *            The name of the filter
	 * @return The filter object
	 */
	public CamanFilter filter(String name) {
		try {
			CamanFilter plugin = loadFilter(name);

			// Reset the isRendered flag because we've added more filters
			isRendered = false;

			return plugin;
		} catch (Exception e) {
			System.err.println("CamanJ: Invalid filter name");
			return null;
		}
	}

	/**
	 * Goes through all of the filters (in order) and applies them to the image.
	 */
	public void render() {
		// Benchmarking
		long start = System.currentTimeMillis();

		while (!filters.isEmpty()) {
			CamanFilter filter = filters.remove();

			try {
				filter.precomputeParams();
			} catch (InvalidArgumentsException e) {
				System.err.println("CamanJ: invalid arguments given to "
						+ filter.getClass().getName());
				continue;
			}

			for (int i = 0; i < image.getWidth(); i++) {
				for (int j = 0; j < image.getHeight(); j++) {
					if (filter.type() == PluginType.PIXELWISE) {
						image.pixels[i][j] = filter.process(image.pixels[i][j]);
					}
				}
			}
		}

		long end = System.currentTimeMillis();
		System.out.println("CamanJ: rendering finished in " + (end - start)
				+ "ms");

		// Set isRendered to true until we add more filters again to prevent
		// re-rendering the same content
		isRendered = true;
	}

	/**
	 * Saves the modified image to a new BufferedImage object. Will
	 * automatically call render() if it has not been called already.
	 * 
	 * @return The new BufferedImage object
	 */
	public BufferedImage save() {
		if (!isRendered) {
			// If the image hasn't been rendered yet, do so first.
			render();
		}

		BufferedImage dest = image.getDestImage();

		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				dest.setRGB(i, j, image.getPixelRGB(i, j));
			}
		}

		return dest;
	}

	/**
	 * Saves the modified image to a file.
	 * 
	 * @param outFile
	 *            The path to the output file.
	 */
	public void save(String outFile) {
		BufferedImage dest = this.save();
		File file = new File(outFile);
		try {
			ImageIO.write(dest, "png", file);
		} catch (IOException e) {
			System.err.println("CamanJ: error writing to file "
					+ file.getName());
		}
	}
}
