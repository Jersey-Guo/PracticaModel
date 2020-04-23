package com.jersey.practicalmodel.base;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import java.util.Stack;

public class PageManager {

    private static Stack<Activity> activityStack;
    private static Stack<Fragment> fragmentStack;

    private static class InstanceHolder {
        private static PageManager INSTANCE = new PageManager();
    }

    /**
     * signInstance
     */
    public static PageManager getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private PageManager() {
    }


    /**
     * get all activitys stack
     */
    public static Stack<Activity> getActivityStack() {
        return activityStack;
    }

    /**
     * get all fragments stack
     */
    public static Stack<Fragment> getFragmentStack() {
        return fragmentStack;
    }

    /**
     * add activity to stack
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * remove activity from stack
     */
    public void removeActivity(Activity activity) {
        if (activityStack != null) {
            activityStack.remove(activity);
        }
    }

    /**
     * is have activity in stack
     */
    public boolean hasActivity() {
        return activityStack != null && !activityStack.isEmpty();
    }

    /**
     * get current activity from stack
     */
    public Activity getCurrentActivity() {
        return activityStack == null ? null : activityStack.lastElement();
    }

    /**
     * get activity by class
     */
    public Activity getActivity(Class<?> cls) {
        if (activityStack != null)
            for (Activity activity : activityStack) {
                if (activity.getClass().equals(cls)) {
                    return activity;
                }
            }
        return null;
    }

    /**
     * finish current activity
     */
    public void finishActivity() {
        if (activityStack != null) {
            activityStack.lastElement().finish();
        }
    }

    /**
     * finish activity by @activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            activity.finish();
        }
    }

    /**
     * finish activity by @cls
     */
    public void finishActivity(Class<?> cls) {
        if (activityStack == null) {
            return;
        }
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                break;
            }
        }
    }

    /**
     * finish all activity
     */
    public void finishAllActivity() {
        if (activityStack == null) {
            return;
        }
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                finishActivity(activityStack.get(i));
            }
        }
        activityStack.clear();
    }


    /**
     * add fragment to stack
     */
    public void addFragment(Fragment fragment) {
        if (fragmentStack == null) {
            fragmentStack = new Stack<>();
        }
        fragmentStack.add(fragment);
    }

    /**
     * remove fragment from stack
     */
    public void removeFragment(Fragment fragment) {
        if (fragment != null) {
            fragmentStack.remove(fragment);
        }
    }


    /**
     * is have fragment
     */
    public boolean hasFragment() {
        return fragmentStack != null && !fragmentStack.isEmpty();
    }

    /**
     * get current fragment from stack
     */
    public Fragment currentFragment() {
        if (fragmentStack != null) {
            return fragmentStack.lastElement();
        }
        return null;
    }


    /**
     * exit app
     */
    public void AppExit() {
        try {
            finishAllActivity();
            //System.exit(0);
        } catch (Exception e) {
            activityStack.clear();
            e.printStackTrace();
        }
    }
}
