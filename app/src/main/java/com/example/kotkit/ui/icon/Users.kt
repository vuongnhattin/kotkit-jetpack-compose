package com.example.kotkit.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val Users: ImageVector
	get() {
		if (_Users != null) {
			return _Users!!
		}
		_Users = ImageVector.Builder(
            name = "Users",
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
				moveTo(15f, 19.1276f)
				curveTo(15.8329f, 19.37f, 16.7138f, 19.5f, 17.625f, 19.5f)
				curveTo(19.1037f, 19.5f, 20.5025f, 19.1576f, 21.7464f, 18.5478f)
				curveTo(21.7488f, 18.4905f, 21.75f, 18.4329f, 21.75f, 18.375f)
				curveTo(21.75f, 16.0968f, 19.9031f, 14.25f, 17.625f, 14.25f)
				curveTo(16.2069f, 14.25f, 14.956f, 14.9655f, 14.2136f, 16.0552f)
				moveTo(15f, 19.1276f)
				verticalLineTo(19.125f)
				curveTo(15f, 18.0121f, 14.7148f, 16.9658f, 14.2136f, 16.0552f)
				moveTo(15f, 19.1276f)
				curveTo(15f, 19.1632f, 14.9997f, 19.1988f, 14.9991f, 19.2343f)
				curveTo(13.1374f, 20.3552f, 10.9565f, 21f, 8.625f, 21f)
				curveTo(6.2935f, 21f, 4.1126f, 20.3552f, 2.2509f, 19.2343f)
				curveTo(2.2503f, 19.198f, 2.25f, 19.1615f, 2.25f, 19.125f)
				curveTo(2.25f, 15.6042f, 5.1042f, 12.75f, 8.625f, 12.75f)
				curveTo(11.0329f, 12.75f, 13.129f, 14.085f, 14.2136f, 16.0552f)
				moveTo(12f, 6.375f)
				curveTo(12f, 8.239f, 10.489f, 9.75f, 8.625f, 9.75f)
				curveTo(6.761f, 9.75f, 5.25f, 8.239f, 5.25f, 6.375f)
				curveTo(5.25f, 4.511f, 6.761f, 3f, 8.625f, 3f)
				curveTo(10.489f, 3f, 12f, 4.511f, 12f, 6.375f)
				close()
				moveTo(20.25f, 8.625f)
				curveTo(20.25f, 10.0747f, 19.0747f, 11.25f, 17.625f, 11.25f)
				curveTo(16.1753f, 11.25f, 15f, 10.0747f, 15f, 8.625f)
				curveTo(15f, 7.1753f, 16.1753f, 6f, 17.625f, 6f)
				curveTo(19.0747f, 6f, 20.25f, 7.1753f, 20.25f, 8.625f)
				close()
			}
		}.build()
		return _Users!!
	}

private var _Users: ImageVector? = null
