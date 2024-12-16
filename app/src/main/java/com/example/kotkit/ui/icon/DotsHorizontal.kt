package com.example.kotkit.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val DotsHorizontal: ImageVector
	get() {
		if (_DotsHorizontal != null) {
			return _DotsHorizontal!!
		}
		_DotsHorizontal = ImageVector.Builder(
            name = "DotsHorizontal",
            defaultWidth = 15.dp,
            defaultHeight = 15.dp,
            viewportWidth = 15f,
            viewportHeight = 15f
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
				moveTo(3.625f, 7.5f)
				curveTo(3.625f, 8.1213f, 3.1213f, 8.625f, 2.5f, 8.625f)
				curveTo(1.8787f, 8.625f, 1.375f, 8.1213f, 1.375f, 7.5f)
				curveTo(1.375f, 6.8787f, 1.8787f, 6.375f, 2.5f, 6.375f)
				curveTo(3.1213f, 6.375f, 3.625f, 6.8787f, 3.625f, 7.5f)
				close()
				moveTo(8.625f, 7.5f)
				curveTo(8.625f, 8.1213f, 8.1213f, 8.625f, 7.5f, 8.625f)
				curveTo(6.8787f, 8.625f, 6.375f, 8.1213f, 6.375f, 7.5f)
				curveTo(6.375f, 6.8787f, 6.8787f, 6.375f, 7.5f, 6.375f)
				curveTo(8.1213f, 6.375f, 8.625f, 6.8787f, 8.625f, 7.5f)
				close()
				moveTo(12.5f, 8.625f)
				curveTo(13.1213f, 8.625f, 13.625f, 8.1213f, 13.625f, 7.5f)
				curveTo(13.625f, 6.8787f, 13.1213f, 6.375f, 12.5f, 6.375f)
				curveTo(11.8787f, 6.375f, 11.375f, 6.8787f, 11.375f, 7.5f)
				curveTo(11.375f, 8.1213f, 11.8787f, 8.625f, 12.5f, 8.625f)
				close()
			}
		}.build()
		return _DotsHorizontal!!
	}

private var _DotsHorizontal: ImageVector? = null
