package com.example.kotkit.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val Lock: ImageVector
	get() {
		if (_Lock != null) {
			return _Lock!!
		}
		_Lock = ImageVector.Builder(
            name = "Lock",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
			path(
    			fill = SolidColor(Color.Black),
    			fillAlpha = 1.0f,
    			stroke = null,
    			strokeAlpha = 1.0f,
    			strokeLineWidth = 1.0f,
    			strokeLineCap = StrokeCap.Butt,
    			strokeLineJoin = StrokeJoin.Miter,
    			strokeLineMiter = 1.0f,
    			pathFillType = PathFillType.NonZero
			) {
				moveTo(240f, 880f)
				quadToRelative(-33f, 0f, -56.5f, -23.5f)
				reflectiveQuadTo(160f, 800f)
				verticalLineToRelative(-400f)
				quadToRelative(0f, -33f, 23.5f, -56.5f)
				reflectiveQuadTo(240f, 320f)
				horizontalLineToRelative(40f)
				verticalLineToRelative(-80f)
				quadToRelative(0f, -83f, 58.5f, -141.5f)
				reflectiveQuadTo(480f, 40f)
				reflectiveQuadToRelative(141.5f, 58.5f)
				reflectiveQuadTo(680f, 240f)
				verticalLineToRelative(80f)
				horizontalLineToRelative(40f)
				quadToRelative(33f, 0f, 56.5f, 23.5f)
				reflectiveQuadTo(800f, 400f)
				verticalLineToRelative(400f)
				quadToRelative(0f, 33f, -23.5f, 56.5f)
				reflectiveQuadTo(720f, 880f)
				close()
				moveToRelative(0f, -80f)
				horizontalLineToRelative(480f)
				verticalLineToRelative(-400f)
				horizontalLineTo(240f)
				close()
				moveToRelative(240f, -120f)
				quadToRelative(33f, 0f, 56.5f, -23.5f)
				reflectiveQuadTo(560f, 600f)
				reflectiveQuadToRelative(-23.5f, -56.5f)
				reflectiveQuadTo(480f, 520f)
				reflectiveQuadToRelative(-56.5f, 23.5f)
				reflectiveQuadTo(400f, 600f)
				reflectiveQuadToRelative(23.5f, 56.5f)
				reflectiveQuadTo(480f, 680f)
				moveTo(360f, 320f)
				horizontalLineToRelative(240f)
				verticalLineToRelative(-80f)
				quadToRelative(0f, -50f, -35f, -85f)
				reflectiveQuadToRelative(-85f, -35f)
				reflectiveQuadToRelative(-85f, 35f)
				reflectiveQuadToRelative(-35f, 85f)
				close()
				moveTo(240f, 800f)
				verticalLineToRelative(-400f)
				close()
			}
		}.build()
		return _Lock!!
	}

private var _Lock: ImageVector? = null
