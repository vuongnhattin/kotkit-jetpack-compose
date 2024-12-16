package com.example.kotkit.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val Public: ImageVector
	get() {
		if (_Public != null) {
			return _Public!!
		}
		_Public = ImageVector.Builder(
            name = "Public",
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
				moveTo(480f, 880f)
				quadToRelative(-83f, 0f, -156f, -31.5f)
				reflectiveQuadTo(197f, 763f)
				reflectiveQuadToRelative(-85.5f, -127f)
				reflectiveQuadTo(80f, 480f)
				reflectiveQuadToRelative(31.5f, -156f)
				reflectiveQuadTo(197f, 197f)
				reflectiveQuadToRelative(127f, -85.5f)
				reflectiveQuadTo(480f, 80f)
				reflectiveQuadToRelative(156f, 31.5f)
				reflectiveQuadTo(763f, 197f)
				reflectiveQuadToRelative(85.5f, 127f)
				reflectiveQuadTo(880f, 480f)
				reflectiveQuadToRelative(-31.5f, 156f)
				reflectiveQuadTo(763f, 763f)
				reflectiveQuadToRelative(-127f, 85.5f)
				reflectiveQuadTo(480f, 880f)
				moveToRelative(-40f, -82f)
				verticalLineToRelative(-78f)
				quadToRelative(-33f, 0f, -56.5f, -23.5f)
				reflectiveQuadTo(360f, 640f)
				verticalLineToRelative(-40f)
				lineTo(168f, 408f)
				quadToRelative(-3f, 18f, -5.5f, 36f)
				reflectiveQuadToRelative(-2.5f, 36f)
				quadToRelative(0f, 121f, 79.5f, 212f)
				reflectiveQuadTo(440f, 798f)
				moveToRelative(276f, -102f)
				quadToRelative(20f, -22f, 36f, -47.5f)
				reflectiveQuadToRelative(26.5f, -53f)
				reflectiveQuadToRelative(16f, -56.5f)
				reflectiveQuadToRelative(5.5f, -59f)
				quadToRelative(0f, -98f, -54.5f, -179f)
				reflectiveQuadTo(600f, 184f)
				verticalLineToRelative(16f)
				quadToRelative(0f, 33f, -23.5f, 56.5f)
				reflectiveQuadTo(520f, 280f)
				horizontalLineToRelative(-80f)
				verticalLineToRelative(80f)
				quadToRelative(0f, 17f, -11.5f, 28.5f)
				reflectiveQuadTo(400f, 400f)
				horizontalLineToRelative(-80f)
				verticalLineToRelative(80f)
				horizontalLineToRelative(240f)
				quadToRelative(17f, 0f, 28.5f, 11.5f)
				reflectiveQuadTo(600f, 520f)
				verticalLineToRelative(120f)
				horizontalLineToRelative(40f)
				quadToRelative(26f, 0f, 47f, 15.5f)
				reflectiveQuadToRelative(29f, 40.5f)
			}
		}.build()
		return _Public!!
	}

private var _Public: ImageVector? = null
