package com.example.kotkit.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val User: ImageVector
	get() {
		if (_User != null) {
			return _User!!
		}
		_User = ImageVector.Builder(
            name = "User",
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
				moveTo(15.75f, 6f)
				curveTo(15.75f, 8.0711f, 14.071f, 9.75f, 12f, 9.75f)
				curveTo(9.9289f, 9.75f, 8.25f, 8.0711f, 8.25f, 6f)
				curveTo(8.25f, 3.9289f, 9.9289f, 2.25f, 12f, 2.25f)
				curveTo(14.071f, 2.25f, 15.75f, 3.9289f, 15.75f, 6f)
				close()
			}
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
				moveTo(4.5011f, 20.1182f)
				curveTo(4.5714f, 16.0369f, 7.9018f, 12.75f, 12f, 12.75f)
				curveTo(16.0982f, 12.75f, 19.4287f, 16.0371f, 19.4988f, 20.1185f)
				curveTo(17.216f, 21.166f, 14.6764f, 21.75f, 12.0003f, 21.75f)
				curveTo(9.324f, 21.75f, 6.7841f, 21.1659f, 4.5011f, 20.1182f)
				close()
			}
		}.build()
		return _User!!
	}

private var _User: ImageVector? = null
