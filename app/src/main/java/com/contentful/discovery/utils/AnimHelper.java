package com.contentful.discovery.utils;

import android.animation.Animator;
import android.os.Build;

/**
 * Animations Helper.
 */
public class AnimHelper {
    public static void startOrResumeAnimator(Animator animator) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (animator.isPaused()) {
                animator.resume();
                return;
            }
        }

        animator.start();
    }

    public static void pauseOrStopAnimator(Animator animator) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            animator.pause();
        } else {
            animator.cancel();
        }
    }
}
