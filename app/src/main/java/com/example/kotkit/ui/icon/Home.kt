package com.example.kotkit.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val Home: ImageVector
	get() {
		if (_Home != null) {
			return _Home!!
		}
		_Home = ImageVector.Builder(
            name = "Home",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
			path(
    			fill = null,
    			fillAlpha = 1.0f,
    			stroke = SolidColor(Color(0xFF0F172A)),
    			strokeAlpha = 1.0f,
    			strokeLineWidth = 1.5f,
    			strokeLineCap = StrokeCap.Round,
    			strokeLineJoin = StrokeJoin.Round,
    			strokeLineMiter = 1.0f,
    			pathFillType = PathFillType.NonZero
			) {
				moveTo(2.25f, 12f)
				lineTo(11.2045f, 3.04549f)
				curveTo(11.6438f, 2.6061f, 12.3562f, 2.6061f, 12.7955f, 3.0455f)
				lineTo(21.75f, 12f)
				moveTo(4.5f, 9.75f)
				verticalLineTo(19.875f)
				curveTo(4.5f, 20.4963f, 5.0037f, 21f, 5.625f, 21f)
				horizontalLineTo(9.75f)
				verticalLineTo(16.125f)
				curveTo(9.75f, 15.5037f, 10.2537f, 15f, 10.875f, 15f)
				horizontalLineTo(13.125f)
				curveTo(13.7463f, 15f, 14.25f, 15.5037f, 14.25f, 16.125f)
				verticalLineTo(21f)
				horizontalLineTo(18.375f)
				curveTo(18.9963f, 21f, 19.5f, 20.4963f, 19.5f, 19.875f)
				verticalLineTo(9.75f)
				moveTo(8.25f, 21f)
				horizontalLineTo(16.5f)
			}
		}.build()
		return _Home!!
	}

private var _Home: ImageVector? = null
