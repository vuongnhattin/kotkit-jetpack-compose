package com.example.kotkit.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val Heart: ImageVector
	get() {
		if (_Heart != null) {
			return _Heart!!
		}
		_Heart = ImageVector.Builder(
			name = "Heart",
			defaultWidth = 16.dp,
			defaultHeight = 16.dp,
			viewportWidth = 16f,
			viewportHeight = 16f
		).apply {
			path(
				fill = SolidColor(Color(0xFF000000)),
				fillAlpha = 1.0f,
				stroke = null,
				strokeAlpha = 1.0f,
				strokeLineWidth = 1.0f,
				strokeLineCap = StrokeCap.Butt,
				strokeLineJoin = StrokeJoin.Miter,
				strokeLineMiter = 1.0f,
				pathFillType = PathFillType.EvenOdd
			) {
				moveTo(8f, 1.314f)
				curveTo(12.438f, -3.248f, 23.534f, 4.735f, 8f, 15f)
				curveTo(-7.534f, 4.736f, 3.562f, -3.248f, 8f, 1.314f)
			}
		}.build()
		return _Heart!!
	}

private var _Heart: ImageVector? = null