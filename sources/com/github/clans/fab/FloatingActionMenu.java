package com.github.clans.fab;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;

public class FloatingActionMenu extends ViewGroup {
    private static final int ANIMATION_DURATION = 300;
    private static final float CLOSED_PLUS_ROTATION = 0.0f;
    private static final int LABELS_POSITION_LEFT = 0;
    private static final int LABELS_POSITION_RIGHT = 1;
    private static final float OPENED_PLUS_ROTATION_LEFT = -135.0f;
    private static final float OPENED_PLUS_ROTATION_RIGHT = 135.0f;
    private static final int OPEN_DOWN = 1;
    private static final int OPEN_UP = 0;
    private int mAnimationDelayPerItem;
    private int mBackgroundColor;
    private int mButtonSpacing;
    private int mButtonsCount;
    private AnimatorSet mCloseAnimatorSet;
    private Interpolator mCloseInterpolator;
    private Typeface mCustomTypefaceFromFont;
    private ValueAnimator mHideBackgroundAnimator;
    private Drawable mIcon;
    private boolean mIconAnimated;
    private AnimatorSet mIconToggleSet;
    private ImageView mImageToggle;
    private Animation mImageToggleHideAnimation;
    private Animation mImageToggleShowAnimation;
    private boolean mIsAnimated;
    private boolean mIsMenuButtonAnimationRunning;
    private boolean mIsMenuOpening;
    private boolean mIsSetClosedOnTouchOutside;
    private int mLabelsColorNormal;
    private int mLabelsColorPressed;
    private int mLabelsColorRipple;
    private Context mLabelsContext;
    private int mLabelsCornerRadius;
    private int mLabelsEllipsize;
    private int mLabelsHideAnimation;
    private int mLabelsMargin;
    private int mLabelsMaxLines;
    private int mLabelsPaddingBottom;
    private int mLabelsPaddingLeft;
    private int mLabelsPaddingRight;
    private int mLabelsPaddingTop;
    private int mLabelsPosition;
    private int mLabelsShowAnimation;
    private boolean mLabelsShowShadow;
    private boolean mLabelsSingleLine;
    private int mLabelsStyle;
    private ColorStateList mLabelsTextColor;
    private float mLabelsTextSize;
    private int mLabelsVerticalOffset;
    private int mMaxButtonWidth;
    private FloatingActionButton mMenuButton;
    private Animation mMenuButtonHideAnimation;
    private Animation mMenuButtonShowAnimation;
    private int mMenuColorNormal;
    private int mMenuColorPressed;
    private int mMenuColorRipple;
    private int mMenuFabSize;
    private String mMenuLabelText;
    private boolean mMenuOpened;
    private int mMenuShadowColor;
    private float mMenuShadowRadius;
    private float mMenuShadowXOffset;
    private float mMenuShadowYOffset;
    private boolean mMenuShowShadow;
    private AnimatorSet mOpenAnimatorSet;
    private int mOpenDirection;
    private Interpolator mOpenInterpolator;
    private ValueAnimator mShowBackgroundAnimator;
    private OnMenuToggleListener mToggleListener;
    private Handler mUiHandler;
    private boolean mUsingMenuLabel;

    /* renamed from: com.github.clans.fab.FloatingActionMenu$3 */
    class C02623 implements OnClickListener {
        C02623() {
        }

        public void onClick(View v) {
            FloatingActionMenu.this.toggle(FloatingActionMenu.this.mIsAnimated);
        }
    }

    /* renamed from: com.github.clans.fab.FloatingActionMenu$5 */
    class C02645 implements Runnable {
        C02645() {
        }

        public void run() {
            FloatingActionMenu.this.mMenuOpened = true;
            if (FloatingActionMenu.this.mToggleListener != null) {
                FloatingActionMenu.this.mToggleListener.onMenuToggle(true);
            }
        }
    }

    /* renamed from: com.github.clans.fab.FloatingActionMenu$7 */
    class C02667 implements Runnable {
        C02667() {
        }

        public void run() {
            FloatingActionMenu.this.mMenuOpened = false;
            if (FloatingActionMenu.this.mToggleListener != null) {
                FloatingActionMenu.this.mToggleListener.onMenuToggle(false);
            }
        }
    }

    public interface OnMenuToggleListener {
        void onMenuToggle(boolean z);
    }

    public FloatingActionMenu(Context context) {
        this(context, null);
    }

    public FloatingActionMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FloatingActionMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mOpenAnimatorSet = new AnimatorSet();
        this.mCloseAnimatorSet = new AnimatorSet();
        this.mButtonSpacing = Util.dpToPx(getContext(), 0.0f);
        this.mLabelsMargin = Util.dpToPx(getContext(), 0.0f);
        this.mLabelsVerticalOffset = Util.dpToPx(getContext(), 0.0f);
        this.mUiHandler = new Handler();
        this.mLabelsPaddingTop = Util.dpToPx(getContext(), 4.0f);
        this.mLabelsPaddingRight = Util.dpToPx(getContext(), 8.0f);
        this.mLabelsPaddingBottom = Util.dpToPx(getContext(), 4.0f);
        this.mLabelsPaddingLeft = Util.dpToPx(getContext(), 8.0f);
        this.mLabelsCornerRadius = Util.dpToPx(getContext(), 3.0f);
        this.mMenuShadowRadius = 4.0f;
        this.mMenuShadowXOffset = 1.0f;
        this.mMenuShadowYOffset = 3.0f;
        this.mIsAnimated = true;
        this.mIconAnimated = true;
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray attr = context.obtainStyledAttributes(attrs, C0271R.styleable.FloatingActionMenu, 0, 0);
        this.mButtonSpacing = attr.getDimensionPixelSize(C0271R.styleable.FloatingActionMenu_menu_buttonSpacing, this.mButtonSpacing);
        this.mLabelsMargin = attr.getDimensionPixelSize(C0271R.styleable.FloatingActionMenu_menu_labels_margin, this.mLabelsMargin);
        this.mLabelsPosition = attr.getInt(C0271R.styleable.FloatingActionMenu_menu_labels_position, 0);
        this.mLabelsShowAnimation = attr.getResourceId(C0271R.styleable.FloatingActionMenu_menu_labels_showAnimation, this.mLabelsPosition == 0 ? C0271R.anim.fab_slide_in_from_right : C0271R.anim.fab_slide_in_from_left);
        this.mLabelsHideAnimation = attr.getResourceId(C0271R.styleable.FloatingActionMenu_menu_labels_hideAnimation, this.mLabelsPosition == 0 ? C0271R.anim.fab_slide_out_to_right : C0271R.anim.fab_slide_out_to_left);
        this.mLabelsPaddingTop = attr.getDimensionPixelSize(C0271R.styleable.FloatingActionMenu_menu_labels_paddingTop, this.mLabelsPaddingTop);
        this.mLabelsPaddingRight = attr.getDimensionPixelSize(C0271R.styleable.FloatingActionMenu_menu_labels_paddingRight, this.mLabelsPaddingRight);
        this.mLabelsPaddingBottom = attr.getDimensionPixelSize(C0271R.styleable.FloatingActionMenu_menu_labels_paddingBottom, this.mLabelsPaddingBottom);
        this.mLabelsPaddingLeft = attr.getDimensionPixelSize(C0271R.styleable.FloatingActionMenu_menu_labels_paddingLeft, this.mLabelsPaddingLeft);
        this.mLabelsTextColor = attr.getColorStateList(C0271R.styleable.FloatingActionMenu_menu_labels_textColor);
        if (this.mLabelsTextColor == null) {
            this.mLabelsTextColor = ColorStateList.valueOf(-1);
        }
        this.mLabelsTextSize = attr.getDimension(C0271R.styleable.FloatingActionMenu_menu_labels_textSize, getResources().getDimension(C0271R.dimen.labels_text_size));
        this.mLabelsCornerRadius = attr.getDimensionPixelSize(C0271R.styleable.FloatingActionMenu_menu_labels_cornerRadius, this.mLabelsCornerRadius);
        this.mLabelsShowShadow = attr.getBoolean(C0271R.styleable.FloatingActionMenu_menu_labels_showShadow, true);
        this.mLabelsColorNormal = attr.getColor(C0271R.styleable.FloatingActionMenu_menu_labels_colorNormal, -13421773);
        this.mLabelsColorPressed = attr.getColor(C0271R.styleable.FloatingActionMenu_menu_labels_colorPressed, -12303292);
        this.mLabelsColorRipple = attr.getColor(C0271R.styleable.FloatingActionMenu_menu_labels_colorRipple, 1728053247);
        this.mMenuShowShadow = attr.getBoolean(C0271R.styleable.FloatingActionMenu_menu_showShadow, true);
        this.mMenuShadowColor = attr.getColor(C0271R.styleable.FloatingActionMenu_menu_shadowColor, 1711276032);
        this.mMenuShadowRadius = attr.getDimension(C0271R.styleable.FloatingActionMenu_menu_shadowRadius, this.mMenuShadowRadius);
        this.mMenuShadowXOffset = attr.getDimension(C0271R.styleable.FloatingActionMenu_menu_shadowXOffset, this.mMenuShadowXOffset);
        this.mMenuShadowYOffset = attr.getDimension(C0271R.styleable.FloatingActionMenu_menu_shadowYOffset, this.mMenuShadowYOffset);
        this.mMenuColorNormal = attr.getColor(C0271R.styleable.FloatingActionMenu_menu_colorNormal, -2473162);
        this.mMenuColorPressed = attr.getColor(C0271R.styleable.FloatingActionMenu_menu_colorPressed, -1617853);
        this.mMenuColorRipple = attr.getColor(C0271R.styleable.FloatingActionMenu_menu_colorRipple, -1711276033);
        this.mAnimationDelayPerItem = attr.getInt(C0271R.styleable.FloatingActionMenu_menu_animationDelayPerItem, 50);
        this.mIcon = attr.getDrawable(C0271R.styleable.FloatingActionMenu_menu_icon);
        if (this.mIcon == null) {
            this.mIcon = getResources().getDrawable(C0271R.drawable.fab_add);
        }
        this.mLabelsSingleLine = attr.getBoolean(C0271R.styleable.FloatingActionMenu_menu_labels_singleLine, false);
        this.mLabelsEllipsize = attr.getInt(C0271R.styleable.FloatingActionMenu_menu_labels_ellipsize, 0);
        this.mLabelsMaxLines = attr.getInt(C0271R.styleable.FloatingActionMenu_menu_labels_maxLines, -1);
        this.mMenuFabSize = attr.getInt(C0271R.styleable.FloatingActionMenu_menu_fab_size, 0);
        this.mLabelsStyle = attr.getResourceId(C0271R.styleable.FloatingActionMenu_menu_labels_style, 0);
        String customFont = attr.getString(C0271R.styleable.FloatingActionMenu_menu_labels_customFont);
        try {
            if (!TextUtils.isEmpty(customFont)) {
                this.mCustomTypefaceFromFont = Typeface.createFromAsset(getContext().getAssets(), customFont);
            }
            this.mOpenDirection = attr.getInt(C0271R.styleable.FloatingActionMenu_menu_openDirection, 0);
            this.mBackgroundColor = attr.getColor(C0271R.styleable.FloatingActionMenu_menu_backgroundColor, 0);
            if (attr.hasValue(C0271R.styleable.FloatingActionMenu_menu_fab_label)) {
                this.mUsingMenuLabel = true;
                this.mMenuLabelText = attr.getString(C0271R.styleable.FloatingActionMenu_menu_fab_label);
            }
            if (attr.hasValue(C0271R.styleable.FloatingActionMenu_menu_labels_padding)) {
                initPadding(attr.getDimensionPixelSize(C0271R.styleable.FloatingActionMenu_menu_labels_padding, 0));
            }
            this.mOpenInterpolator = new OvershootInterpolator();
            this.mCloseInterpolator = new AnticipateInterpolator();
            this.mLabelsContext = new ContextThemeWrapper(getContext(), this.mLabelsStyle);
            initBackgroundDimAnimation();
            createMenuButton();
            initMenuButtonAnimations(attr);
            attr.recycle();
        } catch (RuntimeException ex) {
            throw new IllegalArgumentException("Unable to load specified custom font: " + customFont, ex);
        }
    }

    private void initMenuButtonAnimations(TypedArray attr) {
        int showResId = attr.getResourceId(C0271R.styleable.FloatingActionMenu_menu_fab_show_animation, C0271R.anim.fab_scale_up);
        setMenuButtonShowAnimation(AnimationUtils.loadAnimation(getContext(), showResId));
        this.mImageToggleShowAnimation = AnimationUtils.loadAnimation(getContext(), showResId);
        int hideResId = attr.getResourceId(C0271R.styleable.FloatingActionMenu_menu_fab_hide_animation, C0271R.anim.fab_scale_down);
        setMenuButtonHideAnimation(AnimationUtils.loadAnimation(getContext(), hideResId));
        this.mImageToggleHideAnimation = AnimationUtils.loadAnimation(getContext(), hideResId);
    }

    private void initBackgroundDimAnimation() {
        int maxAlpha = Color.alpha(this.mBackgroundColor);
        final int red = Color.red(this.mBackgroundColor);
        final int green = Color.green(this.mBackgroundColor);
        final int blue = Color.blue(this.mBackgroundColor);
        this.mShowBackgroundAnimator = ValueAnimator.ofInt(new int[]{0, maxAlpha});
        this.mShowBackgroundAnimator.setDuration(300);
        this.mShowBackgroundAnimator.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                FloatingActionMenu.this.setBackgroundColor(Color.argb(((Integer) animation.getAnimatedValue()).intValue(), red, green, blue));
            }
        });
        this.mHideBackgroundAnimator = ValueAnimator.ofInt(new int[]{maxAlpha, 0});
        this.mHideBackgroundAnimator.setDuration(300);
        this.mHideBackgroundAnimator.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                FloatingActionMenu.this.setBackgroundColor(Color.argb(((Integer) animation.getAnimatedValue()).intValue(), red, green, blue));
            }
        });
    }

    private boolean isBackgroundEnabled() {
        return this.mBackgroundColor != 0;
    }

    private void initPadding(int padding) {
        this.mLabelsPaddingTop = padding;
        this.mLabelsPaddingRight = padding;
        this.mLabelsPaddingBottom = padding;
        this.mLabelsPaddingLeft = padding;
    }

    private void createMenuButton() {
        this.mMenuButton = new FloatingActionButton(getContext());
        this.mMenuButton.mShowShadow = this.mMenuShowShadow;
        if (this.mMenuShowShadow) {
            this.mMenuButton.mShadowRadius = Util.dpToPx(getContext(), this.mMenuShadowRadius);
            this.mMenuButton.mShadowXOffset = Util.dpToPx(getContext(), this.mMenuShadowXOffset);
            this.mMenuButton.mShadowYOffset = Util.dpToPx(getContext(), this.mMenuShadowYOffset);
        }
        this.mMenuButton.setColors(this.mMenuColorNormal, this.mMenuColorPressed, this.mMenuColorRipple);
        this.mMenuButton.mShadowColor = this.mMenuShadowColor;
        this.mMenuButton.mFabSize = this.mMenuFabSize;
        this.mMenuButton.updateBackground();
        this.mMenuButton.setLabelText(this.mMenuLabelText);
        this.mImageToggle = new ImageView(getContext());
        this.mImageToggle.setImageDrawable(this.mIcon);
        addView(this.mMenuButton, super.generateDefaultLayoutParams());
        addView(this.mImageToggle);
        createDefaultIconAnimation();
    }

    private void createDefaultIconAnimation() {
        float collapseAngle;
        float expandAngle;
        if (this.mOpenDirection == 0) {
            collapseAngle = this.mLabelsPosition == 0 ? OPENED_PLUS_ROTATION_LEFT : OPENED_PLUS_ROTATION_RIGHT;
            expandAngle = this.mLabelsPosition == 0 ? OPENED_PLUS_ROTATION_LEFT : OPENED_PLUS_ROTATION_RIGHT;
        } else {
            collapseAngle = this.mLabelsPosition == 0 ? OPENED_PLUS_ROTATION_RIGHT : OPENED_PLUS_ROTATION_LEFT;
            expandAngle = this.mLabelsPosition == 0 ? OPENED_PLUS_ROTATION_RIGHT : OPENED_PLUS_ROTATION_LEFT;
        }
        ObjectAnimator collapseAnimator = ObjectAnimator.ofFloat(this.mImageToggle, "rotation", new float[]{collapseAngle, 0.0f});
        this.mOpenAnimatorSet.play(ObjectAnimator.ofFloat(this.mImageToggle, "rotation", new float[]{0.0f, expandAngle}));
        this.mCloseAnimatorSet.play(collapseAnimator);
        this.mOpenAnimatorSet.setInterpolator(this.mOpenInterpolator);
        this.mCloseAnimatorSet.setInterpolator(this.mCloseInterpolator);
        this.mOpenAnimatorSet.setDuration(300);
        this.mCloseAnimatorSet.setDuration(300);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int i;
        int height = 0;
        this.mMaxButtonWidth = 0;
        int maxLabelWidth = 0;
        measureChildWithMargins(this.mImageToggle, widthMeasureSpec, 0, heightMeasureSpec, 0);
        for (i = 0; i < this.mButtonsCount; i++) {
            View child = getChildAt(i);
            if (!(child.getVisibility() == 8 || child == this.mImageToggle)) {
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);
                this.mMaxButtonWidth = Math.max(this.mMaxButtonWidth, child.getMeasuredWidth());
            }
        }
        for (i = 0; i < this.mButtonsCount; i++) {
            child = getChildAt(i);
            if (!(child.getVisibility() == 8 || child == this.mImageToggle)) {
                int usedWidth = 0 + child.getMeasuredWidth();
                height += child.getMeasuredHeight();
                Label label = (Label) child.getTag(C0271R.id.fab_label);
                if (label != null) {
                    int labelOffset = (this.mMaxButtonWidth - child.getMeasuredWidth()) / (this.mUsingMenuLabel ? 1 : 2);
                    measureChildWithMargins(label, widthMeasureSpec, ((child.getMeasuredWidth() + label.calculateShadowWidth()) + this.mLabelsMargin) + labelOffset, heightMeasureSpec, 0);
                    maxLabelWidth = Math.max(maxLabelWidth, (usedWidth + label.getMeasuredWidth()) + labelOffset);
                }
            }
        }
        int width = (Math.max(this.mMaxButtonWidth, this.mLabelsMargin + maxLabelWidth) + getPaddingLeft()) + getPaddingRight();
        height = adjustForOvershoot(height + (((this.mButtonSpacing * (this.mButtonsCount - 1)) + getPaddingTop()) + getPaddingBottom()));
        if (getLayoutParams().width == -1) {
            width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        }
        if (getLayoutParams().height == -1) {
            height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        }
        setMeasuredDimension(width, height);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int buttonsHorizontalCenter;
        int menuButtonTop;
        int nextY;
        if (this.mLabelsPosition == 0) {
            buttonsHorizontalCenter = ((r - l) - (this.mMaxButtonWidth / 2)) - getPaddingRight();
        } else {
            buttonsHorizontalCenter = (this.mMaxButtonWidth / 2) + getPaddingLeft();
        }
        boolean openUp = this.mOpenDirection == 0;
        if (openUp) {
            menuButtonTop = ((b - t) - this.mMenuButton.getMeasuredHeight()) - getPaddingBottom();
        } else {
            menuButtonTop = getPaddingTop();
        }
        int menuButtonLeft = buttonsHorizontalCenter - (this.mMenuButton.getMeasuredWidth() / 2);
        this.mMenuButton.layout(menuButtonLeft, menuButtonTop, this.mMenuButton.getMeasuredWidth() + menuButtonLeft, this.mMenuButton.getMeasuredHeight() + menuButtonTop);
        int imageLeft = buttonsHorizontalCenter - (this.mImageToggle.getMeasuredWidth() / 2);
        int imageTop = ((this.mMenuButton.getMeasuredHeight() / 2) + menuButtonTop) - (this.mImageToggle.getMeasuredHeight() / 2);
        this.mImageToggle.layout(imageLeft, imageTop, this.mImageToggle.getMeasuredWidth() + imageLeft, this.mImageToggle.getMeasuredHeight() + imageTop);
        if (openUp) {
            nextY = (this.mMenuButton.getMeasuredHeight() + menuButtonTop) + this.mButtonSpacing;
        } else {
            nextY = menuButtonTop;
        }
        for (int i = this.mButtonsCount - 1; i >= 0; i--) {
            View child = getChildAt(i);
            if (child != this.mImageToggle) {
                FloatingActionButton fab = (FloatingActionButton) child;
                if (fab.getVisibility() != 8) {
                    int childY;
                    int childX = buttonsHorizontalCenter - (fab.getMeasuredWidth() / 2);
                    if (openUp) {
                        childY = (nextY - fab.getMeasuredHeight()) - this.mButtonSpacing;
                    } else {
                        childY = nextY;
                    }
                    if (fab != this.mMenuButton) {
                        fab.layout(childX, childY, fab.getMeasuredWidth() + childX, fab.getMeasuredHeight() + childY);
                        if (!this.mIsMenuOpening) {
                            fab.hide(false);
                        }
                    }
                    View label = (View) fab.getTag(C0271R.id.fab_label);
                    if (label != null) {
                        int labelXAwayFromButton;
                        int labelLeft;
                        int labelRight;
                        int labelsOffset = (this.mUsingMenuLabel ? this.mMaxButtonWidth / 2 : fab.getMeasuredWidth() / 2) + this.mLabelsMargin;
                        int labelXNearButton = this.mLabelsPosition == 0 ? buttonsHorizontalCenter - labelsOffset : buttonsHorizontalCenter + labelsOffset;
                        if (this.mLabelsPosition == 0) {
                            labelXAwayFromButton = labelXNearButton - label.getMeasuredWidth();
                        } else {
                            labelXAwayFromButton = labelXNearButton + label.getMeasuredWidth();
                        }
                        if (this.mLabelsPosition == 0) {
                            labelLeft = labelXAwayFromButton;
                        } else {
                            labelLeft = labelXNearButton;
                        }
                        if (this.mLabelsPosition == 0) {
                            labelRight = labelXNearButton;
                        } else {
                            labelRight = labelXAwayFromButton;
                        }
                        int labelTop = (childY - this.mLabelsVerticalOffset) + ((fab.getMeasuredHeight() - label.getMeasuredHeight()) / 2);
                        label.layout(labelLeft, labelTop, labelRight, label.getMeasuredHeight() + labelTop);
                        if (!this.mIsMenuOpening) {
                            label.setVisibility(4);
                        }
                    }
                    if (openUp) {
                        nextY = childY - this.mButtonSpacing;
                    } else {
                        nextY = (child.getMeasuredHeight() + childY) + this.mButtonSpacing;
                    }
                }
            }
        }
    }

    private int adjustForOvershoot(int dimension) {
        return (int) ((((double) dimension) * 0.03d) + ((double) dimension));
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        bringChildToFront(this.mMenuButton);
        bringChildToFront(this.mImageToggle);
        this.mButtonsCount = getChildCount();
        createLabels();
    }

    private void createLabels() {
        for (int i = 0; i < this.mButtonsCount; i++) {
            if (getChildAt(i) != this.mImageToggle) {
                FloatingActionButton fab = (FloatingActionButton) getChildAt(i);
                if (fab.getTag(C0271R.id.fab_label) == null) {
                    addLabel(fab);
                    if (fab == this.mMenuButton) {
                        this.mMenuButton.setOnClickListener(new C02623());
                    }
                }
            }
        }
    }

    private void addLabel(FloatingActionButton fab) {
        String text = fab.getLabelText();
        if (!TextUtils.isEmpty(text)) {
            Label label = new Label(this.mLabelsContext);
            label.setClickable(true);
            label.setFab(fab);
            label.setShowAnimation(AnimationUtils.loadAnimation(getContext(), this.mLabelsShowAnimation));
            label.setHideAnimation(AnimationUtils.loadAnimation(getContext(), this.mLabelsHideAnimation));
            if (this.mLabelsStyle > 0) {
                label.setTextAppearance(getContext(), this.mLabelsStyle);
                label.setShowShadow(false);
                label.setUsingStyle(true);
            } else {
                label.setColors(this.mLabelsColorNormal, this.mLabelsColorPressed, this.mLabelsColorRipple);
                label.setShowShadow(this.mLabelsShowShadow);
                label.setCornerRadius(this.mLabelsCornerRadius);
                if (this.mLabelsEllipsize > 0) {
                    setLabelEllipsize(label);
                }
                label.setMaxLines(this.mLabelsMaxLines);
                label.updateBackground();
                label.setTextSize(0, this.mLabelsTextSize);
                label.setTextColor(this.mLabelsTextColor);
                int left = this.mLabelsPaddingLeft;
                int top = this.mLabelsPaddingTop;
                if (this.mLabelsShowShadow) {
                    left += fab.getShadowRadius() + Math.abs(fab.getShadowXOffset());
                    top += fab.getShadowRadius() + Math.abs(fab.getShadowYOffset());
                }
                label.setPadding(left, top, this.mLabelsPaddingLeft, this.mLabelsPaddingTop);
                if (this.mLabelsMaxLines < 0 || this.mLabelsSingleLine) {
                    label.setSingleLine(this.mLabelsSingleLine);
                }
            }
            if (this.mCustomTypefaceFromFont != null) {
                label.setTypeface(this.mCustomTypefaceFromFont);
            }
            label.setText(text);
            label.setOnClickListener(fab.getOnClickListener());
            addView(label);
            fab.setTag(C0271R.id.fab_label, label);
        }
    }

    private void setLabelEllipsize(Label label) {
        switch (this.mLabelsEllipsize) {
            case 1:
                label.setEllipsize(TruncateAt.START);
                return;
            case 2:
                label.setEllipsize(TruncateAt.MIDDLE);
                return;
            case 3:
                label.setEllipsize(TruncateAt.END);
                return;
            case 4:
                label.setEllipsize(TruncateAt.MARQUEE);
                return;
            default:
                return;
        }
    }

    public MarginLayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    protected MarginLayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    protected MarginLayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(-2, -2);
    }

    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof MarginLayoutParams;
    }

    private void hideMenuButtonWithImage(boolean animate) {
        if (!isMenuButtonHidden()) {
            this.mMenuButton.hide(animate);
            if (animate) {
                this.mImageToggle.startAnimation(this.mImageToggleHideAnimation);
            }
            this.mImageToggle.setVisibility(4);
            this.mIsMenuButtonAnimationRunning = false;
        }
    }

    private void showMenuButtonWithImage(boolean animate) {
        if (isMenuButtonHidden()) {
            this.mMenuButton.show(animate);
            if (animate) {
                this.mImageToggle.startAnimation(this.mImageToggleShowAnimation);
            }
            this.mImageToggle.setVisibility(0);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (!this.mIsSetClosedOnTouchOutside) {
            return super.onTouchEvent(event);
        }
        switch (event.getAction()) {
            case 0:
                return isOpened();
            case 1:
                close(this.mIsAnimated);
                return true;
            default:
                return false;
        }
    }

    public boolean isOpened() {
        return this.mMenuOpened;
    }

    public void toggle(boolean animate) {
        if (isOpened()) {
            close(animate);
        } else {
            open(animate);
        }
    }

    public void open(final boolean animate) {
        if (!isOpened()) {
            if (isBackgroundEnabled()) {
                this.mShowBackgroundAnimator.start();
            }
            if (this.mIconAnimated) {
                if (this.mIconToggleSet != null) {
                    this.mIconToggleSet.start();
                } else {
                    this.mCloseAnimatorSet.cancel();
                    this.mOpenAnimatorSet.start();
                }
            }
            int delay = 0;
            int counter = 0;
            this.mIsMenuOpening = true;
            for (int i = getChildCount() - 1; i >= 0; i--) {
                View child = getChildAt(i);
                if ((child instanceof FloatingActionButton) && child.getVisibility() != 8) {
                    counter++;
                    final FloatingActionButton fab = (FloatingActionButton) child;
                    this.mUiHandler.postDelayed(new Runnable() {
                        public void run() {
                            if (!FloatingActionMenu.this.isOpened()) {
                                if (fab != FloatingActionMenu.this.mMenuButton) {
                                    fab.show(animate);
                                }
                                Label label = (Label) fab.getTag(C0271R.id.fab_label);
                                if (label != null && label.isHandleVisibilityChanges()) {
                                    label.show(animate);
                                }
                            }
                        }
                    }, (long) delay);
                    delay += this.mAnimationDelayPerItem;
                }
            }
            this.mUiHandler.postDelayed(new C02645(), (long) (this.mAnimationDelayPerItem * (counter + 1)));
        }
    }

    public void close(final boolean animate) {
        if (isOpened()) {
            if (isBackgroundEnabled()) {
                this.mHideBackgroundAnimator.start();
            }
            if (this.mIconAnimated) {
                if (this.mIconToggleSet != null) {
                    this.mIconToggleSet.start();
                } else {
                    this.mCloseAnimatorSet.start();
                    this.mOpenAnimatorSet.cancel();
                }
            }
            int delay = 0;
            int counter = 0;
            this.mIsMenuOpening = false;
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                if ((child instanceof FloatingActionButton) && child.getVisibility() != 8) {
                    counter++;
                    final FloatingActionButton fab = (FloatingActionButton) child;
                    this.mUiHandler.postDelayed(new Runnable() {
                        public void run() {
                            if (FloatingActionMenu.this.isOpened()) {
                                if (fab != FloatingActionMenu.this.mMenuButton) {
                                    fab.hide(animate);
                                }
                                Label label = (Label) fab.getTag(C0271R.id.fab_label);
                                if (label != null && label.isHandleVisibilityChanges()) {
                                    label.hide(animate);
                                }
                            }
                        }
                    }, (long) delay);
                    delay += this.mAnimationDelayPerItem;
                }
            }
            this.mUiHandler.postDelayed(new C02667(), (long) (this.mAnimationDelayPerItem * (counter + 1)));
        }
    }

    public void setIconAnimationInterpolator(Interpolator interpolator) {
        this.mOpenAnimatorSet.setInterpolator(interpolator);
        this.mCloseAnimatorSet.setInterpolator(interpolator);
    }

    public void setIconAnimationOpenInterpolator(Interpolator openInterpolator) {
        this.mOpenAnimatorSet.setInterpolator(openInterpolator);
    }

    public void setIconAnimationCloseInterpolator(Interpolator closeInterpolator) {
        this.mCloseAnimatorSet.setInterpolator(closeInterpolator);
    }

    public void setAnimated(boolean animated) {
        long j;
        long j2 = 300;
        this.mIsAnimated = animated;
        AnimatorSet animatorSet = this.mOpenAnimatorSet;
        if (animated) {
            j = 300;
        } else {
            j = 0;
        }
        animatorSet.setDuration(j);
        AnimatorSet animatorSet2 = this.mCloseAnimatorSet;
        if (!animated) {
            j2 = 0;
        }
        animatorSet2.setDuration(j2);
    }

    public boolean isAnimated() {
        return this.mIsAnimated;
    }

    public void setAnimationDelayPerItem(int animationDelayPerItem) {
        this.mAnimationDelayPerItem = animationDelayPerItem;
    }

    public int getAnimationDelayPerItem() {
        return this.mAnimationDelayPerItem;
    }

    public void setOnMenuToggleListener(OnMenuToggleListener listener) {
        this.mToggleListener = listener;
    }

    public void setIconAnimated(boolean animated) {
        this.mIconAnimated = animated;
    }

    public boolean isIconAnimated() {
        return this.mIconAnimated;
    }

    public ImageView getMenuIconView() {
        return this.mImageToggle;
    }

    public void setIconToggleAnimatorSet(AnimatorSet toggleAnimatorSet) {
        this.mIconToggleSet = toggleAnimatorSet;
    }

    public AnimatorSet getIconToggleAnimatorSet() {
        return this.mIconToggleSet;
    }

    public void setMenuButtonShowAnimation(Animation showAnimation) {
        this.mMenuButtonShowAnimation = showAnimation;
        this.mMenuButton.setShowAnimation(showAnimation);
    }

    public void setMenuButtonHideAnimation(Animation hideAnimation) {
        this.mMenuButtonHideAnimation = hideAnimation;
        this.mMenuButton.setHideAnimation(hideAnimation);
    }

    public boolean isMenuHidden() {
        return getVisibility() == 4;
    }

    public boolean isMenuButtonHidden() {
        return this.mMenuButton.isHidden();
    }

    public void showMenu(boolean animate) {
        if (isMenuHidden()) {
            if (animate) {
                startAnimation(this.mMenuButtonShowAnimation);
            }
            setVisibility(0);
        }
    }

    public void hideMenu(final boolean animate) {
        if (!isMenuHidden() && !this.mIsMenuButtonAnimationRunning) {
            this.mIsMenuButtonAnimationRunning = true;
            if (isOpened()) {
                close(animate);
                this.mUiHandler.postDelayed(new Runnable() {
                    public void run() {
                        if (animate) {
                            FloatingActionMenu.this.startAnimation(FloatingActionMenu.this.mMenuButtonHideAnimation);
                        }
                        FloatingActionMenu.this.setVisibility(4);
                        FloatingActionMenu.this.mIsMenuButtonAnimationRunning = false;
                    }
                }, (long) (this.mAnimationDelayPerItem * this.mButtonsCount));
                return;
            }
            if (animate) {
                startAnimation(this.mMenuButtonHideAnimation);
            }
            setVisibility(4);
            this.mIsMenuButtonAnimationRunning = false;
        }
    }

    public void toggleMenu(boolean animate) {
        if (isMenuHidden()) {
            showMenu(animate);
        } else {
            hideMenu(animate);
        }
    }

    public void showMenuButton(boolean animate) {
        if (isMenuButtonHidden()) {
            showMenuButtonWithImage(animate);
        }
    }

    public void hideMenuButton(final boolean animate) {
        if (!isMenuButtonHidden() && !this.mIsMenuButtonAnimationRunning) {
            this.mIsMenuButtonAnimationRunning = true;
            if (isOpened()) {
                close(animate);
                this.mUiHandler.postDelayed(new Runnable() {
                    public void run() {
                        FloatingActionMenu.this.hideMenuButtonWithImage(animate);
                    }
                }, (long) (this.mAnimationDelayPerItem * this.mButtonsCount));
                return;
            }
            hideMenuButtonWithImage(animate);
        }
    }

    public void toggleMenuButton(boolean animate) {
        if (isMenuButtonHidden()) {
            showMenuButton(animate);
        } else {
            hideMenuButton(animate);
        }
    }

    public void setClosedOnTouchOutside(boolean close) {
        this.mIsSetClosedOnTouchOutside = close;
    }

    public void setMenuButtonColorNormal(int color) {
        this.mMenuColorNormal = color;
        this.mMenuButton.setColorNormal(color);
    }

    public void setMenuButtonColorNormalResId(int colorResId) {
        this.mMenuColorNormal = getResources().getColor(colorResId);
        this.mMenuButton.setColorNormalResId(colorResId);
    }

    public int getMenuButtonColorNormal() {
        return this.mMenuColorNormal;
    }

    public void setMenuButtonColorPressed(int color) {
        this.mMenuColorPressed = color;
        this.mMenuButton.setColorPressed(color);
    }

    public void setMenuButtonColorPressedResId(int colorResId) {
        this.mMenuColorPressed = getResources().getColor(colorResId);
        this.mMenuButton.setColorPressedResId(colorResId);
    }

    public int getMenuButtonColorPressed() {
        return this.mMenuColorPressed;
    }

    public void setMenuButtonColorRipple(int color) {
        this.mMenuColorRipple = color;
        this.mMenuButton.setColorRipple(color);
    }

    public void setMenuButtonColorRippleResId(int colorResId) {
        this.mMenuColorRipple = getResources().getColor(colorResId);
        this.mMenuButton.setColorRippleResId(colorResId);
    }

    public int getMenuButtonColorRipple() {
        return this.mMenuColorRipple;
    }

    public void addMenuButton(FloatingActionButton fab) {
        addView(fab, this.mButtonsCount - 2);
        this.mButtonsCount++;
        addLabel(fab);
    }

    public void removeMenuButton(FloatingActionButton fab) {
        removeView(fab.getLabelView());
        removeView(fab);
        this.mButtonsCount--;
    }

    public void addMenuButton(FloatingActionButton fab, int index) {
        int size = this.mButtonsCount - 2;
        if (index < 0) {
            index = 0;
        } else if (index > size) {
            index = size;
        }
        addView(fab, index);
        this.mButtonsCount++;
        addLabel(fab);
    }

    public void removeAllMenuButtons() {
        close(true);
        List<FloatingActionButton> viewsToRemove = new ArrayList();
        for (int i = 0; i < getChildCount(); i++) {
            View v = getChildAt(i);
            if (!(v == this.mMenuButton || v == this.mImageToggle || !(v instanceof FloatingActionButton))) {
                viewsToRemove.add((FloatingActionButton) v);
            }
        }
        for (FloatingActionButton v2 : viewsToRemove) {
            removeMenuButton(v2);
        }
    }

    public void setMenuButtonLabelText(String text) {
        this.mMenuButton.setLabelText(text);
    }

    public String getMenuButtonLabelText() {
        return this.mMenuLabelText;
    }

    public void setOnMenuButtonClickListener(OnClickListener clickListener) {
        this.mMenuButton.setOnClickListener(clickListener);
    }

    public void setOnMenuButtonLongClickListener(OnLongClickListener longClickListener) {
        this.mMenuButton.setOnLongClickListener(longClickListener);
    }
}
