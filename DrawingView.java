// Inside DrawingView.java - Add these variables
public enum ToolMode { BRUSH, SQUARE, CIRCLE, TRIANGLE, EYEDROPPER, BLEND }
private ToolMode currentMode = ToolMode.BRUSH;

// For shape previewing
private float startX, startY; 

// Method to switch tools
public void setToolMode(ToolMode mode) {
    this.currentMode = mode;
}

// Logic to draw a triangle inside a bounding box
private void drawTriangle(Canvas canvas, float x1, float y1, float x2, float y2, Paint paint) {
    Path path = new Path();
    path.moveTo((x1 + x2) / 2, y1); // Top Point
    path.lineTo(x1, y2);           // Bottom Left
    path.lineTo(x2, y2);           // Bottom Right
    path.close();
    canvas.drawPath(path, paint);
}private void commitShape(float sX, float sY, float eX, float eY) {
    float left = Math.min(sX, eX);
    float right = Math.max(sX, eX);
    float top = Math.min(sY, eY);
    float bottom = Math.max(sY, eY);

    switch (currentMode) {
        case SQUARE:
            drawCanvas.drawRect(left, top, right, bottom, drawPaint);
            break;
        case CIRCLE:
            drawCanvas.drawOval(left, top, right, bottom, drawPaint);
            break;
        case TRIANGLE:
            drawTriangle(drawCanvas, left, top, right, bottom, drawPaint);
            break;
    }
    invalidate();
  public void setBlendMode(boolean active) {
    if (active) {
        drawPaint.setMaskFilter(new BlurMaskFilter(15, BlurMaskFilter.Blur.NORMAL));
        drawPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
    } else {
        drawPaint.setMaskFilter(null);
        drawPaint.setXfermode(null);
    }
}
}
