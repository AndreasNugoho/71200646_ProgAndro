// Generated by view binder compiler. Do not edit!
package com.pemrogandroid.catatantempat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.pemrogandroid.catatantempat.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMapsBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final Button btnKampus;

  @NonNull
  public final Button btnMyLoc;

  @NonNull
  public final Button btnRumah;

  @NonNull
  public final FrameLayout container;

  private ActivityMapsBinding(@NonNull FrameLayout rootView, @NonNull Button btnKampus,
      @NonNull Button btnMyLoc, @NonNull Button btnRumah, @NonNull FrameLayout container) {
    this.rootView = rootView;
    this.btnKampus = btnKampus;
    this.btnMyLoc = btnMyLoc;
    this.btnRumah = btnRumah;
    this.container = container;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMapsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMapsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_maps, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMapsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnKampus;
      Button btnKampus = ViewBindings.findChildViewById(rootView, id);
      if (btnKampus == null) {
        break missingId;
      }

      id = R.id.btnMyLoc;
      Button btnMyLoc = ViewBindings.findChildViewById(rootView, id);
      if (btnMyLoc == null) {
        break missingId;
      }

      id = R.id.btnRumah;
      Button btnRumah = ViewBindings.findChildViewById(rootView, id);
      if (btnRumah == null) {
        break missingId;
      }

      FrameLayout container = (FrameLayout) rootView;

      return new ActivityMapsBinding((FrameLayout) rootView, btnKampus, btnMyLoc, btnRumah,
          container);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
