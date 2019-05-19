package net.opacapp.multilinecollapsingtoolbar;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.support.annotation.ColorInt;
import android.support.v4.text.TextDirectionHeuristicsCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.C0180R;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.animation.Interpolator;

final class CollapsingTextHelper {
    private static final boolean DEBUG_DRAW = false;
    private static final Paint DEBUG_DRAW_PAINT = null;
    private static final boolean USE_SCALING_TEXTURE = (VERSION.SDK_INT < 18);
    private boolean mBoundsChanged;
    private final Rect mCollapsedBounds;
    private float mCollapsedDrawX;
    private float mCollapsedDrawY;
    private int mCollapsedShadowColor;
    private float mCollapsedShadowDx;
    private float mCollapsedShadowDy;
    private float mCollapsedShadowRadius;
    private float mCollapsedTextBlend;
    private ColorStateList mCollapsedTextColor;
    private int mCollapsedTextGravity = 16;
    private float mCollapsedTextSize = 15.0f;
    private Bitmap mCollapsedTitleTexture;
    private Typeface mCollapsedTypeface;
    private Bitmap mCrossSectionTitleTexture;
    private final RectF mCurrentBounds;
    private float mCurrentDrawX;
    private float mCurrentDrawY;
    private float mCurrentTextSize;
    private Typeface mCurrentTypeface;
    private boolean mDrawTitle;
    private final Rect mExpandedBounds;
    private float mExpandedDrawX;
    private float mExpandedDrawY;
    private float mExpandedFirstLineDrawX;
    private float mExpandedFraction;
    private int mExpandedShadowColor;
    private float mExpandedShadowDx;
    private float mExpandedShadowDy;
    private float mExpandedShadowRadius;
    private float mExpandedTextBlend;
    private ColorStateList mExpandedTextColor;
    private int mExpandedTextGravity = 16;
    private float mExpandedTextSize = 15.0f;
    private Bitmap mExpandedTitleTexture;
    private Typeface mExpandedTypeface;
    private boolean mIsRtl;
    private Interpolator mPositionInterpolator;
    private float mScale;
    private int[] mState;
    private CharSequence mText;
    private StaticLayout mTextLayout;
    private final TextPaint mTextPaint;
    private Interpolator mTextSizeInterpolator;
    private CharSequence mTextToDraw;
    private CharSequence mTextToDrawCollapsed;
    private Paint mTexturePaint;
    private boolean mUseTexture;
    private final View mView;
    private int maxLines = 3;

    static {
        if (DEBUG_DRAW_PAINT != null) {
            DEBUG_DRAW_PAINT.setAntiAlias(true);
            DEBUG_DRAW_PAINT.setColor(-65281);
        }
    }

    public CollapsingTextHelper(View view) {
        this.mView = view;
        this.mTextPaint = new TextPaint(129);
        this.mCollapsedBounds = new Rect();
        this.mExpandedBounds = new Rect();
        this.mCurrentBounds = new RectF();
    }

    void setTextSizeInterpolator(Interpolator interpolator) {
        this.mTextSizeInterpolator = interpolator;
        recalculate();
    }

    void setPositionInterpolator(Interpolator interpolator) {
        this.mPositionInterpolator = interpolator;
        recalculate();
    }

    void setExpandedTextSize(float textSize) {
        if (this.mExpandedTextSize != textSize) {
            this.mExpandedTextSize = textSize;
            recalculate();
        }
    }

    void setCollapsedTextSize(float textSize) {
        if (this.mCollapsedTextSize != textSize) {
            this.mCollapsedTextSize = textSize;
            recalculate();
        }
    }

    void setCollapsedTextColor(ColorStateList textColor) {
        if (this.mCollapsedTextColor != textColor) {
            this.mCollapsedTextColor = textColor;
            recalculate();
        }
    }

    void setExpandedTextColor(ColorStateList textColor) {
        if (this.mExpandedTextColor != textColor) {
            this.mExpandedTextColor = textColor;
            recalculate();
        }
    }

    void setExpandedBounds(int left, int top, int right, int bottom) {
        if (!rectEquals(this.mExpandedBounds, left, top, right, bottom)) {
            this.mExpandedBounds.set(left, top, right, bottom);
            this.mBoundsChanged = true;
            onBoundsChanged();
        }
    }

    void setCollapsedBounds(int left, int top, int right, int bottom) {
        if (!rectEquals(this.mCollapsedBounds, left, top, right, bottom)) {
            this.mCollapsedBounds.set(left, top, right, bottom);
            this.mBoundsChanged = true;
            onBoundsChanged();
        }
    }

    void onBoundsChanged() {
        boolean z = this.mCollapsedBounds.width() > 0 && this.mCollapsedBounds.height() > 0 && this.mExpandedBounds.width() > 0 && this.mExpandedBounds.height() > 0;
        this.mDrawTitle = z;
    }

    void setExpandedTextGravity(int gravity) {
        if (this.mExpandedTextGravity != gravity) {
            this.mExpandedTextGravity = gravity;
            recalculate();
        }
    }

    int getExpandedTextGravity() {
        return this.mExpandedTextGravity;
    }

    void setCollapsedTextGravity(int gravity) {
        if (this.mCollapsedTextGravity != gravity) {
            this.mCollapsedTextGravity = gravity;
            recalculate();
        }
    }

    int getCollapsedTextGravity() {
        return this.mCollapsedTextGravity;
    }

    void setCollapsedTextAppearance(int resId) {
        TypedArray a = this.mView.getContext().obtainStyledAttributes(resId, C0180R.styleable.TextAppearance);
        if (a.hasValue(C0180R.styleable.TextAppearance_android_textColor)) {
            this.mCollapsedTextColor = a.getColorStateList(C0180R.styleable.TextAppearance_android_textColor);
        }
        if (a.hasValue(C0180R.styleable.TextAppearance_android_textSize)) {
            this.mCollapsedTextSize = (float) a.getDimensionPixelSize(C0180R.styleable.TextAppearance_android_textSize, (int) this.mCollapsedTextSize);
        }
        this.mCollapsedShadowColor = a.getInt(C0180R.styleable.TextAppearance_android_shadowColor, 0);
        this.mCollapsedShadowDx = a.getFloat(C0180R.styleable.TextAppearance_android_shadowDx, 0.0f);
        this.mCollapsedShadowDy = a.getFloat(C0180R.styleable.TextAppearance_android_shadowDy, 0.0f);
        this.mCollapsedShadowRadius = a.getFloat(C0180R.styleable.TextAppearance_android_shadowRadius, 0.0f);
        a.recycle();
        if (VERSION.SDK_INT >= 16) {
            this.mCollapsedTypeface = readFontFamilyTypeface(resId);
        }
        recalculate();
    }

    void setExpandedTextAppearance(int resId) {
        TypedArray a = this.mView.getContext().obtainStyledAttributes(resId, C0180R.styleable.TextAppearance);
        if (a.hasValue(C0180R.styleable.TextAppearance_android_textColor)) {
            this.mExpandedTextColor = a.getColorStateList(C0180R.styleable.TextAppearance_android_textColor);
        }
        if (a.hasValue(C0180R.styleable.TextAppearance_android_textSize)) {
            this.mExpandedTextSize = (float) a.getDimensionPixelSize(C0180R.styleable.TextAppearance_android_textSize, (int) this.mExpandedTextSize);
        }
        this.mExpandedShadowColor = a.getInt(C0180R.styleable.TextAppearance_android_shadowColor, 0);
        this.mExpandedShadowDx = a.getFloat(C0180R.styleable.TextAppearance_android_shadowDx, 0.0f);
        this.mExpandedShadowDy = a.getFloat(C0180R.styleable.TextAppearance_android_shadowDy, 0.0f);
        this.mExpandedShadowRadius = a.getFloat(C0180R.styleable.TextAppearance_android_shadowRadius, 0.0f);
        a.recycle();
        if (VERSION.SDK_INT >= 16) {
            this.mExpandedTypeface = readFontFamilyTypeface(resId);
        }
        recalculate();
    }

    void setMaxLines(int maxLines) {
        if (maxLines != this.maxLines) {
            this.maxLines = maxLines;
            clearTexture();
            recalculate();
        }
    }

    int getMaxLines() {
        return this.maxLines;
    }

    private Typeface readFontFamilyTypeface(int resId) {
        TypedArray a = this.mView.getContext().obtainStyledAttributes(resId, new int[]{16843692});
        try {
            String family = a.getString(0);
            if (family != null) {
                Typeface create = Typeface.create(family, 0);
                return create;
            }
            a.recycle();
            return null;
        } finally {
            a.recycle();
        }
    }

    void setCollapsedTypeface(Typeface typeface) {
        if (this.mCollapsedTypeface != typeface) {
            this.mCollapsedTypeface = typeface;
            recalculate();
        }
    }

    void setExpandedTypeface(Typeface typeface) {
        if (this.mExpandedTypeface != typeface) {
            this.mExpandedTypeface = typeface;
            recalculate();
        }
    }

    void setTypefaces(Typeface typeface) {
        this.mExpandedTypeface = typeface;
        this.mCollapsedTypeface = typeface;
        recalculate();
    }

    Typeface getCollapsedTypeface() {
        return this.mCollapsedTypeface != null ? this.mCollapsedTypeface : Typeface.DEFAULT;
    }

    Typeface getExpandedTypeface() {
        return this.mExpandedTypeface != null ? this.mExpandedTypeface : Typeface.DEFAULT;
    }

    void setExpansionFraction(float fraction) {
        fraction = MathUtils.constrain(fraction, 0.0f, 1.0f);
        if (fraction != this.mExpandedFraction) {
            this.mExpandedFraction = fraction;
            calculateCurrentOffsets();
        }
    }

    final boolean setState(int[] state) {
        this.mState = state;
        if (!isStateful()) {
            return false;
        }
        recalculate();
        return true;
    }

    final boolean isStateful() {
        return (this.mCollapsedTextColor != null && this.mCollapsedTextColor.isStateful()) || (this.mExpandedTextColor != null && this.mExpandedTextColor.isStateful());
    }

    float getExpansionFraction() {
        return this.mExpandedFraction;
    }

    float getCollapsedTextSize() {
        return this.mCollapsedTextSize;
    }

    float getExpandedTextSize() {
        return this.mExpandedTextSize;
    }

    private void calculateCurrentOffsets() {
        calculateOffsets(this.mExpandedFraction);
    }

    private void calculateOffsets(float fraction) {
        interpolateBounds(fraction);
        this.mCurrentDrawX = lerp(this.mExpandedDrawX, this.mCollapsedDrawX, fraction, this.mPositionInterpolator);
        this.mCurrentDrawY = lerp(this.mExpandedDrawY, this.mCollapsedDrawY, fraction, this.mPositionInterpolator);
        setInterpolatedTextSize(lerp(this.mExpandedTextSize, this.mCollapsedTextSize, fraction, this.mTextSizeInterpolator));
        setCollapsedTextBlend(1.0f - lerp(0.0f, 1.0f, 1.0f - fraction, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        setExpandedTextBlend(lerp(1.0f, 0.0f, fraction, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        if (this.mCollapsedTextColor != this.mExpandedTextColor) {
            this.mTextPaint.setColor(blendColors(getCurrentExpandedTextColor(), getCurrentCollapsedTextColor(), fraction));
        } else {
            this.mTextPaint.setColor(getCurrentCollapsedTextColor());
        }
        this.mTextPaint.setShadowLayer(lerp(this.mExpandedShadowRadius, this.mCollapsedShadowRadius, fraction, null), lerp(this.mExpandedShadowDx, this.mCollapsedShadowDx, fraction, null), lerp(this.mExpandedShadowDy, this.mCollapsedShadowDy, fraction, null), blendColors(this.mExpandedShadowColor, this.mCollapsedShadowColor, fraction));
        ViewCompat.postInvalidateOnAnimation(this.mView);
    }

    @ColorInt
    private int getCurrentExpandedTextColor() {
        if (this.mState != null) {
            return this.mExpandedTextColor.getColorForState(this.mState, 0);
        }
        return this.mExpandedTextColor.getDefaultColor();
    }

    @ColorInt
    private int getCurrentCollapsedTextColor() {
        if (this.mState != null) {
            return this.mCollapsedTextColor.getColorForState(this.mState, 0);
        }
        return this.mCollapsedTextColor.getDefaultColor();
    }

    private void calculateBaseOffsets() {
        float width;
        int i;
        float textHeight;
        float lineLeft;
        int i2 = 1;
        float currentTextSize = this.mCurrentTextSize;
        calculateUsingTextSize(this.mCollapsedTextSize);
        this.mTextToDrawCollapsed = this.mTextToDraw;
        if (this.mTextToDrawCollapsed != null) {
            width = this.mTextPaint.measureText(this.mTextToDrawCollapsed, 0, this.mTextToDrawCollapsed.length());
        } else {
            width = 0.0f;
        }
        int i3 = this.mCollapsedTextGravity;
        if (this.mIsRtl) {
            i = 1;
        } else {
            i = 0;
        }
        int collapsedAbsGravity = GravityCompat.getAbsoluteGravity(i3, i);
        if (this.mTextLayout != null) {
            textHeight = (float) this.mTextLayout.getHeight();
        } else {
            textHeight = 0.0f;
        }
        switch (collapsedAbsGravity & 112) {
            case 48:
                this.mCollapsedDrawY = (float) this.mCollapsedBounds.top;
                break;
            case 80:
                this.mCollapsedDrawY = ((float) this.mCollapsedBounds.bottom) - textHeight;
                break;
            default:
                this.mCollapsedDrawY = ((float) this.mCollapsedBounds.centerY()) - (textHeight / 2.0f);
                break;
        }
        switch (collapsedAbsGravity & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK) {
            case 1:
                this.mCollapsedDrawX = ((float) this.mCollapsedBounds.centerX()) - (width / 2.0f);
                break;
            case 5:
                this.mCollapsedDrawX = ((float) this.mCollapsedBounds.right) - width;
                break;
            default:
                this.mCollapsedDrawX = (float) this.mCollapsedBounds.left;
                break;
        }
        calculateUsingTextSize(this.mExpandedTextSize);
        width = this.mTextLayout != null ? this.mTextLayout.getLineWidth(0) : 0.0f;
        if (this.mTextLayout != null) {
            lineLeft = this.mTextLayout.getLineLeft(0);
        } else {
            lineLeft = 0.0f;
        }
        this.mExpandedFirstLineDrawX = lineLeft;
        i = this.mExpandedTextGravity;
        if (!this.mIsRtl) {
            i2 = 0;
        }
        int expandedAbsGravity = GravityCompat.getAbsoluteGravity(i, i2);
        if (this.mTextLayout != null) {
            textHeight = (float) this.mTextLayout.getHeight();
        } else {
            textHeight = 0.0f;
        }
        switch (expandedAbsGravity & 112) {
            case 48:
                this.mExpandedDrawY = (float) this.mExpandedBounds.top;
                break;
            case 80:
                this.mExpandedDrawY = ((float) this.mExpandedBounds.bottom) - textHeight;
                break;
            default:
                this.mExpandedDrawY = ((float) this.mExpandedBounds.centerY()) - (textHeight / 2.0f);
                break;
        }
        switch (expandedAbsGravity & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK) {
            case 1:
                this.mExpandedDrawX = ((float) this.mExpandedBounds.centerX()) - (width / 2.0f);
                break;
            case 5:
                this.mExpandedDrawX = ((float) this.mExpandedBounds.right) - width;
                break;
            default:
                this.mExpandedDrawX = (float) this.mExpandedBounds.left;
                break;
        }
        clearTexture();
        setInterpolatedTextSize(currentTextSize);
    }

    private void interpolateBounds(float fraction) {
        this.mCurrentBounds.left = lerp((float) this.mExpandedBounds.left, (float) this.mCollapsedBounds.left, fraction, this.mPositionInterpolator);
        this.mCurrentBounds.top = lerp(this.mExpandedDrawY, this.mCollapsedDrawY, fraction, this.mPositionInterpolator);
        this.mCurrentBounds.right = lerp((float) this.mExpandedBounds.right, (float) this.mCollapsedBounds.right, fraction, this.mPositionInterpolator);
        this.mCurrentBounds.bottom = lerp((float) this.mExpandedBounds.bottom, (float) this.mCollapsedBounds.bottom, fraction, this.mPositionInterpolator);
    }

    public void draw(Canvas canvas) {
        int saveCount = canvas.save();
        if (this.mTextToDraw != null && this.mDrawTitle) {
            boolean drawTexture;
            float ascent;
            float x = this.mCurrentDrawX;
            float y = this.mCurrentDrawY;
            if (!this.mUseTexture || this.mExpandedTitleTexture == null) {
                drawTexture = false;
            } else {
                drawTexture = true;
            }
            this.mTextPaint.setTextSize(this.mCurrentTextSize);
            if (drawTexture) {
                ascent = 0.0f;
            } else {
                ascent = this.mTextPaint.ascent() * this.mScale;
            }
            if (this.mScale != 1.0f) {
                canvas.scale(this.mScale, this.mScale, x, y);
            }
            float currentExpandedX = (this.mCurrentDrawX + this.mTextLayout.getLineLeft(0)) - (this.mExpandedFirstLineDrawX * 2.0f);
            if (drawTexture) {
                this.mTexturePaint.setAlpha((int) (this.mExpandedTextBlend * 255.0f));
                canvas.drawBitmap(this.mExpandedTitleTexture, currentExpandedX, y, this.mTexturePaint);
                this.mTexturePaint.setAlpha((int) (this.mCollapsedTextBlend * 255.0f));
                canvas.drawBitmap(this.mCollapsedTitleTexture, x, y, this.mTexturePaint);
                this.mTexturePaint.setAlpha(255);
                canvas.drawBitmap(this.mCrossSectionTitleTexture, x, y, this.mTexturePaint);
            } else {
                canvas.translate(currentExpandedX, y);
                this.mTextPaint.setAlpha((int) (this.mExpandedTextBlend * 255.0f));
                this.mTextLayout.draw(canvas);
                canvas.translate(x - currentExpandedX, 0.0f);
                this.mTextPaint.setAlpha((int) (this.mCollapsedTextBlend * 255.0f));
                canvas.drawText(this.mTextToDrawCollapsed, 0, this.mTextToDrawCollapsed.length(), 0.0f, (-ascent) / this.mScale, this.mTextPaint);
                this.mTextPaint.setAlpha(255);
                Canvas canvas2 = canvas;
                canvas2.drawText(this.mTextToDraw, this.mTextLayout.getLineStart(0), this.mTextLayout.getLineEnd(0), 0.0f, (-ascent) / this.mScale, this.mTextPaint);
            }
        }
        canvas.restoreToCount(saveCount);
    }

    private boolean calculateIsRtl(CharSequence text) {
        boolean defaultIsRtl = true;
        if (ViewCompat.getLayoutDirection(this.mView) != 1) {
            defaultIsRtl = false;
        }
        return (defaultIsRtl ? TextDirectionHeuristicsCompat.FIRSTSTRONG_RTL : TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR).isRtl(text, 0, text.length());
    }

    private void setInterpolatedTextSize(float textSize) {
        calculateUsingTextSize(textSize);
        boolean z = USE_SCALING_TEXTURE && this.mScale != 1.0f;
        this.mUseTexture = z;
        if (this.mUseTexture) {
            ensureExpandedTexture();
            ensureCollapsedTexture();
            ensureCrossSectionTexture();
        }
        ViewCompat.postInvalidateOnAnimation(this.mView);
    }

    private void setCollapsedTextBlend(float blend) {
        this.mCollapsedTextBlend = blend;
        ViewCompat.postInvalidateOnAnimation(this.mView);
    }

    private void setExpandedTextBlend(float blend) {
        this.mExpandedTextBlend = blend;
        ViewCompat.postInvalidateOnAnimation(this.mView);
    }

    private void calculateUsingTextSize(float textSize) {
        if (this.mText != null) {
            float newTextSize;
            float availableWidth;
            int maxLines;
            float collapsedWidth = (float) this.mCollapsedBounds.width();
            float expandedWidth = (float) this.mExpandedBounds.width();
            boolean updateDrawText = false;
            if (isClose(textSize, this.mCollapsedTextSize)) {
                newTextSize = this.mCollapsedTextSize;
                this.mScale = 1.0f;
                if (this.mCurrentTypeface != this.mCollapsedTypeface) {
                    this.mCurrentTypeface = this.mCollapsedTypeface;
                    updateDrawText = true;
                }
                availableWidth = collapsedWidth;
                maxLines = 1;
            } else {
                newTextSize = this.mExpandedTextSize;
                if (this.mCurrentTypeface != this.mExpandedTypeface) {
                    this.mCurrentTypeface = this.mExpandedTypeface;
                    updateDrawText = true;
                }
                if (isClose(textSize, this.mExpandedTextSize)) {
                    this.mScale = 1.0f;
                } else {
                    this.mScale = textSize / this.mExpandedTextSize;
                }
                float textSizeRatio = this.mCollapsedTextSize / this.mExpandedTextSize;
                if (expandedWidth * textSizeRatio > collapsedWidth) {
                    availableWidth = Math.min(collapsedWidth / textSizeRatio, expandedWidth);
                } else {
                    availableWidth = expandedWidth;
                }
                maxLines = this.maxLines;
            }
            if (availableWidth > 0.0f) {
                updateDrawText = this.mCurrentTextSize != newTextSize || this.mBoundsChanged || updateDrawText;
                this.mCurrentTextSize = newTextSize;
                this.mBoundsChanged = false;
            }
            if (this.mTextToDraw == null || updateDrawText) {
                CharSequence truncatedText;
                Alignment alignment;
                this.mTextPaint.setTextSize(this.mCurrentTextSize);
                this.mTextPaint.setTypeface(this.mCurrentTypeface);
                StaticLayout layout = new StaticLayout(this.mText, this.mTextPaint, (int) availableWidth, Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
                if (layout.getLineCount() > maxLines) {
                    int lastLine = maxLines - 1;
                    CharSequence textBefore = lastLine > 0 ? this.mText.subSequence(0, layout.getLineEnd(lastLine - 1)) : "";
                    CharSequence lineText = this.mText.subSequence(layout.getLineStart(lastLine), layout.getLineEnd(lastLine));
                    CharSequence lineEnd = "";
                    if (lineText.charAt(lineText.length() - 1) == ' ') {
                        lineEnd = lineText.subSequence(lineText.length() - 1, lineText.length());
                        lineText = lineText.subSequence(0, lineText.length() - 1);
                    }
                    CharSequence truncatedLineText = TextUtils.ellipsize(TextUtils.concat(new CharSequence[]{lineText, "…", lineEnd}), this.mTextPaint, availableWidth, TruncateAt.END);
                    truncatedText = TextUtils.concat(new CharSequence[]{textBefore, truncatedLineText});
                } else {
                    truncatedText = this.mText;
                }
                if (!TextUtils.equals(truncatedText, this.mTextToDraw)) {
                    this.mTextToDraw = truncatedText;
                    this.mIsRtl = calculateIsRtl(this.mTextToDraw);
                }
                switch (this.mExpandedTextGravity & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK) {
                    case 1:
                        alignment = Alignment.ALIGN_CENTER;
                        break;
                    case 5:
                    case GravityCompat.END /*8388613*/:
                        alignment = Alignment.ALIGN_OPPOSITE;
                        break;
                    default:
                        alignment = Alignment.ALIGN_NORMAL;
                        break;
                }
                this.mTextLayout = new StaticLayout(this.mTextToDraw, this.mTextPaint, (int) availableWidth, alignment, 1.0f, 0.0f, false);
            }
        }
    }

    private void ensureExpandedTexture() {
        if (this.mExpandedTitleTexture == null && !this.mExpandedBounds.isEmpty() && !TextUtils.isEmpty(this.mTextToDraw)) {
            calculateOffsets(0.0f);
            int w = this.mTextLayout.getWidth();
            int h = this.mTextLayout.getHeight();
            if (w > 0 && h > 0) {
                this.mExpandedTitleTexture = Bitmap.createBitmap(w, h, Config.ARGB_8888);
                this.mTextLayout.draw(new Canvas(this.mExpandedTitleTexture));
                if (this.mTexturePaint == null) {
                    this.mTexturePaint = new Paint(3);
                }
            }
        }
    }

    private void ensureCollapsedTexture() {
        if (this.mCollapsedTitleTexture == null && !this.mCollapsedBounds.isEmpty() && !TextUtils.isEmpty(this.mTextToDraw)) {
            calculateOffsets(0.0f);
            int w = Math.round(this.mTextPaint.measureText(this.mTextToDraw, 0, this.mTextToDraw.length()));
            int h = Math.round(this.mTextPaint.descent() - this.mTextPaint.ascent());
            if (w > 0 || h > 0) {
                this.mCollapsedTitleTexture = Bitmap.createBitmap(w, h, Config.ARGB_8888);
                new Canvas(this.mCollapsedTitleTexture).drawText(this.mTextToDrawCollapsed, 0, this.mTextToDrawCollapsed.length(), 0.0f, (-this.mTextPaint.ascent()) / this.mScale, this.mTextPaint);
                if (this.mTexturePaint == null) {
                    this.mTexturePaint = new Paint(3);
                }
            }
        }
    }

    private void ensureCrossSectionTexture() {
        if (this.mCrossSectionTitleTexture == null && !this.mCollapsedBounds.isEmpty() && !TextUtils.isEmpty(this.mTextToDraw)) {
            calculateOffsets(0.0f);
            int w = Math.round(this.mTextPaint.measureText(this.mTextToDraw, this.mTextLayout.getLineStart(0), this.mTextLayout.getLineEnd(0)));
            int h = Math.round(this.mTextPaint.descent() - this.mTextPaint.ascent());
            if (w > 0 || h > 0) {
                this.mCrossSectionTitleTexture = Bitmap.createBitmap(w, h, Config.ARGB_8888);
                new Canvas(this.mCrossSectionTitleTexture).drawText(this.mTextToDraw, this.mTextLayout.getLineStart(0), this.mTextLayout.getLineEnd(0), 0.0f, (-this.mTextPaint.ascent()) / this.mScale, this.mTextPaint);
                if (this.mTexturePaint == null) {
                    this.mTexturePaint = new Paint(3);
                }
            }
        }
    }

    public void recalculate() {
        if (this.mView.getHeight() > 0 && this.mView.getWidth() > 0) {
            calculateBaseOffsets();
            calculateCurrentOffsets();
        }
    }

    void setText(CharSequence text) {
        if (text == null || !text.equals(this.mText)) {
            this.mText = text;
            this.mTextToDraw = null;
            clearTexture();
            recalculate();
        }
    }

    CharSequence getText() {
        return this.mText;
    }

    private void clearTexture() {
        if (this.mExpandedTitleTexture != null) {
            this.mExpandedTitleTexture.recycle();
            this.mExpandedTitleTexture = null;
        }
        if (this.mCollapsedTitleTexture != null) {
            this.mCollapsedTitleTexture.recycle();
            this.mCollapsedTitleTexture = null;
        }
        if (this.mCrossSectionTitleTexture != null) {
            this.mCrossSectionTitleTexture.recycle();
            this.mCrossSectionTitleTexture = null;
        }
    }

    private static boolean isClose(float value, float targetValue) {
        return Math.abs(value - targetValue) < 0.001f;
    }

    ColorStateList getExpandedTextColor() {
        return this.mExpandedTextColor;
    }

    ColorStateList getCollapsedTextColor() {
        return this.mCollapsedTextColor;
    }

    private static int blendColors(int color1, int color2, float ratio) {
        float inverseRatio = 1.0f - ratio;
        return Color.argb((int) ((((float) Color.alpha(color1)) * inverseRatio) + (((float) Color.alpha(color2)) * ratio)), (int) ((((float) Color.red(color1)) * inverseRatio) + (((float) Color.red(color2)) * ratio)), (int) ((((float) Color.green(color1)) * inverseRatio) + (((float) Color.green(color2)) * ratio)), (int) ((((float) Color.blue(color1)) * inverseRatio) + (((float) Color.blue(color2)) * ratio)));
    }

    private static float lerp(float startValue, float endValue, float fraction, Interpolator interpolator) {
        if (interpolator != null) {
            fraction = interpolator.getInterpolation(fraction);
        }
        return AnimationUtils.lerp(startValue, endValue, fraction);
    }

    private static boolean rectEquals(Rect r, int left, int top, int right, int bottom) {
        return r.left == left && r.top == top && r.right == right && r.bottom == bottom;
    }
}
