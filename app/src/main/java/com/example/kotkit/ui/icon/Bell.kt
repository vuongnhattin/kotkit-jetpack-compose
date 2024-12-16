import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val Bell: ImageVector
	get() {
		if (_Bell != null) {
			return _Bell!!
		}
		_Bell = ImageVector.Builder(
			name = "Bell",
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
				moveTo(13.377f, 10.573f)
				arcToRelative(7.63f, 7.63f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.383f, -2.38f)
				verticalLineTo(6.195f)
				arcToRelative(5.115f, 5.115f, 0f, isMoreThanHalf = false, isPositiveArc = false, -1.268f, -3.446f)
				arcToRelative(5.138f, 5.138f, 0f, isMoreThanHalf = false, isPositiveArc = false, -3.242f, -1.722f)
				curveToRelative(-0.694f, -0.072f, -1.4f, 0f, -2.07f, 0.227f)
				curveToRelative(-0.67f, 0.215f, -1.28f, 0.574f, -1.794f, 1.053f)
				arcToRelative(4.923f, 4.923f, 0f, isMoreThanHalf = false, isPositiveArc = false, -1.208f, 1.675f)
				arcToRelative(5.067f, 5.067f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.431f, 2.022f)
				verticalLineToRelative(2.2f)
				arcToRelative(7.61f, 7.61f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.383f, 2.37f)
				lineTo(2f, 12.343f)
				lineToRelative(0.479f, 0.658f)
				horizontalLineToRelative(3.505f)
				curveToRelative(0f, 0.526f, 0.215f, 1.04f, 0.586f, 1.412f)
				curveToRelative(0.37f, 0.37f, 0.885f, 0.586f, 1.412f, 0.586f)
				curveToRelative(0.526f, 0f, 1.04f, -0.215f, 1.411f, -0.586f)
				reflectiveCurveToRelative(0.587f, -0.886f, 0.587f, -1.412f)
				horizontalLineToRelative(3.505f)
				lineToRelative(0.478f, -0.658f)
				lineToRelative(-0.586f, -1.77f)
				close()
				moveToRelative(-4.69f, 3.147f)
				arcToRelative(0.997f, 0.997f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.705f, 0.299f)
				arcToRelative(0.997f, 0.997f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.706f, -0.3f)
				arcToRelative(0.997f, 0.997f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.3f, -0.705f)
				horizontalLineToRelative(1.999f)
				arcToRelative(0.939f, 0.939f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.287f, 0.706f)
				close()
				moveToRelative(-5.515f, -1.71f)
				lineToRelative(0.371f, -1.114f)
				arcToRelative(8.633f, 8.633f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0.443f, -2.691f)
				verticalLineTo(6.004f)
				curveToRelative(0f, -0.563f, 0.12f, -1.113f, 0.347f, -1.616f)
				curveToRelative(0.227f, -0.514f, 0.55f, -0.969f, 0.969f, -1.34f)
				curveToRelative(0.419f, -0.382f, 0.91f, -0.67f, 1.436f, -0.837f)
				curveToRelative(0.538f, -0.18f, 1.1f, -0.24f, 1.65f, -0.18f)
				arcToRelative(4.147f, 4.147f, 0f, isMoreThanHalf = false, isPositiveArc = true, 2.597f, 1.4f)
				arcToRelative(4.133f, 4.133f, 0f, isMoreThanHalf = false, isPositiveArc = true, 1.004f, 2.776f)
				verticalLineToRelative(2.01f)
				curveToRelative(0f, 0.909f, 0.144f, 1.818f, 0.443f, 2.691f)
				lineToRelative(0.371f, 1.113f)
				horizontalLineToRelative(-9.63f)
				verticalLineToRelative(-0.012f)
				close()
			}
		}.build()
		return _Bell!!
	}

private var _Bell: ImageVector? = null
