package com.example.kotkit.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val Gallery_thumbnail: ImageVector
	get() {
		if (_Gallery_thumbnail != null) {
			return _Gallery_thumbnail!!
		}
		_Gallery_thumbnail = ImageVector.Builder(
            name = "Gallery_thumbnail",
            defaultWidth = 48.dp,
            defaultHeight = 48.dp,
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
				moveTo(120f, 760f)
				quadToRelative(-33f, 0f, -56.5f, -23.5f)
				reflectiveQuadTo(40f, 680f)
				verticalLineToRelative(-400f)
				quadToRelative(0f, -33f, 23.5f, -56.5f)
				reflectiveQuadTo(120f, 200f)
				horizontalLineToRelative(400f)
				quadToRelative(33f, 0f, 56.5f, 23.5f)
				reflectiveQuadTo(600f, 280f)
				verticalLineToRelative(400f)
				quadToRelative(0f, 33f, -23.5f, 56.5f)
				reflectiveQuadTo(520f, 760f)
				close()
				moveToRelative(600f, -320f)
				quadToRelative(-17f, 0f, -28.5f, -11.5f)
				reflectiveQuadTo(680f, 400f)
				verticalLineToRelative(-160f)
				quadToRelative(0f, -17f, 11.5f, -28.5f)
				reflectiveQuadTo(720f, 200f)
				horizontalLineToRelative(160f)
				quadToRelative(17f, 0f, 28.5f, 11.5f)
				reflectiveQuadTo(920f, 240f)
				verticalLineToRelative(160f)
				quadToRelative(0f, 17f, -11.5f, 28.5f)
				reflectiveQuadTo(880f, 440f)
				close()
				moveToRelative(40f, -80f)
				horizontalLineToRelative(80f)
				verticalLineToRelative(-80f)
				horizontalLineToRelative(-80f)
				close()
				moveTo(120f, 680f)
				horizontalLineToRelative(400f)
				verticalLineToRelative(-400f)
				horizontalLineTo(120f)
				close()
				moveToRelative(40f, -80f)
				horizontalLineToRelative(320f)
				lineTo(375f, 460f)
				lineToRelative(-75f, 100f)
				lineToRelative(-55f, -73f)
				close()
				moveToRelative(560f, 160f)
				quadToRelative(-17f, 0f, -28.5f, -11.5f)
				reflectiveQuadTo(680f, 720f)
				verticalLineToRelative(-160f)
				quadToRelative(0f, -17f, 11.5f, -28.5f)
				reflectiveQuadTo(720f, 520f)
				horizontalLineToRelative(160f)
				quadToRelative(17f, 0f, 28.5f, 11.5f)
				reflectiveQuadTo(920f, 560f)
				verticalLineToRelative(160f)
				quadToRelative(0f, 17f, -11.5f, 28.5f)
				reflectiveQuadTo(880f, 760f)
				close()
				moveToRelative(40f, -80f)
				horizontalLineToRelative(80f)
				verticalLineToRelative(-80f)
				horizontalLineToRelative(-80f)
				close()
				moveToRelative(-640f, 0f)
				verticalLineToRelative(-400f)
				close()
				moveToRelative(640f, -320f)
				verticalLineToRelative(-80f)
				close()
				moveToRelative(0f, 320f)
				verticalLineToRelative(-80f)
				close()
			}
		}.build()
		return _Gallery_thumbnail!!
	}

private var _Gallery_thumbnail: ImageVector? = null
