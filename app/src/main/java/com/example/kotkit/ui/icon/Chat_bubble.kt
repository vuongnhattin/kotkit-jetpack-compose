package com.example.kotkit.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val Chat_bubble: ImageVector
	get() {
		if (_Chat_bubble != null) {
			return _Chat_bubble!!
		}
		_Chat_bubble = ImageVector.Builder(
            name = "Chat_bubble",
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
				moveTo(80f, 880f)
				verticalLineToRelative(-720f)
				quadToRelative(0f, -33f, 23.5f, -56.5f)
				reflectiveQuadTo(160f, 80f)
				horizontalLineToRelative(640f)
				quadToRelative(33f, 0f, 56.5f, 23.5f)
				reflectiveQuadTo(880f, 160f)
				verticalLineToRelative(480f)
				quadToRelative(0f, 33f, -23.5f, 56.5f)
				reflectiveQuadTo(800f, 720f)
				horizontalLineTo(240f)
				close()
				moveToRelative(126f, -240f)
				horizontalLineToRelative(594f)
				verticalLineToRelative(-480f)
				horizontalLineTo(160f)
				verticalLineToRelative(525f)
				close()
				moveToRelative(-46f, 0f)
				verticalLineToRelative(-480f)
				close()
			}
		}.build()
		return _Chat_bubble!!
	}

private var _Chat_bubble: ImageVector? = null
