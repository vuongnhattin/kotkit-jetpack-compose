package com.example.kotkit.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val Comment: ImageVector
	get() {
		if (_Comment != null) {
			return _Comment!!
		}
		_Comment = ImageVector.Builder(
			name = "Comment",
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
				moveTo(1.5f, 2f)
				horizontalLineToRelative(13f)
				verticalLineToRelative(9f)
				horizontalLineTo(7.707f)
				lineTo(4.854f, 13.854f)
				lineTo(4f, 13.5f)
				verticalLineTo(11f)
				horizontalLineTo(1.5f)
				verticalLineToRelative(-9f)
				close()
			}
		}.build()
		return _Comment!!
	}

private var _Comment: ImageVector? = null