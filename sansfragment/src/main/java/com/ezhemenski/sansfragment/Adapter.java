package com.ezhemenski.sansfragment;

import android.support.annotation.NonNull;

interface Adapter<S> {
    @NonNull ViewHolder createViewHolder(@NonNull S screen, @NonNull Navigator<S> navigator);
}
