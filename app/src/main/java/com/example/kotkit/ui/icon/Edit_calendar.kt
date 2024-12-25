package com.example.kotkit.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val Edit_calendar: ImageVector
	get() {
		if (_Edit_calendar != null) {
			return _Edit_calendar!!
		}
		_Edit_calendar = ImageVector.Builder(
            name = "Edit_calendar",
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
				moveTo(200f, 880f)
				quadToRelative(-33f, 0f, -56.5f, -23.5f)
				reflectiveQuadTo(120f, 800f)
				verticalLineToRelative(-560f)
				quadToRelative(0f, -33f, 23.5f, -56.5f)
				reflectiveQuadTo(200f, 160f)
				horizontalLineToRelative(40f)
				verticalLineToRelative(-80f)
				horizontalLineToRelative(80f)
				verticalLineToRelative(80f)
				horizontalLineToRelative(320f)
				verticalLineToRelative(-80f)
				horizontalLineToRelative(80f)
				verticalLineToRelative(80f)
				horizontalLineToRelative(40f)
				quadToRelative(33f, 0f, 56.5f, 23.5f)
				reflectiveQuadTo(840f, 240f)
				verticalLineToRelative(200f)
				horizontalLineToRelative(-80f)
				verticalLineToRelative(-40f)
				horizontalLineTo(200f)
				verticalLineToRelative(400f)
				horizontalLineToRelative(280f)
				verticalLineToRelative(80f)
				close()
				moveToRelative(0f, -560f)
				horizontalLineToRelative(560f)
				verticalLineToRelative(-80f)
				horizontalLineTo(200f)
				close()
				moveToRelative(0f, 0f)
				verticalLineToRelative(-80f)
				close()
				moveTo(560f, 880f)
				verticalLineToRelative(-123f)
				lineToRelative(221f, -220f)
				quadToRelative(9f, -9f, 20f, -13f)
				reflectiveQuadToRelative(22f, -4f)
				quadToRelative(12f, 0f, 23f, 4.5f)
				reflectiveQuadToRelative(20f, 13.5f)
				lineToRelative(37f, 37f)
				quadToRelative(8f, 9f, 12.5f, 20f)
				reflectiveQuadToRelative(4.5f, 22f)
				reflectiveQuadToRelative(-4f, 22.5f)
				reflectiveQuadToRelative(-13f, 20.5f)
				lineTo(683f, 880f)
				close()
				moveToRelative(300f, -263f)
				lineToRelative(-37f, -37f)
				close()
				moveTo(620f, 820f)
				horizontalLineToRelative(38f)
				lineToRelative(121f, -122f)
				lineToRelative(-18f, -19f)
				lineToRelative(-19f, -18f)
				lineToRelative(-122f, 121f)
				close()
				moveToRelative(141f, -141f)
				lineToRelative(-19f, -18f)
				lineToRelative(37f, 37f)
				close()
			}
		}.build()
		return _Edit_calendar!!
	}

private var _Edit_calendar: ImageVector? = null
