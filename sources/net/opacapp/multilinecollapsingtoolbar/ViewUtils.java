package net.opacapp.multilinecollapsingtoolbar;

import android.graphics.PorterDuff.Mode;
import android.os.Build.VERSION;

class ViewUtils {
    static final Creator DEFAULT_ANIMATOR_CREATOR = new C06051();

    /* renamed from: net.opacapp.multilinecollapsingtoolbar.ViewUtils$1 */
    static class C06051 implements Creator {
        C06051() {
        }

        public ValueAnimatorCompat createAnimator() {
            return new ValueAnimatorCompat(VERSION.SDK_INT >= 12 ? new ValueAnimatorCompatImplHoneycombMr1() : new ValueAnimatorCompatImplGingerbread());
        }
    }

    ViewUtils() {
    }

    static ValueAnimatorCompat createAnimator() {
        return DEFAULT_ANIMATOR_CREATOR.createAnimator();
    }

    static boolean objectEquals(Object a, Object b) {
        return a == b || (a != null && a.equals(b));
    }

    static Mode parseTintMode(int value, Mode defaultMode) {
        switch (value) {
            case 3:
                return Mode.SRC_OVER;
            case 5:
                return Mode.SRC_IN;
            case 9:
                return Mode.SRC_ATOP;
            case 14:
                return Mode.MULTIPLY;
            case 15:
                return Mode.SCREEN;
            default:
                return defaultMode;
        }
    }
}
