package com.clabs.demos;

import com.clabs.engine.api.Game;
import com.clabs.engine.core.EngineException;

import java.awt.*;

public class Mandelbrot implements Game {

    private double startX;
    private double startY;
    private double width;
    private double height;

    private int rows;
    private int cols;
    private int maxIterations;

    private int[][] counts;
    private int[] colors;

    private double deltaX;
    private double deltaY;

    @Override
    public void init() throws EngineException {
        startX = -2;
        startY = 2;
        width = 4;
        height = 4;
        rows = 800;
        cols = 800;
        maxIterations = 500;

        deltaX = width / (cols -1);
        deltaY = height / (rows -1);

        counts = new int[rows][cols];
        colors = new int[maxIterations];

        initColors();
        iterate();
    }

    private void iterate() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Complex z = new Complex(0,0);
                double c_re = startX + j * deltaX;
                double c_im = startY - i + deltaY;
                Complex c = new Complex(c_re, c_im);

                int iterations = 0;

                while (true) {
                    // f(x) = x² + c
                    z = (z.multiply(z)).add(c);
                    iterations++;
                    if (iterations > maxIterations || z.modulus() > 2) break;
                }

                if (iterations >= maxIterations) {
                    counts[i][j] = 0;
                    // TODO: set pixel at i,j to black
                } else {
                    counts[i][j] = iterations;
                    // TODO: set pixel at i,j to colors[iterations]
                }
            }
        }
    }

    private void initColors() {
        for (int i = 0; i < colors.length; i++) {
            colors[i] = Color.HSBtoRGB(i/256f, 1.0f, i/(i+8.0f));
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {

    }

    @Override
    public void destroy() throws EngineException {

    }
}

class Complex {

    private final double re;
    private final double im;

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public Complex add(Complex c) {
        double re = this.re + c.re;
        double im = this.im + c.im;
        return new Complex(re, im);
    }

    public Complex multiply(Complex c) {
        double re = this.re * c.re - this.im * c.im;
        double im = this.re * c.im + this.im * c.re;
        return new Complex(re, im);
    }

    public double modulus() {
        return Math.sqrt(this.re*this.re + this.im * this.im);
    }

}
