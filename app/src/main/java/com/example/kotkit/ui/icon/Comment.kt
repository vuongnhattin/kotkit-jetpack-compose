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
    			pathFillType = PathFillType.NonZero
			) {
				moveTo(14.5f, 2f)
				horizontalLineToRelative(-13f)
				lineToRelative(-0.5f, 0.5f)
				verticalLineToRelative(9f)
				lineToRelative(0.5f, 0.5f)
				horizontalLineTo(4f)
				verticalLineToRelative(2.5f)
				lineToRelative(0.854f, 0.354f)
				lineTo(7.707f, 12f)
				horizontalLineTo(14.5f)
				lineToRelative(0.5f, -0.5f)
				verticalLineToRelative(-9f)
				lineToRelative(-0.5f, -0.5f)
				close()
				moveToRelative(-0.5f, 9f)
				horizontalLineTo(7.5f)
				lineToRelative(-0.354f, 0.146f)
				lineTo(5f, 13.293f)
				verticalLineTo(11.5f)
				lineToRelative(-0.5f, -0.5f)
				horizontalLineTo(2f)
				verticalLineTo(3f)
				horizontalLineToRelative(12f)
				verticalLineToRelative(8f)
				close()
			}
		}.build()
		return _Comment!!
	}

private var _Comment: ImageVector? = null
