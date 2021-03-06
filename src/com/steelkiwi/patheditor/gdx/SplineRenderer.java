/*
 * Copyright (C) 2013 Steelkiwi Development, Julia Zudikova
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.steelkiwi.patheditor.gdx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import com.steelkiwi.patheditor.gdx.SplineBuilder.renderMode;
import com.steelkiwi.patheditor.path.PathSpline;
import com.steelkiwi.patheditor.utils.ColorUtils;

public class SplineRenderer {
	private PathSpline spline;
	private ShapeRenderer renderer;
	
	private Color controlColor;
	private Color segmentColor;
	private Color selectColor;
	
	public SplineRenderer(PathSpline spline, String controlColorHex, String segmentColorHex, String selectColorHex) {
		this.spline = spline;
		java.awt.Color tempControlColor = java.awt.Color.decode(controlColorHex);
		this.controlColor = new Color(tempControlColor.getRed()/255f, tempControlColor.getGreen()/255f, tempControlColor.getBlue()/255f, 1f);
		java.awt.Color tempSegmentColor = java.awt.Color.decode(segmentColorHex);
		this.segmentColor = new Color(tempSegmentColor.getRed()/255f, tempSegmentColor.getGreen()/255f, tempSegmentColor.getBlue()/255f, 1f);
		java.awt.Color tempSelectColor  = java.awt.Color.decode(selectColorHex);
		this.selectColor  = new Color(tempSelectColor.getRed()/255f, tempSelectColor.getGreen()/255f, tempSelectColor.getBlue()/255f, 1f);
		renderer = new ShapeRenderer();
	}
	
	public void present(Matrix4 cameraCombined, int curVertexIndex, int leftVertexIndex, int rightVertexIndex, renderMode mode) {
		renderer.setProjectionMatrix(cameraCombined);
		
        renderer.begin(ShapeType.FilledCircle);
        spline.present(renderer, curVertexIndex, leftVertexIndex, rightVertexIndex, mode, controlColor, segmentColor, selectColor);
        renderer.end();
	}
	
	public void dispose() {
		spline = null;
		if (renderer != null) { renderer.dispose(); renderer = null; }
	}

	public String getControlColor() {
		java.awt.Color tempControlColor = new java.awt.Color(controlColor.r, controlColor.g, controlColor.b);
		return ColorUtils.colorToHex(tempControlColor);
	}

	public String getSegmentColor() {
		java.awt.Color tempSegmentColor = new java.awt.Color(segmentColor.r, segmentColor.g, segmentColor.b);
		return ColorUtils.colorToHex(tempSegmentColor);
	}

	public String getSelectColor() {
		java.awt.Color tempSelectColor = new java.awt.Color(selectColor.r, selectColor.g, selectColor.b);
		return ColorUtils.colorToHex(tempSelectColor);
	}
}
