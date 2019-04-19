/*
 * Copyright 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.material.picker.selector;

import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import com.google.android.material.picker.MaterialCalendar;
import com.google.android.material.picker.MonthAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import java.util.Calendar;

/**
 * Interface for users of {@link MaterialCalendar<S>} to control how the Calendar displays and
 * returns selections.
 *
 * <p>Implementors must implement {@link Parcelable} so that selection can be maintained through
 * Lifecycle events (e.g., Fragment destruction).
 *
 * @param <S> The type of item available when cells are selected in the {@link AdapterView}
 * @hide
 */
@RestrictTo(Scope.LIBRARY_GROUP)
public interface GridSelector<S> extends Parcelable {

  /** Returns the current selection */
  @Nullable
  S getSelection();

  /**
   * Modifies the {@link AdapterView} to represent its current selection state
   *
   * <p>This method is called when the {@link AdapterView} is first shown and when clicks change its
   * state.
   *
   * @param parent The view that holds the selection (e.g., {@link android.widget.GridView})
   */
  void drawSelection(AdapterView<? extends MonthAdapter> parent);

  /**
   * Allows this selection handler to respond to clicks within the {@link AdapterView}
   *
   * @param parent The view that holds the selection (e.g., {@link android.widget.GridView})
   * @param view The view that was clicked
   * @param position The index of the item clicked in parent
   * @param row The row of the item clicked in parent
   */
  void changeSelection(
      AdapterView<? extends MonthAdapter> parent, View view, int position, long row);

  /**
   * Modifies the provided {@link View} to indicate its selection status.
   *
   * <p>Called from {@link GridSelector#drawSelection(AdapterView)} for each {@link View}.
   *
   * @param cell The {@link View} returned from {@link MonthAdapter#getView(int, View,
   *     ViewGroup)}
   * @param item The {@link Calendar} returned from {@link MonthAdapter#getItem(int)}.
   */
  void drawCell(View cell, Calendar item);
}