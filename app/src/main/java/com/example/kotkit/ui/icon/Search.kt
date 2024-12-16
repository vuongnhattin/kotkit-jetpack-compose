package com.example.kotkit.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val Search: ImageVector
	get() {
		if (_Search != null) {
			return _Search!!
		}
		_Search = ImageVector.Builder(
            name = "Search",
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
    			pathFillType = PathFillType.NonZero
			) {
				moveTo(11.742f, 10.344f)
				arcToRelative(6.5f, 6.5f, 0f, isMoreThanHalf = true, isPositiveArc = false, -1.397f, 1.398f)
				horizontalLineToRelative(-0.001f)
				quadToRelative(0.044f, 0.06f, 0.098f, 0.115f)
				lineToRelative(3.85f, 3.85f)
				arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = false, 1.415f, -1.414f)
				lineToRelative(-3.85f, -3.85f)
				arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.115f, -0.1f)
				close()
				moveTo(12f, 6.5f)
				arcToRelative(5.5f, 5.5f, 0f, isMoreThanHalf = true, isPositiveArc = true, -11f, 0f)
				arcToRelative(5.5f, 5.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, 11f, 0f)
			}
		}.build()
		return _Search!!
	}

private var _Search: ImageVector? = null
